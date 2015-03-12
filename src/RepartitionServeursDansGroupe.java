import java.util.LinkedList;
import java.util.List;


public class RepartitionServeursDansGroupe {
	
	DataCenter dc;
	Groupe groupes[];
		
	RepartitionServeursDansGroupe(DataCenter _dc){
		dc = _dc;
		groupes = new Groupe[dc.nb_groups];
		System.out.println("Building " + dc.nb_groups + " groups.");
		for(int i=0; i<dc.nb_groups; i++){
			groupes[i] = new Groupe(i, dc.nb_rangee);
		}
	}
	
	List<Groupe> trouverPireServeurDansRangee(int idRangee){
		List<Groupe> l = new LinkedList<Groupe>();
		int pireCapacite = Integer.MAX_VALUE;
		
		for(int i=0; i<dc.nb_groups; i++){
			if(groupes[i].capaciteDansRangee(idRangee) < pireCapacite){
				l = new LinkedList<Groupe>();
				l.add(groupes[i]);
				pireCapacite = groupes[i].capaciteDansRangee(idRangee);
			}
			
			else if(groupes[i].capaciteDansRangee(idRangee) == pireCapacite){
				l.add(groupes[i]);
			}
		}		
		
		return l;
	}
	
	Groupe trouverPireServeurDansRangeeApresEgalite(int idRangee){
		List<Groupe> l = trouverPireServeurDansRangee(idRangee);
		
		Groupe g = l.get(0);
		int pireCapacite = g.capacite();
		
		for(int i=1; i<dc.nb_groups; i++){
			if(groupes[i].capacite()<pireCapacite){
				g = groupes[i];
				pireCapacite = groupes[i].capacite();
			}
		}		
		
		return g;
	}
	
	void repartirRangee(int idRangee){
		int curseur = 0;
		Rangee r = dc.rangees.get(idRangee);
		while(curseur < dc.nb_empl){
			if(r.repartitionServeurs[curseur]>=0){
				int idServeur = r.repartitionServeurs[curseur];
				
				//Find a group
				Groupe g = trouverPireServeurDansRangeeApresEgalite(idRangee);	
				g.addServer(dc.servers.get(idServeur), idRangee);
				System.out.println("Adding server " + idServeur + " to group " + g.getId());
				
				do{
					curseur++;
				} while(r.repartitionServeurs[curseur] == idServeur);
			}
			else{
				curseur++;
			}
		}
	}
	
	void repartir(){
		for(int i=0; i<dc.nb_rangee; i++){
			repartirRangee(i);
		}
	}
}
