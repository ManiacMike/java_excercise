package test;

public class HelloWorld extends HelloWorldFather {
    public static void main(String []args) {
    	HelloWorld a = new HelloWorld();
    	a.greet();
        System.out.println("Hello World");
     }

}

//It's a relevantly private to HelloWorld
 class HelloWorldFather {
    protected String name ;
    protected int age;
    
    public void greet(){
    	System.out.println("Hello World"+name+age);
    }
}
