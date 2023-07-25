package com.example.project.Search;

import com.example.project.Product.Market;
import com.example.project.Product.Product;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;

@Service
public interface SearchService {

    /** 검색 결과 페이지 가져오기 **/
    LinkedHashMap<String, Product> getSearchResult(Market market, int pagenum, String keyword);

}
