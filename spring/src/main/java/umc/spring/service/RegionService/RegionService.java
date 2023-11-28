package umc.spring.service.RegionService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.converter.RegionConverter;
import umc.spring.domain.Region;
import umc.spring.repository.RegionRepository;
import umc.spring.web.dto.RegionRequestDTO;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RegionService {
    private final RegionRepository regionRepository;
    @Transactional
    public Region createRegion(RegionRequestDTO.RegionDTO request){
        Region region = RegionConverter.saveRegion(request);
        return regionRepository.save(region);

    }
    public Optional<Region> findById(Long regionId){
        return regionRepository.findById(regionId);
    }

}