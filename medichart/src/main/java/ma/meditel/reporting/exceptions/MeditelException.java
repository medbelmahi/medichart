package ma.meditel.reporting.exceptions;

public class MeditelException extends Exception {
	private static final long serialVersionUID = -3841074034721149161L;
	
	//Constantes
	public final static String ERROR = "error";
	public final static String ALERT = "alert";
	public final static String INFO = "info";
	public final static String WARNING = "warning";
	
	private String type;
	private String message;
	
	public MeditelException(String type, String message) {
		this.type = type;
		this.message = message;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
}
