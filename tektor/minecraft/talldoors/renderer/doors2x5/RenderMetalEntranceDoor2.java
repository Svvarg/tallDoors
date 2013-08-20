package tektor.minecraft.talldoors.renderer.doors2x5;

import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RenderMetalEntranceDoor2 extends RenderEntranceDoor2 {

	@Override
	protected ResourceLocation func_110775_a(Entity entity) {
		return new ResourceLocation(
				"talldoors:textures/entities/modelEntrance2.png");
	}
}
