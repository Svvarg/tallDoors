package tektor.minecraft.talldoors.entities.drawbridge;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class EntityConnector extends Entity{

	public EntityConnector(World par1World) {
		super(par1World);
		this.ignoreFrustumCheck = true;
	}

	@Override
	protected void entityInit() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound nbttagcompound) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound nbttagcompound) {
		// TODO Auto-generated method stub
		
	}

	public void setEnd(int par4, int par5, int par6) {
	
		
	}

}
