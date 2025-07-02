package ra.ojt.advice;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ra.ojt.exception.*;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class HandlerExceptionController {
    // Lỗi validate DTO (@NotNull, @NotBlank, ...)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<DataError<Map<String, String>>> handleValidationException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(err ->
                errors.put(err.getField(), err.getDefaultMessage())
        );
        return ResponseEntity.badRequest().body(
                DataError.<Map<String, String>>builder()
                        .code(400)
                        .message(errors)
                        .build()
        );
    }

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<DataError<String>> handlerCustomException(CustomException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new DataError<>(404, exception.getMessage()));
    }

    // Lỗi khi không tìm thấy dữ liệu
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<DataError<String>> handleNotFound(ResourceNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(DataError.<String>builder()
                        .code(404)
                        .message(ex.getMessage())
                        .build());
    }

    //Lỗi xử lý logic nghiệp vụ (ví dụ chưa thanh toán, status chưa đúng)
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<DataError<String>> handleBusiness(BusinessException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(DataError.<String>builder()
                        .code(400)
                        .message(ex.getMessage())
                        .build());
    }
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
    @ExceptionHandler(ExistsException.class)
    public ResponseEntity<Map<String, DataError<String>>> handlerExistsException(ExistsException e) {
        DataError<String> err = new DataError<>();
        err.setCode(409); //409 conflict   -   dữ liệu đã tồn tại
        err.setMessage(e.getMessage());
        Map<String, DataError<String>> map = new HashMap<>();
        map.put("Error", err);
        return new ResponseEntity<>(map, HttpStatus.CONFLICT);
    }

    // Bắt và xữ lý lỗi Not Allowed Exception
    @ExceptionHandler(NotAllowedException.class)
    public ResponseEntity<Map<String, DataError<String>>> handlerNotAlowedException(NotAllowedException e) {
        DataError<String> err = new DataError<>();
        err.setCode(400); //409 conflict  -   trạng thái không phù hợp với yêu cầu
        err.setMessage(e.getMessage());
        Map<String, DataError<String>> map = new HashMap<>();
        map.put("Error", err);
        return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
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




    // Lỗi hệ thống như gửi mail thất bại...
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<DataError<String>> handleServerError(RuntimeException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(DataError.<String>builder()
                        .code(500)
                        .message("Error: " + ex.getMessage())
                        .build());
    }
}