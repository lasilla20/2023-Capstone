package com.example.project.Product;

import org.springframework.stereotype.Service;

@Service
public interface ProductService {

    /** 상품 상세 정보 가져오기 **/
    Product getProduct(String id, Market market);

}
