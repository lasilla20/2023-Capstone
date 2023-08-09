package com.example.project.Controller;

import com.example.project.Product.Market;
import com.example.project.Product.Product;
import com.example.project.Product.ProductService;
import com.example.project.config.auth.PrincipalDetails;
import com.example.project.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/api/product")
@RequiredArgsConstructor
public class ProductController {
    private final MyLogger myLogger;
    private final ProductService productService;
    private String classPath = Thread.currentThread().getStackTrace()[1].getClassName();

    /** String -> Market **/
    public Market parseMarket(String s){
        String m = s.toUpperCase();
        if(m.equals("J")){
            return Market.JOONGGONARA;
        } else if(m.equals("B")){
            return Market.BUNJANG;
        } else if (m.equals("C")) {
            return Market.CARROT;
        }
        return null;
    }

    /** 상품 상세 페이지 **/
    @GetMapping("/{itemId}/{market}")
    public Product getProductId(@PathVariable("itemId")String itemId, @PathVariable String market, HttpServletRequest request) {
//      http://localhost:3000/api/product/231821733/B

        myLogger.printRequestInfo(request, classPath, "상품 상세 페이지를 로딩합니다··· 상품 아이디 " + itemId);

        Market m = parseMarket(market);
        Product product = productService.getProduct(itemId, m);

        return product;
    }

    /** 외부 사이트 이동 **/
    @GetMapping("/{itemId}/{market}/url")
    public String getProuctUrl(@PathVariable("itemId")String itemId, @PathVariable String market, HttpServletRequest request){
//      http://localhost:3000/api/product/231821733/B/url

        myLogger.printRequestInfo(request, classPath, "외부 사이트로 이동합니다··· 상품 아이디 " + itemId);

        Market m = parseMarket(market);
        Product product = productService.getProduct(itemId, m);

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
