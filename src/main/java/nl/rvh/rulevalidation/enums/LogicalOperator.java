package nl.rvh.rulevalidation.enums;

public enum LogicalOperator {

    AND("and"),
    OR("or"),
    XOR("xor");

    private final String description;

    LogicalOperator(String operator) {
        this.description = operator;
    }

    public String getDescription() {
        return description;
    }
}
