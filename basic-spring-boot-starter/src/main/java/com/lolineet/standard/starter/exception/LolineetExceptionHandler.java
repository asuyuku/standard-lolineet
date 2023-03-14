package com.lolineet.standard.starter.exception;

import com.lolineet.standard.R;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@Component
@ControllerAdvice
@ConditionalOnProperty(prefix = "ghwl.exception", name = "enabled",matchIfMissing = true, havingValue = "true")
public class LolineetExceptionHandler {

    @ResponseBody
    @ExceptionHandler(LolineetException.class)
    public R handleGHWLException(LolineetException e) {
        return R.error(e.getCode(), e.getMessage());
    }
}
