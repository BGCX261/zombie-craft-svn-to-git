package net.minecraft.src;

// SDK
public class SdkZcPair<L, R>
{
	private final L left;
	private final R right;
	
	public SdkZcPair(L left, R right)
	{
		this.left = left;
		this.right = right;
	}
	
	public L getLeft() { return left; }
	public R getRight() { return right; }
	
	public int hashCode() { return left.hashCode() ^ right.hashCode(); }
	
	public boolean equals(Object o)
	{
		if (o == null) return false;
		if (!(o instanceof SdkZcPair)) return false;
		SdkZcPair pairo = (SdkZcPair)o;
		return this.left.equals(pairo.getLeft()) && this.right.equals(pairo.getRight());
	}
}
