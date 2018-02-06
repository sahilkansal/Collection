package tree;

class node {
	int info;
	node left;
	node right;
	node parent;

	node(int val, node p) {
		info = val;
		left = null;
		right = null;
		parent = p;
	}
}

class Tree {
	private node root;

	Tree() {
		root = null;
	}

	private void insert_temp(int val, node N, node parent) {
		if (N == null) {
			node temp = new node(val, parent);
			if (parent.info > val) {
				parent.left = temp;
			} else {
				parent.right = temp;
			}
			return;
		}
		node temp = N;

		if (temp.info > val) {
			temp = temp.left;
		} else {
			temp = temp.right;
		}
		insert_temp(val, temp, N);
	}

	void insert(int val) {
		if (root == null) {
			node temp = new node(val, null);
			root = temp;
			return;
		}
		insert_temp(val, root, null);
	}

	void inorder() {
		if (root != null) {
			inorder_t(root);
		} else
			System.out.println("empty tree");
	}

	private void inorder_t(node n) {
		if (n != null) {
			inorder_t(n.left);
			System.out.print(n.info + "  parent node: ");
			if (n.parent != null) {
				System.out.println(n.parent.info);
			} else {
				System.out.println("null");
			}
			inorder_t(n.right);
		}
	}

	node searchNode(int val) {
		if (root != null) {
			return searchNode(val, root);
		}
		return null;
	}

	private node searchNode(int val, node root2) {
		if (val == root2.info) {
			return root2;
		} else if (val > root2.info) {
			return searchNode(val, root2.right);
		} else {
			return searchNode(val, root2.left);
		}
	}

	node successor(int val) {
		node N = searchNode(val);
		// node result = null;
		if (N.right != null) {

			return TreeMinimum(N.right);
		} else {
			node P = N.parent;
			while (P != null && val == P.right.info) {
				val = P.info;
				P = P.parent;
			}
			return P;
		}
	}

	private node TreeMinimum(node root) {
		node temp = root;
		while (temp.left != null) {
			temp = TreeMinimum(temp.left);
		}
		return temp;
	}

	void delete(int val) {
		if (root != null)
			delete_temp(val, root);
	}

	private void delete_temp(int val, node root) {
		if (val > root.info) {
			delete_temp(val, root.right);
		} else if (val < root.info) {
			delete_temp(val, root.left);
		}else {
			if(root.left == null) {
				Transplant(root,root.right);
			}
			else if(root.right == null) {
				Transplant(root,root.left);
			}else {
				node S = successor(val);
				root.info = S.info;
				Transplant(S, S.right);
			}
			
		}
	}

	private void Transplant(node root, node subTree) {
		if(root.parent == null) {
			this.root = subTree;
		}
		if(root.info == root.parent.left.info) {
			root.parent.left = subTree;
		}
		if(root.info == root.parent.right.info) {
			root.parent.right = subTree;
		}
		
		if(subTree != null) {
			subTree.parent = root.parent;
		}
	}
}

public class BST {
	public static void main(String[] args) {
		Tree T = new Tree();
		T.insert(12);
		T.insert(5);
		T.insert(2);
		T.insert(9);
		T.insert(18);
		T.insert(19);
		T.insert(15);
		T.insert(17);
		T.insert(13);
		T.insert(14);
		
		T.delete(12);
		 T.inorder();
	}
}
