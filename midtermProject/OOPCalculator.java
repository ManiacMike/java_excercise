package midtermProject;

import java.util.InputMismatchException;
import java.util.Scanner;

public class OOPCalculator {
	public float firstFloat,secondFloat,result;
	public String stringVerb, stringIng;
	public int menuChoice;
	
	public int askCalcChoice()throws NullPointerException,
									InputMismatchException{
											 
			@SuppressWarnings("resource")
			Scanner in=new Scanner(System.in);
			System.out.println("Welcome to <Yichun Yao's> Handy Calculator\n\n\t1.Addition"
					+ "\n\t2.Subtraction\n\t3.Multiplication\n\t4.Division\n\t5.Exit\n\n");
			
		do{
			System.out.print("What would you like to do?");
			try{	
				menuChoice = this.getIntCalcChoice(in.next());//get input from the user
				if(menuChoice>=1&&menuChoice<=5){
					break;//if input is within range, then it is ready to move on
				}else{
					System.out.println("You have not entered a number between 1 and 5. Try again.");
					continue;}//the do...while loop will continue
			}catch (NullPointerException e){
				System.out.println("Message: "+e.getMessage());
				System.out.println("Stack Trace: "+e.getStackTrace());
				in.nextLine();
				continue;
			}catch(InputMismatchException e){
				System.out.println("You have entered an invalid input. Try again.");
				in.nextLine();
				continue;
			}catch (Exception e){
				System.out.println("Message: Your input is invalid");
				in.nextLine();
				continue;
			}
		
		}while(true);
		
		if (menuChoice==5){
//			System.out.print("\n\nThank you for using <Yichun Yao's> Handy Calculator");
//			System.exit(0);
			}
		if(menuChoice==1){
			stringVerb="add";stringIng="adding";}
		if(menuChoice==2){
			stringVerb="substract";stringIng="subtracting";}
		if(menuChoice==3){
			stringVerb="multiply";stringIng="multiplying";}
		if(menuChoice==4){
			stringVerb="divide";stringIng="dividing";}					
		if(menuChoice>0&&menuChoice<5){
			System.out.print("Please enter two floats to "+stringVerb+", seperated by a space:");
			};
		in.nextLine();	
		
		return menuChoice;	
	
	}



	public float[] askTwoValues() throws NullPointerException,
									 InputMismatchException{

		@SuppressWarnings("resource")
		Scanner in=new Scanner(System.in);
		do{
			try{
				firstFloat=in.nextFloat();
				secondFloat=in.nextFloat();
				this.displayResults();
			break;//continue to the next step if there's no problem getting the two float input
			}catch(InputMismatchException e){
				System.out.print("You have entered an invalid input. Please re-enter:");
				in.nextLine();
				continue;
			}catch (NullPointerException e){
				System.out.println("Message: "+e.getMessage());
				System.out.println("Stack Trace: "+e.getStackTrace());
				in.nextLine();
				continue;	
			}catch (Exception e){
				System.out.print("Your input is invalid. Please re-enter:");
				in.nextLine();
				continue;
			}	
		}while(true);
		return new float[]{firstFloat,secondFloat};
	
	}	    


	public float displayResults() {
		do{				    
			   if (menuChoice==1){result=(firstFloat+secondFloat);
			   break;}
			   if (menuChoice==2){ result=(firstFloat-secondFloat);
			   break;}
			   if (menuChoice==3){ result=(firstFloat*secondFloat);
			   break;}
			   if (menuChoice==4) {
				   if (firstFloat!=0){
					   result=(firstFloat/secondFloat); 
					   break;}
				   else
					   System.out.print("You can't divide by zero please re-enter both floats:");//addressed the divide 0 problem
				   continue;
			   }
		}while(true);
		
		System.out.println("Result of "+stringIng+" "+String.format("%.2f", firstFloat)+" by "+String.format("%.2f", secondFloat)+" is "+ String.format("%.2f", result)+"." );
		return result;
		
	}


	public void displayBye() {
		Scanner s= new Scanner(System.in);
		System.out.println("\nThank you for using <Yichun Yao's> Handy Calculator \nPress Enter to Continue...\n");
		s.nextLine();
	//	s.close();
	}
	
	//convert string input to int
	protected int getIntCalcChoice(String str)  
	{  
	  try  
	  {
	     return (int)Double.parseDouble(str);  
	  }
	  catch(NumberFormatException nfe)  
	  {  
		  switch(str){
		  case  "A":
			  return 1;
		  case "S":
			  return 2;
		  case "M":
			  return 3;
		  case "D":
			  return 4;
		  case "E":
			  return 5;
		  default:
			return -1;
		  }
	  }
	}
	
	public static void main(String[] args) {
		OOPCalculator calc = new OOPCalculator();
		System.out.println("test");
		while(true){
			if(calc. askCalcChoice () != 5){
				calc. askTwoValues (); 
			}else{
				calc.displayBye();
			}
		}
	}
	
	public static void showVersion(){
		
	}
}


