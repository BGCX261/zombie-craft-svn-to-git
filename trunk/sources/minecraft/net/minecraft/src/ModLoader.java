package net.minecraft.src;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.security.CodeSource;
import java.security.ProtectionDomain;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import javax.imageio.ImageIO;
import net.minecraft.client.Minecraft;
import org.lwjgl.input.Keyboard;

public final class ModLoader
{

    public static int AddAllFuel(int i)
    {
        logger.finest((new StringBuilder("Finding fuel for ")).append(i).toString());
        int j = 0;
        for(Iterator iterator = modList.iterator(); iterator.hasNext() && j == 0; j = ((BaseMod)iterator.next()).AddFuel(i)) { }
        if(j != 0)
        {
            logger.finest((new StringBuilder("Returned ")).append(j).toString());
        }
        return j;
    }

    public static void AddAllRecipes(CraftingManager craftingmanager)
    {
        if(!hasInit)
        {
            init();
            logger.fine("Initialized");
        }
        logger.finer("Initializing recipes");
        BaseMod basemod;
        for(Iterator iterator = modList.iterator(); iterator.hasNext(); basemod.AddRecipes(craftingmanager))
        {
            basemod = (BaseMod)iterator.next();
        }

    }

    public static void AddAllRenderers(Map map)
    {
        if(!hasInit)
        {
            init();
            logger.fine("Initialized");
        }
        BaseMod basemod;
        for(Iterator iterator = modList.iterator(); iterator.hasNext(); basemod.AddRenderer(map))
        {
            basemod = (BaseMod)iterator.next();
        }

    }

    public static void addAnimation(TextureFX texturefx)
    {
        logger.finest((new StringBuilder("Adding animation ")).append(texturefx.toString()).toString());
        animList.add(texturefx);
    }

    public static int AddArmor(String s)
    {
        try
        {
            String as[] = (String[])field_armorList.get(null);
            List list = Arrays.asList(as);
            ArrayList arraylist = new ArrayList();
            arraylist.addAll(list);
            if(!arraylist.contains(s))
            {
                arraylist.add(s);
            }
            int i = arraylist.indexOf(s);
            field_armorList.set(null, ((Object) (arraylist.toArray(new String[0]))));
            return i;
        }
        catch(IllegalArgumentException illegalargumentexception)
        {
            logger.throwing("ModLoader", "AddArmor", illegalargumentexception);
            ThrowException("An impossible error has occured!", illegalargumentexception);
        }
        catch(IllegalAccessException illegalaccessexception)
        {
            logger.throwing("ModLoader", "AddArmor", illegalaccessexception);
            ThrowException("An impossible error has occured!", illegalaccessexception);
        }
        return -1;
    }

    public static void AddLocalization(String s, String s1)
    {
        Properties properties = null;
        try
        {
            properties = (Properties)getPrivateValue(StringTranslate.class, StringTranslate.getInstance(), 1);
        }
        catch(SecurityException securityexception)
        {
            logger.throwing("ModLoader", "AddLocalization", securityexception);
            ThrowException(securityexception);
        }
        catch(NoSuchFieldException nosuchfieldexception)
        {
            logger.throwing("ModLoader", "AddLocalization", nosuchfieldexception);
            ThrowException(nosuchfieldexception);
        }
        if(properties != null)
        {
            properties.put(s, s1);
        }
    }

    private static void addMod(ClassLoader classloader, String s)
    {
        try
        {
            String s1 = s.split("\\.")[0];
            Package package1 = (ModLoader.class).getPackage();
            if(package1 != null)
            {
                s1 = (new StringBuilder(String.valueOf(package1.getName()))).append(".").append(s1).toString();
            }
            Class class1 = classloader.loadClass(s1);
            if(class1.isAssignableFrom(BaseMod.class))
            {
                return;
            }
            BaseMod basemod = (BaseMod)class1.newInstance();
            if(basemod != null)
            {
                modList.add(basemod);
                logger.fine((new StringBuilder("Mod Loaded: \"")).append(basemod.toString()).append("\" from ").append(s).toString());
                System.out.println((new StringBuilder("Mod Loaded: ")).append(basemod.toString()).toString());
            }
        }
        catch(Throwable throwable)
        {
            logger.throwing("ModLoader", "addMod", throwable);
            ThrowException(throwable);
        }
    }

    public static void AddName(Object obj, String s)
    {
        String s1 = null;
        if(obj instanceof Item)
        {
            Item item = (Item)obj;
            if(item.getItemName() != null)
            {
                s1 = (new StringBuilder(String.valueOf(item.getItemName()))).append(".name").toString();
            }
        } else
        if(obj instanceof Block)
        {
            Block block = (Block)obj;
            if(block.getBlockName() != null)
            {
                s1 = (new StringBuilder(String.valueOf(block.getBlockName()))).append(".name").toString();
            }
        } else
        if(obj instanceof ItemStack)
        {
            ItemStack itemstack = (ItemStack)obj;
            if(itemstack.func_20109_f() != null)
            {
                s1 = (new StringBuilder(String.valueOf(itemstack.func_20109_f()))).append(".name").toString();
            }
        } else
        {
            Exception exception = new Exception((new StringBuilder(String.valueOf(obj.getClass().getName()))).append(" cannot have name attached to it!").toString());
            logger.throwing("ModLoader", "AddName", exception);
            ThrowException(exception);
        }
        if(s1 != null)
        {
            AddLocalization(s1, s);
        } else
        {
            Exception exception1 = new Exception((new StringBuilder()).append(obj).append(" is missing name tag!").toString());
            logger.throwing("ModLoader", "AddName", exception1);
            ThrowException(exception1);
        }
    }

    public static int addOverride(String s, String s1)
    {
        try
        {
            int i = getUniqueSpriteIndex(s);
            addOverride(s, s1, i);
            return i;
        }
        catch(Throwable throwable)
        {
            logger.throwing("ModLoader", "addOverride", throwable);
            ThrowException(throwable);
            throw new RuntimeException(throwable);
        }
    }

    public static void addOverride(String s, String s1, int i)
    {
        int j = -1;
        if(s.equals("/terrain.png"))
        {
            j = 0;
        } else
        if(s.equals("/gui/items.png"))
        {
            j = 1;
        } else
        {
            return;
        }
        logger.finer((new StringBuilder("addOverride(")).append(s).append(",").append(s1).append(",").append(i).append(")").toString());
        Object obj = (Map)overrides.get(Integer.valueOf(j));
        if(obj == null)
        {
            obj = new HashMap();
            overrides.put(Integer.valueOf(j), obj);
        }
        ((Map) (obj)).put(s1, Integer.valueOf(i));
    }

    public static void AddSmelting(int i, ItemStack itemstack)
    {
        FurnaceRecipes.smelting().addSmelting(i, itemstack);
    }

    public static Entity DispenseEntity(World world, double d, double d1, double d2, float f, 
            float f1, int i)
    {
        Entity entity = null;
        for(Iterator iterator = modList.iterator(); iterator.hasNext() && entity == null; entity = ((BaseMod)iterator.next()).DispenseEntity(world, d, d1, d2, f, f1, i)) { }
        return entity;
    }

    public static List getLoadedMods()
    {
        return Collections.unmodifiableList(modList);
    }

    public static Logger getLogger()
    {
        return logger;
    }

    public static Minecraft getMinecraftInstance()
    {
        if(instance == null)
        {
            try
            {
                ThreadGroup threadgroup = Thread.currentThread().getThreadGroup();
                int i = threadgroup.activeCount();
                Thread athread[] = new Thread[i];
                threadgroup.enumerate(athread);
                for(int j = 0; j < athread.length; j++)
                {
                    if(!athread[j].getName().equals("Minecraft main thread"))
                    {
                        continue;
                    }
                    instance = (Minecraft)getPrivateValue(java.lang.Thread.class, athread[j], "target");
                    break;
                }

            }
            catch(SecurityException securityexception)
            {
                logger.throwing("ModLoader", "getMinecraftInstance", securityexception);
                throw new RuntimeException(securityexception);
            }
            catch(NoSuchFieldException nosuchfieldexception)
            {
                logger.throwing("ModLoader", "getMinecraftInstance", nosuchfieldexception);
                throw new RuntimeException(nosuchfieldexception);
            }
        }
        return instance;
    }

    public static Object getPrivateValue(Class class1, Object obj, int i)
        throws IllegalArgumentException, SecurityException, NoSuchFieldException
    {
        try
        {
            Field field = class1.getDeclaredFields()[i];
            field.setAccessible(true);
            return field.get(obj);
        }
        catch(IllegalAccessException illegalaccessexception)
        {
            logger.throwing("ModLoader", "getPrivateValue", illegalaccessexception);
            ThrowException("An impossible error has occured!", illegalaccessexception);
            return null;
        }
    }

    public static Object getPrivateValue(Class class1, Object obj, String s)
        throws IllegalArgumentException, SecurityException, NoSuchFieldException
    {
        try
        {
            Field field = class1.getDeclaredField(s);
            field.setAccessible(true);
            return field.get(obj);
        }
        catch(IllegalAccessException illegalaccessexception)
        {
            logger.throwing("ModLoader", "getPrivateValue", illegalaccessexception);
            ThrowException("An impossible error has occured!", illegalaccessexception);
            return null;
        }
    }

    public static int getUniqueEntityId()
    {
        return highestEntityId++;
    }

    private static int getUniqueItemSpriteIndex()
    {
        for(; itemSpriteIndex < usedItemSprites.length; itemSpriteIndex++)
        {
            if(!usedItemSprites[itemSpriteIndex])
            {
                usedItemSprites[itemSpriteIndex] = true;
                return itemSpriteIndex++;
            }
        }

        Exception exception = new Exception("No more empty item sprite indices left!");
        logger.throwing("ModLoader", "getUniqueItemSpriteIndex", exception);
        ThrowException(exception);
        return 0;
    }

    public static int getUniqueSpriteIndex(String s)
    {
        if(s.equals("/gui/items.png"))
        {
            return getUniqueItemSpriteIndex();
        }
        if(s.equals("/terrain.png"))
        {
            return getUniqueTerrainSpriteIndex();
        } else
        {
            Exception exception = new Exception((new StringBuilder("No registry for this texture: ")).append(s).toString());
            logger.throwing("ModLoader", "getUniqueItemSpriteIndex", exception);
            ThrowException(exception);
            return 0;
        }
    }

    private static int getUniqueTerrainSpriteIndex()
    {
        for(; terrainSpriteIndex < usedTerrainSprites.length; terrainSpriteIndex++)
        {
            if(!usedTerrainSprites[terrainSpriteIndex])
            {
                usedTerrainSprites[terrainSpriteIndex] = true;
                return terrainSpriteIndex++;
            }
        }

        Exception exception = new Exception("No more empty terrain sprite indices left!");
        logger.throwing("ModLoader", "getUniqueItemSpriteIndex", exception);
        ThrowException(exception);
        return 0;
    }

    private static void init()
    {
        File file;
        String s = "1111111111111111111111111111111111111101111111011111111111110001111111111111111111111011111000111111100110000011111110000000001111111001100000110000000100000011000000010000001100000000000000110000000000000000000000000000000000000000000000001100000000000000";
        String s1 = "1111111111111111111111111111110111111100011111111111110001111110111111111111000011110011111111111111001111000000111111111111100011111111000010001111011110000000111011000000000011100000000000001110000000000111111000000000001101000000000001111111111111000011";
        for(int i = 0; i < 256; i++)
        {
            usedItemSprites[i] = s.charAt(i) == '1';
            usedTerrainSprites[i] = s1.charAt(i) == '1';
        }

        try
        {
            field_modifiers = (java.lang.reflect.Field.class).getDeclaredField("modifiers");
            field_modifiers.setAccessible(true);
            field_blockList = (Session.class).getDeclaredFields()[0];
            field_blockList.setAccessible(true);
            field_TileEntityRenderers = (TileEntityRenderer.class).getDeclaredFields()[0];
            field_TileEntityRenderers.setAccessible(true);
            field_armorList = (RenderPlayer.class).getDeclaredFields()[3];
            field_modifiers.setInt(field_armorList, field_armorList.getModifiers() & 0xffffffef);
            field_armorList.setAccessible(true);
            try
            {
                method_RegisterTileEntity = (TileEntity.class).getDeclaredMethod("a", new Class[] {
                    java.lang.Class.class, java.lang.String.class
                });
            }
            catch(NoSuchMethodException nosuchmethodexception)
            {
                method_RegisterTileEntity = (TileEntity.class).getDeclaredMethod("addMapping", new Class[] {
                    java.lang.Class.class, java.lang.String.class
                });
            }
            method_RegisterTileEntity.setAccessible(true);
            try
            {
                method_RegisterEntityID = (EntityList.class).getDeclaredMethod("a", new Class[] {
                    java.lang.Class.class, java.lang.String.class, Integer.TYPE
                });
            }
            catch(NoSuchMethodException nosuchmethodexception1)
            {
                method_RegisterEntityID = (EntityList.class).getDeclaredMethod("addMapping", new Class[] {
                    java.lang.Class.class, java.lang.String.class, Integer.TYPE
                });
            }
            method_RegisterEntityID.setAccessible(true);
        }
        catch(SecurityException securityexception)
        {
            logger.throwing("ModLoader", "init", securityexception);
            ThrowException(securityexception);
            throw new RuntimeException(securityexception);
        }
        catch(NoSuchFieldException nosuchfieldexception)
        {
            logger.throwing("ModLoader", "init", nosuchfieldexception);
            ThrowException(nosuchfieldexception);
            throw new RuntimeException(nosuchfieldexception);
        }
        catch(NoSuchMethodException nosuchmethodexception2)
        {
            logger.throwing("ModLoader", "init", nosuchmethodexception2);
            ThrowException(nosuchmethodexception2);
            throw new RuntimeException(nosuchmethodexception2);
        }
        catch(IllegalArgumentException illegalargumentexception)
        {
            logger.throwing("ModLoader", "init", illegalargumentexception);
            ThrowException(illegalargumentexception);
            throw new RuntimeException(illegalargumentexception);
        }
        catch(IllegalAccessException illegalaccessexception)
        {
            logger.throwing("ModLoader", "init", illegalaccessexception);
            ThrowException(illegalaccessexception);
            throw new RuntimeException(illegalaccessexception);
        }
        file = new File(Minecraft.getMinecraftDir(), "ModLoader.txt");
        try
        {
            logger.setLevel(Level.FINER);
            if(logHandler == null)
            {
                logHandler = new FileHandler(file.getPath());
                logHandler.setFormatter(new SimpleFormatter());
                logger.addHandler(logHandler);
            }
            logger.fine("ModLoader Beta 1.3_01v3 Initializing...");
            System.out.println("ModLoader Beta 1.3_01v3 Initializing...");
            File file1 = new File((ModLoader.class).getProtectionDomain().getCodeSource().getLocation().toURI());
            readFromClassPath(file1);
            System.out.println("Done.");
            BaseMod basemod;
            for(Iterator iterator = modList.iterator(); iterator.hasNext(); basemod.ModsLoaded())
            {
                basemod = (BaseMod)iterator.next();
            }

        }
        catch(Throwable throwable)
        {
            logger.throwing("ModLoader", "init", throwable);
            ThrowException("ModLoader has failed to initialize.", throwable);
            if(logHandler != null)
            {
                logHandler.close();
            }
            throw new RuntimeException(throwable);
        } finally {
        hasInit = true;
		}
    }

    public static boolean isGUIOpen(Class class1)
    {
        Minecraft minecraft = getMinecraftInstance();
        if(minecraft == null)
        {
            return false;
        }
        if(class1 == null)
        {
            return minecraft.currentScreen == null;
        }
        if(minecraft.currentScreen == null && class1 != null)
        {
            return false;
        } else
        {
            return class1.isInstance(minecraft.currentScreen);
        }
    }

    public static boolean isModLoaded(String s)
    {
        Class class1 = null;
        try
        {
            class1 = Class.forName(s);
        }
        catch(ClassNotFoundException classnotfoundexception)
        {
            return false;
        }
        if(class1 != null)
        {
            for(Iterator iterator = modList.iterator(); iterator.hasNext();)
            {
                BaseMod basemod = (BaseMod)iterator.next();
                if(class1.isInstance(basemod))
                {
                    return true;
                }
            }

        }
        return false;
    }

    private static java.awt.image.BufferedImage loadImage(RenderEngine renderengine, String s)
        throws Exception
    {
        TexturePackList texturepacklist = (TexturePackList)getPrivateValue(RenderEngine.class, renderengine, 10);
        InputStream inputstream = texturepacklist.selectedTexturePack.func_6481_a(s);
        if(inputstream == null)
        {
            throw new Exception((new StringBuilder("Image not found: ")).append(s).toString());
        }
        java.awt.image.BufferedImage bufferedimage = ImageIO.read(inputstream);
        if(bufferedimage == null)
        {
            throw new Exception((new StringBuilder("Image not found: ")).append(s).toString());
        } else
        {
            return bufferedimage;
        }
    }

    public static void OpenModGUI(EntityPlayer entityplayer, Object obj)
    {
        if(!hasInit)
        {
            init();
            logger.fine("Initialized");
        }
        Minecraft minecraft = getMinecraftInstance();
        if(minecraft == null || minecraft.thePlayer != entityplayer)
        {
            return;
        }
        GuiScreen guiscreen = null;
        for(Iterator iterator = modList.iterator(); iterator.hasNext() && guiscreen == null; guiscreen = ((BaseMod)iterator.next()).OpenModGUI(entityplayer, obj)) { }
        if(guiscreen != null)
        {
            minecraft.displayGuiScreen(guiscreen);
        }
    }

    public static void PopulateChunk(IChunkProvider ichunkprovider, int i, int j, World world)
    {
        if(!hasInit)
        {
            init();
            logger.fine("Initialized");
        }
        for(Iterator iterator = modList.iterator(); iterator.hasNext();)
        {
            BaseMod basemod = (BaseMod)iterator.next();
            if(ichunkprovider instanceof ChunkProviderGenerate)
            {
                basemod.GenerateSurface(world, world.rand, i, j);
            } else
            if(ichunkprovider instanceof ChunkProviderHell)
            {
                basemod.GenerateNether(world, world.rand, i, j);
            }
        }

    }

    private static void readFromClassPath(File file)
        throws FileNotFoundException, IOException
    {
        logger.finer((new StringBuilder("Adding mods from ")).append(file.getCanonicalPath()).toString());
        ClassLoader classloader = (ModLoader.class).getClassLoader();
        if(file.isFile() && (file.getName().endsWith(".jar") || file.getName().endsWith(".zip")))
        {
            logger.finer("Zip found.");
            FileInputStream fileinputstream = new FileInputStream(file);
            ZipInputStream zipinputstream = new ZipInputStream(fileinputstream);
            Object obj = null;
            do
            {
                ZipEntry zipentry = zipinputstream.getNextEntry();
                if(zipentry == null)
                {
                    break;
                }
                String s1 = zipentry.getName();
                if(!zipentry.isDirectory() && s1.startsWith("mod_") && s1.endsWith(".class"))
                {
                    addMod(classloader, s1);
                }
            } while(true);
            fileinputstream.close();
        } else
        if(file.isDirectory())
        {
            Package package1 = (ModLoader.class).getPackage();
            if(package1 != null)
            {
                String s = package1.getName().replace('.', File.separatorChar);
                file = new File(file, s);
            }
            logger.finer("Directory found.");
            File afile[] = file.listFiles();
            if(afile != null)
            {
                for(int i = 0; i < afile.length; i++)
                {
                    String s2 = afile[i].getName();
                    if(afile[i].isFile() && s2.startsWith("mod_") && s2.endsWith(".class"))
                    {
                        addMod(classloader, s2);
                    }
                }

            }
        }
    }

    public static void RegisterAllTextureOverrides(RenderEngine renderengine)
    {
        Minecraft minecraft = getMinecraftInstance();
        if(minecraft != null)
        {
            BaseMod basemod;
            for(Iterator iterator = modList.iterator(); iterator.hasNext(); basemod.RegisterAnimation(minecraft))
            {
                basemod = (BaseMod)iterator.next();
            }

        }
        TextureFX texturefx;
        for(Iterator iterator1 = animList.iterator(); iterator1.hasNext(); renderengine.registerTextureFX(texturefx))
        {
            texturefx = (TextureFX)iterator1.next();
        }

        for(Iterator iterator2 = overrides.entrySet().iterator(); iterator2.hasNext();)
        {
            java.util.Map.Entry entry = (java.util.Map.Entry)iterator2.next();
            for(Iterator iterator3 = ((Map)entry.getValue()).entrySet().iterator(); iterator3.hasNext();)
            {
                java.util.Map.Entry entry1 = (java.util.Map.Entry)iterator3.next();
                String s = (String)entry1.getKey();
                int i = ((Integer)entry1.getValue()).intValue();
                int j = ((Integer)entry.getKey()).intValue();
                String s1 = null;
                if(j == 0)
                {
                    s1 = "/terrain.png";
                } else
                if(j == 1)
                {
                    s1 = "/gui/items.png";
                } else
                {
                    throw new ArrayIndexOutOfBoundsException(j);
                }
                System.out.println((new StringBuilder("Overriding ")).append(s1).append(" with ").append(s).append(" @ ").append(i).toString());
                try
                {
                    java.awt.image.BufferedImage bufferedimage = loadImage(renderengine, s);
                    renderengine.registerTextureFX(new ModTexture(i, j, bufferedimage));
                }
                catch(Exception exception)
                {
                    logger.throwing("ModLoader", "RegisterAllTextureOverrides", exception);
                    ThrowException(exception);
                    throw new RuntimeException(exception);
                }
            }

        }

    }

    public static void RegisterBlock(Block block)
    {
        RegisterBlock(block, null);
    }

    public static void RegisterBlock(Block block, Class class1)
    {
        try
        {
            if(block == null)
            {
                throw new IllegalArgumentException("block parameter cannot be null.");
            }
            List list = (List)field_blockList.get(null);
            list.add(block);
            int i = block.blockID;
            ItemBlock itemblock = null;
            if(class1 != null)
            {
                itemblock = (ItemBlock)class1.getConstructor(new Class[] {
                    Integer.TYPE
                }).newInstance(new Object[] {
                    Integer.valueOf(i - 256)
                });
            } else
            {
                itemblock = new ItemBlock(i - 256);
            }
            if(Block.blocksList[i] != null && Item.itemsList[i] == null)
            {
                Item.itemsList[i] = itemblock;
            }
        }
        catch(IllegalArgumentException illegalargumentexception)
        {
            logger.throwing("ModLoader", "RegisterBlock", illegalargumentexception);
            ThrowException(illegalargumentexception);
        }
        catch(IllegalAccessException illegalaccessexception)
        {
            logger.throwing("ModLoader", "RegisterBlock", illegalaccessexception);
            ThrowException(illegalaccessexception);
        }
        catch(SecurityException securityexception)
        {
            logger.throwing("ModLoader", "RegisterBlock", securityexception);
            ThrowException(securityexception);
        }
        catch(InstantiationException instantiationexception)
        {
            logger.throwing("ModLoader", "RegisterBlock", instantiationexception);
            ThrowException(instantiationexception);
        }
        catch(InvocationTargetException invocationtargetexception)
        {
            logger.throwing("ModLoader", "RegisterBlock", invocationtargetexception);
            ThrowException(invocationtargetexception);
        }
        catch(NoSuchMethodException nosuchmethodexception)
        {
            logger.throwing("ModLoader", "RegisterBlock", nosuchmethodexception);
            ThrowException(nosuchmethodexception);
        }
    }

    public static void RegisterEntityID(Class class1, String s, int i)
    {
        try
        {
            method_RegisterEntityID.invoke(null, new Object[] {
                class1, s, Integer.valueOf(i)
            });
        }
        catch(IllegalArgumentException illegalargumentexception)
        {
            logger.throwing("ModLoader", "RegisterEntityID", illegalargumentexception);
            ThrowException(illegalargumentexception);
        }
        catch(IllegalAccessException illegalaccessexception)
        {
            logger.throwing("ModLoader", "RegisterEntityID", illegalaccessexception);
            ThrowException(illegalaccessexception);
        }
        catch(InvocationTargetException invocationtargetexception)
        {
            logger.throwing("ModLoader", "RegisterEntityID", invocationtargetexception);
            ThrowException(invocationtargetexception);
        }
    }

    public static void RegisterKey(BaseMod basemod, KeyBinding keybinding, boolean flag)
    {
        Object obj = (Map)keyList.get(basemod);
        if(obj == null)
        {
            obj = new HashMap();
        }
        boolean aflag[] = new boolean[2];
        aflag[0] = flag;
        ((Map) (obj)).put(keybinding, aflag);
        keyList.put(basemod, obj);
    }

    public static KeyBinding[] RegisterAllKeys(KeyBinding akeybinding[])
    {
        List list = Arrays.asList(akeybinding);
        ArrayList arraylist = new ArrayList();
        arraylist.addAll(list);
        Map map;
        for(Iterator iterator = keyList.values().iterator(); iterator.hasNext(); arraylist.addAll(map.keySet()))
        {
            map = (Map)iterator.next();
        }

        return (KeyBinding[])arraylist.toArray(new KeyBinding[0]);
    }

    public static int getUniqueBlockModelID(BaseMod basemod)
    {
        int i = nextBlockModelID++;
        blockModels.put(Integer.valueOf(i), basemod);
        return i;
    }

    public static boolean RenderBlock(RenderBlocks renderblocks, IBlockAccess iblockaccess, int i, int j, int k, Block block, int l)
    {
        BaseMod basemod = (BaseMod)blockModels.get(Integer.valueOf(l));
        if(basemod == null)
        {
            return false;
        } else
        {
            return basemod.RenderBlock(renderblocks, iblockaccess, i, j, k, block, l);
        }
    }

    public static void RegisterTileEntity(Class class1, String s)
    {
        RegisterTileEntity(class1, s, null);
    }

    public static void RegisterTileEntity(Class class1, String s, TileEntitySpecialRenderer tileentityspecialrenderer)
    {
        try
        {
            method_RegisterTileEntity.invoke(null, new Object[] {
                class1, s
            });
            if(tileentityspecialrenderer != null)
            {
                TileEntityRenderer tileentityrenderer = TileEntityRenderer.instance;
                Map map = (Map)field_TileEntityRenderers.get(tileentityrenderer);
                map.put(class1, tileentityspecialrenderer);
                tileentityspecialrenderer.setTileEntityRenderer(tileentityrenderer);
            }
        }
        catch(IllegalArgumentException illegalargumentexception)
        {
            logger.throwing("ModLoader", "RegisterTileEntity", illegalargumentexception);
            ThrowException(illegalargumentexception);
        }
        catch(IllegalAccessException illegalaccessexception)
        {
            logger.throwing("ModLoader", "RegisterTileEntity", illegalaccessexception);
            ThrowException(illegalaccessexception);
        }
        catch(InvocationTargetException invocationtargetexception)
        {
            logger.throwing("ModLoader", "RegisterTileEntity", invocationtargetexception);
            ThrowException(invocationtargetexception);
        }
    }

    public static void RunOSDHooks(Minecraft minecraft)
    {
        if(!hasInit)
        {
            init();
            logger.fine("Initialized");
        }
        BaseMod basemod;
        for(Iterator iterator = modList.iterator(); iterator.hasNext(); basemod.OSDHook(minecraft, minecraft.currentScreen != null))
        {		
            basemod = (BaseMod)iterator.next();			
        }

        for(Iterator iterator1 = keyList.entrySet().iterator(); iterator1.hasNext();)
        {
            java.util.Map.Entry entry = (java.util.Map.Entry)iterator1.next();
            for(Iterator iterator2 = ((Map)entry.getValue()).entrySet().iterator(); iterator2.hasNext();)
            {
                java.util.Map.Entry entry1 = (java.util.Map.Entry)iterator2.next();
                boolean flag = Keyboard.isKeyDown(((KeyBinding)entry1.getKey()).keyCode);
                boolean aflag[] = (boolean[])entry1.getValue();
                boolean flag1 = aflag[1];
                aflag[1] = flag;
                if(flag && (!flag1 || aflag[0]))
                {
                    ((BaseMod)entry.getKey()).KeyboardEvent((KeyBinding)entry1.getKey());
                }
            }

        }

    }

    public static void setPrivateValue(Class class1, Object obj, int i, Object obj1)
        throws IllegalArgumentException, SecurityException, NoSuchFieldException
    {
        try
        {
            Field field = class1.getDeclaredFields()[i];
            field.setAccessible(true);
            int j = field_modifiers.getInt(field);
            if((j & 0x10) != 0)
            {
                field_modifiers.setInt(field, j & 0xffffffef);
            }
            field.set(obj, obj1);
        }
        catch(IllegalAccessException illegalaccessexception)
        {
            logger.throwing("ModLoader", "setPrivateValue", illegalaccessexception);
            ThrowException("An impossible error has occured!", illegalaccessexception);
        }
    }

    public static void setPrivateValue(Class class1, Object obj, String s, Object obj1)
        throws IllegalArgumentException, SecurityException, NoSuchFieldException
    {
        try
        {
            Field field = class1.getDeclaredField(s);
            int i = field_modifiers.getInt(field);
            if((i & 0x10) != 0)
            {
                field_modifiers.setInt(field, i & 0xffffffef);
            }
            field.setAccessible(true);
            field.set(obj, obj1);
        }
        catch(IllegalAccessException illegalaccessexception)
        {
            logger.throwing("ModLoader", "setPrivateValue", illegalaccessexception);
            ThrowException("An impossible error has occured!", illegalaccessexception);
        }
    }

    public static void ThrowException(String s, Throwable throwable)
    {
        Minecraft minecraft = getMinecraftInstance();
        if(minecraft != null)
        {
            minecraft.displayUnexpectedThrowable(new UnexpectedThrowable(s, throwable));
        } else
        {
            throw new RuntimeException(throwable);
        }
    }

    private static void ThrowException(Throwable throwable)
    {
        ThrowException("Exception occured in ModLoader", throwable);
    }

    private ModLoader()
    {
    }

    private static final List animList = new LinkedList();
    public static final boolean DEBUG = false;
    private static Field field_armorList = null;
    private static Field field_blockList = null;
    private static Field field_modifiers = null;
    private static Field field_TileEntityRenderers = null;
    private static boolean hasInit = false;
    private static int highestEntityId = 3000;
    private static Minecraft instance = null;
    private static int itemSpriteIndex = 0;
    private static final Map keyList = new HashMap();
    private static final Logger logger = Logger.getLogger("ModLoader");
    private static FileHandler logHandler = null;
    private static Method method_RegisterEntityID = null;
    private static Method method_RegisterTileEntity = null;
    public static final LinkedList modList = new LinkedList();
    private static final Map overrides = new HashMap();
    private static final Map blockModels = new HashMap();
    private static int terrainSpriteIndex = 0;
    private static final boolean usedItemSprites[] = new boolean[256];
    private static final boolean usedTerrainSprites[] = new boolean[256];
    public static final String VERSION = "ModLoader Beta 1.3_01v3";
    private static int nextBlockModelID = 1000;

}
