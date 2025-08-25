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

<img width="647" height="548" alt="Screenshot 2025-08-25 alle 11 23 57" src="https://github.com/user-attachments/assets/f9410b49-0d3e-4ad9-81e5-ab3a0dae662f" />


Example: **A — \[15,104,44,48]** means “the letter A is inside a bounding box that starts **15 px** from the left and **104 px** from the top, **44 px** wide and **48 px** tall.”

Ita - > Esempio: A — [15,104,44,48] significa “la lettera A è dentro un riquadro che inizia a 15 px da sinistra e 104 px dall’alto, largo 44 px e alto 48 px”.
Fra -> Exemple : A — [15,104,44,48] signifie « la lettre A se trouve dans un cadre (bounding box) qui commence à 15 px depuis la gauche et 104 px depuis le haut, d’une largeur de 44 px et d’une hauteur de 48 px. »
Kor -> 예: A — [15,104,44,48] 는 문자 A가 왼쪽에서 15px, 위쪽에서 104px 떨어진 지점에서 시작하는 바운딩 박스 안에 있으며, 너비 44px, 높이 48px임을 의미합니다.
Chin -> 示例：A — [15,104,44,48] 表示“字母 A 位于一个边界框内，该边界框从左侧 15 像素、顶部 104 像素处开始，宽 44 像素，高 48 像素。”.
