package tektor.minecraft.talldoors.doorworkshop.renderer;

import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class NullPartRenderer extends AbstractModuleDoorRenderer{

	@Override
	public void doRender(Entity entity, double x, double y, double z,
			float var8, float var9){
		super.doRender(entity, x, y, z,
				var8, var9);
	}
	
	@Override
	protected ResourceLocation getEntityTexture(Entity var1) {
		return new ResourceLocation("talldoors:textures/doorparts/plain.png");
	}

	@Override
	protected void renderingStuff(Entity entity, double x, double y, double z,
			float var8, float var9) {
	}

}
