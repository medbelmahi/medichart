package ma.meditel.reporting.metier.implementations;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ma.meditel.reporting.dao.interfaces.INodeDao;
import ma.meditel.reporting.entities.Node;
import ma.meditel.reporting.metier.interfaces.INodeMetier;

@Transactional
@Service
public class NodeMetierImpl implements INodeMetier {

	private INodeDao dao;
	
	@Override
	public void addNode(Node node) throws Exception {
		dao.addNode(node);
	}

	@Override
	public void removeNode(Long idNode) throws Exception {
		dao.removeNode(idNode);
	}

	@Override
	public Node getNode(Long idNode) throws Exception {
		return dao.getNode(idNode);
	}

	@Override
	public Node getNode(String nameNode) throws Exception {
		return dao.getNode(nameNode);
	}

	@Override
	public List<Node> getAllNodes() throws Exception {
		return dao.getAllNodes();
		//
	}
	
	@Override
	public Node getNodeWithCollectionInitialize(Long idNode) throws Exception {
		return dao.getNodeWithCollectionInitialize(idNode);
	}

	@Override
	public Node getNodeWithCollectionInitialize(String nameNode) throws Exception {
		return dao.getNodeWithCollectionInitialize(nameNode);
	}
	
	@Override
	public void removeAllNodes() throws Exception {
		dao.removeAllNodes();
	}


	public INodeDao getDao() {
		return dao;
	}

	public void setDao(INodeDao dao) {
		this.dao = dao;
	}

	@Override
	public Long getNodesCount() {
		return dao.getNodesCount();
	}
}
