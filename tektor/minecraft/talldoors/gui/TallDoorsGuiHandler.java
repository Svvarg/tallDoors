package tektor.minecraft.talldoors.gui;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import tektor.minecraft.chalith.container.ChalithWorkplaceContainer;
import tektor.minecraft.chalith.container.DryStandContainer;
import tektor.minecraft.chalith.container.OilPressContainer;
import tektor.minecraft.chalith.entity.DryStand;
import tektor.minecraft.chalith.entity.oilPress.OilPress;
import tektor.minecraft.chalith.entity.tileentity.ChalithWorkplaceTileEntity;
import tektor.minecraft.chalith.gui.ChalithWorkplaceGui;
import tektor.minecraft.chalith.gui.DryStandGui;
import tektor.minecraft.chalith.gui.OilPressGui;
import tektor.minecraft.talldoors.container.DrawbridgeWorkbenchContainer;
import tektor.minecraft.talldoors.container.MachineWorkbenchContainer;
import tektor.minecraft.talldoors.entities.tileentities.DrawbridgeWorkbenchTileEntity;
import cpw.mods.fml.common.network.IGuiHandler;

public class TallDoorsGuiHandler implements IGuiHandler {

	@Override
	public Object getServerGuiElement(int id, EntityPlayer player, World world,
			int x, int y, int z) {
		if (id == 0) {
			TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
			if (tileEntity instanceof DrawbridgeWorkbenchTileEntity) {
				return new DrawbridgeWorkbenchContainer(player.inventory,
						(DrawbridgeWorkbenchTileEntity) tileEntity);
			}
		}else if (id == 1) {
			TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
			if (tileEntity instanceof DrawbridgeWorkbenchTileEntity) {
				return new MachineWorkbenchContainer(player.inventory,
						(DrawbridgeWorkbenchTileEntity) tileEntity);
			}
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int id, EntityPlayer player, World world,
			int x, int y, int z) {
		if (id == 0) {
			TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
			if (tileEntity instanceof DrawbridgeWorkbenchTileEntity) {
				return new DrawbridgeWorkbenchGUI(player, player.inventory,
						(DrawbridgeWorkbenchTileEntity) tileEntity);
			}
		} else if (id == 1) {
			TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
			if (tileEntity instanceof DrawbridgeWorkbenchTileEntity) {
				return new MachineWorkbenchGUI(player, player.inventory,
						(DrawbridgeWorkbenchTileEntity) tileEntity);
			}
		}
		return null;

	}

}
