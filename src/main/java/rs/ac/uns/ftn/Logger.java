package rs.ac.uns.ftn;

/**
 * Created by Micko on 02-Jul-17.
 */
public class Logger {
    private String text;

    public Logger() {
    }

    public Logger(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
