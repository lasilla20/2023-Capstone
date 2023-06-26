package ProductDetails;

public interface ProductMapper {
    //상품 상세 페이지 조회

    /* 상품 상세 페이지 요청을 하면,
    사용자가 지정한 '상품 정보'가 포함된 '상품 상세 페이지'를 전송
     */

    //상품 정보가 담긴 클래스를 반환
    public itemRepository getProductInfo(int itemId);
    /*
    https://kimvampa.tistory.com/258
    https://catsbi.oopy.io/0e9b3db0-1c93-4593-9e5f-fb62915f0ac3
     */
}
