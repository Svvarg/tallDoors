package tektor.minecraft.talldoors.gui;

import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import tektor.minecraft.talldoors.TallDoorsBase;
import tektor.minecraft.talldoors.container.KeyMakerGuiContainer;
import tektor.minecraft.talldoors.entities.workbenches.KeyMaker;
import tektor.minecraft.talldoors.packet.KeyPacket;

public class KeyMakerGUI extends GuiContainer {

	private KeyMaker te;
	public GuiTextField itemNameField;

	public KeyMakerGUI(EntityPlayer player, InventoryPlayer inventoryPlayer,
			KeyMaker e) {
		super(new KeyMakerGuiContainer(inventoryPlayer, e));
		te = e;
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int param1, int param2) {
		// draw text and stuff here
		// the parameters for drawString are: string, x, y, color
		fontRendererObj.drawString("Key Maker", 8, 6, 4210752);
		// draws "Inventory" or your regional equivalent
		fontRendererObj.drawString(
				StatCollector.translateToLocal("container.inventory"), 8,
				ySize - 96 + 2, 4210752);
		fontRendererObj.drawString("gold key", 117, 46, 4210752);
		fontRendererObj.drawString("iron key", 117, 60, 4210752);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float par1, int par2,
			int par3) {
		// draw your Gui here, only thing you need to change is the path
		this.mc.renderEngine.bindTexture(new ResourceLocation("talldoors",
				"textures/gui/keyMakerGUI.png"));
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		int x = (width - xSize) / 2;
		int y = (height - ySize) / 2;
		this.drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
		this.drawTexturedModalRect(x + 35, y + 20, 0, this.ySize, 100, 12);
	}

	@Override
	protected void mouseClicked(int par1, int par2, int par3) {
		super.mouseClicked(par1, par2, par3);
		this.itemNameField.mouseClicked(par1, par2, par3);
		par1 = par1 - guiLeft;
		par2 = par2 - guiTop;

		if (par3 == 0 && par1 > 113 && par1 < 161 && par2 > 57 && par2 < 69) {
			this.press(false);
		}
		if (par3 == 0 && par1 > 113 && par1 < 161 && par2 > 43 && par2 < 55) {
			this.press(true);
		}
	}

	private void press(boolean b) {
		KeyPacket packet = new KeyPacket((int)te.posX,(int)te.posY,(int)te.posZ, b,itemNameField.getText());
		if (!te.worldObj.isRemote) {
		} else if (te.worldObj.isRemote) {
			TallDoorsBase.packetPipeline.sendToServer(packet);
		}
		
	}

	@Override
	public void initGui() {
		super.initGui();
		Keyboard.enableRepeatEvents(true);
		int i = (this.width - this.xSize) / 2;
		int j = (this.height - this.ySize) / 2;
		this.itemNameField = new GuiTextField(this.fontRendererObj, i + 37,
				j + 22, 90, 12);
		this.itemNameField.setTextColor(-1);
		this.itemNameField.setDisabledTextColour(-1);
		this.itemNameField.setEnableBackgroundDrawing(false);
		this.itemNameField.setMaxStringLength(40);
		this.itemNameField.setText("key code");
	}

	@Override
	public void onGuiClosed() {
		super.onGuiClosed();
		Keyboard.enableRepeatEvents(false);
	}

	@Override
	protected void keyTyped(char par1, int par2) {
		if (this.itemNameField.textboxKeyTyped(par1, par2)) {
			// this.rename();
		} else {
			super.keyTyped(par1, par2);
		}
	}

	@Override
	public void drawScreen(int par1, int par2, float par3) {
		super.drawScreen(par1, par2, par3);
		GL11.glDisable(GL11.GL_LIGHTING);
		this.itemNameField.drawTextBox();
	}
}
