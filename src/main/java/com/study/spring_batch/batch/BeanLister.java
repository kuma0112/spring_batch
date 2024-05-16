package com.study.spring_batch.batch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class BeanLister {
    @Autowired
    private ApplicationContext context;

    public void listBeans() {
        String[] beanNames = context.getBeanDefinitionNames();
        Arrays.sort(beanNames);  // 이름을 알파벳 순으로 정렬
        for (String beanName : beanNames) {
//            System.out.println(beanName);
        }
    }
}
