package tektor.minecraft.talldoors.entities.tileentities;

import tektor.minecraft.talldoors.services.MosaicIconRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet132TileEntityData;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;

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
		return new Packet132TileEntityData(xCoord, yCoord, zCoord, 0, tag);
	}

	@Override
	public void onDataPacket(INetworkManager net, Packet132TileEntityData pkt) {
		NBTTagCompound tag = pkt.data;
		this.readFromNBT(tag);
		worldObj.markBlockForRenderUpdate(xCoord, yCoord, zCoord);

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
