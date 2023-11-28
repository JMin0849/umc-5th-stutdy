package umc.spring.converter;

import umc.spring.domain.Region;
import umc.spring.web.dto.RegionRequestDTO;

public class RegionConverter {
    public static Region saveRegion(RegionRequestDTO.RegionDTO request){
        return Region
                .builder()
                .name(request.getName())
                .build();
    }
}
