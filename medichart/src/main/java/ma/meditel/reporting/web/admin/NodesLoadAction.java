package ma.meditel.reporting.web.admin;

import java.io.File;
import java.util.List;

import ma.meditel.reporting.entities.Node;
import ma.meditel.reporting.metier.interfaces.INodeMetier;
import ma.meditel.reporting.util.LogReader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionSupport;

@Component
public class NodesLoadAction extends ActionSupport {
	private static final long serialVersionUID = 7545692075375052975L;
	private static final String MESSAGE_INPUT_EMPTY = "Veuillez remplir tous les champs obligatoires.";
	private static final String MESSAGE_CSV_ERROR = "Veuillez charger un fichier de type CSV.";
	
	@Autowired
	public INodeMetier nodeMetier;
	
	 private File file;
     @SuppressWarnings("unused")
     private String contentType;
     private String filename;
     
     public String delimiteur;
     public String startLine;
     
     public int totalLines = 0;
     public int notAddedLines = 0;

     public void setUpload(File file) {
        this.file = file;
     }

     public void setUploadContentType(String contentType) {
        this.contentType = contentType;
     }

     public void setUploadFileName(String filename) {
        this.filename = filename;
     }

     public String execute() {
    	if(file == null){
    		return ActionSupport.NONE;
    	}
    	//apres validation
    	try {
			List<String> lines = LogReader.getLinesFromLog(file.getAbsolutePath(), Integer.valueOf(startLine));
			for(String line : lines){
				String[] lineToArray = LogReader.splitLine(line, delimiteur);
				
				String nodeName 		= lineToArray[Node.NODE_NAME].replaceAll("\"", "");
				String deviceCategory 	= lineToArray[Node.DEVICE_CATEGORY].replaceAll("\"", "");
				String deviceFamily		= lineToArray[Node.DEVICE_FAMILY].replaceAll("\"", "");
				String deviceProfile	= lineToArray[Node.DEVICE_PROFILE].replaceAll("\"", "");
				String deviceVendor		= lineToArray[Node.DEVICE_VENDOR].replaceAll("\"", "");
				//String hostname			= lineToArray[Node.HOST_NAME].replaceAll("\"", "");
				String managementAddress= lineToArray[Node.MANAGEMENT_ADDRESS].replaceAll("\"", "");
				String nodeManagedMode	= lineToArray[Node.NODE_MANAGED_MODE].replaceAll("\"", "");
				String systemDescription= lineToArray[Node.SYSTEM_DESCRIPTION].replaceAll("\"", "");
				String systemObjectId	= lineToArray[Node.SYSTEM_OBJECT_ID].replaceAll("\"", "");
				
				Node node = new Node();
				node.setNodeName(nodeName);
				node.setDeviceCategory(deviceCategory);
				node.setDeviceFamily(deviceFamily);
				node.setDeviceProfile(deviceProfile);
				node.setDeviceVendor(deviceVendor);
				node.setManagementAddress(managementAddress);
				node.setNodeManaged(nodeManagedMode);
				node.setSystemDescription(systemDescription);
				node.setSystemObjectId(systemObjectId);
				
				try{
					nodeMetier.addNode(node);
				}catch(Exception e){
					notAddedLines++;
				}
				totalLines++;
			}
			
			addActionMessage("Opération est bien éffectuée. Nombre total de lines: " + totalLines + ", Nombre de lines non inserés: " + notAddedLines + ".");
			
		} catch (Exception e) {
			addActionMessage("Une Erreur survenue!");
		}
    	
        return ActionSupport.SUCCESS;
     }
     @Override
    public void validate() {
    	if(file != null){
    		if(!filename.toLowerCase().endsWith(".csv")){
    			addActionError(MESSAGE_CSV_ERROR);
    			return;
    		}
    		if(delimiteur.trim().equals("")){
    			addActionError(MESSAGE_INPUT_EMPTY);
    			return;
    		}
    		if(startLine.trim().equals("")){
    			addActionError(MESSAGE_INPUT_EMPTY);
    			return;
    		}
    		
    		try{
    			Integer.valueOf(startLine);
    		}catch(Exception e){
    			addActionError("");
    			return;
    		}
    	}
    }
}
