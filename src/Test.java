import java.io.IOException;


public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		DataCenter center = new DataCenter();
		try {
			center.load("data/dc.in");
			center.print();
			Allocation alloc = Repartition.calcule(center);
			alloc.save("results/result.out");
			RepartitionServeursDansGroupe rep = new RepartitionServeursDansGroupe(center);
			rep.repartir();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// TODO Auto-generated method stub
		System.out.println("Working");
	}

}
