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

import static nl.rvh.rulevalidation.enums.ComparisonOperator.CONTAINS;

class TestBusinessRule {

    Logger log = LoggerFactory.getLogger(TestBusinessRule.class);

    @Test
    void testRule() {

        BusinessRuleSet businessRules = getBusinessRuleSet();
        Assertions.assertFalse(businessRules.evaluate(5));

    }

    @Test
    void serializeAndDeserializeRuleSet() {
        XStream xstream = new XStream(new StaxDriver());

        String xml = xstream.toXML(getBusinessRuleSet());
        log.debug(xml);
        BusinessRuleSet businessRuleSet = (BusinessRuleSet) xstream.fromXML(xml);
        log.debug("{}", businessRuleSet);

        Assertions.assertTrue(businessRuleSet.evaluate(6));

    }

    private BusinessRuleSet getBusinessRuleSet() {
        BusinessRuleSet businessRules = new BusinessRuleSet("Check Golden Cross", LogicalOperator.AND);

        BusinessRule businessRule1 = new MaGoldenCross(CONTAINS, "TEST");
        BusinessRule businessRule2 = new MaGoldenCross(CONTAINS, 5);

        Map<String, Object> succesMap = new HashMap<>();
        succesMap.put("log", "the result is SUCCESS!");

        LogApplicator successLogApplicator = new LogApplicator(succesMap);
        LogApplicator failLogApplicator = new LogApplicator(succesMap);

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