package com.aerocos.zoo.animals.exceptionhandler;


import com.aerocos.zoo.animals.exception.AnimalNotFoundException;
import com.aerocos.zoo.exception.ErrorBody;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AnimalExceptionHandler {

    @ExceptionHandler(AnimalNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorBody handleAnimalNotFoundException() {
        return new ErrorBody("animal_not_found", "Животное не найдено");
    }

}
