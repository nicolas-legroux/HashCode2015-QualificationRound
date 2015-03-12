
public class Server {
	
	Server(int id, int taille, int capacite) {
		this.id = id;
		this.taille = taille;
		this.capacite = capacite;
		score = capacite / (double)taille;
	}
	
	int id;
	int taille;
	int capacite;
	double score;

}
