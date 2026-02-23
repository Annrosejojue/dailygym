package com.gym.dailygym.repository;

import com.gym.dailygym.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
