import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;


public class Allocation {
	
	DataCenter problem;
	// null pour les serveurs inutilisés.
	List<Emplacement> allocation;
	
	void save(String filename) {
		try {
		    BufferedWriter file = new BufferedWriter(new FileWriter(filename));
		    for (Emplacement e : allocation) {
		    	// Serveur non utilisé
		    	String line = "x";
		    	// Serveur utilisé
		    	if (e != null) {
			    	line = String.valueOf(e.position.rangee) + " "
			    		 + String.valueOf(e.position.emplacement) + " "
			    		 + String.valueOf(e.groupe);
		    	}
		    	file.write(line);
		    	file.newLine();
		    	file.flush();
		    }
		    file.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
