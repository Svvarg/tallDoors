package tektor.minecraft.talldoors.doorworkshop;

import java.util.SortedSet;
import java.util.TreeSet;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import tektor.minecraft.talldoors.TallDoorsBase;
import tektor.minecraft.talldoors.container.MosaicGuiContainer;
import tektor.minecraft.talldoors.entities.tileentities.MosaicTileEntity;
import tektor.minecraft.talldoors.packet.MosaicPacket;
import tektor.minecraft.talldoors.services.MosaicIconRegistry;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;

public class DoorModuleWorkbenchGUI extends GuiContainer{
	
	String[] keys;
	String[] keysOrigin;
	int off;
	int page;
	int pages;
	String chosen;
	public GuiTextField itemNameField;
	public GuiButton button;
	
	private DoorModuleWorkbenchTileEntity te;

	public DoorModuleWorkbenchGUI(EntityPlayer player,
			InventoryPlayer inventoryPlayer, DoorModuleWorkbenchTileEntity e) {
		super(new DoorModuleWorkbenchContainer(inventoryPlayer, e));
		te = e;
		off = 0;
		chosen = "plain";
		xSize = 256;
		SortedSet key = new TreeSet();
		key.addAll(DoorPartRegistry.registeredParts.keySet());
		keys = keysOrigin = (String[]) key.toArray(new String[0]);
		page = 1;
		pages = (int) Math.ceil(keys.length / 10f);
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int param1, int param2) {
		// draw text and stuff here
		// the parameters for drawString are: string, x, y, color
		fontRendererObj.drawString("Door Modules", 8, 6, 9919952);
		fontRendererObj.drawString("Modules:", 8, 17, 9919952);
		for (int i = off; (i < off + 10 && i < keys.length); i++) {
			int color = 9919952;
			if (chosen.equals(keys[i]))
				color = 5919952;

			fontRendererObj.drawString(keys[i], 8, 27 + (i - off) * 10, color);
		}
		fontRendererObj.drawString(page + "/" + pages, 48, 127, 9919952);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float par1, int par2,
			int par3) {
		// draw your Gui here, only thing you need to change is the path
		this.mc.renderEngine.bindTexture(new ResourceLocation("talldoors",
				"textures/gui/doorModuleGUI.png"));
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		int x = (width - xSize) / 2;
		int y = (height - ySize) / 2;
		this.drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
		this.drawTexturedModalRect(x+40, y +137, 0, 166, 14, 15);
		this.drawTexturedModalRect(x+59, y +137, 14, 166, 14, 15);
		drawPreview(x, y);
	}
	
	public void drawPreview(int x, int y) {
		this.mc.renderEngine.bindTexture(new ResourceLocation("talldoors",
				"textures/doorparts/preview/" + chosen + ".png"));
		Tessellator tessellator = Tessellator.instance;
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GL11.glDisable(GL11.GL_ALPHA_TEST);
		tessellator.startDrawingQuads();
		tessellator.addVertexWithUV(x + 163, y + 16 + 64, 0, 0.0, 1.0);
		tessellator.addVertexWithUV(x + 163 + 64, y + 16 + 64, 0, 1.0, 1.0);
		tessellator.addVertexWithUV(x + 163 + 64, y + 16, 0, 1.0, 0.0);
		tessellator.addVertexWithUV(x + 163, y + 16, 0, 0.0, 0.0);
		tessellator.draw();
		GL11.glEnable(GL11.GL_ALPHA_TEST);
		GL11.glDisable(GL11.GL_BLEND);
	}
	
	public void onGuiClosed() {
		super.onGuiClosed();
		
		Keyboard.enableRepeatEvents(false);
	}
	
	@Override
	protected void keyTyped(char par1, int par2) {
		if (this.itemNameField.textboxKeyTyped(par1, par2)) {
			this.search();
		} else {
			super.keyTyped(par1, par2);
		}
	}
	
	private void search() {
		SortedSet key = new TreeSet();
		
		for(String s : keysOrigin)
		{
			if(s.contains(itemNameField.getText())) key.add(s);
		}
		keys = (String[]) key.toArray(new String[0]);
		
	}

	@Override
	public void initGui() {
		super.initGui();
		Keyboard.enableRepeatEvents(true);
		int i = (this.width - this.xSize) / 2;
		int j = (this.height - this.ySize) / 2;
		this.itemNameField = new GuiTextField(this.fontRendererObj, i + 151,
				j + 84, 90, 12);
		this.itemNameField.setTextColor(-1);
		this.itemNameField.setDisabledTextColour(-1);
		this.itemNameField.setEnableBackgroundDrawing(false);
		this.itemNameField.setMaxStringLength(40);
		this.itemNameField.setText("");
		
		this.button = new GuiButton(i + 100, j + 100, 50, 50,14,"OK");
	}
	
	@Override
	public void drawScreen(int par1, int par2, float par3) {
		super.drawScreen(par1, par2, par3);
		GL11.glDisable(GL11.GL_LIGHTING);
		this.itemNameField.drawTextBox();
	}

}
