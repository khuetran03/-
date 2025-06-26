package ra.ojt.advice;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ra.ojt.exception.CustomException;
import ra.ojt.exception.ExistsException;
import ra.ojt.exception.NotAllowedException;
import ra.ojt.exception.NotFoundException;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class HandlerExceptionController {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, DataError<String>>> handlerValidException(MethodArgumentNotValidException exception) {
        String errorMessage = exception.getFieldErrors()
                .stream()
                .map(fieldError -> fieldError.getDefaultMessage())
                .collect(Collectors.joining(", "));
        Map<String,DataError<String>> map = new HashMap<>();
        map.put("error", new DataError(400, errorMessage));
        return ResponseEntity.badRequest().body(map);
    }

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<Map<String,DataError<String>>> handlerCustomException(CustomException exception) {
        Map<String, DataError<String>> map = new HashMap<>();
        map.put("error", new DataError<>(400, exception.getMessage()));
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(map);
    }
    //
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Map<String,DataError<String>>> handleEmptyBody(HttpMessageNotReadableException e) {
        DataError<String> err = new DataError<>();
        err.setCode(400);
        err.setMessage("Request body không hợp lệ hoặc bị thiếu");
        Map<String, DataError<String>> map = new HashMap<>();
        map.put("error", err);
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(map);
    }
    // Bắt và xữ lý lỗi NotfoundException
    @ExceptionHandler
    public ResponseEntity<Map<String, DataError<String>>> handlerNotFoundException(NotFoundException e) {
        DataError<String> err = new DataError<>();
        err.setCode(404);
        err.setMessage(e.getMessage());
        Map<String, DataError<String>> map = new HashMap<>();
        map.put("Error", err);
        return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
    }
    // Bắt và xữ lý lỗi ExistException
    @ExceptionHandler
    public ResponseEntity<Map<String, DataError<String>>> handlerExistsException(ExistsException e) {
        DataError<String> err = new DataError<>();
        err.setCode(409); //409 conflict   -   dữ liệu đã tồn tại
        err.setMessage(e.getMessage());
        Map<String, DataError<String>> map = new HashMap<>();
        map.put("Error", err);
        return new ResponseEntity<>(map, HttpStatus.CONFLICT);
    }

    // Bắt và xữ lý lỗi Not Allowed Exception
    @ExceptionHandler
    public ResponseEntity<Map<String, DataError<String>>> handlerNotAlowedException(NotAllowedException e) {
        DataError<String> err = new DataError<>();
        err.setCode(409); //409 conflict  -   trạng thái không phù hợp với yêu cầu
        err.setMessage(e.getMessage());
        Map<String, DataError<String>> map = new HashMap<>();
        map.put("Error", err);
        return new ResponseEntity<>(map, HttpStatus.CONFLICT);
    }

    // Lỗi conflict dữ liệu do ràng buộc Unique
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Map<String, DataError<String>>> handlerConflictException(DataIntegrityViolationException e) {
        DataError<String> err = new DataError<>();
        err.setCode(409);
        err.setMessage(HttpStatus.CONFLICT.name());
        Map<String, DataError<String>> map = new HashMap<>();
        map.put("Error", err);
        return new ResponseEntity<>(map, HttpStatus.CONFLICT);
    }
}