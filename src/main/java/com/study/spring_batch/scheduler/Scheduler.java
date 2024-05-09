package com.study.spring_batch.scheduler;

import com.study.spring_batch.naver.ItemDto;
import com.study.spring_batch.naver.NaverApiService;
import com.study.spring_batch.product.Product;
import com.study.spring_batch.product.ProductRepository;
import com.study.spring_batch.product.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j(topic = "Scheduler")
@Component
@RequiredArgsConstructor
public class Scheduler {

    private final NaverApiService naverApiService;
    private final ProductService productService;
    private final ProductRepository productRepository;

    @Scheduled(cron = "0 0 1 * * *") // 매일 자정 1시
    public void updatePrice() throws InterruptedException {
        log.info("가격 업데이트 실행");
        List<Product> productList = productRepository.findAll();
        for (Product product : productList) {
            TimeUnit.SECONDS.sleep(1); // naver 제한으로 1초에 한 상품 씩만 조회

            String title = product.getTitle();;
            List<ItemDto> itemDtoList = naverApiService.searchItems(title);

            if (itemDtoList.size()>0) {
                ItemDto itemDto = itemDtoList.get(0); // 검색 결과 중 첫 번째 결과를 이용하여 최저 가격 업데이트
                Long id = product.getId();
                try {
                    productService.updateBySearch(id, itemDto);
                } catch (Exception e) {
                    log.error(id + " : " + e.getMessage());
                }
            }
        }
    }
}
