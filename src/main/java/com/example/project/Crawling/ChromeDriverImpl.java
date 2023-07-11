package com.example.project.Crawling;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.stereotype.Component;

@Component
public class ChromeDriverImpl implements ChromeDriver{

    private WebDriver webDriver;

    public ChromeDriverImpl() {
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
