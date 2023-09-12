package priorityQueue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

class node implements Comparable<node> {
	int priority;
	int info;

	node(int a, int b) {
		priority = a;
		info = b;
	}

	public String toString() {
		return "priority: "+ this.priority+", info: "+this.info;
	}
	@Override
	public int compareTo(node that) {
		if(that == null) {
			return 1;
		}
		if (this.priority >that.priority) {
			return 1;
		}
		return 0;
	}
}

class priorityQueue {
	List<node> ll;

	priorityQueue() {
		ll = new ArrayList<node>();
		ll.add(new node(-1, -1));
	}

	void insert(int priority, int info) {
		node n = new node(priority, info);
		ll.add(n);
		int len = (ll.size() - 1) / 2;
		// create max heap;
		for (int i = len; i > 0; i--) {
			Heapify(i, ll.size() - 1);
		}
	}
	
	node delete() {
		node val = ll.get(1);
		Collections.swap(ll, 1, ll.size()-1);
		ll.remove(ll.size()-1);
		Heapify(1, ll.size()-1);
		return val;
	}

	private void Heapify(int index, int len) {
		node left = null;
		node right = null;
		if (2 * index <= len) {
			left = ll.get(2 * index);
		}
		if ((2 * index + 1) <= len) {
			right = ll.get(2 * index + 1);
		}
		if (left == null && right == null)
			return;

		if (left.compareTo(right) == 1) {
			if (left.compareTo(ll.get(index)) == 1) {
				Collections.swap(ll, index, 2 * index);
				Heapify(index * 2, len);
			}
		} else {
			if (right.compareTo(ll.get(index)) == 1) {
				Collections.swap(ll, index, 2 * index + 1);
				Heapify(2 * index + 1, len);
			}
		}
	}
	
	
	
	void printQueue() {
		Iterator<node> it = ll.iterator();
		it.next();
		while(it.hasNext()) {
			System.out.println(it.next().toString());
		}
	}
}

public class test {
	public static void main(String[] a) {
		priorityQueue Queue = new priorityQueue();
		Queue.insert(5, 10);
		Queue.insert(8, 110);
		Queue.insert(6, 120);
		Queue.insert(3, 104);
		Queue.insert(2, 104);
		Queue.insert(1, 104);
		Queue.insert(15, 104);
		Queue.insert(10, 104);
		
		System.out.println(Queue.delete());
		System.out.println(Queue.delete());
		System.out.println(Queue.delete());
		System.out.println(Queue.delete());
		System.out.println("----------------");
		Queue.printQueue();
	}
}
