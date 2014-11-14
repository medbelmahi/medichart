package ma.meditel.reporting.web.admin;

import java.util.ArrayList;
import java.util.List;

import ma.meditel.reporting.entities.InterfacePhysic;
import ma.meditel.reporting.entities.Node;
import ma.meditel.reporting.metier.interfaces.IInterfacePhysicMetier;
import ma.meditel.reporting.metier.interfaces.INodeMetier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionSupport;

@Component
public class InterfacesPhysicsAddAction extends ActionSupport {
	private static final long serialVersionUID = -7865437962726546259L;
	private static final String MESSAGE_INPUT_EMPTY = "Veuillez remplir tous les champs obligatoires";
	private static final String MESSAGE_NAME_EXIST = "L'index saisie est déjà utilisée pour une interface";
	private static final String MESSAGE_INTERFACE_ADDED = "L'interface est bien crée";
	private static final String MESSAGE_NO_NODE = "Veuillez charger d'abord une liste des nodes";
	
	@Autowired
	public INodeMetier nodeMetier;
	@Autowired
	public IInterfacePhysicMetier interfacePhysicsMetier;
	
	public List<String> nodesList = new ArrayList<>();
	
	public String indexInterfacePhysic;
	public String nameInterfacePhysic;
	public String nameNode;
	
	@Override
	public String execute() {
		//Fill the list
		fillListNodes();
		//
		if(indexInterfacePhysic == null){
			return ActionSupport.NONE;
		}
		
		//Apres validation
		InterfacePhysic i = new  InterfacePhysic();
		i.setIndexInterfacePhysic(indexInterfacePhysic);
		i.setNameInterfacePhysic(nameInterfacePhysic);
		try {
			Node node = nodeMetier.getNode(nameNode);
			interfacePhysicsMetier.addInterfacePhysic(i, node.getIdNode());
			
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
		
		if(indexInterfacePhysic != null){
			if(indexInterfacePhysic.trim().isEmpty()){
				addActionError(MESSAGE_INPUT_EMPTY);
			}
			else if(nameInterfacePhysic.trim().isEmpty()){
				addActionError(MESSAGE_INPUT_EMPTY);
			}
			
			else if(nameNode.trim().isEmpty()){
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
