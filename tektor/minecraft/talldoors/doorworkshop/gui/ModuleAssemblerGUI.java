package tektor.minecraft.talldoors.doorworkshop.gui;

import java.util.List;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import tektor.minecraft.talldoors.TallDoorsBase;
import tektor.minecraft.talldoors.doorworkshop.entity.ModuleAssemblerTileEntity;
import tektor.minecraft.talldoors.doorworkshop.network.DirectionPacket;
import tektor.minecraft.talldoors.doorworkshop.network.ModuleAssemblerPacket;

public class ModuleAssemblerGUI extends GuiContainer {

	private ModuleAssemblerTileEntity te;
	private GuiTextField itemNameField;
	private GuiTextField itemNameField2;
	private int x, y = 0;
	private int dSizeX, dSizeY;
	private int mode;
	private String doortype;

	public ModuleAssemblerGUI(EntityPlayer player,
			InventoryPlayer inventoryPlayer, ModuleAssemblerTileEntity e) {
		super(new ModuleAssemblerContainer(inventoryPlayer, e));
		te = e;
		xSize = 328;
		ySize = 220;
		dSizeX = 1;
		dSizeY = 1;
		this.doortype = "standard";
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float var1, int var2,
			int var3) {
		if (this.mode == 0) {
			this.mc.renderEngine.bindTexture(new ResourceLocation("talldoors",
					"textures/gui/moduleAssemblerGUI.png"));
		} else if (this.mode == 1) {
			this.mc.renderEngine.bindTexture(new ResourceLocation("talldoors",
					"textures/gui/moduleAssemblerOptionsGUI.png"));
		}
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		int x = (width - xSize) / 2;
		int y = (height - ySize) / 2;
		this.drawTexturedModalRect(x, y, 0, 0, 256, 189);
		this.drawTexturedModalRect(x - 36, y + 3, 0, 189, 36, 67);
		this.drawTexturedModalRect(x + 256, y + 3, 72, 189, 36, 67);
		this.drawTexturedModalRect(x - 36, y + 70, 36, 189, 36, 67);
		this.drawTexturedModalRect(x + 256, y + 70, 108, 189, 36, 49);
		this.drawTexturedModalRect(x - 56, y + 120, 144, 189, 56, 67);
		this.drawTexturedModalRect(x + 256, y + 120, 200, 189, 56, 67);
		if(mode == 1){
			if(this.doortype.equals("standard"))
			{
				this.drawTexturedModalRect(x+151, y+13, 109, 239, 12, 12);
				this.drawTexturedModalRect(x+151, y+33, 123, 239, 12, 12);
			}
			else if(this.doortype.equals("sliding"))
			{
				this.drawTexturedModalRect(x+151, y+13, 123, 239, 12, 12);
				this.drawTexturedModalRect(x+151, y+33, 109, 239, 12, 12);
			}
		}

	}

	@Override
	protected void drawGuiContainerForegroundLayer(int param1, int param2) {
		if (mode == 0) {
			fontRendererObj.drawString("Width: " + this.dSizeX, -51, 123, -1);
			fontRendererObj.drawString("Height: " + this.dSizeY, -51, 152, -1);
			int negX = (int) (Math.ceil(dSizeX / 2D)) - 1;
			int posX = (int) (Math.floor(dSizeX / 2D));
			int negY = (int) (Math.ceil(dSizeY / 2D) - 1);
			int posY = (int) (Math.floor(dSizeY / 2D));
			if (posX < (x + 7) && posX > (x - 8)) {
				this.drawVerticalLine(137 + (posX - x) * 18, 4, 150, -20000000);
			}
			if (-negX > (x - 7) && -negX < (x + 8)) {
				this.drawVerticalLine(118 - (negX + x) * 18, 4, 150, -20000000);
			}
			if (posY < (y + 5) && posY > (y - 5)) {
				this.drawHorizontalLine(11, 244, 77 + (posY - y) * 18,
						-20000000);
			}
			if (-negY > (y - 4) && -negY < (y + 6)) {
				this.drawHorizontalLine(11, 244, 58 - (negY + y) * 18,
						-20000000);
			}
		}
		else if(mode == 1){
			if(this.doortype.equals("standard"))
			{
				
			}
			else if(this.doortype.equals("sliding"))
			{
				
			}
		}
	}

	@Override
	public void initGui() {
		super.initGui();

		int i = (this.width - this.xSize) / 2;
		int j = (this.height - this.ySize) / 2;

		this.itemNameField = new GuiTextField(this.fontRendererObj, i + 300,
				j + 20, 40, 15);
		this.itemNameField.setTextColor(-1);
		this.itemNameField.setDisabledTextColour(-1);
		this.itemNameField.setEnableBackgroundDrawing(true);
		this.itemNameField.setMaxStringLength(3);
		this.itemNameField.setText("" + y);

		this.itemNameField2 = new GuiTextField(this.fontRendererObj, i + 300,
				j + 40, 40, 15);
		this.itemNameField2.setTextColor(-1);
		this.itemNameField2.setDisabledTextColour(-1);
		this.itemNameField2.setEnableBackgroundDrawing(true);
		this.itemNameField2.setMaxStringLength(3);
		this.itemNameField2.setText("" + x);

		this.buttonList.clear();
		this.buttonList.add(new GuiButton(0, i + 260, j + 125, 20, 20, "L"));
		this.buttonList.add(new GuiButton(10, i + 288, j + 125, 20, 20, "R"));
		this.buttonList
				.add(new GuiButton(1, i + 300, j + 70, 20, 20, "\u2190"));
		this.buttonList
				.add(new GuiButton(2, i + 320, j + 60, 20, 20, "\u2191"));
		this.buttonList
				.add(new GuiButton(3, i + 320, j + 80, 20, 20, "\u2193"));
		this.buttonList
				.add(new GuiButton(4, i + 340, j + 70, 20, 20, "\u2192"));
		this.buttonList
				.add(new GuiButton(5, i + 345, j + 28, 40, 20, "Center"));
		this.buttonList.add(new GuiButton(6, i - 53, j + 131, 20, 20, "-"));
		this.buttonList.add(new GuiButton(7, i - 23, j + 131, 20, 20, "+"));
		this.buttonList.add(new GuiButton(8, i - 53, j + 160, 20, 20, "-"));
		this.buttonList.add(new GuiButton(9, i - 23, j + 160, 20, 20, "+"));
		this.buttonList.add(new GuiButton(11, i, j - 20, 50, 20, "Assembly"));
		this.buttonList
				.add(new GuiButton(12, i + 50, j - 20, 50, 20, "Options"));

		DirectionPacket pack = new DirectionPacket(this.te.xCoord,
				this.te.yCoord, this.te.zCoord, this.x, this.y);
		if (!te.getWorldObj().isRemote) {
		} else if (te.getWorldObj().isRemote) {
			TallDoorsBase.packetPipeline.sendToServer(pack);
		}

	}

	private void addAssemblyObjects() {
		int i = (this.width - this.xSize) / 2;
		int j = (this.height - this.ySize) / 2;
		this.itemNameField.setVisible(true);
		this.itemNameField2.setVisible(true);

		this.buttonList.clear();
		this.buttonList.add(new GuiButton(0, i + 260, j + 125, 20, 20, "L"));
		this.buttonList.add(new GuiButton(10, i + 288, j + 125, 20, 20, "R"));
		this.buttonList
				.add(new GuiButton(1, i + 300, j + 70, 20, 20, "\u2190"));
		this.buttonList
				.add(new GuiButton(2, i + 320, j + 60, 20, 20, "\u2191"));
		this.buttonList
				.add(new GuiButton(3, i + 320, j + 80, 20, 20, "\u2193"));
		this.buttonList
				.add(new GuiButton(4, i + 340, j + 70, 20, 20, "\u2192"));
		this.buttonList
				.add(new GuiButton(5, i + 345, j + 28, 40, 20, "Center"));
		this.buttonList.add(new GuiButton(6, i - 53, j + 131, 20, 20, "-"));
		this.buttonList.add(new GuiButton(7, i - 23, j + 131, 20, 20, "+"));
		this.buttonList.add(new GuiButton(8, i - 53, j + 160, 20, 20, "-"));
		this.buttonList.add(new GuiButton(9, i - 23, j + 160, 20, 20, "+"));
		this.buttonList.add(new GuiButton(11, i, j - 20, 50, 20, "Assembly"));
		this.buttonList
				.add(new GuiButton(12, i + 50, j - 20, 50, 20, "Options"));
		
		for(int c = 37;c < this.te.container.inventorySlots.size(); c++)
		{
			Slot slot = ((List<Slot>)this.te.container.inventorySlots).get(c);
			slot.yDisplayPosition = slot.yDisplayPosition + 500;
		}
	}

	private void addOptionObjects()
	{
		int i = (this.width - this.xSize) / 2;
		int j = (this.height - this.ySize) / 2;
		this.itemNameField.setVisible(false);
		this.itemNameField2.setVisible(false);
		
		this.buttonList.clear();
		this.buttonList.add(new GuiButton(0, i + 260, j + 125, 20, 20, "L"));
		this.buttonList.add(new GuiButton(10,i+288, j+125,20,20,"R"));
		this.buttonList.add(new GuiButton(11,i,j-20,50,20, "Assembly"));
		this.buttonList.add(new GuiButton(12,i+50,j-20,50,20, "Options"));
		this.buttonList.add(new GuiButton(13,i+165,j+10,80,20,"Standard Door"));
		this.buttonList.add(new GuiButton(14,i+165,j+30,80,20,"Sliding Door"));
		
		
		for(int c = 37;c < this.te.container.inventorySlots.size(); c++)
		{
			Slot slot = ((List<Slot>)this.te.container.inventorySlots).get(c);
			
			slot.yDisplayPosition = slot.yDisplayPosition - 500;
		}
		
	}

	@Override
	public void drawScreen(int par1, int par2, float par3) {
		super.drawScreen(par1, par2, par3);
		GL11.glDisable(GL11.GL_LIGHTING);
		this.itemNameField.drawTextBox();
		this.itemNameField2.drawTextBox();
	}

	protected void actionPerformed(GuiButton button) {
		switch (button.id) {
		case 0: {

			if (!te.getWorldObj().isRemote) {
			} else if (te.getWorldObj().isRemote) {

				ModuleAssemblerPacket pack = new ModuleAssemblerPacket(
						this.te.xCoord, this.te.yCoord, this.te.zCoord,
						this.dSizeX, this.dSizeY, true,this.doortype);
				TallDoorsBase.packetPipeline.sendToServer(pack);
			}
			break;
		}
		case 1: {
			x--;
			DirectionPacket pack = new DirectionPacket(this.te.xCoord,
					this.te.yCoord, this.te.zCoord, this.x, this.y);
			if (!te.getWorldObj().isRemote) {
			} else if (te.getWorldObj().isRemote) {
				TallDoorsBase.packetPipeline.sendToServer(pack);
			}
			this.itemNameField2.setText("" + x);
			break;
		}
		case 2: {
			y--;
			DirectionPacket pack = new DirectionPacket(this.te.xCoord,
					this.te.yCoord, this.te.zCoord, this.x, this.y);
			if (!te.getWorldObj().isRemote) {
			} else if (te.getWorldObj().isRemote) {
				TallDoorsBase.packetPipeline.sendToServer(pack);
			}
			this.itemNameField.setText("" + y);
			break;
		}
		case 3: {
			y++;
			DirectionPacket pack = new DirectionPacket(this.te.xCoord,
					this.te.yCoord, this.te.zCoord, this.x, this.y);
			if (!te.getWorldObj().isRemote) {
			} else if (te.getWorldObj().isRemote) {
				TallDoorsBase.packetPipeline.sendToServer(pack);
			}
			this.itemNameField.setText("" + y);
			break;
		}
		case 4: {
			x++;
			DirectionPacket pack = new DirectionPacket(this.te.xCoord,
					this.te.yCoord, this.te.zCoord, this.x, this.y);
			if (!te.getWorldObj().isRemote) {
			} else if (te.getWorldObj().isRemote) {
				TallDoorsBase.packetPipeline.sendToServer(pack);
			}
			this.itemNameField2.setText("" + x);
			break;
		}
		case 5: {
			x = 0;
			y = 0;
			DirectionPacket pack = new DirectionPacket(this.te.xCoord,
					this.te.yCoord, this.te.zCoord, this.x, this.y);
			if (!te.getWorldObj().isRemote) {
			} else if (te.getWorldObj().isRemote) {
				TallDoorsBase.packetPipeline.sendToServer(pack);
			}
			this.itemNameField.setText("" + y);
			this.itemNameField2.setText("" + x);
			break;
		}
		case 6: {
			if (this.dSizeX > 1) {
				this.dSizeX--;
			}
			break;
		}
		case 7: {
			this.dSizeX++;
			break;
		}
		case 8: {
			if (this.dSizeY > 1) {
				this.dSizeY--;
			}
			break;
		}
		case 9: {
			this.dSizeY++;
			break;
		}
		case 10: {
			if (!te.getWorldObj().isRemote) {
			} else if (te.getWorldObj().isRemote) {

				ModuleAssemblerPacket pack = new ModuleAssemblerPacket(
						this.te.xCoord, this.te.yCoord, this.te.zCoord,
						this.dSizeX, this.dSizeY, false, doortype);
				TallDoorsBase.packetPipeline.sendToServer(pack);
			}
			break;
		}
		case 11: {
			if (mode != 0) {
				this.mode = 0;
				this.addAssemblyObjects();
				this.updateScreen();
			}
			break;
		}
		case 12: {
			if (mode != 1) {
				this.mode = 1;
				this.addOptionObjects();
				this.updateScreen();
			}
			break;
		}
		case 13: {
			this.doortype = "standard";
			break;
		}
		case 14: {
			this.doortype = "sliding";
			break;
		}
		}
	}

}
