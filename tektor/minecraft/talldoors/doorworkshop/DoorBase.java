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
import tektor.minecraft.talldoors.services.DoorPartRegistry;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class DoorBase extends Entity {

	public int orientation;
	String[][] constructionPlan;
	
	List<AbstractDoorPart> parts;
	
	public DoorBase(World par1World) {
		super(par1World);
		constructionPlan = new String[1][1];
		parts = new ArrayList<AbstractDoorPart>(1);

		this.ignoreFrustumCheck = true;
	}

	
	
	public void setConstructionPlan(String[][] plan)
	{
		this.constructionPlan = plan;
		this.parts = new ArrayList<AbstractDoorPart>(plan[0].length * plan.length);
	}
	
	// logic: each array in this array represents a column

	@Override
	protected void entityInit() {
		// TODO Auto-generated method stub

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
	protected void readEntityFromNBT(NBTTagCompound var1) {
		byte[] byteArray = var1.getByteArray("constructionPlan");
		byte[] bytes2 = var1.getByteArray("parts");
		final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(
				byteArray);
		final ByteArrayInputStream byteArrayInputStream2 = new ByteArrayInputStream(
				bytes2);
		try {
			ObjectInputStream objectInputStream = new ObjectInputStream(
					byteArrayInputStream);
			constructionPlan = (String[][]) objectInputStream.readObject();
			objectInputStream = new ObjectInputStream(
					byteArrayInputStream2);
			parts = (List<AbstractDoorPart>) objectInputStream.readObject();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound var1) {
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
		var1.setByteArray("constructionPlan", bytes);
		stream.reset();
		ObjectOutputStream objStream;
		try {
			objStream = new ObjectOutputStream(stream);
			objStream.writeObject(parts);
			objStream.flush();
			objStream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		final byte[] bytes2 = stream.toByteArray();
		var1.setByteArray("parts", bytes2);
		
	}



	public void setOrientation(boolean b, int var24) {
		orientation = var24;
		
	}

}
