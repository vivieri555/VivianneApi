package com.VivianneApi.repository;

import com.VivianneApi.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {

    List<Member> findByEmail(String email);

    //    @Query("""
//            SELECT m
//            FROM Member m
//            WHERE m.address = :address
//            ORDER BY m.lastName DESC
//            """)
//    List<Member> findMembers(
//            String firstName,
//            String lastName
//    );
}
