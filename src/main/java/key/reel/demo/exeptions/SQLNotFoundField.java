package key.reel.demo.exeptions;

public class SQLNotFoundField extends Exception {
    public SQLNotFoundField(String message) {
        super(message);
    }
}
