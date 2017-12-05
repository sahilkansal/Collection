package LinkList;

class node<T> {
	T info;
	node<T> next;

	node(T val) {
		info = val;
		next = null;
	}
}

public class list<T> {
	private node<T> HEAD;

	list() {
		HEAD = null;
	}

	public void insert(T val) {
		node<T> n = new node<T>(val);
		node<T> temp = HEAD;
		if (HEAD == null) {
			HEAD = n;
			return;
		}
		while (temp.next != null)
			temp = temp.next;
		temp.next = n;
	}

	public T delete(T val) {
		if (HEAD == null) {
			return null;
		}
		T t;
		/*if(HEAD.next == null) {
			T t = HEAD.info;
			HEAD = null;
			return t;
		}*/
		node<T> temp = HEAD;
		node<T> prev = null;
		while (temp.next != null && temp.info != val) {
			prev = temp;
			temp = temp.next;
		}
		t = temp.info;
		if(prev == null) {
			HEAD = temp.next;
			return t;
		}
		prev.next = temp.next;
		temp.next = null;
		return t;
	}

	public void Print() {
		node<T> temp = HEAD;

		while (temp != null) {
			System.out.println(temp.info);
			temp = temp.next;
		}
	}

	public static void main(String[] args) {
		list<Integer> ll = new list<Integer>();
		ll.Print();
		ll.insert(1);
		ll.insert(2);
		ll.insert(3);
		ll.insert(4);
		ll.delete(4);
		ll.Print();
	}
}
