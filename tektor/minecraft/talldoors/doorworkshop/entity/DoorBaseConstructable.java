package tektor.minecraft.talldoors.doorworkshop.entity;

import net.minecraft.entity.player.EntityPlayer;
import tektor.minecraft.talldoors.doorworkshop.entity.doorparts.AbstractDoorPart;
import tektor.minecraft.talldoors.doorworkshop.util.ModuleTexturePackage;

public interface DoorBaseConstructable {

	void setOrientation(boolean left, int var24);

	void setPositionC(double i, double j, double par6);

	void setConstructionPlan(ModuleTexturePackage[][] result);

	void constructFromPlan();

	void addPart(AbstractDoorPart abstractDoorPart);

	double getPosX();

	double getPosY();

	double getPosZ();

	boolean doWork(EntityPlayer player);

}
