package com.lolineet.standard.starter.exception.impl;


import com.lolineet.standard.starter.exception.ExceptionCode;
import com.lolineet.standard.starter.exception.LolineetException;


public class InvalidSystemClockException extends LolineetException {
    public InvalidSystemClockException() {
        super(ExceptionCode.INVALID_SYSTEM_CLOCK, "时钟回拨");
    }

    public InvalidSystemClockException(String msg) {
        super(ExceptionCode.INVALID_SYSTEM_CLOCK, msg);
    }

    public InvalidSystemClockException(String msg, String detail) {
        super(ExceptionCode.INVALID_SYSTEM_CLOCK, msg, detail);
    }

}
