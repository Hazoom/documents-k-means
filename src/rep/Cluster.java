package rep;

import java.util.ArrayList;
import java.util.List;

/**
 * Class represents a cluster of documents
 * @author hazoom
 *
 */
public class Cluster {

	private List<Document> documents;
	private WordsVector centroid;
	private double controidNorm;
	
	/**
	 * Constructor initialize a new cluster with one document
	 * @param document
	 */
	public Cluster(Document document) {
		this.centroid = document.getTfidf().clone();
		this.controidNorm = document.getNorm();
		
		this.documents = new ArrayList<Document>();
		this.documents.add(document);
	}
	
	/**
	 * Method add a new document to the cluster
	 * @param document
	 */
	public void add(Document document) {
		this.documents.add(document);
		document.setAssignToCluster(true);
	}
	
	/**
	 * Method updates the centroid of the cluster and updates the norm of the cluster
	 */
	public void updateCentroid() {
		
		this.centroid = null;
		
		for (Document document : this.documents) {
			if (centroid == null) { // First time
				centroid = document.getTfidf().clone();
			} else { // Not first time
				this.centroid.add(document.getTfidf());
			}
		}
		
		this.centroid.divide(this.documents.size());
		this.controidNorm = this.centroid.norm();
	}

	public List<Document> getDocuments() {
		return documents;
	}

	public void setDocuments(List<Document> documents) {
		this.documents = documents;
	}

	public WordsVector getCentroid() {
		return centroid;
	}

	public void setCentroid(WordsVector centroid) {
		this.centroid = centroid;
	}

	public double getControidNorm() {
		return controidNorm;
	}

	public void setControidNorm(double controidNorm) {
		this.controidNorm = controidNorm;
	}
}
