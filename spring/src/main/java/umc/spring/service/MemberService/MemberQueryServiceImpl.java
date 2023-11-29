package umc.spring.service.MemberService;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.repository.MemberMissionRepository;
import umc.spring.repository.MemberRepository;
import umc.spring.domain.Member;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberQueryServiceImpl implements MemberQueryService{

    private final MemberRepository memberRepository;
    private final MemberMissionRepository memberMissionRepository;

    @Override
    public Optional<Member> findMember(Long id) {
        return memberRepository.findById(id);
    }

    @Override
    public Page<MemberMission> getMissionList(Long memberId, Integer page) {
        Member member = memberRepository.findById(memberId).get();

        if (page != null && page > 0) {
            page -= 1;  // 페이지 번호에서 1을 빼서 0부터 시작하도록 조정
        }

        Page<MemberMission> ChallengingPage = memberMissionRepository.findAllByMember(member, PageRequest.of(page, 10));
        return ChallengingPage;
    }
}