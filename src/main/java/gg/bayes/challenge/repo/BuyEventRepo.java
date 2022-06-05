package gg.bayes.challenge.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import gg.bayes.challenge.entity.BuyEvents;
import gg.bayes.challenge.rest.model.HeroItems;

public interface BuyEventRepo extends CrudRepository<BuyEvents, Long>{
	
	@Query("SELECT new gg.bayes.challenge.rest.model.HeroItems(be.item, be.timestamp) FROM BuyEvents be WHERE be.ingestRequest.id = :matchId and be.hero = :pHero")
	List<HeroItems> getHeroItems(Long matchId, String pHero);
	
	
	
}
