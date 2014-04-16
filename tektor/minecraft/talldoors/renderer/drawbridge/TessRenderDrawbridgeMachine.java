package tektor.minecraft.talldoors.renderer.drawbridge;

import org.lwjgl.opengl.GL11;

import tektor.minecraft.talldoors.entities.drawbridge.DrawbridgeMachine;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class TessRenderDrawbridgeMachine extends Render {

	@Override
	public void doRender(Entity entity, double d0, double d1, double d2,
			float f, float f1) {
		DrawbridgeMachine base = (DrawbridgeMachine) entity;
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
		drawBase(tess, base.height2, base.width2, base.lon, base.rotation, base);

		GL11.glPopMatrix();

	}

	private void drawBase(Tessellator tess, double height2, double width2,
			double lon, double rotation, DrawbridgeMachine base) {
		// spool 1 out
		// front
		tess.addVertexWithUV(0, 0.125, 0, 0, 0);
		tess.addVertexWithUV(0, height2, 0, 0, 0.1 * height2);
		tess.addVertexWithUV(lon, height2, 0, 0.1 * lon, 0.1 * height2);
		tess.addVertexWithUV(lon, 0.125, 0, 0.1 * lon, 0);
		// right
		tess.addVertexWithUV(0, 0.125, 0, 0, 0);
		tess.addVertexWithUV(0, 0.125, 0.125, 0.01, 0);
		tess.addVertexWithUV(0, height2, 0.125, 0.01, 0.1 * height2);
		tess.addVertexWithUV(0, height2, 0, 0, 0.1 * height2);
		// back
		tess.addVertexWithUV(0, 0.125, 0.125, 0, 0);
		tess.addVertexWithUV(lon, 0.125, 0.125, 0.1 * lon, 0);
		tess.addVertexWithUV(lon, height2, 0.125, 0.1 * lon, 0.1 * height2);
		tess.addVertexWithUV(0, height2, 0.125, 0, 0.1 * height2);
		// left
		tess.addVertexWithUV(lon, 0.125, 0, 0, 0);
		tess.addVertexWithUV(lon, height2, 0, 0, 0.1 * height2);
		tess.addVertexWithUV(lon, height2, 0.125, 0.01, 0.1 * height2);
		tess.addVertexWithUV(lon, 0.125, 0.125, 0.01, 0);
		// top
		tess.addVertexWithUV(0, height2, 0, 0, 0);
		tess.addVertexWithUV(0, height2, 0.125, 0, 0.01);
		tess.addVertexWithUV(lon, height2, 0.125, 0.1 * lon, 0.01);
		tess.addVertexWithUV(lon, height2, 0, 0.1 * lon, 0);
		// spool 1 in
		// front
		tess.addVertexWithUV(0, 0.125, base.spool - 0.125, 0, 0);
		tess.addVertexWithUV(0, height2, base.spool - 0.125, 0, 0.1 * height2);
		tess.addVertexWithUV(lon, height2, base.spool - 0.125, 0.1 * lon,
				0.1 * height2);
		tess.addVertexWithUV(lon, 0.125, base.spool - 0.125, 0.1 * lon, 0);
		// right
		tess.addVertexWithUV(0, 0.125, base.spool - 0.125, 0, 0);
		tess.addVertexWithUV(0, 0.125, base.spool, 0.01, 0);
		tess.addVertexWithUV(0, height2, base.spool, 0.01, 0.1 * height2);
		tess.addVertexWithUV(0, height2, base.spool - 0.125, 0, 0.1 * height2);
		// back
		tess.addVertexWithUV(0, 0.125, base.spool, 0, 0);
		tess.addVertexWithUV(lon, 0.125, base.spool, 0.1 * lon, 0);
		tess.addVertexWithUV(lon, height2, base.spool, 0.1 * lon, 0.1 * height2);
		tess.addVertexWithUV(0, height2, base.spool, 0, 0.1 * height2);
		// left
		tess.addVertexWithUV(lon, 0.125, base.spool - 0.125, 0, 0);
		tess.addVertexWithUV(lon, height2, base.spool - 0.125, 0, 0.1 * height2);
		tess.addVertexWithUV(lon, height2, base.spool, 0.01, 0.1 * height2);
		tess.addVertexWithUV(lon, 0.125, base.spool, 0.01, 0);
		// top
		tess.addVertexWithUV(0, height2, base.spool - 0.125, 0, 0);
		tess.addVertexWithUV(0, height2, base.spool, 0, 0.01);
		tess.addVertexWithUV(lon, height2, base.spool, 0.1 * lon, 0.01);
		tess.addVertexWithUV(lon, height2, base.spool - 0.125, 0.1 * lon, 0);

		// spool 2 out
		// front
		tess.addVertexWithUV(0, 0.125, width2 - 0.125, 0, 0);
		tess.addVertexWithUV(0, height2, width2 - 0.125, 0, 0.1 * height2);
		tess.addVertexWithUV(lon, height2, width2 - 0.125, 0.1 * lon,
				0.1 * height2);
		tess.addVertexWithUV(lon, 0.125, width2 - 0.125, 0.1 * lon, 0);
		// right
		tess.addVertexWithUV(0, 0.125, width2 - 0.125, 0, 0);
		tess.addVertexWithUV(0, 0.125, width2, 0.01, 0);
		tess.addVertexWithUV(0, height2, width2, 0.01, 0.1 * height2);
		tess.addVertexWithUV(0, height2, width2 - 0.125, 0, 0.1 * height2);
		// back
		tess.addVertexWithUV(0, 0.125, width2, 0, 0);
		tess.addVertexWithUV(lon, 0.125, width2, 0.1 * lon, 0);
		tess.addVertexWithUV(lon, height2, width2, 0.1 * lon, 0.1 * height2);
		tess.addVertexWithUV(0, height2, width2, 0, 0.1 * height2);
		// left
		tess.addVertexWithUV(lon, 0.125, width2 - 0.125, 0, 0);
		tess.addVertexWithUV(lon, height2, width2 - 0.125, 0, 0.1 * height2);
		tess.addVertexWithUV(lon, height2, width2, 0.01, 0.1 * height2);
		tess.addVertexWithUV(lon, 0.125, width2, 0.01, 0);
		// top
		tess.addVertexWithUV(0, height2, width2 - 0.125, 0, 0);
		tess.addVertexWithUV(0, height2, width2, 0, 0.01);
		tess.addVertexWithUV(lon, height2, width2, 0.1 * lon, 0.01);
		tess.addVertexWithUV(lon, height2, width2 - 0.125, 0.1 * lon, 0);

		// spool 2 in
		// front
		tess.addVertexWithUV(0, 0.125, width2 - base.spool, 0, 0);
		tess.addVertexWithUV(0, height2, width2 - base.spool, 0, 0.1 * height2);
		tess.addVertexWithUV(lon, height2, width2 - base.spool, 0.1 * lon,
				0.1 * height2);
		tess.addVertexWithUV(lon, 0.125, width2 - base.spool, 0.1 * lon, 0);
		// right
		tess.addVertexWithUV(0, 0.125, width2 - base.spool, 0, 0);
		tess.addVertexWithUV(0, 0.125, width2 - base.spool + 0.125, 0.01, 0);
		tess.addVertexWithUV(0, height2, width2 - base.spool + 0.125, 0.01,
				0.1 * height2);
		tess.addVertexWithUV(0, height2, width2 - base.spool, 0, 0.1 * height2);
		// back
		tess.addVertexWithUV(0, 0.125, width2 - base.spool + 0.125, 0, 0);
		tess.addVertexWithUV(lon, 0.125, width2 - base.spool + 0.125,
				0.1 * lon, 0);
		tess.addVertexWithUV(lon, height2, width2 - base.spool + 0.125,
				0.1 * lon, 0.1 * height2);
		tess.addVertexWithUV(0, height2, width2 - base.spool + 0.125, 0,
				0.1 * height2);
		// left
		tess.addVertexWithUV(lon, 0.125, width2 - base.spool, 0, 0);
		tess.addVertexWithUV(lon, height2, width2 - base.spool, 0,
				0.1 * height2);
		tess.addVertexWithUV(lon, height2, width2 - base.spool + 0.125, 0.01,
				0.1 * height2);
		tess.addVertexWithUV(lon, 0.125, width2 - base.spool + 0.125, 0.01, 0);
		// top
		tess.addVertexWithUV(0, height2, width2 - base.spool, 0, 0);
		tess.addVertexWithUV(0, height2, width2 - base.spool + 0.125, 0, 0.01);
		tess.addVertexWithUV(lon, height2, width2 - base.spool + 0.125,
				0.1 * lon, 0.01);
		tess.addVertexWithUV(lon, height2, width2 - base.spool, 0.1 * lon, 0);

		// bottom of all
		tess.addVertexWithUV(0, 0, 0, 0, 0);
		tess.addVertexWithUV(0, 0.125, 0, 0, 0.01);
		tess.addVertexWithUV(lon, 0.125, 0, 0.1 * lon, 0.01);
		tess.addVertexWithUV(lon, 0, 0, 0.1 * lon, 0);

		tess.addVertexWithUV(0, 0, 0, 0, 0);
		tess.addVertexWithUV(0, 0, width2, 0.1 * width2, 0);
		tess.addVertexWithUV(0, 0.125, width2, 0.1 * width2, 0.01);
		tess.addVertexWithUV(0, 0.125, 0, 0, 0.01);

		tess.addVertexWithUV(0, 0, width2, 0, 0);
		tess.addVertexWithUV(lon, 0, width2, 0.1 * lon, 0);
		tess.addVertexWithUV(lon, 0.125, width2, 0.1 * lon, 0.01);
		tess.addVertexWithUV(0, 0.125, width2, 0, 0.01);

		tess.addVertexWithUV(lon, 0, width2, 0, 0);
		tess.addVertexWithUV(lon, 0, 0, lon * 0.1, 0);
		tess.addVertexWithUV(lon, 0.125, 0, lon * 0.1, 0.01);
		tess.addVertexWithUV(lon, 0.125, width2, 0, 0.01);

		tess.addVertexWithUV(0, 0.125, 0, 0, 0);
		tess.addVertexWithUV(0, 0.125, width2, 0.1 * width2, 0);
		tess.addVertexWithUV(lon, 0.125, width2, 0.1 * width2, 0.1 * lon);
		tess.addVertexWithUV(lon, 0.125, 0, 0, 0.1 * lon);

		tess.addVertexWithUV(0, 0, 0, 0, 0);
		tess.addVertexWithUV(lon, 0, 0, 0, 0.1 * lon);
		tess.addVertexWithUV(lon, 0, width2, 0.1 * width2, 0.1 * lon);
		tess.addVertexWithUV(0, 0, width2, 0.1 * width2, 0);

		// connector
		tess.addVertexWithUV(0.4375 * lon, 0.4375 * height2, base.spool, 0, 0);
		tess.addVertexWithUV(0.4375 * lon, 0.4375 * height2, width2
				- base.spool, 0.1 * (width2 - 2 * base.spool), 0);
		tess.addVertexWithUV(0.4375 * lon, 0.5625 * height2, width2
				- base.spool, 0.1 * (width2 - 2 * base.spool), 0.1);
		tess.addVertexWithUV(0.4375 * lon, 0.5625 * height2, base.spool, 0, 0.1);
		
		tess.addVertexWithUV(0.5625 * lon, 0.4375 * height2, base.spool, 0, 0);
		tess.addVertexWithUV(0.5625 * lon, 0.5625 * height2, base.spool, 0, 0.1);
		tess.addVertexWithUV(0.5625 * lon, 0.5625 * height2, width2
				- base.spool, 0.1 * (width2 - 2 * base.spool), 0.1);
		tess.addVertexWithUV(0.5625 * lon, 0.4375 * height2, width2
				- base.spool, 0.1 * (width2 - 2 * base.spool), 0);
		
		tess.addVertexWithUV(0.4375 * lon, 0.5625 * height2, base.spool, 0, 0.1);
		tess.addVertexWithUV(0.4375 * lon, 0.5625 * height2, width2
				- base.spool, 0.1 * (width2 - 2 * base.spool), 0.1);
		tess.addVertexWithUV(0.5625 * lon, 0.5625 * height2, width2
				- base.spool, 0.1 * (width2 - 2 * base.spool), 0.1);
		tess.addVertexWithUV(0.5625 * lon, 0.5625 * height2, base.spool, 0, 0.1);
		
		tess.addVertexWithUV(0.4375 * lon, 0.4375 * height2, base.spool, 0, 0.1);
		tess.addVertexWithUV(0.5625 * lon, 0.4375 * height2, base.spool, 0, 0.1);
		tess.addVertexWithUV(0.5625 * lon, 0.4375 * height2, width2
				- base.spool, 0.1 * (width2 - 2 * base.spool), 0.1);
		tess.addVertexWithUV(0.4375 * lon, 0.4375 * height2, width2
				- base.spool, 0.1 * (width2 - 2 * base.spool), 0.1);
		
		tess.draw();

		tess.startDrawingQuads();
		Minecraft.getMinecraft().renderEngine
				.bindTexture(new ResourceLocation(
						"talldoors:textures/entities/drawbridgeBase2.png"));

		// spool 1
		tess.addVertexWithUV(0.25 * lon, 0.25 * height2, 0.125, 0, 0);
		tess.addVertexWithUV(0.25 * lon, 0.25 * height2, base.spool - 0.125, 1,
				0);
		tess.addVertexWithUV(0.25 * lon, 0.75 * height2, base.spool - 0.125, 1,
				1);
		tess.addVertexWithUV(0.25 * lon, 0.75 * height2, 0.125, 0, 1);

		tess.addVertexWithUV(0.75 * lon, 0.25 * height2, 0.125, 0, 0);
		tess.addVertexWithUV(0.75 * lon, 0.75 * height2, 0.125, 0, 1);
		tess.addVertexWithUV(0.75 * lon, 0.75 * height2, base.spool - 0.125, 1,
				1);
		tess.addVertexWithUV(0.75 * lon, 0.25 * height2, base.spool - 0.125, 1,
				0);

		tess.addVertexWithUV(0.25 * lon, 0.75 * height2, 0.125, 0, 1);
		tess.addVertexWithUV(0.25 * lon, 0.75 * height2, base.spool - 0.125, 1,
				1);
		tess.addVertexWithUV(0.75 * lon, 0.75 * height2, base.spool - 0.125, 1,
				1);
		tess.addVertexWithUV(0.75 * lon, 0.75 * height2, 0.125, 0, 1);

		tess.addVertexWithUV(0.25 * lon, 0.25 * height2, 0.125, 0, 1);
		tess.addVertexWithUV(0.75 * lon, 0.25 * height2, 0.125, 0, 1);
		tess.addVertexWithUV(0.75 * lon, 0.25 * height2, base.spool - 0.125, 1,
				1);
		tess.addVertexWithUV(0.25 * lon, 0.25 * height2, base.spool - 0.125, 1,
				1);

		// spool 2
		tess.addVertexWithUV(0.25 * lon, 0.25 * height2, width2 - base.spool
				+ 0.125, 0, 0);
		tess.addVertexWithUV(0.25 * lon, 0.25 * height2, width2 - 0.125, 1, 0);
		tess.addVertexWithUV(0.25 * lon, 0.75 * height2, width2 - 0.125, 1, 1);
		tess.addVertexWithUV(0.25 * lon, 0.75 * height2, width2 - base.spool
				+ 0.125, 0, 1);

		tess.addVertexWithUV(0.75 * lon, 0.25 * height2, width2 - base.spool
				+ 0.125, 0, 0);
		tess.addVertexWithUV(0.75 * lon, 0.75 * height2, width2 - base.spool
				+ 0.125, 0, 1);
		tess.addVertexWithUV(0.75 * lon, 0.75 * height2, width2 - 0.125, 1, 1);
		tess.addVertexWithUV(0.75 * lon, 0.25 * height2, width2 - 0.125, 1, 0);

		tess.addVertexWithUV(0.25 * lon, 0.75 * height2, width2 - base.spool
				+ 0.125, 0, 1);
		tess.addVertexWithUV(0.25 * lon, 0.75 * height2, width2 - 0.125, 1, 1);
		tess.addVertexWithUV(0.75 * lon, 0.75 * height2, width2 - 0.125, 1, 1);
		tess.addVertexWithUV(0.75 * lon, 0.75 * height2, width2 - base.spool
				+ 0.125, 0, 1);

		tess.addVertexWithUV(0.25 * lon, 0.25 * height2, width2 - base.spool
				+ 0.125, 0, 1);
		tess.addVertexWithUV(0.75 * lon, 0.25 * height2, width2 - base.spool
				+ 0.125, 0, 1);
		tess.addVertexWithUV(0.75 * lon, 0.25 * height2, width2 - 0.125, 1, 1);
		tess.addVertexWithUV(0.25 * lon, 0.25 * height2, width2 - 0.125, 1, 1);

		tess.draw();

	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return new ResourceLocation(
				"talldoors:textures/entities/drawbridgeBase.png");
	}

}
