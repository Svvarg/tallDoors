package tektor.minecraft.talldoors.entities.tileentities;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet132TileEntityData;
import net.minecraft.tileentity.TileEntity;

public class KeyRedstoneLockTileEntity extends TileEntity {
	
	public String keycode = "-1";
	
	@Override
	public void writeToNBT(NBTTagCompound par1) {
		super.writeToNBT(par1);
		par1.setString("keycode", keycode);

	}

	@Override
	public void readFromNBT(NBTTagCompound par1) {
		super.readFromNBT(par1);
		keycode = par1.getString("keycode");
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

}
