package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {
    private final MemberRepository memberRepository;

    @Transactional(readOnly = false)
    public Long save(Member member){

        //만약에 이름같은 member있으면 예외처리
        //이런게 비지니스 로직임
        validateDuplicateMember(member);

        return memberRepository.save(member);
    }
    private void validateDuplicateMember(Member member){
        //아래와 같이 말고 != null로 하면 오류가 발생한다
        if(!memberRepository.findByName(member.getName()).isEmpty()){
            //만약에 같은이름인 애가 찾아진다면 예외를 터뜨려야함
            throw new IllegalStateException("이미 존재하는 회원입니다");
        }
    }

    public Member findById(Long id){
        return memberRepository.findById(id);
    }
    public List<Member> findByName(String name){
        return memberRepository.findByName(name);
    }
    public List<Member> findAll(){
        return memberRepository.findAll();
    }
}
