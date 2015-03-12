
/*
 * Calcule un coloriage des serveurs à partir d'une répartition.
 */
public class Coloriage {
	
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
		
		for (Slot slot : allocation.problem.allSlots) {
			for (Server server : slot.servers) {
				int best = 0;
				for (int i = 1 ; i < allocation.problem.nb_groups ; ++i) {
					if (scores[i] < scores[best])
						best = i;
				}
				
				allocation.allocation.get(server.id).groupe = best;
				scores[best] += server.capacite;
			}
		}
		System.out.println("naiveColoriage done");
	}

}
