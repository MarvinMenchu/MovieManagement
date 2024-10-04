package gt.core.MovieManagement.exception;

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
        return super.getMessage().concat("(Object not found: ").concat(this.objectNotFoundName).concat(")");
    }

    public String getObjectNotFoundName() {
        return objectNotFoundName;
    }
}