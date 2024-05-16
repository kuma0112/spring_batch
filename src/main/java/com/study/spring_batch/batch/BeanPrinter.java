package com.study.spring_batch.batch;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BeanPrinter implements CommandLineRunner {
    private final BeanLister beanLister;

    @Override
    public void run(String... args) throws Exception {
//        beanLister.listBeans();
    }
}
