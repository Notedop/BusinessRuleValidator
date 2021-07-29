package nl.rvh.rulevalidation;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import nl.rvh.rulevalidation.applicators.LogApplicator;
import nl.rvh.rulevalidation.enums.LogicalOperator;
import nl.rvh.rulevalidation.rules.MaGoldenCross;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

import static nl.rvh.rulevalidation.enums.ComparisonOperator.GREATER_THAN;

class BusinessRuleTest {

    RuleSerializer<BusinessRuleSet> serializer = new RuleSerializer<>();
    Logger log = LoggerFactory.getLogger(BusinessRuleTest.class);

    @Test
    void testRuleOperatorAnd() {

        BusinessRuleSet businessRules = getBusinessRuleSet(LogicalOperator.AND);
        Assertions.assertFalse(businessRules.evaluate(5));
        Assertions.assertFalse(businessRules.evaluate(6));
        Assertions.assertFalse(businessRules.evaluate(3));
        Assertions.assertTrue(businessRules.evaluate(8));

    }

    @Test
    void testRuleOperatorOr() {

        BusinessRuleSet businessRules = getBusinessRuleSet(LogicalOperator.OR);
        Assertions.assertFalse(businessRules.evaluate(5));
        Assertions.assertTrue(businessRules.evaluate(6));
        Assertions.assertFalse(businessRules.evaluate(3));
        Assertions.assertTrue(businessRules.evaluate(8));

    }


    @Test
    void serializeAndDeserializeRuleSet() {
//        XStream xstream = new XStream(new StaxDriver());
//        xstream.autodetectAnnotations(true);

        String xml = serializer.serialize(getBusinessRuleSet(LogicalOperator.AND));
        log.debug(xml);
        BusinessRuleSet businessRuleSet = serializer.deserialize(xml);
        log.debug("{}", businessRuleSet);

        Assertions.assertFalse(businessRuleSet.evaluate(5));
        Assertions.assertFalse(businessRuleSet.evaluate(6));
        Assertions.assertFalse(businessRuleSet.evaluate(3));
        Assertions.assertTrue(businessRuleSet.evaluate(8));

    }

    private BusinessRuleSet getBusinessRuleSet(LogicalOperator operator) {
        BusinessRuleSet businessRules = new BusinessRuleSet("Check Golden Cross", operator);

        BusinessRule businessRule1 = new MaGoldenCross(GREATER_THAN, 7);
        BusinessRule businessRule2 = new MaGoldenCross(GREATER_THAN, 5);

        Map<String, Object> succesMap = new HashMap<>();
        succesMap.put("log", "the result is SUCCESS!");

        Map<String, Object> failMap = new HashMap<>();
        failMap.put("log", "the result is FAIL!");

        LogApplicator successLogApplicator = new LogApplicator(succesMap);
        LogApplicator failLogApplicator = new LogApplicator(failMap);

        businessRule1.setSuccessResultApplicator(successLogApplicator);
        businessRule2.setSuccessResultApplicator(successLogApplicator);
        businessRule1.setFailResultApplicator(failLogApplicator);
        businessRule2.setFailResultApplicator(failLogApplicator);

        businessRules.addRule(businessRule1);
        businessRules.addRule(businessRule2);

        businessRules.setSuccessResultApplicator(successLogApplicator);
        businessRules.setFailResultApplicator(failLogApplicator);
        return businessRules;
    }


}