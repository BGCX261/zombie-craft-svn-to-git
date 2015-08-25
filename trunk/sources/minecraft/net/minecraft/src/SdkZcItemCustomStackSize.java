package net.minecraft.src;

// SDK
public class SdkZcItemCustomStackSize extends Item {
	protected SdkZcItemCustomStackSize(int i, int stackSize)
	{
		super(i);
		maxStackSize = stackSize;
	}
}
