package umc.spring.converter;

import org.springframework.data.domain.Page;
import umc.spring.domain.Mission;
import umc.spring.domain.Review;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.web.dto.MemberResponseDTO;
import umc.spring.web.dto.StoreResponseDTO;

import java.awt.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class MissionConverter {
    public static MemberMission challengingMission(){
        return MemberMission.builder()
                .status(MissionStatus.CHALLENGING)
                .build();
    }

    public static StoreResponseDTO.MissionPreViewDTO missionPreViewDTO(Mission mission){
        return StoreResponseDTO.MissionPreViewDTO.builder()
                .storeName(mission.getStore().getName())
                .reward(mission.getReward())
                .missionSpec(mission.getMissionSpec())
                .build();
    }

    public static StoreResponseDTO.MissionPreViewListDTO missionPreViewListDTO(Page<Mission> missionList){
        List<StoreResponseDTO.MissionPreViewDTO> missionPreViewDTOList = missionList.stream()
                .map(MissionConverter::missionPreViewDTO).collect(Collectors.toList());

        return StoreResponseDTO.MissionPreViewListDTO.builder()
                .isLast(missionList.isLast())
                .isFirst(missionList.isFirst())
                .totalPage(missionList.getTotalPages())
                .totalElements(missionList.getTotalElements())
                .listSize(missionPreViewDTOList.size())
                .missionList(missionPreViewDTOList)
                .build();
    }

    public static MemberResponseDTO.ChallengingMissionPreViewDTO challengingMissionPreViewDTO(MemberMission challengingMission){
        return MemberResponseDTO.ChallengingMissionPreViewDTO.builder()
                .storeName(challengingMission.getMission().getStore().getName())
                .reward(challengingMission.getMission().getReward())
                .build();
    }
    public static MemberResponseDTO.ChallengingMissionPreViewListDTO challengingMissionPreViewListDTO(Page<MemberMission> challengingMissionList){
        List<MemberResponseDTO.ChallengingMissionPreViewDTO> challengingMissionPreViewDTOS = challengingMissionList.stream()
                .map(MissionConverter::challengingMissionPreViewDTO).collect(Collectors.toList());

        return MemberResponseDTO.ChallengingMissionPreViewListDTO.builder()
                .isLast(challengingMissionList.isLast())
                .isFirst(challengingMissionList.isFirst())
                .totalPage(challengingMissionList.getTotalPages())
                .totalElements(challengingMissionList.getTotalElements())
                .listSize(challengingMissionPreViewDTOS.size())
                .ChallengingMissionList(challengingMissionPreViewDTOS)
                .build();
    }
}
