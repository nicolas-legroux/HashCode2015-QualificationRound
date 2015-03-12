import java.util.LinkedList;
import java.util.List;

/*
 * Espace libre contigu
 */
public class Slot {
	
	Slot(Position position, int taille) {
		this.position = position;
		current = position;
		this.taille = taille;
		servers = new LinkedList<Server>();
	}
	
	Position addServer(Server server) {
		servers.add(server);
		taille = taille - server.taille;
		
		Position result = current;
		current = new Position(position.rangee, position.emplacement + server.taille);
		return result;
	}

	Position position;
	Position current;
	int taille;
	List<Server> servers;

}
