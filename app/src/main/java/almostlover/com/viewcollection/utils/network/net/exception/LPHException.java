package almostlover.com.viewcollection.utils.network.net.exception;

public class LPHException extends RuntimeException {
    private String message;
    private String code;

    public LPHException(String message, String code) {
        this(message);
        this.code = code;
        this.message = message;

    }

    public LPHException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return message;
    }

    public String getCode() {
        return code;
    }
}
