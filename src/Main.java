import api.Encoder;
import impl.CosineDistance;
import impl.KMeansImpl;
import impl.TfidfEncoder;
import rep.Cluster;
import rep.ClusterList;
import rep.Distance;
import rep.DocumentList;

/**
 * Main class to run k-means algorithm
 * @author hazoom
 *
 */
public class Main {

	public static void main(String[] args) {	
		DocumentList documents = new DocumentList(args[0]);
		
		System.out.println("Finish preprocessing...");
		
		Encoder encoder = new TfidfEncoder(30000);
		encoder.encode(documents);

		System.out.println("Finish encoding...");
		
		Distance distancce = new CosineDistance();
		
		KMeansImpl kmeans = new KMeansImpl(distancce, 8, 10);
		ClusterList clusters = kmeans.cluster(documents);
		
		System.out.println("Finish K-means algorithm...");
		
		int i = 1;
		for (Cluster cluster : clusters.getClusters()) {
			System.out.println("Cluster no. " + i + " has " + cluster.getDocuments().size() + " documents.");
			i++;
		}
	}

}
