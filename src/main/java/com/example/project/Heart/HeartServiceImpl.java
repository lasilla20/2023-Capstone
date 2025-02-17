package com.example.project.Heart;

import com.example.project.Product.Market;
import com.example.project.Product.Product;
import com.example.project.Product.ProductService;
import com.example.project.config.auth.PrincipalDetails;
import com.example.project.domain.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.List;

@Getter
@Setter
@Component
@RequiredArgsConstructor
public class HeartServiceImpl implements HeartService {
    private Heart heart;
    private ProductService productService;
    private PrincipalDetails principalDetails;

    public HeartServiceImpl(Heart heart, ProductService productService, PrincipalDetails principalDetails) {
        this.heart = heart;
        this.productService = productService;
        this.principalDetails = principalDetails;
    }

    @Override
    public void addHeart(String itemId, Market market) {
        Product product = productService.getProduct(itemId, market);
        if(product.getHearts()==0){
            List<Product> heartList = heart.getHeartList();
            heartList.add(product);
            product.setHearts(1);
        }
    }

    @Override
    public void deleteHeart(String itemId, Market market) {
        Product product = productService.getProduct(itemId, market);
        if(product.getHearts()==1){
            List<Product> heartList = heart.getHeartList();
            heartList.remove(product);
            product.setHearts(0);
        }
    }

    @Override
    public String getHeartLink(String itemId, Market market) {
        Product product = productService.getProduct(itemId, market);
        return product.getProducturl();
    }

    @Override
    public Heart getHeartList(String userId) {
        User user = principalDetails.getUser();
        //return user.getHeartList();
        //TODO : user에 저장된 찜목록 불러오기
        return null;
    }

    @Override
    public String toString() {
        return "HeartServiceImpl{" +
                "heart=" + heart +
                ", productService=" + productService +
                ", principalDetails=" + principalDetails +
                '}';
    }
}
