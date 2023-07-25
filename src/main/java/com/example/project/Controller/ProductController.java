package com.example.project.Controller;

import com.example.project.Category.CategoryService;
import com.example.project.Crawling.*;
import com.example.project.Heart.HeartService;
import com.example.project.Heart.HeartServiceImpl;
import com.example.project.Product.Market;
import com.example.project.Product.Product;
import com.example.project.Product.ProductService;
import com.example.project.Product.ProductServiceImpl;
import com.example.project.Search.SearchService;
import com.example.project.config.auth.PrincipalDetails;
import com.example.project.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/api/product")
@RequiredArgsConstructor
public class ProductController {
    ChromeDriver chromeDriver = new ChromeDriverImpl();
    private Joonggonara joonggonara = new JoonggonaraImpl(chromeDriver);
    private Bunjang bunjang = new BunjangImpl(chromeDriver);
    private Carrot carrot = new CarrotImpl(chromeDriver);
    private ProductService productService = new ProductServiceImpl(joonggonara, bunjang, carrot);
    private CategoryService categoryService;
    private SearchService searchService;
    private HeartService heartService;

    /** 더미데이터 **/
    Product product = new Product("119272335", "item","image_url",
            10000, Market.JOONGGONARA,"seller","2023-07-14",0,"details","category","https://web.joongna.com/product/119272335");

    /** String -> Market **/
    public Market parseMarket(String m){
        String n = m.toLowerCase();
        if(n.contains("joonggo")){
            return Market.JOONGGONARA;
        } else if(n.contains("bunjang")){
            return Market.BUNJANG;
        } else if (n.contains("carrot")) {
            return Market.CARROT;
        }
        return null;
    }

    /** 상품 상세 페이지 **/
    @GetMapping("/{itemId}/{market}")
    public Product getProductId(@PathVariable("itemId")String itemId, @PathVariable("market")String market, Model model, HttpServletRequest request) {
//        long item = Long.parseLong(itemId);
//        Market m = parseMarket(market);
//        Product product = productService.getProduct(item, m);
        String requestURL = request.getRequestURL().toString();
        System.out.println("requestURL = " + requestURL);

        return product;
    }

    /** 외부 사이트 이동 **/
    @GetMapping("/{itemId}/{market}/url")
    public String getProuctUrl(@PathVariable("itemId")String itemId, @PathVariable("market")String market, Model model){
//        long item = Long.parseLong(itemId);
//        Market m = parseMarket(market);
//        Product product = productService.getProduct(item, m);
        String url = product.getProducturl();
        return url;
    }

    /** 찜 추가 **/
    @PostMapping("/{itemId}/{market}/heart/add")
    public String postHeartAdd(@PathVariable("itemId")String itemId, @PathVariable("market")String market, @RequestBody Product product, @AuthenticationPrincipal PrincipalDetails principalDetails){
        long item = Long.parseLong(itemId);
        Market m = parseMarket(market);
        User user = principalDetails.getUser();
        //Heart heart = user.getHeart();
        //heartService = new HeartServiceImpl(heart, productService, principalDetails);
        //TODO 아래 item pid 타입 String으로 수정해 주세요
//        heartService.addHeart(item, m);
        return "선택한 상품이 추가되었습니다.";
    }

    /** 찜 제거 **/
    @PostMapping("/{itemId}/{market}/heart/delete")
    public String postHeartDelete(@PathVariable("itemId")String itemId, @PathVariable("market")String market, @RequestBody Product product, @AuthenticationPrincipal PrincipalDetails principalDetails){
        long item = Long.parseLong(itemId);
        Market m = parseMarket(market);
        User user = principalDetails.getUser();
        //Heart heart = user.getHeart();
        //heartService = new HeartServiceImpl(heart, productService, principalDetails);
        //TODO 아래 item pid 타입 String으로 수정해 주세요
//        heartService.deleteHeart(item, m);
        return "선택한 상품이 제거되었습니다.";
    }
}
