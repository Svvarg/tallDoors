package tektor.minecraft.talldoors.renderer;

import org.lwjgl.opengl.GL11;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import tektor.minecraft.talldoors.TallDoorsBase;
import tektor.minecraft.talldoors.entities.tileentities.MosaicGlassTileEntity;

public class RenderMosaicGlassTileEntity extends TileEntitySpecialRenderer {

	// This method is called when minecraft renders a tile entity
	public void renderTileEntityAt(TileEntity tileEntity, double d, double d1,
			double d2, float f) {
		GL11.glPushMatrix();
		// This will move our renderer so that it will be on proper place in the
		// world
		GL11.glTranslatef((float) d, (float) d1, (float) d2);
		MosaicGlassTileEntity tileEntityYour = (MosaicGlassTileEntity) tileEntity;
		/*
		 * Note that true tile entity coordinates (tileEntity.xCoord, etc) do
		 * not match to render coordinates (d, etc) that are calculated as [true
		 * coordinates] - [player coordinates (camera coordinates)]
		 */
		renderBlockYour(tileEntityYour, tileEntity.getWorldObj(), tileEntity.xCoord,
				tileEntity.yCoord, tileEntity.zCoord, TallDoorsBase.mosaicGlass);
		GL11.glPopMatrix();
	}

	// And this method actually renders your tile entity
	public void renderBlockYour(MosaicGlassTileEntity tl, World world, int i,
			int j, int k, Block block) {
		Tessellator tess = Tessellator.instance;
		// This will make your block brightness dependent from surroundings
		// lighting.
		RenderHelper.disableStandardItemLighting();
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glDisable(GL11.GL_CULL_FACE);
        
        if (Minecraft.isAmbientOcclusionEnabled())
        {
            GL11.glShadeModel(GL11.GL_SMOOTH);
        }
        else
        {
            GL11.glShadeModel(GL11.GL_FLAT);
        }
        
		int dir = world.getBlockMetadata(i, j, k);
		//adjustLightFixture(world, i, j, k, block);
		GL11.glPushMatrix();
		GL11.glTranslatef(0.5F, 0, 0.5F);
		// This line actually rotates the renderer.
		GL11.glRotatef(dir * (-90F), 0F, 1F, 0F);
		GL11.glTranslatef(-0.5F, 0, -0.5F);
		bindTexture(new ResourceLocation("talldoors:textures/blocks/mosaic/"
				+ tl.icon + ".png"));
		tess.startDrawingQuads();
		tess.setColorRGBA_F(1, 1, 1, 0.5f);
		
		float f1 = 0.001f;
		float f2 = 0.999f;
		tess.addVertexWithUV(f1, f1, f1, 0, 1);
		tess.addVertexWithUV(f1, f1, f2, 1, 1);
		tess.addVertexWithUV(f1, f2, f2, 1, 0);
		tess.addVertexWithUV(f1, f2, f1, 0, 0);
		
		tess.addVertexWithUV(f1, f1, f1, 1, 1);
		tess.addVertexWithUV(f2, f1, f1, 1, 0);
		tess.addVertexWithUV(f2, f1, f2, 0, 0);
		tess.addVertexWithUV(f1, f1, f2, 0, 1);
		
		tess.addVertexWithUV(f1, f1, f1, 1, 1);
		tess.addVertexWithUV(f1, f2, f1, 1, 0);
		tess.addVertexWithUV(f2, f2, f1, 0, 0);
		tess.addVertexWithUV(f2, f1, f1, 0, 1);
		
		tess.addVertexWithUV(f1, f2, f1, 0, 1);
		tess.addVertexWithUV(f1, f2, f2, 1, 1);
		tess.addVertexWithUV(f2, f2, f2, 1, 0);
		tess.addVertexWithUV(f2, f2, f1, 0, 0);
		
		tess.addVertexWithUV(f2, f2, f1, 1, 0);
		tess.addVertexWithUV(f2, f2, f2, 0, 0);
		tess.addVertexWithUV(f2, f1, f2, 0, 1);
		tess.addVertexWithUV(f2, f1, f1, 1, 1);
		
		tess.addVertexWithUV(f1, f1, f2, 0, 1);
		tess.addVertexWithUV(f2, f1, f2, 1, 1);
		tess.addVertexWithUV(f2, f2, f2, 1, 0);
		tess.addVertexWithUV(f1, f2, f2, 0, 0);

		tess.draw();
		tess.setColorRGBA_F(1, 1, 1, 1);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		RenderHelper.enableStandardItemLighting();
		GL11.glDisable(GL11.GL_BLEND);

		GL11.glPopMatrix();
	}

//	private void adjustLightFixture(World world, int i, int j, int k,
//			Block block) {
//		Tessellator tess = Tessellator.instance;
//		float brightness = block.getBlockBrightness(world, i, j, k);
//		int skyLight = world.getLightBrightnessForSkyBlocks(i, j, k, 0);
//		int modulousModifier = skyLight % 65536;
//		int divModifier = skyLight / 65536;
//		tess.setColorOpaque_F(brightness, brightness, brightness);
//		OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit,
//				(float) modulousModifier, divModifier);
//	}
}