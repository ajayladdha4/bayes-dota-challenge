package gg.bayes.challenge.match.engine;

public interface ProcesserFactory {
	public MatchProcesser getProcessor(String payload);
}
