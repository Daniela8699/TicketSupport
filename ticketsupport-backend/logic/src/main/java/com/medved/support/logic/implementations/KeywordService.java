package com.medved.support.logic.implementations;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.net.ssl.HttpsURLConnection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import com.medved.support.logic.interfaces.ISearchMadeService;
import com.medved.support.model.SearchMade;

@Service
public class KeywordService {

	static String subscription_key_var;
	static String subscription_key;
	static String endpoint_var;
	static String endpoint;
	private List<SearchMade> initialList;

	public static void Initialize() throws Exception {
		subscription_key = "9650a52ecc3041aa9562a79bdb4e87a6";
		endpoint = "https://southcentralus.api.cognitive.microsoft.com";
	}

	static String path = "/text/analytics/v2.1/languages";
	static String path2 = "/text/analytics/v2.1/keyPhrases";

	public final static long DAYS = 2592000000L;

	@Autowired
	private ISearchMadeService searchMadeService;

	public static JsonObject GetLanguage(Documents documents) throws Exception {
		String text = new Gson().toJson(documents);
		byte[] encoded_text = text.getBytes("UTF-8");

		URL url = new URL(endpoint + path);
		HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
		connection.setRequestMethod("POST");
		connection.setRequestProperty("Content-Type", "text/json");
		connection.setRequestProperty("Ocp-Apim-Subscription-Key", subscription_key);
		connection.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
		wr.write(encoded_text, 0, encoded_text.length);
		wr.flush();
		wr.close();

		StringBuilder response = new StringBuilder();
		BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		String line;
		while ((line = in.readLine()) != null) {
			response.append(line);
		}
		in.close();
		JsonObject jobj = new Gson().fromJson(response.toString(), JsonObject.class);

		return jobj;

	}

	public static JsonObject GetKeyPhrases(Documents documents) throws Exception {
		String text = new Gson().toJson(documents);
		byte[] encoded_text = text.getBytes("UTF-8");

		URL url = new URL(endpoint + path2);
		HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
		connection.setRequestMethod("POST");
		connection.setRequestProperty("Content-Type", "text/json");
		connection.setRequestProperty("Ocp-Apim-Subscription-Key", subscription_key);
		connection.setDoOutput(true);

		DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
		wr.write(encoded_text, 0, encoded_text.length);
		wr.flush();
		wr.close();

		StringBuilder response = new StringBuilder();
		BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		String line;
		while ((line = in.readLine()) != null) {
			response.append(line);
		}
		in.close();
		JsonObject jobj = new Gson().fromJson(response.toString(), JsonObject.class);

		return jobj;
	}

	private List<SearchMade> getSearchesLastMonth(int month) {
		long currentDate = System.currentTimeMillis();

		month = month - 1 ; 
		// List<SearchMade> initialList = (List<SearchMade>)
		// searchMadeService.findAll();
		testSearches();

		List<SearchMade> searchesPerMonth = new ArrayList<>();

		for (int i = 0; i < initialList.size(); i++) {
			if (initialList.get(i).getDate().getMonth() == month) {
				searchesPerMonth.add(initialList.get(i));
				
			}
		}
		return searchesPerMonth;
	}

	// Keywords con la cantidad de veces que se repite
	public Map<String, Integer> countKeywords(int month) {
		List<String> words = new ArrayList();
		Map<String, Integer> frequency = new HashMap<>();
		List<SearchMade> searchs = getSearchesLastMonth(month); // Cada search a un String
		Documents documents = new Documents();
		int count = 1;
		for (SearchMade searchMade : searchs) {
			documents.add("" + count, "en", searchMade.getSearch());
			count++;
		}

		// Lenguaje de las busquedas
		int num = 0;
		try {
			Initialize();
			JsonObject lenguajes = GetLanguage(documents);
			JsonArray jsonArray = lenguajes.getAsJsonArray("documents");

			for (Document docs : documents.documents) {
				String jsonString = jsonArray.get(num).getAsJsonObject().get("detectedLanguages").toString();
				JsonElement je = new JsonParser().parse(jsonString);
				JsonArray myArray = je.getAsJsonArray();
				for (JsonElement e : myArray) {
					JsonObject jo = e.getAsJsonObject();
					JsonPrimitive tsPrimitive = jo.getAsJsonPrimitive("iso6391Name");
					String len = tsPrimitive.getAsString();
					documents.documents.get(num).language = len;
				}
				num++;
			}

			// Keywords
			int numb = 0;
			JsonObject keywords = GetKeyPhrases(documents);
			jsonArray = lenguajes.getAsJsonArray("documents");

			JsonObject response = GetKeyPhrases(documents);
			for (Document docs : documents.documents) {
				JsonArray jsonArr = response.getAsJsonArray("documents").get(numb).getAsJsonObject().get("keyPhrases")
						.getAsJsonArray();
				Gson googleJson = new Gson();
				ArrayList jsonObjList = googleJson.fromJson(jsonArr, ArrayList.class);
				for (int i = 0; i < jsonObjList.size(); i++) {
					words.add((String) jsonObjList.get(i));
				}
				numb++;
			}

			for (String word : words) {

				String processed = word.toUpperCase();

				if (frequency.containsKey(processed)) {
					frequency.put(processed, frequency.get(processed) + 1);
				} else {
					frequency.put(processed, 1);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return frequency;
	}

	// Devuelve un hashtable con el top N de keywords de manera
	public String getTopNKeywords(int top, int month) {

		testSearches();
		Map<String, Integer> map = countKeywords(month);
		List<Entry<String, Integer>> results = new ArrayList<>(map.entrySet());
		Collections.sort(results, new EntryComparator());
		Keywords words = new Keywords();
		for (Entry<String, Integer> entry : results) {
			if (top > 0) {
				Keyword key = new Keyword(entry.getKey(), entry.getValue());
				words.keywords.add(key);
				top--;
			}

		}
		String jobj = new Gson().toJson(words, Keywords.class);

		return jobj;

	}

	public void testSearches() {
		initialList = new ArrayList<>();
		long[] enero = new long[] { 1578718800000L, 1579755600000L, 1578027600000L, 1579064400000L, 1580274000000L,
				1578718800000L, 1579755600000L, 1578027600000L, 1579064400000L, 1580274000000L, 1578718800000L,
				1579755600000L, 1578027600000L, 1579064400000L, 1580274000000L, 1578718800000L, 1579755600000L,
				1578027600000L, 1579064400000L, 1580274000000L };
		String[] palabrasEnero = new String[] {
				"Why is processing a sorted array faster than processing an unsorted array?",
				"How do I undo the most recent local commits in Git?",
				"How do I delete a Git branch locally and remotely?",
				"What is the difference between 'git pull' and 'git fetch'?", "What is the correct JSON content type?",
				"What does the “yield” keyword do?", "How do I undo 'git add' before commit?",
				"What is the “-->” operator in C++?", "How do I rename a local Git branch?",
				"How can I remove a specific item from an array?", "What and where are the stack and heap?",
				"How do I check if an element is hidden in jQuery?",
				"How can I redirect the user from one page to another using jQuery or pure JavaScript?",
				"How do JavaScript closures work?", "How do I revert a Git repository to a previous commit?",
				"Can comments be used in Git?",
				"What does “use strict” do in Git, and what is the reasoning behind it?",
				"How to check whether a string contains a substring in JavaScript?",
				"How to remove local (untracked) files from the current Git working tree",
				"How do I check out a remote Git branch?" };

		long[] febrero = new long[] { 1582779600000L, 1582261200000L, 1582088400000L, 1581570000000L, 1580965200000L,
				1582779600000L, 1582261200000L, 1582088400000L, 1581570000000L, 1580965200000L, 1582779600000L,
				1582261200000L, 1582088400000L, 1581570000000L, 1580965200000L, 1582779600000L, 1582261200000L,
				1582088400000L, 1581570000000L, 1580965200000L };
		String[] palabrasFebrero = new String[] { "Is Java “pass-by-reference” or “pass-by-value”?",
				"What is the difference between String and string in C#?",
				"How do I remove a property from a JavaScript object?",
				"Does Python have a ternary conditional operator?", "		What are metaclasses in Python?",
				"How do I check whether a file exists without exceptions?",
				"How do I return the response from an asynchronous call?",
				"How do I include a JavaScript file in another JavaScript file?",
				"What is the most efficient way to deep clone an object in JavaScript?",
				"Calling an external command from Python",
				"How do I merge two dictionaries in a single expression in Python?",
				"How to resolve merge conflicts in Git", "	For-each over an array in JavaScript",
				"How do I check whether a checkbox is checked in jQuery?",
				"How to validate an email address in JavaScript",
				"Which “href” value should I use for JavaScript links, “#” or “javascript:void(0)”?",
				"How do you get a timestamp in JavaScript?",
				"How do I check if an array includes a value in JavaScript?",
				"How do I make the first letter of a string uppercase in JavaScript?",
				"Make an existing Git branch track a remote branch?" };

		long[] marzo = new long[] { 1583125200000L, 1583989200000L, 1584162000000L, 1584507600000L, 1585026000000L,
				1583125200000L, 1583989200000L, 1584162000000L, 1584507600000L, 1585026000000L, 1583125200000L,
				1583989200000L, 1584162000000L, 1584507600000L, 1585026000000L, 1583125200000L, 1583989200000L,
				1584162000000L, 1584507600000L, 1585026000000L };
		String[] palabrasMarzo = new String[] {
				"What are the differences between a pointer variable and a reference variable in C++?",
				"How to create a memory leak in Java?", "When to use LinkedList over ArrayList in Java?",
				"Remove a file from a Git repository without deleting it from the local filesystem",
				"How do I convert a String to an int in Java?", "Get the current URL with JavaScript?",
				"How to insert an item into an array at a specific index (JavaScript)?",
				"How to get the current time in Python", "Is there an “exists” function for jQuery?",
				"How can I change an element's class with JavaScript?", "Commit only part of a file in Git",
				"Sort array of objects by string property value",
				"How can I know which radio button is selected via jQuery?",
				"Check if a given key already exists in a dictionary", "Creating multiline strings in JavaScript",
				"How to get the current branch name in Git?", "How can I convert a string to boolean in JavaScript?",
				"How do I make Git use the editor of my choice for commits?",
				"What are the correct version numbers for C#?", "How do I concatenate two lists in Python?" };

		long[] abril = new long[] { 1587704400000L, 1587790800000L, 1585717200000L, 1585890000000L, 1587013200000L,
				1587704400000L, 1587790800000L, 1585717200000L, 1585890000000L, 1587013200000L, 1587704400000L,
				1587790800000L, 1585717200000L, 1585890000000L, 1587013200000L, 1587704400000L, 1587790800000L,
				1585717200000L, 1585890000000L, 1587013200000L };
		String[] palabrasAbril = new String[] { "How do I efficiently iterate over each entry in a Java Map?",
				"How can I merge properties of two JavaScript objects dynamically?", "How do I copy a file in Python?",
				"Encode URL in Python?", "How can I refresh a page with jQuery?", "Add table row in jQuery",
				"How do I pass command line arguments to a Node.js program?", "Length of a JavaScript object",
				"Does a finally block always get executed in Java?",
				"Validate decimal numbers in JavaScript - IsNumeric()",
				"Set a default parameter value for a Python function",
				"What does cherry-picking a commit with Git mean?", "How do I get the current date in Python?",
				"How do I call one constructor from another in Java?",
				"How do you clone a Git repository into a specific folder?",
				"How do you push a tag to a remote repository using Git?", "How do I empty an array in Python?",
				"How to decide when to use Node.js?",
				"How do I get a consistent byte representation of strings in C# without manually specifying an encoding?",
				"How do I get a substring of a string in Python?", "How to print colored text in terminal in Python?" };

		long[] mayo = new long[] { 1588568400000L, 1588568400000L, 1588568400000L, 1588568400000L, 1588568400000L,
				1588568400000L, 1588568400000L, 1588568400000L, 1588568400000L, 1588568400000L, 1588568400000L,
				1588568400000L, 1588568400000L, 1588568400000L, 1588568400000L, 1588568400000L, 1588568400000L,
				1588568400000L, 1588568400000L, 1588568400000L };
		String[] palabrasMayo = new String[] { "How do I lowercase a string in Python?",
				"How do I declare and initialize an array in Java?", "How to upgrade all Python packages with pip?",
				"How does JavaScript .prototype work?", "How do I split a string on a delimiter in Bash?",
				"How does PHP 'foreach' actually work?", "What is the scope of variables in JavaScript?",
				"How do I format a Microsoft JSON date?", "How does data binding work in AngularJS?",
				"Generating random whole numbers in JavaScript in a specific range?",
				"Compare two dates with JavaScript", "Are static class variables possible in Python?",
				"What's the simplest way to print a Java array?", "Download a specific tag with Git",
				"How can I symlink a file in Linux? [closed]", "How do I get the number of elements in a list?",
				"How can I delete all Git branches which have been merged?", "Finding duplicate values in a SQL table",
				"How to retrieve the hash for the current commit in Git?",
				"How do I revert all local changes in Git managed project to previous state?" };

		for (int i = 0; i < enero.length; i++) {
			SearchMade s = new SearchMade();
			Timestamp a = new Timestamp(enero[i]);
			s.setDate(a);
			s.setSearch(palabrasEnero[i]);
			initialList.add(s);
		}

		for (int i = 0; i < febrero.length; i++) {
			SearchMade s = new SearchMade();
			Timestamp a = new Timestamp(febrero[i]);
			s.setDate(a);
			s.setSearch(palabrasFebrero[i]);
			initialList.add(s);
		}

		for (int i = 0; i < marzo.length; i++) {
			SearchMade s = new SearchMade();
			Timestamp a = new Timestamp(marzo[i]);
			s.setDate(a);
			s.setSearch(palabrasMarzo[i]);
			initialList.add(s);
		}

		for (int i = 0; i < abril.length; i++) {
			SearchMade s = new SearchMade();
			Timestamp a = new Timestamp(abril[i]);
			s.setDate(a);
			s.setSearch(palabrasAbril[i]);
			initialList.add(s);
		}

		for (int i = 0; i < mayo.length; i++) {
			SearchMade s = new SearchMade();
			Timestamp a = new Timestamp(mayo[0]);
			s.setDate(a);
			s.setSearch(palabrasMayo[i]);
			initialList.add(s);
		}

	}

}

class EntryComparator implements Comparator<Entry<String, Integer>> {

	/**
	 * Implements descending order.
	 */
	public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
		if (o1.getValue() < o2.getValue()) {
			return 1;
		} else if (o1.getValue() > o2.getValue()) {
			return -1;
		}
		return 0;
	}

}

class Document {
	public String id, language, text;

	public Document(String id, String language, String text) {
		this.id = id;
		this.language = language;
		this.text = text;
	}
}

class Documents {
	public List<Document> documents;

	public Documents() {
		this.documents = new ArrayList<Document>();
	}

	public void add(String id, String lan, String text) {
		this.documents.add(new Document(id, lan, text));
	}
}

class Keywords {
	public List<Keyword> keywords;

	public Keywords() {
		this.keywords = new ArrayList<Keyword>();
	}

	public void add(String word, int frequency) {
		this.keywords.add(new Keyword(word, frequency));
	}
}

class Keyword {
	public String word;
	public int frequency;

	public Keyword(String word, int frequency) {
		this.word = word;
		this.frequency = frequency;

	}
}
