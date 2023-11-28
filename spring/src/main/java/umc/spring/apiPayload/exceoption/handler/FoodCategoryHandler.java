package umc.spring.apiPayload.exceoption.handler;

import umc.spring.apiPayload.code.BaseErrorCode;
import umc.spring.apiPayload.exceoption.GeneralException;

public class FoodCategoryHandler extends GeneralException {
    public FoodCategoryHandler(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
