package edu.vuum.mocca;
import java.util.*;

	 class simple1 extends Thread
	{
		public void run(){
			for (int i=1;i<=10;i++)
			System.out.println("in simple1");
		}
	}
	 class simple2 extends Thread
	{
		public void run(){
			for (int i=1;i<=10;i++)
			System.out.println("in simple2");
		}
	}
	public class test2 {
	public static void main(String args[])
	{
		new simple1().start();
		new simple2().start();
	}

}
