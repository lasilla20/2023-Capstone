package com.example.project.Heart;

import com.example.project.Product.Market;
import org.springframework.stereotype.Service;

@Service
public interface HeartService {

    public void addHeart(String itemId, Market market);
    public void deleteHeart(String itemId, Market market);
    public String getHeartLink(String itemId, Market market);
    public Heart getHeartList(String userId);
}
