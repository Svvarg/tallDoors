package tektor.minecraft.talldoors.renderer.drawbridge;

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

	public void drawBase(Tessellator tess, double height, double width,
			double lon, double angle, DrawbridgeBase base) {

		double a, b, e, d;
		a = Math.cos(Math.toRadians(90 - angle)) * height;
		b = Math.sin(Math.toRadians(90 - angle)) * height;
		d = Math.cos(Math.toRadians(angle)) * lon;
		e = Math.sin(Math.toRadians(angle)) * lon;

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

		tess.draw();

		if (base.machine != null) {
			tess.startDrawingQuads();
			Minecraft.getMinecraft().renderEngine
					.bindTexture(new ResourceLocation(
							"talldoors:textures/entities/drawbridgeBase2.png"));
			double f, g, j, k;
			f = Math.cos(Math.toRadians(angle)) * (lon - 0.125);
			g = Math.sin(Math.toRadians(angle)) * (lon - 0.125);

			double c1, c2, c3;

			c1 = base.machine.posX - base.posX;
			c2 = base.machine.posY - base.posY;
			c3 = base.machine.posZ - base.posZ;

			double width2 = base.machine.width2;
			double spool = base.machine.spool;
			double depth = base.machine.lon;
			double macH = base.machine.height2;
			if (base.machine.orientation == ((base.orientation - 1) % 4)
					|| (base.machine.orientation == 3 && base.orientation == 0)) {

				if (base.orientation == 3) {
					c1 = c1 - base.machine.lon + 1;
					// right
					tess.addVertexWithUV(0, b + e, d - a, 0, 1);
					tess.addVertexWithUV(-c3 + 0.4375 * spool, c2 + 0.56 * macH
							+ 0.15, c1 + 0.7 * depth, 0, 0);
					tess.addVertexWithUV(-c3 + 0.4375 * spool, c2 + 0.56 * macH
							+ 0.025, c1 + 0.7 * depth, 1, 0);
					tess.addVertexWithUV(0, b + g, f - a, 1, 1);
					// top
					tess.addVertexWithUV(0, b + e, d - a, 0, 1);
					tess.addVertexWithUV(0.125, b + e, d - a, 0, 0);
					tess.addVertexWithUV(-c3 + 0.4375 * spool + 0.125, c2
							+ 0.56 * macH + 0.15, c1 + 0.7 * depth, 1, 0);
					tess.addVertexWithUV(-c3 + 0.4375 * spool, c2 + 0.56 * macH
							+ 0.15, c1 + 0.7 * depth, 1, 1);
					// left
					tess.addVertexWithUV(0.125, b + e, d - a, 0, 0);
					tess.addVertexWithUV(0.125, b + g, f - a, 0, 1);
					tess.addVertexWithUV(-c3 + 0.4375 * spool + 0.125, c2
							+ 0.56 * macH + 0.025, c1 + 0.7 * depth, 1, 1);
					tess.addVertexWithUV(-c3 + 0.4375 * spool + 0.125, c2
							+ 0.56 * macH + 0.15, c1 + 0.7 * depth, 1, 0);
					// bottom
					tess.addVertexWithUV(0.125, b + g, f - a, 0, 0);
					tess.addVertexWithUV(0, b + g, f - a, 1, 0);

					tess.addVertexWithUV(-c3 + 0.4375 * spool, c2 + 0.56 * macH
							+ 0.025, c1 + 0.7 * depth, 1, 1);
					tess.addVertexWithUV(-c3 + 0.4375 * spool + 0.025, c2
							+ 0.56 * macH + 0.125, c1 + 0.7 * depth, 0, 1);

					// right
					tess.addVertexWithUV(width - 0.125, b + e, d - a, 0, 1);
					tess.addVertexWithUV(width2 - 0.125 - c3 - 0.4375 * spool,
							c2 + 0.56 * macH + 0.15, c1 + 0.7 * depth, 0, 0);
					tess.addVertexWithUV(width2 - 0.125 - c3 - 0.4375 * spool,
							c2 + 0.56 * macH + 0.025, c1 + 0.7 * depth, 1, 0);
					tess.addVertexWithUV(width - 0.125, b + g, f - a, 1, 1);
					// top
					tess.addVertexWithUV(width - 0.125, b + e, d - a, 0, 1);
					tess.addVertexWithUV(width, b + e, d - a, 0, 0);
					tess.addVertexWithUV(width2 - c3 - 0.4375 * spool, c2
							+ 0.56 * macH + 0.15, c1 + 0.7 * depth, 1, 0);
					tess.addVertexWithUV(width2 - 0.125 - c3 - 0.4375 * spool,
							c2 + 0.56 * macH + 0.15, c1 + 0.7 * depth, 1, 1);
					// left
					tess.addVertexWithUV(width, b + e, d - a, 0, 0);
					tess.addVertexWithUV(width, b + g, f - a, 0, 1);
					tess.addVertexWithUV(-c3 - 0.4375 * spool + width2, c2
							+ 0.56 * macH + 0.025, c1 + 0.7 * depth, 1, 1);
					tess.addVertexWithUV(-c3 - 0.4375 * spool + width2, c2
							+ 0.56 * macH + 0.15, c1 + 0.7 * depth, 1, 0);
					// bottom
					tess.addVertexWithUV(width, b + g, f - a, 0, 0);
					tess.addVertexWithUV(width - 0.125, b + g, f - a, 1, 0);

					tess.addVertexWithUV(-c3 - 0.4375 * spool + width2 - 0.125,
							c2 + 0.56 * macH + 0.025, c1 + 0.7 * depth, 1, 1);
					tess.addVertexWithUV(-c3 - 0.4375 * spool + width2, c2
							+ 0.56 * macH + 0.025, c1 + 0.7 * depth, 0, 1);

				} else if (base.orientation == 2) {
					c3 = c3 + depth - 1;
					// right
					tess.addVertexWithUV(0, b + e, d - a, 0, 1);
					tess.addVertexWithUV(-c1 + 0.4375 * spool, c2 + 0.56 * macH
							+ 0.15, -c3 + 0.7 * depth, 0, 0);
					tess.addVertexWithUV(-c1 + 0.4375 * spool, c2 + 0.56 * macH
							+ 0.025, -c3 + 0.7 * depth, 1, 0);
					tess.addVertexWithUV(0, b + g, f - a, 1, 1);
					// top
					tess.addVertexWithUV(0, b + e, d - a, 0, 1);
					tess.addVertexWithUV(0.125, b + e, d - a, 0, 0);
					tess.addVertexWithUV(-c1 + 0.4375 * spool + 0.125, c2
							+ 0.56 * macH + 0.15, -c3 + 0.7 * depth, 1, 0);
					tess.addVertexWithUV(-c1 + 0.4375 * spool, c2 + 0.56 * macH
							+ 0.15, -c3 + 0.7 * depth, 1, 1);

					// left
					tess.addVertexWithUV(0.125, b + e, d - a, 0, 0);
					tess.addVertexWithUV(0.125, b + g, f - a, 0, 1);
					tess.addVertexWithUV(-c1 + 0.4375 * spool + 0.125, c2
							+ 0.56 * macH + 0.025, -c3 + 0.7 * depth, 1, 1);
					tess.addVertexWithUV(-c1 + 0.4375 * spool + 0.125, c2
							+ 0.56 * macH + 0.15, -c3 + 0.7 * depth, 1, 0);
					// bottom
					tess.addVertexWithUV(0.125, b + g, f - a, 0, 0);
					tess.addVertexWithUV(0, b + g, f - a, 1, 0);

					tess.addVertexWithUV(-c1 + 0.4375 * spool, c2 + 0.56 * macH
							+ 0.025, -c3 + 0.7 * depth, 1, 1);
					tess.addVertexWithUV(-c1 + 0.4375 * spool + 0.125, c2
							+ 0.56 * macH + 0.025, -c3 + 0.7 * depth, 0, 1);

					// right
					tess.addVertexWithUV(width - 0.125, b + e, d - a, 0, 1);
					tess.addVertexWithUV(width2 - 1 - c1 + 0.4375 * spool
							- spool + 1, c2 + 0.56 * macH + 0.15, -c3 + 0.7
							* depth, 0, 0);
					tess.addVertexWithUV(width2 - 1 - c1 + 0.4375 * spool
							- spool + 1, c2 + 0.56 * macH + 0.025, -c3 + 0.7
							* depth, 1, 0);
					tess.addVertexWithUV(width - 0.125, b + g, f - a, 1, 1);
					// top
					tess.addVertexWithUV(width - 0.125, b + e, d - a, 0, 1);
					tess.addVertexWithUV(width, b + e, d - a, 0, 0);
					tess.addVertexWithUV(width2 - 0.875 - c1 + 0.4375 * spool
							- spool + 1, c2 + 0.56 * macH + 0.15, -c3 + 0.7
							* depth, 1, 0);
					tess.addVertexWithUV(width2 + 2 - c1 + 0.4375 * spool
							- spool - 2, c2 + 0.56 * macH + 0.15, -c3 + 0.7
							* depth, 1, 1);

					// left
					tess.addVertexWithUV(width, b + e, d - a, 0, 0);
					tess.addVertexWithUV(width, b + g, f - a, 0, 1);
					tess.addVertexWithUV(-c1 + 0.4375 * spool - spool + 1
							+ width2 - 0.875, c2 + 0.56 * macH + 0.025, -c3
							+ 0.7 * depth, 1, 1);
					tess.addVertexWithUV(-c1 + 0.4375 * spool - spool + 1
							+ width2 - 0.875, c2 + 0.56 * macH + 0.15, -c3
							+ 0.7 * depth, 1, 0);
					// bottom
					tess.addVertexWithUV(width, b + g, f - a, 0, 0);
					tess.addVertexWithUV(width - 0.125, b + g, f - a, 1, 0);

					tess.addVertexWithUV(-c1 + 0.4375 * spool - spool + 1
							+ width2 - 1, c2 + 0.56 * macH + 0.025, -c3 + 0.7
							* depth, 1, 1);
					tess.addVertexWithUV(-c1 + 0.4375 * spool - spool + 1
							+ width2 - 0.875, c2 + 0.56 * macH + 0.025, -c3
							+ 0.7 * depth, 0, 1);
				} else if (base.orientation == 1) {
					c1 = c1 + depth -1;
					// right
					tess.addVertexWithUV(0, b + e, d - a, 0, 1);
					tess.addVertexWithUV(c3 + 0.4375*spool, c2 + 0.56 * macH + 0.15, -c1 +0.7*depth, 0,
							0);
					tess.addVertexWithUV(c3 + 0.4375*spool, c2 + 0.56 * macH + 0.025, -c1 +0.7*depth,
							1, 0);
					tess.addVertexWithUV(0, b + g, f - a, 1, 1);
					// top
					tess.addVertexWithUV(0, b + e, d - a, 0, 1);
					tess.addVertexWithUV(0.125, b + e, d - a, 0, 0);
					tess.addVertexWithUV(c3 + 0.4375*spool + 0.125, c2 + 0.56 * macH + 0.15,
							-c1 +0.7*depth, 1, 0);
					tess.addVertexWithUV(c3 + 0.4375*spool, c2 + 0.56 * macH + 0.15, -c1 +0.7*depth, 1,
							1);
					// left
					tess.addVertexWithUV(0.125, b + e, d - a, 0, 0);
					tess.addVertexWithUV(0.125, b + g, f - a, 0, 1);
					tess.addVertexWithUV(c3 + 0.4375*spool + 0.125, c2 + 0.56 * macH
							+ 0.025, -c1 +0.7*depth, 1, 1);
					tess.addVertexWithUV(c3 + 0.4375*spool + 0.125, c2 + 0.56 * macH + 0.15,
							-c1 +0.7*depth, 1, 0);
					// bottom
					tess.addVertexWithUV(0.125, b + g, f - a, 0, 0);
					tess.addVertexWithUV(0, b + g, f - a, 1, 0);

					tess.addVertexWithUV(c3 + 0.4375*spool, c2 + 0.56 * macH + 0.025, -c1 +0.7*depth,
							1, 1);
					tess.addVertexWithUV(c3 + 0.4375*spool + 0.125, c2 + 0.56 * macH + 0.025,
							-c1 +0.7*depth, 0, 1);

					// right
					tess.addVertexWithUV(width - 0.125, b + e, d - a, 0, 1);
					tess.addVertexWithUV(width2 - 1 + c3 + 0.4375*spool-spool+1, c2 + 0.56 * macH + 0.15,
							-c1 +0.7*depth, 0, 0);
					tess.addVertexWithUV(width2 - 1 + c3 + 0.4375*spool-spool+1, c2 + 0.56 * macH + 0.025,
							-c1 +0.7*depth, 1, 0);
					tess.addVertexWithUV(width - 0.125, b + g, f - a, 1, 1);
					// top
					tess.addVertexWithUV(width - 0.125, b + e, d - a, 0, 1);
					tess.addVertexWithUV(width, b + e, d - a, 0, 0);
					tess.addVertexWithUV(width2 - 0.875 + c3 + 0.4375*spool-spool+1,
							c2 + 0.56 * macH + 0.15, -c1 +0.7*depth, 1, 0);
					tess.addVertexWithUV(width2 - 1 + c3 + 0.4375*spool-spool+1, c2 + 0.56 * macH + 0.15,
							-c1 +0.7*depth, 1, 1);
					// left
					tess.addVertexWithUV(width, b + e, d - a, 0, 0);
					tess.addVertexWithUV(width, b + g, f - a, 0, 1);
					tess.addVertexWithUV(c3 + 0.4375*spool-spool+1 + width2 - 0.875,
							c2 + 0.56 * macH + 0.025, -c1 +0.7*depth, 1, 1);
					tess.addVertexWithUV(c3 + 0.4375*spool-spool+1 + width2 - 0.875,
							c2 + 0.56 * macH + 0.15, -c1 +0.7*depth, 1, 0);
					// bottom
					tess.addVertexWithUV(width, b + g, f - a, 0, 0);
					tess.addVertexWithUV(width - 0.125, b + g, f - a, 1, 0);

					tess.addVertexWithUV(c3 + 0.4375*spool-spool+1 + width2 - 1, c2 + 0.56 * macH + 0.025,
							-c1 +0.7*depth, 1, 1);
					tess.addVertexWithUV(c3 + 0.4375*spool-spool+1 + width2 - 0.875,
							c2 + 0.56 * macH + 0.025, -c1 +0.7*depth, 0, 1);

				} else if (base.orientation == 0) {
					c3 = c3 - depth +1;
					// right
					tess.addVertexWithUV(0, b + e, d - a, 0, 1);
					tess.addVertexWithUV(c1 + 0.4375*spool, c2 + 0.56 * macH + 0.15, c3 +0.7*depth, 0,
							0);
					tess.addVertexWithUV(c1 + 0.4375*spool, c2 + 0.56 * macH + 0.025, c3 +0.7*depth, 1,
							0);
					tess.addVertexWithUV(0, b + g, f - a, 1, 1);
					// top
					tess.addVertexWithUV(0, b + e, d - a, 0, 1);
					tess.addVertexWithUV(0.125, b + e, d - a, 0, 0);
					tess.addVertexWithUV(c1 + 0.4375*spool + 0.125, c2 + 0.56 * macH + 0.15,
							c3 +0.7*depth, 1, 0);
					tess.addVertexWithUV(c1 + 0.4375*spool, c2 + 0.56 * macH + 0.15, c3 +0.7*depth, 1,
							1);
					// left
					tess.addVertexWithUV(0.125, b + e, d - a, 0, 0);
					tess.addVertexWithUV(0.125, b + g, f - a, 0, 1);
					tess.addVertexWithUV(c1 + 0.4375*spool + 0.125, c2 + 0.56 * macH + 0.025,
							c3 +0.7*depth, 1, 1);
					tess.addVertexWithUV(c1 + 0.4375*spool + 0.125, c2 + 0.56 * macH + 0.15,
							c3 +0.7*depth, 1, 0);
					// bottom
					tess.addVertexWithUV(0.125, b + g, f - a, 0, 0);
					tess.addVertexWithUV(0, b + g, f - a, 1, 0);

					tess.addVertexWithUV(c1 + 0.4375*spool, c2 + 0.56 * macH + 0.025, c3 +0.7*depth, 1,
							1);
					tess.addVertexWithUV(c1 + 0.4375*spool + 0.125, c2 + 0.56 * macH + 0.025,
							c3 +0.7*depth, 0, 1);

					// right
					tess.addVertexWithUV(width - 0.125, b + e, d - a, 0, 1);
					tess.addVertexWithUV(width2 - 1 + c1 + 0.4375*spool-spool+1, c2 + 0.56 * macH + 0.15,
							c3 +0.7*depth, 0, 0);
					tess.addVertexWithUV(width2 - 1 + c1 + 0.4375*spool-spool+1, c2 + 0.56 * macH + 0.025,
							c3 +0.7*depth, 1, 0);
					tess.addVertexWithUV(width - 0.125, b + g, f - a, 1, 1);
					// top
					tess.addVertexWithUV(width - 0.125, b + e, d - a, 0, 1);
					tess.addVertexWithUV(width, b + e, d - a, 0, 0);
					tess.addVertexWithUV(width2 - 0.875 + c1 + 0.4375*spool-spool+1,
							c2 + 0.56 * macH + 0.15, c3 +0.7*depth, 1, 0);
					tess.addVertexWithUV(width2 - 1 + c1 + 0.4375*spool-spool+1, c2 + 0.56 * macH + 0.15,
							c3 +0.7*depth, 1, 1);
					// left
					tess.addVertexWithUV(width, b + e, d - a, 0, 0);
					tess.addVertexWithUV(width, b + g, f - a, 0, 1);
					tess.addVertexWithUV(c1 + 0.4375*spool-spool+1 + width2 - 0.875,
							c2 + 0.56 * macH + 0.025, c3 +0.7*depth, 1, 1);
					tess.addVertexWithUV(c1 + 0.4375*spool-spool+1 + width2 - 0.875,
							c2 + 0.56 * macH + 0.15, c3 +0.7*depth, 1, 0);

					// bottom
					tess.addVertexWithUV(width, b + g, f - a, 0, 0);
					tess.addVertexWithUV(width - 0.125, b + g, f - a, 1, 0);

					tess.addVertexWithUV(c1 + 0.4375*spool-spool+1 + width2 - 1, c2 + 0.56 * macH + 0.025,
							c3 +0.7*depth, 1, 1);
					tess.addVertexWithUV(c1 + 0.4375*spool-spool+1 + width2 - 0.875,
							c2 + 0.56 * macH + 0.025, c3 +0.7*depth, 0, 1);
				}
			} else if (base.machine.orientation == ((base.orientation + 1) % 4)) {
				if (base.orientation == 3) {
					c3 = c3 + spool - 1;
					// right
					tess.addVertexWithUV(0, b + e, d - a, 0, 1);
					tess.addVertexWithUV(-width2 - c3 + 1.4375 * spool, c2
							+ 0.56 * macH + 0.15, c1 + 0.7 * depth, 0, 0);
					tess.addVertexWithUV(-width2 - c3 + 1.4375 * spool, c2
							+ 0.56 * macH + 0.025, c1 + 0.7 * depth, 1, 0);
					tess.addVertexWithUV(0, b + g, f - a, 1, 1);
					// top
					tess.addVertexWithUV(0, b + e, d - a, 0, 1);
					tess.addVertexWithUV(0.125, b + e, d - a, 0, 0);
					tess.addVertexWithUV(-width2 + 0.125 - c3 + 1.4375 * spool,
							c2 + 0.56 * macH + 0.15, c1 + 0.7 * depth, 1, 0);
					tess.addVertexWithUV(-width2 - c3 + 1.4375 * spool, c2
							+ 0.56 * macH + 0.15, c1 + 0.7 * depth, 1, 1);

					// left
					tess.addVertexWithUV(0.125, b + e, d - a, 0, 0);
					tess.addVertexWithUV(0.125, b + g, f - a, 0, 1);
					tess.addVertexWithUV(-c3 + 1.4375 * spool - width2 + 0.125,
							c2 + 0.56 * macH + 0.025, c1 + 0.7 * depth, 1, 1);
					tess.addVertexWithUV(-c3 + 1.4375 * spool - width2 + 0.125,
							c2 + 0.56 * macH + 0.15, c1 + 0.7 * depth, 1, 0);
					// bottom
					tess.addVertexWithUV(0.125, b + g, f - a, 0, 0);
					tess.addVertexWithUV(0, b + g, f - a, 1, 0);
					tess.addVertexWithUV(-c3 + 1.4375 * spool - width2, c2
							+ 0.56 * macH + 0.025, c1 + 0.7 * depth, 1, 1);
					tess.addVertexWithUV(-c3 + 1.4375 * spool - width2 + 0.125,
							c2 + 0.56 * macH + 0.025, c1 + 0.7 * depth, 0, 1);

					// right
					tess.addVertexWithUV(width - 0.125, b + e, d - a, 0, 1);
					tess.addVertexWithUV(-c3 + 0.4375 * spool, c2 + 0.56 * macH
							+ 0.15, c1 + 0.7 * depth, 0, 0);
					tess.addVertexWithUV(-c3 + 0.4375 * spool, c2 + 0.56 * macH
							+ 0.025, c1 + 0.7 * depth, 1, 0);
					tess.addVertexWithUV(width - 0.125, b + g, f - a, 1, 1);
					// top
					tess.addVertexWithUV(width - 0.125, b + e, d - a, 0, 1);
					tess.addVertexWithUV(width, b + e, d - a, 0, 0);
					tess.addVertexWithUV(-c3 + 0.4375 * spool + 0.125, c2
							+ 0.56 * macH + 0.15, c1 + 0.7 * depth, 1, 0);
					tess.addVertexWithUV(-c3 + 0.4375 * spool, c2 + 0.56 * macH
							+ 0.15, c1 + 0.7 * depth, 1, 1);

					// left
					tess.addVertexWithUV(width, b + e, d - a, 0, 0);
					tess.addVertexWithUV(width, b + g, f - a, 0, 1);
					tess.addVertexWithUV(-c3 + 0.4375 * spool + 0.125, c2
							+ 0.56 * macH + 0.025, c1 + 0.7 * depth, 1, 1);
					tess.addVertexWithUV(-c3 + 0.4375 * spool + 0.125, c2
							+ 0.56 * macH + 0.15, c1 + 0.7 * depth, 1, 0);
					// bottom
					tess.addVertexWithUV(width, b + g, f - a, 0, 0);
					tess.addVertexWithUV(width - 0.125, b + g, f - a, 1, 0);
					tess.addVertexWithUV(-c3 + 0.4375 * spool, c2 + 0.56 * macH
							+ 0.025, c1 + 0.7 * depth, 1, 1);
					tess.addVertexWithUV(-c3 + 0.4375 * spool + 0.125, c2
							+ 0.56 * macH + 0.025, c1 + 0.7 * depth, 0, 1);
				} else if (base.orientation == 2) {
					// right
					tess.addVertexWithUV(0, b + e, d - a, 0, 1);
					tess.addVertexWithUV(1 - c1 + 0.4375 * spool - width2, c2
							+ 0.56 * macH + 0.15, -c3 + 0.7 * depth, 0, 0);
					tess.addVertexWithUV(1 - c1 + 0.4375 * spool - width2, c2
							+ 0.56 * macH + 0.025, -c3 + 0.7 * depth, 1, 0);
					tess.addVertexWithUV(0, b + g, f - a, 1, 1);
					// top
					tess.addVertexWithUV(0, b + e, d - a, 0, 1);
					tess.addVertexWithUV(0.125, b + e, d - a, 0, 0);
					tess.addVertexWithUV(-1.875 + 3 - c1 + 0.4375 * spool
							- width2, c2 + 0.56 * macH + 0.15, -c3 + 0.7
							* depth, 1, 0);
					tess.addVertexWithUV(-c1 + 1 + 0.4375 * spool - width2, c2
							+ 0.56 * macH + 0.15, -c3 + 0.7 * depth, 1, 1);
					// left
					tess.addVertexWithUV(0.125, b + e, d - a, 0, 0);
					tess.addVertexWithUV(0.125, b + g, f - a, 0, 1);
					tess.addVertexWithUV(-c1 + 0.4375 * spool - 0.875 + 2
							- width2, c2 + 0.56 * macH + 0.025, -c3 + 0.7
							* depth, 1, 1);
					tess.addVertexWithUV(-c1 + 0.4375 * spool - 0.875 + 2
							- width2, c2 + 0.56 * macH + 0.15, -c3 + 0.7
							* depth, 1, 0);
					// bottom
					tess.addVertexWithUV(0.125, b + g, f - a, 0, 0);
					tess.addVertexWithUV(0, b + g, f - a, 1, 0);

					tess.addVertexWithUV(-c1 + 1 + 0.4375 * spool - width2, c2
							+ 0.56 * macH + 0.025, -c3 + 0.7 * depth, 1, 1);
					tess.addVertexWithUV(-c1 + 2 + 0.4375 * spool - 0.875
							- width2, c2 + 0.56 * macH + 0.025, -c3 + 0.7
							* depth, 0, 1);

					// right
					tess.addVertexWithUV(width - 0.125, b + e, d - a, 0, 1);
					tess.addVertexWithUV(-c1 + 0.4375 * spool-spool+1, c2 + 0.56 * macH
							+ 0.15, -c3 + 0.7 * depth, 0, 0);
					tess.addVertexWithUV(-c1 + 0.4375 * spool-spool+1, c2 + 0.56 * macH
							+ 0.025, -c3 + 0.7 * depth, 1, 0);
					tess.addVertexWithUV(width - 0.125, b + g, f - a, 1, 1);
					// top
					tess.addVertexWithUV(width - 0.125, b + e, d - a, 0, 1);
					tess.addVertexWithUV(width, b + e, d - a, 0, 0);
					tess.addVertexWithUV(-c1 + 0.4375 * spool-spool+1 + 0.125, c2
							+ 0.56 * macH + 0.15, -c3 + 0.7 * depth, 1, 0);
					tess.addVertexWithUV(-c1 + 0.4375 * spool-spool+1, c2 + 0.56 * macH
							+ 0.15, -c3 + 0.7 * depth, 1, 1);
					// left
					tess.addVertexWithUV(width, b + e, d - a, 0, 0);
					tess.addVertexWithUV(width, b + g, f - a, 0, 1);
					tess.addVertexWithUV(-c1 + 0.4375 * spool-spool+1 + 0.125, c2
							+ 0.56 * macH + 0.025, -c3 + 0.7 * depth, 1, 1);
					tess.addVertexWithUV(-c1 + 0.4375 * spool-spool+1 + 0.125, c2
							+ 0.56 * macH + 0.15, -c3 + 0.7 * depth, 1, 0);
					// bottom
					tess.addVertexWithUV(width, b + g, f - a, 0, 0);
					tess.addVertexWithUV(width - 0.125, b + g, f - a, 1, 0);

					tess.addVertexWithUV(-c1 + 0.4375 * spool-spool+1, c2 + 0.56 * macH
							+ 0.025, -c3 + 0.7 * depth, 1, 1);
					tess.addVertexWithUV(-c1 + 0.4375 * spool-spool+1 + 0.125, c2
							+ 0.56 * macH + 0.025, -c3 + 0.7 * depth, 0, 1);

				} else if (base.orientation == 1) {
					// right
					tess.addVertexWithUV(0, b + e, d - a, 0, 1);
					tess.addVertexWithUV(c3 + 0.4375*spool - width2 + 1, c2 + 0.56 * macH + 0.15,
							-c1 +0.7*depth, 0, 0);
					tess.addVertexWithUV(c3 + 0.4375*spool - width2 + 1, c2 + 0.56 * macH + 0.025,
							-c1 +0.7*depth, 1, 0);
					tess.addVertexWithUV(0, b + g, f - a, 1, 1);
					// top
					tess.addVertexWithUV(0, b + e, d - a, 0, 1);
					tess.addVertexWithUV(0.125, b + e, d - a, 0, 0);
					tess.addVertexWithUV(c3 + 0.4375*spool - width2 + 1 + 0.125,
							c2 + 0.56 * macH + 0.15, -c1 +0.7*depth, 1, 0);
					tess.addVertexWithUV(c3 + 0.4375*spool - width2 + 1, c2 + 0.56 * macH + 0.15,
							-c1 +0.7*depth, 1, 1);
					// left
					tess.addVertexWithUV(0.125, b + e, d - a, 0, 0);
					tess.addVertexWithUV(0.125, b + g, f - a, 0, 1);
					tess.addVertexWithUV(c3 + 0.4375*spool - width2 + 1 + 0.125,
							c2 + 0.56 * macH + 0.025, -c1 +0.7*depth, 1, 1);
					tess.addVertexWithUV(c3 + 0.4375*spool - width2 + 1 + 0.125,
							c2 + 0.56 * macH + 0.15, -c1 +0.7*depth, 1, 0);
					// bottom
					tess.addVertexWithUV(0.125, b + g, f - a, 0, 0);
					tess.addVertexWithUV(0, b + g, f - a, 1, 0);

					tess.addVertexWithUV(c3 + 0.4375*spool - width2 + 1, c2 + 0.56 * macH + 0.025,
							-c1 +0.7*depth, 1, 1);
					tess.addVertexWithUV(c3 + 0.4375*spool - width2 + 1 + 0.125,
							c2 + 0.56 * macH + 0.025, -c1 +0.7*depth, 0, 1);

					// right
					tess.addVertexWithUV(width - 0.125, b + e, d - a, 0, 1);
					tess.addVertexWithUV(c3 + 0.4375*spool-spool+1, c2 + 0.56 * macH + 0.15, -c1 +0.7*depth, 0,
							0);
					tess.addVertexWithUV(c3 + 0.4375*spool-spool+1, c2 + 0.56 * macH + 0.025, -c1 +0.7*depth,
							1, 0);
					tess.addVertexWithUV(width - 0.125, b + g, f - a, 1, 1);
					// top
					tess.addVertexWithUV(width - 0.125, b + e, d - a, 0, 1);
					tess.addVertexWithUV(width, b + e, d - a, 0, 0);
					tess.addVertexWithUV(1 - 0.875 + c3 + 0.4375*spool-spool+1, c2 + 0.56 * macH + 0.15,
							-c1 +0.7*depth, 1, 0);
					tess.addVertexWithUV(c3 + 0.4375*spool-spool+1, c2 + 0.56 * macH + 0.15, -c1 +0.7*depth, 1,
							1);
					// left
					tess.addVertexWithUV(width, b + e, d - a, 0, 0);
					tess.addVertexWithUV(width, b + g, f - a, 0, 1);
					tess.addVertexWithUV(c3 + 0.4375*spool-spool+1 + 1 - 0.875, c2 + 0.56 * macH + 0.025,
							-c1 +0.7*depth, 1, 1);
					tess.addVertexWithUV(c3 + 0.4375*spool-spool+1 + 1 - 0.875, c2 + 0.56 * macH + 0.15,
							-c1 +0.7*depth, 1, 0);
					// bottom
					tess.addVertexWithUV(width, b + g, f - a, 0, 0);
					tess.addVertexWithUV(width - 0.125, b + g, f - a, 1, 0);

					tess.addVertexWithUV(c3 + 0.4375*spool-spool+1, c2 + 0.56 * macH + 0.025, -c1 +0.7*depth,
							1, 1);
					tess.addVertexWithUV(c3 + 0.4375*spool-spool+1 + 1 - 0.875, c2 + 0.56 * macH + 0.025,
							-c1 +0.7*depth, 0, 1);

				} else if (base.orientation == 0) {
					// right
					tess.addVertexWithUV(0, b + e, d - a, 0, 1);
					tess.addVertexWithUV(c1 + 0.4375*spool - width2 + 1, c2 + 0.56 * macH + 0.15,
							c3 +0.7*depth, 0, 0);
					tess.addVertexWithUV(c1 + 0.4375*spool - width2 + 1, c2 + 0.56 * macH + 0.025,
							c3 +0.7*depth, 1, 0);
					tess.addVertexWithUV(0, b + g, f - a, 1, 1);
					// top
					tess.addVertexWithUV(0, b + e, d - a, 0, 1);
					tess.addVertexWithUV(0.125, b + e, d - a, 0, 0);
					tess.addVertexWithUV(c1 + 0.4375*spool - width2 + 1 + 0.125,
							c2 + 0.56 * macH + 0.15, c3 +0.7*depth, 1, 0);
					tess.addVertexWithUV(c1 + 0.4375*spool - width2 + 1, c2 + 0.56 * macH + 0.15,
							c3 +0.7*depth, 1, 1);
					// left
					tess.addVertexWithUV(0.125, b + e, d - a, 0, 0);
					tess.addVertexWithUV(0.125, b + g, f - a, 0, 1);
					tess.addVertexWithUV(c1 + 0.4375*spool - width2 + 1 + 0.125,
							c2 + 0.56 * macH + 0.025, c3 +0.7*depth, 1, 1);
					tess.addVertexWithUV(c1 + 0.4375*spool - width2 + 1 + 0.125,
							c2 + 0.56 * macH + 0.15, c3 +0.7*depth, 1, 0);
					// bottom
					tess.addVertexWithUV(0.125, b + g, f - a, 0, 0);
					tess.addVertexWithUV(0, b + g, f - a, 1, 0);

					tess.addVertexWithUV(c1 + 0.4375*spool - width2 + 1, c2 + 0.56 * macH + 0.025,
							c3 +0.7*depth, 1, 1);
					tess.addVertexWithUV(c1 + 0.4375*spool - width2 + 1 + 0.125,
							c2 + 0.56 * macH + 0.025, c3 +0.7*depth, 0, 1);

					// right
					tess.addVertexWithUV(width - 0.125, b + e, d - a, 0, 1);
					tess.addVertexWithUV(c1 + 0.4375*spool-spool+1, c2 + 0.56 * macH + 0.15, c3 +0.7*depth, 0,
							0);
					tess.addVertexWithUV(c1 + 0.4375*spool-spool+1, c2 + 0.56 * macH + 0.025, c3 +0.7*depth, 1,
							0);
					tess.addVertexWithUV(width - 0.125, b + g, f - a, 1, 1);
					// top
					tess.addVertexWithUV(width - 0.125, b + e, d - a, 0, 1);
					tess.addVertexWithUV(width, b + e, d - a, 0, 0);
					tess.addVertexWithUV(1 - 0.875 + c1 + 0.4375*spool-spool+1, c2 + 0.56 * macH + 0.15,
							c3 +0.7*depth, 1, 0);
					tess.addVertexWithUV(c1 + 0.4375*spool-spool+1, c2 + 0.56 * macH + 0.15, c3 +0.7*depth, 1,
							1);
					// left
					tess.addVertexWithUV(width, b + e, d - a, 0, 0);
					tess.addVertexWithUV(width, b + g, f - a, 0, 1);
					tess.addVertexWithUV(c1 + 0.4375*spool-spool+1 + 1 - 0.875, c2 + 0.56 * macH + 0.025,
							c3 +0.7*depth, 1, 1);
					tess.addVertexWithUV(c1 + 0.4375*spool-spool+1 + 1 - 0.875, c2 + 0.56 * macH + 0.15,
							c3 +0.7*depth, 1, 0);
					// bottom
					tess.addVertexWithUV(width, b + g, f - a, 0, 0);
					tess.addVertexWithUV(width - 0.125, b + g, f - a, 1, 0);

					tess.addVertexWithUV(c1 + 0.4375*spool-spool+1, c2 + 0.56 * macH + 0.025, c3 +0.7*depth, 1,
							1);
					tess.addVertexWithUV(c1 + 0.4375*spool-spool+1 + 1 - 0.875, c2 + 0.56 * macH + 0.025,
							c3 +0.7*depth, 0, 1);

				}
			}
			tess.draw();
		}

	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return new ResourceLocation(
				"talldoors:textures/entities/drawbridgeBase.png");
	}

}
