package com.example.project.Crawling;

import com.example.project.Product.Market;
import com.example.project.Product.Product;
import com.sun.istack.NotNull;
import org.checkerframework.checker.units.qual.C;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
public class BunjangImpl implements Bunjang {
    @Override
    public HashMap<Long, Product> getPage(String category, int pagenum) {
        int categoryid;
        String url = "\0";
        HashMap<Long, Product> page = new HashMap<>();

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        chromeOptions.addArguments("--remote-allow-origins=*");
        chromeOptions.addArguments("headless");
        WebDriver webDriver = new ChromeDriver(chromeOptions);

        try {
            categoryid = setCategory(category);
            url = "https://m.bunjang.co.kr/categories/" + categoryid + "?page=" + pagenum + "&req_ref=popular_category";
            webDriver.get(url);
            System.out.println("html = " + webDriver.getPageSource());

            WebElement webElement = webDriver.findElement(By.className("sc-gVLVqr"));
            System.out.println("pid = " + webElement.findElement(By.tagName("a")).getAttribute("data-pid"));


            /*Long time = new Date().getTime();
            while(new Date().getTime() < time + 30000){
                Thread.sleep(500);
                ((JavascriptExecutor) webDriver)
                        .executeScript("window.scrollTo(0, document.body.scrollHeight)", webElement);
            }*/

            List<WebElement> webElements = webDriver.findElements(By.className("sc-gVLVqr"));

            /*for (WebElement element : webElements){
                webDriver.manage().timeouts().implicitlyWait(100000, TimeUnit.MILLISECONDS);

                System.out.println("pid = " + element.findElement(By.tagName("a")).getAttribute("data-pid"));

                String pid = element.findElement(By.tagName("a")).getAttribute("data-pid");
                webDriver.navigate().to(pid);
                Thread.sleep(500);
            }*/

            /*for (int i = 0; i < 80; i++){
                String[] id_string = ids.get(i).attr("href").split("/");
                Long id = Long.parseLong(id_string[2]);

                String name = imgs.get(i).attr("alt");
                String img = imgs.get(i).attr("src");

                String price_string = prices.get(i).text().replaceAll("[^0-9]", "");
                int price = Integer.parseInt(price_string);

                Product product = new Product(id, name, img, price, Market.Bunjang);
                page.put(id, product);
            }*/
        } catch(Exception e){
            System.out.println("번개장터 크롤링 오류");
        } finally {
            webDriver.quit();
        }
        return page;
    }

    @Override
    public HashMap<Long, Product> getSearchResult(String keyword) {
        return null;
    }

    @Override
    public Product getProduct(Long id) {
        return null;
    }

    public int setCategory(@NotNull String category) {

        int categoryid;

        switch(category){
            case "WOMANCLOTHES":
                categoryid = Bunjang.WOMANCLOTHES;
                break;
            case "MANCLOTHES":
                categoryid = Bunjang.MANCLOTHES;
                break;
            case "BEAUTY":
                categoryid = Bunjang.BEAUTY;
                break;
            case "FURNITURE":
                categoryid = Bunjang.FURNITURE;
                break;
            case "FOOD":
                categoryid = Bunjang.FOOD;
                break;
            case "KIDS":
                categoryid = Bunjang.KIDS;
                break;
            case "PETS":
                categoryid = Bunjang.PETS;
                break;
            case "LIVES":
                categoryid = Bunjang.LIVES1;
                break;
            case "DIGITAL":
                categoryid = Bunjang.DIGITAL;
                break;
            case "SPORTS":
                categoryid = Bunjang.SPORTS;
                break;
            case "HEALTH":
                categoryid = Bunjang.HEALTH;
                break;
            case "STATIONERY":
                categoryid = Bunjang.STATIONERY;
                break;
            default:
                categoryid = Bunjang.MAIN;
        }

        return categoryid;
    }
}
