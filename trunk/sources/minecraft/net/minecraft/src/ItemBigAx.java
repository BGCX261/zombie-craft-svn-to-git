package net.minecraft.src;

public class ItemBigAx extends Item
{

    public ItemBigAx(int i, EnumToolMaterial enumtoolmaterial)
    {
        super(i);
        maxStackSize = 1;
        maxDamage = enumtoolmaterial.getMaxUses();
        weaponDamage = 6 + enumtoolmaterial.getDamageVsEntity() * 2;
    }

    public float getStrVsBlock(ItemStack itemstack, Block block)
    {
        return 1.5F;
    }

    public void hitEntity(ItemStack itemstack, EntityLiving entityliving)
    {
        itemstack.damageItem(1);
    }

    public void hitBlock(ItemStack itemstack, int i, int j, int k, int l)
    {
        itemstack.damageItem(2);
    }

    public int getDamageVsEntity(Entity entity)
    {
        return weaponDamage;
    }

    public boolean isFull3D()
    {
        return true;
    }

    private int weaponDamage;
}
