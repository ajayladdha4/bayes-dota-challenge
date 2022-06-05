package gg.bayes.challenge.match.engine.impl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

import gg.bayes.challenge.match.engine.MatchProcesser;


@Component
public class SpellMatchProcesser implements MatchProcesser{
	
//	private Pattern spellPattern = Pattern.compile("\\[\\b(.*)\\] \\b(.*) buys item \\b(.*)");
	private String queryTemplate = "insert into dim_spells_events (match_id, timestamp, hero, spell, level, casted_on_hero) values (\'%1$s\', \'%2$s\', \'%3$s\', \'%4$s\', \'%5$s\',\'%6$s\')";
	
//	public static Pattern killedPattern = Pattern.compile("\\[\\b(.*)\\] \\b(.*) is killed by \\b(.*)");
	private Pattern pattern = Pattern.compile("\\[\\b(.*)\\] \\b(.*) casts ability \\b(.*) \\(lvl (.*)\\) on \\b(.*)");
//	public static Pattern damagePattern = Pattern.compile("\\[\\b(.*)\\] \\b(.*) \\b(hits\\s*) \\b(.*) \\b(with) \\b(.*) \\b(for) \\b(.*) \\b(damage)\\b \\((.*)->(.*)\\)");
	
	
	@Override
	public Pattern getPattern() {
		return pattern;
	}

	@Override
	public String getDBQueryTemplate() {
		return queryTemplate;
	}

	@Override
	public String populateQuery(Matcher matcher, Long matchId) {
		String timestamp = matcher.group(1);
    	String hero = matcher.group(2).replace("npc_dota_hero_", "");
    	String spell = matcher.group(3);
    	String level = matcher.group(4);
    	String opHero = matcher.group(5).replace("npc_dota_hero_", "");
		return String.format(getDBQueryTemplate(), matchId, timestamp, hero, spell, level, opHero);
	}

}
