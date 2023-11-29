package umc.spring.service.StoreService;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.converter.StoreConverter;
import umc.spring.domain.Mission;
import umc.spring.domain.Review;
import umc.spring.domain.Store;
import umc.spring.repository.MissionRepository;
import umc.spring.repository.ReviewRepository;
import umc.spring.repository.StoreRepository;
import umc.spring.service.RegionService.RegionService;
import umc.spring.web.dto.StoreSaveReponseDTO;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StoreQueryServiceImpl implements StoreQueryService{

    private final StoreRepository storeRepository;
    private final RegionService regionService;
    private final ReviewRepository reviewRepository;
    private final MissionRepository missionRepository;

    @Override
    public Optional<Store> findStore(Long id) {
        return storeRepository.findById(id);
    }

    public Page<Review> getReviewList(Long StoreId, Integer page) {
        Store store = storeRepository.findById(StoreId).get();


        if (page != null && page > 0) {
            page -= 1;  // 페이지 번호에서 1을 빼서 0부터 시작하도록 조정
        }

        Page<Review> StorePage = reviewRepository.findAllByStore(store, PageRequest.of(page, 10));
        return StorePage;
    }

    @Override
    public Page<Mission> getMissionList(Long StoreId, Integer page) {
        Store store = storeRepository.findById(StoreId).get();

        if (page != null && page > 0) {
            page -= 1;  // 페이지 번호에서 1을 빼서 0부터 시작하도록 조정
        }

        Page<Mission> MissionPage = missionRepository.findAllByStore(store, PageRequest.of(page, 10));
        return MissionPage;
    }

    @Transactional
    public Store createStore(Long RegionId,StoreSaveReponseDTO.CreateStoreResultDTO request) {
        Store store = StoreConverter.saveStore(request);
        store.setRegion(regionService.findById(RegionId).get());
        return storeRepository.save(store);
    }
}
