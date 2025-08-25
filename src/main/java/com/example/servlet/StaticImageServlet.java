package com.example.servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.*;

@WebServlet("/img/*")

/*
Serve per visualizzare l'immagine caricata.
Entra in funzione solo quando arriva una richiesta HTTP.
La richiesta la genera automaticamente il browser, perchè ho messo:

      String imgUrl = request.getContextPath() + "/img/" + java.net.URLEncoder.encode(fileName, "UTF-8");
      out.println("<img src=\"" + imgUrl + "\" alt='Circuito' style='max-width:100%;height:auto;border:1px solid #ddd;border-radius:8px;'>");

      nel result.jsp.

 */
public class StaticImageServlet extends HttpServlet {
    private Path uploadDir; //percorso della cartella dove salvo gli upload

    @Override
    public void init() {
        String base = System.getProperty("circuiti.upload.dir",
                System.getProperty("java.io.tmpdir"));
        uploadDir = Path.of(base, "circuiti-uploads");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //Entra qui quando il browser chiede un URL che matcha il mapping della servlet (es. @WebServlet("/img/*")
        String pathInfo = req.getPathInfo();            // es: "/nomefile.jpg"
        if (pathInfo == null || pathInfo.length() <= 1) { resp.sendError(400); return; }

        Path file = uploadDir.resolve(pathInfo.substring(1)).normalize();

        /*substring(1) toglie lo slash iniziale ("/nome.jpg" → "nome.jpg").

        resolve(...).normalize():
        unisce in modo sicuro uploadDir con il nome file richiesto e
        normalizza il path (rimuove . e risolve eventuali ..).
         */

        // stop path traversal e not found
        if (!file.startsWith(uploadDir) || !Files.exists(file) || Files.isDirectory(file)) {
            resp.sendError(404);
            return;
        }

        String ct = Files.probeContentType(file);
        if (ct == null) {
            String name = file.getFileName().toString().toLowerCase();
            ct = name.endsWith(".png") ? "image/png" :
                    (name.endsWith(".jpg") || name.endsWith(".jpeg")) ? "image/jpeg" :
                            "application/octet-stream";
        }
        resp.setContentType(ct);
        resp.setHeader("Content-Disposition", "inline; filename=\"" + file.getFileName() + "\"");
        resp.setContentLengthLong(Files.size(file));

        try (OutputStream out = resp.getOutputStream()) {
            Files.copy(file, out);
        }

        System.out.println("ctx=" + req.getContextPath()
                + " servlet=" + req.getServletPath()
                + " pathInfo=" + req.getPathInfo()
                + " query=" + req.getQueryString());

    }
}
