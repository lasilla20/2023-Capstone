package com.example.project.Crawling;

import com.example.project.Product.Product;

import java.util.HashMap;

import static java.sql.Types.NULL;

public interface Bunjang {

    int WOMANCLOTHES = 310; // 여성패션
    int MANCLOTHES = 320; // 남성패션
    int BEAUTY = 410; // 화장품, 미용
    int FURNITURE = 810; // 가구, 인테리어
    int FOOD = 820; // 식품
    int KIDS = 500; // 출산, 유아동
    int PETS = 980; // 반려동물용품
    int LIVES1 = 800; // 생활(주방)
    int LIVES2 = 610; // 생활(가전)
    int DIGITAL = 600; // 컴퓨터, 디지털
    int SPORTS = 700; // 스포츠, 레저
    int HEALTH = NULL; // 건강, 의료용품
    int STATIONERY = 900; // 도서, 음반, 문구
    int MAIN = 0; // 메인화면

    /** 번개장터 카테고리 페이지 가져오기 **/
    public HashMap<Long, Product> getPage(String category, int pagenum);

    /** 번개장터 검색 결과 가져오기 **/
    public HashMap<Long, Product> getSearchResult(String keyword);

    /** 번개장터 상품 상세 가져오기 **/
    public Product getProduct(Long id);
}
