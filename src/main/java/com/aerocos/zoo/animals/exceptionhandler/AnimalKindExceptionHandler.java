package com.aerocos.zoo.animals.exceptionhandler;


import com.aerocos.zoo.animals.exception.AnimalKindNotFoundException;
import com.aerocos.zoo.exception.ErrorBody;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AnimalKindExceptionHandler {
    @ExceptionHandler(AnimalKindNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorBody handleAnimalKindNotFoundException() {
        return new ErrorBody("animal_kind_not_found", "Вид животного не найден");
    }
}
