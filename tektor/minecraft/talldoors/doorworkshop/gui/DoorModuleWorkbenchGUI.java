package tektor.minecraft.talldoors.doorworkshop.gui;

import java.util.SortedSet;
import java.util.TreeSet;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import tektor.minecraft.talldoors.TallDoorsBase;
import tektor.minecraft.talldoors.doorworkshop.DoorPartRegistry;
import tektor.minecraft.talldoors.doorworkshop.entity.DoorModuleWorkbenchTileEntity;
import tektor.minecraft.talldoors.doorworkshop.network.DoorModuleWorkbenchPacket;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class DoorModuleWorkbenchGUI extends GuiContainer {

	String[] keys;
	String[] keysOrigin;
	int off;
	int page;
	int pages;
	String chosen;
	public GuiTextField itemNameField;

	private DoorModuleWorkbenchTileEntity te;
	private String moduleType;
	private int priority;

	public DoorModuleWorkbenchGUI(EntityPlayer player,
			InventoryPlayer inventoryPlayer, DoorModuleWorkbenchTileEntity e) {
		super(new DoorModuleWorkbenchContainer(inventoryPlayer, e));
		te = e;
		off = 0;
		chosen = "plain";
		xSize = 328;
		ySize = 205;
		SortedSet<String> key = new TreeSet<String>();
		key.addAll(DoorPartRegistry.registeredParts.keySet());
		keys = keysOrigin = (String[]) key.toArray(new String[0]);
		page = 1;
		priority = 1;
		moduleType = "horiz.";
		pages = (int) Math.ceil(keys.length / 8f);
	}

	@Override
	public void drawScreen(int par1, int par2, float par3) {
		super.drawScreen(par1, par2, par3);
		GL11.glDisable(GL11.GL_LIGHTING);
		this.itemNameField.drawTextBox();
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

		// id, x,y,width,height,buttontext
		this.buttonList.clear();
		this.buttonList.add(new GuiButton(0, i + 178, j + 135, 40, 20, "OK"));
		this.buttonList.add(new GuiButton(1, i + 164, j + 95, 20, 20, "1"));
		this.buttonList.add(new GuiButton(2, i + 186, j + 95, 20, 20, "2"));
		this.buttonList.add(new GuiButton(3, i + 208, j + 95, 20, 20, "3"));
		this.buttonList.add(new GuiButton(4, i + 230, j + 95, 20, 20, "4"));
		this.buttonList.add(new GuiButton(5, i + 154, j + 115, 20, 20, "S"));
		this.buttonList.add(new GuiButton(6, i + 176, j + 115, 20, 20, "H"));
		this.buttonList.add(new GuiButton(7, i + 198, j + 115, 20, 20, "V"));
		this.buttonList.add(new GuiButton(8, i + 154, j + 135, 20, 20, "F"));
		this.buttonList.add(new GuiButton(9, i + 25, j + 102, 20, 20, "-"));
		this.buttonList.add(new GuiButton(10, i + 69, j + 102, 20, 20, "+"));
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int param1, int param2) {
		// draw text and stuff here
		// the parameters for drawString are: string, x, y, color
		fontRendererObj.drawString("Door Modules", 8, 6, -1);
		fontRendererObj.drawString("Modules:", 8, 17, -1);
		for (int i = off; (i < off + 8 && i < keys.length); i++) {
			int color = -1;
			if (chosen.equals(keys[i]))
				color = 5919952;

			fontRendererObj.drawString(keys[i], 8, 27 + (i - off) * 10, color);
		}
		fontRendererObj.drawString(page + "/" + pages, 48, 107, -1);

		fontRendererObj.drawString("Prio " + priority + " " + moduleType, 8, 122, -1);
		int i = 0;
		for (String s : DoorPartRegistry.getPartForIndex(chosen)
				.getCostAsString()) {
			fontRendererObj.drawString(s, 8, 132 + i, -1);
			i = i + 10;
		}
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
		this.drawTexturedModalRect(x, y, 0, 0, 256, 189);
		this.drawTexturedModalRect(x - 36, y + 3, 0, 189, 36, 67);
		this.drawTexturedModalRect(x + 256, y + 3, 72, 189, 36, 67);
		this.drawTexturedModalRect(x - 36, y + 70, 36, 189, 36, 67);
		this.drawTexturedModalRect(x + 256, y + 70, 108, 189, 36, 49);
		drawPreview(x, y);
	}

	public void drawPreview(int x, int y) {
		this.mc.renderEngine.bindTexture(new ResourceLocation("talldoors",
				"textures/doorparts/preview/" + chosen + ".png"));
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.drawTexturedModalRect(x + 150, y + 5, 0, 0, 100, 77);
	}

	@Override
	protected void mouseClicked(int par1, int par2, int par3) {
		super.mouseClicked(par1, par2, par3);
		this.itemNameField.mouseClicked(par1, par2, par3);
		par1 = par1 - guiLeft;
		par2 = par2 - guiTop;

		if (par3 == 0) {
			if (par2 > 26 && par2 < 37 && off < keys.length)
				chosen = keys[off];
			else if (par2 > 36 && par2 < 47 && off + 1 < keys.length)
				chosen = keys[off + 1];
			else if (par2 > 46 && par2 < 57 && off + 2 < keys.length)
				chosen = keys[off + 2];
			else if (par2 > 56 && par2 < 67 && off + 3 < keys.length)
				chosen = keys[off + 3];
			else if (par2 > 66 && par2 < 77 && off + 4 < keys.length)
				chosen = keys[off + 4];
			else if (par2 > 76 && par2 < 87 && off + 5 < keys.length)
				chosen = keys[off + 5];
			else if (par2 > 86 && par2 < 97 && off + 6 < keys.length)
				chosen = keys[off + 6];
			else if (par2 > 96 && par2 < 107 && off + 7 < keys.length)
				chosen = keys[off + 7];
		}

	}

	@Override
	protected void keyTyped(char par1, int par2) {
		if (this.itemNameField.textboxKeyTyped(par1, par2)) {
			this.search();
		} else {
			super.keyTyped(par1, par2);
		}
	}

	protected void actionPerformed(GuiButton button) {
		switch (button.id) {
		case 0: {
			DoorModuleWorkbenchPacket pack = new DoorModuleWorkbenchPacket(
					this.te.xCoord, this.te.yCoord, this.te.zCoord,
					this.priority, this.chosen, this.moduleType);
			if (!te.getWorldObj().isRemote) {
			} else if (te.getWorldObj().isRemote) {
				TallDoorsBase.packetPipeline.sendToServer(pack);
			}
			break;
		}
		case 1: {
			this.priority = 1;
			break;
		}
		case 2: {
			this.priority = 2;
			break;
		}
		case 3: {
			this.priority = 3;
			break;
		}
		case 4: {
			this.priority = 4;
			break;
		}
		case 5: {
			this.moduleType = "single";
			break;
		}
		case 6: {
			this.moduleType = "horiz.";
			break;
		}
		case 7: {
			this.moduleType = "vertical";
			break;
		}
		case 8: {
			this.moduleType = "full";
			break;
		}
		case 9: {
			if (page > 1) {
				page--;
				off = (page - 1) * 8;
			}
			break;
		}
		case 10: {
			if (page < pages) {
				page++;
				off = (page - 1) * 8;
			}
			break;
		}
		}
	}

	public void onGuiClosed() {
		super.onGuiClosed();

		Keyboard.enableRepeatEvents(false);
	}

	private void search() {
		SortedSet key = new TreeSet();

		for (String s : keysOrigin) {
			if (s.contains(itemNameField.getText()))
				key.add(s);
		}
		keys = (String[]) key.toArray(new String[0]);

	}

}
