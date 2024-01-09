package com.example.productservicenaman.controlleradvices;


import com.example.productservicenaman.dtos.ArithmaticExceptionDto;
import com.example.productservicenaman.dtos.ExceptionDto;
import com.example.productservicenaman.exceptions.ProductNotExistException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice//method in this class can be modified as per response require to client
public class ExceptionHandlers {

    @ExceptionHandler(ArithmeticException.class)
    public ResponseEntity<ArithmaticExceptionDto>handleArithmaticException(){
        ArithmaticExceptionDto arithmaticExceptionDto = new ArithmaticExceptionDto();
        arithmaticExceptionDto.setMessage("it is too bad happen");
        return new ResponseEntity<ArithmaticExceptionDto>(arithmaticExceptionDto, HttpStatus.OK);
    }

    @ExceptionHandler(ArrayIndexOutOfBoundsException.class)
    public ResponseEntity<Void> handleArrayIndexOutOfBoundException() {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ExceptionHandler(ProductNotExistException.class)
    public ResponseEntity<ExceptionDto> handleProductNotExistsException(ProductNotExistException exception) {
        ExceptionDto dto = new ExceptionDto();
        dto.setMessage(exception.getMessage());
        dto.setDetail("Check the product id. It probably doesn't exist.");

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
}
