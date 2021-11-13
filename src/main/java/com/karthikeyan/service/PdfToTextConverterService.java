package com.karthikeyan.service;

import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.io.RandomAccessFile;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Service;

import java.io.File;
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
