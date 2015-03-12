import java.util.LinkedList;
import java.util.List;

public class Groupe {
	int id;
	int nbRangees;
	List<Server> serveurs;
	
	int calculCapacite[];
	
	Groupe(int _id, int _nbRangees){
		id = _id;
		calculCapacite = new int[_nbRangees];
		serveurs = new LinkedList<Server>();
		nbRangees = _nbRangees;
	}
	
	void addServer(Server s, int rangee){
		calculCapacite[rangee] += s.capacite;
		serveurs.add(s);
	}
	
	int capaciteDansRangee(int rangee){
		return calculCapacite[rangee];
	}
	
	int capaciteDansPlusGrosseRangee(){
		int max = 0;
		for (int i=0; i<nbRangees; i++){
			if(calculCapacite[i]>max){
				max = calculCapacite[i];
			}
		}		
		return max;
	}
	
	int capacite(){
		int total = 0;
		for(int i=0; i<nbRangees; i++){
			total += calculCapacite[i];			
		}
		return total-capaciteDansPlusGrosseRangee();
	}
	
	public int getId(){
		return id;
	}
}
