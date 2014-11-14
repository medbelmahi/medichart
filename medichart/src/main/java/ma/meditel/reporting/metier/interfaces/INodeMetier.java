package ma.meditel.reporting.metier.interfaces;

import java.util.List;

import ma.meditel.reporting.entities.Node;

public interface INodeMetier {
	public void addNode(Node node) throws Exception;
	public void removeNode(Long idNode) throws Exception;
	public Node getNode(Long idNode) throws Exception;
	public Node getNodeWithCollectionInitialize(Long idNode) throws Exception;
	public Node getNode(String nameNode) throws Exception;
	public Node getNodeWithCollectionInitialize(String nameNode) throws Exception;
	public List<Node> getAllNodes() throws Exception;
	public void removeAllNodes() throws Exception;
	
	public Long getNodesCount();
}
