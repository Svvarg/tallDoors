package tektor.minecraft.talldoors.renderer.drawbridge;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RenderRopeConnector extends Render{

	@Override
	public void doRender(Entity entity, double d0, double d1, double d2,
			float f, float f1) {
		GL11.glPushMatrix();
		GL11.glTranslatef((float)d0+0.375f, (float)d1, (float)d2+0.375f);
		Minecraft.getMinecraft().renderEngine.func_110577_a(func_110775_a(entity));
		Tessellator tess = Tessellator.instance;
		tess.startDrawingQuads();
		
		drawBase(tess);
		
		tess.addVertexWithUV(0, 0.25, 0, 0, 0);
		tess.addVertexWithUV(0, 0.25, 0.25, 0.5, 0);
		tess.addVertexWithUV(0, 1.25, 1.25, 0.5, 1);
		tess.addVertexWithUV(0, 1.25, 1.00, 0, 1);
		
		tess.draw();
		GL11.glPopMatrix();
		
	}

	public void drawBase(Tessellator tess) {
		tess.addVertexWithUV(0,0,0,0.5,0);
		tess.addVertexWithUV(0, 0, 0.25, 1, 0);
		tess.addVertexWithUV(0, 0.25, 0.25, 1, 1);
		tess.addVertexWithUV(0, 0.25, 0, 0.5, 1);
		
		tess.addVertexWithUV(0,0,0.25,0.5,0);
		tess.addVertexWithUV(0.25, 0, 0.25, 1, 0);
		tess.addVertexWithUV(0.25, 0.25, 0.25, 1, 1);
		tess.addVertexWithUV(0, 0.25, 0.25, 0.5, 1);
		
		tess.addVertexWithUV(0.25,0,0.25,0.5,0);
		tess.addVertexWithUV(0.25, 0, 0, 1, 0);
		tess.addVertexWithUV(0.25, 0.25, 0, 1, 1);
		tess.addVertexWithUV(0.25, 0.25, 0.25, 0.5, 1);
		
		tess.addVertexWithUV(0.25,0,0,0.5,0);
		tess.addVertexWithUV(0, 0, 0, 1, 0);
		tess.addVertexWithUV(0, 0.25, 0, 1, 1);
		tess.addVertexWithUV(0.25, 0.25, 0, 0.5, 1);
		
		tess.addVertexWithUV(0,0.25,0,0.5,0);
		tess.addVertexWithUV(0, 0.25, 0.25, 1, 0);
		tess.addVertexWithUV(0.25, 0.25, 0.25, 1, 1);
		tess.addVertexWithUV(0.25, 0.25, 0, 0.5, 1);
		
		tess.addVertexWithUV(0,0,0,0.5,0);
		tess.addVertexWithUV(0, 0, 0.25, 1, 0);
		tess.addVertexWithUV(0.25, 0, 0.25, 1, 1);
		tess.addVertexWithUV(0.25, 0, 0, 0.5, 1);
	}

	@Override
	protected ResourceLocation func_110775_a(Entity entity) {
		return new ResourceLocation("talldoors", "textures/entities/ropeConnector.png");
	}

}
