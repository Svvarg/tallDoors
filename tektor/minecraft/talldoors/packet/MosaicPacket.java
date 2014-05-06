package tektor.minecraft.talldoors.packet;

import tektor.minecraft.talldoors.TallDoorsBase;
import tektor.minecraft.talldoors.entities.tileentities.MosaicTileEntity;
import cpw.mods.fml.common.network.ByteBufUtils;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;

public class MosaicPacket extends AbstractPacket{
	
	int corX, corY, corZ;
	String chosen;
	
	public MosaicPacket(){
		
	}
	
	public MosaicPacket(int cor1, int cor2, int cor3, String pat)
	{
		this.corX = cor1;
		this.corY = cor2;
		this.corZ = cor3;
		this.chosen = pat;
	}

	@Override
	public void encodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {
		buffer.writeInt(corX);
		buffer.writeInt(corY);
		buffer.writeInt(corZ);
		ByteBufUtils.writeUTF8String(buffer, chosen);
		
	}

	@Override
	public void decodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {
		this.corX = buffer.readInt();
		this.corY = buffer.readInt();
		this.corZ = buffer.readInt();
		this.chosen = ByteBufUtils.readUTF8String(buffer);
		
	}

	@Override
	public void handleClientSide(EntityPlayer player) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handleServerSide(EntityPlayer player) {
		EntityPlayerMP play = (EntityPlayerMP) player;
		if (play.inventory.getCurrentItem().getItem().equals(TallDoorsBase.mosaicTool)) {
			if (play.worldObj.getTileEntity(corX, corY, corZ) instanceof MosaicTileEntity ) {
				MosaicTileEntity ent = (MosaicTileEntity) play.worldObj
						.getTileEntity(corX, corY, corZ);
				ent.setIcon(chosen);
			}
		}
		else
		{
			play.inventory.getCurrentItem().stackTagCompound = new NBTTagCompound();
			play.inventory.getCurrentItem().stackTagCompound.setString("chosen", chosen);
		}
		
	}

}
