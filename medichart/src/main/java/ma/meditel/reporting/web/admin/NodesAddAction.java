package ma.meditel.reporting.web.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ma.meditel.reporting.entities.Node;
import ma.meditel.reporting.metier.interfaces.INodeMetier;

import com.opensymphony.xwork2.ActionSupport;

@Component
public class NodesAddAction extends ActionSupport {
	private static final long serialVersionUID = 5873827496527062886L;
	private static final String MESSAGE_INPUT_EMPTY = "Veuillez remplir tous les champs obligatoires";
	private static final String MESSAGE_NAME_EXIST = "Le nom saisie est déjà utilisée pour une node";
	private static final String MESSAGE_NODE_ADDED = "La node est bien crée";
	
	@Autowired
	public INodeMetier nodeMetier;
	
	public String nameNode;
	public String deviceCat;
	public String deviceFamily;
	public String deviceProfile;
	public String deviceVendor;
	public String managementAddress;
	public String nodeManaged;
	public String systemDescription;
	public String systemObjectId;

	@Override
	public String execute() throws Exception {
		if(nameNode == null){
			return ActionSupport.NONE;
		}
		//apres validation
		addActionMessage(MESSAGE_NODE_ADDED);
		return ActionSupport.SUCCESS;
	}
	
	@Override
	public void validate() {
		if(nameNode != null){
			//etape 1
			if(nameNode.trim().equals("")){
				addActionError(MESSAGE_INPUT_EMPTY);
				return;
			}
			if(deviceCat.trim().equals("")){
				addActionError(MESSAGE_INPUT_EMPTY);
				return;
			}
			if(deviceFamily.trim().equals("")){
				addActionError(MESSAGE_INPUT_EMPTY);
				return;
			}
			if(deviceProfile.trim().equals("")){
				addActionError(MESSAGE_INPUT_EMPTY);
				return;
			}
			if(deviceVendor.trim().equals("")){
				addActionError(MESSAGE_INPUT_EMPTY);
				return;
			}
			if(managementAddress.trim().equals("")){
				addActionError(MESSAGE_INPUT_EMPTY);
				return;
			}
			if(nodeManaged.trim().equals("")){
				addActionError(MESSAGE_INPUT_EMPTY);
				return;
			}
			if(systemDescription.trim().equals("")){
				addActionError(MESSAGE_INPUT_EMPTY);
				return;
			}
			if(systemObjectId.trim().equals("")){
				addActionError(MESSAGE_INPUT_EMPTY);
				return;
			}
			//etaps 2
			Node n = new Node();
			n.setNodeName(nameNode);
			n.setDeviceCategory(deviceCat);
			n.setDeviceFamily(deviceFamily);
			n.setDeviceProfile(deviceProfile);
			n.setDeviceVendor(deviceVendor);
			n.setManagementAddress(managementAddress);
			n.setNodeManaged(nodeManaged);
			n.setSystemDescription(systemDescription);
			n.setSystemObjectId(systemObjectId);
			
			try{
				nodeMetier.addNode(n);
			}catch(Exception e){
				addActionError(MESSAGE_NAME_EXIST);
			}
		}
	}
}
