package com.WordDensityAanlysis;

/*
 * This the entry point of class.
 */
public class Main {

	public static void main(String[] args) {
		if(args.length!=1){
			System.out.println("Invalid Arguments");
			return;
		}	
		String url = args[0];
		WordDensity wordDensity = new WordDensity(url);
		Result r = new Result();
		r.printResult(wordDensity.calculateDensity());
		

	}

}
