package com.example.project.Crawling;


import com.example.project.Product.Market;
import com.example.project.Product.Product;
import com.example.project.Search.SearchService;
import com.example.project.Search.SearchServiceImpl;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.HashMap;
import java.util.List;

public class SearchServiceTest {

    com.example.project.Crawling.ChromeDriver chromeDriver = new ChromeDriverImpl();
    Joonggonara joonggonara = new JoonggonaraImpl(chromeDriver);
    Bunjang bunjang = new BunjangImpl(chromeDriver);
    Carrot carrot = new CarrotImpl(chromeDriver);
    SearchService searchService = new SearchServiceImpl(joonggonara, bunjang, carrot);

    /** 중고나라 검색 테스트 **/
    @Test
    void getSearchResultTest() {
        String url = "https://web.joongna.com/search/%EC%A7%B1%EA%B5%AC?page=1";
        HashMap<Long, Product> page = new HashMap<>();
        WebDriver webDriver = setChrome();

        try {
            webDriver.get(url);
            Thread.sleep(500);

            List<WebElement> webElements = webDriver.findElements(By.cssSelector("a.group.box-border"));

            for (WebElement webElement : webElements) {
                String ad = webElement.findElement(By.cssSelector(".w-full.overflow-hidden div.my-1 span:nth-child(3)")).getText();
                if(!ad.equals("광고")){
                    String[] pid = webElement.getAttribute("href").split("t/");
                    Long id = Long.parseLong(pid[1]);
                    System.out.println("ppid = " + id);

                    String name = webElement.getAttribute("title");
                    System.out.println("name = " + name);

                    String img = webElement.findElement(By.cssSelector("div.relative img")).getAttribute("src");
                    System.out.println("img = " + img);

                    String price_string = webElement.findElement(By.cssSelector("div div.font-semibold")).getText()
                            .replaceAll("[^0-9]", "");
                    int price = Integer.parseInt(price_string);
                    System.out.println("price = " + price);
                }
            }
        } catch (Exception e) {
            System.out.println("중고나라 크롤링 오류_검색");
        } finally {
            webDriver.quit();
        }
    }

    /** 번개장터 검색 테스트 **/
    @Test
    void getSearchResultTest2(){
        String url = "https://m.bunjang.co.kr/search/products?order=date&page=1&q=%EC%A7%B1%EA%B5%AC";
        HashMap<Long, Product> page = new HashMap<>();
        WebDriver webDriver = setChrome();

        try {
            webDriver.get(url);
            Thread.sleep(500);

            List<WebElement> webElements = webDriver.findElements(By.className("sc-kcDeIU"));

            for(WebElement webElement : webElements){
                String pid = webElement.findElement(By.tagName("a")).getAttribute("data-pid");
                Long id = Long.parseLong(pid);
                System.out.println("ppid = " + id);

                String name = webElement.findElement(By.cssSelector("a div.sc-eInJlc div.sc-gtfDJT")).getText();
                System.out.println("name = " + name);

                String img = webElement.findElement(By.cssSelector("a div.sc-hgHYgh img")).getAttribute("src");
                System.out.println("img = " + img);

                String price_string = webElement.findElement(By.cssSelector("a div.sc-eInJlc div.sc-jeCdPy div")).getText()
                        .replaceAll("[^0-9]", "");;
                System.out.println("price_string = " + price_string);
                int price = Integer.parseInt(price_string);

                Product product = new Product(id, name, img, price, Market.BUNJANG, null, null, 0, 0, null, null, url);
                page.put(id, product);
            }

            for(Long key:page.keySet()){
                Product product = page.get(key);
                System.out.println("product.getId() = " + product.getId());
                System.out.println("product.getName() = " + product.getName());
            }
        } catch(Exception e){
            System.out.println("번개장터 크롤링 오류_검색");
        } finally {
            webDriver.quit();
        }
    }

    @Test
    void getSearchResultTest3(){
        String url = "https://www.daangn.com/search/%EC%A7%B1%EA%B5%AC/";
        HashMap<Long, Product> page = new HashMap<>();
        WebDriver webDriver = setChrome();

        try {
            webDriver.get(url);
            Thread.sleep(500);

            List<WebElement> webElements = webDriver.findElements(By.cssSelector("article.flea-market-article"));

            for(WebElement webElement : webElements){
                String[] pid = webElement.findElement(By.tagName("a")).getAttribute("href").split("s/");
                Long id = Long.parseLong(pid[1]);
                System.out.println("ppid = " + id);

                String name = webElement.findElement(By.cssSelector("a div.card-photo img")).getAttribute("alt");
                System.out.println("name = " + name);

                String img = webElement.findElement(By.cssSelector("a div.card-photo img")).getAttribute("src");
                System.out.println("img = " + img);

                String price_string = webElement.findElement(By.cssSelector("a div.article-info p.article-price")).getText()
                        .replaceAll("[^0-9]", "");;
                int price = Integer.parseInt(price_string);
                System.out.println("price = " + price);
            }
        } catch(Exception e){
            System.out.println("당근마켓 크롤링 오류_검색");
        } finally {
            webDriver.quit();
        }
    }

    private WebDriver setChrome(){
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        chromeOptions.addArguments("--remote-allow-origins=*");
//        chromeOptions.addArguments("headless");
        WebDriver webDriver = new ChromeDriver(chromeOptions);

        return webDriver;
    }
}
