package by.savik.jobbler.exception;

public class HeadHunterApiException extends RuntimeException {
    public HeadHunterApiException(String message) {
        super(message);
    }

    public HeadHunterApiException(String message, Throwable cause) {
        super(message, cause);
    }
}
