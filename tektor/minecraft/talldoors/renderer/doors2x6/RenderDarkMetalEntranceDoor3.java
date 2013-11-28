package tektor.minecraft.talldoors.renderer.doors2x6;

import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RenderDarkMetalEntranceDoor3 extends RenderEntranceDoor3{
	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return new ResourceLocation(
				"talldoors:textures/entities/modelEntrance3.png");
	}
}
