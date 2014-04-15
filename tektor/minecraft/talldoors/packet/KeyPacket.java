package tektor.minecraft.talldoors.packet;

import java.util.List;

import tektor.minecraft.talldoors.entities.workbenches.KeyMaker;
import cpw.mods.fml.common.network.ByteBufUtils;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.AxisAlignedBB;

public class KeyPacket extends AbstractPacket{

	int x,y,z;
	boolean b;
	String chosen;
	
	public KeyPacket()
	{
		
	}
	
	public KeyPacket(int var1, int var2, int var3, boolean bool, String key)
	{
		this.x = var1;
		this.y = var2;
		this.z = var3;
		this.b = bool;
		this.chosen = key;
	}
	
	@Override
	public void encodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {
		buffer.writeInt(x);
		buffer.writeInt(y);
		buffer.writeInt(z);
		buffer.writeBoolean(b);
		ByteBufUtils.writeUTF8String(buffer, chosen);
		
	}

	@Override
	public void decodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {
		x = buffer.readInt();
		y = buffer.readInt();
		z = buffer.readInt();
		b = buffer.readBoolean();
		chosen = ByteBufUtils.readUTF8String(buffer);
	}

	@Override
	public void handleClientSide(EntityPlayer player) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handleServerSide(EntityPlayer player) {
		EntityPlayerMP play = (EntityPlayerMP) player;
		List<Entity> list = play.worldObj.getEntitiesWithinAABB(Entity.class,
				AxisAlignedBB.getBoundingBox(x, y, z, x + 1, y + 1, z + 1));
		if (!list.isEmpty()) {
			for (Entity ent : list) {
				if (ent instanceof KeyMaker) {
					((KeyMaker) ent).pressKey(chosen,b);
				}
			}

		}
		
	}

}
