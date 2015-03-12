import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;


public class DataCenter {
	
	int nb_rangee;
	int nb_empl;
	int nb_indisp;
	List<Position> indisponibles;
	int nb_groups;
	int nb_servers;
	List<Server> servers;
	List<Rangee> rangees = new LinkedList<Rangee>();
	
	public DataCenter() {
		indisponibles = new LinkedList<Position>();
		servers = new LinkedList<Server>();
	}
	
	void load(String filename) throws IOException {
		BufferedReader data = new BufferedReader(new FileReader(filename));
		String ldata=data.readLine();
		String[] ParsedData=ldata.split(" ");
		nb_rangee=Integer.parseInt(ParsedData[0]);
		nb_empl=Integer.parseInt(ParsedData[1]);
		nb_indisp=Integer.parseInt(ParsedData[2]);
		nb_groups=Integer.parseInt(ParsedData[3]);
		nb_servers=Integer.parseInt(ParsedData[4]);
		
		int tailleTotale = 0;
		
		//Construire les rangées
		for(int i=0; i<nb_rangee; i++){
			Rangee rangee = new Rangee(i, nb_empl);
			rangees.add(rangee);
		}
		
		for (int i = 0 ; i < nb_indisp ; ++i) {
			ldata = data.readLine();
			String[] temp = ldata.split(" ");
			Position pos = new Position(Integer.parseInt(temp[0]), Integer.parseInt(temp[1]));
			indisponibles.add(pos);
			//Ajout de l'emplacement pour la rangée
			rangees.get(pos.rangee).addIndisponible(pos.emplacement);
		}
		
		for (int i = 0 ; i < nb_servers ; ++i) {
			ldata = data.readLine();
			String[] temp = ldata.split(" ");
			Server serv = new Server(i, Integer.parseInt(temp[0]), Integer.parseInt(temp[1]));
			servers.add(serv);
			tailleTotale += serv.taille;
		}
		
		System.out.println("Taille totale des serveurs : " + tailleTotale);
		
		data.close();
	}
	
	void print() {
		for (int i = 0 ; i < nb_rangee ; ++i) {
			Rangee r = rangees.get(i);
			Set<Integer> indisp = r.getIndisponibles();
			for (int j = 0 ; j < nb_empl ; ++j) {
				if (indisp.contains(j))
					System.out.print("*");
				else
					System.out.print(".");
			}
			System.out.print(" " + String.valueOf(indisp.size()));
			System.out.print("\n");
		}
	}

}
