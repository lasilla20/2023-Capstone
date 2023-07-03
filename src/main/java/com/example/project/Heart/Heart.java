package com.example.project.Heart;

import com.example.project.Product.Product;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Heart {
    private List<Product> heartList = new ArrayList<>();
}
