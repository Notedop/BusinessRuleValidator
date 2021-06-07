package nl.rvh.rulevalidation;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import nl.rvh.rulevalidation.enums.LogicalOperator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

import static nl.rvh.rulevalidation.enums.LogicalOperator.AND;
import static nl.rvh.rulevalidation.enums.LogicalOperator.OR;

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, property = "@class")
public class BusinessRuleSet extends Rule {

    @JacksonXmlProperty
    private LogicalOperator operator;
    @JacksonXmlProperty(localName = "businessRules")
    private List<Rule> businessRules = new ArrayList<>();

    private Logger log = LoggerFactory.getLogger(BusinessRuleSet.class);

    @JsonCreator
    public BusinessRuleSet(@JsonProperty("name") String name, @JsonProperty("operator") LogicalOperator operator) {
        super(name);
        this.operator = operator;
    }

    @Override
    public boolean evaluate(Object objectToEvaluate) {

        log.debug("Evaluating business rule set {} with operator {} ", name, operator);
        boolean result = true;

        for (Rule rule : businessRules) {
            result = rule.evaluate(objectToEvaluate);
            log.debug("Rule {} evaluated to {}", rule.name, result);
            rule.applyResult(result);

            if (!result) {
                if (operator.equals(AND)) {
                    //for AND operator all rules must be true else break out
                    result = false;
                    break;
                }
            } else {
                //rule evaluated to true
                if (operator.equals(OR)) {
                    //for OR operator we can break out once we have one rule evaluated to true
                    break;
                }
            }
        }
        log.debug("Rule {} evaluated to {}", this.name, result);
        applyResult(result);
        return result;
    }

    public LogicalOperator getOperator() {
        return operator;
    }

    public void setOperator(LogicalOperator operator) {
        this.operator = operator;
    }

    public List<Rule> getBusinessRules() {
        return businessRules;
    }

    public void setBusinessRules(List<Rule> businessRules) {
        this.businessRules = businessRules;
    }

    public List<Rule> addRule(Rule rule) {
        businessRules.add(rule);
        return businessRules;
    }

    public List<Rule> removeRule(Rule rule) {
        businessRules.remove(rule);
        return businessRules;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BusinessRuleSet)) return false;
        if (!super.equals(o)) return false;

        BusinessRuleSet that = (BusinessRuleSet) o;

        if (operator != that.operator) return false;
        if (businessRules != null ? !businessRules.equals(that.businessRules) : that.businessRules != null)
            return false;
        return log != null ? log.equals(that.log) : that.log == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (operator != null ? operator.hashCode() : 0);
        result = 31 * result + (businessRules != null ? businessRules.hashCode() : 0);
        result = 31 * result + (log != null ? log.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("BusinessRuleSet{");
        sb.append("operator=").append(operator);
        sb.append(", businessRules=").append(businessRules);
        sb.append(", name='").append(name).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
