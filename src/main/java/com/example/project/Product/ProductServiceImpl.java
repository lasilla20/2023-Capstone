package com.example.project.Product;

import com.example.project.Crawling.Bunjang;
import com.example.project.Crawling.Carrot;
import com.example.project.Crawling.Joonggonara;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{

    private final Joonggonara joonggonara;
    private final Bunjang bunjang;
    private final Carrot carrot;

    @Override
    public Product getProduct(String id, Market market) {

        if (market == Market.JOONGGONARA) {
            Product product = joonggonara.getProduct(id, market);
            return product;
        } else if (market == Market.BUNJANG) {
            Product product = bunjang.getProduct(id, market);
            return product;
        } else if (market == Market.CARROT) {
            Product product = carrot.getProduct(id, market);
            return product;
        }

        return null;
    }
}
