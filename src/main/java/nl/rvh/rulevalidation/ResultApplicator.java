package nl.rvh.rulevalidation;

import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;


public abstract class ResultApplicator {

    @XStreamOmitField
    protected Logger log = LoggerFactory.getLogger(this.getClass());
    protected Map<String, Object> parameters;

    protected ResultApplicator(Map<String, Object> parameters) {
        this.parameters = parameters;
    }

    public abstract void applyResult();

    public Map<String, Object> getParameters() {
        return parameters;
    }

    public void setParameters(Map<String, Object> parameters) {
        this.parameters = parameters;
    }

    /**
     * This method is used by deserialization process and is intented to instantiate any transient or fields annotated
     * with the @XStreamOmitField. Override this method if your implementation requires additional transient fields.
     * @return object with omitted fields instantiated
     */
    public Object readResolve() {
        log = LoggerFactory.getLogger(this.getClass());
        return this;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ResultApplicator{");
        sb.append("parameters=").append(parameters);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ResultApplicator)) return false;

        ResultApplicator that = (ResultApplicator) o;

        return parameters != null ? parameters.equals(that.parameters) : that.parameters == null;
    }

    @Override
    public int hashCode() {
        return parameters != null ? parameters.hashCode() : 0;
    }
}
