package jpabook.jpashop;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MemberRepositoryTest {
    @Autowired
    MemberRepository memberRepository;

    @Test
    public void savetest(){
        //given
        Member member = new Member();
        member.setUsername("김민지");
        //when
        Long member1Id = memberRepository.save(member);
        memberRepository.findById(member1Id);
        //then
        Assertions.assertThat(member1Id).isEqualTo(member.getId());;
        Assertions.assertThat(memberRepository.findById(member1Id).getUsername()).isEqualTo(member.getUsername());
    }

}