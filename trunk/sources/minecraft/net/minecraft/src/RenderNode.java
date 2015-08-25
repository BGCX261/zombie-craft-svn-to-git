package net.minecraft.src;

import org.lwjgl.opengl.GL11;

public class RenderNode extends Render {
    public static final boolean renderLine = true;
    public static final boolean renderBobber = false;
    public static float yoffset = 0.5f;
    public static float caughtOffset = 0.8F;

    public RenderNode() {
        super();
    }

    public void renderLine(Entity entitypirate, Entity entity, double d, double d1, double d2, float f, float f1) {
        Tessellator tessellator = Tessellator.instance;
        RenderManager rm = RenderManager.instance;
        
        float castProgress = 1.0F;

        float f9 = ((entitypirate.prevRotationYaw + (entitypirate.rotationYaw - entitypirate.prevRotationYaw) * f1) * 3.141593F) / 180F;
        float f11 = ((entitypirate.prevRotationPitch + (entitypirate.rotationPitch - entitypirate.prevRotationPitch) * f1) * 3.141593F) / 180F;

        double d3 = MathHelper.sin(f9);
        double d5 = MathHelper.cos(f9);
        double d7 = MathHelper.sin(f11);
        double d8 = MathHelper.cos(f11);

        float f10 = 0F;//((entitypirate.prevRenderYawOffset + (entitypirate.renderYawOffset - entitypirate.prevRenderYawOffset) * f1) * 3.141593F) / 180F;
        double d4 = MathHelper.sin(f10);
        double d6 = MathHelper.cos(f10);

        double pirateX = entitypirate.posX;//(entitypirate.prevPosX + (entitypirate.posX - entitypirate.prevPosX) * f1) - d6 * 0.35D - d4 * 0.85D;
        double pirateY = entitypirate.posY;//(entitypirate.prevPosY + (entitypirate.posY - entitypirate.prevPosY) * f1) +yoffset;
        double pirateZ = entitypirate.posZ;//((entitypirate.prevPosZ + (entitypirate.posZ - entitypirate.prevPosZ) * f1) - d4 * 0.35D) + d6 * 0.85D;
        double entX = entity.posX;//(entity.boundingBox.minX + (entity.boundingBox.maxX - entity.boundingBox.minX) / 2D);
        double entY = entity.posY;//(entity.boundingBox.minY + (entity.boundingBox.maxY - entity.boundingBox.minY) / 2D);
        double entZ = entity.posZ;//(entity.boundingBox.minZ + (entity.boundingBox.maxZ - entity.boundingBox.minZ) / 2D);

        double fishX = castProgress*(entX - pirateX);
        double fishY = castProgress*(entY - pirateY);
        double fishZ = castProgress*(entZ - pirateZ);
        GL11.glDisable(3553 /*GL_TEXTURE_2D*/);
        GL11.glDisable(2896 /*GL_LIGHTING*/);
        tessellator.startDrawing(3);
        if (((EntityNode)entitypirate).render) {
        	stringColor = 0x880000;
        } else {
        	stringColor = 0x888888;
        }
        tessellator.setColorOpaque_I(stringColor);
        int steps = 16;

        for (int i = 0; i < steps; ++i) {
            float f4 = i/(float)steps;
            tessellator.addVertex(
                pirateX - rm.renderPosX + fishX * f4,//(f4 * f4 + f4) * 0.5D + 0.25D,
                pirateY - rm.renderPosY + fishY * f4,//(f4 * f4 + f4) * 0.5D + 0.25D,
                pirateZ - rm.renderPosZ + fishZ * f4);
        }
        
        tessellator.draw();
        GL11.glEnable(2896 /*GL_LIGHTING*/);
        GL11.glEnable(3553 /*GL_TEXTURE_2D*/);
    }

    public void doRenderNode(Entity entitynode, double d, double d1, double d2,
                               float f, float f1) {
        //super.doRender(entitynode, d, d1, d2, f, f1);
        EntityNode entNode = ((EntityNode)entitynode);
        if (entNode.losNodeCount > 0) {
        	for (int i = 0; i < entNode.losNodeCount; i++) {
        		Entity entity = entNode.losNodes[i];

    	        if(entity == null)
    	            return;
    	
    	        if (renderLine)
    	            renderLine(entitynode, entity, d, d1, d2, f, f1);
        	}
        	
        }
    }

    public void doRender(Entity entity, double d, double d1, double d2,
                               float f, float f1) {
        
    	doRenderNode(entity, d, d1, d2, f, f1);
        
        GL11.glPushMatrix();
        renderOffsetAABB(entity.boundingBox, d - entity.lastTickPosX, d1 - entity.lastTickPosY, d2 - entity.lastTickPosZ, ((EntityNode)entity).isUsed);
        GL11.glPopMatrix();
    }
    
    public static void renderOffsetAABB(AxisAlignedBB axisalignedbb, double d, double d1, double d2, boolean used)
    {
        GL11.glDisable(3553 /*GL_TEXTURE_2D*/);
        Tessellator tessellator = Tessellator.instance;
        if (used) {
        	GL11.glColor4f(0.0F, 0.0F, 1.0F, 1.0F);
        } else {
        	GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        }
        tessellator.startDrawingQuads();
        tessellator.setTranslationD(d, d1, d2);
        tessellator.setNormal(0.0F, 0.0F, -1F);
        tessellator.addVertex(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.minZ);
        tessellator.addVertex(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.minZ);
        tessellator.addVertex(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.minZ);
        tessellator.addVertex(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.minZ);
        tessellator.setNormal(0.0F, 0.0F, 1.0F);
        tessellator.addVertex(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.maxZ);
        tessellator.addVertex(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.maxZ);
        tessellator.addVertex(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.maxZ);
        tessellator.addVertex(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.maxZ);
        tessellator.setNormal(0.0F, -1F, 0.0F);
        tessellator.addVertex(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.minZ);
        tessellator.addVertex(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.minZ);
        tessellator.addVertex(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.maxZ);
        tessellator.addVertex(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.maxZ);
        tessellator.setNormal(0.0F, 1.0F, 0.0F);
        tessellator.addVertex(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.maxZ);
        tessellator.addVertex(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.maxZ);
        tessellator.addVertex(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.minZ);
        tessellator.addVertex(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.minZ);
        tessellator.setNormal(-1F, 0.0F, 0.0F);
        tessellator.addVertex(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.maxZ);
        tessellator.addVertex(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.maxZ);
        tessellator.addVertex(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.minZ);
        tessellator.addVertex(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.minZ);
        tessellator.setNormal(1.0F, 0.0F, 0.0F);
        tessellator.addVertex(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.minZ);
        tessellator.addVertex(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.minZ);
        tessellator.addVertex(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.maxZ);
        tessellator.addVertex(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.maxZ);
        tessellator.setTranslationD(0.0D, 0.0D, 0.0D);
        tessellator.draw();
        GL11.glEnable(3553 /*GL_TEXTURE_2D*/);
    }

    public static int stringColor = 0x888888;

}