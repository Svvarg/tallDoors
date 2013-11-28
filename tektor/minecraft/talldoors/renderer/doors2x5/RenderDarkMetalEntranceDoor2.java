package tektor.minecraft.talldoors.renderer.doors2x5;

import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RenderDarkMetalEntranceDoor2 extends RenderEntranceDoor2{

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return new ResourceLocation(
				"talldoors:textures/entities/modelEntrance3.png");
	}
}
