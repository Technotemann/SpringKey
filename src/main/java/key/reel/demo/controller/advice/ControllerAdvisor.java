package key.reel.demo.controller.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.support.WebExchangeBindException;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ControllerAdvisor {

//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<String> handleCityNotFoundException(Exception ex) {
//
//        return ResponseEntity.ok(ex.getLocalizedMessage());
//    }

    @ExceptionHandler({Throwable.class})
    public ResponseEntity<List<String>> handleThrowable(Throwable t) {
        if (t instanceof  WebExchangeBindException) {
            WebExchangeBindException w = (WebExchangeBindException)t;
            return new ResponseEntity<>(handleException(w), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(List.of(t.getMessage()), HttpStatus.BAD_REQUEST);
    }

    private static List<String> handleException(WebExchangeBindException e) {
        List<String> reasons = new ArrayList<>();
        for (ObjectError objectError : e.getBindingResult().getAllErrors()) {
            reasons.add(objectError.getDefaultMessage());
        }
        return reasons;
    }
}