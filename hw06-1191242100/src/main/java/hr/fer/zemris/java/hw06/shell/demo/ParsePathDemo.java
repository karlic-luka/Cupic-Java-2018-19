package hr.fer.zemris.java.hw06.shell.demo;

import hr.fer.zemris.java.hw06.shell.commands.UtilShellCommand;

/**
 * Demo class for testing parsePath method.
 * @author Luka
 *
 */
public class ParsePathDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

//		String string = "/home/john/info.txt /home/john/backupFolder";
//		String string2 = "\"C:/Program Files/Program1/info.txt\" C:/tmp/informacije.txt";
//		char[] chars = string2.toCharArray();
		
		String unos = "\"C:\\Users\\Luka\\Desktop\\java-copy-testovi\\test.txt\"     \"C:\\Users\\Luka\\Desktop\\java-copy-testovi\\test2.txt\"";
//		String unos = "path1 path2";
		
		String path = UtilShellCommand.parsePath(unos);
		System.out.println(path);
		System.out.println(UtilShellCommand.actualIndexIncludingEscape);
		
		String path2 = UtilShellCommand.parsePath(unos.substring(UtilShellCommand.actualIndexIncludingEscape).trim());
		System.out.println(path2);
		System.out.println(UtilShellCommand.actualIndexIncludingEscape);
		System.out.println(unos.length());
//		System.out.println(unos.replaceFirst(path, "").replaceFirst(path2, "").replaceAll("\"", "").replaceAll(" ", ""));
		System.out.println(unos.substring(0, unos.indexOf(path2, 0)).length() + UtilShellCommand.actualIndexIncludingEscape);
	}
}
