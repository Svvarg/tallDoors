package tektor.minecraft.talldoors.doorworkshop.renderer.windows;

import org.lwjgl.opengl.GL11;

import tektor.minecraft.talldoors.doorworkshop.entity.doorparts.AbstractDoorPart;
import tektor.minecraft.talldoors.doorworkshop.renderer.AbstractModuleDoorRenderer;
import tektor.minecraft.talldoors.renderer.RenderUtil;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class SimpleWindowPartRenderer extends AbstractModuleDoorRenderer {

	@Override
	public void doRender(Entity entity, double x, double y, double z,
			float var8, float var9) {
		super.doRender(entity, x, y, z, var8, var9);

	}

	@Override
	protected ResourceLocation getEntityTexture(Entity var1) {
		return new ResourceLocation("talldoors:textures/doorparts/plain.png");
	}

	@Override
	protected void renderingStuff(Entity entity, double x, double y, double z,
			float var8, float var9) {

		AbstractDoorPart ent = (AbstractDoorPart) entity;
		
		this.bindTexture(new ResourceLocation("talldoors:textures/doorparts/side.png"));
		RenderUtil.renderOutline(ent.height2, 1, ent.depth, 0, ent);
		
		this.bindTexture(this.getEntityTexture(entity));

		Tessellator tess = Tessellator.instance;
		drawWindow(ent, tess);
		for(int i = 1; i < ent.height2; i++)
		{
			GL11.glTranslatef(0, 1, 0);
			drawWindow(ent,tess);		
		}

		
	}

	private void drawWindow(AbstractDoorPart ent, Tessellator tess) {
		tess.startDrawingQuads();
		float depth = ent.depth;
		//front
		///left
		tess.addVertexWithUV(0, 0, depth, 0, 0);
		tess.addVertexWithUV(0.125f, 0, depth, 0.125f, 0);
		tess.addVertexWithUV(0.125f, 1, depth, 0.125f, 1);
		tess.addVertexWithUV(0, 1, depth, 0, 1);
		///right
		tess.addVertexWithUV(0.875f, 0, depth, 0.875f, 0);
		tess.addVertexWithUV(1, 0, depth, 1, 0);
		tess.addVertexWithUV(1, 1, depth, 1, 1);
		tess.addVertexWithUV(0.875f, 1, depth, 0.875f, 1);
		///top
		tess.addVertexWithUV(0.125f, 0.875f, depth, 0.125f, 0.875f);
		tess.addVertexWithUV(0.875f, 0.875f, depth, 0.875f, 0.875f);
		tess.addVertexWithUV(0.875f, 1, depth, 0.875f, 1);
		tess.addVertexWithUV(0.125f, 1, depth, 0.125f, 1);
		///Bottom
		tess.addVertexWithUV(0.125f, 0, depth, 0.125f, 0);
		tess.addVertexWithUV(0.875f, 0, depth, 0.875f, 0);
		tess.addVertexWithUV(0.875f, 0.125f, depth, 0.875f, 0.125f);
		tess.addVertexWithUV(0.125f, 0.125f, depth, 0.125f, 0.125f);
		///Cross left to right
		tess.addVertexWithUV(0.125f, 0.4375f, depth, 0.125f, 0.4375f);
		tess.addVertexWithUV(0.875f, 0.4375f, depth, 0.875f, 0.4375f);
		tess.addVertexWithUV(0.875f, 0.5625f, depth, 0.875f, 0.5625f);
		tess.addVertexWithUV(0.125f, 0.5625f, depth, 0.125f, 0.5625f);
		///Cross top down bottom part
		tess.addVertexWithUV(0.4375f, 0.125f, depth, 0.4375f, 0.125f);
		tess.addVertexWithUV(0.5625, 0.125, depth, 0.5625, 0.125);
		tess.addVertexWithUV(0.5625, 0.4375, depth, 0.5625, 0.4375);
		tess.addVertexWithUV(0.4375, 0.4375, depth, 0.4375, 0.4375);
		///Cross top down top part
		tess.addVertexWithUV(0.4375f, 0.5625, depth, 0.4375f, 0.5626);
		tess.addVertexWithUV(0.5625, 0.5625, depth, 0.5625, 0.5626);
		tess.addVertexWithUV(0.5625, 0.875, depth, 0.5625, 0.875);
		tess.addVertexWithUV(0.4375, 0.875, depth, 0.4375, 0.875);
		
		//back
		tess.addVertexWithUV(0, 0, 0, 0, 0);
		tess.addVertexWithUV(0, 1, 0, 0, 1);
		tess.addVertexWithUV(0.125f, 1, 0, 0.125f, 1);
		tess.addVertexWithUV(0.125f, 0, 0, 0.125f, 0);
		///right
		tess.addVertexWithUV(0.875f, 0, 0, 0.875f, 0);
		tess.addVertexWithUV(0.875f, 1, 0, 0.875f, 1);
		tess.addVertexWithUV(1, 1, 0, 1, 1);
		tess.addVertexWithUV(1, 0, 0, 1, 0);
		///top
		tess.addVertexWithUV(0.125f, 0.875f, 0, 0.125f, 0.875f);
		tess.addVertexWithUV(0.125f, 1, 0, 0.125f, 1);
		tess.addVertexWithUV(0.875f, 1, 0, 0.875f, 1);
		tess.addVertexWithUV(0.875f, 0.875f, 0, 0.875f, 0.875f);
		///Bottom
		tess.addVertexWithUV(0.125f, 0, 0, 0.125f, 0);
		tess.addVertexWithUV(0.125f, 0.125f, 0, 0.125f, 0.125f);
		tess.addVertexWithUV(0.875f, 0.125f, 0, 0.875f, 0.125f);
		tess.addVertexWithUV(0.875f, 0, 0, 0.875f, 0);
		///Cross left to right
		tess.addVertexWithUV(0.125f, 0.4375f, 0, 0.125f, 0.4375f);
		tess.addVertexWithUV(0.125f, 0.5625f, 0, 0.125f, 0.5625f);
		tess.addVertexWithUV(0.875f, 0.5625f, 0, 0.875f, 0.5625f);
		tess.addVertexWithUV(0.875f, 0.4375f, 0, 0.875f, 0.4375f);
		///Cross top down bottom part
		tess.addVertexWithUV(0.4375f, 0.125f, 0, 0.4375f, 0.125f);
		tess.addVertexWithUV(0.4375, 0.4375, 0, 0.4375, 0.4375);
		tess.addVertexWithUV(0.5625, 0.4375, 0, 0.5625, 0.4375);
		tess.addVertexWithUV(0.5625, 0.125, 0, 0.5625, 0.125);
		///Cross top down top part
		tess.addVertexWithUV(0.4375f, 0.5625, 0, 0.4375f, 0.5626);
		tess.addVertexWithUV(0.4375, 0.875, 0, 0.4375, 0.875);
		tess.addVertexWithUV(0.5625, 0.875, 0, 0.5625, 0.875);
		tess.addVertexWithUV(0.5625, 0.5625, 0, 0.5625, 0.5626);
		
		//inner
		///left bottom 
		////bottom
		tess.addVertexWithUV(0.125, 0.125, depth, 0.125, 0.125);
		tess.addVertexWithUV(0.4375, 0.125, depth, 0.4375, 0.125);
		tess.addVertexWithUV(0.4375, 0.125, 0, 0.4375, depth+0.125);
		tess.addVertexWithUV(0.125, 0.125, 0, 0.125, depth+0.125);
		////left
		tess.addVertexWithUV(0.125, 0.125, depth, 0.125, 0.125);
		tess.addVertexWithUV(0.125, 0.125, 0, 0.125, depth+0.125);
		tess.addVertexWithUV(0.125, 0.4375, 0, 0.4375, depth+0.125);
		tess.addVertexWithUV(0.125, 0.4375, depth, 0.4375, 0.125);
		///right
		tess.addVertexWithUV(0.4375, 0.125, depth, 0.125, 0.125);
		tess.addVertexWithUV(0.4375, 0.4375, depth, 0.4375, 0.125);
		tess.addVertexWithUV(0.4375, 0.4375, 0, 0.4375, depth+0.125);
		tess.addVertexWithUV(0.4375, 0.125, 0, 0.125, depth+0.125);
		////top
		tess.addVertexWithUV(0.125, 0.4375, depth, 0.125, 0.125);
		tess.addVertexWithUV(0.125, 0.4375, 0, 0.125, depth+0.125);
		tess.addVertexWithUV(0.4375, 0.4375, 0, 0.4375, depth+0.125);
		tess.addVertexWithUV(0.4375, 0.4375, depth, 0.4375, 0.125);
		
		///left top
		tess.addVertexWithUV(0.125, 0.5625, depth, 0.125, 0.125);
		tess.addVertexWithUV(0.4375, 0.5625, depth, 0.4375, 0.125);
		tess.addVertexWithUV(0.4375, 0.5625, 0, 0.4375, depth+0.125);
		tess.addVertexWithUV(0.125, 0.5625, 0, 0.125, depth+0.125);
		////left
		tess.addVertexWithUV(0.125, 0.5625, depth, 0.125, 0.125);
		tess.addVertexWithUV(0.125, 0.5625, 0, 0.125, depth+0.125);
		tess.addVertexWithUV(0.125, 0.875, 0, 0.4375, depth+0.125);
		tess.addVertexWithUV(0.125, 0.875, depth, 0.4375, 0.125);
		///right
		tess.addVertexWithUV(0.4375, 0.5625, depth, 0.125, 0.125);
		tess.addVertexWithUV(0.4375, 0.875, depth, 0.4375, 0.125);
		tess.addVertexWithUV(0.4375, 0.875, 0, 0.4375, depth+0.125);
		tess.addVertexWithUV(0.4375, 0.5625, 0, 0.125, depth+0.125);
		////top
		tess.addVertexWithUV(0.125, 0.875, depth, 0.125, 0.125);
		tess.addVertexWithUV(0.125, 0.875, 0, 0.125, depth+0.125);
		tess.addVertexWithUV(0.4375, 0.875, 0, 0.4375, depth+0.125);
		tess.addVertexWithUV(0.4375, 0.875, depth, 0.4375, 0.125);
		
		///right bottom
		tess.addVertexWithUV(0.5625, 0.125, depth, 0.125, 0.125);
		tess.addVertexWithUV(0.875, 0.125, depth, 0.4375, 0.125);
		tess.addVertexWithUV(0.875, 0.125, 0, 0.4375, depth+0.125);
		tess.addVertexWithUV(0.5625, 0.125, 0, 0.125, depth+0.125);
		////left
		tess.addVertexWithUV(0.5625, 0.125, depth, 0.125, 0.125);
		tess.addVertexWithUV(0.5625, 0.125, 0, 0.125, depth+0.125);
		tess.addVertexWithUV(0.5625, 0.4375, 0, 0.4375, depth+0.125);
		tess.addVertexWithUV(0.5625, 0.4375, depth, 0.4375, 0.125);
		///right
		tess.addVertexWithUV(0.875, 0.125, depth, 0.125, 0.125);
		tess.addVertexWithUV(0.875, 0.4375, depth, 0.4375, 0.125);
		tess.addVertexWithUV(0.875, 0.4375, 0, 0.4375, depth+0.125);
		tess.addVertexWithUV(0.875, 0.125, 0, 0.125, depth+0.125);
		////top
		tess.addVertexWithUV(0.5625, 0.4375, depth, 0.125, 0.125);
		tess.addVertexWithUV(0.5625, 0.4375, 0, 0.125, depth+0.125);
		tess.addVertexWithUV(0.875, 0.4375, 0, 0.4375, depth+0.125);
		tess.addVertexWithUV(0.875, 0.4375, depth, 0.4375, 0.125);
		
		///right top
		tess.addVertexWithUV(0.5625, 0.5625, depth, 0.125, 0.125);
		tess.addVertexWithUV(0.875, 0.5625, depth, 0.4375, 0.125);
		tess.addVertexWithUV(0.875, 0.5625, 0, 0.4375, depth+0.125);
		tess.addVertexWithUV(0.5625, 0.5625, 0, 0.125, depth+0.125);
		////left
		tess.addVertexWithUV(0.5625, 0.5625, depth, 0.125, 0.125);
		tess.addVertexWithUV(0.5625, 0.5625, 0, 0.125, depth+0.125);
		tess.addVertexWithUV(0.5625, 0.875, 0, 0.4375, depth+0.125);
		tess.addVertexWithUV(0.5625, 0.875, depth, 0.4375, 0.125);
		///right
		tess.addVertexWithUV(0.875, 0.5625, depth, 0.125, 0.125);
		tess.addVertexWithUV(0.875, 0.875, depth, 0.4375, 0.125);
		tess.addVertexWithUV(0.875, 0.875, 0, 0.4375, depth+0.125);
		tess.addVertexWithUV(0.875, 0.5625, 0, 0.125, depth+0.125);
		////top
		tess.addVertexWithUV(0.5625, 0.875, depth, 0.125, 0.125);
		tess.addVertexWithUV(0.5625, 0.875, 0, 0.125, depth+0.125);
		tess.addVertexWithUV(0.875, 0.875, 0, 0.4375, depth+0.125);
		tess.addVertexWithUV(0.875, 0.875, depth, 0.4375, 0.125);
		tess.draw();
	}

}
