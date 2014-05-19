package tektor.minecraft.talldoors.gui;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import tektor.minecraft.talldoors.container.DrawbridgeWorkbenchContainer;
import tektor.minecraft.talldoors.container.KeyMakerGuiContainer;
import tektor.minecraft.talldoors.container.MachineWorkbenchContainer;
import tektor.minecraft.talldoors.container.MosaicGuiContainer;
import tektor.minecraft.talldoors.doorworkshop.entity.DoorModuleWorkbenchTileEntity;
import tektor.minecraft.talldoors.doorworkshop.entity.ModuleAssemblerTileEntity;
import tektor.minecraft.talldoors.doorworkshop.gui.DoorModuleWorkbenchContainer;
import tektor.minecraft.talldoors.doorworkshop.gui.DoorModuleWorkbenchGUI;
import tektor.minecraft.talldoors.doorworkshop.gui.ModuleAssemblerContainer;
import tektor.minecraft.talldoors.doorworkshop.gui.ModuleAssemblerGUI;
import tektor.minecraft.talldoors.entities.tileentities.DrawbridgeWorkbenchTileEntity;
import tektor.minecraft.talldoors.entities.tileentities.MosaicTileEntity;
import tektor.minecraft.talldoors.entities.workbenches.KeyMaker;
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
		else if (id == 2) {
			TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
			if (tileEntity instanceof MosaicTileEntity) {
				return new MosaicGuiContainer(player.inventory,
						(MosaicTileEntity) tileEntity);
			}
		}else if (id == 3) {
			List<Entity> list = world.getEntitiesWithinAABB(Entity.class,
					AxisAlignedBB.getBoundingBox(x, y, z, x + 1, y + 1, z + 1));
			if (!list.isEmpty()) {
				for (Entity ent : list) {
					if (ent instanceof KeyMaker) {
						return new KeyMakerGuiContainer(player.inventory,
								(KeyMaker) ent);
					}
				}

			}
			
		}
		else if (id == 4) {
			TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
			if (tileEntity instanceof DoorModuleWorkbenchTileEntity) {
				return new DoorModuleWorkbenchContainer(player.inventory,
						(DoorModuleWorkbenchTileEntity) tileEntity);
			}
		}else if (id == 5) {
			TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
			if (tileEntity instanceof ModuleAssemblerTileEntity) {
				return new ModuleAssemblerContainer(player.inventory,
						(ModuleAssemblerTileEntity) tileEntity);
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
		else if (id == 2) {
			TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
			if (tileEntity instanceof MosaicTileEntity) {
				return new MosaicChooserGUI(player, player.inventory,
						(MosaicTileEntity) tileEntity);
			}
		}
		else if (id == 3)
		{
			List<Entity> list = world.getEntitiesWithinAABB(Entity.class,
					AxisAlignedBB.getBoundingBox(x, y, z, x + 1, y + 1, z + 1));
			if (!list.isEmpty()) {
				for (Entity ent : list) {
					if (ent instanceof KeyMaker) {
						return new KeyMakerGUI(player, player.inventory, (KeyMaker) ent);
					}
				}

			}
		}else if (id == 4) {
			TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
			if (tileEntity instanceof DoorModuleWorkbenchTileEntity) {
				return new DoorModuleWorkbenchGUI(player, player.inventory,
						(DoorModuleWorkbenchTileEntity) tileEntity);
			}
		}
		else if (id == 5) {
			TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
			if (tileEntity instanceof ModuleAssemblerTileEntity) {
				return new ModuleAssemblerGUI(player, player.inventory,
						(ModuleAssemblerTileEntity) tileEntity);
			}
		}

		return null;

	}

}
