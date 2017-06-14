package utility;

import java.util.*;
import java.io.IOException;

public class TestLetterGrader {
	public static void main(String[] args) throws IOException {
		do {
			if (args.length < 2) {
				System.out.println("Usage: java fileName inputFile outputFile");
				System.exit(0);
			}
			
			if (args.length >= 2) {

				LetterGrader letterGrader = new LetterGrader();
				letterGrader.inputFileName = args[0];
				letterGrader.outputFileName = args[1];

				letterGrader.InitScoreHandler(new ArrayList<Double>(Arrays.asList(0.1, 0.1, 0.1, 0.1, 0.2, 0.15, 0.25)),
						new ArrayList<String>(Arrays.asList("Q1", "Q2", "Q3", "Q4", "MidI", "MidII", "Final")), 7);

				letterGrader.readScore();
				letterGrader.calcLetterGrade();
				letterGrader.outPutGrade();
				letterGrader.displayGrade();
				letterGrader.doCleanup();
			}
		} while (true);

	}
}
