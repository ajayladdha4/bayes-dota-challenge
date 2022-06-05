package gg.bayes.challenge.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import gg.bayes.challenge.entity.DamageEvents;
import gg.bayes.challenge.rest.model.HeroDamage;

public interface DamageEventRepo extends CrudRepository<DamageEvents, Long>{
	
	@Query("SELECT new gg.bayes.challenge.rest.model.HeroDamage(de.damagedHero, count(id), sum(de.damageScore)) FROM DamageEvents de WHERE de.ingestRequest.id = :matchId and de.hero = :pHero GROUP BY de.damagedHero")
	List<HeroDamage> getHeroDamages(Long matchId, String pHero);
}
