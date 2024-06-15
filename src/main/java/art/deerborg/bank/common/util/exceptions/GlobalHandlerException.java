package art.deerborg.bank.common.util.exceptions;

import art.deerborg.bank.bank.model.util.excepitons.InsufficientFundsException;
import art.deerborg.bank.bank.model.util.excepitons.InvalidBalanceException;
import art.deerborg.bank.common.util.result.ApiResponse;
import art.deerborg.bank.common.util.result.ApiResponseHelper;
import org.springframework.expression.AccessException;
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
    @ExceptionHandler(InvalidBalanceException.class)
    public ResponseEntity<ApiResponse> handleInvalidBalanceException() {
        return new ResponseEntity<>(ApiResponseHelper.INVALID_BALANCE(), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(InsufficientFundsException.class)
    public ResponseEntity<ApiResponse> handleInsufficientFundsException() {
        return new ResponseEntity<>(ApiResponseHelper.INSUFFICIENT_BALANCE(), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(AccessException.class)
    public ResponseEntity<ApiResponse> handleAccessException() {
        return new ResponseEntity<>(ApiResponseHelper.ACTIVE_ACCOUNT(), HttpStatus.BAD_REQUEST);
    }
}
