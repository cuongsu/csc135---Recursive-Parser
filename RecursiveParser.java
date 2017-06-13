import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class RecursiveParser {
	static Queue<String> queue = new LinkedList<String>();
	
	public static void main(String [] args){
		String input = getInput();
		if(!input.endsWith("$")){
			System.out.println("Errors found. Input does not end with $.");
			System.exit(0);
		}
		for(int i = 0; i < input.length(); i++){
			queue.add(input.charAt(i)+"");
		}
		program();
		if(errorOccured == false){
			System.out.println("Legal.");
		}
	}
	
	public static String getInput(){
		String input;
		Scanner user = new Scanner(System.in);
		System.out.print("Input a stream of tokens: ");
		input = user.next();
		user.close();
		return input;
	}
	
	public static void match(String toMatch){		
		if(!queue.isEmpty()){
			if(!toMatch.equals(queue.remove())){
				error();
				System.exit(0);
			}
		}else{
			System.out.println("Errors found.");
		}
	}
	
	static boolean errorOccured = false;
	public static void error(){	
		errorOccured = true;
		System.out.println("Errors found.");		
	}
	
	public static String queuePeek()
	{
		return queue.peek();
	}
	
	public static void program(){
		statemt();
		while(statementList.contains(queuePeek())){
			statemt();
		}
	}
	
	public static void statemt(){
		if(queuePeek().equals("I")){
			ifstmt();
		}else if(queuePeek().equals("F")){
			for_();					
		}else if(queuePeek().equals("N")){
			input();
		}else if(queuePeek().equals("O)")){
			output();
		}else{
			asignmt();
		}
	}
	
	public static void asignmt(){
		ident();
		match("~");
		exprsn();
		match(";");
	}
	
	public static void ifstmt(){
		match("I");
		comprsn();
		match("@");
		while(statementList.contains(queuePeek())){
			statemt();
		}
		if(queuePeek().equals("%")){
			match("%");
		}
		while(statementList.contains(queuePeek())){
			statemt();
		}
		match("&");
	}
	
	static List<String> statementList = Arrays.asList("I","F","N","O","X","Y","Z");
	
	public static void for_(){
		match("F");
		match("(");
		asignmt();
		match(")");
		match("(");
		comprsn();
		match(")");
		match("L");
		while(statementList.contains(queuePeek())){
			statemt();
		}
		match("\\");
	}
	
	public static void input(){
		match("N");
		ident();
		while(queuePeek().equals(",")){
			match(",");
			ident();
		}
		match(";");		
	}
	
	public static void output(){
		match("O");
		ident();
		while(queuePeek().equals(",")){
			match(",");
			ident();
		}
		match(";");
	}
	
	public static void comprsn(){
		match("(");
		oprnd();
		opratr();
		oprnd();
		match(")");
	}
	
	static List<String> sumList = Arrays.asList("+","-");
	
	public static void exprsn(){
		factor();
		while(sumList.contains(queuePeek())){
			sumop();
			factor();
		}
	}
	
	static List<String> productList = Arrays.asList("*","/");
	
	public static void factor(){
		oprnd();
		while(productList.contains(queuePeek())){
			prodop();
			oprnd();
		}
	}
	
	public static void sumop(){
		if(queuePeek().equals("+")){
			match("+");
		}else{
			match("-");
		}	
	}
	
	public static void prodop(){
		if(queuePeek().equals("*")){
			match("*");
		}else{
			match("/");
		}
	}
	
	public static void oprnd(){
		if(digitList.contains(queuePeek())){
			integer();
		}else if(queuePeek().equals("(")){
			exprsn();
		}else{
			ident();
		}
	}
	
	public static void opratr(){
		if(queuePeek().equals("<")){
			match("<");
		}else if(queuePeek().equals("=")){
			match("=");
		}else if(queuePeek().equals(">")){
			match(">");
		}else{
			match("!");
		}
	}
	
	public static void ident(){
		letter();
		while(letterList.contains(queuePeek()) | digitList.contains(queuePeek())){
			char_();
		}
	}
	
	public static void char_(){
		if(letterList.contains(queuePeek())){
			letter();
		}else{
			digit();
		}
	}
	
	public static void integer(){
		do{
			digit();
		}while(digitList.contains(queuePeek()));	
	}
	
	static List<String> letterList = Arrays.asList("X","Y","Z");
	
	public static void letter(){
		if(queuePeek().equals("X")){
			match("X");
		}else if(queuePeek().equals("Y")){
			match("Y");
		}else{
			match("Z");
		}
	}
	
	static List<String> digitList = Arrays.asList("0","1","2","3","4","5","6","7");
	
	public static void digit(){
		switch(queuePeek()){
			case "0": 	match("0");
						break;
			case "1":   match("1");
						break;
			case "2":   match("2");
						break;
			case "3":   match("3");
						break;
			case "4":   match("4");
						break;
			case "5":   match("5");
						break;
			case "6":   match("6");
						break;
			default:    match("7"); 
		}
	}
}
