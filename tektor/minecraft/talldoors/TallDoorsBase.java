package tektor.minecraft.talldoors;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.oredict.OreDictionary;
import tektor.minecraft.talldoors.blocks.DrawbridgeWorkbench;
import tektor.minecraft.talldoors.blocks.KeyRedstoneLock;
import tektor.minecraft.talldoors.blocks.MosaicBlock;
import tektor.minecraft.talldoors.blocks.MosaicGlass;
import tektor.minecraft.talldoors.blocks.OreBase;
import tektor.minecraft.talldoors.blocks.StoneBase;
import tektor.minecraft.talldoors.doorworkshop.DoorModule;
import tektor.minecraft.talldoors.doorworkshop.DoorPartRegistry;
import tektor.minecraft.talldoors.doorworkshop.ModularDoorPlacer;
import tektor.minecraft.talldoors.doorworkshop.blocks.DoorWorkshop;
import tektor.minecraft.talldoors.doorworkshop.blocks.ModuleAssembler;
import tektor.minecraft.talldoors.doorworkshop.doorparttypes.NullPartType;
import tektor.minecraft.talldoors.doorworkshop.doorparttypes.PlainDoorPartType;
import tektor.minecraft.talldoors.doorworkshop.doorparttypes.balks.DoubleHorizontalBalkPartType;
import tektor.minecraft.talldoors.doorworkshop.doorparttypes.balks.DoublePlusBalkPartType;
import tektor.minecraft.talldoors.doorworkshop.doorparttypes.balks.DoubleVerticalFrontBalkPartType;
import tektor.minecraft.talldoors.doorworkshop.doorparttypes.balks.FBVerticalBalkPartType;
import tektor.minecraft.talldoors.doorworkshop.doorparttypes.balks.HorizontalBalkPartType;
import tektor.minecraft.talldoors.doorworkshop.doorparttypes.balks.PlusBalkPartType;
import tektor.minecraft.talldoors.doorworkshop.doorparttypes.balks.VerticalBalkPartType;
import tektor.minecraft.talldoors.doorworkshop.doorparttypes.windows.GlassWindow2PartType;
import tektor.minecraft.talldoors.doorworkshop.doorparttypes.windows.GlassWindowPartType;
import tektor.minecraft.talldoors.doorworkshop.doorparttypes.windows.SimpleWindow2PartType;
import tektor.minecraft.talldoors.doorworkshop.doorparttypes.windows.SimpleWindowPartType;
import tektor.minecraft.talldoors.doorworkshop.entity.DoorBase;
import tektor.minecraft.talldoors.doorworkshop.entity.doorparts.NullPartEntity;
import tektor.minecraft.talldoors.doorworkshop.entity.doorparts.PlainDoorPartEntity;
import tektor.minecraft.talldoors.doorworkshop.entity.doorparts.balks.DoubleHorizontalBalkPartEntity;
import tektor.minecraft.talldoors.doorworkshop.entity.doorparts.balks.DoublePlusBalkPartEntity;
import tektor.minecraft.talldoors.doorworkshop.entity.doorparts.balks.DoubleVerticalFrontBalkPartEntity;
import tektor.minecraft.talldoors.doorworkshop.entity.doorparts.balks.FBVerticalBalkPartEntity;
import tektor.minecraft.talldoors.doorworkshop.entity.doorparts.balks.HorizontalBalkPartEntity;
import tektor.minecraft.talldoors.doorworkshop.entity.doorparts.balks.PlusBalkPartEntity;
import tektor.minecraft.talldoors.doorworkshop.entity.doorparts.balks.VerticalBalkPartEntity;
import tektor.minecraft.talldoors.doorworkshop.entity.doorparts.windows.GlassWindow2PartEntity;
import tektor.minecraft.talldoors.doorworkshop.entity.doorparts.windows.GlassWindowPartEntity;
import tektor.minecraft.talldoors.doorworkshop.entity.doorparts.windows.SimpleWindow2PartEntity;
import tektor.minecraft.talldoors.doorworkshop.entity.doorparts.windows.SimpleWindowPartEntity;
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
import tektor.minecraft.talldoors.items.IngotBase;
import tektor.minecraft.talldoors.items.Key;
import tektor.minecraft.talldoors.items.KeyMakerPlacer;
import tektor.minecraft.talldoors.items.MosaicTool;
import tektor.minecraft.talldoors.items.OreBaseItemBlock;
import tektor.minecraft.talldoors.items.PermanentMosaicTool;
import tektor.minecraft.talldoors.items.StoneBaseItemBlock;
import tektor.minecraft.talldoors.items.TrapDoorsPlacer;
import tektor.minecraft.talldoors.services.MosaicIconRegistry;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@Mod(modid = "TallDoors", name = "TallDoors", version = "0.3.3")
@NetworkMod(channels = { "TallDoors", "TallDoors_Mosaic", "TallDoors2" }, packetHandler = TallDoorsPacketHandler.class, clientSideRequired = true)
public class TallDoorsBase {

	// instance
	@Instance("TallDoors")
	public static TallDoorsBase instance;

	// CreativeTab
	public static CreativeTabs tabTallDoors = new CreativeTabs("tallDoors") {
		@Override
		@SideOnly(Side.CLIENT)
		public Item getTabIconItem() {
			return TallDoorsBase.modularDoorPlacer;
		}
	};

	// Says where the client and server 'proxy' code is loaded.
	@SidedProxy(clientSide = "tektor.minecraft.talldoors.client.TallDoorsClientProxy", serverSide = "tektor.minecraft.talldoors.TallDoorsCommonProxy")
	public static TallDoorsCommonProxy proxy;

	public static int itemID1, itemID2, itemID3, itemID4, itemID5, itemID6,
			itemID7, itemID8, itemID9, itemID10, itemID11, itemID12;
	public static int blockID1, blockID2, blockID3, blockID4, blockID5,
			blockID6, blockID7, blockID8;

	public static Item doorPlacer;
	public static Item drawbridge;
	public static Item connector;
	public static Item destructionHammer;
	public static Item key;
	public static Item mosaicTool;
	public static Item keyMakerPlacer;
	public static Item trapDoor;
	public static Item mosaicTool2;

	public static Item modularDoorPlacer;
	public static Item doorModule;

	public static Item luiviteIngot;

	public static Block drawbridgeWorkbench;
	public static Block keyRedstoneLock;
	public static Block mosaic;
	public static Block mosaicGlass;

	public static Block doorWorkshop;
	public static Block doorAssembly;

	public static Block luiviteOre;
	public static Block iconoStone;
	// MISC
	public static boolean spawnLuivite;

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
				if (ze.getName().contains(
						"assets/talldoors/textures/doorparts/sides/")) {
					String[] a = ze.getName().split(
							"assets/talldoors/textures/doorparts/sides/");
					if (a.length > 1 && a[1].contains(".png")) {
						DoorPartRegistry.texturePaths.put(
								a[1].split("\\.(?=[^\\.]+$)")[0],
								"talldoors:textures/doorparts/sides/" + a[1]);
					}

				}

			}
			zf.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		MosaicIconRegistry.mosaicsIntern = results;

		Configuration config = new Configuration(
				event.getSuggestedConfigurationFile());
		config.load();
		itemID1 = config.get(Configuration.CATEGORY_ITEM, "itemID1", 7100)
				.getInt();
		itemID2 = config.get(Configuration.CATEGORY_ITEM, "itemID2", 7101)
				.getInt();
		itemID3 = config.get(Configuration.CATEGORY_ITEM, "itemID3", 7102)
				.getInt();
		itemID4 = config.get(Configuration.CATEGORY_ITEM, "itemID4", 7103)
				.getInt();
		itemID5 = config.get(Configuration.CATEGORY_ITEM, "itemID5", 7104)
				.getInt();
		itemID6 = config.get(Configuration.CATEGORY_ITEM, "itemID6", 7105)
				.getInt();
		itemID7 = config.get(Configuration.CATEGORY_ITEM, "itemID7", 7106)
				.getInt();
		itemID8 = config.get(Configuration.CATEGORY_ITEM, "itemID8", 7107)
				.getInt();
		itemID9 = config.get(Configuration.CATEGORY_ITEM, "itemID9", 7108)
				.getInt();
		itemID10 = config.get(Configuration.CATEGORY_ITEM, "itemID10", 7109)
				.getInt();
		itemID11 = config.get(Configuration.CATEGORY_ITEM, "itemID11", 7110)
				.getInt();
		itemID12 = config.get(Configuration.CATEGORY_ITEM, "itemID12", 7111)
				.getInt();

		blockID1 = config.get(Configuration.CATEGORY_BLOCK, "blockID1", 860)
				.getInt();
		blockID2 = config.get(Configuration.CATEGORY_BLOCK, "blockID2", 861)
				.getInt();
		blockID3 = config.get(Configuration.CATEGORY_BLOCK, "blockID3", 862)
				.getInt();
		blockID4 = config.get(Configuration.CATEGORY_BLOCK, "blockID4", 863)
				.getInt();
		blockID5 = config.get(Configuration.CATEGORY_BLOCK, "blockID5", 864)
				.getInt();
		blockID6 = config.get(Configuration.CATEGORY_BLOCK, "blockID6", 865)
				.getInt();
		blockID7 = config.get(Configuration.CATEGORY_BLOCK, "blockID7", 866)
				.getInt();
		blockID8 = config.get(Configuration.CATEGORY_BLOCK, "blockID8", 867)
				.getInt();

		spawnLuivite = config.get(Configuration.CATEGORY_GENERAL,
				"spawnLuiviteOre", false).getBoolean(false);
		config.save();
	}

	@EventHandler
	public void load(FMLInitializationEvent event) {

		initializeIDs();
		registerItems();
		registerBlocks();
		registerOre();
		registerIngot();
		registerEntities();
		registerRecipes();
		GameRegistry.registerWorldGenerator(new TallDoorshWorldGen());
		registerDoorParts();
		DoorPartRegistry.initialize();

		proxy.registerRenderers();
		registerTileEntities();

		NetworkRegistry.instance().registerGuiHandler(this,
				new TallDoorsGuiHandler());
	}

	private void registerIngot() {
		GameRegistry.registerItem(luiviteIngot, "luiviteIngot");

		OreDictionary.registerOre("ingotLuivite", new ItemStack(luiviteIngot,
				1, 0));

	}

	private void registerOre() {
		// Luivite
		GameRegistry.registerBlock(this.luiviteOre, OreBaseItemBlock.class,
				"luiviteOre");
		OreDictionary
				.registerOre("oreLuivite", new ItemStack(luiviteOre, 1, 0));
		MinecraftForge.setBlockHarvestLevel(luiviteOre,"pickaxe", 1);
		FurnaceRecipes.smelting().addSmelting(luiviteOre.blockID, 0,
				new ItemStack(TallDoorsBase.luiviteIngot, 1, 0), 0.4F);

	}

	private void registerBlocks() {

		GameRegistry.registerBlock(drawbridgeWorkbench,
				DrawbridgeWorkbenchItemBlock.class, "drawbridgeWorkbench");
		LanguageRegistry.addName(new ItemStack(drawbridgeWorkbench, 1, 0),
				"Drawbridge Workbench");

		GameRegistry.registerBlock(iconoStone, StoneBaseItemBlock.class,
				"iconoStone");
		MinecraftForge.setBlockHarvestLevel(iconoStone, "pickaxe",0);

		GameRegistry.registerBlock(keyRedstoneLock, "keyRedstoneLock");
		LanguageRegistry.addName(new ItemStack(keyRedstoneLock, 1, 0),
				"Redstone Lock");
		GameRegistry.registerBlock(mosaic, "mosaic");
		LanguageRegistry.addName(new ItemStack(mosaic, 1, 0), "Mosaic");
		GameRegistry.registerBlock(mosaicGlass, "mosaicGlass");
		LanguageRegistry.addName(new ItemStack(mosaicGlass, 1, 0),
				"Mosaic Glass");

		GameRegistry.registerBlock(doorWorkshop, "Door Workshop");
		GameRegistry.registerBlock(doorAssembly, "Door Assembly");

	}

	private void registerRecipes() {
		ItemStack door = new ItemStack(Item.doorWood, 1, 0);
		ItemStack hatch = new ItemStack(Block.trapdoor, 1, 0);
		ItemStack fenceGate = new ItemStack(Block.fenceGate, 1);
		ItemStack wood = new ItemStack(Block.planks, 1);
		ItemStack wood2 = new ItemStack(Block.wood, 1);
		ItemStack cobble = new ItemStack(Block.cobblestone, 1);
		ItemStack sand = new ItemStack(Block.sand, 1);
		ItemStack iron = new ItemStack(Item.ingotIron, 1);
		ItemStack string = new ItemStack(Item.silk, 1);
		ItemStack stick = new ItemStack(Item.stick, 1);
		ItemStack redstone = new ItemStack(Item.redstone, 1);
		ItemStack glass = new ItemStack(Block.glass, 1);
		ItemStack glow = new ItemStack(Item.glowstone, 1);
		ItemStack luivite = new ItemStack(TallDoorsBase.luiviteIngot, 1, 0);

		// luivite ingot
		GameRegistry
				.addRecipe(new ItemStack(TallDoorsBase.luiviteIngot, 4, 0),
						new Object[] { "YX", "XZ", 'X', iron, 'Y', redstone,
								'Z', glow });
		GameRegistry
				.addRecipe(new ItemStack(TallDoorsBase.luiviteIngot, 4, 0),
						new Object[] { "ZX", "XY", 'X', iron, 'Y', redstone,
								'Z', glow });

		// mosaic tool
		GameRegistry.addShapedRecipe(new ItemStack(TallDoorsBase.mosaicTool, 1,
				0), new Object[] { "Y Y", "YXY", "X X", 'X', wood, 'Y', iron });
		// mosaic tool 2
		GameRegistry.addShapedRecipe(new ItemStack(TallDoorsBase.mosaicTool2,
				1, 0), new Object[] { "Y Y", "YZY", "X X", 'X', wood, 'Y',
				iron, 'Z', wood2 });

		// mosaic
		GameRegistry.addShapedRecipe(new ItemStack(TallDoorsBase.mosaic, 1, 0),
				new Object[] { "YXY", "XYX", "YXY", 'X', cobble, 'Y', sand });
		// mosaic
		GameRegistry.addShapedRecipe(new ItemStack(TallDoorsBase.mosaicGlass,
				8, 0), new Object[] { "YXY", "XYX", "YXY", 'X', glow, 'Y',
				glass });

		// key maker
		GameRegistry.addShapedRecipe(new ItemStack(
				TallDoorsBase.keyMakerPlacer, 1, 0), new Object[] { "XYX",
				"XYX", "X X", 'X', wood, 'Y', iron });

		// redstone lock
		GameRegistry.addShapedRecipe(new ItemStack(
				TallDoorsBase.keyRedstoneLock, 1, 0), new Object[] { "XYX",
				"XZX", "XYX", 'X', wood, 'Y', iron, 'Z', redstone });

		// Door Module Workbench
		GameRegistry.addShapedRecipe(new ItemStack(TallDoorsBase.doorWorkshop,
				1, 0), new Object[] { "ZYZ", "XXX", "XXX", 'X', wood, 'Y',
				cobble, 'Z', iron });

		// Machine Workbench
		GameRegistry.addShapedRecipe(new ItemStack(TallDoorsBase.doorAssembly,
				1, 0), new Object[] { "ZYZ", "ZXZ", "ZXZ", 'X', wood, 'Y',
				cobble, 'Z', iron });

		// Destruction Hammer
		GameRegistry.addShapedRecipe(new ItemStack(
				TallDoorsBase.destructionHammer, 1, 0), new Object[] { "YYY",
				"YXY", " X ", 'X', stick, 'Y', luivite });

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
		doorPlacer = new DoorPlacer(itemID1);
		connector = new Connector(itemID2);
		drawbridge = new DrawbridgePlacer(itemID3);
		destructionHammer = new DestructionHammer(itemID4);
		key = new Key(itemID5);
		mosaicTool = new MosaicTool(itemID6);
		keyMakerPlacer = new KeyMakerPlacer(itemID7);
		trapDoor = new TrapDoorsPlacer(itemID8);
		mosaicTool2 = new PermanentMosaicTool(itemID9);

		luiviteIngot = new IngotBase(itemID10);
		luiviteOre = new OreBase(blockID5);
		iconoStone = new StoneBase(blockID6);

		modularDoorPlacer = new ModularDoorPlacer(itemID11);

		drawbridgeWorkbench = new DrawbridgeWorkbench(blockID1);
		keyRedstoneLock = new KeyRedstoneLock(blockID2);
		mosaic = new MosaicBlock(blockID3);
		mosaicGlass = new MosaicGlass(blockID4);

		doorWorkshop = new DoorWorkshop(blockID7);
		doorAssembly = new ModuleAssembler(blockID8);
		doorModule = new DoorModule(itemID12);
	}

	private void registerDoorParts() {
		DoorPartRegistry.registerDoorPart("plain", new PlainDoorPartType());
		DoorPartRegistry.registerDoorPart("horizontal_balk",
				new HorizontalBalkPartType());
		DoorPartRegistry.registerDoorPart("double_horizontal",
				new DoubleHorizontalBalkPartType());
		DoorPartRegistry.registerDoorPart("empty", new NullPartType());

		DoorPartRegistry.registerDoorPart("simple_window",
				new SimpleWindowPartType());
		DoorPartRegistry.registerDoorPart("glass_window",
				new GlassWindowPartType());
		DoorPartRegistry.registerDoorPart("glass_window_2",
				new GlassWindow2PartType());
		DoorPartRegistry.registerDoorPart("simple_window_2",
				new SimpleWindow2PartType());
		DoorPartRegistry.registerDoorPart("vertical_balk",
				new VerticalBalkPartType());
		DoorPartRegistry.registerDoorPart("vertical_balk(f_b)",
				new FBVerticalBalkPartType());
		DoorPartRegistry.registerDoorPart("plus_balk", new PlusBalkPartType());
		DoorPartRegistry.registerDoorPart("double_plus_balk",
				new DoublePlusBalkPartType());
		DoorPartRegistry.registerDoorPart("2x_vertical_front",
				new DoubleVerticalFrontBalkPartType());
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
		GameRegistry.registerItem(modularDoorPlacer, "modularDoorPlacer");
		GameRegistry.registerItem(doorModule, "doorModule");
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

		EntityRegistry.registerGlobalEntityID(DoorBase.class, "DoorBase",
				EntityRegistry.findGlobalUniqueEntityId());
		EntityRegistry.registerModEntity(DoorBase.class, "DoorBase", 16,
				TallDoorsBase.instance, 128, 5, true);

		registerDoorPartEntities();

	}

	private void registerDoorPartEntities() {
		EntityRegistry
				.registerModEntity(PlainDoorPartEntity.class,
						"PlainDoorPartEntity", 17, TallDoorsBase.instance, 128,
						5, true);
		EntityRegistry.registerModEntity(HorizontalBalkPartEntity.class,
				"HorizontalDoorPartEntity", 18, TallDoorsBase.instance, 128, 5,
				true);
		EntityRegistry.registerModEntity(DoubleHorizontalBalkPartEntity.class,
				"DoubleHorizontalBalkPartEntity", 19, TallDoorsBase.instance,
				128, 5, true);
		EntityRegistry.registerModEntity(NullPartEntity.class,
				"NullPartEntity", 20, TallDoorsBase.instance, 128, 5, true);
		EntityRegistry.registerModEntity(SimpleWindowPartEntity.class,
				"SimpleWindowPartEntity", 21, TallDoorsBase.instance, 128, 5,
				true);
		EntityRegistry.registerModEntity(GlassWindowPartEntity.class,
				"GlassWindowPartEntity", 22, TallDoorsBase.instance, 128, 5,
				true);
		EntityRegistry.registerModEntity(GlassWindow2PartEntity.class,
				"GlassWindow2PartEntity", 23, TallDoorsBase.instance, 128, 5,
				true);
		EntityRegistry.registerModEntity(SimpleWindow2PartEntity.class,
				"SimpleWindow2PartEntity", 24, TallDoorsBase.instance, 128, 5,
				true);
		EntityRegistry.registerModEntity(VerticalBalkPartEntity.class,
				"VerticalBalkPartEntity", 25, TallDoorsBase.instance, 128, 5,
				true);
		EntityRegistry.registerModEntity(FBVerticalBalkPartEntity.class,
				"FBVerticalBalkPartEntity", 26, TallDoorsBase.instance, 128, 5,
				true);
		EntityRegistry.registerModEntity(PlusBalkPartEntity.class,
				"PlusBalkPartEntity", 27, TallDoorsBase.instance, 128, 5, true);
		EntityRegistry.registerModEntity(DoublePlusBalkPartEntity.class,
				"DoublePlusBalkPartEntity", 28, TallDoorsBase.instance, 128, 5,
				true);
		EntityRegistry.registerModEntity(
				DoubleVerticalFrontBalkPartEntity.class,
				"DoubleVerticalFrontBalkPartEntity", 29,
				TallDoorsBase.instance, 128, 5, true);
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
		GameRegistry
				.registerTileEntity(
						tektor.minecraft.talldoors.doorworkshop.entity.DoorModuleWorkbenchTileEntity.class,
						"doorWorkshop");
		GameRegistry
				.registerTileEntity(
						tektor.minecraft.talldoors.doorworkshop.entity.ModuleAssemblerTileEntity.class,
						"doorAssembly");
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {

	}

}
