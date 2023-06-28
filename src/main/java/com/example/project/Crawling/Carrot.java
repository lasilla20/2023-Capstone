package com.example.project.Crawling;

import com.example.project.Product.Market;
import com.example.project.Product.Product;

import java.util.HashMap;

public interface Carrot {

    /** 당근마켓 검색 결과 가져오기 **/
    public HashMap<Long, Product> getSearchResult(String keyword, int pagenum);

    /** 당근마켓 상품 상세 가져오기 **/
    public Product getProduct(Long id, Market market);

}
