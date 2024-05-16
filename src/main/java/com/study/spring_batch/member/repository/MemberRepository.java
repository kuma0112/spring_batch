package com.study.spring_batch.member.repository;

import com.study.spring_batch.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByEmail(String email);

//    @Query("select m from Member m left join fetch m.products")
//    List<Member> findAllWithProducts();
}
