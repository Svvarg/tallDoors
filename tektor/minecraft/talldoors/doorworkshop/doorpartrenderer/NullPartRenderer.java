package tektor.minecraft.talldoors.doorworkshop.doorpartrenderer;

import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class NullPartRenderer extends AbstractModuleDoorRenderer{

	@Override
	protected ResourceLocation getEntityTexture(Entity var1) {
		return null;
	}

}
