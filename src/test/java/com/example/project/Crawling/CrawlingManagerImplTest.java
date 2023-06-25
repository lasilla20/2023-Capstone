package com.example.project.Crawling;

import com.example.project.Product.Market;
import com.example.project.Product.Product;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class CrawlingManagerImplTest {

    CrawlingManager crawlingManager = new CrawlingManagerImpl(new JoonggonaraImpl(), new BunjangImpl());

    @Test
    void getPage() {
        crawlingManager.getPage(Market.JOONGGONARA, "WOMANCLOTHES", 1);
    }
}