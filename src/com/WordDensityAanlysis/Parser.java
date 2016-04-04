package com.WordDensityAanlysis;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.ArrayList;


/*
 *  This class Parses the HTML page and returns the basic contents of page.
 */
public class Parser {
	private String url;
	private Document document=null;
	Parser(String url){
		this.url=url;
		getConnection();
	}
	// set-up the connection to the given URL.
	private void getConnection(){
		
		try {
			Connection connection = Jsoup.connect(url);
			connection.header("User-Agent", "Mozilla/45.0 Chrome/49.0.2623.95 Safari/538.67");
			document = connection.timeout(10000).get();
			System.out.println("Connected to: "+url);
			
		} catch (Exception e) {
			System.out.println("Cannot make the connection to given url" + url);
			e.printStackTrace();
		}
	}
	public boolean isDocumentNull(){
		if(document==null)
			return true;
		return false;
	}
	public String getTitle(){
		return document.title();
	}
	public String getBody(){
		return document.body().text();
	}
	public String getMetaData(){
		StringBuilder metaData = new StringBuilder();
		for(Element element: document.select("meta[name=description]")){
			metaData.append(" ");
			metaData.append(element.attr("content"));
		}
		return metaData.toString();
	}
	public String getHref(){
		StringBuilder href = new StringBuilder();
		for(Element element: document.select("a")){
			href.append(" ");
			href.append(element.text());
		}
		return href.toString();
	}
	public ArrayList<String> getHeader(int headerNumber){
		ArrayList<String> header = new ArrayList<String>();
		for(Element element:document.select("h"+headerNumber)){
			header.add(element.text());
		}
		return header;
	}
}
