package me.soob.springbootwebservice.service;

import lombok.RequiredArgsConstructor;
import me.soob.springbootwebservice.domain.member.Member;
import me.soob.springbootwebservice.domain.member.MemberRepository;
import me.soob.springbootwebservice.web.dto.MemberDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class MemberService {

    private final MemberRepository memberRepository;

    // 이메일 중복 여부 체크 확인후 회원가입
    @Transactional
    public Long join(MemberDto memberDto) {
        validateDuplicateMember(memberDto);
        return memberRepository.save(memberDto.toEntity()).getId();
    }

    // 이메일 중복 여부 체크
    private void validateDuplicateMember(MemberDto memberDto) {
        List<Member> findMembers = memberRepository.findByEmail(memberDto.getEmail());
        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    // 멤버 정보 수정
    @Transactional
    public Long update(Long memberId, MemberDto memberDto) {
        Member findMember = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("해당 회원이 없습니다. id =" + memberId));
        findMember.update(memberDto.getPicture(), memberDto.getName());

        return memberId;
    }

    // 멤버 1개 조회
    public MemberDto findOne(Long memberId) {
        Member entity = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("해당 회원이 없습니다. id = " + memberId));

        return new MemberDto(entity);
    }

    // 멤버 전체 조회
    public List<MemberDto> findAllDesc() {
        return memberRepository.findAllDesc().stream()
                .map(MemberDto::new)
                .collect(Collectors.toList());
    }

    // 멤버 삭제
    @Transactional
    public void delete(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("해당 회원이 없습니다. id = " + memberId));
        memberRepository.delete(member);
    }
}
