package com.karthikeyan.service;

import com.cloudmersive.client.ConvertDocumentApi;
import com.cloudmersive.client.invoker.ApiClient;
import com.cloudmersive.client.invoker.ApiException;
import com.cloudmersive.client.invoker.Configuration;
import com.cloudmersive.client.invoker.auth.ApiKeyAuth;
import org.springframework.stereotype.Service;

import java.io.File;

/**
 * @author Karthikeyan on 03/07/20
 */

@Service
public class PdfToTextConverterService {

    public String pdfToText(File inputFile) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        ApiKeyAuth Apikey = (ApiKeyAuth) defaultClient.getAuthentication("Apikey");
        Apikey.setApiKey("adb466ae-d5ee-4cac-9f25-9f18045fd013");
//        Apikey.setApiKeyPrefix("Token");

        ConvertDocumentApi apiInstance = new ConvertDocumentApi();
        String textFormattingMode = "minimizeWhitespace"; // String | Optional; specify how whitespace should be handled when converting PDF to text.  Possible values are 'preserveWhitespace' which will attempt to preserve whitespace in the document and relative positioning of text within the document, and 'minimizeWhitespace' which will not insert additional spaces into the document in most cases.  Default is 'preserveWhitespace'.
        String result = "";
        try {
            result = apiInstance.convertDocumentPdfToTxt(inputFile, textFormattingMode).getTextResult();
        } catch (ApiException e) {
            e.printStackTrace();
        }
        return result.trim();
    }
}
