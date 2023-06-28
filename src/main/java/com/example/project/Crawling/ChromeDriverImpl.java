package com.example.project.Crawling;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.stereotype.Component;

@Component
public class ChromeDriverImpl implements ChromeDriver{

    /** 크롬 드라이버 세팅 **/
    @Override
    public WebDriver setChrome(){
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        chromeOptions.addArguments("--remote-allow-origins=*");
        chromeOptions.addArguments("headless");
        WebDriver webDriver = new org.openqa.selenium.chrome.ChromeDriver(chromeOptions);

        return webDriver;
    }
}
