package tektor.minecraft.talldoors.renderer;

import org.lwjgl.opengl.GL11;

import tektor.minecraft.talldoors.entities.drawbridge.DrawbridgeBase;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RenderDrawbridgeBase extends Render {

	@Override
	public void doRender(Entity entity, double d0, double d1, double d2,
			float f, float f1) {
		DrawbridgeBase base = (DrawbridgeBase) entity;
		GL11.glPushMatrix();
		// GL11.glTranslatef((float)d0, (float)d1, (float)d2);
		Minecraft.getMinecraft().renderEngine
				.func_110577_a(func_110775_a(entity));
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
		drawBase(tess, base.height2, base.width2, base.lon, base.rotation, true);

		tess.draw();
		GL11.glPopMatrix();

	}

	public void drawBase(Tessellator tess, double height, double width,
			double lon, double angle, boolean c) {

		double a, b, e, d;
		a = Math.cos(Math.toRadians(90 - angle)) * height;
		b = Math.sin(Math.toRadians(90 - angle)) * height;
		d = Math.cos(Math.toRadians(angle)) * lon;
		e = Math.sin(Math.toRadians(angle)) * lon;
		if (c) {
			// front
			tess.addVertexWithUV(0, 0, 0, 1.0, 0.0);
			tess.addVertexWithUV(0, 0 + b, 0 - a, 1, 0.01);
			tess.addVertexWithUV(width, 0 + b, 0 - a, 0, 0.01);
			tess.addVertexWithUV(width, 0, 0, 0, 0);
			// top
			tess.addVertexWithUV(0, b, 0 - a, 1, 0);
			tess.addVertexWithUV(0, b + e, d - a, 1, 1);
			tess.addVertexWithUV(width, b + e, d - a, 0, 1);
			tess.addVertexWithUV(width, b, -a, 0, 0);
			// right
			tess.addVertexWithUV(0, 0, 0, 0, 0);
			tess.addVertexWithUV(0, e, d, 1, 0);
			tess.addVertexWithUV(0, b + e, d - a, 1, 0.01);
			tess.addVertexWithUV(0, b, -a, 0, 0.01);
			// bottom
			tess.addVertexWithUV(0, 0, 0, 1, 0);
			tess.addVertexWithUV(width, 0, 0, 0, 0);
			tess.addVertexWithUV(width, e, d, 0, 1);
			tess.addVertexWithUV(0, e, d, 1, 1);
			// left
			tess.addVertexWithUV(width, 0, 0, 0, 0);
			tess.addVertexWithUV(width, b, -a, 0, 0.01);
			tess.addVertexWithUV(width, b + e, d - a, 1, 0.01);
			tess.addVertexWithUV(width, e, d, 1, 0);
			// back
			tess.addVertexWithUV(0, e, d, 1.0, 0.0);
			tess.addVertexWithUV(width, e, d, 0, 0);
			tess.addVertexWithUV(width, e + b, d - a, 0, 0.01);
			tess.addVertexWithUV(0, e + b, d - a, 1, 0.01);

		} else {

		}
	}

	@Override
	protected ResourceLocation func_110775_a(Entity entity) {
		return new ResourceLocation(
				"talldoors:textures/entities/drawbridgeBase.png");
	}

}
