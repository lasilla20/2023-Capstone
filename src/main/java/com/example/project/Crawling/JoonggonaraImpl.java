package com.example.project.Crawling;

import com.example.project.Product.Market;
import com.example.project.Product.Product;
import com.sun.istack.NotNull;
import com.sun.xml.bind.v2.TODO;
import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URLEncoder;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JoonggonaraImpl implements Joonggonara{

    private final ChromeDriver chromeDriver;

    /** 중고나라 카테고리 페이지 가져오기 **/
    @Override
    public HashMap<Long, Product> getPage(String category, int pagenum) {
        String url = setURL(category, pagenum);
        HashMap<Long, Product> page = new HashMap<>();

        try {
            Document doc = Jsoup.connect(url).get();

            Elements ids = doc.select(".grid.grid-cols-2 li:nth-child(n) a");
            Elements imgs = doc.select(".grid.grid-cols-2 li:nth-child(n) a div img");
            Elements prices = doc.select(".grid.grid-cols-2 li:nth-child(n) a div div.font-semibold");

            for (int i = 0; i < 80; i++){
                String[] id_string = ids.get(i).attr("href").split("/");
                Long id = Long.parseLong(id_string[2]);

                String name = imgs.get(i).attr("alt");
                String img = imgs.get(i).attr("src");

                String price_string = prices.get(i).text().replaceAll("[^0-9]", "");
                int price = Integer.parseInt(price_string);

                Product product = new Product(id, name, img, price, Market.JOONGGONARA, null, null, 0, 0, null, category, null);
                page.put(id, product);
            }
            return page;
        } catch(IOException e){
            System.out.println("중고나라 크롤링 오류_카테고리");
        }
        return null;
    }

    /** 중고나라 검색 결과 가져오기 **/
    @Override
    public HashMap<Long, Product> getSearchResult(String keyword, int pagenum) {
        String url = setURL(pagenum, keyword);
        HashMap<Long, Product> page = new HashMap<>();
        WebDriver webDriver = chromeDriver.setChrome();

        try {
            webDriver.get(url);
            Thread.sleep(500);

            List<WebElement> webElements = webDriver.findElements(By.cssSelector("a.group.box-border"));

            for (WebElement webElement : webElements) {
                String ad = webElement.findElement(By.cssSelector(".w-full.overflow-hidden div.my-1 span:nth-child(3)")).getText();
                if(!ad.equals("광고")){
                    String[] pid = webElement.getAttribute("href").split("t/");
                    Long id = Long.parseLong(pid[1]);

                    String name = webElement.getAttribute("title");

                    String img = webElement.findElement(By.cssSelector("div.relative img")).getAttribute("src");

                    String price_string = webElement.findElement(By.cssSelector("div div.font-semibold")).getText()
                            .replaceAll("[^0-9]", "");
                    int price = Integer.parseInt(price_string);

                    //TODO 작성 일시 필요하면 불러오기 (의논)

                    Product product = new Product(id, name, img, price, Market.JOONGGONARA, null, null, 0, 0, null, null, null);
                    page.put(id, product);
                }
            }
            return page;
        } catch (Exception e) {
            System.out.println("중고나라 크롤링 오류_검색");
        } finally {
            webDriver.quit();
        }

        return null;
    }

    /** 중고나라 상품 상세 가져오기 **/
    @Override
    public Product getProduct(Long id, Market market) {
        String url = setURL(id);
        WebDriver webDriver = chromeDriver.setChrome();

        try {
            Document doc = Jsoup.connect(url).get();

            Elements names = doc.select(".pb-5 h1");
            String name = names.text();

            Elements imgs = doc.select(".col-span-1 img");
            String img = imgs.attr("src");

            Elements prices = doc.select(".pb-5.border-b.border-gray-300 div div");
            String price_string = prices.text().replaceAll("[^0-9]", "");
            int price = Integer.parseInt(price_string);

            String sellerURL = webDriver.findElement(By.cssSelector(".col-span-2 div div.flex a.font-semibold")).getAttribute("href");
            webDriver.get(sellerURL);
            String seller = webDriver.findElement(By.cssSelector("div.relative h1.hidden")).getText();
            System.out.println("seller = " + seller);

            String[] etcs = doc.select(".text-body span").text().split(" · ");

            String updatedate = etcs[0];

            int view = Integer.parseInt(etcs[1].replaceAll("[^0-9]", ""));
            int heart = Integer.parseInt(etcs[2].replaceAll("[^0-9]", ""));

            Elements details = doc.select(".col-span-3 article p");
            String detail = details.text();

            //TODO 카테고리

            Product product = new Product(id, name, img, price, market, null, updatedate, view, heart, detail, null, url);
            return product;
        } catch (IOException e){
            System.out.println("중고나라 크롤링 오류_상품 상세");
        } finally {
            webDriver.quit();
        }

        return null;
    }

    /** 중고나라 카테고리 세팅 **/
    private int setCategory(@NotNull String category) {

        int categoryid;

        switch(category){
            case "WOMANCLOTHES":
                categoryid = Joonggonara.WOMANCLOTHES;
                break;
            case "MANCLOTHES":
                categoryid = Joonggonara.MANCLOTHES;
                break;
            case "BEAUTY":
                categoryid = Joonggonara.BEAUTY;
                break;
            case "FURNITURE":
                categoryid = Joonggonara.FURNITURE;
                break;
            case "FOOD":
                categoryid = Joonggonara.FOOD;
                break;
            case "KIDS":
                categoryid = Joonggonara.KIDS;
                break;
            case "PETS":
                categoryid = Joonggonara.PETS;
                break;
            case "LIVES":
                categoryid = Joonggonara.LIVES1;
                break;
            case "DIGITAL":
                categoryid = Joonggonara.DIGITAL1;
                break;
            case "SPORTS":
                categoryid = Joonggonara.SPORTS1;
                break;
            case "HEALTH":
                categoryid = Joonggonara.HEALTH;
                break;
            case "STATIONERY":
                categoryid = Joonggonara.STATIONERY;
                break;
            default:
                categoryid = Joonggonara.MAIN;
        }

        return categoryid;
    }

    /** 크롤링 주소 세팅 **/
    private String setURL(@NotNull String category, @NotNull int pagenum){
        int categoryid = setCategory(category);
        String url = "https://web.joongna.com/search?category=" + categoryid + "&page=" + pagenum + "&sort=RECENT_SORT";

        return url;
    }
    private String setURL(@NotNull Long id){
        String url = "https://web.joongna.com/product/" + Long.toString(id);

        return url;
    }
    private String setURL(@NotNull int pagenum, @NotNull String keyword){
        String keyword_encoded = "\0";

        try{
            keyword_encoded = URLEncoder.encode(keyword, "UTF-8");
        } catch (Exception e){
            System.out.println("URL 인코딩 오류");
        }

        String url = "https://web.joongna.com/search/" + keyword_encoded + "?page=" + pagenum;

        return url;
    }
}
