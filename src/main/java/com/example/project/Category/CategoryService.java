package com.example.project.Category;

import com.example.project.Product.Market;
import com.example.project.Product.Product;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public interface CategoryService {

    /** 카테고리별 상품 열람 페이지 가져오기 **/
    HashMap<String, Product> getPage(int category, int pagenum);

}
