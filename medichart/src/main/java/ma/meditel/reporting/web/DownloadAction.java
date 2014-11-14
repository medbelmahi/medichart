package ma.meditel.reporting.web;
 
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import com.opensymphony.xwork2.ActionSupport;
 
public class DownloadAction extends ActionSupport{
 
	/**
	 * 
	 */
	private static final long serialVersionUID = 473007008329906437L;
	private InputStream fileInputStream;
	public String filename;
 
	public InputStream getFileInputStream() {
		return fileInputStream;
	}
 
	public String execute() throws Exception {
	    fileInputStream = new FileInputStream(new File("C://Users//AMIRAL//Desktop//Dossier//ftp//TestFiles//"+filename));
	    return SUCCESS;
	}
}