package code;

/* 
 * ASSIGNMENT 2
 * AUTHOR:  <Adar Bayan>
 * Class : LLDeque
 *
 * You are not allowed to use Java containers!
 * You must implement the linked list yourself
 * Note that it should be a doubly linked list
 *
 * MODIFY 
 * 
 * */

import given.iDeque;
import java.util.Iterator;
import given.Util;

//If you have been following the class, it should be obvious by now how to implement a Deque wth a doubly linked list
public class LLDeque<E> implements iDeque<E> {

	// Use sentinel nodes. See slides if needed
	private Node<E> header;
	private Node<E> trailer;
	private int size1 = 0;
	/*
	 * ADD FIELDS IF NEEDED
	 */

	// The nested node class, provided for your convenience. Feel free to modify
	private class Node<T> {
		private T element;
		private Node<T> next;
		private Node<T> prev;
		/*
		 * ADD FIELDS IF NEEDED
		 */

		Node(T d, Node<T> n, Node<T> p) {
			element = d;
			next = n;
			prev = p;
		}

		/*
		 * ADD METHODS IF NEEDED
		 */
		public T getElement() {
			return element;
		}

		void setNext(Node<T> n) {
			next = n;
		}

		public Node<T> getPrev() {
			return prev;
		}

		public Node<T> getNext() {
			return next;
		}

		void setPrevious(Node<T> n) {
			prev = n;
		}
	}

	public LLDeque() {
		// Remember how we initialized the sentinel nodes
		header = new Node<E>(null, null, header);
		trailer = new Node<E>(null, trailer, header);
		header.next = trailer;

		/*
		 * ADD CODE IF NEEDED
		 */
	}

	public String toString() {
		if (isEmpty())
			return "";
		StringBuilder sb = new StringBuilder(1000);
		sb.append("[");
		Node<E> tmp = header.next;
		while (tmp.next != trailer) {
			sb.append(tmp.element.toString());
			sb.append(", ");
			tmp = tmp.next;
		}
		sb.append(tmp.element.toString());
		sb.append("]");
		return sb.toString();
	}

	/*
	 * ADD METHODS IF NEEDED
	 */
	private void additionTask(E e, Node<E> predecessor, Node<E> successor) {
		Node<E> newNode = new Node<>(e, successor, predecessor);
		predecessor.setNext(newNode);
		successor.setPrevious(newNode);
		size1++;
	}

	private E remove(Node<E> node1) {
		Node<E> predecessor = node1.getPrev();
		Node<E> successor = node1.getNext();
		predecessor.setNext(successor);
		successor.setPrevious(predecessor);
		size1--;
		return node1.getElement();
	}
	/*
	 * Below are the interface methods to be overriden
	 */

	@Override
	public int size() {
		// TODO Auto-generated method stub
//		Util.NotImplementedYetSoft();
		return size1;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
//		Util.NotImplementedYetSoft();
		if (size1 == 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void addFront(E o) {
		// TODO Auto-generated method stub
//		Util.NotImplementedYetSoft();
		additionTask(o, header, header.getNext());
	}

	@Override
	public E removeFront() {
		// TODO Auto-generated method stub
		if (isEmpty())
			return null;
		return remove(header.getNext());

	}

	@Override
	public E front() {
		// TODO Auto-generated method stub
//		Util.NotImplementedYetSoft();
		if (isEmpty()) {
			return null;
		} else {
			return header.getNext().getElement();
		}
	}

	@Override
	public void addBehind(E o) {
		// TODO Auto-generated method stub
//		Util.NotImplementedYetSoft();
		additionTask(o, trailer.getPrev(), trailer);
	}

	@Override
	public E removeBehind() {
		// TODO Auto-generated method stub
//		Util.NotImplementedYetSoft();
		if (isEmpty())
			return null;
		return remove(trailer.getPrev());
	}

	@Override
	public E behind() {
		// TODO Auto-generated method stub
//		Util.NotImplementedYetSoft();
		if (isEmpty()) {
			return null;
		} else {
			return trailer.getPrev().getElement();
		}
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		header.next = trailer;
		trailer.prev = header;
		size1 = 0;
	}

	@Override
	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
		// Hint: Fill in the LLDequeIterator given below and return a new instance of it

		return new LLDequeIterator();
	}

	private final class LLDequeIterator implements Iterator<E> {

		/*
		 * 
		 * ADD A CONSTRUCTOR IF NEEDED Note that you can freely access everything about
		 * the outer class!
		 * 
		 */
		public LLDequeIterator() {
		}

		private Node<E> iterator1 = header;

		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return iterator1.getNext() != trailer;
		}

		@Override
		public E next() {
			// TODO Auto-generated method stub
			if (!hasNext())
				return null;

			iterator1 = iterator1.getNext();

			return iterator1.getElement();
		}

	}
}
