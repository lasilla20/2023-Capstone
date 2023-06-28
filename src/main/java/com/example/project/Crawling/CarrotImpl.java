package com.example.project.Crawling;

import com.example.project.Product.Market;
import com.example.project.Product.Product;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;

@RequiredArgsConstructor
public class CarrotImpl implements Carrot{

    private final ChromeDriver chromeDriver;

    @Override
    public HashMap<Long, Product> getSearchResult(String keyword, int pagenum) {
        return null;
    }

    @Override
    public Product getProduct(Long id, Market market) {
        return null;
    }
}
