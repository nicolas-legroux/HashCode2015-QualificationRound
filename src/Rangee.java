import java.util.List;
import java.util.*;

public class Rangee {
	private int id;
	private int group = -1;	
	private List<Integer> indisponibles;
	
	Rangee(int _id){
		id = _id;	
		indisponibles = new LinkedList<Integer>();
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
	
	List<Integer> getIndisponibles(){
		return indisponibles;
	}
}
