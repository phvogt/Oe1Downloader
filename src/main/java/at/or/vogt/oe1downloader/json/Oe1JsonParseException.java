package at.or.vogt.oe1downloader.json;

/**
 * Error parsing json.
 */
public class Oe1JsonParseException extends Exception {

    /** serial version UID. */
    private static final long serialVersionUID = 3627107431027100383L;

    /**
     * Constructor.
     * @param message message
     * @param cause cause
     */
    public Oe1JsonParseException(final String message, final Throwable cause) {
        super(message, cause);
    }

}
