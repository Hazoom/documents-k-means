package rep;

import java.util.ArrayList;
import java.util.List;

/**
 * Class represents a list of clusters
 * @author hazoom
 *
 */
public class ClusterList {

	private List<Cluster> clusters;
	
	/**
	 * Constructor gets the initial k
	 * @param k
	 */
	public ClusterList(int k) {
		this.clusters = new ArrayList<Cluster>(k);
	}
	
	/**
	 * Adds a new cluster to the list of clusters
	 * @param cluster
	 */
	public void add(Cluster cluster) {
		this.clusters.add(cluster);
	}
	
	/**
	 * Method updates centroids of all clusters in the list
	 */
	public void updateControids() {
		for (Cluster cluster : this.clusters) {
			cluster.updateCentroid();
		}
	}

	/**
	 * Method finds the nearest cluster to the given document within al clusters
	 * @param distance
	 * @param document
	 * @return
	 */
	public Cluster findNearestCluster(Distance distance, Document document) {
		
		double min_distance = Double.MAX_VALUE;
		Cluster nearestCluster = null;
		
		for (Cluster cluster : this.clusters) {
			double dDistance = distance.calcDistance(document, cluster);
			if (dDistance < min_distance) {
				min_distance = dDistance;
				nearestCluster = cluster;
			}
		}
		
		return nearestCluster;
	}
	
	/**
	 * Clear all documents in all clusters
	 */
	public void clearClusters() {
		for (Cluster cluster : this.clusters) {
			cluster.getDocuments().clear();
		}
	}

	public List<Cluster> getClusters() {
		return clusters;
	}
}
