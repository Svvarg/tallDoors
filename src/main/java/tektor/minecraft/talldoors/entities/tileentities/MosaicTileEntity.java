package tektor.minecraft.talldoors.entities.tileentities;

import tektor.minecraft.talldoors.services.MosaicIconRegistry;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;

public class MosaicTileEntity extends TileEntity {

	
	public String icon = "standard";

	@Override
	public void writeToNBT(NBTTagCompound par1) {
		super.writeToNBT(par1);
		par1.setString("icon", icon);

	}

	@Override
	public void readFromNBT(NBTTagCompound par1) {
		super.readFromNBT(par1);
		icon = par1.getString("icon");
	}

	@Override
	public Packet getDescriptionPacket() {
		NBTTagCompound tag = new NBTTagCompound();
		this.writeToNBT(tag);
		return new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 0, tag);
	}

	@Override
	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
		NBTTagCompound tag = pkt.func_148857_g();
		this.readFromNBT(tag);
		worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);

	}

	public void changeIcon() {
		if (!worldObj.isRemote) {
			this.icon = MosaicIconRegistry.getRandom();
			worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
		}
		
	}

	public void setIcon(String chosen) {
		if (!worldObj.isRemote) {
			this.icon = chosen;
			worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
		}
		
	}
}
