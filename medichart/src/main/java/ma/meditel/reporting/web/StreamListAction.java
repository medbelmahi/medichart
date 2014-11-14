package ma.meditel.reporting.web;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import ma.meditel.reporting.entities.Stream;
import ma.meditel.reporting.metier.interfaces.IStreamMetier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionSupport;

@Component
public class StreamListAction extends ActionSupport {
	private static final long serialVersionUID = 2466326470750022872L;

	private static final String MESSAGE_ERROR_DATE_1 = "Date de début doit être inférieur ou égal au Date de fin.";
	private static final String MESSAGE_ERROR_DATE_2 = "Veuillez respecter le format du date (yyyy-MM-dd hh:mm).";
	private static final String MESSAGE_ERROR_DATE_3 = "Veuillez vérifier si la date entrée en paramètre est inférieur à aujourd'hui.";
	//private static final String MESSAGE_ERROR_NO_STREAM_ITEM = "Aucune enregistrement ni disponible pour la representation.";
	//private static final String MESSAGE_TYPE_TRAFFIC_EMPTY = "Veuillez selectioner un type de stream.";

	@Autowired
	public IStreamMetier streamMetier;
	
	public List<Stream> streams;
	public String startDate, endDate;
	private Date sdate, edate;
	public String[] streamType;
	
	@Override
	public String execute() throws Exception {
		
		try {
			if(sdate != null){
				streams = streamMetier.getAllStreams(sdate, edate);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return ActionSupport.SUCCESS;
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void validate() {
		
		if(startDate != null){
			
			//validation date
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try {
				sdate = formatter.parse(startDate+":00");
				edate = formatter.parse(endDate+":00");
				
				//define date interval (forcage des minutes)
				int sMinutes = (sdate.getMinutes()/5) * 5;
				int eMinutes = (edate.getMinutes()/5) * 5 + 5;
				
				sdate.setMinutes(sMinutes);
				edate.setMinutes(eMinutes);
				
				//Date should be < new date (now)
				if(edate.compareTo(new Date()) > 0){
					addActionError(MESSAGE_ERROR_DATE_3);
				}
				//sDate sould be < eDate
				if(sdate.compareTo(edate)  > 0){
					addActionError(MESSAGE_ERROR_DATE_1);
				}

				
			} catch (ParseException e) {
				addActionError(MESSAGE_ERROR_DATE_2);
			}//end dateFormat try
		}
		
	}
}
