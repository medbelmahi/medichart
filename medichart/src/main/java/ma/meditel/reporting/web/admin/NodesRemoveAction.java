package ma.meditel.reporting.web.admin;

import java.util.ArrayList;
import java.util.List;

import ma.meditel.reporting.entities.Node;
import ma.meditel.reporting.metier.interfaces.INodeMetier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionSupport;

@Component
public class NodesRemoveAction extends ActionSupport {
	private static final long serialVersionUID = 8224465761564661473L;
	private static final String MESSAGE_REMOVE = "La Node est bien supprimée.";

	@Autowired
	public INodeMetier nodeMetier;

	public List<Node> nodes = new ArrayList<>();
	public String idNode;

	@Override
	public String execute() throws Exception {
		try {
			if (idNode != null) {
				nodeMetier.removeNode(Long.valueOf(idNode));
				
				addActionMessage(MESSAGE_REMOVE);
			}
			//remplir la list avec les noms des nodes
			nodes = nodeMetier.getAllNodes();
			
		} catch (Exception e) {}
		
		return ActionSupport.SUCCESS;
	}

	@Override
	public void validate() {
		
	}
}
