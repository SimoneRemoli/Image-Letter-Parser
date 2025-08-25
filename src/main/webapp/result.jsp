<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="it">
<head>
  <meta charset="UTF-8" />
  <title>Lettere rilevate</title>
  <style>
    body { font-family: system-ui, sans-serif; margin: 2rem; }
    .wrap { max-width: 960px; }
    .tag { display:inline-block; padding:.25rem .5rem; background:#eef; border-radius:999px; font-size:.85rem; margin-right:.5rem; }
    .chips span { display:inline-block; padding:.2rem .45rem; border:1px solid #ddd; border-radius:8px; margin:.2rem; }
    img { max-width: 100%; height:auto; border-radius: 10px; border:1px solid #ddd; }
    .muted { color:#666; }
  </style>
</head>
<body>
<div class="wrap">
  <h1>Lettere rilevate</h1>

  <%
    String fileName = (String) request.getAttribute("uploadedFileName");
    String imgUrl = request.getContextPath() + "/img/" + java.net.URLEncoder.encode(fileName, "UTF-8");
    out.println("<p class='muted'>File: <code>" + fileName + "</code></p>");
    out.println("<img src=\"" + imgUrl + "\" alt='Immagine caricata'>");
  %>

  <hr/>

  <%
    java.util.List<com.example.servlet.SimpleOCRService.DetectedChar> letters =
        (java.util.List<com.example.servlet.SimpleOCRService.DetectedChar>) request.getAttribute("letters");

    if (letters != null && !letters.isEmpty()) {
        out.println("<h3>Lettere trovate (" + letters.size() + ")</h3>");
        out.println("<div class='chips'>");
        for (com.example.servlet.SimpleOCRService.DetectedChar d : letters) {
            out.print("<span>" + d.ch + "</span>");
        }
        out.println("</div>");

        // (facoltativo) elenco con bbox
        out.println("<details style='margin-top:1rem'><summary>Dettagli (bbox)</summary><ul>");
        for (com.example.servlet.SimpleOCRService.DetectedChar d : letters) {
            java.awt.Rectangle r = d.box;
            out.println("<li>" + d.ch + " — [" + r.x + "," + r.y + "," + r.width + "," + r.height + "]</li>");
        }
        out.println("</ul></details>");
    } else {
        out.println("<p><em>Nessuna lettera rilevata.</em></p>");
        out.println("<p class='muted'>Se usi macOS e vedi sempre vuoto, probabilmente manca Tesseract nativo. " +
                    "Installa con Homebrew e imposta <code>-Dtessdata.dir</code> nei parametri di avvio di Tomcat.</p>");
    }
  %>

  <p style="margin-top:2rem"><a href="<%= request.getContextPath() %>/index.jsp">↩︎ Torna al caricamento</a></p>
</div>
</body>
</html>
