package ma.meditel.reporting.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="SYSTEM_FTP")
public class SystemFTP {
	
	public static final String TRAFIC_IN_OUT = "t_in_out";
	public static final String TRAFIC_CLASS = "t_class";
	public static final String STREAM = "stream";
	public static final String CHARGE_CPU = "charge_cpu";
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_SYSTEM_FTP")
	private Long idSystemFTP;
	@Column(name="HOST_ADDRESS")
	private String hostAddress;
	@Column(name="USERNAME")
	private String username;
	@Column(name="PASSWORD")
	private String password;
	@Column(name="PATH")
	private String path;
	@Column(name="TYPE")
	private String type;
	
	public SystemFTP() {
	}

	public Long getIdSystemFTP() {
		return idSystemFTP;
	}

	public void setIdSystemFTP(Long idSystemFTP) {
		this.idSystemFTP = idSystemFTP;
	}

	public String getHostAddress() {
		return hostAddress;
	}

	public void setHostAddress(String hostAddress) {
		this.hostAddress = hostAddress;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
}
