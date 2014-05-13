package tektor.minecraft.talldoors.doorworkshop.renderer.balks;

import org.lwjgl.opengl.GL11;

import tektor.minecraft.talldoors.doorworkshop.entity.doorparts.balks.DoublePlusBalkPartEntity;
import tektor.minecraft.talldoors.doorworkshop.renderer.AbstractModuleDoorRenderer;
import tektor.minecraft.talldoors.renderer.RenderUtil;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class DoublePlusBalkPartRenderer extends AbstractModuleDoorRenderer {

	@Override
	public void doRender(Entity entity, double x, double y, double z,
			float var8, float var9) {
		super.doRender(entity, x, y, z, var8, var9);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity var1) {
		return new ResourceLocation("talldoors:textures/doorparts/plain.png");
	}

	@Override
	protected void renderingStuff(Entity entity, double x, double y, double z,
			float var8, float var9) {
		this.bindTexture(this.getEntityTexture(entity));
		DoublePlusBalkPartEntity ent = (DoublePlusBalkPartEntity)entity;
		RenderUtil.renderFrontBack(ent.height2, 1, ent.depth, 0, ent);
		this.bindTexture(new ResourceLocation("talldoors:textures/doorparts/side.png"));
		RenderUtil.renderOutline(ent.height2, 1, ent.depth, 0, ent);
		this.bindTexture(new ResourceLocation("talldoors:textures/doorparts/horizontalBalk.png"));
		GL11.glTranslatef(0.375f, 0, ent.depth);
		RenderUtil.renderCuboid(ent, 0.25, ent.height2, +0.125f, 0);
		GL11.glTranslatef(-0.375f, 0.5f, 0);
		RenderUtil.renderCuboid(ent, 0.375, 0.25f, +0.125f, 0);
		GL11.glTranslatef(0.625f, 0, 0);
		RenderUtil.renderCuboid(ent, 0.375, 0.25f, +0.125f, 0);
		for(int i = 1; i < ent.height2; i++)
		{
			GL11.glTranslatef(-0.625f, 1, 0);		
			RenderUtil.renderCuboid(ent, 0.375, 0.25f, +0.125f, 0);
			GL11.glTranslatef(0.625f, 0, 0);
			RenderUtil.renderCuboid(ent, 0.375, 0.25f, +0.125f, 0);
		}
		GL11.glTranslatef(0, (float) -(ent.height2-0.5), 0);
		GL11.glTranslatef(0, 0f, -(ent.depth));
		GL11.glRotatef(180f, 0, 1, 0);
		RenderUtil.renderCuboid(ent, 0.25, ent.height2, +0.125f, 0);
		GL11.glTranslatef(-0.375f, 0.5f, 0);
		RenderUtil.renderCuboid(ent, 0.375, 0.25f, +0.125f, 0);
		GL11.glTranslatef(0.625f, 0, 0);
		RenderUtil.renderCuboid(ent, 0.375, 0.25f, +0.125f, 0);
		for(int i = 1; i < ent.height2; i++)
		{
			GL11.glTranslatef(-0.625f, 1, 0);		
			RenderUtil.renderCuboid(ent, 0.375, 0.25f, +0.125f, 0);
			GL11.glTranslatef(0.625f, 0, 0);
			RenderUtil.renderCuboid(ent, 0.375, 0.25f, +0.125f, 0);
		}
		
	}

}
