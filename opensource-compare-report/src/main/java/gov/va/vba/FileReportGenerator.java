package gov.va.vba;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedList;

import org.bitbucket.cowwoc.diffmatchpatch.DiffMatchPatch.Diff;

public class FileReportGenerator {
	private LinkedList<Diff> diffs;

	public FileReportGenerator(LinkedList<Diff> diffs) {
		super();
		this.diffs = diffs;
	}
	
	public boolean generatePublicReport(String reportPath) {
		
		try {
			writeReportToFile(diffPrettyHtmlPublic(this.diffs), reportPath);
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean generatePrivateReport(String reportPath) {
		try {
			writeReportToFile(diffPrettyHtmlPrivate(this.diffs), reportPath);
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	private void writeReportToFile(String htmlDiff, String path) throws IOException {

		BufferedOutputStream bufferedOutputStream = null;
		try {
			bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(path));
			bufferedOutputStream.write(htmlDiff.getBytes());
		} finally {
			if (bufferedOutputStream != null) {
				bufferedOutputStream.close();
			}
		}

	}

	private String diffPrettyHtmlPrivate(LinkedList<Diff> diffs) {
		StringBuilder html = new StringBuilder();
		for (Diff aDiff : diffs) {
			String text = aDiff
					.text
					.replaceAll("&", "&amp;")
					.replaceAll("<", "&lt;")
					.replaceAll(">", "&gt;")
					.replaceAll("\n", "&para;\n<br>");
			
			switch (aDiff.operation) {
				case INSERT:
					html
						.append("<ins style=\"background:#e6ffe6;\">")
						.append(text)
						.append("</ins>\n");
					break;
				case DELETE:
					html
						.append("<del style=\"background:#ffe6e6;\">")
						.append(text)
						.append("</del>\n");
					break;
				case EQUAL:
					html
						.append("<span>")
						.append(text)
						.append("</span>\n");
					break;
				default:
					break;
			}
		}
		return html.toString();
	}

	private String diffPrettyHtmlPublic(LinkedList<Diff> diffs) {
		StringBuilder html = new StringBuilder();
		for (Diff aDiff : diffs) {
			String text = aDiff
					.text
					.replaceAll("&", "&amp;")
					.replaceAll("<", "&lt;")
					.replaceAll(">", "&gt;")
					.replaceAll("\n", "&para;\n<br>");
			
			switch (aDiff.operation) {
				case INSERT:
					html
						.append("<ins style=\"background:#fab284;\">")
						.append(text)
						.append("</ins>\n");
					break;
				case EQUAL:
					html
						.append("<span>")
						.append(text)
						.append("</span>\n");
					break;
				default:
					break;	
			}
		}
		return html.toString();
	}

}
