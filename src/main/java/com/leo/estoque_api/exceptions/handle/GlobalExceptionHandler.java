package com.leo.estoque_api.exceptions.handle;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.PropertyBindingException;
import com.leo.estoque_api.exceptions.BusinessRuleException;
import com.leo.estoque_api.exceptions.EntityNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {


    private static final String MSG_GENERIC_ERROR = "Ocorreu um erro inesperado no sistema. Tente novamente " +
            "e se o problema persistir, entre em contato com um administrador do sistema.";

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers,
                                                                  HttpStatusCode status, WebRequest request) {
        Throwable rootCause = ex.getCause();

        if (rootCause instanceof InvalidFormatException) {
            return handleInvalidFormatException((InvalidFormatException) rootCause, headers, status, request);
        } else if (rootCause instanceof PropertyBindingException) {
            return  handlePropertyBindingException((PropertyBindingException) rootCause, headers, status, request);
        }

        TypeError type = TypeError.INVALID_BODY;
        String message = "O corpo da requisção está inválido. Verifique a sintaxe.";

        ErrorResponse errorResponse = createErrorResponse((HttpStatus) status, type, message).build();
        return handleExceptionInternal(ex, errorResponse, headers, status, request);
    }

    private ResponseEntity<Object> handlePropertyBindingException(PropertyBindingException ex, HttpHeaders headers,
                                                                  HttpStatusCode status, WebRequest request) {
        String cause = ex.getPath().stream()
                .map(JsonMappingException.Reference::getFieldName)
                .collect(Collectors.joining("."));

        TypeError type = TypeError.INVALID_BODY;
        String message = String.format(
                "A propriedade '%s' não existe. Corrija ou remova essa "
                        + "propriedade e tente novamente.", cause);

        ErrorResponse errorResponse = createErrorResponse((HttpStatus) status, type, message).build();
        return handleExceptionInternal(ex, errorResponse, headers, status, request);
    }

    private ResponseEntity<Object> handleInvalidFormatException(InvalidFormatException ex, HttpHeaders headers,
                                                                HttpStatusCode status, WebRequest request) {
        String cause = ex.getPath().stream()
                .map(JsonMappingException.Reference::getFieldName)
                .collect(Collectors.joining("."));

        TypeError type = TypeError.INVALID_BODY;
        String message = String.format(
                "A propriedade '%s' recebeu um valor '%s', que é um valor inválido. Corrija e informe o valor " +
                        "compativel com tipo %s.",
                cause,
                ex.getValue(),
                ex.getTargetType().getSimpleName());

        ErrorResponse errorResponse = createErrorResponse((HttpStatus) status, type, message).build();
        return handleExceptionInternal(ex, errorResponse, headers, status, request);
    }


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers,
                                                                  HttpStatusCode status, WebRequest request) {
        String messageError = "Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente.";
        TypeError type = TypeError.INVALID_DATA;

        List<ErrorResponse.Field> fields = ex.getFieldErrors().stream()
                .map(fieldError -> {
                    String message = fieldError.getDefaultMessage();

                    return ErrorResponse.Field.builder()
                            .field(fieldError.getField())
                            .message(message)
                            .build();
                })
                .toList();

        ErrorResponse errorResponse = createErrorResponse((HttpStatus) status, type, messageError)
                .fields(fields).build();

        return handleExceptionInternal(ex, errorResponse, headers, status, request);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleBusinessRuleException(Exception ex, WebRequest request) {
        TypeError type = TypeError.SYSTEM_ERROR;
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

        ErrorResponse errorResponse = createErrorResponse(status, type, MSG_GENERIC_ERROR).build();
        return handleExceptionInternal(ex, errorResponse, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(BusinessRuleException.class)
    public ResponseEntity<Object> handleBusinessRuleException(BusinessRuleException ex, WebRequest request) {
        TypeError type = TypeError.BUSINESS_ROLE_VIOLATION;
        String message = ex.getMessage();
        HttpStatus status = HttpStatus.BAD_REQUEST;

        ErrorResponse errorResponse = createErrorResponse(status, type, message).build();

        return handleExceptionInternal(ex, errorResponse, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object> handleEntityNotFoundException(EntityNotFoundException ex, WebRequest request) {
        TypeError type = TypeError.ENTITY_NOT_FOUND;
        String message = ex.getMessage();
        HttpStatus status = HttpStatus.NOT_FOUND;

        ErrorResponse errorResponse = createErrorResponse(status, type, message).build();

        return handleExceptionInternal(ex, errorResponse, new HttpHeaders(), status, request);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
                                                             HttpStatusCode statusCode, WebRequest request) {
        if (body == null) {
            body = createErrorResponse(
                    (HttpStatus) statusCode,
                    TypeError.SYSTEM_ERROR,
                    HttpStatus.valueOf(statusCode.value()).getReasonPhrase()
            ).build();
        } else if (body instanceof String) {
            body = createErrorResponse(
                    (HttpStatus) statusCode,
                    TypeError.SYSTEM_ERROR,
                    (String) body
            ).build();
        }

        return super.handleExceptionInternal(ex, body, headers, statusCode, request);
    }

    public ErrorResponse.ErrorResponseBuilder createErrorResponse(HttpStatus status, TypeError type, String message) {
        return ErrorResponse.builder()
                .timestamp(OffsetDateTime.now())
                .status(status.value())
                .error(type.getType())
                .message(message);
    }

}
