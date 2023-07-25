package com.example.project.Crawling;

import com.example.project.Product.Market;
import com.example.project.Product.Product;

import java.util.LinkedHashMap;

import static java.sql.Types.NULL;

public interface Joonggonara {

    int WOMANCLOTHES = 111; // 여성패션
    int MANCLOTHES = 112; // 남성패션
    int BEAUTY = 4; // 화장품, 미용
    int FURNITURE = 10; // 가구, 인테리어
    int FOOD = NULL; // 식품
    int KIDS = 5; // 출산, 유아동
    int PETS = 208; // 반려동물용품
    int LIVES1 = 11; // 생활(주방)
    int LIVES2 = 7; // 생활(가전)
    int DIGITAL1 = 8; // 컴퓨터
    int DIGITAL2 = 9; // 디지털
    int SPORTS1 = 16; // 스포츠
    int SPORTS2 = 17; // 레저, 여행
    int HEALTH = NULL; // 건강, 의료용품
    int STATIONERY = 14; // 도서, 음반, 문구
    int MAIN = 0; // 메인화면

    /**
     * 중고나라 카테고리 페이지 가져오기
     **/
    public LinkedHashMap<String, Product> getPage(String category, int pagenum);

    /**
     * 중고나라 검색 결과 가져오기
     **/
    public LinkedHashMap<String, Product> getSearchResult(String keyword, int pagenum);

    /** 중고나라 상품 상세 가져오기 **/
    public Product getProduct(String id, Market market);

    /**
     * 중고나라 메인(추천상품) 가져오기
     **/
    public LinkedHashMap<String, Product> getMainPage();

}
