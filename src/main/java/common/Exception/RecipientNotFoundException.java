package common.Exception;

/**
 * @author: wangzx
 * @Date: 2018-01-31-10:35
 */
public class RecipientNotFoundException extends Exception {

    public RecipientNotFoundException(String message) {
        super(message);
    }

    public RecipientNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public RecipientNotFoundException(Throwable cause) {
        super(cause);
    }
}
