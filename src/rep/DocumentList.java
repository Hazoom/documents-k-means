package rep;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DocumentList {

	private List<Document> documents;
	
	/**
	 * Constructor reads input file and creates the list of documents
	 * @param filename
	 */
	public DocumentList(String filename) {
		
		this.documents = new ArrayList<Document>();
		
		BufferedReader br = null;
		
		try {
			br = new BufferedReader(new FileReader(filename));
			String line = null;
			int count = 0;
			Integer sentenceId = null;
			String[] words;
			List<String> topics = null;
			List<String> tokens;
			while ((line = br.readLine()) != null) {
				if (!line.isEmpty()) {
					if (count % 2 == 0) { // header
						words = line.split("\t");
						for (String word : words) {
							word = word.trim();
						}
						sentenceId = Integer.valueOf(words[1]);
						topics = new ArrayList<String>();
						for (int i = 2; i < words.length; i++) {
							if (i == words.length - 1) { // Remove last character
								words[i] = words[i].substring(0, words[i].length() - 1);
							}
							topics.add(words[i]);
						}
					} else { // Body
						words = line.split(" ");
						tokens = new ArrayList<String>();
						for (String word : words) {
							tokens.add(word);
						}
						
						String[] tokensArr = new String[tokens.size()];
						for (int i = 0; i < tokens.size(); i++) {
							tokensArr[i] = tokens.get(i);
						}
						
						Document document = new Document(sentenceId, topics.get(0), tokensArr);
						this.documents.add(document);
					}
					count++;
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public List<Document> getDocuments() {
		return documents;
	}

	public void setDocuments(List<Document> documents) {
		this.documents = documents;
	}
}
