package code;

/* 
 * ASSIGNMENT 2
 * AUTHOR:  <Adar Bayan>
 * Class : ArrayDeque
 *
 * You are not allowed to use Java containers!
 * You must implement the Array Deque yourself
 *
 * MODIFY 
 * 
 * */

import given.iDeque;
import java.util.Arrays;
import java.util.Iterator;
import given.Util;

/*
 * You must have a circular implementation. 
 * 
 * WARNING: Modulo operator (%) might create issues with negative numbers.
 * Use Math.floorMod instead if you are having issues
 */
public class ArrayDeque<E> implements iDeque<E> {

	private E[] A; // Do not change this name!

	/*
	 * ADD FIELDS IF NEEDED
	 */
	private int size = 0;
	private int front = 0;

	public ArrayDeque() {
		this(1000);
		/*
		 * ADD CODE IF NEEDED
		 */
	}

	public ArrayDeque(int initialCapacity) {
		if (initialCapacity < 1)
			throw new IllegalArgumentException();
		A = createNewArrayWithSize(initialCapacity);

		/*
		 * ADD CODE IF NEEDED
		 */
	}

	// This is given to you for your convenience since creating arrays of generics
	// is not straightforward in Java
	@SuppressWarnings({ "unchecked" })
	private E[] createNewArrayWithSize(int size) {
		return (E[]) new Object[size];
	}

	// Modify this such that the dequeue prints from front to back!
	// Hint, after you implement the iterator, use that!
	public String toString() {

		/*
		 * MODIFY THE BELOW CODE
		 */
		if (isEmpty()) {
			return "";
		}

		StringBuilder sb = new StringBuilder(1000);
		sb.append("[");
		Iterator<E> iter = this.iterator();
		while (iter.hasNext()) {
			E e = iter.next();
			if (e == null)
				continue;
			sb.append(e);
			if (!iter.hasNext())
				sb.append("]");
			else
				sb.append(", ");
		}
		return sb.toString();
	}

	/*
	 * ADD METHODS IF NEEDED
	 */

	/*
	 * Below are the interface methods to be overriden
	 */

	@Override
	public int size() {
		// TODO Auto-generated method stub
//		Util.NotImplementedYetSoft();
		return size;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
//		Util.NotImplementedYetSoft();
		if (size == 0) {
			return true;
		} else {
			return false;
		}

	}

	@Override
	public void addFront(E o) {
		// TODO Auto-generated method stub
		if (size == A.length) {
			E firstTask[] = createNewArrayWithSize(2 * A.length);
			for (int i = 0; i < size - 1; i++) {
				firstTask[i] = A[i];
			}
			front = 0;
			A = firstTask;
		}
		front = Math.floorMod((front - 1 + A.length), A.length);
		A[front] = o;
		size++;
	}

//		Util.NotImplementedYetSoft();

	@Override
	public E removeFront() {
		// TODO Auto-generated method stub
//		Util.NotImplementedYetSoft();
		if (isEmpty()) {
			return null;
		} else {
			E temp = A[front];
			A[front] = null;
			front = Math.floorMod((front + 1), A.length);
			size--;
			return temp;
		}
	}

	@Override
	public E front() {
		// TODO Auto-generated method stub
//		Util.NotImplementedYetSoft();
		if (isEmpty()) {
			return null;
		} else {
			return A[front];
		}
	}

	@Override
	public void addBehind(E o) {
		// TODO Auto-generated method stub
		if (size == A.length) {
			E[] newOne1 = createNewArrayWithSize(2 * A.length);
			int temp = 0;
			for (int i = 0; i < size; i++) {
				newOne1[temp] = A[Math.floorMod((front + i), A.length)];
				temp++;
			}
			A = newOne1;
			front = 0;
		}
		int r = Math.floorMod((front + size), A.length);
		A[r] = o;
		size++;
	}

	@Override
	public E removeBehind() {
		// TODO Auto-generated method stub
//		Util.NotImplementedYetSoft();
		if (isEmpty()) {
			return null;
		} else {
			int temp = (Math.floorMod((front - 1 + size), A.length));
			E removed = A[temp];
			A[Math.floorMod((front - 1 + size), A.length)] = null;
			size--;
			return removed;
		}
	}

	@Override
	public E behind() {
		// TODO Auto-generated method stub
//		Util.NotImplementedYetSoft();
		if (isEmpty()) {
			return null;
		} else {
			return A[Math.floorMod((front - 1 + size), A.length)];
		}
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		for (int i = 0; i < A.length; i++) {
			A[i] = null;
		}
		front = 0;
		size = 0;

	}

	// Must print from front to back
	@Override
	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
		// Hint: Fill in the ArrayDequeIterator given below and return a new instance of
		// it
		return new ArrayDequeIterator();
	}

	private final class ArrayDequeIterator implements Iterator<E> {
		/*
		 * 
		 * ADD A CONSTRUCTOR IF NEEDED Note that you can freely access everything about
		 * the outer class!
		 * 
		 */
		private int dimension = 0;

		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			if (size > dimension)
				return true;
			return false;
		}

		@Override
		public E next() {
			// TODO Auto-generated method stub
			E answer;
			answer = A[Math.floorMod((front + dimension), A.length)];
			dimension++;
			return answer;
		}
	}
}
