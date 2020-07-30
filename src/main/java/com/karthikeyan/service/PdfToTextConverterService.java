package com.karthikeyan.service;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;
import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.io.RandomAccessFile;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.junit.Test;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author Karthikeyan on 03/07/20
 */

@Service
public class PdfToTextConverterService {

    public String pdfToText(File inputFile) {
        String parsedText = "";
        PDFParser parser;
        try {
            parser = new PDFParser(new RandomAccessFile(inputFile, "r"));
            parser.parse();
            COSDocument cosDoc = parser.getDocument();
            PDFTextStripper pdfStripper = new PDFTextStripper();
            PDDocument pdDoc = new PDDocument(cosDoc);
            parsedText = pdfStripper.getText(pdDoc);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return parsedText;
    }

    @Test
    public void textToPdf() throws IOException {
        File file = new File("src/main/resources/application-AA031705.pdf");
        PdfReader reader = new PdfReader(file.getName());
        System.out.println(reader);
        ff(file);
        /*Document pdfDoc = new Document(PageSize.A4);
        try {
            PdfWriter.getInstance(pdfDoc, new FileOutputStream(""))
                    .setPdfVersion(PdfWriter.PDF_VERSION_1_7);
        } catch (FileNotFoundException | DocumentException e) {
            e.printStackTrace();
        }
        pdfDoc.open();*/
    }

    public String ff(File inputFile) {
        String parsedText = "";
        PDFParser parser;
        try {
            parser = new PDFParser(new RandomAccessFile(inputFile, "r"));
            parser.parse();
            COSDocument cosDoc = parser.getDocument();
            PDFTextStripper pdfStripper = new PDFTextStripper();
            PDDocument pdDoc = new PDDocument(cosDoc);
            parsedText = pdfStripper.getText(pdDoc);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return parsedText;
    }
}
