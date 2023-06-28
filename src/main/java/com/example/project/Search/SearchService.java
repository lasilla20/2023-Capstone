package com.example.project.Search;

import com.example.project.Product.Market;
import com.example.project.Product.Product;

import java.util.HashMap;

public interface SearchService {

    /** 검색 결과 페이지 가져오기 **/
    HashMap<Long, Product> getSearchResult(Market market, int pagenum, String keyword);

}
