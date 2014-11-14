package ma.meditel.reporting.metier.interfaces;

import java.util.Date;
import java.util.List;

import ma.meditel.reporting.entities.Stream;

public interface IStreamMetier {
	public List<Stream> getAllStreams(Date startDate, Date endDate);
	public List<Stream> getAllStreamsBetween(Date startDate, Date endDate, String Emetteur, String recepteur, String typeStream, String voix);
}
