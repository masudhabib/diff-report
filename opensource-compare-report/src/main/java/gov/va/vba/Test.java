package gov.va.vba;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.bitbucket.cowwoc.diffmatchpatch.DiffMatchPatch;
import org.bitbucket.cowwoc.diffmatchpatch.DiffMatchPatch.Diff;
import org.bitbucket.cowwoc.diffmatchpatch.DiffMatchPatch.Patch;

public class Test {
	
	static String heading = "<!DOCTYPE html>\n<html>\n<head></head><body><h3>Comparison Detail</h3>";
	static String tail = "</body></html>";
	static String tableBegin = "<table><tbody>";
	static String tableEnd = "</tbody></table>";
	static String tableRow = "<tr><td><div></div><span><a href=\"report\\";
	static String tableRowEnd = "</a></span></td></tr>";
	

	public static void main(String[] args) throws IOException {
		
	HashMap<Integer, String> h = new HashMap<>();
	h.put(1, "C:\\Users\\habibm\\eclipse-workspace-photon\\opensource-compare-report\\test-original.txt");
	h.put(2, "C:\\Users\\habibm\\eclipse-workspace-photon\\opensource-compare-report\\test-original2.txt");
	BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream("report.html"));
	bufferedOutputStream.write(heading.getBytes());
	bufferedOutputStream.write(tableBegin.getBytes());
	h.entrySet().forEach( x->{
			String id = x.getKey().toString();
			String compare = x.getValue();
			String compareto = x.getValue().replace("original", "redacted");
			FileContentComparator fcc = new FileContentComparator(compare, compareto);
			FileReportGenerator frg = new FileReportGenerator(fcc.compareFile());
			frg.generatePrivateReport("report/"+id+".html");
			
			try {
				bufferedOutputStream.write(tableRow.getBytes());
				bufferedOutputStream.write((id+".html\">"+id +"-" + x.getValue().substring(x.getValue().lastIndexOf('\\') + 1) + tableRowEnd).getBytes());
			} catch (IOException e) {
				e.printStackTrace();
			}
			
	}
			);
	bufferedOutputStream.write(tableEnd.getBytes());
	bufferedOutputStream.write(tail.getBytes());
	bufferedOutputStream.flush();
	bufferedOutputStream.close();
		//FileContentComparator fcc = new FileContentComparator(
			//	"C:\\Users\\habibm\\eclipse-workspace-photon\\opensource-compare-report\\test-original",
			//	"C:\\Users\\habibm\\eclipse-workspace-photon\\opensource-compare-report\\test-redacted");
		
		//FileReportGenerator frg = new FileReportGenerator(fcc.compareFile());
		//frg.generatePrivateReport("C:\\Users\\habibm\\eclipse-workspace-photon\\opensource-compare-report\\report_private.html");
		//frg.generatePublicReport("C:\\Users\\habibm\\eclipse-workspace-photon\\opensource-compare-report\\report_public.html");
		
		
		
	}
	
	private void writeToFile(String htmlDiff, String path) throws IOException {

		BufferedOutputStream bufferedOutputStream = null;
		try {
			bufferedOutputStream = new BufferedOutputStream(new FileOutputStream("report.html"));
			bufferedOutputStream.write(htmlDiff.getBytes());
		} finally {
			if (bufferedOutputStream != null) {
				bufferedOutputStream.close();
			}
		}

	}
	

}
