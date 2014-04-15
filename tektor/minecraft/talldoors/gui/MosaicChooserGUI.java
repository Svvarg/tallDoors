package tektor.minecraft.talldoors.gui;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import cpw.mods.fml.common.network.PacketDispatcher;

import tektor.minecraft.talldoors.container.DrawbridgeWorkbenchContainer;
import tektor.minecraft.talldoors.container.MosaicGuiContainer;
import tektor.minecraft.talldoors.entities.tileentities.DrawbridgeWorkbenchTileEntity;
import tektor.minecraft.talldoors.entities.tileentities.MosaicTileEntity;
import tektor.minecraft.talldoors.services.MosaicIconRegistry;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

public class MosaicChooserGUI extends GuiContainer {
	String[] keys;
	String[] keysOrigin;
	int off;
	int page;
	int pages;
	String chosen;
	public GuiTextField itemNameField;

	private MosaicTileEntity te;
	private EntityPlayer play;

	public MosaicChooserGUI(EntityPlayer player,
			InventoryPlayer inventoryPlayer, MosaicTileEntity e) {
		super(new MosaicGuiContainer(inventoryPlayer, e));
		te = e;
		play = player;
		off = 0;
		chosen = e.icon;
		xSize = 256;
		SortedSet key = new TreeSet();
		key.addAll(MosaicIconRegistry.icons.keySet());
		keys = keysOrigin = (String[]) key.toArray(new String[0]);
		page = 1;
		pages = (int) Math.ceil(keys.length / 10f);
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int param1, int param2) {
		// draw text and stuff here
		// the parameters for drawString are: string, x, y, color
		fontRendererObj.drawString("Mosaics", 8, 6, 9919952);
		fontRendererObj.drawString("Textures:", 8, 17, 9919952);
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
				"textures/gui/mosaicGUI.png"));
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
				"textures/blocks/mosaic/" + chosen + ".png"));
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

	@Override
	protected void mouseClicked(int par1, int par2, int par3) {
		super.mouseClicked(par1, par2, par3);
		this.itemNameField.mouseClicked(par1, par2, par3);
		par1 = par1 - guiLeft;
		par2 = par2 - guiTop;

		if (par3 == 0) {
			if (par1 > 7 && par1 < 147) {
				if(par1 >39 && par1 <54 && par2 >136 && par2<153&&page > 1)
				{
					page--;
					off = (page - 1) * 10;
				}
				else if(par1 >58 && par1 <73 && par2 >136 && par2<153&&page < pages)
				{
					page++;
					off = (page - 1) * 10;
				}
				else if (par2 > 26 && par2 < 37 && off < keys.length)
					chosen = keys[off];
				else if (par2 > 36 && par2 < 47 && off+1 < keys.length)
					chosen = keys[off + 1];
				else if (par2 > 46 && par2 < 57 && off+2 < keys.length)
					chosen = keys[off + 2];
				else if (par2 > 56 && par2 < 67 && off+3 < keys.length)
					chosen = keys[off + 3];
				else if (par2 > 66 && par2 < 77 && off+4 < keys.length)
					chosen = keys[off + 4];
				else if (par2 > 76 && par2 < 87 && off+5 < keys.length)
					chosen = keys[off + 5];
				else if (par2 > 86 && par2 < 97 && off+6 < keys.length)
					chosen = keys[off + 6];
				else if (par2 > 96 && par2 < 107 && off+7 < keys.length)
					chosen = keys[off + 7];
				else if (par2 > 106 && par2 < 117 && off+8 < keys.length)
					chosen = keys[off + 8];
				else if (par2 > 116 && par2 < 127 && off+9 < keys.length)
					chosen = keys[off + 9];
				
			}

		}
	}

	public void onGuiClosed() {
		super.onGuiClosed();
		ByteArrayOutputStream bos = new ByteArrayOutputStream(8);
		DataOutputStream outputStream = new DataOutputStream(bos);
		try {
			outputStream.writeUTF(this.chosen);
			outputStream.writeInt(te.xCoord);
			outputStream.writeInt(te.yCoord);
			outputStream.writeInt(te.zCoord);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		Packet250CustomPayload packet = new Packet250CustomPayload();
		packet.channel = "TallDoors_Mosaic";
		packet.data = bos.toByteArray();
		packet.length = bos.size();
		if (!te.worldObj.isRemote) {
		} else if (te.worldObj.isRemote) {
			EntityClientPlayerMP player2 = (EntityClientPlayerMP) this.play;
			PacketDispatcher.sendPacketToServer(packet);
		}
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
	}
	
	@Override
	public void drawScreen(int par1, int par2, float par3) {
		super.drawScreen(par1, par2, par3);
		GL11.glDisable(GL11.GL_LIGHTING);
		this.itemNameField.drawTextBox();
	}
}
