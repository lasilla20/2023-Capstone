package com.example.project.Search;

import com.example.project.Crawling.Bunjang;
import com.example.project.Crawling.Joonggonara;
import com.example.project.Product.Market;
import com.example.project.Product.Product;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;

@RequiredArgsConstructor
public class SearchServiceImpl implements SearchService{

    private final Joonggonara joonggonara;
    private final Bunjang bunjang;

    @Override
    public HashMap<Long, Product> getSearchResult(Market market, int pagenum, String keyword) {

        HashMap<Long, Product> page = new HashMap<>();

        if (market == Market.JOONGGONARA) {
            page = joonggonara.getSearchResult(keyword, pagenum);
            return page;
        } else if (market == Market.BUNJANG) {
            page = bunjang.getSearchResult(keyword, pagenum);
            return page;
        } else if (market == Market.CARROT) {
            //TODO 당근마켓 추가
        }

        return null;
    }
}
