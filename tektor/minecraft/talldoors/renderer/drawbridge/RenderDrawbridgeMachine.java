package tektor.minecraft.talldoors.renderer.drawbridge;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import tektor.minecraft.talldoors.entities.doors_width2.EntranceDoor2;
import tektor.minecraft.talldoors.entities.drawbridge.DrawbridgeMachine;
import tektor.minecraft.talldoors.models.ModelDrawbridgeMachine;
import tektor.minecraft.talldoors.models.ModelEntranceDoor2;

public class RenderDrawbridgeMachine extends Render {

	public RenderDrawbridgeMachine() {
		modeldrawmachine = new ModelDrawbridgeMachine();
	}

	protected ModelDrawbridgeMachine modeldrawmachine;

	@Override
	public void doRender(Entity entity, double x, double y, double z, float f,
			float f1) {
		// Push a blank matrix onto the stack
		GL11.glPushMatrix();
		GL11.glTranslatef((float) x + 0.5f, (float) y + 1.5f, (float) z + 0.5f);

		GL11.glRotatef(180f, 0f, 0f, 1f);
		DrawbridgeMachine mach = (DrawbridgeMachine) entity;
		switch(mach.orientation)
		{
			case 0: GL11.glRotatef(0f, 0f, 1f, 0f); break;
			case 1:GL11.glRotatef(90f, 0f, 1f, 0f); break;
			case 2:GL11.glRotatef(180f, 0f, 1f, 0f); break;
			case 3:GL11.glRotatef(270f, 0f, 1f, 0f); break;
		}

		GL11.glScalef(1f, 1f, 1f);
		this.func_110776_a(this.func_110775_a(entity));
		this.modeldrawmachine.render(entity, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F,
				0.0625F);
		GL11.glPopMatrix();

	}

	@Override
	protected ResourceLocation func_110775_a(Entity entity) {
		return new ResourceLocation(
				"talldoors:textures/entities/drawbridgeMachine.png");
	}

}
