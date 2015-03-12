
public class Server {
	
	Server(int id, int taille, int capacite) {
		this.id = id;
		this.taille = taille;
		this.capacite = capacite;
		score = capacite / Math.pow(taille, 1.5);
	}
	
	int id;
	int taille;
	int capacite;
	double score;

}
