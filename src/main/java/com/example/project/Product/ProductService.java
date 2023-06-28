package com.example.project.Product;

import java.util.HashMap;

public interface ProductService {

    /** 상품 상세 정보 가져오기 **/
    Product getProduct(Long id, Market market);

}
