package tektor.minecraft.talldoors.doorworkshop.renderer.balks;

import org.lwjgl.opengl.GL11;

import tektor.minecraft.talldoors.doorworkshop.DoorPartRegistry;
import tektor.minecraft.talldoors.doorworkshop.entity.doorparts.AbstractDoorPart;
import tektor.minecraft.talldoors.doorworkshop.entity.doorparts.balks.DoubleHorizontalBalkPartEntity;
import tektor.minecraft.talldoors.doorworkshop.renderer.AbstractModuleDoorRenderer;
import tektor.minecraft.talldoors.renderer.RenderUtil;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class DoubleHorizontalBalkPartRenderer extends AbstractModuleDoorRenderer{

	@Override
	public void doRender(Entity entity, double x, double y, double z,
			float var8, float var9) {
		super.doRender(entity, x, y, z, var8, var9);	
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity var1) {
		return new ResourceLocation(DoorPartRegistry.texturePaths.get(((AbstractDoorPart) var1).texture1));
	}

	@Override
	protected void renderingStuff(Entity entity, double x, double y, double z,
			float var8, float var9) {
		this.bindTexture(this.getEntityTexture(entity));
		DoubleHorizontalBalkPartEntity ent = (DoubleHorizontalBalkPartEntity)entity;

		RenderUtil.renderFrontBack(ent.height2, 1, ent.depth, 0, ent);

		this.bindTexture(new ResourceLocation(DoorPartRegistry.texturePaths.get(ent.sideTexture)));
		RenderUtil.renderOutline(ent.height2, 1, ent.depth, 0, ent);
		
		this.bindTexture(new ResourceLocation(DoorPartRegistry.texturePaths.get(ent.texture2)));
		GL11.glTranslatef(0f, 0.5f, ent.depth);
		RenderUtil.renderCuboid(ent, 1, 0.25f, +0.125f, 0);
		for(int i = 1; i < ent.height2; i++)
		{
			GL11.glTranslatef(0, 1, 0);
			RenderUtil.renderCuboid(ent, 1, 0.25f, 0.125f, 0);			
		}
		GL11.glTranslatef(0, (float) -(ent.height2-1), 0);
		GL11.glTranslatef(1f, 0f, -(ent.depth));
		GL11.glRotatef(180f, 0, 1, 0);
		RenderUtil.renderCuboid(ent, 1, 0.25f, 0.125f, 0);
		for(int i = 1; i < ent.height2; i++)
		{
			GL11.glTranslatef(0, 1, 0);
			RenderUtil.renderCuboid(ent, 1, 0.25f, 0.125f, 0);			
		}
		
	}

}
