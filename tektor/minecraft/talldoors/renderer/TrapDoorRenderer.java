package tektor.minecraft.talldoors.renderer;

import org.lwjgl.opengl.GL11;

import tektor.minecraft.talldoors.entities.trapdoors.TrapDoor;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class TrapDoorRenderer extends Render {

	@Override
	public void doRender(Entity entity, double d0, double d1, double d2,
			float f, float f1) {
		TrapDoor base = (TrapDoor) entity;
		GL11.glPushMatrix();
		// GL11.glTranslatef((float)d0, (float)d1, (float)d2);
		Minecraft.getMinecraft().renderEngine
				.bindTexture(getEntityTexture(entity));
		Tessellator tess = Tessellator.instance;
		tess.startDrawingQuads();

		switch (base.orientation) {
		case 0:
			GL11.glTranslatef((float) d0, (float) d1, (float) d2);
			break;
		case 1:
			GL11.glTranslatef((float) d0 + 1, (float) d1, (float) d2);
			GL11.glRotated(270, 0, 1, 0);
			break;
		case 2:
			GL11.glTranslatef((float) d0 + 1, (float) d1, (float) d2 + 1);
			GL11.glRotated(180, 0, 1, 0);
			break;
		case 3:
			GL11.glTranslatef((float) d0, (float) d1, (float) d2 + 1);
			GL11.glRotated(90, 0, 1, 0);
			break;
		}
		drawBase(tess, 0.125f, base.width2, base.depth2, base);

		GL11.glPopMatrix();
	}

	private void drawBase(Tessellator tess, float f, int width2, int depth2,
			TrapDoor base) {
		if (!base.open) {
			// front
			tess.addVertexWithUV(0, 0, 0, width2, 0.0);
			tess.addVertexWithUV(0, 0 + f, 0, width2, 0.01);
			tess.addVertexWithUV(width2, 0 + f, 0, 0, 0.01);
			tess.addVertexWithUV(width2, 0, 0, 0, 0);
			// top
			tess.addVertexWithUV(0, f, 0, width2, 0);
			tess.addVertexWithUV(0, f, depth2, width2, depth2);
			tess.addVertexWithUV(width2, f, depth2, 0, depth2);
			tess.addVertexWithUV(width2, f, 0, 0, 0);
			// right
			tess.addVertexWithUV(0, 0, 0, 0, 0);
			tess.addVertexWithUV(0, 0, depth2, depth2, 0);
			tess.addVertexWithUV(0, f, depth2, depth2, 0.01);
			tess.addVertexWithUV(0, f, 0, 0, 0.01);
			// bottom
			tess.addVertexWithUV(0, 0, 0, width2, 0);
			tess.addVertexWithUV(width2, 0, 0, 0, 0);
			tess.addVertexWithUV(width2, 0, depth2, 0, depth2);
			tess.addVertexWithUV(0, 0, depth2, width2, depth2);
			// left
			tess.addVertexWithUV(width2, 0, 0, 0, 0);
			tess.addVertexWithUV(width2, f, 0, 0, 0.01);
			tess.addVertexWithUV(width2, f, depth2, depth2, 0.01);
			tess.addVertexWithUV(width2, 0, depth2, depth2, 0);
			// back
			tess.addVertexWithUV(0, 0, depth2, width2, 0.0);
			tess.addVertexWithUV(width2, 0, depth2, 0, 0);
			tess.addVertexWithUV(width2, f, depth2, 0, 0.01);
			tess.addVertexWithUV(0, f, depth2, width2, 0.01);
		}
		else
		{
			//top
			tess.addVertexWithUV(0, f, 0, 0, 0);
			tess.addVertexWithUV(0, f, f, 0, 0.01);
			tess.addVertexWithUV(width2, f, f, width2, 0.01);
			tess.addVertexWithUV(width2, f, 0, width2, 0);
			
			//out
			tess.addVertexWithUV(0, f, f, 0, 0);
			tess.addVertexWithUV(0, f-depth2, f, 0, depth2);
			tess.addVertexWithUV(width2, f-depth2, f, width2, depth2);
			tess.addVertexWithUV(width2, f, f, width2, 0);
			
			//bottom
			tess.addVertexWithUV(0, f-depth2, 0, 0, 0);
			tess.addVertexWithUV(width2, f-depth2, 0, width2, 0);
			tess.addVertexWithUV(width2, f-depth2, f, width2, 0.01);
			tess.addVertexWithUV(0, f-depth2, f, 0, 0.01);
			
			//in
			tess.addVertexWithUV(0, f, 0, 0, 0);
			tess.addVertexWithUV(width2, f, 0, width2, 0);
			tess.addVertexWithUV(width2, f-depth2, 0, width2, depth2);
			tess.addVertexWithUV(0, f-depth2, 0, 0, depth2);
			
			//right
			tess.addVertexWithUV(0, f, 0, 0, 0);
			tess.addVertexWithUV(0, f-depth2, 0, depth2, 0);
			tess.addVertexWithUV(0, f-depth2, f, depth2, 0.01);
			tess.addVertexWithUV(0, f, f, 0, 0.01);
			
			//left
			tess.addVertexWithUV(width2, f, 0, 0, 0);
			tess.addVertexWithUV(width2, f, f, 0, 0.01);
			tess.addVertexWithUV(width2, f-depth2, f, depth2, 0.01);
			tess.addVertexWithUV(width2, f-depth2, 0, depth2, 0);
		}
		tess.draw();
		
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return new ResourceLocation(
				"talldoors:textures/entities/trapDoorTile.png");
	}

}
