package tektor.minecraft.talldoors.doorworkshop.doorparts;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public abstract class AbstractDoorPart extends Entity{

	public int orientation; //28
	public int pos; //25
	public float depth; //26
	public double height2; //27
	
	public AbstractDoorPart(World par1World) {
		super(par1World);
		this.ignoreFrustumCheck = true;
	}
	
	public AbstractDoorPart(World par1World, int posX, int heightPosition, int posZ,
			int heightSize, int orientation, float depth) {
		super(par1World);
		this.posX = posX;
		this.posY = heightPosition;
		this.posZ = posZ;
		this.height2 = heightSize;
		this.orientation = orientation;
		this.pos = 0;
		this.depth = depth;
		
		this.dataWatcher.updateObject(28, this.orientation);
		this.dataWatcher.updateObject(25, pos);
		this.dataWatcher.updateObject(26, this.depth);
		this.dataWatcher.updateObject(27, "" + this.height2);

		this.ignoreFrustumCheck = true;
	}
	
	public void onUpdate() {
		if (this.worldObj.isRemote) {
			orientation = this.dataWatcher.getWatchableObjectInt(28);
			pos = this.dataWatcher.getWatchableObjectInt(25);
			depth = this.dataWatcher.getWatchableObjectFloat(26);
			height2 = Double.parseDouble(this.dataWatcher
					.getWatchableObjectString(27));
		}
	}
	
	@Override
	protected void entityInit() {
		this.dataWatcher.addObject(28, 0);

		this.dataWatcher.addObject(25, 0);
		this.dataWatcher.addObject(26, 0f);
		this.dataWatcher.addObject(27, ""+0);
	}
	
	@Override
	protected void readEntityFromNBT(NBTTagCompound nbt) {
		depth = nbt.getFloat("depth");
		height2 = nbt.getDouble("height2");
		pos = nbt.getInteger("pos");
		orientation = nbt.getInteger("orientation");
		
		this.dataWatcher.updateObject(28, this.orientation);
		this.dataWatcher.updateObject(25, pos);
		this.dataWatcher.updateObject(26, this.depth);
		this.dataWatcher.updateObject(27, "" + this.height2);
		
	}
	
	@Override
	protected void writeEntityToNBT(NBTTagCompound nbt) {
		nbt.setInteger("orientation", orientation);
		nbt.setInteger("pos", pos); 
		nbt.setFloat("depth",depth);
		nbt.setDouble("height2",height2);
		
	}

}
