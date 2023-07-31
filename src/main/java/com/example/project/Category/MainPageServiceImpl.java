package com.example.project.Category;

import com.example.project.Crawling.Bunjang;
import com.example.project.Crawling.Carrot;
import com.example.project.Crawling.Joonggonara;
import com.example.project.Product.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;

@RequiredArgsConstructor
@Component
public class MainPageServiceImpl implements MainPageService{

    private final Joonggonara joonggonara;
    private final Bunjang bunjang;
    private final Carrot carrot;

    /**
     * 메인화면에 인기상품 40개 띄우기
     **/
    @Override
    public LinkedHashMap<String, Product> getPage() {
        LinkedHashMap<String, Product> page = new LinkedHashMap<>();
        LinkedHashMap<String, Product> crawlingpage = new LinkedHashMap<>();
        int i = 0;

        // 중고나라
/*        crawlingpage = joonggonara.getMainPage();
        page.putAll(crawlingpage);
        crawlingpage.clear();*/

        // 번개장터
        crawlingpage = bunjang.getMainPage();
        for(String s:crawlingpage.keySet()){
            page.put(s, crawlingpage.get(s));
            if(++i == 13) break;
        }
        crawlingpage.clear();
        i = 0;

        // 당근마켓
/*        crawlingpage = carrot.getMainPage();
        for(String s:crawlingpage.keySet()){
            page.put(s, crawlingpage.get(s));
            if(++i == 17) break;
        }*/

        return page;
    }
}
