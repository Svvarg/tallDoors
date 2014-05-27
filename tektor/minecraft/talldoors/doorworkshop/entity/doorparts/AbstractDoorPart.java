package tektor.minecraft.talldoors.doorworkshop.entity.doorparts;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import tektor.minecraft.talldoors.doorworkshop.entity.DoorBase;
import tektor.minecraft.talldoors.doorworkshop.entity.DoorBaseConstructable;

public abstract class AbstractDoorPart extends Entity{

	public int orientation; //28
	public int pos; //25
	public float depth; //26
	public double height2; //27
	public boolean left; //29
	public double mX,mY,mZ;
	public String texture1; //24
	
	public DoorBaseConstructable master;
	public String sideTexture;//30
	
	public AbstractDoorPart(World par1World) {
		super(par1World);
		this.ignoreFrustumCheck = true;
		this.preventEntitySpawning = true;
		this.noClip = true;
		setBoundsAt(posX, posY, posZ);
		this.sideTexture = "plain";
	}
	
	public AbstractDoorPart(World par1World, double posX, int heightPosition, double posZ,
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
		
		mX = 0;
		mY = 0;
		mZ = 0;
		
		this.left = true;
		if(left)
		{
			this.dataWatcher.updateObject(29, 1);
		}
		else
		{
			this.dataWatcher.updateObject(29, 0);
		}
		this.ignoreFrustumCheck = true;
		this.preventEntitySpawning = true;
		this.noClip = true;
		this.sideTexture = "plain";
		setBoundsAt(posX, posY, posZ);
	}
	
	public void setTexture(int which, String txt)
	{
		if(txt == null) texture1 = "plain";
		else texture1 = txt;
		this.dataWatcher.updateObject(24, texture1);
	}
	
	public void onUpdate() {
		if (this.worldObj.isRemote) {
			texture1 = this.dataWatcher.getWatchableObjectString(24);
			orientation = this.dataWatcher.getWatchableObjectInt(28);
			pos = this.dataWatcher.getWatchableObjectInt(25);
			left = this.dataWatcher.getWatchableObjectInt(29) == 1;
			depth = this.dataWatcher.getWatchableObjectFloat(26);
			height2 = Double.parseDouble(this.dataWatcher
					.getWatchableObjectString(27));
			sideTexture = this.dataWatcher.getWatchableObjectString(30);
			
		}
		if(this.master == null)
		{
			List<DoorBaseConstructable> list = (List<DoorBaseConstructable>) worldObj
					.getEntitiesWithinAABB(DoorBaseConstructable.class, boundingBox
							.getBoundingBox(mX - 0.2,
									mY - 0.2,
									mZ - 0.2,
									mX + 0.2,
									mY + 0.2,
									mZ + 0.2));
			master = list.isEmpty() ? null : list.get(0);
			if(master != null)
			{
				master.addPart(this);
			}
		}
		if(!this.worldObj.isRemote)
		{
			if(this.texture1 == null || this.texture1.equals(""))
			{
				this.texture1 = "plain";
				this.dataWatcher.updateObject(24, texture1);
			}
			if(this.sideTexture == null || this.sideTexture.equals(""))
			{
				this.sideTexture = "plain";
				this.dataWatcher.updateObject(30, sideTexture);
			}
		}
		setBoundsAt(posX, posY, posZ);
	}
	


	@Override
	public AxisAlignedBB getBoundingBox() {
		return this.boundingBox;
	}

	@Override
	public AxisAlignedBB getCollisionBox(Entity par1Entity) {
		return this.boundingBox;
	}

	@Override
	public boolean canBeCollidedWith() {
		return true;
	}
	
	@Override
	protected void entityInit() {
		this.dataWatcher.addObject(28, 0);

		this.dataWatcher.addObject(25, 0);
		this.dataWatcher.addObject(26, 0f);
		this.dataWatcher.addObject(27, ""+0);
		this.dataWatcher.addObject(29, 0);
		this.dataWatcher.addObject(30, "");
		this.dataWatcher.addObject(24, "plain");
	}
	
	@Override
	protected void readEntityFromNBT(NBTTagCompound nbt) {
		depth = nbt.getFloat("depth");
		height2 = nbt.getDouble("height2");
		pos = nbt.getInteger("pos");
		orientation = nbt.getInteger("orientation");
		texture1 = nbt.getString("texture1");
		sideTexture = nbt.getString("sideTexture");
		this.dataWatcher.updateObject(28, this.orientation);
		this.dataWatcher.updateObject(25, pos);
		this.dataWatcher.updateObject(26, this.depth);
		this.dataWatcher.updateObject(27, "" + this.height2);
		this.dataWatcher.updateObject(24, texture1);
		this.dataWatcher.updateObject(30, sideTexture);
		
		@SuppressWarnings({ "static-access", "unchecked" })
		List<DoorBaseConstructable> list = (List<DoorBaseConstructable>) worldObj
				.getEntitiesWithinAABB(DoorBaseConstructable.class, boundingBox
						.getBoundingBox(nbt.getDouble("mX") - 0.2,
								nbt.getDouble("mY") - 0.2,
								nbt.getDouble("mZ") - 0.2,
								nbt.getDouble("mx") + 0.2,
								nbt.getDouble("mY") + 0.2,
								nbt.getDouble("mZ") + 0.2));
		master = list.isEmpty() ? null : list.get(0);
		if(master != null)
		{
			master.addPart(this);
		}
		
		this.mX = nbt.getDouble("mX");
		this.mY = nbt.getDouble("mY");
		this.mZ = nbt.getDouble("mZ");
		
		left = nbt.getBoolean("left");

		if(left)
		{
			this.dataWatcher.updateObject(29, 1);
		}
		else
		{
			this.dataWatcher.updateObject(29, 0);
		}
	}
	
	@Override
	protected void writeEntityToNBT(NBTTagCompound nbt) {
		nbt.setInteger("orientation", orientation);
		nbt.setInteger("pos", pos); 
		nbt.setFloat("depth",depth);
		nbt.setDouble("height2",height2);
		nbt.setBoolean("left", left);
		nbt.setString("texture1", texture1);
		nbt.setString("sideTexture", sideTexture);
		if (master != null) {
			nbt.setDouble("mX", master.getPosX());
			nbt.setDouble("mY", master.getPosY());
			nbt.setDouble("mZ", master.getPosZ());
		}
	}
	
	@Override
	public void setPosition(double par1, double par3, double par5) {
		this.posX = par1;
		this.posY = par3;
		this.posZ = par5;
		setBoundsAt(par1, par3, par5);

	}
	
	public int func_82329_d() {
		return 32;
	}

	public int func_82330_g() {
		return 2;
	}
	
	public void setBoundsAt(double x, double y, double z) {
		float f = this.width;
		double f1 = this.height2;
		double f2 = this.depth;
		if (orientation == 0) {
			if (!left) {
				if (pos == 0) {
					this.boundingBox.setBounds(x+0.2D, y+0.1D, z, x+1-0.2D, y+height2-0.1D, z+depth);
				} else {
					
					this.boundingBox.setBounds(x+1-depth, y+0.1D, z+0.2D, x+1, y+height2-0.1D, z+0.8D);
					
				}
			} else {
				if (pos == 0) {
					this.boundingBox.setBounds(x+0.2D, y+0.1D, z, x+0.8D, y+height2-0.1D, z+depth);
				} else {
					this.boundingBox.setBounds(x+1, y+0.1D, z+0.2D, x+depth+1, y+height2-0.1D, z+0.8D);
				}
			}
		} else if (orientation == 1) {
			if (!left) {
				if (pos == 1) {
					this.boundingBox.setBounds(x+0.2D,y+0.1D,z-depth+1,x+0.8D,y+height2-0.1D,z+1);
				} else {
					this.boundingBox.setBounds(x-depth+1,y+0.1D,z+0.2D,x+1,y+height2-0.1D,z+0.8D);
				}
			} else {
				if (pos == 1) {
					this.boundingBox.setBounds(x+0.2D,y+0.1D,z-depth+1,x+0.8D,y+height2-0.1D,z+1);
					
				} else {
					this.boundingBox.setBounds(x-depth+1,y+0.1D,z+0.2D,x+1,y+height2-0.1D,z+0.8D);
				}
			}
		} else if (orientation == 2) {
			if (!left) {
				if (pos == 0) {
					this.boundingBox.setBounds(x+0.2D, y+0.1D, z+1, x+0.8D, y+height2-0.1D, z+depth+1);
					
				} else {
					this.boundingBox.setBounds(x, y+0.1D, z+0.2D, x+depth, y+height2-0.1D, z+0.8D);
				}
			} else {
				if (pos == 0) {
					this.boundingBox.setBounds(x+0.2D, y+0.1D, z+1, x+1-0.2D, y+height2-0.1D, z+depth+1);
				} else {
					this.boundingBox.setBounds(x, y+0.1D, z+0.2D, x+depth, y+height2-0.1D, z+0.8D);
				}
			}

		} else if (orientation == 3) {
			if (!left) {
				if (pos == 1) {
					this.boundingBox.setBounds(x+0.2D,y+0.1D,z,x+0.8D,y+height2-0.1D,z+depth);
					
				} else {
					this.boundingBox.setBounds(x,y+0.1D,z+0.2D,x+depth,y+height2-0.1D,z+0.8D);
					
				}
			} else {
				if (pos == 1) {
					this.boundingBox.setBounds(x+0.2D,y+0.1D,z,x+0.8D,y+height2-0.1D,z+depth);
				} else {
					this.boundingBox.setBounds(x,y+0.1D,z+0.2D,x+depth,y+height2-0.1D,z+0.8D);
					
				}
			}
		}
	}
	


	@Override
	public boolean interactFirst(EntityPlayer player) {

		if (!this.worldObj.isRemote) {
			if (master != null)
				master.doWork(player);
			else this.setDead();
			return true;
		}
		return false;

	}
	


	@Override
	public boolean attackEntityFrom(DamageSource par1DamageSource, float par2) {
		return false;
	}
	
	public void setOrientation(boolean b, int var24) {
		left = b;
		orientation = var24;
		this.dataWatcher.updateObject(28, this.orientation);
		this.dataWatcher.updateObject(25, pos);
		if (left)
		{
			this.dataWatcher.updateObject(29, 1);
		}
		else
		{
			this.dataWatcher.updateObject(29, 0);
		}
	}
	
	@Override
	public void onCollideWithPlayer(EntityPlayer par1EntityPlayer) {

	}

	@Override
	public void setPositionAndRotation2(double par1, double par3, double par5,
			float par7, float par8, int par9) {
		this.setPosition(par1, par3, par5);
		this.setRotation(par7, par8);
	}
	
	public void setPos(int pos)
	{
		this.pos = pos;
		this.dataWatcher.updateObject(25,pos);
	}

	public void setSideTexture(String sideTexture) {
		this.sideTexture = sideTexture;
		this.dataWatcher.updateObject(30, sideTexture);
	}

}
