package me.soob.springbootwebservice.service;

import lombok.RequiredArgsConstructor;
import me.soob.springbootwebservice.domain.member.Member;
import me.soob.springbootwebservice.domain.member.MemberRepository;
import me.soob.springbootwebservice.web.dto.member.MemberJoinRequestDto;
import me.soob.springbootwebservice.web.dto.member.MemberResponseDto;
import me.soob.springbootwebservice.web.dto.member.MemberUpdateReqeustDto;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class MemberService implements UserDetailsService {

    private final MemberRepository memberRepository;

    /**
     * 상세정보를 조회하는 메소드, 사용자의 계정정보와 권한을 갖는 UserDetails 인터페이스를 반환
     *  ====== 아이디가 이메일이며, 로그인을 하는 form에서 name="username"으로 요청해야 한다 ======
     *
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Member findMember = memberRepository.findByEmail(email);

        List<GrantedAuthority> authorities = new ArrayList<>();

        if (("admin@example.com").equals(userEmail)) {
            authorities.add(new SimpleGrantedAuthority(Role.ADMIN.getValue()));
        } else {
            authorities.add(new SimpleGrantedAuthority(Role.MEMBER.getValue()));
        }

        return new User(findMember.getEmail(), findMember.getPassword(), authorities);
    }

    /**
     * 멤버 가입
     * email 중복 검사
     * password 암호화
     */
    @Transactional
    public Long join(MemberJoinRequestDto memberDto) {
        validateDuplicateMember(memberDto.getEmail());

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        memberDto.encodePassword(passwordEncoder.encode(memberDto.getPassword()));

        return memberRepository.save(memberDto.toEntity()).getId();
    }

    /**
     * 멤버 정보 수정
     * name, picture, password 수정 가능
     */
    @Transactional
    public Long update(Long memberId, MemberUpdateReqeustDto memberDto) {
        Member findMember = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("해당 회원이 없습니다. id =" + memberId));
        findMember.update(memberDto.getName(),memberDto.getPassword());

        return memberId;
    }

    /**
     * 멤버 1개 조회
     */
    public MemberResponseDto findOne(Long memberId) {
        Member entity = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("해당 회원이 없습니다. id = " + memberId));

        return new MemberResponseDto(entity);
    }

    /**
     * 멤버 전체 조회
     */
    public List<MemberResponseDto> findAllDesc() {
        return memberRepository.findAllDesc().stream()
                .map(MemberResponseDto::new)
                .collect(Collectors.toList());
    }

    /**
     * 멤버 삭제
     */
    @Transactional
    public void delete(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("해당 회원이 없습니다. id = " + memberId));
        memberRepository.delete(member);
    }

    /**
     * 이메일 중복 여부 확인 메소드
     */
    private void validateDuplicateMember(String email) {
        Member findMember = memberRepository.findByEmail(email);
        if (findMember != null) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }
}
