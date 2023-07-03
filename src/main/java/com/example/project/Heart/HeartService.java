package com.example.project.Heart;

import com.example.project.Product.Market;

public interface HeartService {
    public void addHeart(Long itemId, Market market);
    public void deleteHeart(Long itemId, Market market);
    public String getHeartLink(Long itemId, Market market);
    public Heart getHeartList(Long userId);
    public Heart getHeartList();
}
