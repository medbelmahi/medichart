package ma.meditel.reporting.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ChartJSONObject {
	public List<String> getLabels() {
		return labels;
	}

	public void setLabels(List<String> labels) {
		this.labels = labels;
	}

	public List<ChartJSONDataset> getDatasets() {
		return datasets;
	}

	public void setDatasets(List<ChartJSONDataset> datasets) {
		this.datasets = datasets;
	}

	public List<String> labels = new ArrayList<>();
	public List<ChartJSONDataset> datasets = new ArrayList<>();
	
	public void addLabel(String l){
		labels.add(l);
	}
	
	public void addDataset(){
		datasets.add(new ChartJSONDataset());
	}
	
	public ChartJSONDataset getDataset(int i){
		return datasets.get(i);
	}
	
	public ChartJSONDataset getLastDataset(){
		return datasets.get(datasets.size()-1);
	}
	//Render labels
	@SuppressWarnings("deprecation")
	public void renderLabel(Date sdate, Date edate, int margeAgregation){
		List<String> tempListLabel = new ArrayList<>();
		for(Date d = new Date(sdate.getTime()); d.compareTo(edate)<=0; d.setMinutes(d.getMinutes() + margeAgregation)){
			String label = "";
			int dd = d.getMonth()+1; 
			label += d.getDate() + "/" + dd + " " + d.getHours() + ":" + d.getMinutes();
			tempListLabel.add(label);
		}
		this.setLabels(tempListLabel);//JsonObject setLabel
	}
	
	//Render labels version2
		@SuppressWarnings("deprecation")
		public void renderLabel_V2(Date sdate, Date edate, int margeAgregation){
			List<String> tempListLabel = new ArrayList<>();
			sdate.setMinutes(0);
			if(margeAgregation != 1440)
			{
				for(Date d = new Date(sdate.getTime()); d.compareTo(edate)<=0; d.setMinutes(d.getMinutes() + margeAgregation)){
					String label = "";
					int dd = d.getMonth()+1; 
//					label += d.getDate() + "/" + dd + " " + d.getHours() + ":" + d.getMinutes();
					if(d.getHours() == 12 && d.getMinutes()==0) label += d.getDate();
					else label +=" ";
					tempListLabel.add(label);
				}
			}else
			{
				for(Date d = new Date(sdate.getTime()); d.compareTo(edate)<=0; d.setMinutes(d.getMinutes() + margeAgregation)){
					String label = "";
					int dd = d.getMonth()+1; 
//					label += d.getDate() + "/" + dd + " " + d.getHours() + ":" + d.getMinutes();
					label += d.getDate();
					tempListLabel.add(label);
				}
			}
			
			this.setLabels(tempListLabel);//JsonObject setLabel
		}
	
	//Check if dataset grather then 50 (chart condensé)
	public boolean isDatasetRepresentable(){
		if(labels.size() > 80){
			return false;
		}
		
		if(labels.size() < 2){
			return false;
		}
		return true;
	}
	
	//Check if dataset grather then 80 (chart condensé)
	public boolean isDatasetGratherThen(int variable){
		if(labels.size() > variable){
			return false;
		}
		
		return true;
	}
}


