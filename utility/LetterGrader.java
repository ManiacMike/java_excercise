package utility;

import java.io.*;
import java.util.*;

public class LetterGrader extends ScoreHandler {

	int counter = 0;// used for student#counting in output file
	String inputFileName, outputFileName;

	FileReader fileStream;
	BufferedReader bufferedReader;
	PrintWriter textPrintStream;

	public void readScore() {

		StreamTokenizer midTermTokenizer;
		String fileName = inputFileName;// input file
		int nextToken;
		String name;
		Double finalScore;

		File inputFile = new File(fileName);

		// create a buffered stream to read from the file

		try {
			fileStream = new FileReader(inputFile);
			bufferedReader = new BufferedReader(fileStream);
		} catch (FileNotFoundException err) {
			System.out.println(err);
			System.exit(-1);// EXIT if file not found
		}
		midTermTokenizer = new StreamTokenizer(bufferedReader);
		midTermTokenizer.whitespaceChars(',', ',');
		try {
			nextToken = midTermTokenizer.nextToken();
			while (nextToken != StreamTokenizer.TT_EOF) {

				name = "";
				while (nextToken != StreamTokenizer.TT_NUMBER) {
					name += midTermTokenizer.sval + " ";
					nextToken = midTermTokenizer.nextToken();
				}
				name = name.trim();
				ArrayList<Double> scoreOfOneLine = new ArrayList<Double>();
				for (int i = 0; i < scoreCount; i++) {
					Double s = midTermTokenizer.nval;
					if (scores.size() != i) {
						scores.get(i).add(s);
					} else {
						ArrayList<Double> l = new ArrayList<Double>();
						l.add(s);
						scores.add(l);
					}
					scoreOfOneLine.add(s);
					nextToken = midTermTokenizer.nextToken();
				}
				finalScore = this.getFinalScore(scoreOfOneLine);
				Names.add(name);// store name
				FinalScore.add(finalScore);// store final score
				counter += 1;// counter for output file student #
			} // EOF

		} catch (IOException err) {
			System.out.println(err);
		}
	}

	public void outPutGrade() {

		String outfileName = outputFileName;

		try {
			textPrintStream = new PrintWriter(new FileOutputStream(outfileName, true));
			textPrintStream
					.println("Letter grade for " + counter + " students given in " + inputFileName + " file is:");

			for (Map.Entry<String, ?> entry : ScoreMap.entrySet()) {
				textPrintStream.println(entry.getKey() + "\t\t" + entry.getValue());
			}

		} catch (FileNotFoundException e) {
			System.out.println("Error opening the file " + outfileName + "\n" + e.getMessage());
			System.exit(0);
		}

	}

	public void displayGrade() {
		this.calculateGrades();
		System.out.println("Letter grade has been calculated for students listed in input file " + inputFileName
				+ " and written to output file " + outputFileName);
		System.out.println("\nHere is the class averages:");
		String head = "\t";
		String avg = "Average:";
		String min = "Minimum:";
		String max = "Maximum:";
		for (int i = 0; i < scoreCount; i++) {
			head += "\t" + testNames.get(i);
			avg += "\t" + avgScores.get(i);
			min += "\t" + minScores.get(i);
			max += "\t" + maxScores.get(i);
		}
		System.out.println(head);
		System.out.println(avg);
		System.out.println(min);
		System.out.println(max);
	}

	public void doCleanup() throws IOException {
		fileStream.close();
		bufferedReader.close();
		textPrintStream.close();

		Scanner s = new Scanner(System.in);
		System.out.println("\nPress Enter to Continue...");
		s.nextLine();
	}

}

class ScoreHandler {
	int scoreCount = 0;
	ArrayList<String> testNames = new ArrayList<String>();
	// how every score rate in the final score
	ArrayList<Double> scoreRate = new ArrayList<Double>();

	ArrayList<ArrayList<Double>> scores = new ArrayList<ArrayList<Double>>();
	ArrayList<Double> minScores = new ArrayList<Double>();
	ArrayList<Double> maxScores = new ArrayList<Double>();
	ArrayList<Double> avgScores = new ArrayList<Double>();

	Collection<String> Names = new ArrayList<String>();
	Collection<Double> FinalScore = new ArrayList<Double>();
	Collection<Character> LetterGrade = new ArrayList<Character>();
	Map<String, Character> ScoreMap = new TreeMap<String, Character>();

	public void InitScoreHandler(ArrayList<Double> _scoreRate, ArrayList<String> _testNames, int _scoreCount) {
		this.scoreRate = _scoreRate;
		this.testNames = _testNames;
		this.scoreCount = _scoreCount;
	}

	public void calcLetterGrade() {
		Character letterGrade = null;
		for (double finalScore : FinalScore) {
			if (finalScore >= 90) {
				letterGrade = 'A';
			} else if (finalScore >= 80 && finalScore < 90) {
				letterGrade = 'B';
			} else if (finalScore >= 70 && finalScore < 80) {
				letterGrade = 'C';
			} else if (finalScore >= 60 && finalScore < 70) {
				letterGrade = 'D';
			} else if (finalScore < 60) {
				letterGrade = 'F';
			}
			LetterGrade.add(letterGrade);
		}

		for (String name : Names) {
			for (Character l : LetterGrade) {
				ScoreMap.put(name, l);// putting name and letter grade into a
										// tree map
			}
		}
	}

	protected double getFinalScore(ArrayList<Double> scores) {
		double finalScore = 0;
		for (int i = 0; i < scoreCount; i++) {
			finalScore += scores.get(i) * scoreRate.get(i);
		}
		return finalScore;
	}

	public void calculateGrades() {
		for (int i = 0; i < scoreCount; i++) {
			ArrayList<Double> s = scores.get(i);
			minScores.add(Collections.min(s));
			maxScores.add(Collections.max(s));
			avgScores.add(s.stream().mapToDouble(val -> val).average().getAsDouble());
		}
	}

}
