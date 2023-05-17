package wp.eshop.emtlab.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class NotEnoughReturnedBooksException extends RuntimeException{
    public NotEnoughReturnedBooksException() {
        super(String.format("The returned number of copies must be greater than zero"));
    }
}
