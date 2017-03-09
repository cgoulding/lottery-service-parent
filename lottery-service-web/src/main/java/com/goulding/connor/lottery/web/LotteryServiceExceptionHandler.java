package com.goulding.connor.lottery.web;

import com.goulding.connor.lottery.service.LotteryServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Connor Goulding
 */
@ControllerAdvice
public class LotteryServiceExceptionHandler extends ResponseEntityExceptionHandler {

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler({LotteryServiceException.class, AssertionError.class})
    public ResponseEntity<Object> badRequest(HttpServletRequest req, Exception exception) {
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
