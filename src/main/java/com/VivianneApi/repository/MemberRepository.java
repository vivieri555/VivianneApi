package com.VivianneApi.repository;

import com.VivianneApi.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findById(Long id);
    Boolean existsByEmail(String email);

    //Om man vill göra egen query
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
