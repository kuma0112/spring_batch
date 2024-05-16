package com.study.spring_batch.dto;

import com.study.spring_batch.member.entity.Member;
import com.study.spring_batch.member.entity.MemberRoleEnum;
import com.study.spring_batch.product.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class MemberResponseDto {
    private Long memberId;
    private String email;
    private String nickname;
    private MemberRoleEnum role;
    private List<ProductDto> products;

    public MemberResponseDto(Member member) {
        this.memberId = member.getMemberId();
        this.email = member.getEmail();
        this.nickname = member.getNickname();
        this.role = member.getRole();
        // 지연 로딩을 트리거하여 N+1 문제를 유발
        this.products = member.getProducts().stream()
                .map(ProductDto::new)
                .collect(Collectors.toList());
    }

}
