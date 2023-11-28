package umc.spring.service.StoreService;

import umc.spring.domain.Store;
import umc.spring.web.dto.StoreSaveReponseDTO;

import java.util.Optional;

public interface StoreQueryService {

    Optional<Store> findStore(Long id);
    Store createStore (Long RegionId, StoreSaveReponseDTO.CreateStoreResultDTO request);
}
