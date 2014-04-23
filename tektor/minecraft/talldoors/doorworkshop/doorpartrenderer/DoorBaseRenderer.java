package tektor.minecraft.talldoors.doorworkshop.doorpartrenderer;

import org.lwjgl.opengl.GL11;

import tektor.minecraft.talldoors.doorworkshop.doorparts.PlainDoorPartEntity;
import tektor.minecraft.talldoors.renderer.RenderUtil;
import tektor.minecraft.talldoors.doorworkshop.DoorBase;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class DoorBaseRenderer extends Render{

	@Override
	public void doRender(Entity entity, double x, double y, double z,
			float var8, float var9) {
		GL11.glPushMatrix();

		int orientation = ((DoorBase) entity).orientation;
		int pos = ((DoorBase) entity).pos;
		//System.out.println(orientation);
		if ( orientation == 0) {
			
			GL11.glTranslatef((float) x + 0.5f, (float) y, (float) z
					- 0.3f + 0.8f * pos);
			//GL11.glRotatef(180f, 0, 0f, 1f);
			int i = 0;
			i = 1;
			GL11.glRotatef(180f + i * pos * 90f, 0f,
					1f, 0f);
		}
		else if(orientation == 1)
		{
			
				if (pos == 1) {
					x = x - 0.8f;
					z = z - 0.8f;
				} else {
					
				}
			
			GL11.glTranslatef((float) x + 1.3f, (float) y + 1.5f, (float) z
					+0.5f + 0.8f * pos);
			GL11.glRotatef(180f, 0f, 0f, 1f);
			int i = 0;
			
				i = 1;
			GL11.glRotatef(270f + i * pos * 90f, 0f,
					1f, 0f);
		}
		else if(orientation == 2)
		{
			
			GL11.glTranslatef((float) x + 0.5f, (float) y + 1.5f, (float) z
					+1.3f - 0.8f * pos);
			GL11.glRotatef(180f, 0f, 0f, 1f);
			int i = 0;
			
				i = 1;
			GL11.glRotatef(0f + i * pos * 90f, 0f,
					1f, 0f);
		}
		else if(orientation == 3)
		{
			
			
				if (pos == 1) {
					x = x - 0.8f;
					z = z - 0.8f;
				} else {
					x = x - 1.6f;
				}
			
			GL11.glTranslatef((float) x + 1.3f, (float) y + 1.5f, (float) z
					+0.5f + 0.8f * pos);
			GL11.glRotatef(180f, 0f, 0f, 1f);
			int i = 0;
			
				i = 1;
			GL11.glRotatef(90f + i * pos * 90f, 0f,
					1f, 0f);
		}
		GL11.glScalef(1f, 1f, 1f);
		this.bindTexture(this.getEntityTexture(entity));
		
		DoorBase ent = (DoorBase)entity;
		
		RenderUtil.renderOutline(ent.height2, ent.width2, ent.depth, 0, ent);
		GL11.glPopMatrix();
		
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity var1) {
		return new ResourceLocation("talldoors:textures/doorparts/side.png");
	}

}
