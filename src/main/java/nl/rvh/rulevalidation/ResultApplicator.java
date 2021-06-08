package nl.rvh.rulevalidation;

import java.util.Map;


public abstract class ResultApplicator {

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
