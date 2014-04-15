package tektor.minecraft.talldoors.renderer;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

import tektor.minecraft.talldoors.client.TallDoorsClientProxy;
import tektor.minecraft.talldoors.entities.drawbridge.DrawbridgeBase;
import tektor.minecraft.talldoors.entities.tileentities.MosaicTileEntity;
import tektor.minecraft.talldoors.services.MosaicIconRegistry;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class RenderMosaicTileEnity implements ISimpleBlockRenderingHandler {
	
	@Override
    public void renderInventoryBlock(Block block, int metadata, int modelID,
                    RenderBlocks renderer) {
		    }

    @Override
    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z,
                    Block block, int modelId, RenderBlocks renderer) {
    	MosaicTileEntity te = (MosaicTileEntity) world.getTileEntity(x, y, z);
    	IIcon ic = MosaicIconRegistry.getIcon(te.icon);
    	renderer.renderAllFaces = true;
    	float f = 0.1875F;
        renderer.setOverrideBlockTexture(ic);
        renderer.setRenderBounds(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
        renderer.renderStandardBlock(block, x, y, z);
        renderer.renderAllFaces = false;
        renderer.clearOverrideBlockTexture();
		return true;
    }


   
    @Override
    public boolean shouldRender3DInInventory(int modelID) {
           
            return false;
    }

    @Override
    public int getRenderId() {
           
            return TallDoorsClientProxy.mosaicRenderType;
    }

}