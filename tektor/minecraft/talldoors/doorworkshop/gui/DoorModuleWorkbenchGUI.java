package tektor.minecraft.talldoors.doorworkshop.gui;

import java.util.List;
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
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.util.ResourceLocation;

public class DoorModuleWorkbenchGUI extends GuiContainer {

	String[] keys;
	String[] keysOrigin;
	String[] keysT, keysT2, keysSide;
	String[] keysTOrigin;
	int off, off2, off3, offSide;
	int page, pageT, pageT2, pageSide;
	int pages, pagesT;
	String chosen;
	public GuiTextField itemNameField, itemNameField2;
	String texture1, texture2, sideTexture;

	private DoorModuleWorkbenchTileEntity te;
	private String moduleType;
	private int priority;
	private int mode;

	public DoorModuleWorkbenchGUI(EntityPlayer player,
			InventoryPlayer inventoryPlayer, DoorModuleWorkbenchTileEntity e) {
		super(new DoorModuleWorkbenchContainer(inventoryPlayer, e));
		te = e;
		off = off2 = off3 = offSide = 0;
		chosen = "plain";
		xSize = 328;
		ySize = 205;
		SortedSet<String> key = new TreeSet<String>();

		key.addAll(DoorPartRegistry.registeredParts.keySet());
		keys = keysOrigin = (String[]) key.toArray(new String[0]);

		SortedSet<String> keyT = new TreeSet<String>();

		keyT.addAll(DoorPartRegistry.texturePaths.keySet());
		keysT = keysT2 = keysTOrigin = keysSide = (String[]) keyT.toArray(new String[0]);

		page = 1;
		pageT = 1;
		pageT2 = 1;
		pageSide = 1;
		priority = 1;
		moduleType = "horiz.";
		texture1 = "plain";
		texture2 = "horizontalBalk";
		sideTexture = "side";
		pages = (int) Math.ceil(keys.length / 7f);
		pagesT = (int) Math.ceil(keysT.length / 7f);
	}

	@Override
	public void drawScreen(int par1, int par2, float par3) {
		super.drawScreen(par1, par2, par3);
		GL11.glDisable(GL11.GL_LIGHTING);
		this.itemNameField.drawTextBox();
		this.itemNameField2.drawTextBox();
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
		this.itemNameField.setEnableBackgroundDrawing(true);
		this.itemNameField.setMaxStringLength(40);
		this.itemNameField.setText("");

		this.itemNameField2 = new GuiTextField(this.fontRendererObj, i + 21,
				j + 84, 90, 12);
		this.itemNameField2.setTextColor(-1);
		this.itemNameField2.setDisabledTextColour(-1);
		this.itemNameField2.setEnableBackgroundDrawing(true);
		this.itemNameField2.setMaxStringLength(40);
		this.itemNameField2.setText("");
		addModuleChooseElements();
	}

	@SuppressWarnings("unchecked")
	private void addModuleChooseElements() {
		int i = (this.width - this.xSize) / 2;
		int j = (this.height - this.ySize) / 2;
		this.itemNameField.setVisible(true);
		this.itemNameField2.setVisible(false);

		((List<Slot>) this.te.container.inventorySlots).get(0).yDisplayPosition = 133;

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

		this.buttonList.add(new GuiButton(11, i, j - 20, 50, 20, "Modules"));
		this.buttonList.add(new GuiButton(12, i + 50, j - 20, 50, 20,
				"Textures"));
		this.buttonList.add(new GuiButton(17, i + 100, j - 20, 50, 20,
				"Sides"));

	}

	@SuppressWarnings("unchecked")
	private void addTextureChooseElements() {
		int i = (this.width - this.xSize) / 2;
		int j = (this.height - this.ySize) / 2;
		this.buttonList.clear();
		((List<Slot>) this.te.container.inventorySlots).get(0).yDisplayPosition = -1000;
		// this.itemNameField.setVisible(false);
		this.itemNameField2.setVisible(true);
		this.buttonList.add(new GuiButton(11, i, j - 20, 50, 20, "Modules"));
		this.buttonList.add(new GuiButton(12, i + 50, j - 20, 50, 20,
				"Textures"));
		this.buttonList.add(new GuiButton(13, i + 19, j + 99, 20, 20, "-"));
		this.buttonList.add(new GuiButton(14, i + 93, j + 99, 20, 20, "+"));
		this.buttonList.add(new GuiButton(15, i + 149, j + 99, 20, 20, "-"));
		this.buttonList.add(new GuiButton(16, i + 223, j + 99, 20, 20, "+"));

		this.buttonList.add(new GuiButton(17, i + 100, j - 20, 50, 20,
				"Sides"));
	}
	@SuppressWarnings("unchecked")
	private void addSideChooseElements() {
		int i = (this.width - this.xSize) / 2;
		int j = (this.height - this.ySize) / 2;
		this.buttonList.clear();
		((List<Slot>) this.te.container.inventorySlots).get(0).yDisplayPosition = -1000;
		this.itemNameField.setVisible(false);
		this.itemNameField2.setVisible(true);
		this.buttonList.add(new GuiButton(11, i, j - 20, 50, 20, "Modules"));
		this.buttonList.add(new GuiButton(12, i + 50, j - 20, 50, 20,
				"Textures"));
		this.buttonList.add(new GuiButton(13, i + 19, j + 99, 20, 20, "-"));
		this.buttonList.add(new GuiButton(14, i + 93, j + 99, 20, 20, "+"));
		this.buttonList.add(new GuiButton(17, i + 100, j - 20, 50, 20,
				"Sides"));
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int param1, int param2) {
		if (mode == 0) {
			// draw text and stuff here
			// the parameters for drawString are: string, x, y, color
			fontRendererObj.drawString("Door Modules", 8, 6, -1);
			fontRendererObj.drawString("Modules:", 8, 17, -1);
			for (int i = off; (i < off + 7 && i < keys.length); i++) {
				int color = -1;
				if (chosen.equals(keys[i]))
					color = 5919952;

				fontRendererObj.drawString(keys[i], 8, 27 + (i - off) * 10,
						color);
			}
			fontRendererObj.drawString(page + "/" + pages, 48, 107, -1);
			fontRendererObj.drawString("Prio " + priority + " " + moduleType,
					8, 122, -1);
			int i = 0;
			for (String s : DoorPartRegistry.getPartForIndex(chosen)
					.getCostAsString()) {
				fontRendererObj.drawString(s, 8, 132 + i, -1);
				i = i + 10;
			}
		} else if (mode == 1) {
			for (int i = off2; (i < off2 + 7 && i < keysT.length); i++) {
				int color = -1;
				if (texture1.equals(keysT[i]))
					color = 5919952;

				fontRendererObj.drawString(keysT[i], 8, 7 + (i - off2) * 10,
						color);
			}
			fontRendererObj.drawString(pageT + "/" + pagesT, 58, 105, -1);

			for (int i = off3; (i < off3 + 7 && i < keysT2.length); i++) {
				int color = -1;
				if (texture2.equals(keysT2[i]))
					color = 5919952;

				fontRendererObj.drawString(keysT2[i], 140, 7 + (i - off3) * 10,
						color);
			}
			fontRendererObj.drawString(pageT2 + "/" + pagesT, 190, 105, -1);
		}
		else if (mode == 2)
		{
			for (int i = offSide; (i < offSide + 7 && i < keysSide.length); i++) {
				int color = -1;
				if (texture1.equals(keysSide[i]))
					color = 5919952;

				fontRendererObj.drawString(keysSide[i], 8, 7 + (i - offSide) * 10,
						color);
			}
			fontRendererObj.drawString(pageSide + "/" + pagesT, 58, 105, -1);
		}
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float par1, int par2,
			int par3) {
		if (mode == 0) {
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
		} else if (mode == 1) {
			this.mc.renderEngine.bindTexture(new ResourceLocation("talldoors",
					"textures/gui/doorTextureGUI.png"));
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			int x = (width - xSize) / 2;
			int y = (height - ySize) / 2;
			this.drawTexturedModalRect(x, y, 0, 0, 256, 189);
			this.drawTexturedModalRect(x - 36, y + 3, 0, 189, 36, 67);
			this.drawTexturedModalRect(x + 256, y + 3, 72, 189, 36, 67);
			this.drawTexturedModalRect(x - 36, y + 70, 36, 189, 36, 67);
			this.drawTexturedModalRect(x + 256, y + 70, 108, 189, 36, 49);
			drawPreviewT1(x, y);
			drawPreviewT2(x, y);
		}
		else if (mode == 2){
			this.mc.renderEngine.bindTexture(new ResourceLocation("talldoors",
					"textures/gui/doorTextureGUI.png"));
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			int x = (width - xSize) / 2;
			int y = (height - ySize) / 2;
			this.drawTexturedModalRect(x, y, 0, 0, 256, 189);
			this.drawTexturedModalRect(x - 36, y + 3, 0, 189, 36, 67);
			this.drawTexturedModalRect(x + 256, y + 3, 72, 189, 36, 67);
			this.drawTexturedModalRect(x - 36, y + 70, 36, 189, 36, 67);
			this.drawTexturedModalRect(x + 256, y + 70, 108, 189, 36, 49);
			drawPreviewSide(x, y);
		}
	}
	
	public void drawPreviewSide(int x, int y) {
		this.mc.renderEngine.bindTexture(new ResourceLocation(
				DoorPartRegistry.texturePaths.get(sideTexture)));
		Tessellator tessellator = Tessellator.instance;
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GL11.glDisable(GL11.GL_ALPHA_TEST);
		tessellator.startDrawingQuads();
		tessellator.addVertexWithUV(x + 46, y + 160, 0, 0.0, 1.0);
		tessellator.addVertexWithUV(x + 86, y + 160, 0, 1.0, 1.0);
		tessellator.addVertexWithUV(x + 86, y + 120, 0, 1.0, 0.0);
		tessellator.addVertexWithUV(x + 46, y + 120, 0, 0.0, 0.0);
		tessellator.draw();
		GL11.glEnable(GL11.GL_ALPHA_TEST);
		GL11.glDisable(GL11.GL_BLEND);
	}

	public void drawPreviewT1(int x, int y) {
		this.mc.renderEngine.bindTexture(new ResourceLocation(
				DoorPartRegistry.texturePaths.get(texture1)));
		Tessellator tessellator = Tessellator.instance;
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GL11.glDisable(GL11.GL_ALPHA_TEST);
		tessellator.startDrawingQuads();
		tessellator.addVertexWithUV(x + 46, y + 160, 0, 0.0, 1.0);
		tessellator.addVertexWithUV(x + 86, y + 160, 0, 1.0, 1.0);
		tessellator.addVertexWithUV(x + 86, y + 120, 0, 1.0, 0.0);
		tessellator.addVertexWithUV(x + 46, y + 120, 0, 0.0, 0.0);
		tessellator.draw();
		GL11.glEnable(GL11.GL_ALPHA_TEST);
		GL11.glDisable(GL11.GL_BLEND);
	}

	public void drawPreviewT2(int x, int y) {
		this.mc.renderEngine.bindTexture(new ResourceLocation(
				DoorPartRegistry.texturePaths.get(texture2)));
		Tessellator tessellator = Tessellator.instance;
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GL11.glDisable(GL11.GL_ALPHA_TEST);
		tessellator.startDrawingQuads();
		tessellator.addVertexWithUV(x + 175, y + 160, 0, 0.0, 1.0);
		tessellator.addVertexWithUV(x + 215, y + 160, 0, 1.0, 1.0);
		tessellator.addVertexWithUV(x + 215, y + 120, 0, 1.0, 0.0);
		tessellator.addVertexWithUV(x + 175, y + 120, 0, 0.0, 0.0);
		tessellator.draw();
		GL11.glEnable(GL11.GL_ALPHA_TEST);
		GL11.glDisable(GL11.GL_BLEND);
	}

	public void drawPreview(int x, int y) {
		this.mc.renderEngine.bindTexture(new ResourceLocation("talldoors",
				"textures/doorparts/preview/" + chosen + ".jpg"));
		Tessellator tessellator = Tessellator.instance;
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GL11.glDisable(GL11.GL_ALPHA_TEST);
		tessellator.startDrawingQuads();
		tessellator.addVertexWithUV(x + 150, y + 5 + 77, 0, 0.0, 1.0);
		tessellator.addVertexWithUV(x + 150 + 100, y + 5 + 77, 0, 1.0, 1.0);
		tessellator.addVertexWithUV(x + 150 + 100, y + 5, 0, 1.0, 0.0);
		tessellator.addVertexWithUV(x + 150, y + 5, 0, 0.0, 0.0);
		tessellator.draw();
		GL11.glEnable(GL11.GL_ALPHA_TEST);
		GL11.glDisable(GL11.GL_BLEND);
	}

	@Override
	protected void mouseClicked(int par1, int par2, int par3) {
		super.mouseClicked(par1, par2, par3);
		this.itemNameField.mouseClicked(par1, par2, par3);
		this.itemNameField2.mouseClicked(par1, par2, par3);
		par1 = par1 - guiLeft;
		par2 = par2 - guiTop;

		if (par3 == 0) {
			if (mode == 0) {
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
			} else if (mode == 1) {
				if (par1 < 128) {
					if (par2 > 6 && par2 < 17 && off2 < keysT.length)
						texture1 = keysT[off2];
					else if (par2 > 16 && par2 < 27 && off2 + 1 < keysT.length)
						texture1 = keysT[off2 + 1];
					else if (par2 > 26 && par2 < 37 && off2 + 2 < keysT.length)
						texture1 = keysT[off2 + 2];
					else if (par2 > 36 && par2 < 47 && off2 + 3 < keysT.length)
						texture1 = keysT[off2 + 3];
					else if (par2 > 46 && par2 < 57 && off2 + 4 < keysT.length)
						texture1 = keysT[off2 + 4];
					else if (par2 > 56 && par2 < 67 && off2 + 5 < keysT.length)
						texture1 = keysT[off2 + 5];
					else if (par2 > 66 && par2 < 77 && off2 + 6 < keysT.length)
						texture1 = keysT[off2 + 6];

				}
				if (par1 > 128) {
					if (par2 > 6 && par2 < 17 && off3 < keysT2.length)
						texture2 = keysT2[off3];
					else if (par2 > 16 && par2 < 27 && off3 + 1 < keysT2.length)
						texture2 = keysT2[off3 + 1];
					else if (par2 > 26 && par2 < 37 && off3 + 2 < keysT2.length)
						texture2 = keysT2[off3 + 2];
					else if (par2 > 36 && par2 < 47 && off3 + 3 < keysT2.length)
						texture2 = keysT2[off3 + 3];
					else if (par2 > 46 && par2 < 57 && off3 + 4 < keysT2.length)
						texture2 = keysT2[off3 + 4];
					else if (par2 > 56 && par2 < 67 && off3 + 5 < keysT2.length)
						texture2 = keysT2[off3 + 5];
					else if (par2 > 66 && par2 < 77 && off3 + 6 < keysT2.length)
						texture2 = keysT2[off3 + 6];

				}
			}
			else if (mode == 2)
			{
				if (par1 < 128) {
					if (par2 > 6 && par2 < 17 && offSide < keysSide.length)
						sideTexture = keysSide[offSide];
					else if (par2 > 16 && par2 < 27 && offSide + 1 < keysSide.length)
						sideTexture = keysSide[offSide + 1];
					else if (par2 > 26 && par2 < 37 && offSide + 2 < keysSide.length)
						sideTexture = keysSide[offSide + 2];
					else if (par2 > 36 && par2 < 47 && offSide + 3 < keysSide.length)
						sideTexture = keysSide[offSide + 3];
					else if (par2 > 46 && par2 < 57 && offSide + 4 < keysSide.length)
						sideTexture = keysSide[offSide + 4];
					else if (par2 > 56 && par2 < 67 && offSide + 5 < keysSide.length)
						sideTexture = keysSide[offSide + 5];
					else if (par2 > 66 && par2 < 77 && offSide + 6 < keysSide.length)
						sideTexture = keysSide[offSide + 6];

				}
			}
		}
	}

	@Override
	protected void keyTyped(char par1, int par2) {
		if (this.itemNameField.textboxKeyTyped(par1, par2)) {
			this.search(1);
		} else if (this.itemNameField2.textboxKeyTyped(par1, par2)) {
			this.search(2);
		} else {

			super.keyTyped(par1, par2);
		}
	}

	protected void actionPerformed(GuiButton button) {
		switch (button.id) {
		case 0: {
			DoorModuleWorkbenchPacket pack = new DoorModuleWorkbenchPacket(
					this.te.xCoord, this.te.yCoord, this.te.zCoord,
					this.priority, this.chosen, this.moduleType, this.texture1,
					this.texture2, this.sideTexture);
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
				off = (page - 1) * 7;
			}
			break;
		}
		case 10: {
			if (page < pages) {
				page++;
				off = (page - 1) * 7;
			}
			break;
		}
		case 11: {
			this.mode = 0;
			this.addModuleChooseElements();
			this.updateScreen();
			break;
		}
		case 12: {
			this.mode = 1;
			this.addTextureChooseElements();
			this.updateScreen();
			break;
		}
		case 13: {
			if (mode == 1) {
				if (pageT > 1) {
					pageT--;
					off2 = (pageT - 1) * 7;
				}
				
			}
			else if (mode == 2){
				if (pageSide > 1) {
					pageSide--;
					offSide = (pageSide - 1) * 7;
				}
				
			}
			break;
		}
		case 14: {
			if (mode == 1) {
				if (pageT < pagesT) {
					pageT++;
					off2 = (pageT - 1) * 7;
				}
			}
			else if (mode == 2)
			{
				if (pageSide < pagesT) {
					pageSide++;
					offSide = (pageSide - 1) * 7;
				}
			}
			break;
		}
		case 15: {
			if (pageT2 > 1) {
				pageT2--;
				off3 = (pageT2 - 1) * 7;
			}
			break;
		}
		case 16: {
			if (pageT2 < pagesT) {
				pageT2++;
				off3 = (pageT2 - 1) * 7;
			}
			break;
		}
		case 17: {
			this.mode = 2;
			this.addSideChooseElements();
			this.updateScreen();
			break;
		}
		}
	}

	public void onGuiClosed() {
		super.onGuiClosed();

		Keyboard.enableRepeatEvents(false);
	}

	private void search(int field) {
		if (mode == 0) {
			SortedSet<String> key = new TreeSet<String>();
			for (String s : keysOrigin) {
				if (s.contains(itemNameField.getText()))
					key.add(s);
			}
			keys = (String[]) key.toArray(new String[0]);
		} else if (mode == 1) {
			if (field == 1) {
				SortedSet<String> keyT2 = new TreeSet<String>();
				for (String s : keysTOrigin) {
					if (s.contains(itemNameField.getText()))
						keyT2.add(s);
				}
				keysT2 = (String[]) keyT2.toArray(new String[0]);
			} else if (field == 2) {
				SortedSet<String> keyT = new TreeSet<String>();
				for (String s : keysTOrigin) {
					if (s.contains(itemNameField2.getText()))
						keyT.add(s);
				}
				keysT = (String[]) keyT.toArray(new String[0]);
			}
		}
		else if (mode == 2)
		{
			SortedSet<String> keySide = new TreeSet<String>();
			for (String s : keysTOrigin) {
				if (s.contains(itemNameField2.getText()))
					keySide.add(s);
			}
			keysSide = (String[]) keySide.toArray(new String[0]);
		}

	}

}
