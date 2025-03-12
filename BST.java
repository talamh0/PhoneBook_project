public class BST<T> {

	private BSTNode<T> root, current;

	public BST() {
		current = root = null;
	}

	public void clear() {
		current = root = null;
	}

	public boolean empty() {
		return root == null;
	}

	public boolean full() {
		return false;
	}

	public T retrieve() {
		return current.data;
	}

	public BSTNode<T> getRoot() {
		return root;
	}
	
	public boolean findKey(String k) {
		BSTNode<T> p = root, q = root;
		if (empty())
			return false;
		while (p != null) {
			q = p;
			if (p.key.compareTo(k) == 0) {
				current = p;
				return true;
			} else if (p.key.compareTo(k) > 0)
				p = p.left;
			else
				p = p.right;
		}
		current = q;
		return false;
	}

	public boolean insert(String k, T val) {
		BSTNode<T> p, q = current;
		if (findKey(k)) {
			current = q;
			return false;
		}
		p = new BSTNode<T>(k, val);
		if (empty()) {
			root = current = p;
			return true;
		} else {
			if (k.compareTo(current.key) < 0)
				current.left = p;
			else
				current.right = p;
			current = p;
			return true;
		}
	}

	public boolean removeKey(String k) {
		String k1 = k;
		BSTNode<T> p = root;
		BSTNode<T> q = null;
		while (p != null) {
			if (k1.compareTo(p.key) < 0) {
				q = p;
				p = p.left;
			} else if (k1.compareTo(p.key) > 0) {
				q = p;
				p = p.right;
			} else {
				if ((p.left != null) && (p.right != null)) {
					BSTNode<T> min = p.right;
					q = p;
					while (min.left != null) {
						q = min;
						min = min.left;
					}
					p.key = min.key;
					p.data = min.data;
					k1 = min.key;
					p = min;
				}
				if (p.left != null) {
					p = p.left;
				} else {
					p = p.right;
				}

				if (q == null) {
					root = p;
				} else {
					if (k1.compareTo(q.key) < 0) {
						q.left = p;
					} else {
						q.right = p;
					}
				}
				current = root;
				return true;

			}
		}

		return false;
	}
	
	public boolean find(T val) {
		Contact contact = (Contact) val;
		return findKey(contact.getName()) || findPhone(root, contact.phone);
	}
	
	public boolean insert(T val) {
		Contact contact = (Contact) val;	
		return insert(contact.getName(), val);
	}
	
	public boolean remove() {
		if (!empty()) 
			return removeKey(current.key);
		else
			return false;
	}

	private boolean findPhone(BSTNode<T> node, String phone) {
		if (node == null)
		      return false;
		else {
			Contact contact = (Contact) node.data;
			return findPhone(node.left, phone) || contact.phone.equals(phone) || findPhone(node.right, phone);
		}
	}

	public String getContactsByFirstName(String firstName) {
		return getContactsByFirstName(root, firstName);
	}

	private String getContactsByFirstName(BSTNode<T> node, String firstName) {
		if (node == null)
		      return "";
		
		String contacts = "";
		contacts += getContactsByFirstName(node.left, firstName);
		
		Contact contact = (Contact) node.data;
		if(contact.firstName.equalsIgnoreCase(firstName))
			contacts += contact.toString() + "\n";

		contacts += getContactsByFirstName(node.right, firstName);
		
		return contacts;
	}
}