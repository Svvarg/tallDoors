package tektor.minecraft.talldoors;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import tektor.minecraft.talldoors.blocks.DrawbridgeWorkbench;
import tektor.minecraft.talldoors.blocks.KeyRedstoneLock;
import tektor.minecraft.talldoors.blocks.MosaicBlock;
import tektor.minecraft.talldoors.blocks.MosaicGlass;
import tektor.minecraft.talldoors.entities.FakeEntity;
import tektor.minecraft.talldoors.entities.FenceGate1;
import tektor.minecraft.talldoors.entities.doors_width2.DarkMetalEntranceDoor1;
import tektor.minecraft.talldoors.entities.doors_width2.DarkMetalEntranceDoor2;
import tektor.minecraft.talldoors.entities.doors_width2.DarkMetalEntranceDoor3;
import tektor.minecraft.talldoors.entities.doors_width2.EntranceDoor1;
import tektor.minecraft.talldoors.entities.doors_width2.EntranceDoor2;
import tektor.minecraft.talldoors.entities.doors_width2.EntranceDoor3;
import tektor.minecraft.talldoors.entities.doors_width2.MetalEntranceDoor1;
import tektor.minecraft.talldoors.entities.doors_width2.MetalEntranceDoor2;
import tektor.minecraft.talldoors.entities.doors_width2.MetalEntranceDoor3;
import tektor.minecraft.talldoors.entities.drawbridge.DrawbridgeBase;
import tektor.minecraft.talldoors.entities.drawbridge.DrawbridgeMachine;
import tektor.minecraft.talldoors.entities.drawbridge.EntityConnector;
import tektor.minecraft.talldoors.entities.trapdoors.TrapDoor;
import tektor.minecraft.talldoors.entities.workbenches.KeyMaker;
import tektor.minecraft.talldoors.gui.TallDoorsGuiHandler;
import tektor.minecraft.talldoors.items.Connector;
import tektor.minecraft.talldoors.items.DestructionHammer;
import tektor.minecraft.talldoors.items.DoorPlacer;
import tektor.minecraft.talldoors.items.DrawbridgePlacer;
import tektor.minecraft.talldoors.items.DrawbridgeWorkbenchItemBlock;
import tektor.minecraft.talldoors.items.Key;
import tektor.minecraft.talldoors.items.KeyMakerPlacer;
import tektor.minecraft.talldoors.items.MosaicTool;
import tektor.minecraft.talldoors.items.PermanentMosaicTool;
import tektor.minecraft.talldoors.items.TrapDoorsPlacer;
import tektor.minecraft.talldoors.services.MosaicIconRegistry;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

@Mod(modid = "TallDoors", name = "TallDoors", version = "0.4.0")
public class TallDoorsBase {

	// instance
	@Instance("TallDoors")
	public static TallDoorsBase instance;

	// Says where the client and server 'proxy' code is loaded.
	@SidedProxy(clientSide = "tektor.minecraft.talldoors.client.TallDoorsClientProxy", serverSide = "tektor.minecraft.talldoors.TallDoorsCommonProxy")
	public static TallDoorsCommonProxy proxy;

	public static Item doorPlacer;
	public static Item drawbridge;
	public static Item connector;
	public static Item destructionHammer;
	public static Item key;
	public static Item mosaicTool;
	public static Item keyMakerPlacer;
	public static Item trapDoor;
	public static Item mosaicTool2;
	
	public static Block drawbridgeWorkbench;
	public static Block keyRedstoneLock;
	public static Block mosaic;
	public static Block mosaicGlass;


	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		
		File jarName = null;
		List<String> results = new ArrayList<String>();
		try {
			jarName = event.getSourceFile();
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			ZipFile zf = new ZipFile(jarName.getAbsoluteFile());
			Enumeration e = zf.entries();
			while (e.hasMoreElements()) {
				ZipEntry ze = (ZipEntry) e.nextElement();

				if (ze.getName().contains(
						"assets/talldoors/textures/blocks/mosaic/")) {
					String[] a = ze.getName().split(
							"assets/talldoors/textures/blocks/mosaic/");
					if (a.length > 1 && a[1].contains(".png")) {
						results.add(a[1]);
					}
				}
			}
			zf.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		MosaicIconRegistry.mosaicsIntern = results;

	}

	@EventHandler
	public void load(FMLInitializationEvent event) {

		initializeIDs();
		registerItems();
		registerBlocks();
		registerEntities();
		registerRecipes();
		proxy.registerRenderers();
		registerTileEntities();

		NetworkRegistry.INSTANCE.registerGuiHandler(this,
				new TallDoorsGuiHandler());
	}

	private void registerBlocks() {

		GameRegistry.registerBlock(drawbridgeWorkbench,
				DrawbridgeWorkbenchItemBlock.class, "drawbridgeWorkbench");
		LanguageRegistry.addName(new ItemStack(drawbridgeWorkbench, 1, 0),
				"Drawbridge Workbench");

		GameRegistry.registerBlock(keyRedstoneLock, "keyRedstoneLock");
		LanguageRegistry.addName(new ItemStack(keyRedstoneLock, 1, 0),
				"Redstone Lock");
		GameRegistry.registerBlock(mosaic, "mosaic");
		LanguageRegistry.addName(new ItemStack(mosaic, 1, 0), "Mosaic");
		GameRegistry.registerBlock(mosaicGlass, "mosaicGlass");
		LanguageRegistry.addName(new ItemStack(mosaicGlass, 1, 0), "Mosaic Glass");

	}

	private void registerRecipes() {
		ItemStack door = new ItemStack(Items.wooden_door, 1, 0);
		ItemStack hatch = new ItemStack(Blocks.trapdoor, 1, 0);
		ItemStack fenceGate = new ItemStack(Blocks.fence_gate, 1);
		ItemStack wood = new ItemStack(Blocks.planks, 1);
		ItemStack wood2 = new ItemStack(Blocks.log, 1);
		ItemStack cobble = new ItemStack(Blocks.cobblestone, 1);
		ItemStack sand = new ItemStack(Blocks.sand, 1);
		ItemStack iron = new ItemStack(Items.iron_ingot, 1);
		ItemStack string = new ItemStack(Items.string, 1);
		ItemStack stick = new ItemStack(Items.stick, 1);
		ItemStack redstone = new ItemStack(Items.redstone, 1);
		ItemStack glass = new ItemStack(Blocks.glass,1);
		ItemStack glow = new ItemStack(Items.glowstone_dust,1);

		// mosaic tool
		GameRegistry.addShapedRecipe(new ItemStack(TallDoorsBase.mosaicTool, 1,
				0), new Object[] { "Y Y", "YXY", "X X", 'X', wood, 'Y', iron });
		// mosaic tool 2
		GameRegistry.addShapedRecipe(new ItemStack(TallDoorsBase.mosaicTool2, 1,
				0), new Object[] { "Y Y", "YZY", "X X", 'X', wood, 'Y', iron,
				'Z', wood2 });
 
		// mosaic
		GameRegistry.addShapedRecipe(new ItemStack(TallDoorsBase.mosaic, 1, 0),
				new Object[] { "YXY", "XYX", "YXY", 'X', cobble, 'Y', sand });
		// mosaic
				GameRegistry.addShapedRecipe(new ItemStack(TallDoorsBase.mosaicGlass, 8, 0),
						new Object[] { "YXY", "XYX", "YXY", 'X', glow, 'Y', glass });
				
		// key maker
		GameRegistry.addShapedRecipe(new ItemStack(
				TallDoorsBase.keyMakerPlacer, 1, 0), new Object[] { "XYX",
				"XYX", "X X", 'X', wood, 'Y', iron });

		// redstone lock
		GameRegistry.addShapedRecipe(new ItemStack(
				TallDoorsBase.keyRedstoneLock, 1, 0), new Object[] { "XYX",
				"XZX", "XYX", 'X', wood, 'Y', iron, 'Z', redstone });

		// Destruction Hammer
		GameRegistry.addShapedRecipe(new ItemStack(
				TallDoorsBase.destructionHammer, 1, 0), new Object[] { "YYY",
				"YXY", " X ", 'X', stick, 'Y', iron });
		// Drawbridge Workbench
		GameRegistry.addShapedRecipe(new ItemStack(
				TallDoorsBase.drawbridgeWorkbench, 1, 0), new Object[] { "YYY",
				"XXX", "XXX", 'X', wood, 'Y', cobble });

		// Machine Workbench
		GameRegistry.addShapedRecipe(new ItemStack(
				TallDoorsBase.drawbridgeWorkbench, 1, 1), new Object[] { "ZYZ",
				"XXX", "XXX", 'X', wood, 'Y', cobble, 'Z', redstone });

		// Connector
		GameRegistry.addShapedRecipe(new ItemStack(TallDoorsBase.connector, 1,
				0), new Object[] { " X ", "XYX", " X ", 'X', string, 'Y',
				cobble });

		// draw base
		// GameRegistry
		// .addShapedRecipe(new ItemStack(TallDoorsBase.drawbridge, 1, 0),
		// new Object[] { "XXX", "XYX", "XXX", 'X', wood, 'Y',
		// wood2 });

		// draw machine
		// GameRegistry.addShapedRecipe(new ItemStack(TallDoorsBase.drawbridge,
		// 1,
		// 1),
		// new Object[] { "XXX", "YXY", "XXX", 'X', wood, 'Y', cobble });

		// Dark Metal Right 4 high Door
		GameRegistry.addShapedRecipe(new ItemStack(TallDoorsBase.doorPlacer, 1,
				10),
				new Object[] { "XXX", "XXY", "XXX", 'X', cobble, 'Y', door });
		// Dark Metal left 4 high
		GameRegistry.addShapedRecipe(new ItemStack(TallDoorsBase.doorPlacer, 1,
				11),
				new Object[] { "XXX", "XXX", "YXX", 'X', cobble, 'Y', door });
		// Metal right 5 high
		GameRegistry.addShapedRecipe(new ItemStack(TallDoorsBase.doorPlacer, 1,
				12), new Object[] { "XXX", "XXY", "XXX", 'X', cobble, 'Y',
				new ItemStack(TallDoorsBase.doorPlacer, 1, 10) });
		// Metal left 5 high
		GameRegistry.addShapedRecipe(new ItemStack(TallDoorsBase.doorPlacer, 1,
				13), new Object[] { "XXX", "YXX", "XXX", 'X', cobble, 'Y',
				new ItemStack(TallDoorsBase.doorPlacer, 1, 11) });
		// Metal right 6 high
		GameRegistry.addShapedRecipe(new ItemStack(TallDoorsBase.doorPlacer, 1,
				14), new Object[] { "XXX", "XXY", "XXX", 'X', cobble, 'Y',
				new ItemStack(TallDoorsBase.doorPlacer, 1, 12) });
		// Metal left 6 high
		GameRegistry.addShapedRecipe(new ItemStack(TallDoorsBase.doorPlacer, 1,
				15), new Object[] { "XXX", "YXX", "XXX", 'X', cobble, 'Y',
				new ItemStack(TallDoorsBase.doorPlacer, 1, 13) });

		// Metal Right 4 high Door
		GameRegistry.addShapedRecipe(new ItemStack(TallDoorsBase.doorPlacer, 1,
				8), new Object[] { "XXX", "XXY", "XXX", 'X', iron, 'Y', door });
		// Metal left 4 high
		GameRegistry.addShapedRecipe(new ItemStack(TallDoorsBase.doorPlacer, 1,
				9), new Object[] { "XXX", "XXX", "YXX", 'X', iron, 'Y', door });
		// Metal right 5 high
		GameRegistry.addShapedRecipe(new ItemStack(TallDoorsBase.doorPlacer, 1,
				16), new Object[] { "XXX", "XXY", "XXX", 'X', iron, 'Y',
				new ItemStack(TallDoorsBase.doorPlacer, 1, 8) });
		// Metal left 5 high
		GameRegistry.addShapedRecipe(new ItemStack(TallDoorsBase.doorPlacer, 1,
				17), new Object[] { "XXX", "YXX", "XXX", 'X', iron, 'Y',
				new ItemStack(TallDoorsBase.doorPlacer, 1, 9) });
		// Metal right 6 high
		GameRegistry.addShapedRecipe(new ItemStack(TallDoorsBase.doorPlacer, 1,
				18), new Object[] { "XXX", "XXY", "XXX", 'X', iron, 'Y',
				new ItemStack(TallDoorsBase.doorPlacer, 1, 16) });
		// Metal left 6 high
		GameRegistry.addShapedRecipe(new ItemStack(TallDoorsBase.doorPlacer, 1,
				19), new Object[] { "XXX", "YXX", "XXX", 'X', iron, 'Y',
				new ItemStack(TallDoorsBase.doorPlacer, 1, 17) });

		// Right 4 high Door
		GameRegistry.addShapedRecipe(new ItemStack(TallDoorsBase.doorPlacer, 1,
				0), new Object[] { "XXX", "XXY", "XXX", 'X', wood, 'Y', door });
		// left 4 high
		GameRegistry.addShapedRecipe(new ItemStack(TallDoorsBase.doorPlacer, 1,
				1), new Object[] { "XXX", "XXX", "YXX", 'X', wood, 'Y', door });
		// right 5 high
		GameRegistry.addShapedRecipe(new ItemStack(TallDoorsBase.doorPlacer, 1,
				2), new Object[] { "XXX", "XXY", "XXX", 'X', wood, 'Y',
				new ItemStack(TallDoorsBase.doorPlacer, 1, 0) });
		// left 5 high
		GameRegistry.addShapedRecipe(new ItemStack(TallDoorsBase.doorPlacer, 1,
				3), new Object[] { "XXX", "YXX", "XXX", 'X', wood, 'Y',
				new ItemStack(TallDoorsBase.doorPlacer, 1, 1) });
		// right 6 high
		GameRegistry.addShapedRecipe(new ItemStack(TallDoorsBase.doorPlacer, 1,
				4), new Object[] { "XXX", "XXY", "XXX", 'X', wood, 'Y',
				new ItemStack(TallDoorsBase.doorPlacer, 1, 2) });
		// left 6 high
		GameRegistry.addShapedRecipe(new ItemStack(TallDoorsBase.doorPlacer, 1,
				5), new Object[] { "XXX", "YXX", "XXX", 'X', wood, 'Y',
				new ItemStack(TallDoorsBase.doorPlacer, 1, 3) });
		// right fence gate
		GameRegistry.addShapedRecipe(new ItemStack(TallDoorsBase.doorPlacer, 1,
				6), new Object[] { "XXX", "YXX", "XXX", 'X', wood, 'Y',
				fenceGate });
		// left fence gate
		GameRegistry.addShapedRecipe(new ItemStack(TallDoorsBase.doorPlacer, 1,
				7), new Object[] { "XXX", "XXX", "YXX", 'X', wood, 'Y',
				fenceGate });

	}

	private void initializeIDs() {
		doorPlacer = new DoorPlacer();
		connector = new Connector();
		drawbridge = new DrawbridgePlacer();
		destructionHammer = new DestructionHammer();
		key = new Key();
		mosaicTool = new MosaicTool();
		keyMakerPlacer = new KeyMakerPlacer();
		trapDoor = new TrapDoorsPlacer();
		mosaicTool2 = new PermanentMosaicTool();

		drawbridgeWorkbench = new DrawbridgeWorkbench();
		keyRedstoneLock = new KeyRedstoneLock();
		mosaic = new MosaicBlock();
		mosaicGlass = new MosaicGlass();

	}

	private void registerItems() {
		GameRegistry.registerItem(doorPlacer, "doorplacer");
		GameRegistry.registerItem(connector, "connector");
		GameRegistry.registerItem(drawbridge, "drawbridge");
		GameRegistry.registerItem(destructionHammer, "destructionHammer");
		GameRegistry.registerItem(key, "key");
		GameRegistry.registerItem(mosaicTool, "mosaicTool");
		GameRegistry.registerItem(keyMakerPlacer, "keyMakerPlacer");
		GameRegistry.registerItem(trapDoor, "trapDoorPlacer");
		LanguageRegistry.addName(mosaicTool, "Mosaic Tool");
		LanguageRegistry.addName(mosaicTool2, "Permanent Mosaic Tool");
	}

	private void registerEntities() {
		EntityRegistry.registerGlobalEntityID(EntranceDoor1.class,
				"EntranceDoor1", EntityRegistry.findGlobalUniqueEntityId());
		EntityRegistry.registerModEntity(EntranceDoor1.class, "EntranceDoor1",
				0, TallDoorsBase.instance, 120, 5, true);
		EntityRegistry.registerGlobalEntityID(EntranceDoor2.class,
				"EntranceDoor2", EntityRegistry.findGlobalUniqueEntityId());
		EntityRegistry.registerModEntity(EntranceDoor2.class, "EntranceDoor2",
				1, TallDoorsBase.instance, 120, 5, true);
		EntityRegistry.registerGlobalEntityID(EntranceDoor3.class,
				"EntranceDoor3", EntityRegistry.findGlobalUniqueEntityId());
		EntityRegistry.registerModEntity(EntranceDoor3.class, "EntranceDoor3",
				2, TallDoorsBase.instance, 120, 5, true);
		EntityRegistry.registerGlobalEntityID(FenceGate1.class, "FenceGate1",
				EntityRegistry.findGlobalUniqueEntityId());
		EntityRegistry.registerModEntity(FenceGate1.class, "FenceGate1", 3,
				TallDoorsBase.instance, 120, 5, true);
		EntityRegistry.registerGlobalEntityID(EntityConnector.class,
				"EntityConnector", EntityRegistry.findGlobalUniqueEntityId());
		EntityRegistry.registerModEntity(EntityConnector.class,
				"EntityConnector", 4, TallDoorsBase.instance, 120, 5, true);
		EntityRegistry
				.registerGlobalEntityID(MetalEntranceDoor1.class,
						"MetalEntranceDoor1",
						EntityRegistry.findGlobalUniqueEntityId());
		EntityRegistry.registerModEntity(MetalEntranceDoor1.class,
				"MetalEntranceDoor1", 5, TallDoorsBase.instance, 120, 5, true);
		EntityRegistry.registerGlobalEntityID(DarkMetalEntranceDoor1.class,
				"DarkMetalEntranceDoor1",
				EntityRegistry.findGlobalUniqueEntityId());
		EntityRegistry.registerModEntity(DarkMetalEntranceDoor1.class,
				"DarkMetalEntranceDoor1", 6, TallDoorsBase.instance, 120, 5,
				true);
		EntityRegistry.registerGlobalEntityID(DrawbridgeBase.class,
				"DrawbridgeBase", EntityRegistry.findGlobalUniqueEntityId());
		EntityRegistry.registerModEntity(DrawbridgeBase.class,
				"DrawbridgeBase", 7, TallDoorsBase.instance, 120, 5, true);
		EntityRegistry.registerGlobalEntityID(DrawbridgeMachine.class,
				"DrawbridgeMachine", EntityRegistry.findGlobalUniqueEntityId());
		EntityRegistry.registerModEntity(DrawbridgeMachine.class,
				"DrawbridgeMachine", 8, TallDoorsBase.instance, 120, 5, true);

		EntityRegistry.registerGlobalEntityID(DarkMetalEntranceDoor2.class,
				"DarkMetalEntranceDoor2",
				EntityRegistry.findGlobalUniqueEntityId());
		EntityRegistry.registerModEntity(DarkMetalEntranceDoor2.class,
				"DarkMetalEntranceDoor2", 9, TallDoorsBase.instance, 120, 5,
				true);

		EntityRegistry.registerGlobalEntityID(DarkMetalEntranceDoor3.class,
				"DarkMetalEntranceDoor3",
				EntityRegistry.findGlobalUniqueEntityId());
		EntityRegistry.registerModEntity(DarkMetalEntranceDoor3.class,
				"DarkMetalEntranceDoor3", 10, TallDoorsBase.instance, 120, 5,
				true);
		EntityRegistry
				.registerGlobalEntityID(MetalEntranceDoor2.class,
						"MetalEntranceDoor2",
						EntityRegistry.findGlobalUniqueEntityId());
		EntityRegistry.registerModEntity(MetalEntranceDoor2.class,
				"MetalEntranceDoor2", 11, TallDoorsBase.instance, 120, 5, true);
		EntityRegistry
				.registerGlobalEntityID(MetalEntranceDoor3.class,
						"MetalEntranceDoor3",
						EntityRegistry.findGlobalUniqueEntityId());
		EntityRegistry.registerModEntity(MetalEntranceDoor3.class,
				"MetalEntranceDoor3", 12, TallDoorsBase.instance, 120, 5, true);
		EntityRegistry.registerGlobalEntityID(FakeEntity.class, "FakeEntity",
				EntityRegistry.findGlobalUniqueEntityId());
		EntityRegistry.registerModEntity(FakeEntity.class, "FakeEntity", 13,
				TallDoorsBase.instance, 120, 5, true);

		EntityRegistry.registerGlobalEntityID(KeyMaker.class, "KeyMaker",
				EntityRegistry.findGlobalUniqueEntityId());
		EntityRegistry.registerModEntity(KeyMaker.class, "KeyMaker", 14,
				TallDoorsBase.instance, 120, 5, true);

		EntityRegistry.registerGlobalEntityID(TrapDoor.class, "TrapDoor",
				EntityRegistry.findGlobalUniqueEntityId());
		EntityRegistry.registerModEntity(TrapDoor.class, "TrapDoor", 15,
				TallDoorsBase.instance, 120, 5, true);

	}

	private void registerTileEntities() {
		GameRegistry
				.registerTileEntity(
						tektor.minecraft.talldoors.entities.tileentities.DrawbridgeWorkbenchTileEntity.class,
						"Drawbridge_Workbench");
		GameRegistry
				.registerTileEntity(
						tektor.minecraft.talldoors.entities.tileentities.MosaicTileEntity.class,
						"Mosaic");
		GameRegistry
		.registerTileEntity(
				tektor.minecraft.talldoors.entities.tileentities.MosaicGlassTileEntity.class,
				"Mosaic Glass");
		GameRegistry
				.registerTileEntity(
						tektor.minecraft.talldoors.entities.tileentities.KeyRedstoneLockTileEntity.class,
						"keylock");
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {

	}

}
