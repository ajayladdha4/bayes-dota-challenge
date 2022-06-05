package gg.bayes.challenge.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import gg.bayes.challenge.entity.SpellEvents;
import gg.bayes.challenge.rest.model.HeroSpells;

public interface SpellEventRepo extends CrudRepository<SpellEvents, Long>{

	
	@Query("SELECT new gg.bayes.challenge.rest.model.HeroSpells(se.spell, count(*)) FROM SpellEvents se WHERE se.ingestRequest.id = :matchId and se.hero = :pHero GROUP BY se.spell")
	List<HeroSpells> getHeroSpells(Long matchId, String pHero);
	
}
