package nl.rvh.rulevalidation;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import nl.rvh.rulevalidation.enums.ComparisonOperator;

import javax.print.DocFlavor;

/**
 * Abstract business rule class. Extend this class to implement custom business rule.
 * The implementing class must contain a constructor having the @JsonCreator annotation
 * and JsonProperty annotation on the constructor parameters to assure proper serialization
 * and de-serialization.
 *
 * Implement the Evaluate method in the derived class. You can process the objectToEvaluate to your
 * liking and then use the comparisonOperator.compare() method to do the final evaluation
 *
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, property = "@class", visible = true)
public abstract class BusinessRule extends Rule {

    @JacksonXmlProperty
    protected ComparisonOperator comparisonOperator;
    @JacksonXmlProperty
    @JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, property = "@class", visible = true)
    protected Object objectToEvaluate;

    @JsonCreator
    protected BusinessRule(@JsonProperty("comparisonOperator") ComparisonOperator comparisonOperator, @JsonProperty("objectToEvaluate") Object objectToEvaluate, @JsonProperty("name") String name) {
        super(name);
        this.objectToEvaluate = objectToEvaluate;
        this.comparisonOperator = comparisonOperator;
    }

    public ComparisonOperator getComparisonOperator() {
        return comparisonOperator;
    }

    public void setComparisonOperator(ComparisonOperator comparisonOperator) {
        this.comparisonOperator = comparisonOperator;
    }

    public Object getObjectToEvaluate() {
        return objectToEvaluate;
    }

    public void setObjectToEvaluate(Object objectToEvaluate) {
        this.objectToEvaluate = objectToEvaluate;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("BusinessRule{");
        sb.append("comparisonOperator=").append(comparisonOperator);
        sb.append(", objectToEvaluate=").append(objectToEvaluate);
        sb.append(", name='").append(name).append('\'');
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BusinessRule)) return false;
        if (!super.equals(o)) return false;

        BusinessRule that = (BusinessRule) o;

        if (comparisonOperator != that.comparisonOperator) return false;
        return objectToEvaluate != null ? objectToEvaluate.equals(that.objectToEvaluate) : that.objectToEvaluate == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (comparisonOperator != null ? comparisonOperator.hashCode() : 0);
        result = 31 * result + (objectToEvaluate != null ? objectToEvaluate.hashCode() : 0);
        return result;
    }
}
