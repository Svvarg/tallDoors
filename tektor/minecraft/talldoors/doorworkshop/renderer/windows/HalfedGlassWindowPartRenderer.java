package tektor.minecraft.talldoors.doorworkshop.renderer.windows;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import tektor.minecraft.talldoors.doorworkshop.DoorPartRegistry;
import tektor.minecraft.talldoors.doorworkshop.entity.doorparts.AbstractDoorPart;
import tektor.minecraft.talldoors.doorworkshop.entity.doorparts.windows.HalfedGlassWindowPartEntity;
import tektor.minecraft.talldoors.doorworkshop.renderer.AbstractModuleDoorRenderer;
import tektor.minecraft.talldoors.renderer.RenderUtil;

public class HalfedGlassWindowPartRenderer extends AbstractModuleDoorRenderer {

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
		HalfedGlassWindowPartEntity ent = (HalfedGlassWindowPartEntity) entity;

		this.bindTexture(new ResourceLocation(DoorPartRegistry.texturePaths
				.get(ent.sideTexture)));
		RenderUtil.renderOutline(ent.height2, 1, ent.depth, 0, ent);

		Tessellator tess = Tessellator.instance;
		drawWindow(ent, tess);
		drawGlass(ent, tess);

		for (int i = 1; i < ent.height2; i++) {
			GL11.glTranslatef(0, 1, 0);
			drawWindow(ent, tess);
			drawGlass(ent, tess);
		}

	}

	private void drawGlass(HalfedGlassWindowPartEntity ent, Tessellator tess) {
		this.bindTexture(new ResourceLocation(DoorPartRegistry.texturePaths
				.get(ent.texture2)));
		float depth = ent.depth;
		tess.startDrawingQuads();
		float depthH = depth / 2;
		// left bottom front
		tess.addVertexWithUV(0.125, 0.125, depthH + 0.05, 1, 1);
		tess.addVertexWithUV(0.4375, 0.125, depthH + 0.05, 1, 0.6875);
		tess.addVertexWithUV(0.4375, 0.875, depthH + 0.05, 0, 0.6875);
		tess.addVertexWithUV(0.125, 0.875, depthH + 0.05, 0, 1);
		// left bottom back
		tess.addVertexWithUV(0.125, 0.125, depthH - 0.05, 1, 1);
		tess.addVertexWithUV(0.125, 0.875, depthH - 0.05, 0, 1);
		tess.addVertexWithUV(0.4375, 0.875, depthH - 0.05, 0, 0.6875);
		tess.addVertexWithUV(0.4375, 0.125, depthH - 0.05, 1, 0.6875);

		tess.addVertexWithUV(0.5625, 0.125, depthH + 0.05, 1, 0.3125);
		tess.addVertexWithUV(0.875, 0.125, depthH + 0.05, 1, 0);
		tess.addVertexWithUV(0.875, 0.875, depthH + 0.05, 0, 0);
		tess.addVertexWithUV(0.5625, 0.875, depthH + 0.05, 0, 0.3125);
		// left bottom back
		tess.addVertexWithUV(0.5625, 0.125, depthH - 0.05, 1, 0.3125);
		tess.addVertexWithUV(0.5625, 0.875, depthH - 0.05, 0, 0.3125);
		tess.addVertexWithUV(0.875, 0.875, depthH - 0.05, 0, 0);
		tess.addVertexWithUV(0.875, 0.125, depthH - 0.05, 1, 0);

		tess.draw();
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
		tess.addVertexWithUV(0.125f, 0.875f, depth, 0.125f, 0.875f);
		tess.addVertexWithUV(0.875f, 0.875f, depth, 0.875f, 0.875f);
		tess.addVertexWithUV(0.875f, 1, depth, 0.875f, 1);
		tess.addVertexWithUV(0.125f, 1, depth, 0.125f, 1);
		// /Bottom
		tess.addVertexWithUV(0.125f, 0, depth, 0.125f, 0);
		tess.addVertexWithUV(0.875f, 0, depth, 0.875f, 0);
		tess.addVertexWithUV(0.875f, 0.125f, depth, 0.875f, 0.125f);
		tess.addVertexWithUV(0.125f, 0.125f, depth, 0.125f, 0.125f);

		// middle
		tess.addVertexWithUV(0.4375, 0.125, depth, 0.4375, 0.125);
		tess.addVertexWithUV(0.5625, 0.125, depth, 0.5625, 0.125);
		tess.addVertexWithUV(0.5625, 0.875, depth, 0.5625, 0.875);
		tess.addVertexWithUV(0.4375, 0.875, depth, 0.4375, 0.875);

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
		tess.addVertexWithUV(0.125f, 0.875f, 0, 0.125f, 0.875f);
		tess.addVertexWithUV(0.125f, 1, 0, 0.125f, 1);
		tess.addVertexWithUV(0.875f, 1, 0, 0.875f, 1);
		tess.addVertexWithUV(0.875f, 0.875f, 0, 0.875f, 0.875f);
		// /Bottom
		tess.addVertexWithUV(0.125f, 0, 0, 0.125f, 0);
		tess.addVertexWithUV(0.125f, 0.125f, 0, 0.125f, 0.125f);
		tess.addVertexWithUV(0.875f, 0.125f, 0, 0.875f, 0.125f);
		tess.addVertexWithUV(0.875f, 0, 0, 0.875f, 0);

		// middle
		tess.addVertexWithUV(0.4375, 0.125, 0, 0.4375, 0.125);
		tess.addVertexWithUV(0.4375, 0.875, 0, 0.4375, 0.875);
		tess.addVertexWithUV(0.5625, 0.875, 0, 0.5625, 0.875);
		tess.addVertexWithUV(0.5625, 0.125, 0, 0.5625, 0.125);

		// inner
		// //bottom
		tess.addVertexWithUV(0.125, 0.125, depth, 0.125, 0.125);
		tess.addVertexWithUV(0.875, 0.125, depth, 0.875, 0.125);
		tess.addVertexWithUV(0.875, 0.125, 0, 0.875, depth + 0.125);
		tess.addVertexWithUV(0.125, 0.125, 0, 0.125, depth + 0.125);
		// //left
		tess.addVertexWithUV(0.125, 0.125, depth, 0.125, 0.125);
		tess.addVertexWithUV(0.125, 0.125, 0, 0.125, depth + 0.125);
		tess.addVertexWithUV(0.125, 0.875, 0, 0.875, depth + 0.125);
		tess.addVertexWithUV(0.125, 0.875, depth, 0.875, 0.125);
		// /right
		tess.addVertexWithUV(0.875, 0.125, depth, 0.125, 0.125);
		tess.addVertexWithUV(0.875, 0.875, depth, 0.875, 0.125);
		tess.addVertexWithUV(0.875, 0.875, 0, 0.875, depth + 0.125);
		tess.addVertexWithUV(0.875, 0.125, 0, 0.125, depth + 0.125);
		// //top
		tess.addVertexWithUV(0.125, 0.875, depth, 0.125, 0.125);
		tess.addVertexWithUV(0.125, 0.875, 0, 0.125, depth + 0.125);
		tess.addVertexWithUV(0.875, 0.875, 0, 0.875, depth + 0.125);
		tess.addVertexWithUV(0.875, 0.875, depth, 0.875, 0.125);

		// middle left
		tess.addVertexWithUV(0.4375, 0.125, depth, 0.125, depth + 0.125);
		tess.addVertexWithUV(0.4375, 0.875, depth, 0.875, depth + 0.125);
		tess.addVertexWithUV(0.4375, 0.875, 0, 0.875, 0.125);
		tess.addVertexWithUV(0.4375, 0.125, 0, 0.125, 0.125);

		// middle right
		tess.addVertexWithUV(0.4375, 0.125, depth, 0.125, depth + 0.125);
		tess.addVertexWithUV(0.4375, 0.125, depth, 0.125, 0.125);
		tess.addVertexWithUV(0.4375, 0.875, 0, 0.875, 0.125);
		tess.addVertexWithUV(0.4375, 0.875, 0, 0.875, depth + 0.125);

		tess.draw();

	}

}
