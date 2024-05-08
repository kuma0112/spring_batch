package com.study.spring_batch.product;

import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.cglib.core.Local;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@MappedSuperclass // 테이블로 직접 매핑 X. 이 클래스를 상속받으면 이 클래스의 필드 정보 활용 가능
@EntityListeners(AuditingEntityListener.class) // Jpa Auditing 기능 활성화. createdAt과 lastModifiedAt으로 생성 수정 시간 관리
public class Timestamped {

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column
    private LocalDateTime modifiedAt;

}
