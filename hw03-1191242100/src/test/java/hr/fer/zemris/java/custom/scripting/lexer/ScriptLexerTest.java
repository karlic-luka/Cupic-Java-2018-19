/**
 * 
 */
package hr.fer.zemris.java.custom.scripting.lexer;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import hr.fer.zemris.java.custom.scripting.elems.ElementConstantInteger;
import hr.fer.zemris.java.hw03.prob1.LexerException;

public class ScriptLexerTest {

	@Test
	public void testNotNull() {
		ScriptLexer ScriptLexer = new ScriptLexer("");
		
		assertNotNull(ScriptLexer.nextToken(), "ScriptToken was expected but null was returned.");
	}

	@Test
	public void testNullInput() {
		// must throw!
		assertThrows(NullPointerException.class, () -> new ScriptLexer(null));
	}

	@Test
	public void testEmpty() {
		ScriptLexer ScriptLexer = new ScriptLexer("");
		
		assertEquals(ScriptTokenType.EOF, ScriptLexer.nextToken().getType(), "Empty input must generate only EOF ScriptToken.");
	}

	@Test
	public void testGetReturnsLastNext() {
		// Calling getToken once or several times after calling nextToken must return each time what nextToken returned...
		ScriptLexer lexer = new ScriptLexer("");
		
		ScriptToken token = lexer.nextToken();
		assertEquals(token, lexer.getToken(), "getToken returned different ScriptToken than nextToken.");
		assertEquals(token, lexer.getToken(), "getToken returned different ScriptToken than nextToken.");
	}

	@Test
	public void testAfterEOF() {
		ScriptLexer lexer = new ScriptLexer("");

		// will obtain EOF
		lexer.nextToken();
		// will throw!
		assertThrows(LexerException.class, () -> lexer.nextToken());
	}
	
	@Test
	public void testTextExtraction() {
		// Lets check for several words...
		ScriptLexer lexer = new ScriptLexer("  Štefanija\r\n\t Automobil   ");
		lexer.nextToken();
		assertEquals(lexer.getToken().getValue().asText(),"  Štefanija\r\n\t Automobil   ");
	}

	@Test
	public void testTagExtraction() {
		// Lets check for several words...
		ScriptLexer lexer = new ScriptLexer("{$= i 10 -2.3  @sin  $}");
		lexer.nextToken();
		assertEquals(lexer.getToken().getType(), ScriptTokenType.TAG);
		assertEquals(lexer.getToken().getValue().asText(),"open");
		
		lexer.nextToken();
		assertEquals(lexer.getToken().getType(), ScriptTokenType.SYMBOL);
		assertEquals(lexer.getToken().getValue().asText(),"=");
		
		lexer.nextToken();
		assertEquals(lexer.getToken().getType(), ScriptTokenType.VARIABLE);
		assertEquals(lexer.getToken().getValue().asText(),"i");
		
		lexer.nextToken();
		assertEquals(lexer.getToken().getType(), ScriptTokenType.INT);
		assertEquals(Integer.parseInt(lexer.getToken().getValue().asText()), Integer.valueOf(10));
		
		lexer.nextToken();
		assertEquals(lexer.getToken().getType(), ScriptTokenType.DOUBLE);
		assertEquals(Double.parseDouble(lexer.getToken().getValue().asText()), Double.valueOf(-2.3));
		
		lexer.nextToken();
		assertEquals(lexer.getToken().getType(), ScriptTokenType.FUNCTION);
		assertEquals(lexer.getToken().getValue().asText(),"@sin");
	
	}	
}
