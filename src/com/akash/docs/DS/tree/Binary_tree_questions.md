Preorder, inorder, postorder using recursion | without using recursion
level order traversal (BFS) -> queue, recursion, 2 stack
get level of a node -> queue, recursion
get all nodes at a level -> queue, recursion
height of a binary tree
check if a binary tree is BST
print left view of binary tree		-- NLR keep tracking depth from the root , iterative | recursive
print right view of binary tree		-- NRL keep tracking depth from the root , iterative | recursive
print top view of binary tree		-- level order traversal, keep track of horizontal dist from root, create a map for (dist_from_root, node)
print bottom view of binary tree	-- same as top view, but next node found for same dist key , will override old key
print binary tree in vertical order
print boundary nodes in binary tree
print diagonal nodes in binary tree
Diagonal sum of binary tree
Kth largest in BST
Inorder traversal of binary tree without recursion with stack
Inorder traversal of binary tree without recursion without stack
Delete binary tree
check if two binary trees are identical
check if two binary trees are isomorphic
check if two trees are mirror
	->recursive
		->node.data, left==right, right==left
	->iterative
		->level order traversal with isLeft varaible
		->compare LNR and RNL
convert a binary tree into its mirror
--sorted linked list to BST
	->O(nlog(n)) mid of linked list will be root, use recursive for left and right
	->O(n) 
sorted array to balanced BST
	->mid of array will be root, use recursive for left and right
BST to min heap
	->inorder traversal of BST is min heap
normal BST to balanced BST
	->inorder of BST, sorted array to balanced BST
find median in BST
	->check no of nodes in BST,do inorder traversal and stop at n/2
BST from preorder
	->first element will be root, find first number in array greater than root,right subtree starts from there.Recursive
BST from postorder
Construct Tree from given Inorder and Preorder traversals
Construct a tree from Inorder and Level order traversals
Construct Tree from given Inorder and Postorder traversals
binary tree to doubly linked list

Lowest Common Ancestor(LCA)
	->findPath(Node,Node,List<Node>)
			recursively find full path of a node in a tree and capture it in a list.
			time = O(n),  memory = O(n) 
	-> do any traversal on tree and store parent of each node in a hashmap 
			time = O(n),  memory = O(n) 
	-> Every node should return a status object of two boolean variables
		every node should tell if its subtree(including itself), contains both nodes
	-> Build Euler path of tree with level of nodes
		find minimum level bw the two nodes

check if binary tree is heap   -> level order traversal, check parent child relation (0 to n/2)
given level order traversal of binary tree, check if min heap

--Construct all possible BSTs for keys 1 to N
--Find all possible binary trees with given Inorder Traversal
--merge k sorted arrays
--check if two nodes are swapped in BST
--Diameter of a binary tree
--floor and ceiling of a BST

--path with max sum in binary tree
--Check if all leaves are at same level
print perfect binary tree in below form:
	1 2 3 4 7 5 6 8 15 9 14 10 13 11 12 16 31 17 30 18 29 19 28 20 27 21 26  22 25 23 24
	http://www.geeksforgeeks.org/perfect-binary-tree-specific-level-order-traversal/
	-> 2 queue and 1 stack
--Remove all nodes which don�t lie in any path with sum>= k
--Convert left-right representation of a binary tree to down-right
Minimum no. of iterations to pass information to all nodes in the tree

Check for Children Sum Property in a Binary Tree 
	
--------------------------------------------------

--binary tree
--BST
--AVL tree
--Segment tree
	https://kartikkukreja.wordpress.com/2014/11/09/a-simple-approach-to-segment-trees/

red black tree
interval tree
threaded binary tree

--Heap

-------------------------------------------------


public void printVerticalNodes() {
	Map<Integer, List<Node<V>>> map = new HashMap<>();
	getVericalNodes(rootNode, map, 0);
	Iterator it = map.entrySet().iterator();
	while(it.hasNext()) {
		Map.Entry<Integer, List<Node<V>>> entry = it.next();
		System.out.print(entry.getKey()+" ");
		for(Node<V> node : entry.getValue()) {
			System.out.print(node.getValue()+" ");
		}
		System.out.println();
	}
}

private void getVericalNodes(Node<V> rootNode, Map<Integer, List<Node<V>>> map, int horizontalDistance) {
	if(rootNode==null) {
		return;
	}
	map.put(horizontalDistance, rootNode);
	getVericalNodes(rootNode.getLeft(), map, horizontalDistance-1);
	getVericalNodes(rootNode.getRight(), map, horizontalDistance+1);
}

private static class QNode<V> {
	Node<V> node;
	int hd;
}

public void printTopView(Node<V> rootNode) {
	Set<Integer> set = new HashSet<>();
	Queue<QNode<V>> queue = new LinkedList<>();
	queue.offer(rootNode);
	int hd;
	Node<V> temp;
	while(!queue.isEmpty()) {
		QNode<V> qNode = queue.poll();
		hd = qNode.hd;
		temp = qNode.node;
		if(!set.contains(hd)) {
			print(qNode.node.getValue()+" ");
			set.add(hd);
		}
		if(temp.left != null) {
			qNode.offer(new QNode(temp.left, hd-1));
		}
		if(temp.right != null) {
			qNode.offer(new QNode(temp.right, hd+1));
		}
	}
}


public void printBottomView(Node<V> rootNode) {
	Set<Integer> set = new HashSet<>();
	Queue<QNode<V>> queue = new LinkedList<>();
	Stack<QNode<V>> stack = new Stack<>();
	QNode newQNode = new QNode(rootNode, 0);
	queue.offer(newQNode);
	int hd;
	Node<V> temp;
	while(!queue.isEmpty()) {
		QNode<V> qNode = queue.poll();
		hd = qNode.hd;
		temp = qNode.node;
		if(temp.left != null) {
			newQNode = new QNode(temp.left, hd-1);
			queue.offer(newQNode);
		}
		if(temp.right != null) {
			newQNode = new QNode(temp.left, hd-1);
			queue.offer(newQNode);
		}
	}
	while(!stack.isEmpty()) {
		QNode<V> qNode = stack.pop();
		if(!set.contains(hd)) {
			print(qNode.node.getValue()+" ");
			set.add(hd);
		}
	}
}