package com.example.project.Crawling;

import com.example.project.Product.Product;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class BunjangImpl implements Bunjang {
    @Override
    public HashMap<Long, Product> getPage(String category, int pagenum) {
        return null;
    }

    @Override
    public HashMap<Long, Product> getSearchResult(String keyword) {
        return null;
    }

    @Override
    public Product getProduct(Long id) {
        return null;
    }
}
