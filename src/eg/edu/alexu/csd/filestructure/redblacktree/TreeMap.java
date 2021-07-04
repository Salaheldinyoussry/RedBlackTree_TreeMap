package eg.edu.alexu.csd.filestructure.redblacktree;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.Set;
import java.util.TreeSet;

import javax.management.RuntimeErrorException;
import javax.swing.text.html.HTMLDocument.Iterator;

public class TreeMap<T extends Comparable<T>, V> extends AbstractTree<T,V> implements ITreeMap {
	
	
	@Override
	public Entry ceilingEntry(Comparable key) {
		if(key==null) {
			throw new RuntimeErrorException(null);
		}
		INode<T,V> ceil=ceil(key);   
	
		return Map.entry(ceil.getKey(), ceil.getValue());
		//return  Map.Entry<Integer,Integer>();
	}
//	private Entry findMinforN(INode<T,V> root,T N) {
//		if ((root.getLeftChild()==null || root.getLeftChild().isNull()) && 
//		        (root.getRightChild()==null && root.getRightChild().isNull()) && root.getKey().compareTo(N)<0 )
//		        return null;
//		  
//		    // If node's value is greater than N and left value
//		    // is null or smaller then return the node value
//		    if ((root.getKey().compareTo(N) >=0 &&( root.getLeftChild()==null ||root.getLeftChild().isNull())) || 
//		        (root.getKey().compareTo(N) >=0 && root.getLeftChild().getKey().compareTo(N)<0 ))
//		        return Map.entry(root.getKey(), root.getValue());
//	
//		  
//		    // if node value is smaller than N search in the
//		    // right subtree
//		    
//		    if (root.getKey().compareTo(N) <= 0)
//		        return findMinforN(root.getRightChild(), N);
//		  
//		    // if node value is greater than N search in the
//		    // left subtree
//		    else
//		        return findMinforN(root.getLeftChild(), N);
//	
//	}
	
	private INode<T,V> ceil(Comparable key) {
		INode<T,V> root=(Node<T, V>) getRoot(); 
		INode<T,V> ceil = null;	
		while (root != null)
        {
            // if a node with the desired value is found, both floor and ceil is equal
            // to the current node
            if (root.getKey().compareTo((T) key)==0)
            {
//                obj.setFloor(root);
//                obj.setCeil(root);
            	ceil=root;
                break;
                
                
            }
            
        
 
            // if the given key is less than the root node, visit the left subtree
            else if (key.compareTo(root.getKey()) < 0)
            {
                // update ceil to the current node before visiting the left subtree
                //obj.setCeil(root);
                ceil=root;
            	root = root.getLeftChild();
            }
 
            // if the given key is more than the root node, visit the right subtree
            else {
                // update floor to the current node before visiting the right subtree
              //  obj.setFloor(root);
                root = root.getRightChild();
            }}
		
  return ceil;
	}
	
	
	@Override
	public Comparable ceilingKey(Comparable key) {
		if(key==null) {
			throw new RuntimeErrorException(null);
		}
		//////
	
	
		//////
		return  ceil(key).getKey();
	}

	

	
	
	

	@Override
	public void clear() {
		super.clear();
	}

	@Override
	public boolean containsKey(Comparable key) {
		// TODO Auto-generated method stub
		if(key==null) {
			throw new RuntimeErrorException(null);
		}
		return contains((T) key);
	}
     boolean flagg=false;
	 private void preorder(INode<T,V> node,Object value)
	    {
	        if (node.isNull())
	            return;

	        
	        if(node.getValue().equals( value)) {
	        	flagg=true;
	        	return;}
	        preorder(node.getLeftChild(), value);
	 
	        preorder(node.getRightChild(), value);
	    }
	
	@Override
	public boolean containsValue(Object value) {
		if(value==null) {
			throw new RuntimeErrorException(null);
		}
		// TODO Auto-generated method stub
		flagg=false;
		preorder(getRoot(),value);
		return flagg;
		
	}
	final class myEntry<T extends Comparable<T>, V>  implements Map.Entry<T, V>{
		  private  T key;
		  private V value;
		
		 public myEntry(T key ,V val) {
			 this.key=key;
			 this.value=val;
		 }
		  @Override
		public T getKey() {
			return this.key;
		}
		@Override
		public V getValue() {
			// TODO Auto-generated method stub
			return this.value;
		}
		@Override
		public V setValue(V value) {
			// TODO Auto-generated method stub
			this.value=value;
			return value;
		}
		public void setKey(T key) {
			// TODO Auto-generated method stub
			this.key=key;
		}	
		  
	}
	
	Set<Map.Entry<T, V>> s;
	@Override
	public Set entrySet() {
		s=new HashSet<Map.Entry<T,V>>();
		preorder2(getRoot());
		//Set<Map.Entry<T, V>> sortedSet = new TreeSet<>(s);
	       TreeSet<Map.Entry<T, V>> treeSet
           = new TreeSet<Map.Entry<T, V>>(new Mapc());
	       treeSet.addAll(s);
	       
	       
		return treeSet;
	}
	
	class Mapc implements Comparator<Map.Entry<T, V>> {
		  
	    // defining compare method
	    public int compare(Map.Entry<T, V> obj1, Map.Entry<T, V> obj2)
	    {
	        return obj1.getKey().compareTo((T) obj2.getKey());
	    }
	}
	
	 private void preorder2(INode<T,V> node)
	    {
	        if (node.isNull())
	            return;
	 
	        /* first print data of node */
	      //  System.out.print(node.key + " ");
	        	s.add(Map.entry(node.getKey(), node.getValue()));
	        /* then recur on left sutree */
	        preorder2(node.getLeftChild());
	 
	        /* now recur on right subtree */
	        preorder2(node.getRightChild());
	    }
	

	@Override
	public Entry firstEntry() {
		INode<T,V> root=(Node<T, V>) getRoot();   
		if(root.isNull())
			return null;
		while(!root.getLeftChild().isNull()) {
			root=root.getLeftChild();
		}
		if(root.isNull()) {
			return null;
		}
		return new myEntry(root.getKey(), root.getValue());
	}

	@Override
	public Comparable firstKey() {
		INode<T,V> root=(Node<T, V>) getRoot();   
		if(root.isNull())
			return null;
		while(!root.getLeftChild().isNull()) {
			root=root.getLeftChild();
		}
		if(root.isNull()) {
			return null;
		}
		return root.getKey();
	}

	private INode<T,V> findMaxforN(INode<T,V> root, T N)
	{
	    // Base cases
	    if (root == null)
	        return null;
	    if (root.getKey().compareTo(N) == 0)
	        return root;
	 
	    // If root's value is smaller, try in right
	    // subtree
	    else if (root.getKey().compareTo(N)   < 0) {
	         INode<T,V> node = findMaxforN(root.getRightChild(), N);
	        if(node==null)
	            return root;
	        else
	            return node;
	    }
	 
	    // If root's key is greater, return value
	    // from left subtree.
	    else if (root.getKey().compareTo(N) > 0)
	        return findMaxforN(root.getLeftChild(), N);
	    return null;
	}
	
	
	@Override
	public Entry floorEntry(Comparable key) {
		if(key==null) {
			throw new RuntimeErrorException(null);
		}
		// TODO Auto-generated method stub
		INode<T,V> n=findMaxforN(getRoot(),(T) key);
		if(n==null) {
		return null;}
		return new myEntry(n.getKey() ,n.getValue() );
	}

	@Override
	public Comparable floorKey(Comparable key) {
		if(key==null) {
			throw new RuntimeErrorException(null);
		}
		INode<T,V> n=findMaxforN(getRoot(),(T) key);
		if(n==null) {
		return null;}
		return n.getKey();
	}

	@Override
	public Object get(Comparable key) {
		if(key==null) {
			throw new RuntimeErrorException(null);
		}
		// TODO Auto-generated method stub
		return search((T) key);
	}
	ArrayList<Map.Entry<T, V>> a;	 
	 
	 
	 private void orderTraversal(boolean b,T key)
	    {
	        Queue<INode<T,V>> queue = new LinkedList<INode<T,V>>();
	        queue.add(getRoot());
	        while (!queue.isEmpty())
	        {
	 
	            INode<T,V> tempNode = queue.poll();
		        
	            if(tempNode.getKey().compareTo(key)<0) {
		        	a.add(Map.entry(tempNode.getKey() ,tempNode.getValue() ));
		        }
		        if(b) {
		        	if(tempNode.getKey().compareTo(key)==0) {
			        	a.add( Map.entry(tempNode.getKey() ,tempNode.getValue() ));
			        }
		        }	 
	            /*Enqueue left child */
	            if (tempNode.getLeftChild() != null && tempNode.getLeftChild() != nil) {
	                queue.add(tempNode.getLeftChild());
	            }
	 
	            /*Enqueue right child */
	            if (tempNode.getRightChild() != null && tempNode.getRightChild() != nil) {
	                queue.add(tempNode.getRightChild());
	            }
	        }}
	 
	 
	 
	 
	@Override
	public ArrayList headMap(Comparable toKey) {
		if(toKey==null) {
			throw new RuntimeErrorException(null);
		}
		a=new ArrayList<Map.Entry<T, V>>();
		
		orderTraversal(false,(T) toKey);
		Collections.sort(a,new Mapc());
		return a;
	}

	@Override
	public ArrayList headMap(Comparable toKey, boolean inclusive) {
		if(toKey==null) {
			throw new RuntimeErrorException(null);
		}
		a=new ArrayList<Map.Entry<T, V>>();
		orderTraversal(inclusive,(T) toKey);
		Collections.sort(a,new Mapc());
		return a;	// TODO Auto-generated method stub
	}

	Set<T> ss;
	 private void preorder4(INode<T,V> node)
	    {
	        if (node.isNull())
	            return;
	 
	      //  System.out.print(node.key + " ");
	      
	        
	        ss.add(node.getKey());
	        /* then recur on left sutree */
	        preorder4(node.getLeftChild());
	 
	        preorder4(node.getRightChild());
	    }
	@Override
	public Set keySet() {
		ss=new TreeSet<T>();
		preorder4(getRoot());
		return ss;
	}

	@Override
	public Entry lastEntry() {
		INode <T,V> root=getRoot();
		if(root.isNull())
			return null;
		while(!root.getRightChild().isNull()) {
			root=root.getRightChild();
		}
		if(root.isNull()) {
			return null;
		}
		
		return new myEntry(root.getKey(), root.getValue());
	}

	@Override
	public Comparable lastKey() {
		INode <T,V> root=getRoot();
		if(root.isNull())
			return null;
		while(!root.getRightChild().isNull()) {
			root=root.getRightChild();
		}
		if(root.isNull()) {
			return null;
		}
		
		return root.getKey();
	}

	@Override
	public Entry pollFirstEntry() {
		// TODO Auto-generated method stub
		myEntry<T, V> m=(TreeMap<T, V>.myEntry<T, V>) this.firstEntry();
		if(m==null) {
			return null;
		}
		delete(m.getKey());		
		return m;
	}

	@Override
	public Entry pollLastEntry() {
		myEntry<T, V> m=(TreeMap<T, V>.myEntry<T, V>) this.lastEntry();
		if(m==null) {
			return null;
		}
		delete(m.getKey());		
		return m;
	}

	@Override
	public void put(Comparable key, Object value) {
		if(key==null || value==null) {
			throw new RuntimeErrorException(null);
		}
		insert((T)key, (V) value);
	}

	@Override
	public void putAll(Map map) {
		if(map==null) {
			throw new RuntimeErrorException(null);
			
		}
		  for(Object  m: map.entrySet()){  
			 Map.Entry<T, V> x= (Entry<T, V>) m; 
				if(x.getKey()==null || x.getValue()==null) {
					throw new RuntimeErrorException(null);
				}
			  this.put(x.getKey(), x.getValue());
			  } 
	}

	@Override
	public boolean remove(Comparable key) {
		// TODO Auto-generated method stub
		if(key==null) {
			throw new RuntimeErrorException(null);
		}
		return delete((T) key);
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return size;
	}
	
	
	private Collection<V> ll1;
	 private void preorder55(INode<T,V> node)
	    {
	        if (node.isNull())
	            return;
	 
	      //  System.out.print(node.key + " ");
	      
	        
	        ll1.add(node.getValue());
	        /* then recur on left sutree */
	        preorder55(node.getLeftChild());
	 
	        preorder55(node.getRightChild());
	    }
	@Override
	public Collection values() {
		ll1=new ArrayList<V>();
		
		preorder55(super.getRoot());
		
		return ll1;
	}

}
