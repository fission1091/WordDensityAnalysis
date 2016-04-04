# Overview
The basic idea behind calculating the relevance of words in the web page is firstly remove all the stop words like "are","is" etc.(Stored in stopword.txt)
secondly assign different weights to each component of HTML page i.e. Title of page is most important so assign more weight to it. Thirdly generates ngrams from 
from different words of string i.e. creating phrases generally of 2/3 words in length and also incresing the score based on the frequency of phrases.

Data Structure used:
HashMap<String,Double> -- to store the word density score of each phrases.
TreeMap<Double,List<String>> -- to store the sorted values bases on the word density scores

Input: the url of the web page

Run Command: java -jar <JarFileName>.jar "<url of web page>"
e.g. java -jar Assignment.jar "http://www.cnn.com/2013/06/10/politics/edward-snowden-profile/"

Output:
Connected to: http://www.cnn.com/2013/06/10/politics/edward-snowden-profile/
Parsing HTML page
Calculating word Densities

-----------------List of Topics that decribe the page---------------------------
nsa leaks,leaks safeguard,man nsa leaks,safeguard privacy,man nsa,leaks safeguard
privacy,nsa leaks safeguard,
replay videos,
safeguard privacy liberty,privacy liberty,
edward snowden

Run Command: java - jar <JarFileName> "http://www.amazon.com/Cuisinart-CPT-122-Compact-2-Slice-Toaster/dp/B009GQ034C/ref=sr_1_1?s=kitchen&ie=UTF8&qid=1431620315&sr=1-1&keywords=toaster"
Output:
Connected to: http://www.amazon.com/Cuisinart-CPT-122-Compact-2-Slice-Toaster/dp/B009GQ034C/ref=sr_1_1?s=kitchen&ie=UTF8&qid=1431620315&sr=1-1&keywords=toaster
Parsing HTML page
Calculating word Densities

-----------------List of Topics that decribe the page-------------------------
compact plastic,plastic toaster,compact plastic toaster,
cuisinart cpt-122,
cpt-122 2-slice,2-slice compact plastic,cuisinart cpt-122 2-slice,cpt-122 2-slic
e compact,2-slice compact,
conair cuisinart cpt-122,2-slice toaster,conair cuisinart,
   
