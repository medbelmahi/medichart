package ma.meditel.reporting.metier.implementations;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ma.meditel.reporting.dao.interfaces.IStreamDao;
import ma.meditel.reporting.entities.Stream;
import ma.meditel.reporting.metier.interfaces.IStreamMetier;

@Transactional
@Service
public class StreamMetierImpl implements IStreamMetier {

	private IStreamDao dao;

	@Override
	public List<Stream> getAllStreams(Date startDate, Date endDate) {
		return dao.getAllStreams(startDate, endDate);
	}
	
	public IStreamDao getDao() {
		return dao;
	}
	
	public void setDao(IStreamDao dao) {
		this.dao = dao;
	}

	@Override
	public List<Stream> getAllStreamsBetween(Date startDate, Date endDate, String Emetteur, String recepteur, String typeStream, String voix) {
		return dao.getAllStreamsBetween(startDate, endDate, Emetteur, recepteur, typeStream, voix);
	}
}
