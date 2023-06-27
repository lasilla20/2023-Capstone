package com.example.project.Crawling;

import com.example.project.Product.Market;
import com.example.project.Product.Product;
import com.sun.istack.NotNull;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

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
            Thread.sleep(500);

            List<WebElement> webElements = webDriver.findElements(By.className("sc-gVLVqr"));

            for(WebElement webElement : webElements){
                String pid = webElement.findElement(By.tagName("a")).getAttribute("data-pid");
                Long id = Long.parseLong(pid);

                String name = webElement.findElement(By.className("sc-kasBVs")).getText();
                String img = webElement.findElement(By.tagName("img")).getAttribute("src");

                String price_string = webElement.findElement(By.className("sc-hgHYgh")).getText()
                        .replaceAll("[^0-9]", "");
                int price = Integer.parseInt(price_string);

                Product product = new Product(id, name, img, price, Market.BUNJANG);
                page.put(id, product);
            }
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

    /** URL 세팅 함수 만들기 (상품 상세정보용, 카테고리용, 검색정보용 오버로딩) **/
    /** 크롬 브라우저 세팅용 함수 만들기 (상품 상세정보, 카테고리, 검색정보에 공통적으로 쓰일 것) **/
    /** 크롤링 코드에 일관성 있으면 함수로 따로 빼기 **/

    private int setCategory(@NotNull String category) {

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
