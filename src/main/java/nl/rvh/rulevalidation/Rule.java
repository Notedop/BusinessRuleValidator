package nl.rvh.rulevalidation;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

/**
 * Abstract rule class which is used by the BusinessRule and BusinessRuleSet class.
 * You should implement the BusinessRule class for custom rule definitions
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, property = "@class")
public abstract class Rule {

    @JacksonXmlProperty
    protected String name;
    @JacksonXmlProperty
    private ResultApplicator successResultApplicator;
    @JacksonXmlProperty
    private ResultApplicator failResultApplicator;

    @JsonCreator
    protected Rule(@JsonProperty("name") String name) {
        this.name = name;
    }

    public void applyResult(boolean success) {
        if (success && successResultApplicator != null)
            successResultApplicator.applyResult();
        if (!success && failResultApplicator != null)
            failResultApplicator.applyResult();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ResultApplicator getSuccessResultApplicator() {
        return successResultApplicator;
    }

    public void setSuccessResultApplicator(ResultApplicator successResultApplicator) {
        this.successResultApplicator = successResultApplicator;
    }

    public ResultApplicator getFailResultApplicator() {
        return failResultApplicator;
    }

    public void setFailResultApplicator(ResultApplicator failResultApplicator) {
        this.failResultApplicator = failResultApplicator;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Rule{");
        sb.append("name='").append(name).append('\'');
        sb.append(", successResultApplicator=").append(successResultApplicator);
        sb.append(", failResultApplicator=").append(failResultApplicator);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Rule)) return false;

        Rule rule = (Rule) o;

        if (name != null ? !name.equals(rule.name) : rule.name != null) return false;
        if (successResultApplicator != null ? !successResultApplicator.equals(rule.successResultApplicator) : rule.successResultApplicator != null)
            return false;
        return failResultApplicator != null ? failResultApplicator.equals(rule.failResultApplicator) : rule.failResultApplicator == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (successResultApplicator != null ? successResultApplicator.hashCode() : 0);
        result = 31 * result + (failResultApplicator != null ? failResultApplicator.hashCode() : 0);
        return result;
    }

    /**
     * Implement a evaluation logic for the input object and return a boolean value.
     * @param objectToEvaluate object which requires evaluation
     * @return returns true if evaluated successfully or false if failed.
     */
    abstract boolean evaluate(Object objectToEvaluate);
}
