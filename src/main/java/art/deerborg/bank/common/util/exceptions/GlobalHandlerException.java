package art.deerborg.bank.common.util.exceptions;

import art.deerborg.bank.common.util.result.ApiResponse;
import art.deerborg.bank.common.util.result.ApiResponseHelper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalHandlerException {
    @ExceptionHandler(NotFoundIdException.class)
    public ResponseEntity<ApiResponse> handleNotFoundIdException() {
        return new ResponseEntity<>(ApiResponseHelper.NOT_FOUND_ID(), HttpStatus.NOT_FOUND);
    }
}
