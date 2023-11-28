package umc.spring.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.converter.StoreConverter;
import umc.spring.domain.*;
import umc.spring.repository.MemberRepository;
import umc.spring.service.MissionService.MissionService;
import umc.spring.service.RegionService.RegionService;
import umc.spring.service.StoreService.StoreCommandService;
import umc.spring.service.StoreService.StoreQueryServiceImpl;
import umc.spring.validation.annotation.ExistMember;
import umc.spring.validation.annotation.ExistStore;
import umc.spring.web.dto.RegionRequestDTO;
import umc.spring.web.dto.StoreRequestDTO;
import umc.spring.web.dto.StoreResponseDTO;
import umc.spring.web.dto.StoreSaveReponseDTO;

@RestController
@Validated
@RequiredArgsConstructor
@RequestMapping("/stores")
public class StoreRestController {

    private final StoreCommandService storeCommandService;
    private final StoreQueryServiceImpl storeQueryService;
    private final RegionService regionService;
    private final MissionService missionService;
    private final MemberRepository memberRepository;

    @PostMapping("/{storeId}/reviews")
    public ApiResponse<StoreResponseDTO.CreateReviewResultDTO> createReview(@RequestBody @Valid StoreRequestDTO.ReveiwDTO request,
                                                                            @ExistStore @PathVariable(name = "storeId") Long storeId,
                                                                            @ExistMember @RequestParam(name = "memberId") Long memberId){
        Review review = storeCommandService.createReview(memberId, storeId, request);
        return ApiResponse.onSuccess(StoreConverter.toCreateReviewResultDTO(review));

    }
    @PostMapping("/save")
    public void createStore(@RequestBody StoreSaveReponseDTO.CreateStoreResultDTO request){
        Store store = storeQueryService.createStore(1L,request);
    }
    @PostMapping("/save/region")
    public void createRegion(@RequestBody RegionRequestDTO.RegionDTO request){
        Region region = regionService.createRegion(request);
    }
    @PostMapping("save/mission")
    public void createMission(@RequestBody StoreRequestDTO.MissionDTO request){
        Mission mission = missionService.createMission(2L,request);

    }
    @PostMapping("save/myMission")
    public void createChallenging(@RequestBody @Valid StoreRequestDTO.ChallengingDTO request){
        Mission mission = missionService.findById(request.getId());
        Member member = memberRepository.findById(1L).get();
        missionService.setMymission(mission,member);


    }
}
