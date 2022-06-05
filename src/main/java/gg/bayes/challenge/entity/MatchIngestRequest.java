package gg.bayes.challenge.entity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "dim_ingest_match")
public class MatchIngestRequest {
	
	
	
	public MatchIngestRequest() {
		super();
	}

	public MatchIngestRequest(Long id, String timestamp, String file_content, Integer valid, Integer invalid,
			Integer notIntrested, String status, String error) {
		super();
		this.id = id;
		this.timestamp = timestamp;
		this.file_content = file_content;
		this.valid = valid;
		this.invalid = invalid;
		this.notIntrested = notIntrested;
		this.status = status;
		this.error = error;
	}

	@Column(name = "id")
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "timestamp")
	private String timestamp;
	
	@Column(name = "file_content")
	private String file_content;
	
	@Column(name = "valid")
	private Integer valid;
	
	@Column(name = "invalid")
	private Integer invalid;
	
	@Column(name = "not_intrested")
	private Integer notIntrested;
	
	@Column(name = "status")
	private String status;
	
	@Column(name = "error")
	private String error;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getFile_content() {
		return file_content;
	}

	public void setFile_content(String file_content) {
		this.file_content = file_content;
	}

	

	public Integer getValid() {
		return valid;
	}

	public void setValid(Integer valid) {
		this.valid = valid;
	}

	public Integer getInvalid() {
		return invalid;
	}

	public void setInvalid(Integer invalid) {
		this.invalid = invalid;
	}

	public Integer getNotIntrested() {
		return notIntrested;
	}

	public void setNotIntrested(Integer notIntrested) {
		this.notIntrested = notIntrested;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	@Override
	public String toString() {
		return "MatchIngestRequest [id=" + id + ", timestamp=" + timestamp + ", file_content=" + file_content
				+ ", valid=" + valid + ", invalid=" + invalid + ", notIntrested=" + notIntrested + ", status=" + status
				+ ", error=" + error + "]";
	}
	
	
}
