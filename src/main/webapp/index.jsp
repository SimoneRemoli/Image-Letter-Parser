<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="it">
<head>
  <meta charset="UTF-8" />
  <title>Carica Foto</title>
  <style>
    body { font-family: system-ui, sans-serif; margin: 2rem; }
    .card { max-width: 680px; padding: 1.25rem; border: 1px solid #ddd; border-radius: 14px; box-shadow: 0 2px 8px rgba(0,0,0,.06); }
    label { display:block; margin: .5rem 0 .25rem; font-weight:600; }
    .row { display:flex; gap:1rem; align-items:center; }
    .row > * { flex:1; }
    button { padding:.7rem 1rem; border-radius:10px; border:0; cursor:pointer; }
    .primary { background:#0b5ed7; color:#fff; }
    .muted { color:#555; font-size:.9rem; }
  </style>
</head>
<body>
  <div class="card">
    <h1>Parsing Immagine</h1>
    <p class="muted">Carica la foto.</p>

<form action="ImageUploadServlet" method="post" enctype="multipart/form-data">
      <label for="file">Immagine del circuito (PNG/JPG)</label>
      <input id="file" name="file" type="file" accept="image/png,image/jpeg" required />
      <div style="align-self:end; text-align:right;">
                <button class="primary" type="submit">Elabora</button>
      </div>
    </form>
  </div>
</body>
</html>
