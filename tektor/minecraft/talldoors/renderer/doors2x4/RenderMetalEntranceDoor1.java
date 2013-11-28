package tektor.minecraft.talldoors.renderer.doors2x4;

import org.lwjgl.opengl.GL11;

import tektor.minecraft.talldoors.entities.doors_width2.MetalEntranceDoor1;
import tektor.minecraft.talldoors.models.ModelEntranceDoor1;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RenderMetalEntranceDoor1 extends RenderEntranceDoorSize4{
	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return new ResourceLocation(
				"talldoors:textures/entities/modelEntrance2.png");
	}

}