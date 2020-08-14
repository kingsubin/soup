package me.soob.springbootwebservice.service;

import lombok.RequiredArgsConstructor;
import me.soob.springbootwebservice.domain.member.Member;
import me.soob.springbootwebservice.domain.member.MemberRepository;
import me.soob.springbootwebservice.web.dto.member.MemberJoinRequestDto;
import me.soob.springbootwebservice.web.dto.member.MemberResponseDto;
import me.soob.springbootwebservice.web.dto.member.MemberUpdateReqeustDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class MemberService {

    private final MemberRepository memberRepository;

    /**
     * 멤버 가입
     * email 중복 검사 후 진행
     */
    @Transactional
    public Long join(MemberJoinRequestDto memberDto) {
        validateDuplicateMember(memberDto.getEmail());
        return memberRepository.save(memberDto.toEntity()).getId();
    }

    /**
     * 멤버 로그인
     * email, password 확인후 진행
     */


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
        List<Member> findMembers = memberRepository.findByEmail(email);
        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }
}
