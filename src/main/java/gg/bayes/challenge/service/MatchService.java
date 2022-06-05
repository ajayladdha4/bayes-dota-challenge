package gg.bayes.challenge.service;

import java.util.List;

import gg.bayes.challenge.rest.model.HeroDamage;
import gg.bayes.challenge.rest.model.HeroItems;
import gg.bayes.challenge.rest.model.HeroKills;
import gg.bayes.challenge.rest.model.HeroSpells;

public interface MatchService {
    Long ingestMatch(String payload);
    
    List<HeroKills> getHeroKillsForMatch(Long matchId);

    List<HeroItems> getHeroItems(Long matchId, String heroName);

    List<HeroSpells> getHeroSpells(Long matchId, String heroName);

    List<HeroDamage> getHeroDamages(Long matchId, String heroName);
}
