/**
 * Class Monitor
 * To synchronize dining phils.
 *
 * @author Serguei A. Mokhov, mokhov@cs.concordia.ca
 */

// IMPORTANT: Please note that the starting TID in BaseThread has been lowered to 0 to facilitate the implementation of
// this class using an array for the philosopher. The TID of a philosopher becomes their position in the array.
public class Monitor
{
	enum status {
		EATING, HUNGRY, THINKING, TALKING, SLEEPING, STANDING
	}
	// Starting number of chopsticks
	private int N;
	// Number of philosophers, updated upon philosophers leaving or rejoining.
	private int P;
	// Array of philosophers
	private status[] phils;
	/**
	 * Constructor. Initialize N and P and sets all the philosopher to think.
	 */
	public Monitor(int piNumberOfPhilosophers)
	{
		N = piNumberOfPhilosophers;
		P = piNumberOfPhilosophers;
		phils = new status[piNumberOfPhilosophers];
		for (int i=0; i<piNumberOfPhilosophers; i++){
			phils[i]= status.THINKING;
		}

	}

	/**
	 * Method to make a philosopher pick up the chopsticks. It will get hungry, test itself and wait until it is eating.
	 *
	 * @param piTID - ID of the philosopher.
	 */
	public synchronized void pickUp(final int piTID)
	{

		phils[piTID] = status.HUNGRY;
		System.out.println("Philosopher " + piTID + " is hungry.");
		test(piTID);
		while (phils[piTID]!=status.EATING){
			try {
				wait();
			} catch (InterruptedException e) {
				// do nothing
			}
		}
	}

	/**
	 * Method to make a philosopher put down the chopsticks and stop eating. It will then test its neighbors.
	 *
	 * @param piTID - ID of the philosopher.
	 */
	public synchronized void putDown(final int piTID)
	{
		phils[piTID] = status.THINKING;
		test(this.findLeft(piTID));
		test(this.findRight(piTID));
	}


	/**
	 * Method to make a philosopher request to talk. A philosopher can only tak if no other philosophers are talking
	 * or sleeping, so the philosopher waits until they are done doing before talking.
	 *
	 * @param piTID - ID of the philosopher.
	 */
	public synchronized void requestTalk(final int piTID)
	{
		while (phils[piTID] != status.TALKING) {
			boolean canTalk = true;
			for (int i=0; i<phils.length; i++) {
				if (phils[i] == status.TALKING || phils[i] == status.SLEEPING) {
					canTalk = false;
				}
			}
			if (canTalk==true){
				phils[piTID] = status.TALKING;
			} else {
				try {
					wait();
				} catch (InterruptedException e) {
					// do nothing
				}
			}
		}
	}

	/**
	 * Method to make a philosopher ends his speech. Notifies other philosophers upon execution.
	 *
	 * @param piTID - ID of the philosopher.
	 */
	public synchronized void endTalk(final int piTID)
	{
		phils[piTID] = status.THINKING;
		notifyAll();
	}
	/**
	 * Method to make a philosopher take a nap (sleep). It checks if any of the philosopher are talking first and wait
	 * until they are finished talking to sleep.
	 *
	 * @param piTID - ID of the philosopher.
	 */
	public synchronized void startNap(final int piTID)
	{
		while (phils[piTID] != status.SLEEPING) {
			boolean canNap = true;
			for (int i = 0; i < phils.length; i++) {
				if (phils[i] == status.TALKING) {
					canNap = false;
				}
			}
			if (canNap) {
				phils[piTID] = status.SLEEPING;
			} else {
				try {
					wait();
				} catch (InterruptedException e) {
					// do nothing
				}
			}
		}
	}
	/**
	 * Method to make a philosopher end its nap. Notifies other philosophers upon execution.
	 *
	 * @param piTID - ID of the philosopher.
	 */
	public synchronized void endNap(final int piTID)
	{
		phils[piTID] = status.THINKING;
		notifyAll();
	}
	/**
	 * Method to make a philosopher leave the table. A philosopher can only leave if the number of philosopher is
	 * greater than 2.
	 *
	 * @param piTID - ID of the philosopher.
	 */
	public synchronized void leave(final int piTID) {

		while (phils[piTID] != status.STANDING) {
			if (P>2){
				phils[piTID] = status.STANDING;
				P--;
			} else {
				try {
					wait();
				} catch (InterruptedException e) {
					// do nothing
				}
			}
		}

	}

	/**
	 * Method to make a philosopher rejoin the table. Notifies other philosophers upon execution.
	 *
	 * @param piTID - ID of the philosopher.
	 */
	public synchronized void rejoin(final int piTID) {
		phils[piTID] = status.THINKING;
		P++;
		notifyAll();
	}
	/**
	 * Method to test if the neighbors of the philosophers are eating. If they are not and the philsopher is hungry,
	 * the philosopher starts to eat and notifies other philosophers.
	 *
	 * @param piTID - ID of the philosopher.
	 */
	public synchronized void test(final int piTID) {
		if (this.checkLeft(piTID) &&
				this.checkRight(piTID)  &&
				phils[piTID] == status.HUNGRY) {
			phils[piTID] = status.EATING;
			notifyAll();
		}
	}
	/**
	 * Method to check the status of the left philosopher. If it is eating or if it is hungry and had a
	 * higher priority, return false. Else return true.
	 *
	 * @param piTID - ID of the philosopher.
	 * @return boolean
	 */
	public synchronized boolean checkLeft(final int piTID){
		if (phils[this.findLeft(piTID)] != status.EATING &&
				(this.findLeft(piTID) > piTID || phils[this.findLeft(piTID)] != status.HUNGRY)){
			return true;
		} else {
			return false;
		}
	}
	/**
	 * Method to check the status of the right philosopher. If it is eating or if it is hungry and had a
	 * higher priority, return false. Else return true.
	 *
	 * @param piTID - ID of the philosopher.
	 * @return boolean
	*/
	public synchronized boolean checkRight(final int piTID){
		if (phils[this.findRight(piTID)] != status.EATING &&
				(this.findRight(piTID) > piTID || phils[this.findRight(piTID)] != status.HUNGRY)){
			return true;
		} else {
			return false;
		}
	}
	/**
	 * Method to find the index (or TID) of the left philosopher and return it. This method will recurse if the
	 * left philosopher is standing, checking its left philosopher instead.
	 *
	 * @param piTID - ID of the philosopher.
	 * @return int
	*/
	public synchronized int findLeft(final int piTID) {
		if (phils[(piTID - 1 + N) % N] == status.STANDING){
			return findLeft((piTID - 1 + N) % N);
		} else {
			return (piTID - 1 + N) % N;
		}
	}
	/**
	 * Method to find the index (or TID) of the right philosopher and return it. This method will recurse if the
	 * right philosopher is standing, checking its right philosopher instead.
	 *
	 * @param piTID - ID of the philosopher.
	 * @return int
	 */
	public synchronized int findRight(final int piTID) {
		if (phils[(piTID + 1) % N] == status.STANDING){
			return findRight((piTID + 1) % N);
		} else {
			return (piTID + 1) % N;
		}
	}
}

// EOF
