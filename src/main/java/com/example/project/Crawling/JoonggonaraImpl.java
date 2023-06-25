package com.example.project.Crawling;

import com.example.project.Product.Market;
import com.example.project.Product.Product;
import com.sun.istack.NotNull;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;

@Component
public class JoonggonaraImpl implements Joonggonara{

    private int categoryid;

    @Override
    public HashMap<Long, Product> getPage(String category, int pagenum) {
        int categoryid;
        String url = "\0";
        HashMap<Long, Product> page = new HashMap<>();

        try {
            categoryid = setCategory(category);
            url = "https://web.joongna.com/search?category=" + categoryid + "&page=" + page + "&sort=RECENT_SORT";
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

                Product product = new Product(id, name, img, price, Market.JOONGGONARA);
                page.put(id, product);
            }
        } catch(IOException e){
            System.out.println("중고나라 크롤링 오류");
        }
        return null;
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
}
