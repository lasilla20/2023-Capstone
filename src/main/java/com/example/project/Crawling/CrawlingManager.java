package com.example.project.Crawling;

import com.example.project.Product.Market;
import com.example.project.Product.Product;

import java.util.HashMap;

public interface CrawlingManager {

    /** 카테고리별 상품 열람 페이지 가져오기 **/
    HashMap<Long, Product> getPage(Market market, String category, int pagenum);

    /** 검색 결과 페이지 가져오기 **/
    HashMap<Long, Product> getPage(Market market, int pagenum, String search);

    /** 상품 상세 정보 가져오기 **/
    Product getProduct(Long id);
}
