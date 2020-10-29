package hr.fer.zemris.java.hw05.db;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ConditionalExpressionTest {

    ConditionalExpression expression;
    ConditionalExpression expression2;
    StudentRecord record;

    @BeforeEach
    public void setUp() throws Exception {

        expression = new ConditionalExpression(
                FieldValueGetters.LAST_NAME,
                "K*ić",
                ComparisonOperators.LIKE
        );
        
        expression2 = new ConditionalExpression(
                FieldValueGetters.LAST_NAME,
                "K*ic",
                ComparisonOperators.LIKE
        );

        record = new StudentRecord("1191242100", "Karlić", "Luka", "5");
    }

    @Test
    public void testSatisified() {
        assertTrue(expression.getComparisonOperator().satisfied(
                expression.getFieldGetter().get(record),
                expression.getStringLiteral()
        ));
    }
    
    @Test
    public void testNotSatisified() {
        assertFalse(expression2.getComparisonOperator().satisfied(
                expression2.getFieldGetter().get(record),
                expression2.getStringLiteral()
        ));
    }
}
