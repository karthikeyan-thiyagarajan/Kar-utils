package com.karthikeyan.service;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static com.karthikeyan.utils.HelperUtility.*;

/**
 * @author Karthikeyan on 27/07/20
 */

public class VaahanScrapper {


    @Test
    public void getVaahan() throws IOException {
        WebClient client = getDriver(true);
        String vahanUrl = "https://vahan.nic.in/nrservices/faces/user/searchstatus.xhtml";
        HtmlPage vaahanPage = client.getPage(vahanUrl);
        DomElement i = (DomElement) vaahanPage.getBody().getByXPath("//input[@id='regn_no1_exact']").get(0);
        i.setAttribute("value", "TN65AB4710");

        HtmlImage image = vaahanPage.getFirstByXPath("//*[@alt='capatcha']");
        File imageFile = new File("target/file.jpg");
        image.saveAs(imageFile);
//        String ocr = doOCR(imageFile);
        String ocr = "doOCR(imageFile)";
        int processedTest = processingOCR(ocr);
        System.out.println("CAPTCHA PROCESSED TEXT = " + processedTest);

        DomElement catptcha = (DomElement) vaahanPage.getBody().getByXPath("//input[@id='txt_ALPHA_NUMERIC']").get(0);
        catptcha.setAttribute("value", String.valueOf(processedTest));

        DomElement buttonElement = vaahanPage.getBody().getFirstByXPath("//button[@id=\"j_idt41\"]");
        HtmlPage click = (HtmlPage) buttonElement.click();

        Document doc = Jsoup.parse(click.asXml());
        System.out.println("doc = " + doc.body());

        HtmlDivision a = (HtmlDivision) vaahanPage.getBody().getByXPath("//*[@id=\"rcDetailsPanel\"]").get(0);

        System.out.println(a.asXml());
    }

    @Test
    public void getyss() throws IOException {
        WebClient client = getDriver(true);
        String vahanUrl = "https://yssofindia.org/";
        HtmlPage vaahanPage = client.getPage(vahanUrl);
        List<Object> byXPath = vaahanPage.getBody().getByXPath("//*[@class='dynamic-quote']");
        HtmlDivision a = (HtmlDivision) byXPath.get(0);

        HtmlElement h4 = a.getElementsByTagName("h4").get(0);
        String fullH4 = h4.asText();
        String date = h4.getElementsByTagName("small").get(0).asText();
        String heading = date + " - " + fullH4.split(date)[0];

        System.out.println("heading = " + heading);


        System.out.println(a.getElementsByTagName("p").get(0).asText());
        System.out.println(a.getElementsByTagName("p").get(1).asText());
    }


}
