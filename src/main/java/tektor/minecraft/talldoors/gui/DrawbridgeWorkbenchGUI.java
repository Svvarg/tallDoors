package tektor.minecraft.talldoors.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

import org.lwjgl.opengl.GL11;

import tektor.minecraft.talldoors.TallDoorsBase;
import tektor.minecraft.talldoors.container.DrawbridgeWorkbenchContainer;
import tektor.minecraft.talldoors.entities.tileentities.DrawbridgeWorkbenchTileEntity;
import tektor.minecraft.talldoors.packet.DrawBridgeWorkbenchPacket;

public class DrawbridgeWorkbenchGUI extends GuiContainer {

	private int x, y;
	private int planks, iron;
	private DrawbridgeWorkbenchTileEntity te;
	public DrawbridgeWorkbenchGUI(EntityPlayer player,
			InventoryPlayer inventoryPlayer, DrawbridgeWorkbenchTileEntity e) {
		super(new DrawbridgeWorkbenchContainer(inventoryPlayer, e));
		x = y = 1;
		planks = 1;
		iron = 2;
		te = e;
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int param1, int param2) {
		// draw text and stuff here
		// the parameters for drawString are: string, x, y, color
		fontRendererObj.drawString("Drawbridge Workbench", 8, 6, 9919952);
		// draws "Inventory" or your regional equivalent
		fontRendererObj.drawString(
				StatCollector.translateToLocal("container.inventory"), 8,
				ySize - 96 + 2, 9919952);
		fontRendererObj.drawString("" + x, 27, 35, 0000000);
		fontRendererObj.drawString("" + y, 27, 51, 0000000);
		fontRendererObj.drawString("Fence: " + planks * 2, 62, 22, 9919952);//old Planks:
		fontRendererObj.drawString("Luivite Ingot: " + iron, 62, 32, 9919952);//old Iron:
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float par1, int par2,
			int par3) {
		// draw your Gui here, only thing you need to change is the path
		this.mc.renderEngine.bindTexture(new ResourceLocation("talldoors",
				"textures/gui/drawbridgeWorkbenchGUI.png"));
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		int x = (width - xSize) / 2;
		int y = (height - ySize) / 2;
		this.drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
	}

	@Override
	protected void mouseClicked(int par1, int par2, int par3) {
		super.mouseClicked(par1, par2, par3);
		par1 = par1 - guiLeft;
		par2 = par2 - guiTop;
		if (par3 == 0 && par1 > 11 && par1 < 23 && par2 > 33 && par2 < 45
				&& x > 1) {
			x--;
		} else if (par3 == 0 && par1 > 43 && par1 < 55 && par2 > 33
				&& par2 < 45 && x < 64) {
			x++;
		} else if (par3 == 0 && par1 > 11 && par1 < 23 && par2 > 49
				&& par2 < 61 && y > 1) {
			y--;
		} else if (par3 == 0 && par1 > 43 && par1 < 55 && par2 > 49
				&& par2 < 61 && y < 64) {
			y++;
		}
		recalc();
		if (par3 == 0 && par1 > 96 && par1 < 126 && par2 > 57 && par2 < 73) {



			DrawBridgeWorkbenchPacket packet = new DrawBridgeWorkbenchPacket(x,y,0,0,te.xCoord,te.yCoord,te.zCoord);
			if (!te.getWorldObj().isRemote) {
			} else if (te.getWorldObj().isRemote) {
				TallDoorsBase.packetPipeline.sendToServer(packet);
			}

		}
	}

	private void recalc() {
		this.planks = x * y;
		this.iron = x + y;

	}
}
