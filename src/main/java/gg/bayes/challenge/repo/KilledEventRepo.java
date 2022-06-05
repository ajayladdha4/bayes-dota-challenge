package gg.bayes.challenge.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import gg.bayes.challenge.entity.KilledEvents;
import gg.bayes.challenge.rest.model.HeroKills;

public interface KilledEventRepo extends CrudRepository<KilledEvents, Long>{
	
	@Query("SELECT new gg.bayes.challenge.rest.model.HeroKills(ke.hero, count(*)) FROM KilledEvents ke WHERE ke.ingestRequest.id = ?1 group by hero")
	List<HeroKills> findKilledHero(Long matchId);
}
