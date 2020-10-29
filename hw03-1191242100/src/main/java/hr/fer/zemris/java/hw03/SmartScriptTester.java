/**
 * 
 */
package hr.fer.zemris.java.hw03;

import hr.fer.zemris.java.custom.scripting.nodes.DocumentNode;
import hr.fer.zemris.java.custom.scripting.nodes.EchoNode;
import hr.fer.zemris.java.custom.scripting.nodes.ForLoopNode;
import hr.fer.zemris.java.custom.scripting.nodes.Node;
import hr.fer.zemris.java.custom.scripting.nodes.TextNode;
import hr.fer.zemris.java.custom.scripting.parser.SmartScriptParser;
import hr.fer.zemris.java.custom.scripting.parser.SmartScriptParserException;
import java.nio.file.Files;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;


/**
 * Class for testing SmartScript
 * @author Luka
 *
 */
public class SmartScriptTester {

	/**
	 * @param args single command line argument used as path to document to be parsed
	 */
	public static void main(String[] args) {
		if (args.length != 1) {
			System.err.println("Invalid number of arugments");
		}

		String filepath = args[0];
		String docBody = null;
		try {
			docBody = new String(Files.readAllBytes(Paths.get(filepath)),
					StandardCharsets.UTF_8);
		} catch (IOException e) {
			e.printStackTrace();
		}

		SmartScriptParser parser = null;
		try {
			parser = new SmartScriptParser(docBody);
		} catch (SmartScriptParserException e) {
			System.out.println("Unable to parse document! \n" + e.getMessage());
			System.exit(-1);
		} catch (Exception e) {
			System.out.println("If this line ever executes, you have failed this class!");
			System.exit(-1);
		}
		//System.out.println("Uspio");
		DocumentNode document = parser.getDocumentNode();
		//System.out.println(document.numberOfChildren());
		String originalBodyDocument = createOriginalDocumentBody(document);
		SmartScriptParser parser2 = new SmartScriptParser(originalBodyDocument);
		DocumentNode document2 = parser2.getDocumentNode();
		System.out.println(originalBodyDocument);
		System.out.println(createOriginalDocumentBody(document2));
	}
	public static String createOriginalDocumentBody(Node document) {
		
		if(document == null) {
			throw new IllegalArgumentException("Your node does not exist.");
		}
		StringBuilder originalDocumentBuilder = new StringBuilder();
		if (document instanceof TextNode) {
			originalDocumentBuilder.append(((TextNode)document).getText()); //tamo nisam nadjacao nista
		} else {
			originalDocumentBuilder.append(document.toString());
		}
		int numberOfChildren = document.numberOfChildren();
		if(numberOfChildren > 0) {
			for(int i = 0; i < numberOfChildren; i++) {
				originalDocumentBuilder.append(createOriginalDocumentBody(document.getChild(i)));
			}
		}
		//for-tagu fali end - medjutim, on se mora dodati poslije dodavanja sve djece
		if(document instanceof ForLoopNode) {
			originalDocumentBuilder.append("{$END$}");
		}
		return originalDocumentBuilder.toString();
	}
}
