package com.WordDensityAanlysis;
import java.util.ArrayList;
import java.util.TreeMap;
/*
 * Output the result from the tree Set. 
 */

public class Result {
	private static final int NUM_WORDS=4;
	public void printResult(TreeMap<Double, ArrayList<String>> sortedWordDensity){
		System.out.println();
		System.out.println("-----------------List of Topics that decribe the page-------------------------");
		int count=0;
		for(Double value: sortedWordDensity.keySet()){
			ArrayList<String> list = sortedWordDensity.get(value);
			for(String phrase:list)
				System.out.print(phrase+",");
			System.out.println();
			count++;
			if(count>=NUM_WORDS)
				break;
		}
	}
}
