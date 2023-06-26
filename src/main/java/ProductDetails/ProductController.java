package ProductDetails;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public interface ProductController {
    //상품 상세 페이지 컨트롤러

    /*

     */
    @GetMapping("/product/{itemId}")
    public String getProductId(@PathVariable("itemId")int itemId, Model model);

}
