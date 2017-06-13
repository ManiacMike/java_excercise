package midtermProject;

public class TestCalculator {
	
	public static void main(String[] args) {
		
		OOPCalculator calc1= new OOPCalculator();//声明一个实例1
		OOPCalculator calc2 = new OOPCalculator();//声明一个实例2
		calc1.askCalcChoice();//实例调用
		calc2.askCalcChoice();//实例调用，不同的实例间相互独立
		OOPCalculator.showVersion();//静态调用，用类名去调用，所有实例公用
		
//		while(true){
//			if(calc. askCalcChoice () != 5){
//				calc. askTwoValues (); 
//			}else{
//				calc.displayBye();
//			}
//		}
	}
}