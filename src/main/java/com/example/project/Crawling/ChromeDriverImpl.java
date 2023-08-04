package com.example.project.Crawling;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

@Component
public class ChromeDriverImpl implements ChromeDriver{

//    private static String WEB_DRIVER_PATH = "/Users/hyunmin/Desktop/2023-Capstone/chromedriver_macos";
    private WebDriver webDriver;

    public ChromeDriverImpl() {
//        if (ObjectUtils.isEmpty(System.getProperty("webdriver.chrome.driver"))) {
//            System.setProperty("webdriver.chrome.driver", WEB_DRIVER_PATH);
//        }

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        chromeOptions.addArguments("--remote-allow-origins=*");
        chromeOptions.addArguments("headless");
        this.webDriver = new org.openqa.selenium.chrome.ChromeDriver(chromeOptions);
    }

    /** 크롬 드라이버 세팅 **/
    @Override
    public WebDriver setChrome(){
        return webDriver;
    }
}
