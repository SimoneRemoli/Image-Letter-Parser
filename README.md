<h1 align="center">
  Webapp Java (Tomcat + JSP/Servlet) PARSING IMAGE
</h1>
Image Letter Parser is a lightweight OCR-oriented tool that takes an input image, cleans it (binarization/denoise), segments characters, and outputs the extracted letters. Designed to be simple to run and easy to extend with different OCR backends or preprocessing steps.
<p align="center"> </p>


<p align="center"></p>
<h3 align="center">OCR/Tesseract (Faculty of Engineering) </h3>

<p align="center">
  <a href="https://www.gnu.org/licenses/gpl-3.0.html#license-text"><img src="https://img.shields.io/github/license/1Panel-dev/maxkb?color=%231890FF" alt="License: GPL v3"></a> 
</p>
<hr/>
<p align="center">Developers and Creators </p>
<table align="center"> <tr> <td align="center"> <a href="https://github.com/SimoneRemoli"> <img src="https://avatars.githubusercontent.com/u/118252611?v=4" width="100px;" alt=""/><br /> <sub><b>Simone Remoli</b></sub> </a> </td> </tr> </table>


### Built With

The technologies listed below constitute the foundational stack employed in the design and implementation of this system:
<p align="center"> <a href="https://www.oracle.com/java/" target="_blank"> <img src="https://img.icons8.com/color/96/000000/java-coffee-cup-logo.png" alt="Java" title="Java" width="60" style="border-radius: 50%;"/> </a> &nbsp;&nbsp; <a href="https://github.com/tesseract-ocr/tesseract" target="_blank"> <img src="https://www.heise.de/download/media/tesseract-ocr/tesseract-ocr_1-1-30.jpg" alt="TESSERACT" title="TESSERACT" width="60" style="border-radius: 50%;"/> </a> &nbsp;&nbsp; </p>


## 🚦 Overview

This project leverages the power of [Tesseract OCR](https://github.com/tesseract-ocr/tesseract) to extract text from images, opening up endless opportunities for document automation, data extraction, and more. With a flexible and modular approach, you can easily customize and expand the parsing pipeline to suit your needs.

### 1. Clone the Repository

```bash
git clone https://github.com/SimoneRemoli/Image-Letter-Parser.git
```

### 2. Install Tesseract

```bash
brew install tesseract
```

### 3. Add Tess4J in your POM

```bash
<dependency>
      <groupId>net.sourceforge.tess4j</groupId>
      <artifactId>tess4j</artifactId>
      <version>5.10.0</version>
</dependency>
```

### 4. Add VM options to Tomcat

IntelliJ → Run/Debug Configurations → Configuration SmartTomcat → VM options

```bash
-Djna.library.path=/usr/local/lib -Dtessdata.dir=/usr/local/share/tessdata
```
Note: Check 
```bash
ls /opt/homebrew/share/tessdata (o /usr/local/share/tessdata)
```
show inside eng.traineddata.


**Let’s turn images into actionable data!**

<img width="825" height="314" alt="Screenshot 2025-08-25 alle 11 22 03" src="https://github.com/user-attachments/assets/2bdfdc18-3c1e-47b6-a3f2-f687ada0fe07" />



