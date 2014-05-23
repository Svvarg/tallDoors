package tektor.minecraft.talldoors.doorworkshop.renderer.windows.watchmen;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import tektor.minecraft.talldoors.doorworkshop.DoorPartRegistry;
import tektor.minecraft.talldoors.doorworkshop.entity.doorparts.AbstractDoorPart;
import tektor.minecraft.talldoors.doorworkshop.entity.doorparts.windows.watchmen.WatchmanWindowEntity;
import tektor.minecraft.talldoors.doorworkshop.renderer.AbstractModuleDoorRenderer;
import tektor.minecraft.talldoors.renderer.RenderUtil;

public class SmallWatchmanWindowRenderer extends AbstractModuleDoorRenderer {

	@Override
	public void doRender(Entity var1, double var2, double var4, double var6,
			float var8, float var9) {
		super.doRender(var1, var2, var4, var6, var8, var9);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity var1) {
		return new ResourceLocation(
				DoorPartRegistry.texturePaths
						.get(((AbstractDoorPart) var1).texture1));
	}

	@Override
	protected void renderingStuff(Entity entity, double x, double y, double z,
			float var8, float var9) {
		WatchmanWindowEntity ent = (WatchmanWindowEntity) entity;

		this.bindTexture(new ResourceLocation(DoorPartRegistry.texturePaths
				.get(ent.sideTexture)));
		RenderUtil.renderOutline(ent.height2, 1, ent.depth, 0, ent);

		Tessellator tess = Tessellator.instance;
		drawWindow(ent, tess);
		drawOpening(ent, tess);

		for (int i = 1; i < ent.height2; i++) {
			GL11.glTranslatef(0, 1, 0);
			drawWindow(ent, tess);
			drawOpening(ent, tess);
		}

	}

	private void drawOpening(WatchmanWindowEntity ent, Tessellator tess) {
		double depth = ent.depth;
		if (ent.watchmanWindow) {
			this.bindTexture(new ResourceLocation(DoorPartRegistry.texturePaths
					.get(ent.texture2)));
			tess.startDrawingQuads();

			// front
			tess.addVertexWithUV(0.125, 0.5, 0, 0, 0);
			tess.addVertexWithUV(0.125, 0.5, 0.75, 0.75, 0);
			tess.addVertexWithUV(0.125, 0.7, 0.75, 0.75, 0.2);
			tess.addVertexWithUV(0.125, 0.7, 0, 0, 0.2);
			// back
			tess.addVertexWithUV(0.125 + depth, 0.5, 0, 0, 0);
			tess.addVertexWithUV(0.125 + depth, 0.7, 0, 0, 0.2);
			tess.addVertexWithUV(0.125 + depth, 0.7, 0.75, 0.75, 0.2);
			tess.addVertexWithUV(0.125 + depth, 0.5, 0.75, 0.75, 0);

			// top
			tess.addVertexWithUV(0.125, 0.7, 0, 0, 0);
			tess.addVertexWithUV(0.125, 0.7, 0.75, 0, 0.75);
			tess.addVertexWithUV(0.125 + depth, 0.7, 0.75, depth, 0.75);
			tess.addVertexWithUV(0.125 + depth, 0.7, 0, depth, 0);

			// bottom
			tess.addVertexWithUV(0.125, 0.5, 0, 0, 0);
			tess.addVertexWithUV(0.125 + depth, 0.5, 0, depth, 0);
			tess.addVertexWithUV(0.125 + depth, 0.5, 0.75, depth, 0.75);
			tess.addVertexWithUV(0.125, 0.5, 0.75, 0, 0.75);

			// "left"
			tess.addVertexWithUV(0.125, 0.5, 0, 0, 0);
			tess.addVertexWithUV(0.125, 0.7, 0, 0, 0.2);
			tess.addVertexWithUV(0.125 + depth, 0.7, 0, depth, 0.2);
			tess.addVertexWithUV(0.125 + depth, 0.5, 0, depth, 0);

			// "right"
			tess.addVertexWithUV(0.125, 0.5, 0.75, 0, 0);
			tess.addVertexWithUV(0.125 + depth, 0.5, 0.75, depth, 0);
			tess.addVertexWithUV(0.125 + depth, 0.7, 0.75, depth, 0.2);
			tess.addVertexWithUV(0.125, 0.7, 0.75, 0, 0.2);

			tess.draw();
		} else {
			this.bindTexture(new ResourceLocation(DoorPartRegistry.texturePaths
					.get(ent.texture2)));
			tess.startDrawingQuads();

			// front
			tess.addVertexWithUV(0.125, 0.5, depth, 0, 0);
			tess.addVertexWithUV(0.875, 0.5, depth, 0.875, 0);
			tess.addVertexWithUV(0.875, 0.7, depth, 0.875, 0.2);
			tess.addVertexWithUV(0.125, 0.7, depth, 0, 0.2);

			// back
			tess.addVertexWithUV(0.125, 0.5, 0, 0, 0);
			tess.addVertexWithUV(0.125, 0.7, 0, 0, 0.2);
			tess.addVertexWithUV(0.875, 0.7, 0, 0.875, 0.2);
			tess.addVertexWithUV(0.875, 0.5, 0, 0.875, 0);

			tess.draw();
		}

	}

	private void drawWindow(AbstractDoorPart ent, Tessellator tess) {
		this.bindTexture(this.getEntityTexture(ent));
		tess.startDrawingQuads();
		float depth = ent.depth;
		// front
		// /left
		tess.addVertexWithUV(0, 0, depth, 0, 0);
		tess.addVertexWithUV(0.125f, 0, depth, 0.125f, 0);
		tess.addVertexWithUV(0.125f, 1, depth, 0.125f, 1);
		tess.addVertexWithUV(0, 1, depth, 0, 1);
		// /right
		tess.addVertexWithUV(0.875f, 0, depth, 0.875f, 0);
		tess.addVertexWithUV(1, 0, depth, 1, 0);
		tess.addVertexWithUV(1, 1, depth, 1, 1);
		tess.addVertexWithUV(0.875f, 1, depth, 0.875f, 1);
		// /top
		tess.addVertexWithUV(0.125f, 0.7, depth, 0.125f, 0.7);
		tess.addVertexWithUV(0.875f, 0.7, depth, 0.875f, 0.7);
		tess.addVertexWithUV(0.875f, 1, depth, 0.875f, 1);
		tess.addVertexWithUV(0.125f, 1, depth, 0.125f, 1);
		// /Bottom
		tess.addVertexWithUV(0.125f, 0, depth, 0.125f, 0);
		tess.addVertexWithUV(0.875f, 0, depth, 0.875f, 0);
		tess.addVertexWithUV(0.875f, 0.5, depth, 0.875f, 0.5);
		tess.addVertexWithUV(0.125f, 0.5, depth, 0.125f, 0.5);

		// back
		tess.addVertexWithUV(0, 0, 0, 0, 0);
		tess.addVertexWithUV(0, 1, 0, 0, 1);
		tess.addVertexWithUV(0.125f, 1, 0, 0.125f, 1);
		tess.addVertexWithUV(0.125f, 0, 0, 0.125f, 0);
		// /right
		tess.addVertexWithUV(0.875f, 0, 0, 0.875f, 0);
		tess.addVertexWithUV(0.875f, 1, 0, 0.875f, 1);
		tess.addVertexWithUV(1, 1, 0, 1, 1);
		tess.addVertexWithUV(1, 0, 0, 1, 0);
		// /top
		tess.addVertexWithUV(0.125f, 0.7, 0, 0.125f, 0.7);
		tess.addVertexWithUV(0.125f, 1, 0, 0.125f, 1);
		tess.addVertexWithUV(0.875f, 1, 0, 0.875f, 1);
		tess.addVertexWithUV(0.875f, 0.7, 0, 0.875f, 0.7);
		// /Bottom
		tess.addVertexWithUV(0.125f, 0, 0, 0.125f, 0);
		tess.addVertexWithUV(0.125f, 0.5, 0, 0.125f, 0.5);
		tess.addVertexWithUV(0.875f, 0.5, 0, 0.875f, 0.5);
		tess.addVertexWithUV(0.875f, 0, 0, 0.875f, 0);

		// inner
		// //bottom
		tess.addVertexWithUV(0.125, 0.5, depth, 0.125, 0.125);
		tess.addVertexWithUV(0.875, 0.5, depth, 0.875, 0.125);
		tess.addVertexWithUV(0.875, 0.5, 0, 0.875, depth + 0.125);
		tess.addVertexWithUV(0.125, 0.5, 0, 0.125, depth + 0.125);
		// //left
		tess.addVertexWithUV(0.125, 0.5, depth, 0.125, 0.125);
		tess.addVertexWithUV(0.125, 0.5, 0, 0.125, depth + 0.125);
		tess.addVertexWithUV(0.125, 0.7, 0, 0.875, depth + 0.125);
		tess.addVertexWithUV(0.125, 0.7, depth, 0.875, 0.125);
		// /right
		tess.addVertexWithUV(0.875, 0.5, depth, 0.125, 0.125);
		tess.addVertexWithUV(0.875, 0.7, depth, 0.875, 0.125);
		tess.addVertexWithUV(0.875, 0.7, 0, 0.875, depth + 0.125);
		tess.addVertexWithUV(0.875, 0.5, 0, 0.125, depth + 0.125);
		// //top
		tess.addVertexWithUV(0.125, 0.7, depth, 0.125, 0.125);
		tess.addVertexWithUV(0.125, 0.7, 0, 0.125, depth + 0.125);
		tess.addVertexWithUV(0.875, 0.7, 0, 0.875, depth + 0.125);
		tess.addVertexWithUV(0.875, 0.7, depth, 0.875, 0.125);

		tess.draw();

	}

}
