package utility;

import java.io.IOException;

public class TestLetterGrader {
	public static void main(String[] args) throws IOException   {
		do{
			if (args.length<2){
				System.out.println("Usage: java fileName inputFile outputFile");
				System.exit(0);
			};
			if (args.length>=2){

			LetterGrader letterGrader= new LetterGrader();
			letterGrader.inputFileName = args[0];
			letterGrader.outputFileName = args[1];
			
			letterGrader.readScore();
			letterGrader.calcLetterGrade();
			letterGrader.outPutGrade();
			letterGrader.displayGrade();
			letterGrader.doCleanup();
			}
		}while(true);

	}
}
