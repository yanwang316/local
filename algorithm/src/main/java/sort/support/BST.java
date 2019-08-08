package sort.support;

/**
 * 整数二叉排序树
 * 	1. 需要构建平衡树吗
 * @author norman
 *
 */
public class BST {
	
	private Node root;
	
	public void add(int value) {
		Node node = new Node(value);
		if (root == null) {
			root = node;
		}else {
			root.addNode(node);
		}
	}
	
	public int[] collect() {
		return this.root.collect();
	}

	class Node{
		private int value;
		
		private Node left;
		
		private Node right;
		
		public Node(int value) {
			this.value = value;
		}
		
		public int getValue() {
			return value;
		}

		public void setValue(int value) {
			this.value = value;
		}

		public Node getLeft() {
			return left;
		}

		public void setLeft(Node left) {
			this.left = left;
		}

		public Node getRight() {
			return right;
		}

		public void setRight(Node right) {
			this.right = right;
		}



		/**
		 * 树规则
		 * 	1. 左子树值小于跟节点
		 * 	2. 右子树值大于跟节点
		 * 	3. 左右子树也为二叉排序树
		 * @param node
		 */
		public void addNode(Node node) {
			// 判断是放左子树还是右子树
			if (this.getValue() > node.getValue()) {
				// 放左子树
				if (this.left == null) {
					this.left = node;
				}else {
					this.left.addNode(node);
				}
			}else {
				if (this.right == null) {
					this.right = node;
				}else {
					this.right.addNode(node);
				}
			}
		}
		
		public int[] collect(){
			int[] leftArray = this.left == null ? null : this.left.collect();
			int currentValue = this.value;
			int[] rightArray = this.right == null ? null: this.right.collect();
			int len = (leftArray == null ? 0 : leftArray.length) + 1 + (rightArray == null ? 0 : rightArray.length),
					left = 0, 
					right = 0,
					r = 0;
			int[] result = new int[len];
			while(leftArray != null && left < leftArray.length)
				result[r++] = leftArray[left++];
			result[r++] = currentValue;
			while (rightArray != null && right < rightArray.length) 
				result[r++] = rightArray[right++];
			return result;
		}
	}
}
