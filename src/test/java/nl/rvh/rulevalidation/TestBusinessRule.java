package nl.rvh.rulevalidation;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.jsontype.TypeResolverBuilder;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import nl.rvh.rulevalidation.enums.LogicalOperator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

import static nl.rvh.rulevalidation.enums.ComparisonOperator.GREATER_THAN;

class TestBusinessRule {

    Logger log = LoggerFactory.getLogger(TestBusinessRule.class);

    @Test
    void testRule() {

        BusinessRuleSet businessRules = getBusinessRuleSet();
        Assertions.assertFalse(businessRules.evaluate(5));

    }

    @Test
    void serializeAndDeserializeRuleSet() throws JsonProcessingException {
        XmlMapper xmlMapper = new XmlMapper();

//        TypeResolverBuilder<?> typeResolver = new CustomTypeResolverBuilder();
//        typeResolver.init(JsonTypeInfo.Id.CLASS, null);
//        typeResolver.inclusion(JsonTypeInfo.As.PROPERTY);
//        typeResolver.typeProperty("@CLASS");
//        xmlMapper.setDefaultTyping(typeResolver);
//        xmlMapper.enableDefaultTyping();
        String xml = xmlMapper.writeValueAsString(getBusinessRuleSet());
        log.debug(xml);
        BusinessRuleSet businessRuleSet = xmlMapper.readValue(xml, BusinessRuleSet.class);
        log.debug("{}", businessRuleSet);

        Assertions.assertTrue(businessRuleSet.evaluate(6));

    }

    private BusinessRuleSet getBusinessRuleSet() {
        BusinessRuleSet businessRules = new BusinessRuleSet("Check Golden Cross", LogicalOperator.AND);

        BusinessRule businessRule1 = new MaGoldenCross(GREATER_THAN, 2);
        BusinessRule businessRule2 = new MaGoldenCross(GREATER_THAN, 5);

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