package com.example.project.Crawling;

import com.example.project.AppConfig;
import com.example.project.Category.CategoryService;
import com.example.project.Category.CategoryServiceImpl;
import com.example.project.Product.Market;
import com.example.project.Product.Product;
import com.example.project.Search.SearchService;
import com.example.project.Search.SearchServiceImpl;
import org.assertj.core.api.Assertions;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

class CategoryServiceTest {
    ChromeDriver chromeDriver = new ChromeDriverImpl();

    Joonggonara joonggonara = new JoonggonaraImpl(chromeDriver);
    Bunjang bunjang = new BunjangImpl(chromeDriver);
    CategoryService categoryService = new CategoryServiceImpl(joonggonara, bunjang);

    /** 중고나라에서 카테고리 불러오기 테스트 1 **/
    @Test
    void getPageTest(){
        String url = "https://web.joongna.com/search?category=111&page=1";
        HashMap<Long, Product> page = new HashMap<>();

        try {
            Document doc = Jsoup.connect(url).get();

            Elements ids = doc.select(".grid.grid-cols-2 li:nth-child(n) a");
            Elements imgs = doc.select(".grid.grid-cols-2 li:nth-child(n) a div img");
            Elements prices = doc.select(".grid.grid-cols-2 li:nth-child(n) a div div.font-semibold");

            for (int i = 0; i < imgs.size(); i++){
                String[] id_string = ids.get(i).attr("href").split("/");
                Long id = Long.parseLong(id_string[2]);

                String name = imgs.get(i).attr("alt");
                String img = imgs.get(i).attr("src");

                String price_string = prices.get(i).text().replaceAll("[^0-9]", "");
                int price = Integer.parseInt(price_string);

                Product product = new Product(id, name, img, price, Market.JOONGGONARA, null, null, 0, null, null, url);
                page.put(id, product);
            }
            for (Long key:page.keySet()){
                Product p = page.get(key);
                System.out.println("p.getId() = " + p.getId());
                System.out.println("p.getName() = " + p.getName());
                System.out.println("p.getImage() = " + p.getImage());
                System.out.println("p.getPrice() = " + p.getPrice());
            }
            System.out.println("page.size() = " + page.size());
        } catch(IOException e){
            System.out.println("중고나라 크롤링 오류_카테고리");
        }
    }

    /** 중고나라에서 카테고리 불러오기 테스트 2 **/
    @Test
    void getPageTest2() {
        HashMap<Long, Product> hashMap = categoryService
                .getPage(Market.JOONGGONARA, "WOMANCLOTHES", 1);
        Assertions.assertThat(hashMap.size()).isEqualTo(40);
    }

    /** 번개장터에서 카테고리 불러오기 테스트 **/
    @Test
    void getPageTest3() {
        HashMap<Long, Product> page = new HashMap<>();

        String url = "https://m.bunjang.co.kr/categories/310";
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
                    System.out.println("ppid = " + id);

                    String name = webElement.findElement(By.className("sc-kasBVs")).getText();
                    System.out.println("name = " + name);

                    String img = webElement.findElement(By.tagName("img")).getAttribute("src");
                    System.out.println("img = " + img);

                    String price_string = webElement.findElement(By.className("sc-hgHYgh")).getText()
                            .replaceAll("[^0-9]", "");
                    int price = Integer.parseInt(price_string);
                    System.out.println("price = " + price);

                    Product product = new Product(id, name, img, price, Market.BUNJANG, null, null, 0, null, null, url);
                    page.put(id, product);
                }
            }
            for (Long key:page.keySet()){
                Product p = page.get(key);
                System.out.println("p.getId() = " + p.getId());
                System.out.println("p.getName() = " + p.getName());
                System.out.println("p.getImage() = " + p.getImage());
                System.out.println("p.getPrice() = " + p.getPrice());
            }
            System.out.println("page.size() = " + page.size());

        } catch(Exception e){
            System.out.println("번개장터 크롤링 오류_카테고리");
        } finally {
            webDriver.quit();
        }
    }

    /** 번개장터에서 카테고리 불러오기 테스트 2 **/
    @Test
    void getPageTest4(){
        HashMap<Long, Product> hashMap = categoryService
                .getPage(Market.BUNJANG, "WOMANCLOTHES", 5);
        Assertions.assertThat(hashMap.size()).isEqualTo(40);
    }

}