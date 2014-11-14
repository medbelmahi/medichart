package ma.meditel.reporting.web.admin;

import java.io.File;
import java.util.List;

import ma.meditel.reporting.entities.Interface;
import ma.meditel.reporting.entities.InterfaceAutorizedAlias;
import ma.meditel.reporting.entities.Node;
import ma.meditel.reporting.metier.interfaces.IInterfaceAutorizedAliasMetier;
import ma.meditel.reporting.metier.interfaces.IInterfaceMetier;
import ma.meditel.reporting.metier.interfaces.INodeMetier;
import ma.meditel.reporting.util.InterfaceAutorizedAliasHelper;
import ma.meditel.reporting.util.LogReader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionSupport;

@Component
public class InterfacesLoadAction extends ActionSupport {
	private static final long serialVersionUID = 3971209554400745796L;
	private static final String MESSAGE_INPUT_EMPTY = "Veuillez remplir tous les champs obligatoires.";
	private static final String MESSAGE_CSV_ERROR = "Veuillez charger un fichier de type CSV.";

	@Autowired
	public IInterfaceMetier interfaceMetier;
	
	@Autowired
	public INodeMetier nodeMetier;
	@Autowired
	public IInterfaceAutorizedAliasMetier interfaceAutorizedAliasMetier;
	
	private List<InterfaceAutorizedAlias> listAutorizedAlias;

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

	@Override
	public String execute() throws Exception {
		if(file == null){
    		return ActionSupport.NONE;
    	}
    	//apres validation
		try {
			listAutorizedAlias = interfaceAutorizedAliasMetier.getAllInterfaceAutorizedAlias();
			
			List<String> lines = LogReader.getLinesFromLog(file.getAbsolutePath(), Integer.valueOf(startLine));
			for (String line : lines) {
				String[] lineToArray = LogReader.splitLine(line, delimiteur);
				
				try{
					String nodename = lineToArray[Interface.HOSTED_ON].replaceAll("\"", "");
					String ifIndex = lineToArray[Interface.IF_INDEX].replaceAll("\"", "");
					String ifAlias = lineToArray[Interface.IF_ALIAS].replaceAll("\"", "");
					String ifDescr = lineToArray[Interface.IF_DESCR].replaceAll("\"", "");
					String ifName = lineToArray[Interface.IF_NAME].replaceAll("\"", "");
					String ifSpeed = lineToArray[Interface.IF_SPEED].replaceAll("\"", "");
					String ifType = lineToArray[Interface.IF_TYPE].replaceAll("\"", "");
					
					//Respecter la tempalte
					//les logs qui ont un nombre de champs plus que Interface.COLUMN_NUMBER seront ignorés
					if(lineToArray.length > Interface.COLUMN_NUMBER){
						throw new Exception();
					}
					if(!InterfaceAutorizedAliasHelper.isAliasAutorized(ifAlias, listAutorizedAlias)){
						throw new Exception();
					}
					
					//apres validation 2
					Interface i = new Interface();
					i.setIfIndex(ifIndex);
					i.setIfAlias(ifAlias);
					i.setIfDescr(ifDescr);
					i.setIfName(ifName);
					i.setIfSpeed(ifSpeed);
					i.setIfType(ifType);
					
					Node node = nodeMetier.getNode(nodename);
					interfaceMetier.addInteface(i, node.getIdNode());
					
				}catch(Exception e){
					notAddedLines++;
				}
				totalLines++;
			}
			
			addActionMessage("Opération est bien éffectuée. Nombre total de lines: " + totalLines + ", Nombre de lines non inserés: " + notAddedLines + ".");
			
		} catch (Exception e) {
			addActionError("Echec: Une erreur est survenue lors de l'opération.");
		}
		
		return ActionSupport.SUCCESS;
	}

	@Override
	public void validate() {
		if (file != null) {
			if (!filename.toLowerCase().endsWith(".csv")) {
				addActionError(MESSAGE_CSV_ERROR);
				return;
			}
			if (delimiteur.trim().equals("")) {
				addActionError(MESSAGE_INPUT_EMPTY);
				return;
			}
			if (startLine.trim().equals("")) {
				addActionError(MESSAGE_INPUT_EMPTY);
				return;
			}

			try {
				Integer.valueOf(startLine);
			} catch (Exception e) {
				addActionError("");
				return;
			}
		}
	}
}
