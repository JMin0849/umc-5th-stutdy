package umc.spring.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.domain.Review;
import umc.spring.domain.Store;
import umc.spring.service.StoreService.StoreQueryService;
import umc.spring.validation.annotation.CheckPage;
import umc.spring.validation.annotation.ExistStore;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class StorePageValidator implements ConstraintValidator<CheckPage, Integer> {

    private final StoreQueryService storeQueryService;

    @Override
    public void initialize(CheckPage constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Integer page, ConstraintValidatorContext context) {
        if ( page < 1) {
            // 페이지 번호가 1 미만인 경우
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(ErrorStatus.PAGE_1OVER.toString()).addConstraintViolation();
            return false;
        }

        // 모든 조건을 통과한 경우
        return true;
    }
}
