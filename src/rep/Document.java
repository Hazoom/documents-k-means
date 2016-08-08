package rep;

/**
 * Class represents a Document
 * @author hazoom
 *
 */
public class Document {

	private int sentenceId;
	private String topic;
	private String[] tokens;
	private WordsVector histogram;
	private WordsVector tfidf;
	private double norm;
	private boolean assignToCluster;
	
	public Document(int sentenceId, String topic, String[] tokens) {
		super();
		this.sentenceId = sentenceId;
		this.topic = topic;
		this.tokens = tokens;
		this.assignToCluster = false;
	}
	
	public int getSentenceId() {
		return sentenceId;
	}
	public void setSentenceId(int sentenceId) {
		this.sentenceId = sentenceId;
	}
	public String getTopic() {
		return topic;
	}
	public void setTopic(String topic) {
		this.topic = topic;
	}
	public String[] getTokens() {
		return tokens;
	}
	public void setTokens(String[] tokens) {
		this.tokens = tokens;
	}
	public WordsVector getHistogram() {
		return histogram;
	}
	public void setHistogram(WordsVector histogram) {
		this.histogram = histogram;
	}

	public double getNorm() {
		return norm;
	}

	public void setNorm(double norm) {
		this.norm = norm;
	}

	public WordsVector getTfidf() {
		return tfidf;
	}

	public void setTfidf(WordsVector tfidf) {
		this.tfidf = tfidf;
	}

	public boolean isAssignToCluster() {
		return assignToCluster;
	}

	public void setAssignToCluster(boolean assignToCluster) {
		this.assignToCluster = assignToCluster;
	}

}
