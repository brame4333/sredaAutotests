package io.zeplin.generate;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.Random;

public class Generate {

    private String getRandomEmailHost() {
        String[] emailHosts = {"gmail.com", "mail.ru", "yandex.ru"};
        return emailHosts[new Random().nextInt(emailHosts.length)];

    }

    public String generateDataForInput(Integer type) {
        String generatedData;
        switch (type) {
            case 1:
                generatedData = RandomStringUtils.randomAlphabetic(10) + "@" + getRandomEmailHost();
                break;
            case 2:
                generatedData = RandomStringUtils.randomAlphabetic(8) + RandomStringUtils.randomNumeric(2) + RandomStringUtils.randomAlphabetic(2).toUpperCase();
                break;
            case 3:
                generatedData = RandomStringUtils.randomAlphabetic(16) + RandomStringUtils.randomNumeric(2);
                break;
            default:
                throw new RuntimeException("wrong argument " + type);
        }
        return generatedData;
    }
}
