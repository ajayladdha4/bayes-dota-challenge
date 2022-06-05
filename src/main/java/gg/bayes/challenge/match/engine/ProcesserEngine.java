package gg.bayes.challenge.match.engine;

import java.util.List;
import java.util.Map;

public interface ProcesserEngine {
	public Map<MatchType, List<String>>  process(String payload, Long matchId) throws Exception;
}
