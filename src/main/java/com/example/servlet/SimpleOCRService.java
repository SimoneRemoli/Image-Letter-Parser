package com.example.servlet;

import net.sourceforge.tess4j.ITessAPI;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.Word;

import javax.imageio.ImageIO;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.nio.file.Path;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class SimpleOCRService {

    /** singolo carattere rilevato */
    public static class DetectedChar {
        public final String ch;          // lettera (A–Z)
        public final Rectangle box;      // bounding box nel piano immagine

        public DetectedChar(String ch, Rectangle box) {
            this.ch = ch;
            this.box = box;
        }
    }

    /** Estrae SOLO lettere [A–Z] con le loro bounding box. */
    public List<DetectedChar> extractLetters(Path imagePath) {
        List<DetectedChar> out = new ArrayList<>();
        try {
            ITesseract t = new Tesseract();
            String dp = System.getProperty("tessdata.dir");
            if (dp != null) t.setDatapath(dp);
            t.setLanguage("eng");

            BufferedImage bi = ImageIO.read(imagePath.toFile());
            // livello simbolo = caratteri singoli
            List<Word> symbols = t.getWords(bi, ITessAPI.TessPageIteratorLevel.RIL_SYMBOL);

            for (Word w : symbols) {
                String raw = w.getText();
                if (raw == null) continue;
                String ch = normalizeChar(raw);
                if (!ch.isEmpty() && ch.matches("[A-Z]")) {
                    out.add(new DetectedChar(ch, w.getBoundingBox()));
                }
            }
        } catch (UnsatisfiedLinkError | NoClassDefFoundError e) {
            System.err.println("OCR non disponibile (lib native mancanti): " + e.getMessage());
        } catch (Exception e) {
            System.err.println("OCR errore: " + e.getMessage());
        }
        return out;
    }

    /** Normalizza un simbolo OCR in una singola lettera A–Z (se possibile). */
    private String normalizeChar(String s) {
        String t = Normalizer.normalize(s, Normalizer.Form.NFKC)
                .toUpperCase(Locale.ROOT)
                .replaceAll("[^A-Z]", "");
        return t.isEmpty() ? "" : t.substring(0, 1);
    }
}
