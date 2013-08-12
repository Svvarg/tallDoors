package tektor.minecraft.talldoors.renderer;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RenderDrawbridgeBase extends Render{

	@Override
	public void doRender(Entity entity, double d0, double d1, double d2,
			float f, float f1) {
		GL11.glPushMatrix();
		GL11.glTranslatef((float)d0, (float)d1, (float)d2);
		Minecraft.getMinecraft().renderEngine.func_110577_a(func_110775_a(entity));
		Tessellator tess = Tessellator.instance;
		tess.startDrawingQuads();
		
		tess.draw();
		GL11.glPopMatrix();
		
	}
	
	public void drawBase(Tessellator tess, double pos1x, double pos1y, double pos1z, double pos2x, double pos2y, double pos2z) {
		
		tess.addVertexWithUV(pos1x,pos1y,pos1z,0,0);
		tess.addVertexWithUV(pos1x, pos1y, pos2z, 1, 0);
		tess.addVertexWithUV(pos1x, pos2y, pos2z, 1, 1);
		tess.addVertexWithUV(pos1x, pos2y, pos1z, 0, 1);
		
		tess.addVertexWithUV(pos1x,pos1y,pos2z,0,0);
		tess.addVertexWithUV(pos2x, pos1y, pos2z, 1, 0);
		tess.addVertexWithUV(pos2x, pos2y, pos2z, 1, 1);
		tess.addVertexWithUV(pos1x, pos2y, pos2z, 0, 1);
		
		tess.addVertexWithUV(pos2x,pos1y,pos2z,0,0);
		tess.addVertexWithUV(pos2x, pos1y, pos1z, 1, 0);
		tess.addVertexWithUV(pos2x, pos2y, pos1z, 1, 1);
		tess.addVertexWithUV(pos2x, pos2y, pos2z, 0, 1);
		
		tess.addVertexWithUV(pos2x,pos1y,pos1z,0,0);
		tess.addVertexWithUV(pos1x, pos1y, pos1z, 1, 0);
		tess.addVertexWithUV(pos1x, pos2y, pos1z, 1, 1);
		tess.addVertexWithUV(pos2x, pos2y, pos1z, 0, 1);
		
		tess.addVertexWithUV(pos1x,pos2y,pos1z,0,0);
		tess.addVertexWithUV(pos1x, pos2y, pos2z, 1, 0);
		tess.addVertexWithUV(pos2x, pos2y, pos2z, 1, 1);
		tess.addVertexWithUV(pos2x, pos2y, pos1z, 0, 1);
		
		tess.addVertexWithUV(pos1x,pos1y,pos1z,0,0);
		tess.addVertexWithUV(pos1x, pos1y, pos2z, 1, 0);
		tess.addVertexWithUV(pos2x, pos1y, pos2z, 1, 1);
		tess.addVertexWithUV(pos2x, pos1y, pos1z, 0, 1);
	}
	

	@Override
	protected ResourceLocation func_110775_a(Entity entity) {
		// TODO Auto-generated method stub
		return null;
	}

}
