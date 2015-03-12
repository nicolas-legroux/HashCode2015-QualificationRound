
public class Server {
	
	Server(int id, int taille, int capacite) {
		this.id = id;
		this.taille = taille;
		this.capacite = capacite;
		score = capacite / (double)taille;
		/*
		if (capacite > 80)
			score = 0;
		//*/
	}
	
	int id;
	int taille;
	int capacite;
	double score;

}
