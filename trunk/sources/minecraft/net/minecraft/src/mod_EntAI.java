package net.minecraft.src;

import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.*; 

import net.minecraft.client.Minecraft;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;


public class mod_EntAI extends BaseMod implements Runnable {
	public static Minecraft mc = ModLoader.getMinecraftInstance();
    public static Field minecraftTicksRan = null;
    
    public static int timeout = 0;
    public static String msg;
    public static int color;
    public static int defaultColor = 0xFF0000;//16777215;
    //public static int 
    
    public static long ticksRan = 0;
    public static EntityNode nodeRef = null;
    
    public static EntityNode entNodes[] = new EntityNode[1024 /*GL_FRONT_LEFT*/];
    public static int entNodeCount = 0;
    public static WPNode nodes[];
    
    public static WPNode lastNode = null;
    public static int nodeCount = 0;
    
    public static int maxPathSize = 64;
    public static int pathSize;
    
    public static boolean pathFound = false;
    
    public static boolean WPNodesInit = true;
    
    public static boolean QueueWPNodesInit = false;
    public static long delayStart = -1;
    
    public static LinkedList nodeOrderData = new LinkedList();
    public static LinkedList nodeData = new LinkedList();
    
    public static int maxNodeLOSDist = 12;
    
    //public static float maxNodeLOSYDiff = 1.0F;
    public static float maxNodeLOSYDiff = 0.8F;
    public static float maxStairNodeLOSXYDiff = 2.0F;
    
    public static int maxDistToPlayer = 16;
    public static float maxNodeYDistToPlayer = 1.1F;
    
    

    
    public static boolean autoRespawn = false;
    
    public static boolean enhance = false;
    
    public static int dbg_WPFP = 0;
    public static int dbg_WPNP = 0;
    
    public static boolean dbg_AI = false;
    //public static Minecraft mc;
    
    public static boolean doorNoClip = false;
    
    public static EntityPlayer playerRef;
    
    public void run() {
	    try {
	      while (true) {
	        if (this.mc == null) {
	          this.mc = ModLoader.getMinecraftInstance();
	        }

	        if (this.mc == null) {
	          Thread.sleep(1000L);
	          continue;
	        }

	        if (this.mc.thePlayer == null) {
	          Thread.sleep(1000L);
	          continue;
	        }

	        //worldRef = this.mc.e;
        	playerRef = this.mc.thePlayer;
        	//hq.lo.b
        	//keys = new int[] {mc.y.k.b, mc.y.l.b, mc.y.m.b, mc.y.n.b, mc.y.o.b};
        	//keys = new int[] {mc.x.l.b, mc.x.m.b, mc.x.n.b, mc.x.o.b, mc.x.p.b};
        	
	        //this.mc.g.bi = (isEnabled() ? 1.0F : 0.5F);
	        Thread.sleep(1000L);
	      }
	    } catch (Throwable e) {
	      e.printStackTrace();
	    }
	  }
    
    public mod_EntAI() {
    	ModLoader.RegisterEntityID(EntityFriend.class, "EntityFriend", ModLoader.getUniqueEntityId());
    	ModLoader.RegisterEntityID(EntityNode.class, "EntityNode", ModLoader.getUniqueEntityId());
		ModLoader.RegisterEntityID(EntityHellSheep.class, "HellSheep" , ModLoader.getUniqueEntityId());
		ModLoader.RegisterEntityID(EntityBunny.class, "Bunny" , ModLoader.getUniqueEntityId());
		enhance = true;
		new Thread(this).start();
    }
    
    public static void newWorldLoaded() {
    	//System.out.println("New World Load Init");
    	ticksRan = 0;
    	QueueWPNodesInit = true;
    	mc.theWorld.zombHUD = true;
    	//mc.theWorld.newWave = true;
    }
    
    public void AddRenderer(Map map)
    {    	
    	map.put(EntityFriend.class, new RenderBiped(((ModelBiped) (new ModelBiped())), 0.5F));
    	map.put(EntityNode.class, new RenderNode());
		map.put(EntityHellSheep.class, new RenderHellSheep(new ModelSheep2(), new ModelSheep1(), 0.7F));
		map.put(EntityBunny.class, new RenderBunny(new ModelBunny(), 0.7F));
    }
    
    public String Version() {
    	return "for ZombieCraft / MC b1.3_01";
    }
    
    public static void displayMessage(String paramString, int paramInt)
    {
      msg = paramString;
      timeout = 5;
      color = paramInt;
    }
    
    public static void displayMessage(String paramString) {
      displayMessage(paramString, defaultColor);
    }
    
    private static void loadNodes()
    {
    	Properties properties = new Properties();
    	try
    	{
    		String propertiesPath = mod_SdkZombieCraft.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
    		propertiesPath = propertiesPath.substring(0, propertiesPath.lastIndexOf('/') + 1);
    		propertiesPath += "mod_SdkZombieCraft.properties";
    		
    		properties.load(new FileInputStream(propertiesPath));
    		String property;
    		
    		property = properties.getProperty("reloadKey");
    		int tempReloadKey = Keyboard.getKeyIndex(property.trim().toUpperCase());
    		if(tempReloadKey == Keyboard.CHAR_NONE)
    		{
    			System.out.println("Error binding reload key. Bad key name: " + property.trim());
    		}
    		else
    		{
    			//reloadKey = tempReloadKey;
    		}
        }
        catch(Exception e)
        {
        	System.out.println("Error loading properties.");
			e.printStackTrace();
        }
    }
    
    public void OSDHook(Minecraft minecraft, boolean menuOpen)
	{
		/*SdkZcTools.addSounds();
		handleDrawing(minecraft, menuOpen);
		handleBreakables();
		*/
    	
    	if (timeout > 0 && msg != null) {
    		ScaledResolution kh1 = new ScaledResolution(minecraft.displayWidth,minecraft.displayHeight);
            int width = kh1.getScaledWidth();
            int height = kh1.getScaledHeight();
            int msgwidth = minecraft.fontRenderer.getStringWidth(msg);
            //game.o.a(msg, (width-msgwidth)/2, height/2, 0xffffff);
            minecraft.fontRenderer.drawStringWithShadow(msg, 3, 85, 0xffffff);
            timeout--;
        }
    	
		try
		{
			tick(menuOpen);
		}
		catch (IllegalAccessException illegalaccessexception)
		{
			ModLoader.ThrowException("An impossible error has occured!", illegalaccessexception);
		}
	}
    
    public void tick(boolean menuOpen) throws IllegalAccessException
	{
    	ticksRan = mc.ticksRan;
    	
    	if (QueueWPNodesInit && delayStart == -1) {
    		delayStart = 40;
    	}
    	
    	if (delayStart > -1) {
    		delayStart--;
    	}
    	
    	if (QueueWPNodesInit && delayStart == 0) { 
    		QueueWPNodesInit = false;
    		delayStart = -1;
    		WPNodesInit = true;
    		if (dbg_AI) System.out.println("Nodes ReInit: " + entNodeCount);
    	}
    	
    	
    	
    	
    
		if (WPNodesInit) {
			WPNodesInit = false;
			unloadNodes();
	    	if (nodeRef != null) {
	    		nodeRef.isLoaded = false;
	    	}
			for (int i = 0; i < entNodeCount; i++) {
				//if (!entNodes[i].isLoaded) {
					
					/*System.out.print("entNodeCount: ");
					System.out.println(mod_EntAI.entNodeCount);*/
					entNodes[i].getLOSNodes();
					entNodes[i].isLoaded = true;
				//}
			}
		}
		
		
    
	}
    
    
    
    public static void setNodeSize(float size) {
    	for (int i = 0; i < entNodeCount; i++) {
    		entNodes[i].setSize(size,size);
    	}
    	//each node isUsed = false; do efficiently
    }
    
    public static void unloadNodes() {
    	for (int i = 0; i < entNodeCount; i++) {
    		entNodes[i].isLoaded = false;
    	}
    	//each node isUsed = false; do efficiently
    }
    
    public static void resetNodes() {
    	for (int i = 0; i < entNodeCount; i++) {
    		entNodes[i].isUsed = false;
    	}
    	//each node isUsed = false; do efficiently
    }
    
    public static EntityNode[] getPathToTarget(EntityLiving ent, EntityLiving target, EntityNode firstNode) {
    	boolean doorPF = true;
    	if (ent instanceof EntityFriend) {
    		doorPF = false;
    	}
    	int stopCounter = 0;
    	EntityNode curNode = firstNode;
    	WPNode curWPNode = null;
    	pathFound = false;
    	lastNode = null;
    	resetNodes();
    	WPNode firstWPNode = new WPNode(curNode); 
    	firstWPNode.distToTarg = curNode.getDistanceToEntity(target);
    	
    	nodes = new WPNode[512];
    	nodes[nodeCount] = firstWPNode;
    	
    	nodeOrderData.clear();
    	nodeOrderData.add(firstWPNode);
    	nodeData.clear();
    	nodeData.add(firstWPNode);
    	pathfindNodes(target,doorPF);
    	
    	EntityNode Path[] = null;
    	
    	
    	
    	//return Path[];
    	
    	if (pathFound) {
    		/*System.out.print("pathFound: ");
        	System.out.println(pathFound);*/
    		if (lastNode != null) {
    			
    			WPNode iterNode = null;
    			
    			//System.out.print("lastNode: ");
            	//System.out.println(lastNode);
    			EntityNode tPath[] = new EntityNode[maxPathSize];
    			curWPNode = lastNode;
    			
    			tPath[0] = curWPNode.entNode;
    			
    			
    			
    			
    			int curNodeNum = 1;
    			pathSize = 0;
    			
    			/*System.out.print("curWPNode: ");
            	System.out.println(curWPNode);*/
            	
            	
    			
    			while (curWPNode != curWPNode.prevNode) {
    				curWPNode = curWPNode.prevNode;
    				if (curWPNode.entNode == null) {
    					//System.out.println("found null entNode");
    					break;
    				}
    				
    				tPath[curNodeNum] = curWPNode.entNode;
    				
    				pathSize++;
    				curNodeNum++;
    				if (curNodeNum > maxPathSize) {
    					//System.out.println("path get fail");
    					break;
    				}
    			}
    			
    			if (dbg_AI) { System.out.print("pathSize: ");
            	System.out.println(pathSize); }
            	/*
    			System.out.print("tPath: ");
            	System.out.println(tPath.length);*/
    			
    			
            	
    			
    			Path = new EntityNode[pathSize+1];
    	    	
    			int i = 0;
    	    	for (i = 0; i < pathSize; i++) {
    	    		Path[i] = tPath[pathSize-i];
    	    		/*System.out.print("x: ");
                	System.out.print(Path[i].posX);
                	System.out.print(" - z: ");
                	System.out.println(Path[i].posZ);*/
    	    	}
    			
    	    	Path[i] = tPath[0];
    	    	
    	    	/*System.out.print("x: ");
            	System.out.print(Path[i].posX);
            	System.out.print(" - z: ");
            	System.out.println(Path[i].posZ);*/
    		}
    	}
    	
    	if (Path != null) {
    		/*System.out.print("Path: ");
        	System.out.println(Path.length); */   		
    	} else {
    		//System.out.print("Null Path");
        	
    	}
    	
    	
    	
    	/*while (!target.canEntityBeSeen(curNode)) {
    		
    		
    		
    		if (stopCounter > 1000) {
    			System.out.println("aborting WPPF");
    			break;
    		} stopCounter++;
    	}*/
    	
    	return Path;
    }
    
    public static void pathfindNodes(EntityLiving target, boolean doorPF) {
    	EntityNode tNode = null;
    	WPNode curNode = null;
    	int newNodeCount = 0;
    	while (newNodeCount < 1000) {
    		if (nodeOrderData.size() == 0) {
    			//fail
    			if (dbg_AI) System.out.print("no end");
    			//WPNode tWPNode = new WPNode(tNode,curNode);
            	
    			
            	//tWPNode.distToTarg = tNode.getDistanceToEntity(target);
				//insertNode(target, tWPNode);
				
    			lastNode = closestNode();
    			pathFound = true;
    			
    			/*System.out.print("x: ");
            	System.out.println(lastNode.entNode.posX);
            	System.out.print("z: ");
            	System.out.println(lastNode.entNode.posZ);*/
    			
    			return;
    		}
    		curNode = (WPNode)nodeOrderData.getFirst();
    		nodeOrderData.removeFirst();
    		
    		for (int i = 0; i < curNode.entNode.losNodeCount; i++) {
    			
    			int tryID = target.rand.nextInt(curNode.entNode.losNodeCount);
    			tNode = curNode.entNode.losNodes[tryID];
    			int tryCount = 0;
    			while (tNode.isTempUsed && tryCount < 20) {
    				tryID = target.rand.nextInt(curNode.entNode.losNodeCount);
        			tNode = curNode.entNode.losNodes[tryID];
        			tryCount++;
    			}
    			if (!tNode.isTempUsed) {
    				tNode.isTempUsed = true;
    			}
    			///Node = curNode.entNode.losNodes[i];
    			if (target.canSeeNode(tNode) && target.getXZDistanceToEntity(tNode) < maxDistToPlayer) {
    				if (dbg_AI) System.out.print("?! - ");
    				if (MathHelper.abs((float)((target.posY-target.yOffset) - (tNode.posY-tNode.yOffset))) < maxNodeYDistToPlayer) {
    					if (dbg_AI) System.out.print("found end: ");
        				
                    	//System.out.println(tNode);
                    	/*System.out.print("x: ");
                    	System.out.println(tNode.posX);
                    	System.out.print("z: ");
                    	System.out.println(tNode.posZ);*/
                    	
                    	
                    	WPNode tWPNode = new WPNode(tNode,curNode);
                    	
                    	tWPNode.distToTarg = tNode.getDistanceToEntity(target);
        				insertNode(target, tWPNode);
        				
            			lastNode = tWPNode;
            			pathFound = true;
            			
            			
            			return;
    				}
    			}
    				
    			if (!doorPF) {
    				if (!curNode.entNode.canEntityBeSeenmDoors((Entity)tNode)) {
    					continue;
    				}
    			}
        		
    			
    			if (!tNode.isUsed) {
    				/*System.out.print("tNode loaded: ");
                	System.out.println(nodeOrderData.size());*/
        			tNode.isUsed = true;
    				newNodeCount++;
    				
    				WPNode tWPNode = new WPNode(tNode,curNode);
    				if (tWPNode == curNode) {
    					//System.out.println("WHAT?!?!");
    				}
    				/*System.out.println("new node");
                	System.out.print("tWPNode: ");
                	System.out.println(tWPNode);
                	System.out.print("prevNode: ");
                	System.out.println(curNode);*/
                	
    				tWPNode.distToTarg = tNode.getDistanceToEntity(target);
    				insertNode(target, tWPNode);
    			}
    		}
    		for (int i = 0; i < curNode.entNode.losNodeCount; i++) {
    			curNode.entNode.losNodes[i].isTempUsed = false;
    		}
    	}
    	if (dbg_AI) System.out.println("Over 1000 node searches, fail");
    }
    
    public static void insertNode(EntityLiving target, WPNode node) {
    	
    	nodeData.addLast(node);
    	
    	ListIterator lIt = nodeOrderData.listIterator();
    	WPNode tNode = null;
    	while (lIt.hasNext()) {
    		tNode = (WPNode)lIt.next();
    		if (node.distToTarg < tNode.distToTarg) {
    			lIt.add(node);
    			return;
    		}
    	}
    	
    	nodeOrderData.addLast(node);
    	
    }
    
    public static WPNode closestNode() {
    	ListIterator lIt = nodeData.listIterator();
    	WPNode tNode = null;
    	WPNode bestNode = null;
    	float bestDist = 9999;
    	while (lIt.hasNext()) {
    		tNode = (WPNode)lIt.next();
    		if (tNode.distToTarg < bestDist) {
    			bestDist = tNode.distToTarg; 
    			bestNode = tNode;
    		}
    	}
    	
    	return bestNode;
    }
    
    /*public static void pathfindNodes(EntityLiving target, WPNode node) {
    	EntityNode tNode = null;
    	int newNodeCount = 0;
    	for (int i = 0; i < node.entNode.losNodeCount; i++) {
    		tNode = node.entNode.losNodes[target.rand.nextInt(node.entNode.losNodeCount)];
    		if (target.canEntityBeSeen(tNode) && target.getDistanceToEntity(tNode) < 12F) {
    			lastNode = node;
    			pathFound = true;
    			return;
    		}
    		
    		if (!tNode.isUsed) {
    			//System.out.print("tNode loaded: ");
            	//System.out.println(tNode);
    			tNode.isUsed = true;
    			newNodeCount++;
    			WPNode tWPNode = new WPNode(tNode,node);
    			nodeCount++;
    			nodes[nodeCount] = tWPNode;
    			
    			pathfindNodes(target,tWPNode);
    		}
    		
    		
    	}
    	
    	if (newNodeCount == 0) {
    		node.entNode.isUsed = false;
    	}
    }*/
    
    
}







