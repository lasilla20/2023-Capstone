package com.example.project.Crawling;

import com.example.project.Product.Product;

import java.util.HashMap;

public interface Bunjang {

    /** 번개장터 카테고리 페이지 가져오기 **/
    public HashMap<Long, Product> getPage(String category, int pagenum);

    /** 번개장터 검색 결과 가져오기 **/
    public HashMap<Long, Product> getSearchResult(String keyword);

    /** 번개장터 상품 상세 가져오기 **/
    public Product getProduct(Long id);
}
