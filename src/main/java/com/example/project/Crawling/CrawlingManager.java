package com.example.project.Crawling;

import com.example.project.Product.Market;
import com.example.project.Product.Product;

import java.util.HashMap;

/*******************
 * 이 인터페이스와 구현체는 나중에 삭제할 것입니다!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! 사유: 구조 변경
카테고리 관련 -> Category, 검색 관련 -> Search(아직 안 만듦), 상품 관련 -> Product(아직 안 만듦)으로 옮길 거임
테스트 파일은 변경된 구조에 맞게 수정했으니 이 파일만 신경 쓰지 마세요 *
 *******************/

public interface CrawlingManager {

    /** 카테고리별 상품 열람 페이지 가져오기 **/
    HashMap<Long, Product> getPage(Market market, String category, int pagenum);

    /** 검색 결과 페이지 가져오기 **/
    HashMap<Long, Product> getPage(Market market, int pagenum, String search);

    /** 상품 상세 정보 가져오기 **/
    Product getProduct(Long id);
}
