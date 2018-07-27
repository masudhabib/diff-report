package gov.va.vba;

import java.util.LinkedList;

import org.bitbucket.cowwoc.diffmatchpatch.DiffMatchPatch;
import org.bitbucket.cowwoc.diffmatchpatch.DiffMatchPatch.Diff;

public class FileContentComparator {

	private String compareThis, compareWith;

	public FileContentComparator(String compareThis, String compareWith) {
		if (compareThis != null && compareWith != null) {
			this.compareThis = compareThis;
			this.compareWith = compareWith;
		} else {
			throw new IllegalArgumentException("Paths can not be null");
		}
	}
	
	public LinkedList<Diff> compareFile() {
		String originalContent = FileContentReader.readAllLines(this.compareThis).toString();
		String comparableContent = FileContentReader.readAllLines(this.compareWith).toString();
		
		DiffMatchPatch dmp = new DiffMatchPatch();
		LinkedList<Diff> diffs = dmp.diffMain(originalContent, comparableContent, true);
		dmp.diffCleanupSemantic(diffs);
		return diffs;
	}
	
	
}
