package tektor.minecraft.talldoors.renderer;

import org.lwjgl.opengl.GL11;

import tektor.minecraft.talldoors.entities.FenceGate1;
import tektor.minecraft.talldoors.models.ModelEntranceDoor1;
import tektor.minecraft.talldoors.models.ModelFenceGate1;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RenderFenceGate1 extends Render {

	public RenderFenceGate1() {
		modelFenceGate1 = new ModelFenceGate1();
	}

	protected ModelFenceGate1 modelFenceGate1;

	@Override
	public void doRender(Entity entity, double x, double y, double z, float f,
			float f1) {
		// Push a blank matrix onto the stack
		GL11.glPushMatrix();

		int orientation = ((FenceGate1) entity).orientation;
		int pos = ((FenceGate1) entity).pos;
		boolean left = ((FenceGate1) entity).left;
		//System.out.println(orientation);
		if ( orientation == 0) {
			if (left) {

				if ( pos == 1) {
					x = x + 1.2f;
					z = z + 0.5f;
				} else {
					z = z + 1.3f;
					x = x + 0.2f;
				}
			}
			else
			{
				if(pos == 1)
				{
					x = x -0.3f;
					z = z +0.5f;
				}
				else
				{
					x = x -0.2f;
					z = z +0.3f;
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
					x = x - 1.3f;
					z = z +0.4f;
				} else {
					x = x - 1.2f;
					z = z + 0.2f;
					
				}
				
			}
			else
			{
				if (pos == 1) {
					x = x - 1.3f;
					z = z - 1.1f;
				} else {
					x = x - 0.3f;
					z = z - 0.2f;
					
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
					x = x -1.2f;
					z = z -0.5f;
				} else {
					z = z - 1.2f;
					x = x + -0.2f;
				}
			
			}
			else{
				if(pos == 1)
				{
					x = x + 0.3f;
					z = z - 0.5f;
				}
				else
				{
				z = z - 0.4f;
				x = x + 0.2f;
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
					x = x - 0.2f;
					z = z - 2.0f;
				} else {
					x = x - 0.3f;
					z = z -0.2f;
					
				}
				
			}
			else
			{
				if (pos == 1) {
					x = x - 0.2f;
					z = z - 0.5f;
				} else {
					x = x - 1.3f;
					z = z + 0.2f;
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
		GL11.glScalef(1.35f, 1f, 1f);
		this.func_110776_a(this.func_110775_a(entity));
		this.modelFenceGate1.render(entity, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F,
				0.0625F);
		GL11.glPopMatrix();

	}

	@Override
	protected ResourceLocation func_110775_a(Entity entity) {
		return new ResourceLocation(
				"talldoors:textures/entities/modelFenceGate1.png");
	}

}
