package impl;

import api.Encoder;
import rep.Document;
import rep.DocumentList;
import rep.WordsVector;

/**
 * TF-IDF encoder
 * @author hazoom
 *
 */
public class TfidfEncoder implements Encoder{

	private int vocabularySize;
	private WordsVector idf;
	
	public TfidfEncoder(int vocabularySize) {
		this.vocabularySize = vocabularySize;
	}
	
	@Override
	public void encode(DocumentList documents) {
		
		this.calcHistogram(documents);
		this.calcIDF(documents);
		
		for (Document document : documents.getDocuments()) {
			this.encode(document);
		}
	}
	
	/**
	 * Method calculates the words histogram for the given document
	 * @param document
	 */
	private void calcHistogram(Document document) {
		
		WordsVector hist = new WordsVector(this.vocabularySize);
		
		String[] tokens = document.getTokens();
		for (String token : tokens) {
			int hash = Math.abs(token.hashCode()) % this.vocabularySize;
			hist.increment(hash);
		}
		
		document.setHistogram(hist);
	}
	
	/**
	 * Method calculates words histogram for a given document list
	 * @param documentList
	 */
	private void calcHistogram(DocumentList documents) {
		for (Document document : documents.getDocuments()) {
			this.calcHistogram(document);
		}
	}
	
	/**
	 * Method calculates the inverse document frequency term of the documents in the corpus
	 * @param documents
	 */
	private void calcIDF(DocumentList documents) {
		
		this.idf = new WordsVector(this.vocabularySize);
				
		for (Document document : documents.getDocuments()) {
			for (int i = 0; i < this.vocabularySize; i++) {
				if (document.getHistogram().get(i) > 0) {
					
					//Increment the count of seen that word in whole documents
					this.idf.increment(i);
				}
			}
		}
		
		// Multiply by the number of documents
		this.idf.multiply(documents.getDocuments().size());
		
		// Log the array
		this.idf.log();
	}
	
	/**
	 * Method encodes the tf-idf score for a given document
	 * @param document
	 */
	private void encode(Document document) {
		
		WordsVector tfidf = document.getHistogram().clone();
		
		// No need of the histogram no more
		document.setHistogram(null);
		
		// Divide by vector's max (normalization)
		tfidf.divide(tfidf.max());
		
		// Dot product of tf and idf vectors
		tfidf.multiply(this.idf);
		
		// Set the tf-idf score and norm of this document
		document.setTfidf(tfidf);
		document.setNorm(tfidf.norm());
	}

}
