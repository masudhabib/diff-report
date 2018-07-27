package gov.va.vba;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class FileContentReader {

	public static String readFile(String filePath) {
		return readAllLines(filePath).toString();

	}

	public static StringBuilder readAllLines(String path) {
		StringBuilder sb = new StringBuilder();
		BufferedReader bufferedReader = null;

		try { 							// begin try block
			bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(new File(path)), StandardCharsets.UTF_8));
			while (bufferedReader.ready()) {
				sb.append(bufferedReader.readLine()).append("\n");
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally { 					// close the buffered reader if not null
			if (bufferedReader != null) {
				try {
					bufferedReader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} 								// end try block

		return sb;
	}

}
