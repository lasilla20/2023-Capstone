package com.example.project.Crawling;

import com.example.project.Category.CategoryService;
import com.example.project.Category.CategoryServiceImpl;
import com.example.project.Product.Market;
import com.example.project.Product.Product;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

class CategoryServiceTest {

    CategoryService categoryService = new CategoryServiceImpl(new JoonggonaraImpl(), new BunjangImpl());

    /** 중고나라에서 카테고리 불러오기 테스트 **/
    @Test
    void getPage() {
        HashMap<Long, Product> hashMap = categoryService
                .getPage(Market.JOONGGONARA, "WOMANCLOTHES", 1);

        // 카테고리에 총 80개 데이터 있어야 함
        Assertions.assertThat(hashMap.size()).isEqualTo(80);
    }

    /** 번개장터에서 카테고리 불러오기 테스트 **/
    @Test
    void getPage2() {
        HashMap<Long, Product> hashMap = categoryService
                .getPage(Market.BUNJANG, "PETS", 1);

        // 카테고리에 총 백 개의 데이터 있어야 함
        Assertions.assertThat(hashMap.size()).isEqualTo(100);
    }

}