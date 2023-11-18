package umc.spring.apiPayload.exceoption.handler;

import umc.spring.apiPayload.code.BaseErrorCode;
import umc.spring.apiPayload.exceoption.GeneralException;

public class TempHandler extends GeneralException {

    public TempHandler(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
