package tektor.minecraft.talldoors.gui;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import tektor.minecraft.talldoors.container.DrawbridgeWorkbenchContainer;
import tektor.minecraft.talldoors.entities.tileentities.DrawbridgeWorkbenchTileEntity;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.network.Player;

public class DrawbridgeWorkbenchGUI extends GuiContainer {

	private int x, y;
	private int planks, iron;
	private InventoryPlayer inv;
	private DrawbridgeWorkbenchTileEntity te;
	private EntityPlayer play;

	public DrawbridgeWorkbenchGUI(EntityPlayer player,
			InventoryPlayer inventoryPlayer, DrawbridgeWorkbenchTileEntity e) {
		super(new DrawbridgeWorkbenchContainer(inventoryPlayer, e));
		x = y = 1;
		planks = 1;
		iron = 2;
		inv = inventoryPlayer;
		te = e;
		play = player;
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int param1, int param2) {
		// draw text and stuff here
		// the parameters for drawString are: string, x, y, color
		fontRenderer.drawString("Drawbridge Workbench", 8, 6, 9919952);
		// draws "Inventory" or your regional equivalent
		fontRenderer.drawString(
				StatCollector.translateToLocal("container.inventory"), 8,
				ySize - 96 + 2, 9919952);
		fontRenderer.drawString("" + x, 27, 35, 0000000);
		fontRenderer.drawString("" + y, 27, 51, 0000000);
		fontRenderer.drawString("Planks: " + planks, 62, 22, 9919952);
		fontRenderer.drawString("Iron: " + iron, 62, 32, 9919952);
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

			ByteArrayOutputStream bos = new ByteArrayOutputStream(8);
			DataOutputStream outputStream = new DataOutputStream(bos);
			try {
				outputStream.writeInt(this.x);
				outputStream.writeInt(this.y);
				outputStream.writeInt(0);
				outputStream.writeInt(0);

				outputStream.writeInt(te.xCoord);
				outputStream.writeInt(te.yCoord);
				outputStream.writeInt(te.zCoord);
			} catch (Exception ex) {
				ex.printStackTrace();
			}

			Packet250CustomPayload packet = new Packet250CustomPayload();
			packet.channel = "TallDoors";
			packet.data = bos.toByteArray();
			packet.length = bos.size();
			if (!te.worldObj.isRemote) {
			} else if (te.worldObj.isRemote) {
				EntityClientPlayerMP player2 = (EntityClientPlayerMP) this.play;
				PacketDispatcher.sendPacketToServer(packet);
			}

		}
	}

	private void recalc() {
		this.planks = x * y;
		this.iron = x + y;

	}
}
