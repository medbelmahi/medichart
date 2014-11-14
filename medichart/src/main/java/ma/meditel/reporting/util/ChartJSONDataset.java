package ma.meditel.reporting.util;

import java.util.ArrayList;
import java.util.List;

public class ChartJSONDataset {
	public String label; 
	public String title; 
	public String fillColor;
	public String strokeColor;
	public String pointColor;
	public String pointStrokeColor;
	public String pointHighlightFill;
	public String pointHighlightStroke;
	
	public List<Double> data = new ArrayList<>();
	
	public void setColor(JSONColor color){
		fillColor = "rgba(" + color.R + ", " + color.G + ", " + color.B + ", 0.2)";
		strokeColor = "rgba(" + color.R + ", " + color.G + ", " + color.B + ", 1)";
		pointColor = "rgba(" + color.R + ", " + color.G + ", " + color.B + ", 1)";
		pointStrokeColor = "#fff";
		pointHighlightFill = "#fff";
		pointHighlightStroke = "rgba(" + color.R + ", " + color.G + ", " + color.B + ", 1)";
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void addData(Double d){
		data.add(d);
	}
	
	public List<Double> getData(){
		return data;
	}
	
	public Double getLastAddedData(){
		if(data.isEmpty()){
			return 0.0;
		}
		
		return data.get(data.size()-1);
	}

	public void setData(List<Double> data) {
		this.data = data;
	}
	
	
}
