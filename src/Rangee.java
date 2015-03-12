import java.util.*;

//Répartition des serveurs :
// -2 == Indisponible
// -1 == Inoccupé
// i occupé par le serveur d'id i

public class Rangee {
	private int id;
	private int taille;
	private int group = -1;
	private SortedSet<Integer> indisponibles;
	public int[] repartitionServeurs;
	public List<Slot> slots;
	
	Rangee(int _id, int _taille){
		id = _id;	
		indisponibles = new TreeSet<Integer>();
		taille = _taille;
		repartitionServeurs = new int[taille];
		
		for(int i=0; i<taille; i++){
			//emplacement libre
			repartitionServeurs[i] = -1;
		}

		slots = new LinkedList<Slot>();
	}

	void setGroup(int _group){
		group = _group;
	}
	
	void addIndisponible(int i){
		repartitionServeurs[i] = -2;
		indisponibles.add(i);		
	}
	
	void buildSlots() {
		int current = 0;
		for (Integer i : indisponibles) {
			slots.add(new Slot(new Position(id, current), i - current));
			current = i + 1;
		}
		slots.add(new Slot(new Position(id, current), taille - current));
	}
	
	int getId(){
		return id;
	}
	
	int getGroup(){
		return group;
	}
	
	boolean addServer(Server s, int emplacement){
		//Check that it is legal 
		for(int i=0; i<s.taille; i++){
			if(repartitionServeurs[emplacement+i] != -1){
				System.out.println("Impossible de placer un serveur à l'emplacement " + (emplacement+i));
				return false;
			}
		}
		
		//Good to go
		for(int i=0; i< s.taille; i++){
			repartitionServeurs[emplacement+i] = s.id;
		}
		
		return true;
	}
	
	Set<Integer> getIndisponibles(){
		return indisponibles;
	}
}
