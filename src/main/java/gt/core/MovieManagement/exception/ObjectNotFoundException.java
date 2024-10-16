package gt.core.MovieManagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ObjectNotFoundException extends RuntimeException {

    private final String objectNotFoundName;
    private final Throwable cause;

    public ObjectNotFoundException(String message, Throwable cause) {
        this.objectNotFoundName = message;
        this.cause = cause;
    }

    public ObjectNotFoundException(String message) {
        this.objectNotFoundName = message;
        this.cause = null;
    }

    @Override
    public String getMessage() {
        String message = super.getMessage();

        if (message == null) {
            message = "";
        }

        return message.concat("(Object not found: ").concat(this.objectNotFoundName).concat(")");
    }

    public String getObjectNotFoundName() {
        return objectNotFoundName;
    }
}