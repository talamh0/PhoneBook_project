public class LinkedList<T extends Comparable<T>> {
	private Node<T> head;
	private Node<T> current;

	public LinkedList() {
		head = current = null;
	}

	public boolean empty() {
		return head == null;
	}

	public boolean last() {
		return current.next == null;
	}

	public boolean full() {
		return false;
	}

	public void findFirst() {
		current = head;
	}

	public void findNext() {
		current = current.next;
	}

	public T retrieve() {
		return current.data;
	}

	public void update(T val) {
		current.data = val;
	}

	// Insert items in order
	public void insert(T val) {
		if (empty()) {
			current = head = new Node<T>(val);
		} else {
			Node<T> node = new Node<T>(val);
			if (val.compareTo(head.data) < 0) {				
				node.next = head;
				current = head = node;
			} else {
				Node<T> temp = head;

				while (temp.next != null && val.compareTo(temp.next.data) > 0)
					temp = temp.next;

				if (temp.next != null) {
					node.next = temp.next;
				}
				
				temp.next = node;
				current = temp;
			}
		}
	}

	public boolean find(T key) {
		Node<T> tmp = head;
		while (tmp != null) {
			if (tmp.data.equals(key)) {
				current = tmp;
				return true;
			}
			tmp = tmp.next;
		}
		return false;
	}

	public void remove() {
		if (current == head) {
			head = head.next;
		} else {
			Node<T> tmp = head;

			while (tmp.next != current)
				tmp = tmp.next;

			tmp.next = current.next;
		}

		if (current.next == null)
			current = head;
		else
			current = current.next;
	}

}
