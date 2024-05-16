package com.study.spring_batch.dto;

import com.study.spring_batch.product.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProductDto {
    private Long id;
    private String title;
    private String image;
    private String link;
    private int lprice;
    private int myprice;

    public ProductDto(Product product) {
        this.id= product.getId();
        this.title = product.getTitle();
        this.image = product.getImage();
        this.link = product.getLink();
        this.lprice = product.getLprice();
        this.myprice = product.getMyprice();
    }
}
