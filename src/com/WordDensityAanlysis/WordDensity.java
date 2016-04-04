package com.WordDensityAanlysis;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.TreeMap;

/*
 * This class calculates the word density of each phrases
 * Each elements of HTML page gets different weight.
 */
public class WordDensity {
	private static final int NUM_HEADERS=4;
	private static final double SCORE_THRESHOLD=2.0;
	/*
	 * weight matrix
	 * 6.0-HTML title
	 * 5.0-MetaData
	 * 4.0-Header
	 * 1.0-Href
	 * 0.2-Body
	 */
	private static final double weights[] = {6.0,5.0,4.0,1.0,0.2};
	/*
	 * Hash Map to store word density and Tree Map to store sorted values of densities of words
	 */
	private static HashMap<String,Double> wordDensity;
	public static TreeMap<Double, ArrayList<String>> sortedWordDensity;
	private static Parser parser;
	private static Ngram ngram;

	WordDensity(String url){
		wordDensity = new HashMap<String,Double>();
		sortedWordDensity = new TreeMap<Double, ArrayList<String>>(Collections.reverseOrder());
		parser = new Parser(url);
		ngram = new Ngram();
	}
	// calculates the density of words/phrases
	public TreeMap<Double, ArrayList<String>> calculateDensity(){
		ngram.loadStopWords();
		HashMap<String,Double> ngramMap;
		
		if(parser.isDocumentNull()){
			System.out.println("Cannot Read HTML Page");
			System.exit(1);
		}
		
		System.out.println("Parsing HTML page");
		System.out.println("Calculating word Densities");	
		
		ngramMap = ngram.assignNgramScore(parser.getTitle(),weights[0]);
		addWordDensity(ngramMap);
		
		ngramMap = ngram.assignNgramScore(parser.getMetaData(), weights[1]);
		addWordDensity(ngramMap);
		

		for(int i=1;i<=NUM_HEADERS;i++){
			ArrayList<String> headerList = parser.getHeader(i);
			for(String header:headerList){
				ngramMap = ngram.assignNgramScore(header, weights[2]*Math.pow(2, 1-i));
				addWordDensity(ngramMap);
			}
		}

		ngramMap = ngram.assignNgramScore(parser.getHref(), weights[3]);
		addWordDensity(ngramMap);
		
		ngramMap = ngram.assignNgramScore(parser.getBody(), weights[4]);
		addWordDensity(ngramMap);
		
		sortWordDensity();
		return sortedWordDensity;
	}
	// this function adds the densities calculated for each element of HTML page to Hash map.
	private void addWordDensity(HashMap<String, Double> ngramMap) {
		for(String k:ngramMap.keySet()){
			double curScore=0.0;
			if(wordDensity.containsKey(k))
				curScore = wordDensity.get(k);
			wordDensity.put(k, curScore+ngramMap.get(k));
		}
	}
	// Sorts the word densities based on its score.
	private void sortWordDensity(){
		for(String k : wordDensity.keySet()){
			double score = wordDensity.get(k);
			if(score>SCORE_THRESHOLD){
				ArrayList<String> scoreList;
				if(sortedWordDensity.containsKey(score))
					scoreList = sortedWordDensity.get(score);
				else
					scoreList = new ArrayList<String>();
				scoreList.add(k);
				sortedWordDensity.put(score, scoreList);
			}
		}
	}
}
