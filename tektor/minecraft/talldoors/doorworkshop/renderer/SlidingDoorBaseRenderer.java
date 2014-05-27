package tektor.minecraft.talldoors.doorworkshop.renderer;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class SlidingDoorBaseRenderer extends Render {

	@Override
	public void doRender(Entity var1, double var2, double var4, double var6,
			float var8, float var9) {

	}

	@Override
	protected ResourceLocation getEntityTexture(Entity var1) {
		return new ResourceLocation("talldoors:textures/doorparts/side.png");
	}

}
