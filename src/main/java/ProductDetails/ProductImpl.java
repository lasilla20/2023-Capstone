package ProductDetails;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import static jdk.internal.module.IllegalAccessLogger.logger;

public class ProductImpl implements ProductController{
    //상품 상세 페이지 임플

    /*
     getProductInfo() 메서드를 호출하여 반환받은 item 객체를 Model 객체를 활용하여 넘겨주는 코드
     */

    @Override
    @GetMapping("/product/{itemId}")
    public String getProductId(@PathVariable("itemId")int itemId, Model model) {
        //PathVariable로 넘어온 아이템 아이디로 상품을 조회 후 모델에 추가해 뷰 템플릿을 호출한다
        Item item = itemRepository.getProductInfo(itemId);
        // ↑아이템 객체에서 ID를 따오면 된다
        model.addAttribute("productInfo", item);

        return "/product";
    }
}
