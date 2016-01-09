package at.or.vogt.oe1downloader;

/**
 * Exception to exit.
 */
public class SystemExitException extends Exception {

    /** serial version UID. */
    private static final long serialVersionUID = -4764629914693714887L;

    /** exit code. */
    private final int exitCode;

    /**
     * Constructor.
     * 
     * @param message
     */
    public SystemExitException(final int exitCode, final String message) {
	super(message);
	this.exitCode = exitCode;
    }

    /**
     * Get the exitCode.
     * 
     * @return the exitCode
     */
    public int getExitCode() {
	return exitCode;
    }

}
