package ma.meditel.reporting.web;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ma.meditel.reporting.entities.Stream;
import ma.meditel.reporting.metier.interfaces.IStreamMetier;
import ma.meditel.reporting.util.ChartJSONObject;
import ma.meditel.reporting.util.JSONColor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport;

@Component
public class StreamGrapheAction extends ActionSupport {
	private static final long serialVersionUID = -6911311772432720400L;
	
	@Autowired
	public IStreamMetier streamMetier;

	public String emetteur;
	public String recepteur;
	public String startDate, endDate;
	public String typeStream;
	public String voix;
	public String typeGraphe;
	
	public String chartData;//Chart Json String
	
	private Date sdate, edate;

	public boolean theTypeGraphe;

	public boolean typeDeGraphe;
	
	@Override
	public String execute() throws Exception {
		if(emetteur!=null && recepteur!=null && startDate!=null && endDate!=null){
			List<Stream> streams = streamMetier.getAllStreamsBetween(sdate, edate, emetteur, recepteur, typeStream, voix);
			
			theTypeGraphe = false;
			typeDeGraphe = true;
			
			//new JSON Object
			ChartJSONObject jsonObj = new ChartJSONObject(); 
			//jsonObj.renderLabel(sdate, edate, 60);
			
			List<Double> tempDataset = new ArrayList<>();
			List<Double> tempDatasetSeuil = new ArrayList<>();
			
			for(Stream s : streams){
				
				jsonObj.addLabel(s.getDate().toString().substring(0, s.getDate().toString().lastIndexOf(":")));
				
				Double d = 0.0;//da=donnees
				Double sueil = 0.0;//seuil
				if(typeGraphe.equals("DPHI")){
					d = s.getDpHiMax();
					sueil = 50000.0;
				} else if(typeGraphe.equals("JPHI")){
					d = s.getJpHiMax();
					sueil = 5000.0;
				}else {
					d = s.getLossPrecentMax();
					sueil = 0.01;
				}
				tempDataset.add(d);
				tempDatasetSeuil.add(sueil);
			}
			
			//Set JsonObject attributes
			jsonObj.addDataset();
			jsonObj.getLastDataset().setData(tempDataset);
			//end set Attributes
			
			//Format Graphe (Title and Label and Color)
			try {
				jsonObj.getLastDataset().setLabel(emetteur + "_" + recepteur + "_" + typeStream + "_" + voix + "_" + typeGraphe);
				jsonObj.getLastDataset().setTitle(emetteur + "_" + recepteur + "_" + typeStream + "_" + voix + "_" + typeGraphe);
				jsonObj.getLastDataset().setColor(JSONColor.getLisColors(0)[0]);
				
			} catch (Exception e) {}
			
			//Set JsonObject attributes
			jsonObj.addDataset();
			jsonObj.getLastDataset().setData(tempDatasetSeuil);
			//end set Attributes
			
			//Format Graphe (Title and Label and Color)
			try {
				jsonObj.getLastDataset().setLabel("Max " + typeGraphe);
				jsonObj.getLastDataset().setTitle("Max " + typeGraphe);
				jsonObj.getLastDataset().setColor(JSONColor.getLisColors(2)[1]);
				
			} catch (Exception e) {}
			
			//Convert JsonObject to String
			chartData = new Gson().toJson(jsonObj);
		}
		
		return ActionSupport.SUCCESS;
	}
	
	@Override
	public void validate() {
		if(emetteur==null || recepteur==null || startDate==null || endDate==null || typeStream==null || voix==null || typeGraphe==null){
			addActionError("Echec de la representation du graphe!");
			return;
		}
		
		//Conversion du date
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			sdate = formatter.parse(startDate + ":00");
			edate = formatter.parse(endDate  + ":00");
			
			
		} catch (Exception e) {
			//e.printStackTrace();
			addActionError("Echec de la representation du graphe!");
			return;
		}
		
	}
}
