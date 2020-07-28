package com.karthikeyan.service;

import com.gargoylesoftware.htmlunit.WebClient;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.karthikeyan.utils.HelperUtility.*;

/**
 * @author Karthikeyan on 25/07/20
 */

@Component
public class GoldAndSilverScrapper {

    /*@Scheduled(cron = "0 15 10 * * ?")
    @Scheduled(cron = "0 30 15 * * ?")*/
    public void job() {
        GoldAndSilverScrapper goldAndSilverScrapper = new GoldAndSilverScrapper();
        System.out.println("Triggered on " + new Date());
        goldAndSilverScrapper.getScrappedValues();
        System.out.println("----------------------------");
    }


    public void getScrappedValues() {
        try {
            WebClient client = getDriver(false);
            String googleGoldRate = getGoogleGoldRate();
            String bullionRates = getBullionRates(client);
            Mailer mailer = new Mailer();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm XM");
            String date = simpleDateFormat.format(new Date());
            String content = "<b style='color: goldenrod;font-size:30px;'>Gold & Silver rate for " + date + "</b><br>";
            content += "<b>Wish you a nice day!</b><br>";
            content += "<font color=green style='font-size: 30px;'>" + "Google Gold Rate (10 gm) : " + googleGoldRate + "</font><br>";
            content += "<font color=darkred style='font-size: 30px;'>" + bullionRates + "</font>";

            List<String> recipients = new ArrayList<>();
            recipients.add("santhoshraj0105@gmail.com");
            recipients.add("carromkarthi36@gmail.com");
            recipients.add("gokulakannan14@gmail.com");
            recipients.add("harikrishnanpmk2000@gmail.com");
            recipients.add("kalbalapmk@gmail.com");
            mailer.sendMail(content, recipients.toArray(new String[0]));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
