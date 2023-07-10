package com.example.project.Category;

import com.example.project.Product.Market;
import com.example.project.Product.Product;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;

@Service
public interface MainPageService {

    /** 메인화면에 인기상품 40개 띄우기 **/
    LinkedHashMap<Long, Product> getPage();
}
