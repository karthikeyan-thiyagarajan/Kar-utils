package com.karthikeyan.utils;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlTableDataCell;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.util.StringUtils;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.IOException;

/**
 * @author Karthikeyan on 27/07/20
 */

public class HelperUtility {

    private HelperUtility() {
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

    public static String getGoogleGoldRate() {
        try {
            Document doc;
            doc = Jsoup.connect("https://www.google.com/search?q=madurai+gold+rate").get();
            Elements links = doc.getElementsByClass("vlzY6d");
            String goldRate = links.text();
            System.out.println("Gold Rate = " + goldRate);
            return goldRate.replace(" Indian Rupee","");
        } catch (IOException e) {
            return "";
        }
    }

    public static String getBullionRates(WebClient client) {
        try {
            String bullion = "http://www.kjpl.in/";
            HtmlPage bullionPage = client.getPage(bullion);
            HtmlTableDataCell goldRate = bullionPage.getBody().getFirstByXPath("//*[@class='gold']");
            HtmlTableDataCell silverRate = bullionPage.getBody().getFirstByXPath("//*[@class='silver']");
            System.out.println("Bullion " + goldRate.getTextContent());
            System.out.println("Bullion " + silverRate.getTextContent());

            return goldRate.getTextContent().replace("GOLD ","")+"/"+silverRate.getTextContent().replace("SILVER ","");
        } catch (IOException e) {
            return "";
        }
    }

    public static String getBullionRates() {
        WebClient client = getDriver(false);
        return getBullionRates(client);
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
    public static double strToDouble(String val) {
        double v = 0;
        if (StringUtils.hasText(val)) {
            v = Double.parseDouble(val.replace(",",""));
        }
        return v;
    }

    public static void main(String[] args) {
        HelperUtility.getBullionRates();
//        HelperUtility.getGoogleGoldRate();
    }
}
