package gg.bayes.challenge.service.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gg.bayes.challenge.entity.MatchIngestRequest;
import gg.bayes.challenge.match.engine.MatchType;
import gg.bayes.challenge.match.engine.ProcesserEngine;
import gg.bayes.challenge.repo.BuyEventRepo;
import gg.bayes.challenge.repo.DamageEventRepo;
import gg.bayes.challenge.repo.KilledEventRepo;
import gg.bayes.challenge.repo.MatchIngestedRepo;
import gg.bayes.challenge.repo.SpellEventRepo;
import gg.bayes.challenge.rest.model.HeroDamage;
import gg.bayes.challenge.rest.model.HeroItems;
import gg.bayes.challenge.rest.model.HeroKills;
import gg.bayes.challenge.rest.model.HeroSpells;
import gg.bayes.challenge.service.MatchService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MatchServiceImpl implements MatchService {
	
	
		
    @Autowired
    public MatchServiceImpl() {
    }
    
    @Autowired
    private BuyEventRepo buyEventRepo;
    
    @Autowired
    private DamageEventRepo damageEventRepo;
    
    @Autowired
    private KilledEventRepo killedEventRepo;
    
    @Autowired
    private SpellEventRepo spellEventRepo;
    
    @Autowired
    private MatchIngestedRepo matchIngestedRepo;
    
    @Autowired
    private EntityManager em;
    
    @Autowired
    private ProcesserEngine processerEngine;

    @Override
    @Transactional(value = TxType.REQUIRES_NEW)
    public Long ingestMatch(String payload) {
//    	String fileName = "data/combatlog_1.txt";
		Map<MatchType, List<String>> validInvalidQuery = null;
		String fileName = savePayload(payload);
		
		
		MatchIngestRequest ingestRequest = new MatchIngestRequest(null, "", fileName, 0, 0, 0, "started", "");
		saveMatch(ingestRequest);
		
		String error =  "";
		String status = "started";
		try {//Files.lines(Paths.get(fileName))
			validInvalidQuery = processerEngine.process(payload, ingestRequest.getId());
			status = "passed";
		} catch (Exception e) {
			error = e.getMessage();
			status = "failed";
		}
		
		ingestRequest.setError(error);
		ingestRequest.setStatus(status);
		
		saveEvents(validInvalidQuery, ingestRequest);
		
		saveMatch(ingestRequest);
		
		return ingestRequest.getId();
    }


	public String savePayload(String payload) {
		UUID uuid=UUID.randomUUID(); 
		String fileName = "payload/"+uuid+".txt";
		PrintWriter out = null;
		try {
			new File(fileName).getParentFile().mkdirs();
			out = new PrintWriter(fileName);
			out.println(payload);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(out!=null)
				out.close();
		}
		return fileName;
	}


	public void saveMatch(MatchIngestRequest ingestRequest) {
		matchIngestedRepo.save(ingestRequest);
	}



   
	public void saveEvents(Map<MatchType, List<String>> validInvalidQuery, MatchIngestRequest ingestRequest) {
		ingestRequest.setValid((validInvalidQuery.get(MatchType.VALID) != null ? validInvalidQuery.get(MatchType.VALID).size(): 0));
		ingestRequest.setInvalid((validInvalidQuery.get(MatchType.INVALID) != null ? validInvalidQuery.get(MatchType.INVALID).size(): 0));
		ingestRequest.setNotIntrested((validInvalidQuery.get(MatchType.NOT_INTERESTED) != null ? validInvalidQuery.get(MatchType.NOT_INTERESTED).size(): 0));
		
		System.out.println(ingestRequest);
		for (String query : validInvalidQuery.get(MatchType.VALID)) {
			em.createNativeQuery(query).executeUpdate();
		}
	}
    
    

	@Override
	public List<HeroKills> getHeroKillsForMatch(Long matchId) {
		return killedEventRepo.findKilledHero(matchId);
	}

	@Override
	public List<HeroItems> getHeroItems(Long matchId, String heroName) {
		return buyEventRepo.getHeroItems(matchId, heroName);
	}

	@Override
	public List<HeroSpells> getHeroSpells(Long matchId, String heroName) {
		return spellEventRepo.getHeroSpells(matchId, heroName);
	}

	@Override
	public List<HeroDamage> getHeroDamages(Long matchId, String heroName) {
		return damageEventRepo.getHeroDamages(matchId, heroName);
	}
}
