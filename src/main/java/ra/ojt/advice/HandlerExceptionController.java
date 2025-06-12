package ra.ojt.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ra.ojt.exception.CustomException;

import java.util.stream.Collectors;

@RestControllerAdvice
public class HandlerExceptionController {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<DataError<String>> handlerValidException(MethodArgumentNotValidException exception) {
        String errorMessage = exception.getFieldErrors()
                .stream()
                .map(fieldError -> fieldError.getDefaultMessage())
                .collect(Collectors.joining(", "));
        return ResponseEntity.badRequest().body(new DataError<>(400, errorMessage));
    }

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<DataError<String>> handlerCustomException(CustomException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new DataError<>(404, exception.getMessage()));
    }
}