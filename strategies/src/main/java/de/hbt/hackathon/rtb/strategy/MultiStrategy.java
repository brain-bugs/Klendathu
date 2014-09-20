package de.hbt.hackathon.rtb.strategy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.hbt.hackathon.rtb.base.message.output.OutputMessage;
import de.hbt.hackathon.rtb.base.strategy.AbstractStrategy;

public class MultiStrategy extends AbstractStrategy {
	
	private Map<ImportanceCalculator, AbstractStrategy> strategies = new HashMap<ImportanceCalculator, AbstractStrategy>();
	private AbstractStrategy lastStrategy;
	
	public MultiStrategy(AbstractStrategy initialStrategy) {
		lastStrategy = initialStrategy;
	}

	@Override
	public String getName() {
		return "MultiStrategy";
	}
	
	public void addStrategy(ImportanceCalculator importanceCalculator, AbstractStrategy strategy) {
		strategies.put(importanceCalculator, strategy);
	}

	@Override
	public List<OutputMessage> process() {
		Importance max = Importance.NOT_REALLY;
		AbstractStrategy best = null;
		for (Map.Entry<ImportanceCalculator, AbstractStrategy> entry : strategies.entrySet()) {
			Importance importance = entry.getKey().calculate();
			if(max == Importance.NOT_REALLY && importance != Importance.NOT_REALLY) {
				best = entry.getValue();
				max = importance;
			} else if(max == Importance.USEFUL && importance == Importance.IMPORTANT) {
				best = entry.getValue();
				max = importance;
			} 
		}
		if(best == null) {
			best = lastStrategy;
		}
		lastStrategy = best;
		return best.process();
	}

}
