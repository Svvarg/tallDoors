package tektor.minecraft.talldoors.doorworkshop.doorpartrenderer;

import org.lwjgl.opengl.GL11;

import tektor.minecraft.talldoors.doorworkshop.entity.DoorBase;
import tektor.minecraft.talldoors.doorworkshop.entity.doorparts.PlainDoorPartEntity;
import tektor.minecraft.talldoors.renderer.RenderUtil;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class DoorBaseRenderer extends Render {

	@Override
	public void doRender(Entity entity, double x, double y, double z,
			float var8, float var9) {
		GL11.glPushMatrix();

		int orientation = ((DoorBase) entity).orientation;
		int pos = ((DoorBase) entity).pos;
		int drop = 0;
		if (pos == 0)
			drop = 1;
		if (orientation == 0) {
			GL11.glTranslatef((float) x + 1f, (float) y, (float) z + 0.2f
					* drop);
			int i = 0;
			i = 1;
			GL11.glRotatef(180f + i * pos * 90f, 0f, 1f, 0f);
		} else if (orientation == 1) {
			GL11.glTranslatef((float) x + 0.75f+0.25f*pos, (float) y, (float) z+1);
			GL11.glRotatef(90f + pos * 90f, 0f, 1f, 0f);
		} else if (orientation == 2) {

			GL11.glTranslatef((float) x, (float) y, (float) z + 0.75f +0.25f
					* pos);
			// GL11.glRotatef(180f, 0f, 0f, 1f);
			int i = 0;

			i = 1;
			GL11.glRotatef(0f + i * pos * 90f, 0f, 1f, 0f);
		} else if (orientation == 3) {

			GL11.glTranslatef((float) x + 0.25f*drop, (float) y, (float) z);
			int i = 0;

			i = 1;
			GL11.glRotatef(270f + i * pos * 90f, 0f, 1f, 0f);
		}
		GL11.glScalef(1f, 1f, 1f);
		
		GL11.glPopMatrix();

	}

	@Override
	protected ResourceLocation getEntityTexture(Entity var1) {
		return new ResourceLocation("talldoors:textures/doorparts/side.png");
	}

}
