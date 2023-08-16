package com.example.project.Heart;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.example.project.Product.Product;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.DynamicUpdate;

@Getter
@Setter
@ToString
@DynamicUpdate
@Entity
@Table(
        name = "heart"
)
public class Heart {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long heartId;
    @Column(
            name = "productId",
            nullable = false
    )
    private String productId;
    @Column(
            name = "productName",
            nullable = false
    )
    private String productName;
    @Column(
            name = "market",
            nullable = false
    )
    private String market;
    @Column(
            name = "productUrl",
            nullable = false
    )
    private String productUrl;
    @Column(
            name = "heartCheck",
            nullable = false
    )
    private int heartCheck;
    @Column(
            name = "userId",
            nullable = false
    )
    private Long userId;

    public Heart(){

    }
    public Heart(Long userId, String productId, String productName, String market, String productUrl, int heartCheck) {
        this.userId = userId;
        this.productId = productId;
        this.productName = productName;
        this.market = market;
        this.productUrl = productUrl;
        this.heartCheck = heartCheck;
    }
}
