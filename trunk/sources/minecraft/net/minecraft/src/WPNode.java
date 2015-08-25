package net.minecraft.src;

import java.util.List;
import java.util.Random;

//import EntityNode.class;

public class WPNode
{
	public EntityNode entNode;
	public WPNode prevNode;
	public static float distToTarg = 9999; 
	
public WPNode(EntityNode refNode) {
		
		entNode = refNode;
		prevNode = this;
		//prevNode = null;
		
		
	}
	
	public WPNode(EntityNode refNode, WPNode prevNodeVar) {
		
		entNode = refNode;
		prevNode = prevNodeVar;
		
		/*System.out.println("NEW NODE___________________________________");
		
		System.out.print("new: ");
    	System.out.println(this);
    	
    	System.out.print("prev: ");
    	System.out.println(prevNode);
		
		WPNode temp = this;
		
		System.out.println("START LIST_____________");
    	
		try {
			while (temp != temp.prevNode) {
				System.out.print("node: ");
		    	System.out.println(temp);
		    	System.out.print("node2: ");
		    	System.out.println(temp.prevNode);
		    	System.out.print("node3: ");
		    	System.out.println(temp.prevNode.prevNode);
		    	System.out.print("node4: ");
		    	System.out.println(temp.prevNode.prevNode.prevNode);
		    	temp = temp.prevNode;
			}
			
			System.out.print("duplicate: ");
	    	System.out.print(temp);
	    	System.out.print(" - node: ");
	    	System.out.println(temp.prevNode);
		} catch (Exception ex) {
			
		}
		
		System.out.println("END NODE___________________________________");*/
    	
    	
		
		
	}
	
}