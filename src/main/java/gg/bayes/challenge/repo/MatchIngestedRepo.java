package gg.bayes.challenge.repo;

import org.springframework.data.repository.CrudRepository;

import gg.bayes.challenge.entity.BuyEvents;
import gg.bayes.challenge.entity.MatchIngestRequest;

public interface MatchIngestedRepo extends CrudRepository<MatchIngestRequest, Long>{

}
