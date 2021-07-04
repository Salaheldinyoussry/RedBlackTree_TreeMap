package eg.edu.alexu.csd.filestructure.redblacktree;

import javax.management.RuntimeErrorException;

public class RedBlackTree<T extends Comparable<T>, V> implements IRedBlackTree{
    private INode<T, V> root = new Node<>();
    public Node<T,V> nil =new Node<T,V>();
    public int size=0;
    public RedBlackTree() {
    	nil.setColor(Node.BLACK);
    	root.setLeftChild(nil);
    	root.setRightChild(nil);
    	root.setParent(nil);
    }
    
	@Override
	public INode<T, V> getRoot() {
     	return this.root;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return size==0;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		size=0;
		root=nil;
	}

	@Override
	public V search(Comparable key) {
		// TODO Auto-generated method stub
		if(key==null) {
			throw new RuntimeErrorException(null); 
		}
		INode temp=root;
		while(!temp.isNull()) {
			if(key.compareTo(temp.getKey())==0) {
				return (V) temp.getValue();
			}
			else if(key.compareTo(temp.getKey())>0) {
				temp=temp.getRightChild();
			}
			else {
				temp=temp.getLeftChild();
				
			}
		}
		
		return null;
	}

	@Override
	public boolean contains(Comparable key) {
		// TODO Auto-generated method stub
		if (key==null) {
			throw new RuntimeErrorException(null);

		}
		return search(key)!=null;
	}

	@Override
	public void insert(Comparable key, Object value) {
		// TODO Auto-generated method stub
		if(key==null || value==null) {
			throw new RuntimeErrorException(null);

		}
		Node <T,V> n=new Node<T,V>();
		n.setKey(key);
		n.setValue(value);
		n.setColor(INode.RED);
		n.setLeftChild(nil);
		n.setRightChild(nil);
		n.setParent(nil);
		
	///	System.out.println(n.getColor());
		if(root.isNull()) {
			root=n;
			size++;
			root.setColor(false);
		return;
		}
	
		add(root,n);

		root.setColor(false);

	
		
	}

	private void add(INode<T, V> parent, Node<T, V> n) {
     	if(n.getKey().compareTo(parent.getKey())>0) {
     		if(parent.getRightChild()==nil) {
     			parent.setRightChild(n);
     			n.setParent(parent);
     			size++;
     			checkViolation(n);
     			return;
     		}
     		add(parent.getRightChild(),n);
     	}
     	else if  (n.getKey().compareTo(parent.getKey())<0) {
     		if(parent.getLeftChild()==nil) {
     			parent.setLeftChild(n);
     			n.setParent(parent);
     			size++;
     			checkViolation(n);
     			return;
     		}
     		add(parent.getLeftChild(),n);
     	}
     	else {
     		parent.setValue(n.getValue());
     	     return;
     	}
			checkViolation(n);

	}

	private void checkViolation(INode<T, V> node) {
			if(node==root || node==nil)
				return;
			if(node.getParent()==root) {
				root.setColor(false);
				return;
			}
			if(node.getColor()==true && node.getParent().getColor()==true)
				fixTree(node);
			checkViolation(node.getParent());
	}

	private void fixTree(INode<T, V> node) {
        if(node.getParent().getParent().getLeftChild()==node.getParent()) {
        	if(node.getParent().getParent().getRightChild()==nil ||
        		node.getParent().getParent().getRightChild().getColor()==false 
        			) {
        		rotate(node);
        		return;
        	}
        	node.getParent().getParent().getRightChild().setColor(false);

        	
        }
        else {
           	if(node.getParent().getParent().getLeftChild()==nil ||
            		node.getParent().getParent().getLeftChild().getColor()==false 
            			) {
            		rotate(node);
            		return;
            	}
            	node.getParent().getParent().getLeftChild().setColor(false);
           }
    	node.getParent().getParent().setColor(true);
    	node.getParent().setColor(false);
	}

	
	
	private void rotate(INode<T, V> node) {
			if(node.getParent().getLeftChild()==node) {
				if(node.getParent().getParent().getLeftChild()==node.getParent()) {
					rightRotate(node.getParent().getParent());
					node.setColor(true);
					node.getParent().setColor(false);
					if(node.getParent().getRightChild()!=nil)
						node.getParent().getRightChild().setColor(true);
					return;
				}
				rightLeftRotate(node.getParent().getParent());
				node.setColor(false);

			}
			else {
				if(node.getParent().getParent().getRightChild()==node.getParent()) {
					leftRotate(node.getParent().getParent());
					node.setColor(true);
					node.getParent().setColor(false);
					if(node.getParent().getLeftChild()!=nil)
						node.getParent().getLeftChild().setColor(true);
					return;
				}
				leftRightRotate(node.getParent().getParent());
				node.setColor(false);
			
			}
			if(node.getRightChild()!=nil)
				node.getRightChild().setColor(INode.RED);
		
			if(node.getLeftChild()!=nil)
				node.getLeftChild().setColor(INode.RED);
	}

	private void leftRotate(INode<T, V> node) {
		INode <T,V> temp= node.getRightChild();
		node.setRightChild(temp.getLeftChild());
		if(node.getRightChild()!=nil) {
			node.getRightChild().setParent(node);
		}
		if(node.getParent()!=nil) {
			temp.setParent(node.getParent());
			if(node.getParent().getLeftChild()==node) {
				temp.getParent().setLeftChild(temp);
			}
			else {
				temp.getParent().setRightChild(temp);
			}
		}
		else {
			root=temp;
			temp.setParent(nil);
		}
		
		temp.setLeftChild(node);
		node.setParent(temp);
		
	}

	private void leftRightRotate(INode<T, V> node) {
		leftRotate(node.getLeftChild());
		rightRotate(node);
	}

	private void rightRotate(INode<T, V> node) {
		INode <T,V> temp= node.getLeftChild();
		node.setLeftChild(temp.getRightChild());
		if(node.getLeftChild()!=nil) {
			node.getLeftChild().setParent(node);
		}
		if(node.getParent()!=nil) {
			temp.setParent(node.getParent());
			if(node.getParent().getLeftChild()==node) {
				temp.getParent().setLeftChild(temp);
			}
			else {
				temp.getParent().setRightChild(temp);
			}
		}
		else {
			root=temp;
			temp.setParent(nil);
		}
		
		temp.setRightChild(node);
		node.setParent(temp);		
	}

	private void rightLeftRotate(INode<T, V> node) {
		rightRotate(node.getRightChild());
		leftRotate(node);		
	}
     private void transplant(INode<T, V> u,INode<T, V> v) {
    	 if(u.getParent()==nil) {
    		 root=v;
    	 }
    	 else if(u==u.getParent().getLeftChild()){
    		 u.getParent().setLeftChild(v);
    	 }
    	 else
    		 u.getParent().setRightChild(v);
    	 
    	 if(v!=null) {
    	 v.setParent(u.getParent());
    	 }
     }
     
        
     private INode<T,V> minimum(INode<T,V> parent){
    		INode<T,V> node =parent;
    		while(node.getLeftChild()!=nil) {
    			node=node.getLeftChild();
    		}
    				
    	 
    	 return node;
    	  }
 	private  INode<T,V> searchNode(Comparable key) {
		// TODO Auto-generated method stub
		 INode temp=root;
		while(temp!=nil) {
			if(key.compareTo(temp.getKey())==0) {
				return temp;
			}
			else if(key.compareTo(temp.getKey())==1) {
				temp=temp.getRightChild();
			}
			else {
				temp=temp.getLeftChild();
				
			}
		}
		
		return null;
	}
	@Override
	public boolean delete(Comparable key) {
		if(key==null) {
			throw new RuntimeErrorException(null);
		}
		Node<T,V> node=(Node<T, V>) searchNode(key);
			if(node==nil || node==null)
				return false;
			boolean color= node.getColor();
			size--;
			Node<T,V> y =node;
			Node<T,V> x; 
			if(node.getLeftChild()==nil) {
				x=(Node<T, V>) node.getRightChild();
				transplant(node,node.getRightChild());
			}
			else if(node.getRightChild()==nil) {
				x=(Node<T, V>) node.getLeftChild();
				transplant(node,node.getLeftChild());
			}
			else{
				y=(Node<T, V>) minimum(node.getRightChild());
				color=y.getColor();
				x=(Node<T, V>) y.getRightChild();
				if( y.getParent()==node){
					x.setParent(y);
				}
				else {
					transplant(y,y.getRightChild());
					y.setRightChild(node.getRightChild());
					y.getRightChild().setParent(y);
 				}
				transplant(node,y);
				y.setLeftChild(node.getLeftChild());
				y.getLeftChild().setParent(y);
				y.setColor(node.getColor());
				
				}
			if (color==Node.BLACK) {
				fixDelete(x);
				
			}
			
		
		 return true;
	}

	private void fixDelete(Node<T, V> x) {
		nil.setColor(Node.BLACK);
		while((x!=root &&  x.getColor()==Node.BLACK)) {
			if(x.getParent().getLeftChild()==x) {
				Node<T,V> w=(Node<T, V>) x.getParent().getRightChild();
				if(w.getColor()==Node.RED) {
					w.setColor(Node.BLACK);
					x.getParent().setColor(Node.RED);
					leftRotate(x.getParent());
					w=(Node<T, V>) x.getParent().getRightChild();
				}
			
				  if((w.getLeftChild().getColor()==Node.BLACK && w.getRightChild().getColor()==Node.BLACK) ){
					w.setColor(Node.RED);
					x=(Node<T, V>) x.getParent();
				}
				else {
					if(w.getRightChild().getColor()==Node.BLACK) {
					w.getLeftChild().setColor(Node.BLACK);
					w.setColor(Node.RED);
					rightRotate(w);
					w=(Node<T, V>) x.getParent().getRightChild();
				}
				w.setColor(x.getParent().getColor());
				x.getParent().setColor(Node.BLACK);
				w.getRightChild().setColor(Node.BLACK);
				leftRotate(x.getParent());
				x=(Node<T, V>) root;}
			}
			else {
				Node<T,V> w=(Node<T, V>) x.getParent().getLeftChild();
				if(w.getColor()==Node.RED) {
					w.setColor(Node.BLACK);
					x.getParent().setColor(Node.RED);
					rightRotate(x.getParent());
					w=(Node<T, V>) x.getParent().getLeftChild();
				}
//				if(w==nil) {
//				System.out.println("fuck of");}				
				if(w.getRightChild().getColor()==Node.BLACK && w.getLeftChild().getColor()==Node.BLACK) {
					w.setColor(Node.RED);
					x=(Node<T, V>) x.getParent();
				}
				else {
					if(w.getLeftChild().getColor()==Node.BLACK) {
					w.getRightChild().setColor(Node.BLACK);
					w.setColor(Node.RED);
					leftRotate(w);
					w=(Node<T, V>) x.getParent().getLeftChild();
				}
				w.setColor(x.getParent().getColor());
				x.getParent().setColor(Node.BLACK);
				w.getLeftChild().setColor(Node.BLACK);
				rightRotate(x.getParent());
				x=(Node<T, V>) root;}
			}
			
		}
		x.setColor(Node.BLACK);

	}

}
