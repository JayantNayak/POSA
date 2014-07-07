package edu.vuum.mocca;

// Import the necessary Java synchronization and scheduling classes.
import java.io.PrintStream;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.*;


/**
 * @class PingPongRight
 * 
 * @brief This class implements a Java program that creates two
 *        instances of the PlayPingPongThread and start these thread
 *        instances to correctly alternate printing "Ping" and "Pong",
 *        respectively, on the console display.
 */
public class PingPongRight {
    /**
     * Number of iterations to run the test program.
     */
    public final static int mMaxIterations = 10;

	private static final boolean True = false;

    /**
     * Latch that will be decremented each time a thread exits.
     */
    public static CountDownLatch mLatch ;

    /**
     * @class PlayPingPongThread
     * 
     * @brief This class implements the ping/pong processing algorithm
     *        using the SimpleSemaphore to alternate printing "ping"
     *        and "pong" to the console display.
     */
    public static class PlayPingPongThread extends Thread {
        /**
         * Constants to distinguish between ping and pong
         * SimpleSemaphores, if you choose to use an array of
         * SimpleSemaphores.  If you don't use this implementation
         * feel free to remove these constants.
         */
        //private final static int FIRST_SEMA = 0;
        //private final static int SECOND_SEMA = 1;
		//private static final boolean True = false;

        /**
         * Maximum number of loop iterations.
         */
        private int mMaxLoopIterations ;

        /**
         * String to print (either "ping!" or "pong"!) for each
         * iteration.
         */
        // TODO - You fill in here.
        private final String stringToPrint;
        
        

        /**
         * Two SimpleSemaphores use to alternate pings and pongs.  You
         * can use an array of SimpleSemaphores or just define them as
         * two data members.
         */
        // TODO - You fill in here.
        SimpleSemaphore semaphoreOne;
        SimpleSemaphore semaphoreTwo ;


        /**
         * Constructor initializes the data member(s).
         */
        public PlayPingPongThread(String stringToPrint,
                                  SimpleSemaphore semaphoreOne,
                                  SimpleSemaphore semaphoreTwo,
                                  int maxIterations) {
            // TODO - You fill in here.
        	 semaphoreOne=semaphoreOne;
        	this.semaphoreTwo=semaphoreTwo;
        	this.stringToPrint=stringToPrint;
        	this.mMaxLoopIterations=maxIterations;
        	//this.mMaxLoopIterations=1000;
        	
        }

        /**
         * Main event loop that runs in a separate thread of control
         * and performs the ping/pong algorithm using the
         * SimpleSemaphores.
         */
        public void run() {
            /**
             * This method runs in a separate thread of control and
             * implements the core ping/pong algorithm.
             */

            // TODO - You fill in here.
        	
        	try{
        		
        		for(int loopsdone=1;loopsdone<=mMaxLoopIterations;loopsdone++)
        		{
        			acquire() ;
        			System.out.println(stringToPrint+"("+loopsdone+")");
        			release();
        		
        		}
           }
        finally{	
        	mLatch.countDown();
        } 
        }

        /**
         * Method for acquiring the appropriate SimpleSemaphore.
         */
        private void acquire() {
        	
            // TODO fill in here
        	/**if (semaphoreOne.availablePermits()>0)
        		semaphoreOne.acquireUninterruptibly();
        	if (semaphoreTwo.availablePermits()>0)
        		semaphoreTwo.acquireUninterruptibly();
        	**/
        	

        	//System.out.println("acquire");
        	//System.out.println("permits"+semaphoreOne.availablePermits());
        	
        	semaphoreOne.acquireUninterruptibly();
        	
        }

        /**
         * Method for releasing the appropriate SimpleSemaphore.
         */
        private void release() {
            // TODO fill in here
        	/**if (semaphoreTwo.availablePermits()<=0)
        		semaphoreOne.release();
        	if (semaphoreOne.availablePermits()<=0)
        		semaphoreTwo.release();
        	**/
        	//System.out.println("release");
        	semaphoreTwo.release();
        	//System.out.println("permits"+semaphoreTwo.availablePermits());
        	
        }
    }

    /**
     * The method that actually runs the ping/pong program.
     */
    public static void process(String startString, 
                               String pingString,
                               String pongString, 
                               String finishString, 
                               int maxIterations) throws InterruptedException {

        // TODO initialize this by replacing null with the appropriate
        // constructor call.
        mLatch = new CountDownLatch(2);

        // Create the ping and pong SimpleSemaphores that control
        // alternation between threads.

        // TODO - You fill in here, make pingSema start out unlocked.
        //SimpleSemaphore pingSema = new SimpleSemaphore(1,true);
        SimpleSemaphore pingSema = new SimpleSemaphore(1,false);

        // TODO - You fill in here, make pongSema start out locked.
        //SimpleSemaphore pongSema =new SimpleSemaphore(0,true);
        SimpleSemaphore pongSema =new SimpleSemaphore(0,false);

        System.out.println(startString);

        // Create the ping and pong threads, passing in the string to
        // print and the appropriate SimpleSemaphores.
        PlayPingPongThread ping = new PlayPingPongThread(/*
                                                          * TODO - You fill in
                                                          * here
                                                          */
        		pingString,pingSema,pongSema,maxIterations);
        PlayPingPongThread pong = new PlayPingPongThread(/*
                                                          * TODO - You fill in
                                                          * here
                                                          */
        		pongString,pongSema,pingSema,maxIterations);

        // TODO - Initiate the ping and pong threads, which will call
        // the run() hook method.
        ping.start();
       pong.start();
        // TODO - replace the following line with a barrier
        // synchronizer call to mLatch that waits for both threads to
        // finish.
        try{
        mLatch.await();
        throw new java.lang.InterruptedException();
        }
        catch(Exception e)
        {}

        System.out.println(finishString);
    }

    /**
     * The main() entry point method into PingPongRight program.
     * 
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        process("Ready...Set...Go!", 
                "Ping!  ",
                " Pong! ",
                "Done!",
                mMaxIterations);
    }
}

