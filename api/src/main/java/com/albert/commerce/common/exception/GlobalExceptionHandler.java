package com.albert.commerce.common.exception;

import com.albert.commerce.common.BusinessLinks;
import com.albert.commerce.store.command.application.StoreAlreadyExistsException;
import com.albert.commerce.store.ui.MyStoreNotFoundException;
import com.albert.commerce.store.ui.StoreNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ErrorResponse storeAlreadyExistsExceptionHandler(
            StoreAlreadyExistsException storeAlreadyExistsException,
            HttpServletRequest httpServletRequest) {
        Link selfRel = Link.of(httpServletRequest.getRequestURL().toString()).withSelfRel();
        ErrorResponse errorResponse = new ErrorResponse(
                storeAlreadyExistsException.getErrorMessage());
        return errorResponse.add(
                selfRel,
                BusinessLinks.MY_STORE
        );
    }

    @ExceptionHandler
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ErrorResponse storeExceptionHandler(StoreNotFoundException storeNotFoundException,
            HttpServletRequest httpServletRequest) {
        Link selfRel = Link.of(httpServletRequest.getRequestURL().toString()).withSelfRel();
        ErrorResponse errorResponse = new ErrorResponse(storeNotFoundException.getErrorMessage());
        return errorResponse.add(
                selfRel,
                BusinessLinks.GET_STORE
        );
    }


    @ExceptionHandler
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ErrorResponse storeExceptionHandler(MyStoreNotFoundException myStoreNotFoundException,
            HttpServletRequest httpServletRequest) {
        Link selfRel = Link.of(httpServletRequest.getRequestURL().toString()).withSelfRel();
        ErrorResponse errorResponse = new ErrorResponse(myStoreNotFoundException.getErrorMessage());
        return errorResponse.add(
                selfRel,
                BusinessLinks.CREATE_STORE
        );
    }
}
