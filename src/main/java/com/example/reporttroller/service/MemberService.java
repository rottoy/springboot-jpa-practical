package com.example.reporttroller.service;

import com.example.reporttroller.entity.Member;
import com.example.reporttroller.repository.MemberRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service // 컴포넌트 스캔 대상
@Transactional(readOnly = true)
@RequiredArgsConstructor
// 스프링 어노테이션인 transactional이 옵션이 더 많음.
// 읽기 전용 명시 시 성능이 좋아짐.
public class MemberService {




    //@Autowired // 얘(fieldInjection)보다 더 좋은 방법은?
    private final MemberRepository memberRepository;

//    @Autowired // Setter injection? 얘도 안좋음. set에서 개발자들이 임의로 변경 건드릴수 있음.
//    public void setMemberRepository(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    }

    //@Autowired // 생성자가 한개 있는 경우 알아서 Autowired 해줌.
    //또한 변수를 final로 해주는게 좋음. 컴파일 시점에 초기화 체크가 가능함.
    //@AllArgsConstructor 어노테이션으로도 가능.
    // 더 좋은건 final 만 injection 해주는 @RequiredArgsConstructor 도 선호.
//    public MemberService(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    }

    //회원 가입
    @Transactional
    public Long join(Member member){
        validateDuplicateMember(member); // 중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    //중복 회원 검증 로직
    //멀티 쓰레드 인 경우 해당 예외처리가 씹힐 수도 있으므로, db member의 name을 unique 옵션을 거는 것이 좋음.
    private void validateDuplicateMember(Member member) {
        List<Member> findMembers=memberRepository.findByName(member.getName());
        //EXCEPTION
        if(!findMembers.isEmpty()){
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    //회원 전체 조회

    public List<Member> findMembers(){
        return memberRepository.findAll();
    }


    public Member findOne(Long memberId){
        return memberRepository.findOne(memberId);
    }
}
