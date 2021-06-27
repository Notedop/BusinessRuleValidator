package nl.rvh.rulevalidation;

import nl.rvh.rulevalidation.enums.ComparisonOperator;

import java.util.Map;

/**
 * Abstract business rule class. Extend this class to implement custom business rule.
 * The implementing class must contain a constructor having the @JsonCreator annotation
 * and JsonProperty annotation on the constructor parameters to assure proper serialization
 * and de-serialization.
 * <p>
 * Implement the Evaluate method in the derived class. You can process the expectedValue to your
 * liking and then use the comparisonOperator.compare() method to do the final evaluation
 *
 * Use the @XStreamOmitField to exclude fields which cannot be serialized. In order to instantiate non-serializable
 * fields after de-serialization you should override the readResolve() method.
 */
public abstract class BusinessRule extends Rule {

    protected final Map<String, String> ruleParameters;
    protected ComparisonOperator comparisonOperator;
    protected Object expectedValue;

    /**
     * Constructor for business rule
     * @param comparisonOperator Defines which compare operator should be used by this rule
     * @param ruleParameters Defines additional parameters which can be used by the rule
     * @param expectedValue Defines the expected value
     * @param name Defines the name of the rule, usually defaulted by the derived class.
     */
    protected BusinessRule(ComparisonOperator comparisonOperator, Map<String, String> ruleParameters, Object expectedValue, String name) {
        super(name);
        this.ruleParameters = ruleParameters;
        this.expectedValue = expectedValue;
        this.comparisonOperator = comparisonOperator;
    }

    public ComparisonOperator getComparisonOperator() {
        return comparisonOperator;
    }

    public void setComparisonOperator(ComparisonOperator comparisonOperator) {
        this.comparisonOperator = comparisonOperator;
    }

    public Object getExpectedValue() {
        return expectedValue;
    }

    public void setExpectedValue(Object expectedValue) {
        this.expectedValue = expectedValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BusinessRule)) return false;
        if (!super.equals(o)) return false;

        BusinessRule that = (BusinessRule) o;

        if (ruleParameters != null ? !ruleParameters.equals(that.ruleParameters) : that.ruleParameters != null)
            return false;
        if (comparisonOperator != that.comparisonOperator) return false;
        return expectedValue != null ? expectedValue.equals(that.expectedValue) : that.expectedValue == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (ruleParameters != null ? ruleParameters.hashCode() : 0);
        result = 31 * result + (comparisonOperator != null ? comparisonOperator.hashCode() : 0);
        result = 31 * result + (expectedValue != null ? expectedValue.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("BusinessRule{");
        sb.append("ruleParameters=").append(ruleParameters);
        sb.append(", comparisonOperator=").append(comparisonOperator);
        sb.append(", expectedValue=").append(expectedValue);
        sb.append(", name='").append(name).append('\'');
        sb.append('}');
        return sb.toString();
    }

}
