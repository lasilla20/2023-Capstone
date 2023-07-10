package com.example.project.Category;

import com.example.project.Crawling.Bunjang;
import com.example.project.Crawling.Carrot;
import com.example.project.Crawling.Joonggonara;
import com.example.project.Product.Product;
import lombok.RequiredArgsConstructor;

import java.util.LinkedHashMap;

@RequiredArgsConstructor
public class MainPageServiceImpl implements MainPageService{

    private final Joonggonara joonggonara;
    private final Bunjang bunjang;
    private final Carrot carrot;

    /** 메인화면에 인기상품 40개 띄우기 **/
    @Override
    public LinkedHashMap<Long, Product> getPage() {
        LinkedHashMap<Long, Product> page = new LinkedHashMap<>();
        LinkedHashMap<Long, Product> crawlingpage = new LinkedHashMap<>();
        int i = 0;

        // 중고나라
        crawlingpage = joonggonara.getMainPage();
        page.putAll(crawlingpage);
        crawlingpage.clear();

        // 번개장터
        crawlingpage = bunjang.getMainPage();
        for(Long l:crawlingpage.keySet()){
            page.put(l, crawlingpage.get(l));
            if(++i == 13) break;
        }
        crawlingpage.clear();
        i = 0;

        // 당근마켓
        crawlingpage = carrot.getMainPage();
        for(Long l:crawlingpage.keySet()){
            page.put(l, crawlingpage.get(l));
            if(++i == 17) break;
        }

        return page;
    }
}
