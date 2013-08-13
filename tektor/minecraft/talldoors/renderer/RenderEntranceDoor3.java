package tektor.minecraft.talldoors.renderer;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import tektor.minecraft.talldoors.models.ModelEntranceDoor3;
import tektor.minecraft.talldoors.entities.doors_width2.EntranceDoor3;

public class RenderEntranceDoor3 extends Render {

	public RenderEntranceDoor3() {
		modelEntranceDoor3 = new ModelEntranceDoor3();
	}

	protected ModelEntranceDoor3 modelEntranceDoor3;

	@Override
	public void doRender(Entity entity, double x, double y, double z, float f,
			float f1) {
		// Push a blank matrix onto the stack
		GL11.glPushMatrix();

		int orientation = ((EntranceDoor3) entity).orientation;
		int pos = ((EntranceDoor3) entity).pos;
		boolean left = ((EntranceDoor3) entity).left;
		//System.out.println(orientation);
		if ( orientation == 0) {
			if (left) {

				if ( pos == 1) {
					x = x + 0.7f;
				} else {
					z = z + 0.7f;
				}
			}
			GL11.glTranslatef((float) x + 0.5f, (float) y + 1.5f, (float) z
					- 0.3f + 0.8f * pos);
			GL11.glRotatef(180f, 0f, 0f, 1f);
			int i = 0;
			if (left) {
				GL11.glRotatef(180f, 0f, 1f, 0f);
				i = -1;

			} else
				i = 1;
			GL11.glRotatef(180f + i * pos * 90f, 0f,
					1f, 0f);
		}
		else if(orientation == 1)
		{
			if (left) {

				if (pos == 1) {
					x = x - 0.8f;
					z = z - 0.1f;
				} else {
					x = x - 0.7f;
					
				}
				
			}
			else
			{
				if (pos == 1) {
					x = x - 0.8f;
					z = z - 0.8f;
				} else {
					
				}
			}
			GL11.glTranslatef((float) x + 1.3f, (float) y + 1.5f, (float) z
					+0.5f + 0.8f * pos);
			GL11.glRotatef(180f, 0f, 0f, 1f);
			int i = 0;
			if (left) {
				GL11.glRotatef(180f, 0f, 1f, 0f);
				i = -1;

			} else
				i = 1;
			GL11.glRotatef(270f + i * pos * 90f, 0f,
					1f, 0f);
		}
		else if(orientation == 2)
		{
			if (left) {

				if ( pos == 1) {
					x = x -0.7f;
				} else {
					z = z - 0.7f;
				}
			
			}
			GL11.glTranslatef((float) x + 0.5f, (float) y + 1.5f, (float) z
					+1.3f - 0.8f * pos);
			GL11.glRotatef(180f, 0f, 0f, 1f);
			int i = 0;
			if (left) {
				GL11.glRotatef(180f, 0f, 1f, 0f);
				i = -1;

			} else
				i = 1;
			GL11.glRotatef(0f + i * pos * 90f, 0f,
					1f, 0f);
		}
		else if(orientation == 3)
		{
			if (left) {

				if (pos == 1) {
					x = x - 0.8f;
					z = z - 1.5f;
				} else {
					x = x - 0.9f;
					
				}
				
			}
			else
			{
				if (pos == 1) {
					x = x - 0.8f;
					z = z - 0.8f;
				} else {
					x = x - 1.6f;
				}
			}
			GL11.glTranslatef((float) x + 1.3f, (float) y + 1.5f, (float) z
					+0.5f + 0.8f * pos);
			GL11.glRotatef(180f, 0f, 0f, 1f);
			int i = 0;
			if (left) {
				GL11.glRotatef(180f, 0f, 1f, 0f);
				i = -1;

			} else
				i = 1;
			GL11.glRotatef(90f + i * pos * 90f, 0f,
					1f, 0f);
		}
		GL11.glScalef(1f, 1f, 1f);
		this.func_110776_a(this.func_110775_a(entity));
		this.modelEntranceDoor3.render(entity, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F,
				0.0625F);
		GL11.glPopMatrix();

	}

	@Override
	protected ResourceLocation func_110775_a(Entity entity) {
		return new ResourceLocation(
				"talldoors:textures/entities/modelEntrance.png");
	}

}
