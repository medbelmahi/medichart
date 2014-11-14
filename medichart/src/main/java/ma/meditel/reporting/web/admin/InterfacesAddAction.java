package ma.meditel.reporting.web.admin;

import java.util.ArrayList;
import java.util.List;

import ma.meditel.reporting.entities.Interface;
import ma.meditel.reporting.entities.Node;
import ma.meditel.reporting.metier.interfaces.IInterfaceMetier;
import ma.meditel.reporting.metier.interfaces.INodeMetier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionSupport;

@Component
public class InterfacesAddAction extends ActionSupport {
	private static final long serialVersionUID = -7865437962726546259L;
	private static final String MESSAGE_INPUT_EMPTY = "Veuillez remplir tous les champs obligatoires";
	private static final String MESSAGE_NAME_EXIST = "L'index saisie est déjà utilisée pour une interface";
	private static final String MESSAGE_INTERFACE_ADDED = "L'interface est bien crée";
	private static final String MESSAGE_NO_NODE = "Veuillez charger d'abord une liste des nodes";
	
	@Autowired
	public INodeMetier nodeMetier;
	@Autowired
	public IInterfaceMetier interfaceMetier;
	
	public List<String> nodesList = new ArrayList<>();
	
	public String ifIndex;
	public String ifAlias;
	public String ifDescr;
	public String ifName;
	public String ifSpeed;
	public String ifType;
	public String nameNode;
	
	@Override
	public String execute() {
		
		//Fill the list
		fillListNodes();
		//
		if(ifIndex == null){
			return ActionSupport.NONE;
		}
		
		//Apres validation
		Interface i = new Interface();
		i.setIfIndex(ifIndex);
		i.setIfAlias(ifAlias);
		i.setIfDescr(ifDescr);
		i.setIfName(ifName);
		i.setIfSpeed(ifSpeed);
		i.setIfType(ifType);
		try {
			Node node = nodeMetier.getNode(nameNode);
			interfaceMetier.addInteface(i, node.getIdNode());
			
			addActionMessage(MESSAGE_INTERFACE_ADDED);
			
		} catch (Exception e) {
			addActionError(MESSAGE_NAME_EXIST);
		}
		return ActionSupport.SUCCESS;
	}
	
	@Override
	public void validate() {
		fillListNodes();
		if(nodesList.isEmpty()){
			addActionError(MESSAGE_NO_NODE);
			return;
		}

		if(ifIndex != null){
			if(ifIndex.trim().isEmpty()){
				addActionError(MESSAGE_INPUT_EMPTY);
			}
			else if(ifAlias.trim().isEmpty()){
				addActionError(MESSAGE_INPUT_EMPTY);
			}
			else if(ifDescr.trim().isEmpty()){
				addActionError(MESSAGE_INPUT_EMPTY);
			}
			else if(ifName.trim().isEmpty()){
				addActionError(MESSAGE_INPUT_EMPTY);
			}
			else if(ifSpeed.trim().isEmpty()){
				addActionError(MESSAGE_INPUT_EMPTY);
			}
			else if(ifType.trim().isEmpty()){
				addActionError(MESSAGE_INPUT_EMPTY);
			}
		}
	}
	
	private void fillListNodes(){
		try {
			List<Node> list = nodeMetier.getAllNodes();
			for(Node n : list){
				nodesList.add(n.getNodeName());
			}
		} catch (Exception e) {}

	}
}
