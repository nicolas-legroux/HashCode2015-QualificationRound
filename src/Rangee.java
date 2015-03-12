import java.util.*;

public class Rangee {
	private int id;
	private int group = -1;	
	private Set<Integer> indisponibles;
	
	Rangee(int _id){
		id = _id;	
		indisponibles = new HashSet<Integer>();
	}
	
	void setGroup(int _group){
		group = _group;
	}
	
	void addIndisponible(int i){
		indisponibles.add(i);		
	}
	
	int getId(){
		return id;
	}
	
	int getGroup(){
		return group;
	}
	
	Set<Integer> getIndisponibles(){
		return indisponibles;
	}
}
