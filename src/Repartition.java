import java.util.Collections;
import java.util.Comparator;


/*
 * Calcule une répartition des serveurs, sans attribuer les groupes.
 */
public class Repartition {
	
	class CompareServers implements Comparator<Server> {
        public int compare(Server server1, Server server2)
        {
        	double diff = server1.score - server2.score;
        	if (diff > 0)
        		return 1;
        	else if (diff < 0)
        		return -1;
        	return 0;
        }
    }
	
	Allocation calcule(DataCenter center) {
		Allocation allocation = new Allocation();
		
		Collections.sort(center.servers, new CompareServers());
		for (Server s : center.servers) {
			// Place le serveur
		}
		
		return allocation;
	}

}
