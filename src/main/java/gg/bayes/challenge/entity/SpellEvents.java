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
@Table(name = "dim_spells_events")
public class SpellEvents {
	
	@Column(name = "id")
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(name = "hero")
	private String hero;
	
	@JoinColumn(name = "match_id")
	@ManyToOne
	private MatchIngestRequest ingestRequest;
	
	@Column(name = "timestamp")
	private String timestamp;
	
	@Column(name = "spell")
	private String spell;
	
	@Column(name = "casted_on_hero")
	private String castedOnHero;
	
	@Column(name = "level")
	private Integer level;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public String getSpell() {
		return spell;
	}

	public void setSpell(String spell) {
		this.spell = spell;
	}

	public String getCastedOnHero() {
		return castedOnHero;
	}

	public void setCastedOnHero(String castedOnHero) {
		this.castedOnHero = castedOnHero;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public MatchIngestRequest getIngestRequest() {
		return ingestRequest;
	}

	public void setIngestRequest(MatchIngestRequest ingestRequest) {
		this.ingestRequest = ingestRequest;
	}
}
