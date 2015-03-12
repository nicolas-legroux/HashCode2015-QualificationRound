import java.util.LinkedList;
import java.util.List;

/*
 * Espace libre contigu
 */
public class Slot {
	
	Slot(Position position, int taille) {
		this.position = position;
		this.taille = taille;
		servers = new LinkedList<Server>();
	}
	
	void addServer(Server server) {
		servers.add(server);
		taille = taille - server.taille;
	}
	
	Position position;
	int taille;
	List<Server> servers;

}
