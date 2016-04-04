package com.WordDensityAanlysis;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;


/*
 * This class calculates ngram of for the given strings
 */
public class Ngram {
	// list of stop words loaded from stopword.txt
	private HashSet<String> stopWords;
	// maximum length of ngrams
	private static int N_GRAMS=3;
	// value used to avoid short length strings
	private static int THRESHOLD =3;
	Ngram(){
		stopWords = new HashSet<String>();
	}
	// this method generates ngram and assigns the scores to the Ngrams 
	public HashMap<String,Double>assignNgramScore(String string,double points){
		HashMap<String,Double> scoredElements = new HashMap<String,Double>();
		String[] allWords = string.toLowerCase().split("[^a-z-0-9']");
		ArrayList<String> meaningfulWords = new ArrayList<String>();
		for(String word:allWords){
			if(!stopWords.contains(word))
				meaningfulWords.add(word);
		}
		for(int i=2;i<=N_GRAMS;i++){
			ArrayList<String> ngramList = getNgrams(meaningfulWords,i);
			for(String ngram:ngramList){
				if(ngram.length()>THRESHOLD){
					double score = 0.0;
					if(scoredElements.containsKey(ngram))
						score = scoredElements.get(ngram);
					scoredElements.put(ngram, score+points);
				}
			}

		}

		return scoredElements;
	}
	// generates ngrams of given length
	private ArrayList<String> getNgrams(ArrayList<String> wordList,int length){
		ArrayList<String> ngramList = new ArrayList<String>();
		for(int i=0;i<wordList.size()-length;i++){
			StringBuilder ngram = new StringBuilder();
			for(int k=0;k<length;k++){
				ngram.append(wordList.get(i+k)+" ");
			}
			ngramList.add(ngram.toString().trim().replaceAll("( )+", " "));
		}
		return ngramList;
	}
	// load stop words from text file
	public void loadStopWords(){
		try {
			InputStream is = getClass().getResourceAsStream("stopwords.txt");
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			String word;
			while((word=br.readLine())!=null){
					stopWords.add(word);
			}
			br.close();
		}catch (FileNotFoundException e){
			System.out.println("Stop Words file not found"+e.toString());
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("IO exception"+e.toString());
			e.printStackTrace();
		}
	}
}
