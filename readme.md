# Business Rule Validator

This is a simple business rule validator which allows implementing custom business rules.
The validator uses a composite pattern which exists out of Rule class, BusinessRule class and BusinessRuleSet class.
Both the BusinessRule and BusinessRuleSet class extend the Rule class. This allows us to build a Tree of business rules
with different level of logical operators.

## Business rule set structure

In below example both rules need to evaluate to TRUE for the business rule set to evaluate to TRUE.

* business rule set (logical operator AND)
    * business rule 1
    * business rule 2


It is also possible to nest sets within sets. There is no limit in how complex you can create the sets.

* business rule set 1 (logical operator AND)
    * business rule set 2 (logical operator OR)
      * business rule 1
      * business rule 2
    * business rule 3

## Logical Operator

Currently, we have 2 types of logical operators which can be applied to a set. 

1. AND logical operator: All immediate child business rules and business rule sets must evaluate to true.
2. OR logical operator: At least one immediate child business rule must evaluate to true. If one rule evaluates to true,
the remaining children are not evaluated anymore.

## Comparison Operator

The ComparisonOperator enum implements different ways to compare a value. This should be used to evaluate the data in 
the implementing class.

|Operator| Description | Types |
|--------|-------------|-------|
|LESS_THAN|\<|Any object extending Number.class|
|GREATER_THAN| \> |Any object extending Number.class|
|LESS_THAN_OR_EQUAL| =< |Any object extending Number.class|
|GREATER_THAN_OR_EQUAL| => |Any object extending Number.class|
|IN_LIST|to be implemented|to implemented|
|CONTAINS|asserts if string contains a value| String.class|
|STARTS_WITH|asserts if string starts with a value| String.class|
|ENDS_WITH|asserts if string ends with a value| String.class|


### Implementation example
```java



public class MaGoldenCross extends BusinessRule {

    private Logger log = LoggerFactory.getLogger(MaGoldenCross.class);

    @JsonCreator
    public MaGoldenCross(@JsonProperty("comparisonOperator") ComparisonOperator comparisonOperator, @JsonProperty("objectToEvaluate") Object expectedValue) {
        super(comparisonOperator, expectedValue, "MaGoldenCross");
    }

    @Override
    boolean evaluate(Object objectToEvaluate) {
        log.debug("Evaluating {} if {} is {} expected value {}", name, objectToEvaluate, comparisonOperator.getDescription(), this.objectToEvaluate);
        
        //do some stuff to the objectToEvaluate, or directly pass it for evaluation
        
        return comparisonOperator.compare(objectToEvaluate, getObjectToEvaluate());
    }

}
```

## Result applicator

A rule can have a ResultApplicator attached to it. This can be either a fail or success result applicator.
The result applicator will be executed after evaluation of the rule. As the result applicator is defined on the
Rule class level and both BusinessRule and BusinessRuleSet implement the Rule class, it is possible to define
applicators on any level. Just note that when using the OR operator the evaluation might come to and end early not all
result applicators may be executed.

### Implementation example

```java
    @Test
    void serializeAndDeserializeRuleSet() throws JsonProcessingException {
            XmlMapper xmlMapper = new XmlMapper();
            String xml = xmlMapper.writeValueAsString(getBusinessRuleSet());
            log.debug(xml);
            BusinessRuleSet businessRuleSet = xmlMapper.readValue(xml, BusinessRuleSet.class);
        log.debug("{}", businessRuleSet);

        Assertions.assertTrue(getBusinessRuleSet().equals(businessRuleSet));

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

```

## Serialization / Deserialization

The business rules can be serialized / deserialized into xml files using Jackson. Make sure that the implementing 
business rule class either has a default constructor or annotates the constructor with a @JsonCreator annotation as seen
in the example.

```java
    @Test
    void serializeAndDeserializeRuleSet() throws JsonProcessingException {
        XmlMapper xmlMapper = new XmlMapper();
        String xml = xmlMapper.writeValueAsString(getBusinessRuleSet());
        log.debug(xml);
        BusinessRuleSet businessRuleSet = xmlMapper.readValue(xml, BusinessRuleSet.class);
        log.debug("{}", businessRuleSet);

        Assertions.assertTrue(getBusinessRuleSet().equals(businessRuleSet));

    }
```

## Logging
This library uses the SLF4J-API for logging. The test cases use the SLF4J-SIMPLE logger. Implementing projects can 
choose their own logging framework by adding the appropriate SLF4J binding to their dependencies. 

## Future plans

1. Implement more Logical Operators
2. Implement more Comparison Operators
3. Allow configuration of Full Run for OR operator
4. Build BusinessRuleResult map which gives more details on the evaluation results.