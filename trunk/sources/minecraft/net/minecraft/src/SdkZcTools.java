package net.minecraft.src;

import java.io.File;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;

// SDK
public class SdkZcTools
{
	private static Minecraft minecraft = ModLoader.getMinecraftInstance();
	
	private static boolean soundsAdded = false;
	public static void addSounds()
	{
		if(!soundsAdded)
		{
			soundsAdded = true;
			try
			{
				File resourcesFolder = new File(minecraft.getMinecraftDir(), "resources/");
				
		        String[] soundTypes =  { "sound", "music", "streaming" };
		        for(int i = 0; i < soundTypes.length; i++)
		        {
		            File[] modSoundFiles = new File(resourcesFolder, "sdkzc/" + soundTypes[i]).listFiles();
		            if (modSoundFiles != null) {
			            for(int j = 0; j < modSoundFiles.length; j++)
						{
			            	minecraft.installResource(soundTypes[i] + "/" + "sdkzc/" + modSoundFiles[j].getName(), modSoundFiles[j]);
						}
		            }
		        }
			}
			catch (Exception e)
			{
				System.out.println("Error adding sounds.");
				e.printStackTrace();
			}
		}
	}

	public static void renderTextureOverlay(String texturePath, float alpha)
	{
		ScaledResolution scaledresolution = new ScaledResolution(minecraft.displayWidth, minecraft.displayHeight);
        int width = scaledresolution.getScaledWidth();
        int height = scaledresolution.getScaledHeight();
        
        GL11.glEnable(3042 /*GL_BLEND*/);
        GL11.glDisable(2929 /*GL_DEPTH_TEST*/);
        GL11.glDepthMask(false);
        GL11.glBlendFunc(770, 771);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, alpha);
        GL11.glDisable(3008 /*GL_ALPHA_TEST*/);
        GL11.glBindTexture(3553 /*GL_TEXTURE_2D*/, minecraft.renderEngine.getTexture(texturePath));
        Tessellator tessellator = Tessellator.instance;
        tessellator.startDrawingQuads();
        tessellator.addVertexWithUV(0.0D, height, -90D, 0.0D, 1.0D);
        tessellator.addVertexWithUV(width, height, -90D, 1.0D, 1.0D);
        tessellator.addVertexWithUV(width, 0.0D, -90D, 1.0D, 0.0D);
        tessellator.addVertexWithUV(0.0D, 0.0D, -90D, 0.0D, 0.0D);
        tessellator.draw();
        GL11.glDepthMask(true);
        GL11.glEnable(2929 /*GL_DEPTH_TEST*/);
        GL11.glEnable(3008 /*GL_ALPHA_TEST*/);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, alpha);
	}
}
