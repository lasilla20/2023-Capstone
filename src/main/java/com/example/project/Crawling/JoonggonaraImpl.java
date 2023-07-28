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
import java.util.LinkedHashMap;
import java.util.List;

import static java.sql.Types.NULL;

@Component
@RequiredArgsConstructor
public class JoonggonaraImpl implements Joonggonara{

    private final ChromeDriver chromeDriver;

    /** 중고나라 카테고리 페이지 가져오기 **/
    @Override
    public LinkedHashMap<String, Product> getPage(String category, int pagenum) {
        String url = setURL(category, pagenum);
        LinkedHashMap<String, Product> page = new LinkedHashMap<>();

        try {
            Document doc = Jsoup.connect(url).get();

            Elements ids = doc.select(".grid.grid-cols-2 li:nth-child(n) a");
            Elements imgs = doc.select(".grid.grid-cols-2 li:nth-child(n) a div img");
            Elements prices = doc.select(".grid.grid-cols-2 li:nth-child(n) a div div.font-semibold");

            for (int i = 0; i < imgs.size(); i++){
                String[] id_string = ids.get(i).attr("href").split("/");
                String id = id_string[2];

                String name = imgs.get(i).attr("alt");
                String img = imgs.get(i).attr("src");

                String price_string = prices.get(i).text().replaceAll("[^0-9]", "");
                int price = Integer.parseInt(price_string);

                Product product = new Product(id, name, img, price, Market.JOONGGONARA, null, null, 0, null, category, null);
                page.put(id, product);
            }
            return page;
        } catch(IOException e){
            System.out.println("중고나라 크롤링 오류_카테고리");
        }
        return null;
    }

    /**
     * 중고나라 검색 결과 가져오기
     **/
    @Override
    public LinkedHashMap<String, Product> getSearchResult(String keyword, int pagenum) {
        String url = setURL(pagenum, keyword);
        LinkedHashMap<String, Product> page = new LinkedHashMap<>();
        WebDriver webDriver = chromeDriver.setChrome();

        try {
            webDriver.get(url);
            Thread.sleep(500);

            List<WebElement> webElements = webDriver.findElements(By.cssSelector("a.group.box-border"));

            for (WebElement webElement : webElements) {
                String ad = webElement.findElement(By.cssSelector(".w-full.overflow-hidden div.my-1 span:nth-child(3)")).getText();
                if(!ad.equals("광고")){
                    String[] pid = webElement.getAttribute("href").split("t/");
                    String id = pid[1];

                    String name = webElement.getAttribute("title");

                    String img = webElement.findElement(By.cssSelector("div.relative img")).getAttribute("src");

                    String price_string = webElement.findElement(By.cssSelector("div div.font-semibold")).getText()
                            .replaceAll("[^0-9]", "");
                    int price = Integer.parseInt(price_string);

                    Product product = new Product(id, name, img, price, Market.JOONGGONARA, null, null, 0, null, null, null);
                    page.put(id, product);
                }
            }
            return page;
        } catch (Exception e) {
            System.out.println("중고나라 크롤링 오류_검색");
        }
//        } finally {
//            webDriver.quit();
//        }

        return null;
    }

    /** 중고나라 상품 상세 가져오기 **/
    @Override
    public Product getProduct(String id, Market market) {
        String url = setURL(id);
        WebDriver webDriver = chromeDriver.setChrome();

        try {
            Document doc = Jsoup.connect(url).get();
            webDriver.get(url);
            Thread.sleep(500);

            String name = doc.select(".pb-5 h1").text();
            String img = doc.select(".col-span-1 img").attr("src");

            String prices = doc.select(".pb-5.border-b.border-gray-300 div div").text()
                    .replaceAll("[^0-9]", "");
            int price = Integer.parseInt(prices);

            String sellerURL = webDriver.findElement(By.cssSelector(".col-span-2 div div.flex a.font-semibold")).getAttribute("href");
            webDriver.get(sellerURL);
            String seller = webDriver.findElement(By.cssSelector("div.relative h1.hidden")).getText();

            String[] etcs = doc.select(".text-body span").text().split(" · ");
            String updatedate = etcs[0];

            int heart = Integer.parseInt(etcs[2].replaceAll("[^0-9]", ""));
            String detail = doc.select(".col-span-3 article p").text();

            HashMap<Integer, String> categoryset = getCategory();
            String[] categories = doc.select(".chawkbazarBreadcrumb ol li:nth-child(3) a").attr("href").split("y=");
            String category = null;
            if (categoryset.containsKey(Integer.parseInt(categories[1]))) {
                category = categoryset.get(Integer.parseInt(categories[1]));
            } else{
                categories = doc.select(".chawkbazarBreadcrumb ol li:nth-child(5) a").attr("href").split("y=");
                if (categoryset.containsKey(Integer.parseInt(categories[1]))) {
                    category = categoryset.get(Integer.parseInt(categories[1]));
                }
            }

            Product product = new Product(id, name, img, price, market, seller, updatedate, heart, detail, category, url);
            return product;
        } catch (Exception e) {
            System.out.println("중고나라 크롤링 오류_상품 상세");
        }
//        } finally {
//            webDriver.quit();
//        }

        return null;
    }

    /** 중고나라 메인(추천상품) 가져오기 **/
    @Override
    public LinkedHashMap<String, Product> getMainPage() {
        String url = setURL();
        LinkedHashMap<String, Product> page = new LinkedHashMap<>();

        try {
            Document doc = Jsoup.connect(url).get();

            Elements ids = doc.select(".swiper-slide.pt-2 a");
            Elements imgs = doc.select(".swiper-slide.pt-2 a div img");
            Elements prices = doc.select(".swiper-slide.pt-2 a div div.font-semibold");

            for (int i = 0; i < 10; i++){
                String[] id_string = ids.get(i).attr("href").split("/");
                String id = id_string[2];

                String name = imgs.get(i).attr("alt");
                String img = imgs.get(i).attr("src");

                String price_string = prices.get(i).text().replaceAll("[^0-9]", "");
                int price = Integer.parseInt(price_string);

                Product product = new Product(id, name, img, price, Market.JOONGGONARA, null, null, 0, null, null, null);
                page.put(id, product);
            }
            return page;
        } catch(IOException e){
            System.out.println("중고나라 크롤링 오류_메인화면");
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
                categoryid = NULL;
                System.out.println("[Error: JoonggonaraImpl.setCategory] 카테고리 이름이 잘못됨");
        }

        return categoryid;
    }

    /** 중고나라 카테고리 세팅 2 **/
    private HashMap<Integer, String> getCategory(){
        HashMap<Integer, String> category = new HashMap<>();
        category.put(Joonggonara.WOMANCLOTHES, "WOMANCLOTHES");
        category.put(Joonggonara.MANCLOTHES, "MANCLOTHES");
        category.put(Joonggonara.BEAUTY, "BEAUTY");
        category.put(Joonggonara.FURNITURE, "FURNITURE");
        category.put(Joonggonara.FOOD, "FOOD");
        category.put(Joonggonara.KIDS, "KIDS");
        category.put(Joonggonara.PETS, "PETS");

        //TODO 생활(주방+가전) 디지털(컴퓨터+디지털) 스포츠(스포츠+레저+여행)
        category.put(Joonggonara.LIVES1, "LIVES");
        category.put(Joonggonara.DIGITAL1, "DIGITAL");
        category.put(Joonggonara.SPORTS1, "SPORTS");

        category.put(Joonggonara.STATIONERY, "STATIONERY");

        return category;
    }

    /** 크롤링 주소 세팅 **/
    private String setURL(@NotNull String category, @NotNull int pagenum){
        int categoryid = setCategory(category);
        String url = "https://web.joongna.com/search?category=" + categoryid + "&page=" + pagenum + "&sort=RECENT_SORT";

        return url;
    }
    private String setURL(@NotNull String id){
        String url = "https://web.joongna.com/product/" +id;

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
    private String setURL(){
        return "https://web.joongna.com/";
    }
}
