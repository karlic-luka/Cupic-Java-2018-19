package hr.fer.zemris.java.custom.scripting.parser;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import hr.fer.zemris.java.custom.scripting.nodes.DocumentNode;
import hr.fer.zemris.java.custom.scripting.nodes.ForLoopNode;
import hr.fer.zemris.java.custom.scripting.nodes.Node;
import hr.fer.zemris.java.custom.scripting.nodes.TextNode;
import hr.fer.zemris.java.hw03.prob1.Lexer;

class SmartScriptParserTest {

	
	
	@Test
	public void testEmpty0Children() {
		SmartScriptParser parser = null;
		String docBody = "";
		parser = new SmartScriptParser(docBody);
		DocumentNode document = parser.getDocumentNode();
		assertEquals(0, document.numberOfChildren());
	}
	
	@Test
	public void test1Child() {
		SmartScriptParser parser = null;
		String docBody = "abc";
		parser = new SmartScriptParser(docBody);
		DocumentNode document = parser.getDocumentNode();
		assertEquals(1, document.numberOfChildren());
	}
	
	@Test
	public void forWithoutEnd() {
		SmartScriptParser parser = null;
		String docBody = "abc{$FOR i 10 20$}";
		//parser = new SmartScriptParser(docBody);
		assertThrows(SmartScriptParserException.class, () -> new SmartScriptParser(docBody));
	}
	
	@Test
	public void testNullInput() {
		// must throw!
		assertThrows(SmartScriptParserException.class, () -> new SmartScriptParser(null));
	}
	
	@Test
	public void forWithEnd2Children() {
		SmartScriptParser parser = null;
		String docBody = "abc{$FOR i 10 20$}{$END$}";
		parser = new SmartScriptParser(docBody);
		DocumentNode document = parser.getDocumentNode();
		assertEquals(2, document.numberOfChildren());
	}
	
	@Test
	public void emptyTag3Children() {
		SmartScriptParser parser = null;
		String docBody = "abc{$= i i 10 20$}kraj";
		parser = new SmartScriptParser(docBody);
		DocumentNode document = parser.getDocumentNode();
		assertEquals(3, document.numberOfChildren());
	}
	
	//osnovni testovi kojima se manje vise pokrije coverage
	//međutim bolje je provjeriti preko dokumenata i demo programa
}
