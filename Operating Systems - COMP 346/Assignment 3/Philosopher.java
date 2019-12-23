import common.BaseThread;

/**
 * Class Philosopher.
 * Outlines main subrutines of our virtual philosopher.
 *
 * @author Serguei A. Mokhov, mokhov@cs.concordia.ca
 */
public class Philosopher extends BaseThread
{
	/**
	 * Max time an action can take (in milliseconds)
	 */
	public static final long TIME_TO_WASTE = 1000;


	/**
	 * The act of eating.
	 * - Print the fact that a given phil (their TID) has started eating.
	 * - yield
	 * - Then sleep() for a random interval.
	 * - yield
	 * - The print that they are done eating.
	 */
	public void eat()
	{
		try
		{
			// ...
			System.out.println("Philosopher " + this.getTID() + " has started eating.");
			yield();
			sleep((long)(Math.random() * TIME_TO_WASTE)); // define variable TIME_TO_WASTE
			yield();
			System.out.println("Philosopher " + this.getTID() + " has finished eating.");
			// ...
		}
		catch(InterruptedException e)
		{
			System.err.println("Philosopher.eat():");
			DiningPhilosophers.reportException(e);
			System.exit(1);
		}
	}

	/**
	 * The act of thinking.
	 * - Print the fact that a given phil (their TID) has started thinking.
	 * - yield
	 * - Then sleep() for a random interval.
	 * - yield
	 * - The print that they are done thinking.
	 */
	public void think()
	{
		try {// ...
			System.out.println("Philosopher " + this.getTID() + " has started thinking.");
			yield();
			sleep((long) (Math.random() * TIME_TO_WASTE)); // define variable TIME_TO_WASTE
			yield();
			System.out.println("Philosopher " + this.getTID() + " has finished thinking.");
		} catch(InterruptedException e) {
			System.err.println("Philosopher.think():");
			DiningPhilosophers.reportException(e);
			System.exit(1);
		}
	}

	/**
	 * The act of talking.
	 * - Print the fact that a given phil (their TID) has started talking.
	 * - yield
	 * - Say something brilliant at random
	 * - yield
	 * - The print that they are done talking.
	 */
	public void talk()
	{
		try {
			System.out.println("Philosopher " + this.getTID() + " has started talking.");
			yield();
			saySomething();
			//Duration of the speech.
			sleep((long) (Math.random() * TIME_TO_WASTE));
			yield();
			System.out.println("Philosopher " + this.getTID() + " has finished talking.");
		} catch (InterruptedException e) {
			// do nothing
		}
	}

	/**
	 * No, this is not the act of running, just the overridden Thread.run()
	 */
	public void run() {
		for(int i = 0; i < DiningPhilosophers.DINING_STEPS; i++) {
			DiningPhilosophers.soMonitor.pickUp(getTID());
			eat();
			DiningPhilosophers.soMonitor.putDown(getTID());
			think();
			// The philosopher has a 1/2 chance of talking
			double x = Math.random()*DiningPhilosophers.DINING_STEPS;
			if((int) x < DiningPhilosophers.DINING_STEPS/2) {
				DiningPhilosophers.soMonitor.requestTalk(getTID());
				talk();
				DiningPhilosophers.soMonitor.endTalk(getTID());
				think();
			}
			// The philosopher has a 1/2 chance of sleeping
			x = Math.random()*DiningPhilosophers.DINING_STEPS;
			if((int) x < DiningPhilosophers.DINING_STEPS/2) {
				DiningPhilosophers.soMonitor.startNap(getTID());
				nap();
				DiningPhilosophers.soMonitor.endNap(getTID());
				think();
			}
			// The philosopher has a 1/5 chance of standing
			if ((int) x < DiningPhilosophers.DINING_STEPS/5){
				DiningPhilosophers.soMonitor.leave(getTID());
				leave();
				DiningPhilosophers.soMonitor.rejoin(getTID());
				think();
			}
			yield();
		}
	} // run()

	/**
	 * Prints out a phrase from the array of phrases at random.
	 * Feel free to add your own phrases.
	 */
	public void saySomething() {
		String[] astrPhrases =
		{
			"Eh, it's not easy to be a philosopher: eat, think, talk, eat...",
			"You know, true is false and false is true if you think of it",
			"2 + 2 = 5 for extremely large values of 2...",
			"If thee cannot speak, thee must be silent",
			"My number is " + getTID() + ""
		};

		System.out.println
		(
			"Philosopher " + getTID() + " says: " +
			astrPhrases[(int)(Math.random() * astrPhrases.length)]
		);
	}
	public void nap()
	{
		try {
			System.out.println("Philosopher " + this.getTID() + " has started sleeping.");
			yield();
			sleep((long) (Math.random() * TIME_TO_WASTE));
			yield();
			System.out.println("Philosopher " + this.getTID() + " has finished sleeping.");

		} catch (InterruptedException e){
			System.err.println("Philosopher.nap():");
			DiningPhilosophers.reportException(e);
			System.exit(1);
		}
	}
	public void leave()
	{
		try {
			System.out.println("Philosopher " + this.getTID() + " has left.");
			yield();
			//Duration of standing.
			sleep((long) (Math.random() * TIME_TO_WASTE * 10));
			yield();
			System.out.println("Philosopher " + this.getTID() + " has rejoined.");

		} catch (InterruptedException e){
			System.err.println("Philosopher.leave():");
			DiningPhilosophers.reportException(e);
			System.exit(1);
		}
	}
}

// EOF
