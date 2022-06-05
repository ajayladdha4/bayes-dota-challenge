package gg.bayes.challenge.match.engine;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public interface MatchProcesser {
	public Pattern getPattern();
	public String getDBQueryTemplate();
	public String populateQuery(Matcher matcher, Long matchId);
	
	default public String processLine(String logEvent, Long matchId) {
		try {
			// Create a matcher for the input String
	        Matcher matcher = getPattern().matcher(logEvent);
	        if (matcher.matches()) {
	            // Get the group matched using group() method
	        	return populateQuery(matcher, matchId);
	        } else {
	        	return "invalid event format >> "+ logEvent;
	        }
		} catch (RuntimeException e) {
			return "";
		}
	}
	
}
