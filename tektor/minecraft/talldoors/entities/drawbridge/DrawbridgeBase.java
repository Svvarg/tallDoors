package tektor.minecraft.talldoors.entities.drawbridge;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import tektor.minecraft.talldoors.TallDoorsBase;
import tektor.minecraft.talldoors.entities.FakeEntity;
import tektor.minecraft.talldoors.items.Connector;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class DrawbridgeBase extends Entity {

	public double rotation;
	public double lon = 7;
	public double width2 = 4;
	public double height2 = 0.125;
	public int orientation;
	boolean up, active;
	public DrawbridgeMachine machine;
	double mX;
	double mY;
	double mZ;
	FakeEntity[] boundEnt;
	int[] places;
	private boolean first;

	public DrawbridgeBase(World par1World) {
		super(par1World);
		rotation = 0;
		this.setSize(7f, 0.125f);
		this.ignoreFrustumCheck = true;
		up = false;
		active = false;
		first = false;
		mX = 0;
		mY = 0;
		mZ = 0;
	}

	public void setPars(int width3, int depth3) {
		width2 = width3;
		lon = depth3;
		makeFakeOnGround(width3, depth3);
		this.dataWatcher.updateObject(22, (int) lon);
		this.dataWatcher.updateObject(23, (int) width2);
		this.setPosition(posX, posY, posZ);
	}

	public void makeFakeOnGround(int width3, int depth3) {
		boundEnt = new FakeEntity[((int) (depth3 / 16) + 2)
				* ((int) (width3 / 16) + 2)];
		places = new int[(((int) (depth3 / 16) + 2) * ((int) (width3 / 16) + 2)) * 3];
		int workWidth = width3;
		int workDepth = depth3;
		int inChunkX, inChunkZ;
		int k = 0;
		if (posX < 0)
			inChunkX = (int) (this.posX % 16) + 16;
		else
			inChunkX = (int) (this.posX % 16);
		if (posZ < 0)
			inChunkZ = (int) (this.posZ % 16) + 16;
		else
			inChunkZ = (int) (this.posZ % 16);
		if (orientation == 0) {
			int firstX = Math.min(16 - inChunkX, workWidth);
			int firstZ = Math.min(16 - inChunkZ, workDepth);
			int i = 0;
			boundEnt[i] = new FakeEntity(worldObj);
			boundEnt[i].setWidthDepth(firstX, firstZ);
			boundEnt[i].setOrientation(orientation);
			boundEnt[i].setPosition(posX, posY, posZ);
			worldObj.spawnEntityInWorld(boundEnt[i]);
			places[k] = (int) boundEnt[i].posX;
			k++;
			places[k] = (int) boundEnt[i].posY;
			k++;
			places[k] = (int) boundEnt[i].posZ;
			k++;
			i++;

			workWidth = workWidth - firstX;
			workDepth = workDepth - firstZ;
			int off = firstX;

			while (workWidth != 0) {
				boundEnt[i] = new FakeEntity(worldObj);
				int newX = Math.min(16, workWidth);
				boundEnt[i].setOrientation(orientation);
				boundEnt[i].setWidthDepth(newX, firstZ);
				boundEnt[i].setPosition(posX + off, posY, posZ);
				worldObj.spawnEntityInWorld(boundEnt[i]);
				workWidth = workWidth - newX;
				off = off + newX;
				places[k] = (int) boundEnt[i].posX;
				k++;
				places[k] = (int) boundEnt[i].posY;
				k++;
				places[k] = (int) boundEnt[i].posZ;
				k++;
				i++;
			}

			int offZ = firstZ;
			while (workDepth != 0) {
				workWidth = width3;
				int newZ = Math.min(16, workDepth);
				off = firstX;
				boundEnt[i] = new FakeEntity(worldObj);
				boundEnt[i].setWidthDepth(firstX, newZ);
				boundEnt[i].setOrientation(orientation);
				boundEnt[i].setPosition(posX, posY, posZ + offZ);
				worldObj.spawnEntityInWorld(boundEnt[i]);
				places[k] = (int) boundEnt[i].posX;
				k++;
				places[k] = (int) boundEnt[i].posY;
				k++;
				places[k] = (int) boundEnt[i].posZ;
				k++;
				i++;
				workWidth = workWidth - firstX;
				while (workWidth != 0) {
					boundEnt[i] = new FakeEntity(worldObj);
					int newX = Math.min(16, workWidth);
					boundEnt[i].setOrientation(orientation);
					boundEnt[i].setWidthDepth(newX, newZ);
					boundEnt[i].setPosition(posX + off, posY, posZ + offZ);
					worldObj.spawnEntityInWorld(boundEnt[i]);
					workWidth = workWidth - newX;
					off = off + newX;
					places[k] = (int) boundEnt[i].posX;
					k++;
					places[k] = (int) boundEnt[i].posY;
					k++;
					places[k] = (int) boundEnt[i].posZ;
					k++;
					i++;
				}
				workDepth = workDepth - newZ;
				offZ = offZ + newZ;
			}
		} else if (orientation == 1) {
			int i = 0;
			int firstWidth = Math.min(16 - inChunkZ, workWidth);
			int firstDepth = Math.min(inChunkX + 1, workDepth);
			boundEnt[i] = new FakeEntity(worldObj);
			boundEnt[i].setWidthDepth(firstWidth, firstDepth);
			boundEnt[i].setOrientation(orientation);
			boundEnt[i].setPosition(posX, posY, posZ);
			worldObj.spawnEntityInWorld(boundEnt[i]);
			places[k] = (int) boundEnt[i].posX;
			k++;
			places[k] = (int) boundEnt[i].posY;
			k++;
			places[k] = (int) boundEnt[i].posZ;
			k++;
			i++;

			workWidth = workWidth - firstWidth;
			workDepth = workDepth - firstDepth;
			int off = firstWidth;

			while (workWidth != 0) {
				int newW = Math.min(16, workWidth);
				boundEnt[i] = new FakeEntity(worldObj);
				boundEnt[i].setWidthDepth(newW, firstDepth);
				boundEnt[i].setOrientation(orientation);
				boundEnt[i].setPosition(posX, posY, posZ + off);
				worldObj.spawnEntityInWorld(boundEnt[i]);
				places[k] = (int) boundEnt[i].posX;
				k++;
				places[k] = (int) boundEnt[i].posY;
				k++;
				places[k] = (int) boundEnt[i].posZ;
				k++;
				i++;
				off = off + newW;
				workWidth = workWidth - newW;

			}
			int offD = firstDepth;
			while (workDepth != 0) {
				workWidth = width3 - firstWidth;
				int newD = Math.min(16, workDepth);
				boundEnt[i] = new FakeEntity(worldObj);
				boundEnt[i].setWidthDepth(firstWidth, newD);
				boundEnt[i].setOrientation(orientation);
				boundEnt[i].setPosition(posX - offD, posY, posZ);
				worldObj.spawnEntityInWorld(boundEnt[i]);
				places[k] = (int) boundEnt[i].posX;
				k++;
				places[k] = (int) boundEnt[i].posY;
				k++;
				places[k] = (int) boundEnt[i].posZ;
				k++;
				i++;
				off = firstWidth;
				while (workWidth != 0) {
					int newW = Math.min(16, workWidth);
					boundEnt[i] = new FakeEntity(worldObj);
					boundEnt[i].setWidthDepth(newW, newD);
					boundEnt[i].setOrientation(orientation);
					boundEnt[i].setPosition(posX - offD, posY, posZ + off);
					worldObj.spawnEntityInWorld(boundEnt[i]);
					places[k] = (int) boundEnt[i].posX;
					k++;
					places[k] = (int) boundEnt[i].posY;
					k++;
					places[k] = (int) boundEnt[i].posZ;
					k++;
					i++;
					off = off + newW;
					workWidth = workWidth - newW;
				}
				offD = offD + newD;
				workDepth = workDepth - newD;

			}

		} else if (orientation == 2) {
			int i = 0;
			int firstWidth = Math.min(inChunkX + 1, workWidth);
			int firstDepth = Math.min(inChunkZ + 1, workDepth);
			boundEnt[i] = new FakeEntity(worldObj);
			boundEnt[i].setWidthDepth(firstWidth, firstDepth);
			boundEnt[i].setOrientation(orientation);
			boundEnt[i].setPosition(posX, posY, posZ);
			worldObj.spawnEntityInWorld(boundEnt[i]);

			places[k] = (int) boundEnt[i].posX;
			k++;
			places[k] = (int) boundEnt[i].posY;
			k++;
			places[k] = (int) boundEnt[i].posZ;
			k++;
			i++;
			workWidth = workWidth - firstWidth;
			workDepth = workDepth - firstDepth;
			int off = firstWidth;

			while (workWidth != 0) {
				int newW = Math.min(16, workWidth);
				boundEnt[i] = new FakeEntity(worldObj);
				boundEnt[i].setWidthDepth(newW, firstDepth);
				boundEnt[i].setOrientation(orientation);
				boundEnt[i].setPosition(posX - off, posY, posZ);
				worldObj.spawnEntityInWorld(boundEnt[i]);
				places[k] = (int) boundEnt[i].posX;
				k++;
				places[k] = (int) boundEnt[i].posY;
				k++;
				places[k] = (int) boundEnt[i].posZ;
				k++;
				i++;
				off = off + newW;
				workWidth = workWidth - newW;

			}

			int offD = firstDepth;
			while (workDepth != 0) {
				workWidth = width3 - firstWidth;
				int newD = Math.min(16, workDepth);
				boundEnt[i] = new FakeEntity(worldObj);
				boundEnt[i].setWidthDepth(firstWidth, newD);
				boundEnt[i].setOrientation(orientation);
				boundEnt[i].setPosition(posX, posY, posZ - offD);
				worldObj.spawnEntityInWorld(boundEnt[i]);
				places[k] = (int) boundEnt[i].posX;
				k++;
				places[k] = (int) boundEnt[i].posY;
				k++;
				places[k] = (int) boundEnt[i].posZ;
				k++;
				i++;
				off = firstWidth;
				while (workWidth != 0) {
					int newW = Math.min(16, workWidth);
					boundEnt[i] = new FakeEntity(worldObj);
					boundEnt[i].setWidthDepth(newW, newD);
					boundEnt[i].setOrientation(orientation);
					boundEnt[i].setPosition(posX - off, posY, posZ - offD);
					worldObj.spawnEntityInWorld(boundEnt[i]);
					places[k] = (int) boundEnt[i].posX;
					k++;
					places[k] = (int) boundEnt[i].posY;
					k++;
					places[k] = (int) boundEnt[i].posZ;
					k++;
					i++;
					off = off + newW;
					workWidth = workWidth - newW;
				}
				offD = offD + newD;
				workDepth = workDepth - newD;

			}

		} else if (orientation == 3) {
			int i = 0;
			int firstWidth = Math.min(inChunkZ + 1, workWidth);
			int firstDepth = Math.min(16 - inChunkX, workDepth);
			boundEnt[i] = new FakeEntity(worldObj);
			boundEnt[i].setWidthDepth(firstWidth, firstDepth);
			boundEnt[i].setOrientation(orientation);
			boundEnt[i].setPosition(posX, posY, posZ);
			worldObj.spawnEntityInWorld(boundEnt[i]);
			places[k] = (int) boundEnt[i].posX;
			k++;
			places[k] = (int) boundEnt[i].posY;
			k++;
			places[k] = (int) boundEnt[i].posZ;
			k++;
			i++;

			workWidth = workWidth - firstWidth;
			workDepth = workDepth - firstDepth;
			int off = firstWidth;

			while (workWidth != 0) {
				int newW = Math.min(16, workWidth);
				boundEnt[i] = new FakeEntity(worldObj);
				boundEnt[i].setWidthDepth(newW, firstDepth);
				boundEnt[i].setOrientation(orientation);
				boundEnt[i].setPosition(posX, posY, posZ - off);
				worldObj.spawnEntityInWorld(boundEnt[i]);
				places[k] = (int) boundEnt[i].posX;
				k++;
				places[k] = (int) boundEnt[i].posY;
				k++;
				places[k] = (int) boundEnt[i].posZ;
				k++;
				i++;
				off = off + newW;
				workWidth = workWidth - newW;

			}

			int offD = firstDepth;
			while (workDepth != 0) {
				workWidth = width3 - firstWidth;
				int newD = Math.min(16, workDepth);
				boundEnt[i] = new FakeEntity(worldObj);
				boundEnt[i].setWidthDepth(firstWidth, newD);
				boundEnt[i].setOrientation(orientation);
				boundEnt[i].setPosition(posX + offD, posY, posZ);
				worldObj.spawnEntityInWorld(boundEnt[i]);
				places[k] = (int) boundEnt[i].posX;
				k++;
				places[k] = (int) boundEnt[i].posY;
				k++;
				places[k] = (int) boundEnt[i].posZ;
				k++;
				i++;
				off = firstWidth;
				while (workWidth != 0) {
					int newW = Math.min(16, workWidth);
					boundEnt[i] = new FakeEntity(worldObj);
					boundEnt[i].setWidthDepth(newW, newD);
					boundEnt[i].setOrientation(orientation);
					boundEnt[i].setPosition(posX + offD, posY, posZ - off);
					worldObj.spawnEntityInWorld(boundEnt[i]);
					places[k] = (int) boundEnt[i].posX;
					k++;
					places[k] = (int) boundEnt[i].posY;
					k++;
					places[k] = (int) boundEnt[i].posZ;
					k++;
					i++;
					off = off + newW;
					workWidth = workWidth - newW;
				}
				offD = offD + newD;
				workDepth = workDepth - newD;

			}
		}

		for (FakeEntity ent : boundEnt) {
			if (ent != null)
				ent.master = this;
		}
	}

	public void makeFakeStanding(int width3, int depth3)
	{
		boundEnt = new FakeEntity[((int) (depth3 / 16) + 2)
		          				* ((int) (width3 / 16) + 2)];
		
		int workWidth = width3;
		int workDepth = depth3;
		int inChunkX, inChunkZ;
		if (posX < 0)
			inChunkX = (int) (this.posX % 16) + 16;
		else
			inChunkX = (int) (this.posX % 16);
		if (posZ < 0)
			inChunkZ = (int) (this.posZ % 16) + 16;
		else
			inChunkZ = (int) (this.posZ % 16);
		if (orientation == 0) {
			int firstX = Math.min(16 - inChunkX, workWidth);
			int firstZ = workDepth;
			int i = 0;
			boundEnt[i] = new FakeEntity(worldObj);
			boundEnt[i].setWidthDepth(firstX, firstZ);
			boundEnt[i].setUpActive(true, false);
			boundEnt[i].setOrientation(orientation);
			boundEnt[i].setPosition(posX, posY, posZ);
			worldObj.spawnEntityInWorld(boundEnt[i]);
			i++;

			workWidth = workWidth - firstX;
			int off = firstX;

			while (workWidth != 0) {
				boundEnt[i] = new FakeEntity(worldObj);
				int newX = Math.min(16, workWidth);
				boundEnt[i].setOrientation(orientation);
				boundEnt[i].setWidthDepth(newX, firstZ);
				boundEnt[i].setUpActive(true, false);
				boundEnt[i].setPosition(posX + off, posY, posZ);
				worldObj.spawnEntityInWorld(boundEnt[i]);
				workWidth = workWidth - newX;
				off = off + newX;
				i++;
			}
		}else if (orientation == 1) {
			int i = 0;
			int firstWidth = Math.min(16 - inChunkZ, workWidth);
			int firstDepth = workDepth;
			boundEnt[i] = new FakeEntity(worldObj);
			boundEnt[i].setWidthDepth(firstWidth, firstDepth);
			boundEnt[i].setOrientation(orientation);
			boundEnt[i].setUpActive(true, false);
			boundEnt[i].setPosition(posX, posY, posZ);
			worldObj.spawnEntityInWorld(boundEnt[i]);
			i++;

			workWidth = workWidth - firstWidth;
			int off = firstWidth;

			while (workWidth != 0) {
				int newW = Math.min(16, workWidth);
				boundEnt[i] = new FakeEntity(worldObj);
				boundEnt[i].setWidthDepth(newW, firstDepth);
				boundEnt[i].setOrientation(orientation);
				boundEnt[i].setUpActive(true, false);
				boundEnt[i].setPosition(posX, posY, posZ + off);
				worldObj.spawnEntityInWorld(boundEnt[i]);
				i++;
				off = off + newW;
				workWidth = workWidth - newW;

			}
		}
		else if (orientation == 2) {
			int i = 0;
			int firstWidth = Math.min(inChunkX + 1, workWidth);
			int firstDepth = workDepth;
			boundEnt[i] = new FakeEntity(worldObj);
			boundEnt[i].setWidthDepth(firstWidth, firstDepth);
			boundEnt[i].setOrientation(orientation);
			boundEnt[i].setUpActive(true, false);
			boundEnt[i].setPosition(posX, posY, posZ);
			worldObj.spawnEntityInWorld(boundEnt[i]);
			i++;
			workWidth = workWidth - firstWidth;
			int off = firstWidth;

			while (workWidth != 0) {
				int newW = Math.min(16, workWidth);
				boundEnt[i] = new FakeEntity(worldObj);
				boundEnt[i].setWidthDepth(newW, firstDepth);
				boundEnt[i].setOrientation(orientation);
				boundEnt[i].setUpActive(true, false);
				boundEnt[i].setPosition(posX - off, posY, posZ);
				worldObj.spawnEntityInWorld(boundEnt[i]);
				i++;
				off = off + newW;
				workWidth = workWidth - newW;

			}
		}
		else if (orientation == 3) {
			int i = 0;
			int firstWidth = Math.min(inChunkZ + 1, workWidth);
			int firstDepth = workDepth;
			boundEnt[i] = new FakeEntity(worldObj);
			boundEnt[i].setWidthDepth(firstWidth, firstDepth);
			boundEnt[i].setOrientation(orientation);
			boundEnt[i].setUpActive(true, false);
			boundEnt[i].setPosition(posX, posY, posZ);
			worldObj.spawnEntityInWorld(boundEnt[i]);
			i++;

			workWidth = workWidth - firstWidth;
			workDepth = workDepth - firstDepth;
			int off = firstWidth;

			while (workWidth != 0) {
				int newW = Math.min(16, workWidth);
				boundEnt[i] = new FakeEntity(worldObj);
				boundEnt[i].setWidthDepth(newW, firstDepth);
				boundEnt[i].setOrientation(orientation);
				boundEnt[i].setUpActive(true, false);
				boundEnt[i].setPosition(posX, posY, posZ - off);
				worldObj.spawnEntityInWorld(boundEnt[i]);
				i++;
				off = off + newW;
				workWidth = workWidth - newW;

			}
		}
		
		
	}
	
	public void onUpdate() {
		if (this.worldObj.isRemote) {
			orientation = this.dataWatcher.getWatchableObjectInt(28);
			rotation = Double.parseDouble(this.dataWatcher
					.getWatchableObjectString(29));

			up = this.dataWatcher.getWatchableObjectInt(20) == 1;
			active = this.dataWatcher.getWatchableObjectInt(21) == 1;

			mX = this.dataWatcher.getWatchableObjectInt(25);
			mY = this.dataWatcher.getWatchableObjectInt(26);
			mZ = this.dataWatcher.getWatchableObjectInt(27);

			lon = this.dataWatcher.getWatchableObjectInt(22);
			width2 = this.dataWatcher.getWatchableObjectInt(23);
		}
		if (!this.worldObj.isRemote) {
			if (up && active) {
				if (rotation < 90) {
					rotation = rotation + 0.4;
					this.dataWatcher.updateObject(29, "" + rotation);
				} else {
					active = false;
					this.dataWatcher.updateObject(21, 0);
				}
			} else if (!up && active) {
				if (rotation > 0) {
					rotation = rotation - 0.4;
					this.dataWatcher.updateObject(29, "" + rotation);
				} else {
					active = false;
					this.dataWatcher.updateObject(21, 0);
				}
			}
			else if (!up&&!active&&first&&boundEnt!= null)
			{
				for (FakeEntity ent : boundEnt) {
					if (ent != null)
						ent.setDead();
				}
				makeFakeOnGround((int)width2, (int)lon);
				first = false;
			}
		}
		if (machine == null) {
			List<DrawbridgeMachine> list = (List<DrawbridgeMachine>) worldObj
					.getEntitiesWithinAABB(DrawbridgeMachine.class, boundingBox
							.getBoundingBox(mX - 1, mY - 1, mZ - 1, mX + 1,
									mY + 1, mZ + 1));
			machine = list.isEmpty() ? null : list.get(0);
		}
		if (boundEnt == null) {
			boundEnt = new FakeEntity[1];
		}
		if (boundEnt != null && boundEnt[0] == null && places != null) {
			Set<FakeEntity> set = new HashSet();
			for (int i = 0; i < places.length; i = i + 3) {
				set.addAll(worldObj.getEntitiesWithinAABB(FakeEntity.class,
						boundingBox.getBoundingBox(places[i] - 1,
								places[i + 1] - 1, places[i + 2] - 1,
								places[i] + 1, places[i + 1] + 1,
								places[i + 2] + 1)));

			}
			boundEnt = new FakeEntity[((int) (lon / 16) + 1)
					* ((int) (width2 / 16) + 1)];
			boundEnt = set.toArray(new FakeEntity[0]);
			for (FakeEntity ent : boundEnt) {
				if (ent != null)
					ent.master = this;
			}
		}
		setBoundsAt(posX, posY, posZ);
	}

	@Override
	protected void entityInit() {
		this.dataWatcher.addObject(28, 0);
		this.dataWatcher.addObject(29, "" + 0);

		this.dataWatcher.addObject(25, 0);
		this.dataWatcher.addObject(26, 0);
		this.dataWatcher.addObject(27, 0);

		this.dataWatcher.addObject(20, 0);
		this.dataWatcher.addObject(21, 0);

		this.dataWatcher.addObject(22, 0);
		this.dataWatcher.addObject(23, 0);
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound nbt) {
		height2 = nbt.getDouble("height");
		rotation = nbt.getDouble("rotation");
		this.dataWatcher.updateObject(29, "" + rotation);
		width2 = nbt.getDouble("width");
		lon = nbt.getDouble("lon");
		this.dataWatcher.updateObject(22, (int) lon);
		this.dataWatcher.updateObject(23, (int) width2);
		this.setOrientation(nbt.getInteger("orientation"));
		List<DrawbridgeMachine> list = (List<DrawbridgeMachine>) worldObj
				.getEntitiesWithinAABB(DrawbridgeMachine.class, boundingBox
						.getBoundingBox(nbt.getDouble("mX") - 1,
								nbt.getDouble("mY") - 1,
								nbt.getDouble("mZ") - 1,
								nbt.getDouble("mx") + 1,
								nbt.getDouble("mY") + 1,
								nbt.getDouble("mZ") + 1));
		machine = list.isEmpty() ? null : list.get(0);
		places = nbt.getIntArray("places");

		this.mX = nbt.getDouble("mX");
		this.mY = nbt.getDouble("mY");
		this.mZ = nbt.getDouble("mZ");
		this.dataWatcher.updateObject(25, (int) mX);
		this.dataWatcher.updateObject(26, (int) mY);
		this.dataWatcher.updateObject(27, (int) mZ);
		active = nbt.getBoolean("active");
		up = nbt.getBoolean("up");
		if (active)
			this.dataWatcher.updateObject(21, 1);
		else
			this.dataWatcher.updateObject(21, 0);
		if (up)
			this.dataWatcher.updateObject(20, 1);
		else
			this.dataWatcher.updateObject(20, 0);

	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound nbt) {
		nbt.setDouble("height", height2);
		nbt.setDouble("rotation", rotation);
		nbt.setDouble("width", width2);
		nbt.setDouble("lon", lon);
		nbt.setInteger("orientation", orientation);
		if (machine != null) {
			nbt.setDouble("mX", machine.posX);
			nbt.setDouble("mY", machine.posY);
			nbt.setDouble("mZ", machine.posZ);
		}
		nbt.setBoolean("up", up);
		nbt.setBoolean("active", active);
		nbt.setIntArray("places", places);
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

	public void setOrientation(int var24) {

		orientation = var24;
		this.dataWatcher.updateObject(28, var24);

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

	@Override
	public void setPosition(double par1, double par3, double par5) {
		this.posX = par1;
		this.posY = par3;
		this.posZ = par5;
		setBoundsAt(par1, par3, par5);

	}

	public void setBoundsAt(double par1, double par3, double par5) {
		float f = this.width / 2.0F;
		double f1 = this.height2;
		if (this.active == false && this.up == false) {
			if (orientation == 0) {
				this.boundingBox.setBounds(par1, par3 - this.yOffset
						+ this.ySize, par5, par1 + width2, par3 - this.yOffset
						+ this.ySize + f1, par5 + lon);
			} else if (orientation == 1) {
				this.boundingBox.setBB(AxisAlignedBB.getBoundingBox(par1 - lon
						+ 1, par3 - this.yOffset + this.ySize, par5, par1 + 1,
						par3 - this.yOffset + this.ySize + f1, par5 + width2));
			} else if (orientation == 2) {
				this.boundingBox.setBounds(par1 - width2 + 1, par3
						- this.yOffset + this.ySize, par5 - lon + 1, par1 + 1,
						par3 - this.yOffset + this.ySize + f1, par5 + 1);
			} else if (orientation == 3) {
				this.boundingBox.setBounds(par1, par3 - this.yOffset
						+ this.ySize, par5 - width2 + 1, par1 + lon, par3
						- this.yOffset + this.ySize + f1, par5 + 1);
			}
		} else {

			f1 = (float) lon;
			if (orientation == 0) {
				this.boundingBox.setBounds(par1, par3 - this.yOffset
						+ this.ySize, par5, par1 + width2, par3 - this.yOffset
						+ this.ySize + f1, par5 + 0.125f);

				

			} else if (orientation == 1) {
				this.boundingBox.setBounds(par1 - 0.125f + 1, par3
						- this.yOffset + this.ySize, par5, par1 + 1, par3
						- this.yOffset + this.ySize + f1, par5 + width2);
				

			} else if (orientation == 2) {
				this.boundingBox.setBounds(par1 - width2 + 1, par3
						- this.yOffset + this.ySize, par5 - 0.125f + 1,
						par1 + 1, par3 - this.yOffset + this.ySize + f1,
						par5 + 1);

				
			} else if (orientation == 3) {
				this.boundingBox.setBounds(par1, par3 - this.yOffset
						+ this.ySize, par5 - width2 + 1, par1 + 0.125f, par3
						- this.yOffset + this.ySize + f1, par5 + 1);
				
			}
		}
	}

	public void activate() {
		if (!this.up) {
			up = true;
			active = true;
			for (FakeEntity ent : boundEnt) {
				if (ent != null)
					ent.setDead();
			}
			makeFakeStanding((int)width2, (int)lon);
			this.dataWatcher.updateObject(21, 1);
			this.dataWatcher.updateObject(20, 1);
		} else {
			up = false;
			active = true;
			first = true;
			this.dataWatcher.updateObject(21, 1);
			this.dataWatcher.updateObject(20, 0);
		}
	}

	public int func_82329_d() {
		return 64;
	}

	public int func_82330_g() {
		return 2;
	}

	@Override
	public boolean interactFirst(EntityPlayer player) {
		if (!this.worldObj.isRemote) {

			if (player.inventory.getCurrentItem() != null
					&& player.inventory.getCurrentItem().getItem().equals(TallDoorsBase.connector)) {
				((Connector) player.inventory.getCurrentItem().getItem()).base = this;
			}
			if (player.inventory.getCurrentItem() != null
					&& player.inventory.getCurrentItem().getItem().equals(TallDoorsBase.destructionHammer)) {
				func_110128_b(player);
				player.inventory.getCurrentItem().damageItem(1, player);
				return true;
			}
		} else {
			if (player.inventory.getCurrentItem() != null
					&& player.inventory.getCurrentItem().getItem().equals(TallDoorsBase.destructionHammer)) {
				player.swingItem();
			}
		}
		return true;
	}

	public void setMachinePos(double posX, double posY, double posZ) {

		mX = posX;
		this.dataWatcher.updateObject(25, (int) mX);
		mY = posY;
		this.dataWatcher.updateObject(26, (int) mY);
		mZ = posZ;
		this.dataWatcher.updateObject(27, (int) mZ);

	}

	@Override
	public boolean attackEntityFrom(DamageSource par1DamageSource, float par2) {
		return false;
	}

	public void func_110128_b(Entity par1Entity) {
		if (par1Entity instanceof EntityPlayer) {
			EntityPlayer entityplayer = (EntityPlayer) par1Entity;
			this.setDead();
			for (FakeEntity ent : boundEnt) {
				if (ent != null)
					ent.setDead();
			}
			if (entityplayer.capabilities.isCreativeMode) {
				return;
			}
		}
		ItemStack drop = new ItemStack(TallDoorsBase.drawbridge, 1, 0);
		drop.stackTagCompound = new NBTTagCompound();
		drop.stackTagCompound.setInteger("width", (int) this.width2);
		drop.stackTagCompound.setInteger("depth", (int) this.lon);
		this.entityDropItem(drop, 0.0F);
	}

}
