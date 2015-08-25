package net.minecraft.src;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 


public class InventoryPlayer
    implements IInventory
{

    public InventoryPlayer(EntityPlayer entityplayer)
    {
        mainInventory = new ItemStack[36];
        armorInventory = new ItemStack[4];
        currentItem = 0;
        inventoryChanged = false;
        player = entityplayer;
        firstGun = null;
		secondGun = null;
    }
    
    public int getOptimalBuySlot(InventoryPlayer inventory, Item buyItem, Block buyBlock) {
    	
    	/*System.out.print("buyItem: ");
    	System.out.println(buyItem);
    	
    	System.out.print("buyBlock: ");
    	System.out.println(buyBlock);*/
    	
    	if (buyItem == mod_ZombieCraft.frienddrink || buyItem == mod_ZombieCraft.jugg || buyItem == mod_ZombieCraft.speed || buyItem instanceof ItemAbility) {
    		return 8; //doesnt matter, item to get auto used
    	}
    	
    	ItemStack tStack = null;
    	Item itemInSlot = null;
    	
		if (buyBlock == mod_ZombieCraft.betty || buyItem instanceof SdkZcItemGrenadeStun) {
			tStack = inventory.getStackInSlot(2);
    		if (tStack == null) {
    			return 2;
    		}
    		tStack = inventory.getStackInSlot(3);
    		if (tStack == null) {
    			return 3;
    		}
    		tStack = inventory.getStackInSlot(2);
    		if (tStack != null) {
    			if (tStack.stackSize < 4) { 
    				return 2;
    			}
    		}
    		tStack = inventory.getStackInSlot(3);
    		if (tStack != null) {
    			if (tStack.stackSize < 4) { 
    				return 3;
    			}
    		}
    		return -1;
    	}
		
		
		
		if (buyItem instanceof ItemSword || buyItem instanceof ItemLSword) {
			tStack = inventory.getStackInSlot(0);
    		if (tStack == null || inventory.currentItem == 0) {
    			return 0;
    		}
    		tStack = inventory.getStackInSlot(1);
    		if (tStack == null || inventory.currentItem == 1) {
    			return 1;
    		}
    		
    		return -1;
    	}
		
		if (buyItem instanceof SdkZcItemGun) {
			
			tStack = inventory.getStackInSlot(0);
			if (tStack != null) {
				if (tStack.getItem() instanceof SdkZcItemGun) {
					firstGun = (SdkZcItemGun)tStack.getItem();
				}
			}
			tStack = inventory.getStackInSlot(1);
			if (tStack != null) {
				if (tStack.getItem() instanceof SdkZcItemGun) {
					secondGun = (SdkZcItemGun)tStack.getItem();
				}
			}
		}
		
		//
		tStack = inventory.getStackInSlot(0);
		if (tStack == null || emptyOrDiffGun(inventory,buyItem,0)) {
			return 0;
		}
		//if (((SdkZcItemGun)itemInSlot).requiredBullet.shiftedIndex)
		tStack = inventory.getStackInSlot(1);
		if (tStack == null || emptyOrDiffGun(inventory,buyItem,1)) {
			return 1;
		}
		
		//now do a full check
		/*tStack = inventory.getStackInSlot(0);
		if (tStack == null || emptyOrDiffGun(inventory,buyItem,0)) {
			return 0;
		}*/
		
			
		
		/*tStack = inventory.getStackInSlot(inventory.currentItem);
		if (tStack != null) {
    		itemInSlot = tStack.getItem();
    		if (!(itemInSlot.shiftedIndex == itemtype.shiftedIndex)) {
    			//tryID = mc.thePlayer.inventory.currentItem;
    		}
		}*/
		
		if (buyItem instanceof SdkZcItemGun) {
			tStack = inventory.getStackInSlot(0);
			if (tStack != null) {
				if (tStack.getItem() instanceof SdkZcItemGun) {
					firstGun = (SdkZcItemGun)tStack.getItem();
				}
			}
			tStack = inventory.getStackInSlot(1);
			if (tStack != null) {
				if (tStack.getItem() instanceof SdkZcItemGun) {
					secondGun = (SdkZcItemGun)tStack.getItem();
				}
			}
			
	    	//find gun without ammo
	    	for (int j = 2+27; j < 9+27; j++) {
	    		tStack = inventory.getStackInSlot(j);
	    		if (tStack != null) {
		    		itemInSlot = tStack.getItem();
		    		
		    		if (itemInSlot == null && (firstGun == buyItem || secondGun == buyItem)) {
		    			return j-27;
		    		}
		    		
		    		if (firstGun == buyItem) {
		    			if (itemInSlot.shiftedIndex == ((SdkZcItemGun)buyItem).requiredBullet.shiftedIndex) {
		    				continue;
		    			}
		    		}
		    		
		    		if (secondGun == buyItem) {
		    			if (itemInSlot.shiftedIndex == ((SdkZcItemGun)buyItem).requiredBullet.shiftedIndex) {
		    				continue;
		    			}
		    		}
		    		
		    		
		    		
		    		/*if (itemInSlot instanceof SdkZcItemGun)
		    		{
		    			int bulletID = ((SdkZcItemGun)itemInSlot).requiredBullet.shiftedIndex;
			    		if (tStack != null) {
			    			//if this gun has no ammo (above it)
			    			if (getSlotAboveItem(inventory, bulletID, j) == -1) {
			    				return j;
			    			}
			    		}
		    		}*/
	    		} else {
	    			if ((firstGun == buyItem || secondGun == buyItem)) {
		    			return j-27;
		    		}
	    		}
	    	}
		}
		
		if (inventory.currentItem < 2) {
			return inventory.currentItem;
		}
    	
    	//find empty slot instead
    	/*for (int j = 0; j < 9; j++) {
    		tStack = inventory.getStackInSlot(j);
    		if (tStack == null) {
    			return j;
    		}
    	}*/
    	
    	return -1;
		
    }
    
    public boolean emptyOrDiffGun(InventoryPlayer inventory, Item buyItem, int slot) {
    	
    	ItemStack gunStack = inventory.getStackInSlot(slot);
    	
		
		
    	if (gunStack == null) return true;
    	
    	if (gunStack.getItem() instanceof SdkZcItemGun) {
    		
    		if (gunStack != null) {
        		Item gunInSlot = gunStack.getItem();
        		if (gunInSlot != null) {
        			
        			//if diff gun and selected, and not in other gun slot
        			if (buyItem != gunInSlot && inventory.currentItem == slot && ((slot == 0 && buyItem != secondGun && secondGun != null) || (slot == 1 && buyItem != firstGun && firstGun != null))) {
        				return true;
        			}
        			
        			int foundAmmo = getInventorySlotContainItem(((SdkZcItemGun)gunInSlot).requiredBullet.shiftedIndex);
        			if (foundAmmo == -1) {
        				return true;
        			} else {
        				return false;
        			}
        			/*ItemStack ammoStack = inventory.getStackInSlot();
	        		Item ammoInSlot = ammoStack.getItem();
	        		if (ammoStack != null && ammoInSlot != null) {
		        		if (ammoInSlot.shiftedIndex == ((SdkZcItemGun)gunInSlot).requiredBullet.shiftedIndex) {
		        			return false;
		        		}
	        		} else {
	        			return true;
	        		}*/
        		} else { return true; }
    		} else {
    			return true;
    		}
    		
    		
    	} else {
    		return false;
    	}
    	
    	//return false;
    }
    
    public int getSlotAboveItem(InventoryPlayer inventory, int i, int curSlot)
    {
    	int j = curSlot;
    	
    	j+=9;
    	
    	//if (inventory.mainInventory[j] != null) System.out.println(inventory.mainInventory[j].itemID);
    	
    	if(inventory.mainInventory[j] != null && inventory.mainInventory[j].itemID == i)
        {
            return j;
        }
    	j+=9;
    	if (inventory.mainInventory[j] != null) System.out.println(inventory.mainInventory[j].itemID);
    	if(inventory.mainInventory[j] != null && inventory.mainInventory[j].itemID == i)
        {
            return j;
        }
    	j+=9;
    	if (inventory.mainInventory[j] != null) System.out.println(inventory.mainInventory[j].itemID);
    	if(inventory.mainInventory[j] != null && inventory.mainInventory[j].itemID == i)
        {
            return j;
        }
    	
        return -1;
    }
    
    public int getSlotAboveUsedItem(InventoryPlayer inventory, int i)
    {
    	int j = inventory.currentItem;
    	
    	j+=9;
    	if(inventory.mainInventory[j] != null && inventory.mainInventory[j].itemID == i)
        {
            return j;
        }
    	j+=9;
    	if(inventory.mainInventory[j] != null && inventory.mainInventory[j].itemID == i)
        {
            return j;
        }
    	j+=9;
    	if(inventory.mainInventory[j] != null && inventory.mainInventory[j].itemID == i)
        {
            return j;
        }
    	
        return -1;
    }

    public ItemStack getCurrentItem()
    {
        return mainInventory[currentItem];
    }

    private int getInventorySlotContainItem(int i)
    {
    	
    	for(int j = 0; j < 9; j++)
        {
    		for (int k = 0; k < 4; k++) {
    			int ii = j + (k*9);
    			if(mainInventory[ii] != null && mainInventory[ii].itemID == i)
	            {
	                return ii;
	            }
    		}
        }
    	
        /*for(int j = 0; j < mainInventory.length; j++)
        {
            if(mainInventory[j] != null && mainInventory[j].itemID == i)
            {
                return j;
            }
        }*/

        return -1;
    }

    private int func_21105_c(ItemStack itemstack)
    {
    	
    	for(int j = 0; j < 9; j++)
        {
    		for (int k = 0; k < 4; k++) {
    			int i = j + (k*9);
	            if(mainInventory[i] != null && mainInventory[i].itemID == itemstack.itemID && mainInventory[i].func_21180_d() && mainInventory[i].stackSize < mainInventory[i].getMaxStackSize() && mainInventory[i].stackSize < getInventoryStackLimit() && (!mainInventory[i].getHasSubtypes() || mainInventory[i].getItemDamage() == itemstack.getItemDamage()))
	            {
	                return i;
	            }
    		}
        }
    	
        /*for(int i = 0; i < mainInventory.length; i++)
        {
            if(mainInventory[i] != null && mainInventory[i].itemID == itemstack.itemID && mainInventory[i].func_21180_d() && mainInventory[i].stackSize < mainInventory[i].getMaxStackSize() && mainInventory[i].stackSize < getInventoryStackLimit() && (!mainInventory[i].getHasSubtypes() || mainInventory[i].getItemDamage() == itemstack.getItemDamage()))
            {
                return i;
            }
        }*/

        return -1;
    }

    private int getFirstEmptyStack()
    {
        for(int i = 0; i < mainInventory.length; i++)
        {
            if(mainInventory[i] == null)
            {
                return i;
            }
        }

        return -1;
    }

    public void setCurrentItem(int i, boolean flag)
    {
        int j = getInventorySlotContainItem(i);
        if(j >= 0 && j < 9)
        {
            currentItem = j;
            return;
        } else
        {
            return;
        }
    }

    public void changeCurrentItem(int i)
    {
        if(i > 0)
        {
            i = 1;
        }
        if(i < 0)
        {
            i = -1;
        }
        if (ModLoader.getMinecraftInstance().theWorld.devMode)
		{
        	for(currentItem -= i; currentItem < 0; currentItem += 9) { }
            for(; currentItem >= 9; currentItem -= 9) { }
		} else if (mainInventory[3] != null)
		{
			for(currentItem -= i; currentItem < 0; currentItem += 4) { }
			for(; currentItem >= 4; currentItem -= 4) { }
		} else if (mainInventory[2] != null)
		{
			for(currentItem -= i; currentItem < 0; currentItem += 3) { }
			for(; currentItem >= 3; currentItem -= 3) { }
		} else { 
			for(currentItem -= i; currentItem < 0; currentItem += 2) { }
			for(; currentItem >= 2; currentItem -= 2) { }
		}
    }

    private int func_21106_d(ItemStack itemstack)
    {
        int i = itemstack.itemID;
        int j = itemstack.stackSize;
        int k = func_21105_c(itemstack);
        if(k < 0)
        {
            k = getFirstEmptyStack();
        }
        if(k < 0)
        {
            return j;
        }
        if(mainInventory[k] == null)
        {
            mainInventory[k] = new ItemStack(i, 0, itemstack.getItemDamage());
        }
        int l = j;
        if(l > mainInventory[k].getMaxStackSize() - mainInventory[k].stackSize)
        {
            l = mainInventory[k].getMaxStackSize() - mainInventory[k].stackSize;
        }
        if(l > getInventoryStackLimit() - mainInventory[k].stackSize)
        {
            l = getInventoryStackLimit() - mainInventory[k].stackSize;
        }
        if(l == 0)
        {
            return j;
        } else
        {
            j -= l;
            mainInventory[k].stackSize += l;
            mainInventory[k].animationsToGo = 5;
            return j;
        }
    }

    public void decrementAnimations()
    {
        for(int i = 0; i < mainInventory.length; i++)
        {
            if(mainInventory[i] != null && mainInventory[i].animationsToGo > 0)
            {
                mainInventory[i].animationsToGo--;
            }
        }

    }

    public boolean consumeInventoryItem(int i)
    {
        int j = getInventorySlotContainItem(i);
        if(j < 0)
        {
            return false;
        }
        if(--mainInventory[j].stackSize <= 0)
        {
            mainInventory[j] = null;
        }
        return true;
    }

    public boolean addItemStackToInventory(ItemStack itemstack)
    {
        if(!itemstack.isItemDamaged())
        {
            itemstack.stackSize = func_21106_d(itemstack);
            if(itemstack.stackSize == 0)
            {
                return true;
            }
        }
        int i = getFirstEmptyStack();
        if(i >= 0)
        {
            mainInventory[i] = itemstack;
            mainInventory[i].animationsToGo = 5;
            return true;
        } else
        {
            return false;
        }
    }

    public ItemStack decrStackSize(int i, int j)
    {
        ItemStack aitemstack[] = mainInventory;
        if(i >= mainInventory.length)
        {
            aitemstack = armorInventory;
            i -= mainInventory.length;
        }
        if(aitemstack[i] != null)
        {
            if(aitemstack[i].stackSize <= j)
            {
                ItemStack itemstack = aitemstack[i];
                aitemstack[i] = null;
                return itemstack;
            }
            ItemStack itemstack1 = aitemstack[i].splitStack(j);
            if(aitemstack[i].stackSize == 0)
            {
                aitemstack[i] = null;
            }
            return itemstack1;
        } else
        {
            return null;
        }
    }

    public void setInventorySlotContents(int i, ItemStack itemstack)
    {
        ItemStack aitemstack[] = mainInventory;
        if(i >= aitemstack.length)
        {
            i -= aitemstack.length;
            aitemstack = armorInventory;
        }
        aitemstack[i] = itemstack;
    }

    public float getStrVsBlock(Block block)
    {
        float f = 1.0F;
        if(mainInventory[currentItem] != null)
        {
            f *= mainInventory[currentItem].getStrVsBlock(block);
        }
        return f;
    }

    public NBTTagList writeToNBT(NBTTagList nbttaglist)
    {
        for(int i = 0; i < mainInventory.length; i++)
        {
            if(mainInventory[i] != null)
            {
                NBTTagCompound nbttagcompound = new NBTTagCompound();
                nbttagcompound.setByte("Slot", (byte)i);
                mainInventory[i].writeToNBT(nbttagcompound);
                nbttaglist.setTag(nbttagcompound);
            }
        }

        for(int j = 0; j < armorInventory.length; j++)
        {
            if(armorInventory[j] != null)
            {
                NBTTagCompound nbttagcompound1 = new NBTTagCompound();
                nbttagcompound1.setByte("Slot", (byte)(j + 100));
                armorInventory[j].writeToNBT(nbttagcompound1);
                nbttaglist.setTag(nbttagcompound1);
            }
        }

        return nbttaglist;
    }

    public void readFromNBT(NBTTagList nbttaglist)
    {
        mainInventory = new ItemStack[36];
        armorInventory = new ItemStack[4];
        for(int i = 0; i < nbttaglist.tagCount(); i++)
        {
            NBTTagCompound nbttagcompound = (NBTTagCompound)nbttaglist.tagAt(i);
            int j = nbttagcompound.getByte("Slot") & 0xff;
            ItemStack itemstack = new ItemStack(nbttagcompound);
            if(itemstack.getItem() == null)
            {
                continue;
            }
            if(j >= 0 && j < mainInventory.length)
            {
                mainInventory[j] = itemstack;
            }
            if(j >= 100 && j < armorInventory.length + 100)
            {
                armorInventory[j - 100] = itemstack;
            }
        }

    }

    public int getSizeInventory()
    {
        return mainInventory.length + 4;
    }

    public ItemStack getStackInSlot(int i)
    {
        ItemStack aitemstack[] = mainInventory;
        if(i >= aitemstack.length)
        {
            i -= aitemstack.length;
            aitemstack = armorInventory;
        }
        return aitemstack[i];
    }

    public String getInvName()
    {
        return "Inventory";
    }

    public int getInventoryStackLimit()
    {
        return 64;
    }

    public int getDamageVsEntity(Entity entity)
    {
        ItemStack itemstack = getStackInSlot(currentItem);
        if(itemstack != null)
        {
            return itemstack.getDamageVsEntity(entity);
        } else
        {
            return 1;
        }
    }

    public boolean canHarvestBlock(Block block)
    {
        if(block.blockMaterial != Material.rock && block.blockMaterial != Material.iron && block.blockMaterial != Material.builtSnow && block.blockMaterial != Material.snow)
        {
            return true;
        }
        ItemStack itemstack = getStackInSlot(currentItem);
        if(itemstack != null)
        {
            return itemstack.canHarvestBlock(block);
        } else
        {
            return false;
        }
    }

    public ItemStack armorItemInSlot(int i)
    {
        return armorInventory[i];
    }

    public int getTotalArmorValue()
    {
        int i = 0;
        int j = 0;
        int k = 0;
        for(int l = 0; l < armorInventory.length; l++)
        {
            if(armorInventory[l] != null && (armorInventory[l].getItem() instanceof ItemArmor))
            {
                int i1 = armorInventory[l].getMaxDamage();
                int j1 = armorInventory[l].getItemDamageForDisplay();
                int k1 = i1 - j1;
                j += k1;
                k += i1;
                int l1 = ((ItemArmor)armorInventory[l].getItem()).damageReduceAmount;
                i += l1;
            }
        }

        if(k == 0)
        {
            return 0;
        } else
        {
            return ((i - 1) * j) / k + 1;
        }
    }

    public void damageArmor(int i)
    {
        for(int j = 0; j < armorInventory.length; j++)
        {
            if(armorInventory[j] == null || !(armorInventory[j].getItem() instanceof ItemArmor))
            {
                continue;
            }
            armorInventory[j].damageItem(i);
            if(armorInventory[j].stackSize == 0)
            {
                armorInventory[j].func_1097_a(player);
                armorInventory[j] = null;
            }
        }

    }

    public void dropAllItems()
    {
        for(int i = 0; i < mainInventory.length; i++)
        {
            if(mainInventory[i] != null)
            {
                player.dropPlayerItemWithRandomChoice(mainInventory[i], true);
                mainInventory[i] = null;
            }
        }

        for(int j = 0; j < armorInventory.length; j++)
        {
            if(armorInventory[j] != null)
            {
                player.dropPlayerItemWithRandomChoice(armorInventory[j], true);
                armorInventory[j] = null;
            }
        }

    }

    public void onInventoryChanged()
    {
        inventoryChanged = true;
    }

    public void setItemStack(ItemStack itemstack)
    {
        itemStack = itemstack;
        player.onItemStackChanged(itemstack);
    }

    public ItemStack getItemStack()
    {
        return itemStack;
    }

    public boolean canInteractWith(EntityPlayer entityplayer)
    {
        if(player.isDead)
        {
            return false;
        }
        return entityplayer.getDistanceSqToEntity(player) <= 64D;
    }

    public ItemStack mainInventory[];
    public ItemStack armorInventory[];
    public int currentItem;
    private EntityPlayer player;
    private ItemStack itemStack;
    public boolean inventoryChanged;
    SdkZcItemGun firstGun;
	SdkZcItemGun secondGun;
}
