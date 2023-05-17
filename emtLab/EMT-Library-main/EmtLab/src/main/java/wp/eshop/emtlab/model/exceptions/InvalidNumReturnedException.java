package wp.eshop.emtlab.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class InvalidNumReturnedException extends RuntimeException{
    public InvalidNumReturnedException() {

    }
}
