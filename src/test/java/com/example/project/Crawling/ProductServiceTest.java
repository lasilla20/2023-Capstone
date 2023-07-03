package com.example.project.Crawling;

import com.example.project.Product.Product;
import com.example.project.Product.ProductService;
import com.example.project.Product.ProductServiceImpl;
import com.example.project.Search.SearchService;
import com.example.project.Search.SearchServiceImpl;
import org.checkerframework.checker.units.qual.C;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class ProductServiceTest {

    com.example.project.Crawling.ChromeDriver chromeDriver = new ChromeDriverImpl();
    Joonggonara joonggonara = new JoonggonaraImpl(chromeDriver);
    Bunjang bunjang = new BunjangImpl(chromeDriver);
    Carrot carrot = new CarrotImpl(chromeDriver);
    ProductService productService = new ProductServiceImpl(joonggonara, bunjang, carrot);

    /** 중고나라 상품 상세 테스트 **/
    @Test
    void getProductTest(){
        String url = "https://web.joongna.com/product/119272335";
        WebDriver webDriver = setChrome();

        try {
            Document doc = Jsoup.connect(url).get();
            webDriver.get(url);
            Thread.sleep(500);

            Elements names = doc.select(".pb-5 h1");
            String name = names.text();
            System.out.println("name = " + name);

            Elements imgs = doc.select(".col-span-1 img");
            String img = imgs.attr("src");
            System.out.println("img = " + img);

            Elements prices = doc.select(".pb-5.border-b.border-gray-300 div div");
            String price_string = prices.text().replaceAll("[^0-9]", "");
            int price = Integer.parseInt(price_string);
            System.out.println("price = " + price);

            String sellerURL = webDriver.findElement(By.cssSelector(".col-span-2 div div.flex a.font-semibold")).getAttribute("href");
            webDriver.get(sellerURL);
            String seller = webDriver.findElement(By.cssSelector("div.relative h1.hidden")).getText();
            System.out.println("seller = " + seller);

            String[] etcs = doc.select(".text-body span").text().split(" · ");

            String updatedate = etcs[0];
            System.out.println("updatedate = " + updatedate);

            int views = Integer.parseInt(etcs[1].replaceAll("[^0-9]", ""));
            System.out.println("views = " + views);

            int hearts = Integer.parseInt(etcs[2].replaceAll("[^0-9]", ""));
            System.out.println("hearts = " + hearts);

            Elements details = doc.select(".col-span-3 article p");
            String detail = details.text();
            System.out.println("detail = " + detail);

        } catch (Exception e){
            System.out.println("중고나라 크롤링 오류_상품 상세");
        } finally {
            webDriver.quit();
        }
//        productService.getProduct(119272335L, Market.JOONGGONARA);
    }

    /** 번개장터 상품 상세 테스트 **/
    @Test
    void getProductTest2(){
        String url = "https://m.bunjang.co.kr/products/224033457";
        WebDriver webDriver = setChrome();

        try {
            webDriver.get(url);
            Thread.sleep(500);

            String name = webDriver.findElement(By.className("ProductSummarystyle__Name-sc-oxz0oy-4")).getText();
            System.out.println("name = " + name);

            String img = webDriver.findElement(By.className("sc-jKVCRD")).findElement(By.tagName("img")).
                    getAttribute("src");
            System.out.println("img = " + img);

            String prices = webDriver.findElement(By.className("ProductSummarystyle__Price-sc-oxz0oy-6")).getText();
            int price = Integer.parseInt(prices.replaceAll("[^0-9]", ""));
            System.out.println("price = " + price);

            String seller = webDriver.findElement(By.className("ProductSellerstyle__Name-sc-1qnzvgu-7")).getText();
            System.out.println("seller = " + seller);

            /** 업데이트 시간 수정해야 함 **/
//            String updatetime = webDriver.findElement(By.xpath("/html/body/div/div/div[4]/div[1]/div/div[2]/div/div[2]/div/div[1]/div[2]/div[1]/div/div[3]/img")).getText();
//            System.out.println("updatetime = " + updatetime);

            /** views 수정해야 함 **/
            String views = webDriver.findElement(By.className("ProductSummarystyle__Status-sc-oxz0oy-13"))
                    .getText();
            System.out.println("views = " + views);

            String hearts = webDriver.findElement(By.className("ProductSummarystyle__StatusValue-sc-oxz0oy-14")).getText();
            int heart = Integer.parseInt(hearts);
            System.out.println("heart = " + heart);

            String detail = webDriver.findElement(By.className("ProductInfostyle__DescriptionContent-sc-ql55c8-3"))
                    .findElement(By.tagName("p")).getText();
            System.out.println("detail = " + detail);

            /** 카테고리 논의 필요 **/
        } catch(Exception e){
            System.out.println("번개장터 크롤링 오류_상품 상세");
        } finally {
            webDriver.quit();
        }
    }

    /** 당근마켓 상품 상세 테스트 **/
    @Test
    void getProductTest3(){
        String url = "https://www.daangn.com/articles/589353858";

        try {
            Document doc = Jsoup.connect(url).get();

            String name = doc.select("#article-title").text();
            System.out.println("name = " + name);

            String img = doc.select(".image-wrap img").attr("data-lazy");
            System.out.println("img = " + img);

            Elements prices = doc.select("#article-price");
            String price_string = prices.text().replaceAll("[^0-9]", "");
            int price = Integer.parseInt(price_string);
            System.out.println("price = " + price);

            String seller = doc.select("#nickname").text();
            System.out.println("seller = " + seller);

            String[] updatedates = doc.select("#article-category time").text().split("끌올 ");
            String updatedate = updatedates[1];
            System.out.println("updatedate = " + updatedate);

            String[] etcs = doc.select("#article-counts").text().split(" ∙ ");

            int view = Integer.parseInt(etcs[2].replaceAll("[^0-9]", ""));
            System.out.println("view = " + view);

            int heart = Integer.parseInt(etcs[0].replaceAll("[^0-9]", ""));
            System.out.println("heart = " + heart);

            String detail = doc.select("#article-detail").text();
            System.out.println("detail = " + detail);

            //TODO 카테고리

        } catch (Exception e){
            System.out.println("당근마켓 크롤링 오류_상품 상세");
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
