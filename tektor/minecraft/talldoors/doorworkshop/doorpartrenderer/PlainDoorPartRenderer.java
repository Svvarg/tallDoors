package tektor.minecraft.talldoors.doorworkshop.doorpartrenderer;

import org.lwjgl.opengl.GL11;

import tektor.minecraft.talldoors.renderer.RenderUtil;

import tektor.minecraft.talldoors.doorworkshop.entity.doorparts.PlainDoorPartEntity;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class PlainDoorPartRenderer extends AbstractModuleDoorRenderer {

	@Override
	public void doRender(Entity entity, double x, double y, double z,
			float var8, float var9) {
		super.doRender(entity, x, y, z, var8, var9);
		
		this.bindTexture(this.getEntityTexture(entity));
		
		RenderUtil.renderFrontBack(((PlainDoorPartEntity)entity).height2, 1, ((PlainDoorPartEntity)entity).depth, 0, entity);
		GL11.glPopMatrix();
		
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity var1) {
		return new ResourceLocation("talldoors:textures/doorparts/plain.png");
	}

}
