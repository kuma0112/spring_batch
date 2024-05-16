package com.study.spring_batch.member;

import com.study.spring_batch.dto.MemberResponseDto;
import com.study.spring_batch.member.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/members")
    public List<MemberResponseDto> getAllMembers() {
        return memberService.getAllMembers();
    }
}
