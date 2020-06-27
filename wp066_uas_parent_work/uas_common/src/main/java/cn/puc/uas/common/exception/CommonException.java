package cn.puc.uas.common.exception;

import cn.puc.uas.common.entity.ResultCode;
import lombok.Getter;

/**
 * 自定义异常
 */
@Getter
public class CommonException extends Exception  {

    private ResultCode resultCode;

    public CommonException(ResultCode resultCode) {
        this.resultCode = resultCode;
    }


}
