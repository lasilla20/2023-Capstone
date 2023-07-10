package com.example.project.Crawling;

import com.example.project.Product.Market;
import com.example.project.Product.Product;

import java.util.LinkedHashMap;

public interface Carrot {

    /** 당근마켓 검색 결과 가져오기 **/
    public LinkedHashMap<Long, Product> getSearchResult(String keyword, int pagenum);

    /** 당근마켓 상품 상세 가져오기 **/
    public Product getProduct(Long id, Market market);

    /** 당근마켓 메인(추천상품) 가져오기 **/
    public LinkedHashMap<Long, Product> getMainPage();
}
