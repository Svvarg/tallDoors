package tektor.minecraft.talldoors.doorworkshop.doorparts;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class PlainDoorPartEntity extends AbstractDoorPart{

	public int orientation;
	public String texture;
	public int pos;
	public float depth;
	
	public PlainDoorPartEntity(World par1World) {
		super(par1World);
		// TODO Auto-generated constructor stub
	}

	public PlainDoorPartEntity(World par1World, int posX, int heightPosition, int posZ,
			int heightSize, int orientation,String texture) {
		super(par1World);
		this.posX = posX;
		this.posY = heightPosition;
		this.posZ = posZ;
		this.height = heightSize;
		this.orientation = orientation;
	}

	@Override
	protected void entityInit() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound var1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound var1) {
		// TODO Auto-generated method stub
		
	}

}
