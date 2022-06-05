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
@Table(name = "dim_damage_events")
public class DamageEvents {
	
	@Column(name = "id")
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@JoinColumn(name = "match_id")
	@ManyToOne
	private MatchIngestRequest ingestRequest;
	
	public MatchIngestRequest getIngestRequest() {
		return ingestRequest;
	}

	public void setIngestRequest(MatchIngestRequest ingestRequest) {
		this.ingestRequest = ingestRequest;
	}

	@Column(name = "hero")
	private String hero;
	
	@Column(name = "damaged_hero")
	private String damagedHero;
	
	@Column(name = "damaged_weapon")
	private String damagedWeapon;
	
	@Column(name = "damage_score")
	private Integer damageScore;
	
	@Column(name = "score_before_damage")
	private Integer scoreBeforeDamage;
	
	@Column(name = "score_after_damage")
	private Integer scoreAfterDamage;
	
	@Column(name = "timestamp")
	private String timestamp;

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

	public String getDamagedHero() {
		return damagedHero;
	}

	public void setDamagedHero(String damagedHero) {
		this.damagedHero = damagedHero;
	}

	public String getDamagedWeapon() {
		return damagedWeapon;
	}

	public void setDamagedWeapon(String damagedWeapon) {
		this.damagedWeapon = damagedWeapon;
	}

	public Integer getDamageScore() {
		return damageScore;
	}

	public void setDamageScore(Integer damageScore) {
		this.damageScore = damageScore;
	}

	public Integer getScoreBeforeDamage() {
		return scoreBeforeDamage;
	}

	public void setScoreBeforeDamage(Integer scoreBeforeDamage) {
		this.scoreBeforeDamage = scoreBeforeDamage;
	}

	public Integer getScoreAfterDamage() {
		return scoreAfterDamage;
	}

	public void setScoreAfterDamage(Integer scoreAfterDamage) {
		this.scoreAfterDamage = scoreAfterDamage;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
}
