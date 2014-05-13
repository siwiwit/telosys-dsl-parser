import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;

/**
 * 
 * First entry point for the telosys entity parser
 * 
 * @author Jonathan Goncalvez, Mathieu Herbert, Thomas Legendre
 * @date 2014-05-13
 * @version 1.0
 */
public class EntityParser {

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

	}

	/**
	 * 
	 * @param fileName
	 */
	public void parse(String fileName) {
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(fileName));
			String line = null;
			StringBuilder stringBuilder = new StringBuilder();
			String ls = System.getProperty("line.separator");

			while ((line = reader.readLine()) != null) {
				stringBuilder.append(line);
				stringBuilder.append(ls);
			}

			stringBuilder.toString();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param file
	 */
	public void parse(File file) {
		this.parse(file.getAbsolutePath());
	}

	/**
	 * 
	 * @param is
	 */
	public void parse(InputStream is) {

	}

}
