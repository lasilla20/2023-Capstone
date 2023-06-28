package com.example.project.Crawling;

import com.example.project.Product.Market;
import com.example.project.Product.Product;
import com.sun.istack.NotNull;
import lombok.RequiredArgsConstructor;
import org.openqa.selenium.*;
import org.springframework.stereotype.Component;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;

@Component
@RequiredArgsConstructor
public class BunjangImpl implements Bunjang {

    private final ChromeDriver chromeDriver;

    /** 번개장터 카테고리 페이지 가져오기 **/
    @Override
    public HashMap<Long, Product> getPage(String category, int pagenum) {
        HashMap<Long, Product> page = new HashMap<>();

        String url = setURL(category, pagenum);
        WebDriver webDriver = chromeDriver.setChrome();

        try {
            webDriver.get(url);
            Thread.sleep(500);

            List<WebElement> webElements = webDriver.findElements(By.className("sc-gVLVqr"));

            for(WebElement webElement : webElements){
                String ad = webElement.findElement(By.cssSelector("a div.sc-iGPElx div.sc-gtfDJT div.sc-fOICqy")).getText();
                if(!ad.equals("AD")) {
                    String pid = webElement.findElement(By.tagName("a")).getAttribute("data-pid");
                    Long id = Long.parseLong(pid);

                    String name = webElement.findElement(By.className("sc-kasBVs")).getText();
                    String img = webElement.findElement(By.tagName("img")).getAttribute("src");

                    String price_string = webElement.findElement(By.className("sc-hgHYgh")).getText()
                            .replaceAll("[^0-9]", "");
                    int price = Integer.parseInt(price_string);

                    Product product = new Product(id, name, img, price, Market.BUNJANG, null, null, 0, 0, null, category);
                    page.put(id, product);
                }
            }
            return page;
        } catch(Exception e){
            System.out.println("번개장터 크롤링 오류_카테고리");
        } finally {
            webDriver.quit();
        }
        return null;
    }

    /** 번개장터 검색 결과 가져오기 **/
    @Override
    public HashMap<Long, Product> getSearchResult(String keyword, int pagenum) {
        HashMap<Long, Product> page = new HashMap<>();

        String url = setURL(pagenum, keyword);
        WebDriver webDriver = chromeDriver.setChrome();

        try {
            webDriver.get(url);
            Thread.sleep(500);

            List<WebElement> webElements = webDriver.findElements(By.className("sc-kcDeIU"));

            for(WebElement webElement : webElements){
                String pid = webElement.findElement(By.tagName("a")).getAttribute("data-pid");
                Long id = Long.parseLong(pid);

                String name = webElement.findElement(By.cssSelector("a div.sc-eInJlc div.sc-gtfDJT")).getText();

                String img = webElement.findElement(By.cssSelector("a div.sc-hgHYgh img")).getAttribute("src");

                String price_string = webElement.findElement(By.cssSelector("a div.sc-eInJlc div.sc-jeCdPy div")).getText()
                        .replaceAll("[^0-9]", "");;
                int price = Integer.parseInt(price_string);

                Product product = new Product(id, name, img, price, Market.BUNJANG, null, null, 0, 0, null, null);
                page.put(id, product);
            }
            return page;
        } catch(Exception e){
            System.out.println("번개장터 크롤링 오류_검색");
        } finally {
            webDriver.quit();
        }
        return null;
    }

    /** 번개장터 상품 상세 가져오기 **/
    @Override
    public Product getProduct(Long id, Market market) {
        String url = setURL(id);
        WebDriver webDriver = chromeDriver.setChrome();

        try {
            webDriver.get(url);
            Thread.sleep(500);

            String name = webDriver.findElement(By.className("ProductSummarystyle__Name-sc-oxz0oy-4")).getText();
            String img = webDriver.findElement(By.className("sc-jKVCRD")).findElement(By.tagName("img")).
                    getAttribute("src");
            String prices = webDriver.findElement(By.className("ProductSummarystyle__Price-sc-oxz0oy-6")).getText();
            int price = Integer.parseInt(prices.replaceAll("[^0-9]", ""));
            String seller = webDriver.findElement(By.className("ProductSellerstyle__Name-sc-1qnzvgu-7")).getText();

            //TODO 업데이트 시간
            //TODO Views 수정 (지금 찜 수가 대신 나오는 중)
            String views = webDriver.findElement(By.className("ProductSummarystyle__Status-sc-oxz0oy-13"))
                    .getText();

            String hearts = webDriver.findElement(By.className("ProductSummarystyle__StatusValue-sc-oxz0oy-14")).getText();
            int heart = Integer.parseInt(hearts);
            String detail = webDriver.findElement(By.className("ProductInfostyle__DescriptionContent-sc-ql55c8-3"))
                    .findElement(By.tagName("p")).getText();

            //TODO 카테고리 추가

            Product product = new Product(id, name, img, price, market, seller, null, 0, heart, detail, null);
            return product;
        } catch(Exception e){
            System.out.println("번개장터 크롤링 오류_상품 상세");
        } finally {
            webDriver.quit();
        }

        return null;
    }

    /** 번개장터 카테고리 세팅 **/
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

    /** 크롤링 주소 세팅 **/
    private String setURL(@NotNull String category, @NotNull int pagenum){
        int categoryid = setCategory(category);
        String url = "https://m.bunjang.co.kr/categories/" + categoryid + "?page=" + pagenum + "&req_ref=popular_category";

        return url;
    }
    private String setURL(@NotNull Long id){
        String url = "https://m.bunjang.co.kr/products/" + id;

        return url;
    }
    private String setURL(@NotNull int pagenum, @NotNull String keyword){
        String keyword_encoded = "\0";

        try{
            keyword_encoded = URLEncoder.encode(keyword, "UTF-8");
        } catch (Exception e){
            System.out.println("URL 인코딩 오류");
        }

        String url = "https://m.bunjang.co.kr/search/products?order=date&page=" + pagenum + "&q=" + keyword_encoded;

        return url;
    }
}
