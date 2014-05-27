package tektor.minecraft.talldoors.doorworkshop.renderer.nails;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import tektor.minecraft.talldoors.doorworkshop.DoorPartRegistry;
import tektor.minecraft.talldoors.doorworkshop.entity.doorparts.AbstractDoorPart;
import tektor.minecraft.talldoors.doorworkshop.entity.doorparts.nails.FourNailsSquarePartEntity;
import tektor.minecraft.talldoors.doorworkshop.renderer.AbstractModuleDoorRenderer;
import tektor.minecraft.talldoors.renderer.RenderUtil;

public class FourNailsSquarePartRenderer extends AbstractModuleDoorRenderer {

	@Override
	public void doRender(Entity entity, double x, double y, double z,
			float var8, float var9) {
		super.doRender(entity, x, y, z, var8, var9);
		
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity var1) {
		return new ResourceLocation(DoorPartRegistry.texturePaths.get(((AbstractDoorPart) var1).texture1));
	}

	@Override
	protected void renderingStuff(Entity entity, double x, double y, double z,
			float var8, float var9) {

		this.bindTexture(this.getEntityTexture(entity));

		FourNailsSquarePartEntity ent = (FourNailsSquarePartEntity) entity;
		RenderUtil.renderFrontBack(ent.height2, 1, ent.depth, 0, entity);

		this.bindTexture(new ResourceLocation(DoorPartRegistry.texturePaths.get(ent.sideTexture)));
		RenderUtil.renderOutline(ent.height2, 1, ent.depth, 0, ent);	
		
		this.bindTexture(new ResourceLocation(DoorPartRegistry.texturePaths.get(ent.texture2)));
		Tessellator tess = Tessellator.instance;
		
		
		drawNails(ent, tess);
		
		for (int i = 1; i < ent.height2; i++) {
			GL11.glTranslatef(0, 1, 0);
			drawNails(ent, tess);
		}
		
		
	}

	private void drawNails(FourNailsSquarePartEntity ent, Tessellator tess) {
		drawNailAt(tess,0.125,0.125,ent);
		drawNailAt(tess,0.85,0125,ent);
		drawNailAt(tess,0.85,0.85,ent);
		drawNailAt(tess,0.125,0.85,ent);
	}
	
	private void drawNailAt(Tessellator tess, double posW, double posH, FourNailsSquarePartEntity ent)
	{
		tess.startDrawingQuads();
		double depth = ent.depth;
		//bottom
		tess.addVertexWithUV(posW, posH, depth, 0.125, 0.125);
		tess.addVertexWithUV(posW+0.025, posH, depth, 0.150, 0.125);
		tess.addVertexWithUV(posW+0.025, posH, depth+0.025, 0.150, 0.150);
		tess.addVertexWithUV(posW, posH, depth+0.025, 0.125, 0.150);
		
		//top
		tess.addVertexWithUV(posW, posH+0.025, depth, 0.125, 0.125);
		tess.addVertexWithUV(posW, posH+0.025, depth+0.025, 0.125, 0.150);
		tess.addVertexWithUV(posW+0.025, posH+0.025, depth+0.025, 0.150, 0.150);
		tess.addVertexWithUV(posW+0.025, posH+0.025, depth, 0.150, 0.125);
		
		//front
		tess.addVertexWithUV(posW, posH, depth+0.025, 0.125, 0.125);
		tess.addVertexWithUV(posW+0.025, posH, depth+0.025, 0.150, 0.125);
		tess.addVertexWithUV(posW+0.025, posH+0.025, depth+0.025,0.150, 0.150);
		tess.addVertexWithUV(posW, posH+0.025, depth+0.025, 0.150, 0.125);
		
		//left
		tess.addVertexWithUV(posW, posH, depth, 0.125, 0.125);
		tess.addVertexWithUV(posW, posH, depth+0.025, 0.150, 0.125);
		tess.addVertexWithUV(posW, posH+0.025, depth+0.025, 0.150, 0.150);
		tess.addVertexWithUV(posW, posH+0.025, depth, 0.125, 0.150);
		
		//right
		tess.addVertexWithUV(posW+0.025, posH, depth, 0.125, 0.125);
		tess.addVertexWithUV(posW+0.025, posH+0.025, depth, 0.125, 0.150);
		tess.addVertexWithUV(posW+0.025, posH+0.025, depth+0.025, 0.150, 0.150);
		tess.addVertexWithUV(posW+0.025, posH, depth+0.025, 0.150, 0.125);
		tess.draw();
	}

}
