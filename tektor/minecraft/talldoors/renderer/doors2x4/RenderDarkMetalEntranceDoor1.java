package tektor.minecraft.talldoors.renderer.doors2x4;

import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RenderDarkMetalEntranceDoor1 extends RenderEntranceDoorSize4{

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return new ResourceLocation(
				"talldoors:textures/entities/modelEntrance3.png");
	}
}
