package com.example.project.Category;

import com.example.project.Crawling.Bunjang;
import com.example.project.Crawling.Joonggonara;
import com.example.project.Product.Market;
import com.example.project.Product.Product;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.LinkedHashMap;

@RequiredArgsConstructor
@Component
public class CategoryServiceImpl implements CategoryService {

    private final Joonggonara joonggonara;
    private final Bunjang bunjang;
    private int categorynum = 0;
    private HashMap<String, Product> crawlingpage = new HashMap<>();
    private int crawlingpagenum = 1;
    private int systemmin = LocalTime.now().getMinute();

    @Override
    public HashMap<String, Product> getPage(int category, int pagenum) {

        int nowminute = LocalTime.now().getMinute();
        if (Math.abs(systemmin - nowminute) > 3) {
            systemmin = nowminute;
            crawlingpage.clear();
        }

        if (categorynum != category) {
            crawlingpage.clear();
            categorynum = category;
        }

        while(true) {
            if (crawlingpage.size() < pagenum * 40) {
                LinkedHashMap<String, Product> newpage = joonggonara.getPage(category, crawlingpagenum);
                if (newpage.isEmpty()) System.out.println("[Error] CategoryServiceImpl: 중고나라 카테고리 로딩 실패");
                else crawlingpage.putAll(newpage);

                newpage = bunjang.getPage(category, crawlingpagenum++);
                if (newpage.isEmpty()) System.out.println("[Error] CategoryServiceImpl: 번개나라 카테고리 로딩 실패");
                else crawlingpage.putAll(newpage);
            } else return crawlingpage;
        }
    }


//    private LinkedHashMap<String, Product> JGpage = new LinkedHashMap<>();
//    private LinkedHashMap<String, Product> BJpage = new LinkedHashMap<>();
//    private int JGpagenum = 1;
//    private int BJpagenum = 1;

//    @Override
//    public LinkedHashMap<String, Product> getPage(Market market, int category, int pagenum) {
//
//        LinkedHashMap<String, Product> page = new LinkedHashMap<>();
//        int i = 0;
//
//        if (categorynum != category) {
//            JGpage.clear();
//            BJpage.clear();
//            categorynum = category;
//        }
//
//        while(true) {
//            if (market == Market.JOONGGONARA) {
//                if (JGpage.size() < pagenum * 40) {
//                    LinkedHashMap<String, Product> newpage = joonggonara.getPage(category, JGpagenum++);
//                    if (newpage.isEmpty()) System.out.println("[Error] CategoryServiceImpl: 중고나라 카테고리 로딩 실패");
//                    else JGpage.putAll(newpage);
//                }
//                else {
//                    for (String key : JGpage.keySet()) {
//                        if ((i > ((pagenum - 1) * 40 - 1)) && (i < pagenum * 40))
//                            page.put(key, JGpage.get(key));
//                        else if (i == pagenum * 40) break;
//                        i++;
//                    }
//                    return page;
//                }
//            } else if (market == Market.BUNJANG) {
//                if (BJpage.size() < pagenum * 40) {
//                    LinkedHashMap<String, Product> newpage = bunjang.getPage(category, BJpagenum++);
//                    if (newpage.isEmpty()) System.out.println("[Error] CategoryServiceImpl: 번개장터 카테고리 로딩 실패");
//                    else BJpage.putAll(newpage);
//                }
//                else {
//                    for (String key : BJpage.keySet()) {
//                        if ((i > ((pagenum - 1) * 40 - 1)) && (i < pagenum * 40)) page.put(key, BJpage.get(key));
//                        else if (i == pagenum * 40) break;
//                        i++;
//                    }
//                    return page;
//                }
//            } else {
//                System.out.println("[Error] CategoryServiceImpl: 올바르지 않은 마켓 값 입력");
//                return null;
//        }
//    }

}