package eg.edu.alexu.csd.filestructure.redblacktree;

public class Node<T extends Comparable<T>, V> implements INode{
      private T key =null;
      private V value;
      private INode<T,V> parent;
      private INode<T,V> leftChild;
      private INode<T,V> rightChild;
      private boolean color=RED;
      @Override
	public void setParent(INode parent) {
		this.parent=parent;
		
	}

	@Override
	public INode<T, V> getParent() {
		return parent;
	}

	@Override
	public void setLeftChild(INode leftChild) {
		// TODO Auto-generated method stub
		this.leftChild=leftChild;
	}

	@Override
	public INode getLeftChild() {
		// TODO Auto-generated method stub
		return this.leftChild;
	}

	@Override
	public void setRightChild(INode rightChild) {
		// TODO Auto-generated method stub
	  this.rightChild=rightChild;	
	}

	@Override
	public INode getRightChild() {
		return this.rightChild;
	}

	@Override
	public Comparable getKey() {
		return this.key;
	}

	@Override
	public void setKey(Comparable key) {
	this.key=(T) key;		
	}

	@Override
	public V getValue() {
		// TODO Auto-generated method stub
		return this.value;
	}

	@Override
	public void setValue(Object value) {
		// TODO Auto-generated method stub
		this.value=(V) value;
	}

	@Override
	public boolean getColor() {
		// TODO Auto-generated method stub
		return this.color;
	}

	@Override
	public void setColor(boolean color) {
		// TODO Auto-generated method stub
	  this.color=color;	
	}

	@Override
	public boolean isNull() {
		// TODO Auto-generated method stub
		return key==null;
	}

}
