/**
 * Class BlockStack
 * Implements character block stack and operations upon it.
 *
 * $Revision: 1.4 $
 * $Last Revision Date: 2019/02/02 $
 *
 * @author Serguei A. Mokhov, mokhov@cs.concordia.ca;
 * Inspired by an earlier code by Prof. D. Probst

 */
class BlockStack
{
	/**
	 * # of letters in the English alphabet + 2
	 */
	public static final int MAX_SIZE = 28;

	/**
	 * Default stack size
	 */
	public static final int DEFAULT_SIZE = 6;

	/**
	 * Current size of the stack
	 */
	// Changed to private
	private int iSize = DEFAULT_SIZE;

	/**
	 * Current top of the stack
	 */
	// Changed to private
	private int iTop  = 3;

	/**
	 * stack[0:5] with four defined values
	 */
	// Changed to private
	private char acStack[] = new char[] {'a', 'b', 'c', 'd', '*', '*'};

	// Changed to private
	private int stackAccessCounter=0;

	/**
	 * Default constructor
	 */

	public BlockStack()
	{
	}

	/**
	 * Supplied size
	 */
	public BlockStack(final int piSize)
	{


                if(piSize != DEFAULT_SIZE)
		{
			this.acStack = new char[piSize];

			// Fill in with letters of the alphabet and keep
			// 2 free blocks
			for(int i = 0; i < piSize - 2; i++)
				this.acStack[i] = (char)('a' + i);

			this.acStack[piSize - 2] = this.acStack[piSize - 1] = '$';

			this.iTop = piSize - 3;
                        this.iSize = piSize;
		}
	}

	/**
	 * Picks a value from the top without modifying the stack
	 * @return top element of the stack, char
	 */
	public char pick()
	{
		//Try/catch block for exception handling. Throws an exception if the stack is empty and return '*'
		try {
			if (this.isEmpty()){
				throw new EmptyStackException();
			} else {
				stackAccessCounter++;
				return this.acStack[this.iTop];
			}
		} catch (EmptyStackException e){
			System.out.print(e.getMessage());
			return '*';
		}
	}

	/**
	 * Returns arbitrary value from the stack array
	 * @return the element, char
	 */
	public char getAt(final int piPosition)
	{
		// Try/catch block for exception handling. Throws an exception if the position is not within the stack and
		// returns '*'. Throws an exception if the stack is empty and return '*'
		try {
			if (this.isEmpty()) {
				throw new EmptyStackException();
			} else if (piPosition<0||piPosition>=this.getISize()){
					throw new IncorrectPositionException();
			} else {
					stackAccessCounter++;
					return this.acStack[piPosition];
			}
		} catch (EmptyStackException e){
			System.out.print(e.getMessage());
			return '*';
		} catch(IncorrectPositionException e){
			System.out.print(e.getMessage());
			return '*';
		}
	}

	/**
	 * Standard push operation
	 */
	public void push(final char pcBlock)
	{

		// Try/catch block for exception handling. If the stack is full, the block cannot be pushed. If the stack is
		// empty, push 'a' instead.
		try {
			if (this.isEmpty()) {
				throw new EmptyStackException();
			} else if (this.getITop()==(this.getISize()-1)){
				throw new FullStackException();
			} else {
				stackAccessCounter++;
				this.acStack[++this.iTop] = pcBlock;
				System.out.println("Successfully pushed a block");
			}
		} catch (EmptyStackException e) {
			System.out.println(e.getMessage());
			stackAccessCounter++;
			this.acStack[++this.iTop] = 'a';
		} catch (FullStackException e){
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Standard pop operation
	 * @return ex-top element of the stack, char
	 */
	public char pop() {
		//Try/catch block for exception handling. Throws an exception if the stack is empty and returns '*'
		try {
			if (this.isEmpty()){
				throw new EmptyStackException();
			} else {
				char cBlock = this.acStack[this.iTop];
				stackAccessCounter++;
				this.acStack[this.iTop--] = '*'; // Leave prev. value undefined
				System.out.println("Successfully poped a block");
				return cBlock;
			}
		} catch (EmptyStackException e){
			System.out.println(e.getMessage());
			return '*';
		}
	}
	public int getAccessCounter(){
		return this.stackAccessCounter;
	}
	public int getITop(){
		return this.iTop;
	}
	public int getISize(){
		return this.iSize;
	}
	public boolean isEmpty(){
		return (this.iTop == -1);
	}
}

// EOF
