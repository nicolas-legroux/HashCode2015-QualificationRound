import java.io.IOException;


public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		DataCenter input = new DataCenter();
		try {
			input.load("data/dc.in");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// TODO Auto-generated method stub
		System.out.println("Working");
	}

}
