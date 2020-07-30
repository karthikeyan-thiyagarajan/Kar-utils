package com.karthikeyan.service;

import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.HtmlDivision;
import com.gargoylesoftware.htmlunit.html.HtmlImage;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

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

}
