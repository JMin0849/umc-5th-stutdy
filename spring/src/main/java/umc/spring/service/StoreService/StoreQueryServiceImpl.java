package umc.spring.service.StoreService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.converter.StoreConverter;
import umc.spring.domain.Store;
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

    @Override
    public Optional<Store> findStore(Long id) {

        return storeRepository.findById(id);
    }

    @Transactional
    public Store createStore(Long RegionId,StoreSaveReponseDTO.CreateStoreResultDTO request) {
        Store store = StoreConverter.saveStore(request);
        store.setRegion(regionService.findById(RegionId).get());
        return storeRepository.save(store);
    }
}
