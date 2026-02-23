package com.gym.dailygym.service;

import com.gym.dailygym.model.Member;
import com.gym.dailygym.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Member createMember(Member member) {
        return memberRepository.save(member);
    }

    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    public Member getMemberById(Long id) {
        return memberRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Member not found"));
    }

    public Member updateMember(Long id, Member updatedMember) {
        Member existing = getMemberById(id);

        existing.setName(updatedMember.getName());
        existing.setEmail(updatedMember.getEmail());
        existing.setPhone(updatedMember.getPhone());
        existing.setMembershipType(updatedMember.getMembershipType());
        existing.setJoinDate(updatedMember.getJoinDate());

        return memberRepository.save(existing);
    }

    public void deleteMember(Long id) {
        memberRepository.deleteById(id);
    }
}
