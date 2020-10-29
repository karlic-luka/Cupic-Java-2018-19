package hr.fer.zemris.java.hw05.db;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class QueryLexerTest {

	@Test
    public void queryJustJmbag() {
        QueryLexer lexer = new QueryLexer("jmbag=\"1191242100\"");

        QueryToken correctData[] = {
                new QueryToken(QueryTokenType.TEXT, "jmbag"),
                new QueryToken(QueryTokenType.SYMBOL, "="),
                new QueryToken(QueryTokenType.STRING, "1191242100"),
                new QueryToken(QueryTokenType.EOF, "EOF")
        };
        checkQueryTokenStream(lexer, correctData);
    }

    @Test
    public void querySpaces() {
        QueryLexer lexer = new QueryLexer("      lastName =         \"Karlić\"");

        QueryToken correctData[] = {
                new QueryToken(QueryTokenType.TEXT, "lastName"),
                new QueryToken(QueryTokenType.SYMBOL, "="),
                new QueryToken(QueryTokenType.STRING, "Karlić"),
                new QueryToken(QueryTokenType.EOF, "EOF")
        };
        checkQueryTokenStream(lexer, correctData);
    }

    @Test
    public void queryMultipleAnd() {
        QueryLexer lexer = new QueryLexer("firstName>\"L\" and lastName LIKE \"K*ć\" and jmbag>\"1191242100\"");

        QueryToken correctData[] = {
                new QueryToken(QueryTokenType.TEXT, "firstName"),
                new QueryToken(QueryTokenType.SYMBOL, ">"),
                new QueryToken(QueryTokenType.STRING, "L"),
                new QueryToken(QueryTokenType.TEXT, "and"),
                new QueryToken(QueryTokenType.TEXT, "lastName"),
                new QueryToken(QueryTokenType.TEXT, "LIKE"),
                new QueryToken(QueryTokenType.STRING, "K*ć"),
                new QueryToken(QueryTokenType.TEXT, "and"),
                new QueryToken(QueryTokenType.TEXT, "jmbag"),
                new QueryToken(QueryTokenType.SYMBOL, ">"),
                new QueryToken(QueryTokenType.STRING, "1191242100"),


                new QueryToken(QueryTokenType.EOF, "EOF")
        };
        checkQueryTokenStream(lexer, correctData);
    }

    private void checkQueryTokenStream(QueryLexer lexer,
                                       QueryToken[] correctData) {
        for (QueryToken expected : correctData) {
            QueryToken actual = lexer.nextToken();
            assertEquals(expected.getTokenType(), actual.getTokenType());
            assertEquals(expected.getValue(), actual.getValue());
        }
    }
}
