package ru.personnel.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.personnel.constants.ResponseConstants;
import ru.personnel.dto.JsonResponseWithData;
import ru.personnel.dto.ViolationDto;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class ErrorHandlingController {

    /**
     * Обработчик исключений ошибки валидации
     *
     * @param e ConstraintViolationException
     */
    @ResponseBody
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus
    public JsonResponseWithData<List<ViolationDto>> constraintValidationException(ConstraintViolationException e) {
        final List<ViolationDto> violations = e.getConstraintViolations()
                .stream()
                .map(violation -> new ViolationDto(
                        violation.getPropertyPath().toString(),
                        violation.getMessage())).collect(Collectors.toList()
                );
        return new JsonResponseWithData<>(ResponseConstants.Code.ERR, "Ошибка валидации значения", violations);
    }

    /**
     * Обработчик исключений проверки аргумента
     *
     * @param e MethodArgumentNotValidException
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public JsonResponseWithData<List<ViolationDto>> methodArgumentNotValidException(MethodArgumentNotValidException e) {
        final List<ViolationDto> violations = e.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> new ViolationDto(
                        error.getField(),
                        error.getDefaultMessage()
                        )
                ).collect(Collectors.toList());
        return new JsonResponseWithData<>(ResponseConstants.Code.ERR, "Ошибка валидации аргумента", violations);
    }
}
