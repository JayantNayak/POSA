package edu.vuum.mocca;

public class test extends Thread {
	//public test(){}
	public void run() {    	
    	
    	for(int loopsdone=1;loopsdone<=50;loopsdone++)
    	{
    		
    		System.out.println("hello "+"("+loopsdone+")");
    		
    		
    	}
    	
    
    }
	static void process(){
		new test().run();
		new test().run();
	}

	
	  public static void main(String[] args) throws InterruptedException {
	        process();
	    }

}
