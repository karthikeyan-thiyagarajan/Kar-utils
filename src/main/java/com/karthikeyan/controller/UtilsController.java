package com.karthikeyan.controller;

import com.karthikeyan.CompareResponses;
import com.karthikeyan.request.CompareRequest;
import com.karthikeyan.service.DiffMatchPatch;
import com.karthikeyan.service.DiffMatchPatch.Diff;
import com.karthikeyan.service.PdfToTextConverterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.LinkedList;


/**
 * @author Karthikeyan on 29/06/20
 */

@RestController
public class UtilsController {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private PdfToTextConverterService converterService;

    @GetMapping("/qr/{content}")
    public ResponseEntity<byte[]> qrScanner(@PathVariable String content) {
        String encoded = "";
        try {
            encoded = URLEncoder.encode(content, StandardCharsets.UTF_8.name());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        ResponseEntity<byte[]> forEntity = restTemplate.getForEntity("https://api.qrserver.com/v1/create-qr-code/?size=150x150&data=" + encoded, byte[].class);
        byte[] encode = Base64.getEncoder().encode(forEntity.getBody());
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(encode);
    }

    @PostMapping("/compare")
    public ResponseEntity<CompareResponses> sdfsfdds(@RequestBody CompareRequest req) {
        DiffMatchPatch diffMatchPatch = new DiffMatchPatch();
        LinkedList<Diff> diffs = diffMatchPatch.diff_main(req.getA(), req.getB());
        CompareResponses aggregate = diffMatchPatch.aggregate(diffs);
        System.out.println(aggregate);
        return ResponseEntity.ok().body(aggregate);
    }

    @PostMapping("/pdf-text")
    public ResponseEntity<String> pdfToText(@RequestParam("file") MultipartFile multipartFile) throws IOException {

        if (multipartFile.isEmpty()) {
            return ResponseEntity.ok().body("Please select a multipartFile to upload");
        }
        byte[] bytes = multipartFile.getBytes();
        Path path = Paths.get(multipartFile.getOriginalFilename());
        File write = Files.write(path, bytes).toFile();
        String text = converterService.pdfToText(write);
        if (write.delete()) {
            System.out.println("deleted");
        } else {
            System.out.println("not deleted");
        }
        return ResponseEntity.ok().body(text);
    }


}


