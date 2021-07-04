package eg.edu.alexu.csd.filestructure.redblacktree;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Random;
import java.util.TreeMap;
import java.util.Map.Entry;

import javax.management.RuntimeErrorException;

import org.junit.Assert;

public class Main {
public static void main(String ars[]) {
//
//  RedBlackTree<Integer,Integer> t=new 	RedBlackTree<Integer,Integer>();
//   t.insert(41, 41);
//   t.insert(38, 38);
//   t.insert(31, 31);
//   t.insert(12, 12);
//   t.insert(19, 19);
//   t.insert(8, 8);
//
//   System.out.println(t.delete(8));
//   System.out.println(t.delete(12));
//  // System.out.println(t.delete(19));
//  // System.out.println(t.delete(31));
//   //System.out.println(t.delete(38));
//  // System.out.println(t.delete(38));
//   
//  Node<Integer ,Integer> n= (Node<Integer, Integer>) t.getRoot();
////   Node<Integer ,Integer> n1= new   Node<Integer ,Integer>();
////n.setKey(56);
////n1.setKey(56);
////	if(n.getKey().compareTo(n1.getKey())>0) {
////		System.out.println(1);
////	}
////   
////	else if (n.getKey().compareTo(n1.getKey())==0) {
////		System.out.println(2);
////
////	}
////	else {
////		System.out.println(3);
////
////	}
//	
//   System.out.println(n.getValue()+"  "+n.getColor());
//   System.out.println(n.getLeftChild().getValue()+"  "+n.getLeftChild().getColor());
//   
//   
//   System.out.println(n.getRightChild().getValue()+"  "+n.getRightChild().getColor());
//  // System.out.println(n.getLeftChild().getLeftChild().getValue() +"  "+ n.getLeftChild().getLeftChild().getColor());
//   System.out.println(n.getLeftChild().getRightChild().getValue() +"  "+ n.getLeftChild().getRightChild().getColor());
//  
//  // System.out.println(n.getLeftChild().getLeftChild().getLeftChild().getValue() +"  "+ n.getLeftChild().getLeftChild().getLeftChild().getColor());
//
//   
//   // System.out.println(n.getValue());

	ITreeMap<Integer, String> treemap = (ITreeMap<Integer, String>) TestRunner.getImplementationInstanceForInterface(ITreeMap.class);
	
	try {
		List<Integer> list = new ArrayList<>();
		Random r = new Random();
		for (int i = 0; i < 1000; i++) {
			int key = r.nextInt(1000000);
			list.add(key);
			treemap.put(key, "soso" + key);
		}
		Collections.shuffle(list);
		String key = "soso" + list.get(r.nextInt(list.size()));
		Assert.assertTrue(treemap.containsValue(key));
	} catch (Throwable e) {
		TestRunner.fail("Fail in containsValue", e);
	}
}



}

