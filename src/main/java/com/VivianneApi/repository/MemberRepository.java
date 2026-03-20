package com.VivianneApi.repository;

import com.VivianneApi.entity.Address;
import com.VivianneApi.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findById(Long id);

   // Om man vill göra egen query
        @Query("""
            SELECT m.firstName, m.lastName, m.address, m.email, m.phone
            FROM Member m
            ORDER BY m.lastName DESC
            """)
        List<Member> findMembers();
}
