import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;


/*
 * Calcule un coloriage des serveurs à partir d'une répartition.
 */
public class Coloriage {
	
	static class CompareServers implements Comparator<Server> {
        public int compare(Server server1, Server server2)
        {
        	double diff = server2.capacite - server1.capacite;
        	if (diff > 0)
        		return 1;
        	else if (diff < 0)
        		return -1;
        	
        	return 0;
        }
    }
	
	static void calcule(Allocation allocation) {
		//naiveColoriage(allocation);
		scoredColoriage(allocation);
	}
	
	static void naiveColoriage(Allocation allocation) {
		int groupe = 0;
		for (Slot slot : allocation.problem.allSlots) {
			for (Server server : slot.servers) {
				allocation.allocation.get(server.id).groupe = groupe;
				groupe = (groupe + 1) % allocation.problem.nb_groups;
			}
		}
		System.out.println("naiveColoriage done");
	}
	
	static void scoredColoriage(Allocation allocation) {
		int[] scores = new int[allocation.problem.nb_groups];
		int[] total = new int[allocation.problem.nb_groups];
		int[] delta = new int[allocation.problem.nb_groups];
		int[][] contains = new int[allocation.problem.nb_groups][allocation.problem.nb_rangee];
		
		LinkedList<LinkedList<Server> > groups = new LinkedList<LinkedList<Server> >();
		for (int i = 0 ; i < allocation.problem.nb_groups ; ++i) {
			groups.add(new LinkedList<Server>());
		}

		Collections.sort(allocation.problem.servers, new CompareServers());
		for (Server server : allocation.problem.servers) {
			Emplacement e = allocation.allocation.get(server.id);
			if (e != null) {
				int best = 0;
				for (int i = 1 ; i < allocation.problem.nb_groups ; ++i) {
					if (contains[i][e.position.rangee] < contains[best][e.position.rangee])
						best = i;
					else if (contains[i][e.position.rangee] == contains[best][e.position.rangee]) {
						if (scores[i] < scores[best])
							best = i;
						/*
						int score_i = total[i] - Math.max(delta[i], server.capacite);
						int score_best = total[best] - Math.max(delta[best], server.capacite);
						if (score_i < score_best)
							best = i;
						//*/
					}
				}
			
				groups.get(best).add(server);
				e.groupe = best;
				total[best] += server.capacite;
				if (server.capacite > delta[best])
					delta[best] = server.capacite;
				scores[best] = total[best] - delta[best];
				contains[best][e.position.rangee] += 1;
			}
		}
		
		/*
		int best = 0;
		int worse = 0;
		for (int i = 0 ; i < allocation.problem.nb_groups ; ++i) {
			Collections.sort(groups.get(i), new CompareServers());
			
			if (scores[i] < scores[worse])
				worse = i;
			if (scores[i] > scores[best])
				best = i;
		}
		
		int diff = scores[best] - scores[worse];
		
		for (int j = 0 ; j < groups.get(best).size() ; ++j) {
			int score = ;
		}
		
		
		for (int group = 0 ; group < allocation.problem.nb_groups ; ++group) {
			
		}
		//*/
	}

}
