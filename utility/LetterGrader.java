package utility;
import java.io.*;
import java.util.*;
import java.util.stream.Collectors;


public class LetterGrader extends ScoreHandler{

	int counter=0;//used for student#counting in output file
	String inputFileName, outputFileName;
	
	FileReader fileStream;
	BufferedReader bufferedReader;
	PrintWriter textPrintStream;

	public void readScore() {
		
		StreamTokenizer midTermTokenizer;
		String fileName =inputFileName;//input file
		int nextToken;
		String name;
		double q1, q2, q3, q4, midTerm1,midTerm2,finalTerm,finalScore;
		
		File inputFile=new File(fileName);

		//create a buffered stream to read from the file

		try{
			fileStream=new FileReader(inputFile);
			bufferedReader =new BufferedReader(fileStream);
		}catch (FileNotFoundException err){
			System.out.println(err);
			System.exit(-1);//EXIT if file not found
		}
		midTermTokenizer=new StreamTokenizer(bufferedReader);
		midTermTokenizer.whitespaceChars(',',',');
		try{
			nextToken = midTermTokenizer.nextToken();
			while(nextToken!= StreamTokenizer.TT_EOF){
				
				name = "";
				while(nextToken != StreamTokenizer.TT_NUMBER){
					name+=midTermTokenizer.sval + " ";
					nextToken = midTermTokenizer.nextToken();
				}
				name = name.trim();//make sure the name does not have extra space on both sides 

				q1=midTermTokenizer.nval;
				nextToken = midTermTokenizer.nextToken();
				q2=midTermTokenizer.nval;
				nextToken = midTermTokenizer.nextToken();
				q3=midTermTokenizer.nval;
				nextToken = midTermTokenizer.nextToken();
				q4=midTermTokenizer.nval;
				nextToken = midTermTokenizer.nextToken();
				midTerm1=midTermTokenizer.nval;
				nextToken = midTermTokenizer.nextToken();
				midTerm2=midTermTokenizer.nval;
				nextToken = midTermTokenizer.nextToken();
				finalTerm=midTermTokenizer.nval;
				//get the next token
				nextToken = midTermTokenizer.nextToken();//last null
				
				finalScore=(q1*0.1+q2*0.1+q3*0.1+q4*0.1+midTerm1*0.2+midTerm2*0.15+finalTerm*0.25);
				Names.add(name);//store name
				FinalScore.add(finalScore);//store final score
				
				scores[0].add(q1);
				Q2.add(q2);
				Q3.add(q3);
				Q4.add(q4);
				MidI.add(midTerm1);
				MidII.add(midTerm2);
				Final.add(finalTerm);//store student performance for each exam, for the later MAX, MIN, AVG calculation
				
				counter +=1;//counter for output file student #
			}//EOF

		}catch(IOException err){
			//this code executes if there is an error in gettin the next token from the file
			System.out.println(err);
		}	
	}
	
	
	public void outPutGrade() {
				
		String outfileName =outputFileName;
		
		try {
			textPrintStream = new PrintWriter(new FileOutputStream(outfileName,true));
			textPrintStream.println("Letter grade for "+counter+" students given in "+inputFileName+" file is:");
			
			for(Map.Entry<String, ?>entry:ScoreMap.entrySet()){
				textPrintStream.println(entry.getKey()+"\t\t"+entry.getValue()); //sorted the tree map according to key
			}
			
		} catch (FileNotFoundException e) {
			System.out.println("Error opening the file " + outfileName + "\n"
															+ e.getMessage());
			System.exit(0);
		}

	}
	
	public void displayGrade() {
		this.calculateGrades();
		System.out.println("Letter grade has been calculated for students listed in input file "
				+inputFileName+" and written to output file "+outputFileName );
		System.out.println("\nHere is the class averages:");
		System.out.println("\t\tQ1\tQ2\tQ3\tQ4\tMidI\tMidII\tFinal");
		System.out.println("Average:\t"+avg1+"\t"+avg2+"\t"+avg3+"\t"+avg4+"\t"+avg5+"\t"+avg6+"\t"+avg7 );
		
		System.out.println("Minimum:\t"+min1+"\t"+min2+"\t"+min3+"\t"+min4+"\t"+min5+"\t"+min6+"\t"+min7);
		System.out.println("Maximum:\t"+max1+"\t"+max2+"\t"+max3+"\t"+max4+"\t"+max5+"\t"+max6+"\t"+max7);
		// the labor-intensive display process for on screen value dispaly
			
	}
	
	public void doCleanup() throws IOException  {
		fileStream.close();
		bufferedReader.close();
		textPrintStream.close();
	
		Scanner s= new Scanner(System.in);
		System.out.println("\nPress Enter to Continue...");
		s.nextLine();
	}

}

class ScoreHandler{
	Collection<String> TestNames = new ArrayList<String>();
	Collection<Collection<Double>> scores = new ArrayList<Collection<Double>>();
	Collection<Double> minScores = new ArrayList<Double>();
	Collection<Double> maxScores = new ArrayList<Double>();
	Collection<Double> avgScores = new ArrayList<Double>();
//	
//	Collection<Double> Q1=new ArrayList<Double>();
//	Collection<Double> Q2=new ArrayList<Double>();
//	Collection<Double> Q3=new ArrayList<Double>();
//	Collection<Double> Q4=new ArrayList<Double>();
//	Collection<Double> MidI=new ArrayList<Double>();
//	Collection<Double> MidII=new ArrayList<Double>();
//	Collection<Double> Final=new ArrayList<Double>();

	Collection<String> Names=new ArrayList<String>();
	Collection<Double> FinalScore=new ArrayList<Double>();
	Collection<Character> LetterGrade=new ArrayList<Character>();
	Map<String, Character> ScoreMap=new TreeMap<String, Character>();
//
//	 
//	double min1,min2,min3,min4,min5,min6,min7;
//	double max1,max2,max3,max4,max5,max6,max7;
//	double avg1,avg2,avg3,avg4,avg5,avg6,avg7;
	
	public ScoreHandler(){
		TestNames.add("Q1");
		TestNames.add("Q2");
		TestNames.add("Q3");
		TestNames.add("Q4");
		TestNames.add("MidI");
		TestNames.add("MidII");
		TestNames.add("Final");
	}
	
	 public void calcLetterGrade() {
		 Character letterGrade = null;
		 for (double finalScore:FinalScore){
				if (finalScore >= 90){
					letterGrade = 'A';
				}else if(finalScore>=80 && finalScore<90){
					letterGrade = 'B';
				}else if (finalScore>=70 && finalScore<80){
					letterGrade = 'C';
				}else if (finalScore>=60 && finalScore<70){
					letterGrade='D';
				}else if(finalScore < 60){
					letterGrade='F';
				}
				LetterGrade.add(letterGrade);
		 }
		 
		 for(String name:Names){
			 for(Character l:LetterGrade){
				 ScoreMap.put(name, l);//putting name and letter grade into a tree map
			 }			 
		 }
	}
	 

		
	public void calculateGrades() {
		fo
		
		min1=Collections.min(Q1);			
		min2=Collections.min(Q2);
		min3=Collections.min(Q3);
		min4=Collections.min(Q4);
		min5=Collections.min(MidI);
		min6=Collections.min(MidII);
		min7=Collections.min(Final);
		
		max1=Collections.max(Q1);
		max2=Collections.max(Q2);
		max3=Collections.max(Q3);
		max4=Collections.max(Q4);
		max5=Collections.max(MidI);
		max6=Collections.max(MidII);
		max7=Collections.max(Final);
		
		avg1=Q1.stream().mapToDouble(val -> val).average().getAsDouble();
		avg2=Q2.stream().mapToDouble(val -> val).average().getAsDouble();
		avg3=Q3.stream().mapToDouble(val -> val).average().getAsDouble();
		avg4=Q4.stream().mapToDouble(val -> val).average().getAsDouble();
		avg5=MidI.stream().mapToDouble(val -> val).average().getAsDouble();
		avg6=MidII.stream().mapToDouble(val -> val).average().getAsDouble();
		avg7=Final.stream().mapToDouble(val -> val).average().getAsDouble();

	}
		
	 
}



