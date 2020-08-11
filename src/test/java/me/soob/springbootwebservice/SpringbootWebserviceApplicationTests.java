package me.soob.springbootwebservice;

import me.soob.springbootwebservice.domain.member.Member;
import me.soob.springbootwebservice.domain.member.MemberRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootWebserviceApplicationTests {

    @Autowired
    MemberRepository memberRepository;

    @Test
    public void contextLoads() {
    }

    @Test
    public void default_member_value_check() throws Exception {
        // given
        String email = "soob@z.com";
        String name = "kingsubin";
        String pass = "123";
        Member member = new Member(email, name, pass);

        // when
        memberRepository.save(member);

        // then
        Assert.assertEquals(1, member.getLevel());
    }
}
