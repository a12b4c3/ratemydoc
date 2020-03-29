package ui;

/* custom exception wrapper which allows error msg to be passed
    100 - WRONG_CRED
    105 - WRONG_rID
    200 - WRONG_FORMAT

    800 - UNEXPECT_ARG


 */
public class inputException extends Exception {
    public inputException(String msg) {
        super(msg);
    }
}
