package com.study.spring_batch.member;

import com.study.spring_batch.dto.MemberResponseDto;
import com.study.spring_batch.member.entity.Member;
import com.study.spring_batch.member.repository.MemberRepository;
import com.study.spring_batch.product.Product;
import com.study.spring_batch.product.ProductResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public List<MemberResponseDto> getAllMembers() {
        List<Member> AllMember = memberRepository.findAllWithProducts();
        List<MemberResponseDto> allMemberResponseDto = new ArrayList<>();
        for (Member member : AllMember) {
            MemberResponseDto responseDto = new MemberResponseDto(member);
            allMemberResponseDto.add(responseDto);
        }
        return allMemberResponseDto;
    }

    public void getMemberProduct(){
        Member member = memberRepository.findById(1L).orElseThrow();

        int size = member.getProducts().size();

    }
}
