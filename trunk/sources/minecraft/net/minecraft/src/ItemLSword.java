package net.minecraft.src;

public class ItemLSword extends Item
{

    public ItemLSword(int i, EnumToolMaterial enumtoolmaterial)
    {
        super(i);
        maxStackSize = 1;
        maxDamage = enumtoolmaterial.getMaxUses();
        weaponDamage = 4 + enumtoolmaterial.getDamageVsEntity() * 2;
    }

    public float getStrVsBlock(ItemStack itemstack, Block block)
    {
        return 1.5F;
    }

    public void hitEntity(ItemStack itemstack, EntityLiving entityliving)
    {
        itemstack.damageItem(1);
	entityliving.fire = 200;
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
