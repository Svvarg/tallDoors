package tektor.minecraft.talldoors.doorworkshop.renderer;

import tektor.minecraft.talldoors.renderer.RenderUtil;

import tektor.minecraft.talldoors.doorworkshop.DoorPartRegistry;
import tektor.minecraft.talldoors.doorworkshop.entity.doorparts.AbstractDoorPart;
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
		return new ResourceLocation(DoorPartRegistry.texturePaths.get(((AbstractDoorPart) var1).texture1));
	}

	@Override
	protected void renderingStuff(Entity entity, double x, double y, double z,
			float var8, float var9) {

		this.bindTexture(this.getEntityTexture(entity));

		AbstractDoorPart ent = (AbstractDoorPart) entity;
		RenderUtil.renderFrontBack(ent.height2, 1, ent.depth, 0, entity);

		this.bindTexture(new ResourceLocation(DoorPartRegistry.texturePaths.get(ent.sideTexture)));
		RenderUtil.renderOutline(ent.height2, 1, ent.depth, 0, ent);	
	}
	

}
