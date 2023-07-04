package com.example.project.Crawling;

import com.example.project.Product.Market;
import com.example.project.Product.Product;
import com.sun.istack.NotNull;
import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;

@RequiredArgsConstructor
@Component
public class CarrotImpl implements Carrot{

    private final ChromeDriver chromeDriver;

    /** 당근마켓 검색 결과 가져오기 **/
    @Override
    public HashMap<Long, Product> getSearchResult(String keyword, int pagenum) {
        HashMap<Long, Product> page = new HashMap<>();

        String url = setURL(keyword);
        WebDriver webDriver = chromeDriver.setChrome();

        try {
            webDriver.get(url);
            Thread.sleep(500);

            //TODO 페이지 새로고침 어떻게 할지

            List<WebElement> webElements = webDriver.findElements(By.cssSelector("article.flea-market-article"));

            for(WebElement webElement : webElements){
                String[] pid = webElement.findElement(By.tagName("a")).getAttribute("href").split("s/");
                Long id = Long.parseLong(pid[1]);

                String name = webElement.findElement(By.cssSelector("a div.card-photo img")).getAttribute("alt");
                String img = webElement.findElement(By.cssSelector("a div.card-photo img")).getAttribute("src");

                String price_string = webElement.findElement(By.cssSelector("a div.article-info p.article-price")).getText()
                        .replaceAll("[^0-9]", "");;
                int price = Integer.parseInt(price_string);

                Product product = new Product(id, name, img, price, Market.CARROT, null, null, 0, 0, null, null, null);
                page.put(id, product);
            }
            return page;
        } catch(Exception e){
            System.out.println("당근마켓 크롤링 오류_검색");
        } finally {
            webDriver.quit();
        }
        return null;
    }

    /** 당근마켓 상품 상세 가져오기 **/
    @Override
    public Product getProduct(Long id, Market market) {
        String url = setURL(id);

        try {
            Document doc = Jsoup.connect(url).get();

            String name = doc.select("#article-title").text();
            String img = doc.select(".image-wrap img").attr("data-lazy");

            Elements prices = doc.select("#article-price");
            String price_string = prices.text().replaceAll("[^0-9]", "");
            int price = Integer.parseInt(price_string);

            String seller = doc.select("#nickname").text();

            String[] updatedates = doc.select("#article-category time").text().split("끌올 ");
            String updatedate = updatedates[1];

            String[] etcs = doc.select("#article-counts").text().split(" ∙ ");

            int view = Integer.parseInt(etcs[2].replaceAll("[^0-9]", ""));
            int heart = Integer.parseInt(etcs[0].replaceAll("[^0-9]", ""));

            String detail = doc.select("#article-detail").text();

            //TODO 카테고리

            Product product = new Product(id, name, img, price, market, seller, updatedate, view, heart, detail, null, url);
            return product;
        } catch (IOException e){
            System.out.println("당근마켓 크롤링 오류_상품 상세");
        }
        return null;
    }

    private String setURL(Long id){
        String url = "https://www.daangn.com/articles/" + id;

        return url;
    }

    private String setURL(@NotNull String keyword){
        String keyword_encoded = "\0";

        try{
            keyword_encoded = URLEncoder.encode(keyword, "UTF-8");
        } catch (Exception e){
            System.out.println("URL 인코딩 오류");
        }

        String url = "https://www.daangn.com/search/" + keyword_encoded + "/";

        return url;
    }
}
