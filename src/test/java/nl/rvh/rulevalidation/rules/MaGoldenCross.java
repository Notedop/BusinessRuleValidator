package nl.rvh.rulevalidation.rules;

import nl.rvh.rulevalidation.BusinessRule;
import nl.rvh.rulevalidation.enums.ComparisonOperator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MaGoldenCross extends BusinessRule {

    private Logger log = LoggerFactory.getLogger(MaGoldenCross.class);

    public MaGoldenCross(ComparisonOperator comparisonOperator, Object expectedValue) {
        super(comparisonOperator, expectedValue, "MaGoldenCross");
    }

    @Override
    public boolean evaluate(Object objectToEvaluate) {
        log.debug("Evaluating {} if {} is {} expected value {}", name, objectToEvaluate, comparisonOperator.getDescription(), this.objectToEvaluate);

        //do some stuff to the objectToEvaluate, or directly pass it for evaluation

        return comparisonOperator.compare(objectToEvaluate, getObjectToEvaluate());
    }

}