import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;


/*
 * Calcule une répartition des serveurs, sans attribuer les groupes.
 */
public class Repartition {
	
	static class CompareServers implements Comparator<Server> {
        public int compare(Server server1, Server server2)
        {
        	double diff = server2.score - server1.score;
        	if (diff > 0)
        		return 1;
        	else if (diff < 0)
        		return -1;

        	//double diffSize = server2.taille - server1.taille;
        	double diffSize = server1.taille - server2.taille;
        	if (diffSize > 0)
        		return 1;
        	else if (diffSize < 0)
        		return -1;
        	
        	return 0;
        }
    }
	
	static Allocation calcule(DataCenter center) {
		Allocation allocation = new Allocation(center);
		
		int[] scores = new int[center.nb_rangee];
		
		Collections.sort(center.servers, new CompareServers());
		System.out.print("Servers :\n");
		for (Server server : center.servers) {
			// Place le serveur
			System.out.print("(" + String.valueOf(server.taille) + ", " + String.valueOf(server.capacite) + ", " + String.valueOf(server.score) + ")");

			Slot slot = findSlot(center, server, scores);
			if (slot != null) {
				Position pos = slot.addServer(server);
				System.out.print(" -> (" + String.valueOf(pos.rangee) + ", " + String.valueOf(pos.emplacement) + ")");
				
				allocation.allocation.set(server.id, new Emplacement(pos));
				scores[pos.rangee] += server.capacite;
				center.rangees.get(pos.rangee).addServer(server, pos.emplacement);
			}
			
			System.out.print("\n");
		}
		
		center.print();
		
		return allocation;
	}
	
	static Slot findSlot(DataCenter center, Server server, int[] scores) {
		List<Slot> goodSlots = new LinkedList<Slot>();
		List<Slot> possibleSlots = new LinkedList<Slot>();
		for (Slot s : center.allSlots) {
			if (s.taille == server.taille)
				goodSlots.add(s);
			if (s.taille > server.taille)
				possibleSlots.add(s);
		}
		
		if (!goodSlots.isEmpty())
		{
			// Choisit le slot dont la rangee a le plus petit score.
			Slot best = goodSlots.get(0);
			for (int i = 1 ; i < goodSlots.size() ; ++i)
			{
				Slot s = goodSlots.get(i);
				if (scores[s.position.rangee] < scores[best.position.rangee])
					best = s;
			}
			
			return best;
		}
		
		if (!possibleSlots.isEmpty())
		{
			// Choisit le slot dont la rangee a le plus petit score.
			Slot best = possibleSlots.get(0);
			for (int i = 1 ; i < possibleSlots.size() ; ++i)
			{
				Slot s = possibleSlots.get(i);
				if (scores[s.position.rangee] < scores[best.position.rangee])
					best = s;
			}
			
			return best;
		}
		
		return null;
	}

}
