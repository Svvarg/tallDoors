package tektor.minecraft.talldoors.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

import tektor.minecraft.talldoors.TallDoorsBase;
import tektor.minecraft.talldoors.container.MachineWorkbenchContainer;
import tektor.minecraft.talldoors.entities.tileentities.DrawbridgeWorkbenchTileEntity;
import tektor.minecraft.talldoors.packet.DrawBridgeWorkbenchPacket;

public class MachineWorkbenchGUI extends GuiContainer {

	private int x, y, z, s;
	private int planks, iron, wool;
	private DrawbridgeWorkbenchTileEntity te;
	public MachineWorkbenchGUI(EntityPlayer player,
			InventoryPlayer inventoryPlayer, DrawbridgeWorkbenchTileEntity e) {
		super(new MachineWorkbenchContainer(inventoryPlayer, e));
		y = z = s = 1;
		x = 2;
		recalc();
		te = e;
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int param1, int param2) {
		// draw text and stuff here
		// the parameters for drawString are: string, x, y, color
		fontRendererObj.drawString("Drawbridge Workbench", 8, 6, 9919952);
		// draws "Inventory"
//		fontRenderer.drawString(
//				StatCollector.translateToLocal("container.inventory"), 8,
//				ySize - 96 + 2, 9919952);
		fontRendererObj.drawString("" + x, 27, 35, 0000000);
		fontRendererObj.drawString("" + y, 27, 51, 0000000);
		fontRendererObj.drawString("" + z, 27, 67, 0000000);
		fontRendererObj.drawString("" + s, 69, 51, 0000000);
		fontRendererObj.drawString("Planks: " + planks, 117, 15, 9919952);
		fontRendererObj.drawString("Iron: " + iron, 117, 25, 9919952);
		fontRendererObj.drawString("Wool: " + wool, 117, 35, 9919952);
		fontRendererObj.drawString("Width,Depth,", 8, 16, 9919952);
		fontRendererObj.drawString("Height:", 8, 24, 9919952);
		fontRendererObj.drawString("Spool:", 57, 38, 9919952);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float par1, int par2,
			int par3) {
		// draw your Gui here, only thing you need to change is the path
		this.mc.renderEngine.bindTexture(new ResourceLocation("talldoors",
				"textures/gui/machineWorkbenchGUI.png"));
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
				&& x > 2) {
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
		}else if (par3 == 0 && par1 > 11 && par1 < 23 && par2 > 65
				&& par2 < 76 && z > 1) {
			z--;
		}else if (par3 == 0 && par1 > 43 && par1 < 55 && par2 > 65
				&& par2 < 76 && z < 64) {
			z++;
		}else if (par3 == 0 && par1 > 55 && par1 < 66 && par2 > 49
				&& par2 < 61 && s > 1) {
			s--;
		} else if (par3 == 0 && par1 > 83 && par1 < 95 && par2 > 49
				&& par2 < 61 && s < (x/2)) {
			s++;
		}
		
		recalc();
		if (par3 == 0 && par1 > 96 && par1 < 126 && par2 > 57 && par2 < 73) {

			DrawBridgeWorkbenchPacket packet = new DrawBridgeWorkbenchPacket(x,y,z,s,te.xCoord,te.yCoord,te.zCoord);
			if (!te.getWorldObj().isRemote) {
			} else if (te.getWorldObj().isRemote) {
				TallDoorsBase.packetPipeline.sendToServer(packet);
			}

		}
	}

	private void recalc() {
		this.planks = 2*(x+y+z);
		this.wool = 2*(y+z+s);
		this.iron = x + y + z;

	}
}
