package nl.rvh.rulevalidation.enums;

public enum ComparisonOperator {

    LESS_THAN("Less than") {
        @Override
        public boolean compare(Object actualValue, Object expectedValue) {
            if (actualValue instanceof Number) {
                if (actualValue instanceof Integer)
                    return (Integer) actualValue < (Integer)  expectedValue;
                else if (actualValue instanceof Double)
                    return (Double) actualValue < (Double)  expectedValue;
                else if (actualValue instanceof Float)
                    return (Float) actualValue < (Float)  expectedValue;
                else if (actualValue instanceof Byte)
                    return (Byte) actualValue < (Byte)  expectedValue;
                else if (actualValue instanceof Short)
                    return (Short) actualValue < (Short)  expectedValue;
                else if (actualValue instanceof Long)
                    return (Long) actualValue < (Long)  expectedValue;
                else
                    throw new UnsupportedOperationException("Unknown format to compare");
            }
            throw new UnsupportedOperationException("LESS_THAN requires number object");
        }
    },
    GREATER_THAN("Greater than") {
        @Override
        public boolean compare(Object actualValue, Object expectedValue) {
            if (actualValue instanceof Number) {
                if (actualValue instanceof Integer)
                    return (Integer) actualValue > (Integer)  expectedValue;
                else if (actualValue instanceof Double)
                    return (Double) actualValue > (Double)  expectedValue;
                else if (actualValue instanceof Float)
                    return (Float) actualValue > (Float)  expectedValue;
                else if (actualValue instanceof Byte)
                    return (Byte) actualValue > (Byte)  expectedValue;
                else if (actualValue instanceof Short)
                    return (Short) actualValue > (Short)  expectedValue;
                else if (actualValue instanceof Long)
                    return (Long) actualValue > (Long)  expectedValue;
                else
                    throw new UnsupportedOperationException("Unknown format to compare");
            }
            throw new UnsupportedOperationException("LESS_THAN_OR_EQUAL requires number object");
        }
    },
    LESS_THAN_OR_EQUAL("Less than or equal") {
        @Override
        public boolean compare(Object actualValue, Object expectedValue) {
            if (actualValue instanceof Number) {
                if (actualValue instanceof Integer)
                    return (Integer) actualValue >= (Integer)  expectedValue;
                else if (actualValue instanceof Double)
                    return (Double) actualValue >= (Double)  expectedValue;
                else if (actualValue instanceof Float)
                    return (Float) actualValue >= (Float)  expectedValue;
                else if (actualValue instanceof Byte)
                    return (Byte) actualValue >= (Byte)  expectedValue;
                else if (actualValue instanceof Short)
                    return (Short) actualValue >= (Short)  expectedValue;
                else if (actualValue instanceof Long)
                    return (Long) actualValue >= (Long)  expectedValue;
                else
                    throw new UnsupportedOperationException("Unknown format to compare");
            }
            throw new UnsupportedOperationException("LESS_THAN_OR_EQUAL requires number object");
        }
    },
    GREATER_THAN_OR_EQUAL("Greater than or equal") {
        @Override
        public boolean compare(Object actualValue, Object expectedValue) {
            if (actualValue instanceof Number) {
                if (actualValue instanceof Integer)
                    return (Integer) actualValue >= (Integer)  expectedValue;
                else if (actualValue instanceof Double)
                    return (Double) actualValue >= (Double)  expectedValue;
                else if (actualValue instanceof Float)
                    return (Float) actualValue >= (Float)  expectedValue;
                else if (actualValue instanceof Byte)
                    return (Byte) actualValue >= (Byte)  expectedValue;
                else if (actualValue instanceof Short)
                    return (Short) actualValue >= (Short)  expectedValue;
                else if (actualValue instanceof Long)
                    return (Long) actualValue >= (Long)  expectedValue;
                else
                    throw new UnsupportedOperationException("Unknown format to compare");
            }
            throw new UnsupportedOperationException("GREATER_THAN_OR_EQUAL requires number object");
        }
    },
    IN_LIST("In list") {
        @Override
        public boolean compare(Object actualValue, Object expectedValue) {
            throw new UnsupportedOperationException("IN_LIST not yet implemented");
        }
    },
    CONTAINS("Contains") {
        @Override
        public boolean compare(Object actualValue, Object expectedValue) {
            if (actualValue instanceof String) {
                return ((String) actualValue).contains((String) expectedValue);
            } else {
                throw new UnsupportedOperationException("Contains comparison requires String Object");
            }
        }
    },
    STARTS_WITH("Starts with") {
        @Override
        public boolean compare(Object actualValue, Object expectedValue) {
            if (actualValue instanceof String) {
                return ((String) actualValue).startsWith((String) expectedValue);
            } else {
                throw new UnsupportedOperationException("STARTS_WITH comparison requires String Object");
            }
        }
    },
    ENDS_WITH("Ends with") {
        @Override
        public boolean compare(Object actualValue, Object expectedValue) {
            if (actualValue instanceof String) {
                return ((String) actualValue).endsWith((String) expectedValue);
            } else {
                throw new UnsupportedOperationException("ENDS_WITH comparison requires String Object");
            }
        }
    };

    private final String description;

    ComparisonOperator(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public abstract boolean compare(Object actualValue, Object expectedValue);

}
