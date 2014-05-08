package tektor.minecraft.talldoors.doorworkshop.entity;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.SerializationUtils;

import tektor.minecraft.talldoors.TallDoorsBase;
import tektor.minecraft.talldoors.doorworkshop.DoorPartRegistry;
import tektor.minecraft.talldoors.doorworkshop.doorparttypes.AbstractDoorPartType;
import tektor.minecraft.talldoors.doorworkshop.entity.doorparts.AbstractDoorPart;
import tektor.minecraft.talldoors.entities.AbstractLockable;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class DoorBase extends AbstractLockable {

	String[][] constructionPlan;

	public int orientation; // 28
	public int pos; // 30
	public float depth; // 26

	List<AbstractDoorPart> parts;
	public double height2; // 27
	public double width2; // 24

	public boolean left;

	public DoorBase(World par1World) {
		super(par1World);
		constructionPlan = new String[1][1];
		parts = new ArrayList<AbstractDoorPart>(1);

		this.preventEntitySpawning = true;
		this.noClip = true;
		this.ignoreFrustumCheck = true;

		this.left = true;
	}

	public void setConstructionPlan(String[][] plan) {
		this.constructionPlan = plan;
		this.parts = new ArrayList<AbstractDoorPart>(plan[0].length
				* plan.length);
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

		this.dataWatcher.addObject(30, 0);
		this.dataWatcher.addObject(26, 0f);
		this.dataWatcher.addObject(27, "" + 0);
		this.dataWatcher.addObject(24, "" + 0);
		this.dataWatcher.addObject(29, 0);
	}

	public void onUpdate() {
		if (this.worldObj.isRemote) {
			orientation = this.dataWatcher.getWatchableObjectInt(28);
			pos = this.dataWatcher.getWatchableObjectInt(30);
			depth = this.dataWatcher.getWatchableObjectFloat(26);
			left = this.dataWatcher.getWatchableObjectInt(29) == 1;
			height2 = Double.parseDouble(this.dataWatcher
					.getWatchableObjectString(27));
			width2 = Double.parseDouble(this.dataWatcher
					.getWatchableObjectString(24));
		}
		setBoundsAt(posX, posY, posZ);
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
					part = classH.getNewEntity(this.worldObj, (int) this.posX
							- columns, (int) heightPosition, (int) this.posZ,
							sizer, orientation);
					this.parts.add(part);
					part.setOrientation(left, orientation);
					worldObj.spawnEntityInWorld(part);
					part.master = this;
					break;
				}
				case 1: {
					AbstractDoorPartType classH = DoorPartRegistry
							.getPartForIndex(constructionPlan[columns][blocks]);
					part = classH.getNewEntity(this.worldObj, (int) this.posX,
							(int) heightPosition, (int) this.posZ - columns,
							sizer, orientation);
					this.parts.add(part);
					part.setOrientation(left, orientation);
					worldObj.spawnEntityInWorld(part);
					part.master = this;
					break;
				}
				case 2: {
					AbstractDoorPartType classH = DoorPartRegistry
							.getPartForIndex(constructionPlan[columns][blocks]);
					part = classH.getNewEntity(this.worldObj, (int) this.posX
							+ columns, (int) heightPosition, (int) this.posZ,
							sizer, orientation);
					this.parts.add(part);
					part.setOrientation(left, orientation);
					worldObj.spawnEntityInWorld(part);
					part.master = this;
					break;
				}
				case 3: {
					AbstractDoorPartType classH = DoorPartRegistry
							.getPartForIndex(constructionPlan[columns][blocks]);
					part = classH.getNewEntity(this.worldObj, (int) this.posX,
							(int) heightPosition, (int) this.posZ + columns,
							sizer, orientation);
					this.parts.add(part);
					part.setOrientation(left, orientation);
					worldObj.spawnEntityInWorld(part);
					part.master = this;
					break;
				}
				}
				heightPosition = (int) (heightPosition + part.height2);
				sizer = 1;
			}

		}
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
		return false;
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound nbt) {
		super.readEntityFromNBT(nbt);
		constructionPlan = (String[][]) SerializationUtils.deserialize(nbt
				.getByteArray("constructionPlan"));
		depth = nbt.getFloat("depth");
		height2 = nbt.getDouble("height2");
		pos = nbt.getInteger("pos");
		orientation = nbt.getInteger("orientation");
		width2 = nbt.getInteger("width2");

		left = nbt.getBoolean("left");
		if (left) {
			this.dataWatcher.updateObject(29, 1);
		} else {
			this.dataWatcher.updateObject(29, 0);
		}

		this.dataWatcher.updateObject(28, this.orientation);
		this.dataWatcher.updateObject(30, pos);
		this.dataWatcher.updateObject(26, this.depth);
		this.dataWatcher.updateObject(27, "" + this.height2);
		this.dataWatcher.updateObject(24, "" + this.width2);

		// this.constructFromPlan();

	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound nbt) {
		super.writeEntityToNBT(nbt);
		final byte[] bytes = SerializationUtils
				.serialize(this.constructionPlan);
		nbt.setByteArray("constructionPlan", bytes);

		nbt.setInteger("orientation", orientation);
		nbt.setInteger("pos", pos);
		nbt.setFloat("depth", depth);
		nbt.setDouble("height2", height2);
		nbt.setDouble("width2", width2);

		nbt.setBoolean("left", left);
	}

	public void setOrientation(boolean b, int var24) {
		left = b;
		orientation = var24;
		this.dataWatcher.updateObject(28, this.orientation);
		this.dataWatcher.updateObject(30, pos);
		if (left) {
			this.dataWatcher.updateObject(29, 1);
		} else {
			this.dataWatcher.updateObject(29, 0);
		}
	}

	public void func_110128_b(Entity par1Entity) {
		if (par1Entity instanceof EntityPlayer) {
			this.setDead();
			for (AbstractDoorPart part : this.parts) {
				part.setDead();
			}
			EntityPlayer entityplayer = (EntityPlayer) par1Entity;

			if (entityplayer.capabilities.isCreativeMode) {
				return;
			}
		}
		this.entityDropItem(getDrop(), 0.0F);
	}

	public void setBoundsAt(double x, double y, double z) {
		this.boundingBox.setBounds(x, y, z, x + 0.1, y + 0.1, z + 0.1);

	}

	@Override
	public void setPosition(double par1, double par3, double par5) {
		this.posX = par1;
		this.posY = par3;
		this.posZ = par5;
		setBoundsAt(par1, par3, par5);

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

	public ItemStack getDrop() {
		return null;

	}

	@Override
	public boolean interactFirst(EntityPlayer player) {
		if (!this.worldObj.isRemote) {
			if (player.inventory.getCurrentItem() != null
					&& player.inventory.getCurrentItem().getItem()
							.equals(TallDoorsBase.destructionHammer)) {
				if (!this.locked) {
					func_110128_b(player);
					player.inventory.getCurrentItem().damageItem(1, player);
				} else {
					player.addChatMessage(new ChatComponentText(
							"You can't destroy locked doors"));
				}
				return true;
			} else if (player.inventory.getCurrentItem() != null
					&& player.inventory.getCurrentItem().getItem()
							.equals(TallDoorsBase.key)) {
				if (!locked
						&& player.inventory.getCurrentItem().stackTagCompound != null
						&& player.inventory.getCurrentItem().getItemDamage() == 1) {
					this.keyCode = player.inventory.getCurrentItem().stackTagCompound
							.getString("keyCode");
					locked = true;
					player.addChatMessage(new ChatComponentText(
							"Locked the door with the key" + keyCode));
					return true;
				} else if (!keyCode.equals("-1")
						&& keyCode
								.equals(player.inventory.getCurrentItem().stackTagCompound
										.getString("keyCode"))
						&& player.inventory.getCurrentItem().getItemDamage() == 1) {
					this.keyCode = "-1";
					locked = false;
					player.addChatMessage(new ChatComponentText(
							"Unlocked this door."));
					return true;

				}

			}
			if (!locked) {
				if (pos == 0) {
					pos = 1;
					worldObj.playSoundAtEntity(this, "random.door_open", 1.0f,
							1.0f);

					switch (orientation) {
					case 0: {
						if (left) {
							for (AbstractDoorPart part : parts) {
								part.setPosition(this.posX, part.posY,
										this.posZ + (this.posX - part.posX));
								part.setPos(pos);
							}
						} else {
							for (AbstractDoorPart part : parts) {
								part.setPosition(this.posX - this.width2
										+ this.depth, part.posY, this.posZ
										+ (this.posX - part.posX));
								part.setPos(pos);
							}
						}
						break;
					}
					case 1: {
						if (left) {
							for (AbstractDoorPart part : parts) {
								part.setPosition(this.posX
										+ (part.posZ - this.posZ), part.posY,
										this.posZ);
								part.setPos(pos);
							}
						} else {
							for (AbstractDoorPart part : parts) {
								part.setPosition(this.posX
										+ (part.posZ - this.posZ), part.posY,
										this.posZ - this.width2 + this.depth);
								part.setPos(pos);
							}
						}
						break;
					}
					case 2: {
						if (left) {
							for (AbstractDoorPart part : parts) {
								part.setPosition(this.posX, part.posY,
										this.posZ + (this.posX - part.posX));
								part.setPos(pos);
							}
						} else {
							for (AbstractDoorPart part : parts) {
								part.setPosition(this.posX + this.width2
										- this.depth, part.posY, this.posZ
										+ (this.posX - part.posX));
								part.setPos(pos);
							}
						}
						break;
					}
					case 3: {
						if (left) {
							for (AbstractDoorPart part : parts) {
								part.setPosition(this.posX
										+ (part.posZ - this.posZ), part.posY,
										this.posZ);
								part.setPos(pos);
							}
						} else {
							for (AbstractDoorPart part : parts) {
								part.setPosition(this.posX
										+ (part.posZ - this.posZ), part.posY,
										this.posZ + this.width2 - this.depth);
								part.setPos(pos);
							}
						}
						break;
					}
					}

				}

				else {
					pos = 0;
					worldObj.playSoundAtEntity(this, "random.door_close", 1.0f,
							1.0f);
					switch (orientation) {
					case 0: {

						for (AbstractDoorPart part : parts) {
							part.setPosition(this.posX
									+ (this.posZ - part.posZ), part.posY,
									this.posZ);
							part.setPos(pos);

						}
						break;
					}
					case 1: {

						for (AbstractDoorPart part : parts) {
							part.setPosition(this.posX, part.posY, this.posZ
									+ (part.posX - this.posX));
							part.setPos(pos);
						}
						break;
					}
					case 2: {
						for (AbstractDoorPart part : parts) {
							part.setPosition(this.posX
									+ (this.posZ - part.posZ), part.posY,
									this.posZ);
							part.setPos(pos);
						}
						break;
					}
					case 3: {
						for (AbstractDoorPart part : parts) {
							part.setPosition(this.posX, part.posY, this.posZ
									+ (part.posX - this.posX));
							part.setPos(pos);
						}
						break;
					}
					}
				}
			} else {
				if (checkKey(player)) {
					if (pos == 0) {
						pos = 1;
						worldObj.playSoundAtEntity(this, "random.door_open",
								1.0f, 1.0f);

					}

					else {
						pos = 0;
						worldObj.playSoundAtEntity(this, "random.door_close",
								1.0f, 1.0f);

					}
				} else {
					player.addChatMessage(new ChatComponentText(
							"This door is locked, you need the right key in your inventory."));
				}
			}

		} else {
			if (player.inventory.getCurrentItem() != null
					&& player.inventory.getCurrentItem().getItem()
							.equals(TallDoorsBase.destructionHammer)) {
				player.swingItem();
			}
		}
		this.dataWatcher.updateObject(30, pos);
		setBoundsAt(posX, posY, posZ);

		return true;

	}

	@Override
	public boolean attackEntityFrom(DamageSource par1DamageSource, float par2) {
		return false;
	}

	public int func_82329_d() {
		return 64;
	}

	public int func_82330_g() {
		return 2;
	}

	public void addPart(AbstractDoorPart abstractDoorPart) {
		parts.add(abstractDoorPart);

	}
}
