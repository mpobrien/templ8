package org.mob.templ8;
public class Stack<T> {
	private final Object[] pile = new Object[255];
	private int topIndex = -1;

	public void push(T t) {
		//TODO check for overflow.
		topIndex++;
		pile[topIndex] = t;
	}


	@SuppressWarnings("unchecked")
	public T pop() {
		if( topIndex < 0 ){
            throw new RuntimeException("stack underflow.");
		}
		T top = (T)pile[topIndex];
		pile[topIndex] = null;
		topIndex--;
		return top;
	}

	@SuppressWarnings("unchecked")
	public T peek(){
		return (T)pile[this.topIndex];
	}

	public int getSize(){
		return this.topIndex + 1;
	}

}

