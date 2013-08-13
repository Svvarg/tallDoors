package tektor.minecraft.talldoors.renderer.doors2x4;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import tektor.minecraft.talldoors.entities.doors_width2.DarkMetalEntranceDoor1;
import tektor.minecraft.talldoors.models.ModelEntranceDoor1;

public class RenderDarkMetalEntranceDoor1 extends RenderEntranceDoorSize4{

	@Override
	protected ResourceLocation func_110775_a(Entity entity) {
		return new ResourceLocation(
				"talldoors:textures/entities/modelEntrance3.png");
	}
}
