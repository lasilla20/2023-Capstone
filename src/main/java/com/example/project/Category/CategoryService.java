package com.example.project.Category;

import com.example.project.Product.Market;
import com.example.project.Product.Product;

import java.util.HashMap;

public interface CategoryService {

    /** 카테고리별 상품 열람 페이지 가져오기 **/
    HashMap<Long, Product> getPage(Market market, String category, int pagenum);

}
