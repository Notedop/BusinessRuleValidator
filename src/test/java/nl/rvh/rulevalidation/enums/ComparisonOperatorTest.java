package nl.rvh.rulevalidation.enums;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ComparisonOperatorTest {

    @Test
    void compareLessThan() {
        Assertions.assertAll(
                //Integer
                () -> assertTrue(ComparisonOperator.LESS_THAN.compare(1, 2)),
                () -> assertFalse(ComparisonOperator.LESS_THAN.compare(2, 1)),
                //Double
                () -> assertTrue(ComparisonOperator.LESS_THAN.compare(1D, 2D)),
                () -> assertFalse(ComparisonOperator.LESS_THAN.compare(2D, 1D)),
                //Float
                () -> assertTrue(ComparisonOperator.LESS_THAN.compare(1F, 2F)),
                () -> assertFalse(ComparisonOperator.LESS_THAN.compare(2F, 1F)),
                //Long
                () -> assertTrue(ComparisonOperator.LESS_THAN.compare(1L, 2L)),
                () -> assertFalse(ComparisonOperator.LESS_THAN.compare(2L, 1L)),
                //byte
                () -> assertTrue(ComparisonOperator.LESS_THAN.compare((byte) 1, (byte) 2)),
                () -> assertFalse(ComparisonOperator.LESS_THAN.compare((byte) 2, (byte) 1)),
                //Short
                () -> assertTrue(ComparisonOperator.LESS_THAN.compare((short) 1, (short) 2)),
                () -> assertFalse(ComparisonOperator.LESS_THAN.compare((short) 2, (short) 1)),
                //BigDecimal
                () -> assertTrue(ComparisonOperator.LESS_THAN.compare(new BigDecimal(1), new BigDecimal(2))),
                () -> assertFalse(ComparisonOperator.LESS_THAN.compare(new BigDecimal(2), new BigDecimal(1)))
        );
    }

    @Test
    void compareGreaterThan() {
        Assertions.assertAll(
                //Integer
                () -> assertFalse(ComparisonOperator.GREATER_THAN.compare(1, 2)),
                () -> assertTrue(ComparisonOperator.GREATER_THAN.compare(2, 1)),
                //Double
                () -> assertFalse(ComparisonOperator.GREATER_THAN.compare(1D, 2D)),
                () -> assertTrue(ComparisonOperator.GREATER_THAN.compare(2D, 1D)),
                //Float
                () -> assertFalse(ComparisonOperator.GREATER_THAN.compare(1F, 2F)),
                () -> assertTrue(ComparisonOperator.GREATER_THAN.compare(2F, 1F)),
                //Long
                () -> assertFalse(ComparisonOperator.GREATER_THAN.compare(1L, 2L)),
                () -> assertTrue(ComparisonOperator.GREATER_THAN.compare(2L, 1L)),
                //byte
                () -> assertFalse(ComparisonOperator.GREATER_THAN.compare((byte) 1, (byte) 2)),
                () -> assertTrue(ComparisonOperator.GREATER_THAN.compare((byte) 2, (byte) 1)),
                //Short
                () -> assertFalse(ComparisonOperator.GREATER_THAN.compare((short) 1, (short) 2)),
                () -> assertTrue(ComparisonOperator.GREATER_THAN.compare((short) 2, (short) 1)),
                //BigDecimal
                () -> assertFalse(ComparisonOperator.GREATER_THAN.compare(new BigDecimal(1), new BigDecimal(2))),
                () -> assertTrue(ComparisonOperator.GREATER_THAN.compare(new BigDecimal(2), new BigDecimal(1)))
        );
    }

    @Test
    void compareLessThanOrEqual() {
        Assertions.assertAll(
                //Integer
                () -> assertTrue(ComparisonOperator.LESS_THAN_OR_EQUAL.compare(1, 1)),
                () -> assertTrue(ComparisonOperator.LESS_THAN_OR_EQUAL.compare(1, 2)),
                () -> assertFalse(ComparisonOperator.LESS_THAN_OR_EQUAL.compare(2, 1)),
                //Double
                () -> assertTrue(ComparisonOperator.LESS_THAN_OR_EQUAL.compare(1D, 1D)),
                () -> assertTrue(ComparisonOperator.LESS_THAN_OR_EQUAL.compare(1D, 2D)),
                () -> assertFalse(ComparisonOperator.LESS_THAN_OR_EQUAL.compare(2D, 1D)),
                //Float
                () -> assertTrue(ComparisonOperator.LESS_THAN_OR_EQUAL.compare(1F, 1F)),
                () -> assertTrue(ComparisonOperator.LESS_THAN_OR_EQUAL.compare(1F, 2F)),
                () -> assertFalse(ComparisonOperator.LESS_THAN_OR_EQUAL.compare(2F, 1F)),
                //Long
                () -> assertTrue(ComparisonOperator.LESS_THAN_OR_EQUAL.compare(1L, 1L)),
                () -> assertTrue(ComparisonOperator.LESS_THAN_OR_EQUAL.compare(1L, 2L)),
                () -> assertFalse(ComparisonOperator.LESS_THAN_OR_EQUAL.compare(2L, 1L)),
                //byte
                () -> assertTrue(ComparisonOperator.LESS_THAN_OR_EQUAL.compare((byte) 1, (byte) 1)),
                () -> assertTrue(ComparisonOperator.LESS_THAN_OR_EQUAL.compare((byte) 1, (byte) 2)),
                () -> assertFalse(ComparisonOperator.LESS_THAN_OR_EQUAL.compare((byte) 2, (byte) 1)),
                //Short
                () -> assertTrue(ComparisonOperator.LESS_THAN_OR_EQUAL.compare((short) 1, (short) 1)),
                () -> assertTrue(ComparisonOperator.LESS_THAN_OR_EQUAL.compare((short) 1, (short) 2)),
                () -> assertFalse(ComparisonOperator.LESS_THAN_OR_EQUAL.compare((short) 2, (short) 1)),
                //BigDecimal
                () -> assertTrue(ComparisonOperator.LESS_THAN_OR_EQUAL.compare(new BigDecimal(1), new BigDecimal(1))),
                () -> assertTrue(ComparisonOperator.LESS_THAN_OR_EQUAL.compare(new BigDecimal(1), new BigDecimal(2))),
                () -> assertFalse(ComparisonOperator.LESS_THAN_OR_EQUAL.compare(new BigDecimal(2), new BigDecimal(1)))
        );
    }

    @Test
    void compareGreaterThanOrEqual() {
        Assertions.assertAll(
                //Integer
                () -> assertTrue(ComparisonOperator.GREATER_THAN_OR_EQUAL.compare(1, 1)),
                () -> assertFalse(ComparisonOperator.GREATER_THAN_OR_EQUAL.compare(1, 2)),
                () -> assertTrue(ComparisonOperator.GREATER_THAN_OR_EQUAL.compare(2, 1)),
                //Double
                () -> assertTrue(ComparisonOperator.GREATER_THAN_OR_EQUAL.compare(1D, 1D)),
                () -> assertFalse(ComparisonOperator.GREATER_THAN_OR_EQUAL.compare(1D, 2D)),
                () -> assertTrue(ComparisonOperator.GREATER_THAN_OR_EQUAL.compare(2D, 1D)),
                //Float
                () -> assertTrue(ComparisonOperator.GREATER_THAN_OR_EQUAL.compare(1F, 1F)),
                () -> assertFalse(ComparisonOperator.GREATER_THAN_OR_EQUAL.compare(1F, 2F)),
                () -> assertTrue(ComparisonOperator.GREATER_THAN_OR_EQUAL.compare(2F, 1F)),
                //Long
                () -> assertTrue(ComparisonOperator.GREATER_THAN_OR_EQUAL.compare(1L, 1L)),
                () -> assertFalse(ComparisonOperator.GREATER_THAN_OR_EQUAL.compare(1L, 2L)),
                () -> assertTrue(ComparisonOperator.GREATER_THAN_OR_EQUAL.compare(2L, 1L)),
                //byte
                () -> assertTrue(ComparisonOperator.GREATER_THAN_OR_EQUAL.compare((byte) 1, (byte) 1)),
                () -> assertFalse(ComparisonOperator.GREATER_THAN_OR_EQUAL.compare((byte) 1, (byte) 2)),
                () -> assertTrue(ComparisonOperator.GREATER_THAN_OR_EQUAL.compare((byte) 2, (byte) 1)),
                //Short
                () -> assertTrue(ComparisonOperator.GREATER_THAN_OR_EQUAL.compare((short) 1, (short) 1)),
                () -> assertFalse(ComparisonOperator.GREATER_THAN_OR_EQUAL.compare((short) 1, (short) 2)),
                () -> assertTrue(ComparisonOperator.GREATER_THAN_OR_EQUAL.compare((short) 2, (short) 1)),
                //BigDecimal
                () -> assertTrue(ComparisonOperator.GREATER_THAN_OR_EQUAL.compare(new BigDecimal(1), new BigDecimal(1))),
                () -> assertFalse(ComparisonOperator.GREATER_THAN_OR_EQUAL.compare(new BigDecimal(1), new BigDecimal(2))),
                () -> assertTrue(ComparisonOperator.GREATER_THAN_OR_EQUAL.compare(new BigDecimal(2), new BigDecimal(1)))
        );
    }

    @Test
    void compareEqualTo() {

        Assertions.assertAll(

                //String
                () -> assertTrue(ComparisonOperator.EQUAL_TO.compare("value", "value")),
                () -> assertFalse(ComparisonOperator.EQUAL_TO.compare("value", "blabla")),
                //Boolean strings
                () -> assertTrue(ComparisonOperator.EQUAL_TO.compare(true, "true")),
                () -> assertFalse(ComparisonOperator.EQUAL_TO.compare(true, "false")),
                () -> assertTrue(ComparisonOperator.EQUAL_TO.compare(false, "false")),
                () -> assertFalse(ComparisonOperator.EQUAL_TO.compare(false, "bogus value")),
                () -> assertFalse(ComparisonOperator.EQUAL_TO.compare(true, "bogus value")),
                () -> assertTrue(ComparisonOperator.EQUAL_TO.compare("true", true)),
                () -> assertTrue(ComparisonOperator.EQUAL_TO.compare("false", false)),
                () -> assertFalse(ComparisonOperator.EQUAL_TO.compare("bogus value", true)),
                () -> assertFalse(ComparisonOperator.EQUAL_TO.compare("bogus value", false)),
                //Boolean
                () -> assertTrue(ComparisonOperator.EQUAL_TO.compare(true, true)),
                () -> assertTrue(ComparisonOperator.EQUAL_TO.compare(false, false)),
                //Integer
                () -> assertTrue(ComparisonOperator.EQUAL_TO.compare(1, 1)),
                //Double
                () -> assertTrue(ComparisonOperator.EQUAL_TO.compare(1D, 1D)),
                //Float
                () -> assertTrue(ComparisonOperator.EQUAL_TO.compare(1F, 1F)),
                //Long
                () -> assertTrue(ComparisonOperator.EQUAL_TO.compare(1L, 1L)),
                //byte
                () -> assertTrue(ComparisonOperator.EQUAL_TO.compare((byte) 1, (byte) 1)),
                //Short
                () -> assertTrue(ComparisonOperator.EQUAL_TO.compare((short) 1, (short) 1)),
                //BigDecimal
                () -> assertTrue(ComparisonOperator.EQUAL_TO.compare(new BigDecimal(1), new BigDecimal(1)))

        );
    }
}