package com.study.spring_batch.product;

import com.study.spring_batch.naver.ItemDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public static final int MIN_MY_PRICE = 100;

    public ProductResponseDto createProduct(ProductRequestDto requestDto) {
        Product product = productRepository.save(new Product(requestDto));
        return new ProductResponseDto(product);
    }

    @Transactional
    public ProductResponseDto updateProduct(Long id, ProductMypriceRequestDto requestDto) {
        int myprice = requestDto.getMyprice();
        if (myprice < MIN_MY_PRICE) {
            throw new IllegalArgumentException("유효하지 않은 가격입니다. 최소 " + MIN_MY_PRICE + "원 이상으로 입력하여 주세요.");
        }

        Product product = productRepository.findById(id).orElseThrow(() ->
                new NullPointerException("해당 상품을 찾을 수 없습니다."));

        product.update(requestDto);
        return new ProductResponseDto(product);
    }

    public List<ProductResponseDto> getProducts() {
        List<Product> AllProducts = productRepository.findAll();
        List<ProductResponseDto> AllProductsResponseDto = new ArrayList<>();
        for (Product product : AllProducts) {
            ProductResponseDto responseDto = new ProductResponseDto(product);
            AllProductsResponseDto.add(responseDto);
        }
        return AllProductsResponseDto;
    }

    public void updateBySearch(Long id, ItemDto itemDto) {
        Product product = productRepository.findById(id).orElseThrow(() ->
                new NullPointerException("찾으시는 상품이 존재하지 않습니다."));
        product.updateByItemDto(itemDto);
    }
}
