package com.karthikeyan.utils;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlTableDataCell;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.Test;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.File;
import java.io.IOException;

/**
 * @author Karthikeyan on 27/07/20
 */

public class HelperUtility {

    public static int processingOCR(String text) {
        System.out.println("-----" + text.replace("\n", "") + "-----");
        int result = 0;
        if (text.contains("less")) {
            text = text.replaceAll("[^a-zA-Z 0-9]", "");
            String[] a = text.split(" ");
            for (String aa : a) {
                if (aa.matches("[0-9]+")) {
                    result = Math.min(Integer.parseInt(aa), result);
                }
            }
        } else if (text.contains("great")) {
            text = text.replaceAll("[^a-zA-Z 0-9]", "");
            String[] a = text.split(" ");
            for (String aa : a) {
                if (aa.matches("[0-9]+")) {
                    result = Math.max(Integer.parseInt(aa), result);
                }
            }
        } else {
            result = expressionSolver(text);
        }
        return result;
    }

    /*public static String doOCR(File inputFile) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        ApiKeyAuth Apikey = (ApiKeyAuth) defaultClient.getAuthentication("Apikey");
        Apikey.setApiKey("adb466ae-d5ee-4cac-9f25-9f18045fd013");

        ImageToTextResponse result = null;
        ImageOcrApi apiInstance = new ImageOcrApi();
        try {
            result = apiInstance.imageOcrPost(inputFile, "Advanced", "English (ENG)", "Auto");
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling ImageOcrApi#imageOcrPost");
            e.printStackTrace();
        }
        return result.getTextResult();
    }*/

    public static WebClient getDriver(boolean javaScriptEnabled) {
        WebClient client = new WebClient(BrowserVersion.CHROME);
        client.getOptions().setCssEnabled(false);
        client.getOptions().setJavaScriptEnabled(javaScriptEnabled);
        client.getOptions().setUseInsecureSSL(true);
        client.getOptions().setThrowExceptionOnScriptError(false);
        client.getOptions().setThrowExceptionOnFailingStatusCode(false);
        client.waitForBackgroundJavaScript(10000);
        client.getCookieManager().setCookiesEnabled(true);
        client.setAjaxController(new NicelyResynchronizingAjaxController());
        return client;
    }

    public static String getGoogleGoldRate() throws IOException {
        Document doc;
        doc = Jsoup.connect("https://www.google.com/search?q=madurai+gold+rate").get();
        System.out.println("doc.text() = " + doc.text());
        Elements links = doc.getElementsByClass("vlzY6d");
        String goldRate = links.text();
        System.out.println("Gold Rate = " + goldRate);
        return goldRate;
    }

    public static String getBullionRates(WebClient client) throws IOException {
        String gold, silver;
        String bullion = "http://www.kjpl.in/";
        HtmlPage bullionPage = client.getPage(bullion);
        HtmlTableDataCell goldRate = bullionPage.getBody().getFirstByXPath("//*[@class='gold']");
        HtmlTableDataCell silverRate = bullionPage.getBody().getFirstByXPath("//*[@class='silver']");
        gold = "Bullion " + goldRate.asText();
        silver = "Bullion " + silverRate.asText();

        System.out.println(gold);
        System.out.println(silver);
        return gold + " /gram <br>" + silver + " /gram";
    }

    private static int expressionSolver(String text) {
        text = text.replaceAll("[^0-9*\\-/+]+", "");
        ScriptEngineManager mgr = new ScriptEngineManager();
        ScriptEngine engine = mgr.getEngineByName("JavaScript");
        try {
            return (int) engine.eval(text.replace(" ", ""));
        } catch (ScriptException e) {
            return -1;
        }

    }

    @Test
    public void dsdsf() {
        Tesseract instance = new Tesseract();
        try {
            instance.setDatapath("src/main/resources");
            String imgText = instance.doOCR(new File("target/file.jpg"));
            System.out.println(imgText);
        } catch (TesseractException e) {
            e.getMessage();

        }
    }
}
