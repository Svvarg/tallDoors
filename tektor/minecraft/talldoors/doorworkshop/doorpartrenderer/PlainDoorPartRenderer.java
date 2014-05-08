package tektor.minecraft.talldoors.doorworkshop.doorpartrenderer;

import tektor.minecraft.talldoors.renderer.RenderUtil;

import tektor.minecraft.talldoors.doorworkshop.entity.doorparts.AbstractDoorPart;
import tektor.minecraft.talldoors.doorworkshop.entity.doorparts.PlainDoorPartEntity;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class PlainDoorPartRenderer extends AbstractModuleDoorRenderer {

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

		AbstractDoorPart ent = (AbstractDoorPart) entity;
		RenderUtil.renderFrontBack(ent.height2, 1, ent.depth, 0, entity);

		this.bindTexture(new ResourceLocation("talldoors:textures/doorparts/side.png"));
		RenderUtil.renderOutline(ent.height2, 1, ent.depth, 0, ent);	
	}
	

}
