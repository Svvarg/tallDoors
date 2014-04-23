package tektor.minecraft.talldoors.doorworkshop.gui;

import org.lwjgl.opengl.GL11;

import tektor.minecraft.talldoors.doorworkshop.ModuleAssemblerTileEntity;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class ModuleAssemblerGUI extends GuiContainer{
	
	private ModuleAssemblerTileEntity te;

	public ModuleAssemblerGUI(EntityPlayer player,
			InventoryPlayer inventoryPlayer, ModuleAssemblerTileEntity e) {
		super(new ModuleAssemblerContainer(inventoryPlayer, e));
		te = e;
		xSize = 256;
		}

	@Override
	protected void drawGuiContainerBackgroundLayer(float var1, int var2,
			int var3) {
		this.mc.renderEngine.bindTexture(new ResourceLocation("talldoors",
				"textures/gui/moduleAssemblerGUI.png"));
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		int x = (width - xSize) / 2;
		int y = (height - ySize) / 2;
		this.drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
		
	}

}
