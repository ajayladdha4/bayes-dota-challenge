package gg.bayes.challenge.entity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "dim_buy_events")
public class BuyEvents {
	
	@Column(name = "id")
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@JoinColumn(name = "match_id")
	@ManyToOne
	private MatchIngestRequest ingestRequest;
	
	@Column(name = "hero")
	private String hero;
	
	@Column(name = "timestamp")
	private String timestamp;
	
	@Column(name = "item")
	private String item;
	
	public String getHero() {
		return hero;
	}
	
	public void setHero(String hero) {
		this.hero = hero;
	}
	
	public String getTimestamp() {
		return timestamp;
	}
	
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	
	public String getItem() {
		return item;
	}
	
	public void setItem(String item) {
		this.item = item;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public MatchIngestRequest getIngestRequest() {
		return ingestRequest;
	}

	public void setIngestRequest(MatchIngestRequest ingestRequest) {
		this.ingestRequest = ingestRequest;
	}
	
	
}
