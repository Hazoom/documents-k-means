package impl;

import java.util.Random;

import rep.Cluster;
import rep.ClusterList;
import rep.Distance;
import rep.Document;
import rep.DocumentList;

public class KMeansImpl {

	private int k;
	private int iterations;
	private Distance distance;
	
	public KMeansImpl(Distance distance, int k, int iterations) {
		this.iterations = iterations;
		this.k = k;
		this.distance = distance;
	}
	
	public ClusterList cluster(DocumentList documents) {
		ClusterList clusters = new ClusterList(this.k);
		
		// Initialize a k clusters with random k samples
		Random random = new Random();
		for (int i = 0; i < k; i++) {
			int rand = random.nextInt(documents.getDocuments().size());
			Cluster cluster = new Cluster(documents.getDocuments().get(rand));
			clusters.add(cluster);
		}
		
		// Run k means algorithm for number of iterations
		for (int i = 0; i < this.iterations; i++) {
			for (Document document : documents.getDocuments()) {
				if (!document.isAssignToCluster()) {
					Cluster nearestCluster = clusters.findNearestCluster(this.distance, document);
					nearestCluster.add(document);
				}
			}
			
			clusters.updateControids();
			
			// Not the last iteration
			if (i < this.iterations - 1) {
				
				// Clear clusters for next iteration
				clusters.clearClusters();
				
				// Deassign the documents to clusters
				for (Document document : documents.getDocuments()) {
					document.setAssignToCluster(false);
				}
			}
		}
		
		return clusters;
	}
}
