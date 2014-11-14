package ma.meditel.reporting.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="STREAM")
public class Stream {
	
	//constants columns
	public static final int SESSION = 0;//prob1_prob2_type_voice
	public static final int DATE = 1;//07/06/2014 11:00
	public static final int DPHIMAX = 0;
	public static final int JPHIMAX = 0;
	public static final int LESSPERECENTMAX = 0;
	//end constant columns
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_STREAM")
	private Long idStream;
	@Column(name="PROB_TRANSMITTER")
	private String probTransmitter;
	@Column(name="PROB_RECEIVER")
	private String probReceiver;
	@Column(name="TYPE_STREAM")
	private String typeStream;
	@Column(name="VOICE")
	private String voice;
	@Column(name="DATE")
	private Date date;
	@Column(name="DPHI_MAX")
	private Double dpHiMax;
	@Column(name="JPHI_MAX")
	private Double jpHiMax;
	@Column(name="LOSS_PRECENT_MAX")
	private Double lossPrecentMax;
	
	public Stream() {
	}

	public Stream(String probTransmitter, String probReceiver, String typeSream, String voice, Date date, Double dpHiMax, Double jpHiMax, Double lossPrecentMax) {
		this.probTransmitter = probTransmitter;
		this.probReceiver = probReceiver;
		this.typeStream = typeSream;
		this.voice = voice;
		this.date = date;
		this.dpHiMax = dpHiMax;
		this.jpHiMax = jpHiMax;
		this.lossPrecentMax = lossPrecentMax;
	}


	public String getVoice() {
		return voice;
	}

	public void setVoice(String voice) {
		this.voice = voice;
	}

	public Long getIdStream() {
		return idStream;
	}

	public void setIdStream(Long idStream) {
		this.idStream = idStream;
	}

	public String getProbTransmitter() {
		return probTransmitter;
	}

	public void setProbTransmitter(String probTransmitter) {
		this.probTransmitter = probTransmitter;
	}

	public String getProbReceiver() {
		return probReceiver;
	}

	public void setProbReceiver(String probReceiver) {
		this.probReceiver = probReceiver;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Double getDpHiMax() {
		return dpHiMax;
	}

	public void setDpHiMax(Double dpHiMax) {
		this.dpHiMax = dpHiMax;
	}

	public Double getJpHiMax() {
		return jpHiMax;
	}

	public void setJpHiMax(Double jpHiMax) {
		this.jpHiMax = jpHiMax;
	}

	public Double getLossPrecentMax() {
		return lossPrecentMax;
	}

	public void setLossPrecentMax(Double lossPrecentMax) {
		this.lossPrecentMax = lossPrecentMax;
	}


	public String getTypeStream() {
		return typeStream;
	}

	public void setTypeStream(String typeSream) {
		this.typeStream = typeSream;
	}
	
	//Sueil test
	public boolean isOverDphi(){
		if(dpHiMax >= 50000) return true;
		return false;
	}
	
	public boolean isOverJphi(){
		if(jpHiMax >= 5000) return true;
		return false;
	}
	
	public boolean isOverLoss(){
		if(lossPrecentMax >= 0.01) return true;
		return false;
	}
	
	public String dateToString(){
		return date.toString().substring(0, date.toString().lastIndexOf(":"));
	}
}
