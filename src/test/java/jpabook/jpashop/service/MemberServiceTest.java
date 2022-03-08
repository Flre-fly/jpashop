package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class MemberServiceTest {
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private MemberService memberService;
    @Test
    public void save(){
        //given
        Member member = new Member();
        member.setName("minjiss0");
        //when
        Long id = memberService.save(member);
        //then
        //어떻게 확인하지? 둘의 id값이 같다는것만 확인하면되나? id가 존재하는지를 확인하면 되지않을까?
        //영속성 컨텍스트 안에선 id값이 같으면 같은 객체로 인식하니까 그래도 될듯

        //바로 아래줄이 맞냐?
        Assertions.assertThat(member.getId()).isEqualTo(id);
        //Assertions.assertThat(member).isEqualTo(memberRepository.findById(id));
    }

    @Test
    public void duplicatedSave(){
        //given
        Member member1 = new Member();
        Member member2 = new Member();

        member1.setName("a");
        member2.setName("a");
        //when
        memberService.save(member1);
        try{
            memberService.save(member2);
        }catch (IllegalStateException e){
            return;
        }

        //then
        fail("실행되면 안되는 코드");
    }


}