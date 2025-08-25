package com.example.servlet;

import org.apache.commons.io.FilenameUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

@WebServlet("/ImageUploadServlet")
@MultipartConfig(
        maxFileSize = 10 * 1024 * 1024,      // 10 MB
        maxRequestSize = 20 * 1024 * 1024,   // 20 MB
        fileSizeThreshold = 0
)
public class ImageUploadServlet extends HttpServlet {

    private Path uploadDir;

    @Override
    public void init() throws ServletException {
        try {
            // Configurabile con: -Dcircuiti.upload.dir=/percorso/assoluto
            String base = System.getProperty("circuiti.upload.dir",
                    System.getProperty("java.io.tmpdir"));
            uploadDir = Path.of(base, "circuiti-uploads");
            Files.createDirectories(uploadDir);
            getServletContext().log("Upload dir: " + uploadDir.toAbsolutePath());
        } catch (IOException e) {
            throw new ServletException("Impossibile creare la cartella di upload", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        // ---- Validazione file ----
        Part filePart = req.getPart("file");
        if (filePart == null || filePart.getSize() == 0) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "File immagine mancante");
            return;
        }

        String contentType = filePart.getContentType();
        if (contentType == null ||
                !(contentType.equalsIgnoreCase("image/png") || contentType.equalsIgnoreCase("image/jpeg"))) {
            resp.sendError(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE, "Sono supportati solo PNG/JPEG");
            return;
        }

        // Nome file sicuro
        String submitted = filePart.getSubmittedFileName();
        String baseName = FilenameUtils.getBaseName(submitted == null ? "img" : submitted);
        String ext = contentType.toLowerCase().contains("png") ? "png" : "jpg";
        String safeName = baseName.replaceAll("[^a-zA-Z0-9-_]", "_")
                + "-" + System.currentTimeMillis() + "." + ext;

        // ---- Salvataggio su disco ----
        Path target = uploadDir.resolve(safeName);
        try (InputStream in = filePart.getInputStream()) {
            Files.copy(in, target, StandardCopyOption.REPLACE_EXISTING);
        }

        //  OCR: estrai SOLO lettere
        SimpleOCRService simple = new SimpleOCRService();
        java.util.List<SimpleOCRService.DetectedChar> letters = simple.extractLetters(target);

        // ---- Attributi per la JSP ----
        req.setAttribute("uploadedFileName", safeName);
        req.setAttribute("uploadedFilePath", target.toAbsolutePath().toString());
        req.setAttribute("letters", letters);

        // Log console
        getServletContext().log("OCR letters: " + (letters == null ? 0 : letters.size())
                + " caratteri per " + safeName);

        // ---- Forward alla pagina di risultato lettere ----
        RequestDispatcher dispatcher = req.getRequestDispatcher("/result.jsp");
        dispatcher.forward(req, resp);
    }
}
