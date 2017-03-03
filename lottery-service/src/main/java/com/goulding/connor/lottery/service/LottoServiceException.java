package com.goulding.connor.lottery.service;

/**
 * @author Connor Goulding
 */
public class LottoServiceException extends IllegalStateException {

    /**
     * Default service runtime exception if an illegal / unknown state is reached
     *
     * @param message
     * @param cause
     */
    public LottoServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
