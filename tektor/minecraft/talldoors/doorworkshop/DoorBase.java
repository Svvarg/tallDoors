package tektor.minecraft.talldoors.doorworkshop;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import tektor.minecraft.talldoors.doorworkshop.doorparts.AbstractDoorPart;
import tektor.minecraft.talldoors.doorworkshop.doorparttypes.AbstractDoorPartType;
import tektor.minecraft.talldoors.entities.AbstractLockable;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class DoorBase extends AbstractLockable {

	String[][] constructionPlan;
	
	public int orientation; //28
	public int pos; //25
	public float depth; //26
	
	List<AbstractDoorPart> parts;
	public double height2; //27
	public double width2; //24
	
	public DoorBase(World par1World) {
		super(par1World);
		constructionPlan = new String[1][1];
		parts = new ArrayList<AbstractDoorPart>(1);

		this.ignoreFrustumCheck = true;
	}
	//TODO Communication of values
	
	
	public void setConstructionPlan(String[][] plan)
	{
		this.constructionPlan = plan;
		this.parts = new ArrayList<AbstractDoorPart>(plan[0].length * plan.length);
		this.width2 = constructionPlan.length;
		this.height2 = constructionPlan[0].length;
		this.depth = 0.25f;
		
		
		this.dataWatcher.updateObject(26, this.depth);
		this.dataWatcher.updateObject(27, "" + this.height2);
		this.dataWatcher.updateObject(24, "" + this.width2);
		
	}
	
	// logic: each array in this array represents a column

	@Override
	protected void entityInit() {
		this.dataWatcher.addObject(28, 0);

		this.dataWatcher.addObject(25, 0);
		this.dataWatcher.addObject(26, 0f);
		this.dataWatcher.addObject(27, ""+0);
		this.dataWatcher.addObject(24, ""+0);
	}
	
	public void onUpdate() {
		if (this.worldObj.isRemote) {
			orientation = this.dataWatcher.getWatchableObjectInt(28);
			pos = this.dataWatcher.getWatchableObjectInt(25);
			depth = this.dataWatcher.getWatchableObjectFloat(26);
			height2 = Double.parseDouble(this.dataWatcher
					.getWatchableObjectString(27));
			width2 = Double.parseDouble(this.dataWatcher.getWatchableObjectString(24));
		}
	}

	public void constructFromPlan() {
		for (int columns = 0; columns < constructionPlan.length; columns++) {
			int heightPosition = (int) this.posY;
			int sizer = 1;

			for (int blocks = 0; blocks < constructionPlan[columns].length; blocks++) {
				if (blocks < (constructionPlan[columns].length - 1)
						&& constructionPlan[columns][blocks]
								.equals(constructionPlan[columns][blocks + 1])) {
					sizer++;
					continue;
				} 
						AbstractDoorPart part = null;
					switch (orientation) {
					case 0: {
						AbstractDoorPartType classH = DoorPartRegistry
								.getPartForIndex(constructionPlan[columns][blocks]);
						part = classH.getNewEntity(this.worldObj,
								(int) this.posX - columns,
								(int) heightPosition, (int) this.posZ, sizer,
								orientation);
						this.parts.add(part);
						worldObj.spawnEntityInWorld(part);
						break;
					}
					}
					heightPosition = (int) (heightPosition + part.height);
					sizer = 1;
				}

			}
		}


	@Override
	protected void readEntityFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		byte[] byteArray = nbt.getByteArray("constructionPlan");
		final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(
				byteArray);
		try {
			ObjectInputStream objectInputStream = new ObjectInputStream(
					byteArrayInputStream);
			constructionPlan = (String[][]) objectInputStream.readObject(); //TODO does not work
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		depth = nbt.getFloat("depth");
		height2 = nbt.getDouble("height2");
		pos = nbt.getInteger("pos");
		orientation = nbt.getInteger("orientation");
		width2 = nbt.getInteger("width2");
		
		this.dataWatcher.updateObject(28, this.orientation);
		this.dataWatcher.updateObject(25, pos);
		this.dataWatcher.updateObject(26, this.depth);
		this.dataWatcher.updateObject(27, "" + this.height2);
		this.dataWatcher.updateObject(24, "" + this.width2);
		
		this.constructFromPlan();

	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound nbt) {
		super.writeEntityToNBT(nbt);
		final ByteArrayOutputStream stream = new ByteArrayOutputStream();
		try {
			final ObjectOutputStream objStream = new ObjectOutputStream(stream);
			objStream.writeObject(constructionPlan);
			objStream.flush();
			objStream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		final byte[] bytes = stream.toByteArray();
		nbt.setByteArray("constructionPlan", bytes);
		
		nbt.setInteger("orientation", orientation);
		nbt.setInteger("pos", pos); 
		nbt.setFloat("depth",depth);
		nbt.setDouble("height2",height2);
		nbt.setDouble("width2", width2);
		
	}



	public void setOrientation(boolean b, int var24) {
		orientation = var24;
		this.dataWatcher.updateObject(28, this.orientation);
		this.dataWatcher.updateObject(25, pos);
	}



	@Override
	public void func_110128_b(Entity player) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void setBoundsAt(double posX, double posY, double posZ) {
		// TODO Auto-generated method stub
		
	}

}
