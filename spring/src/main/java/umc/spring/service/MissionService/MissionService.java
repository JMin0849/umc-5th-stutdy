package umc.spring.service.MissionService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.converter.MissionConverter;
import umc.spring.converter.StoreConverter;
import umc.spring.domain.Member;
import umc.spring.domain.Mission;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.repository.MemberMissionRepository;
import umc.spring.repository.MissionRepository;
import umc.spring.repository.StoreRepository;
import umc.spring.web.dto.StoreRequestDTO;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MissionService {
    private final MissionRepository missionRepository;
    private final StoreRepository storeRepository;
    private final MemberMissionRepository memberMissionRepository;

    @Transactional
    public Mission createMission(Long storeId, StoreRequestDTO.MissionDTO request){
        Mission mission = StoreConverter.saveMission(request);
        mission.setStore(storeRepository.findById(storeId).get());
        return missionRepository.save(mission);
    }
    @Transactional
    public MemberMission setMymission(Mission mission, Member member){
        MemberMission memberMission = MissionConverter.challengingMission();
        memberMission.setMission(mission);
        memberMission.setMember(member);
        return memberMissionRepository.save(memberMission);

    }
    public Mission findById(Long missionId){
        return missionRepository.findById(missionId).get();
    }
}
