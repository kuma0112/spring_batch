package com.study.spring_batch;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class SimpleReader implements ItemReader<String> {
    private final List<String> data = Arrays.asList("Spring", "Batch", "Example");
    private final AtomicInteger index = new AtomicInteger(0);

    @Override
    public String read() throws Exception {
        if (index.get() < data.size()) {
            return data.get(index.getAndIncrement());
        } else {
            return null; // 데이터 끝을 알림
        }
    }
}




