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
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraftforge.common.config.Configuration;
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
import tektor.minecraft.talldoors.doorworkshop.doorparttypes.windows.HalfedGlassWindowPartType;
import tektor.minecraft.talldoors.doorworkshop.doorparttypes.windows.HorHalfedGlassWindowPartType;
import tektor.minecraft.talldoors.doorworkshop.doorparttypes.windows.SimpleWindow2PartType;
import tektor.minecraft.talldoors.doorworkshop.doorparttypes.windows.SimpleWindowPartType;
import tektor.minecraft.talldoors.doorworkshop.doorparttypes.windows.watchmen.SmallWatchmanWindowType;
import tektor.minecraft.talldoors.doorworkshop.doorparttypes.windows.watchmen.WatchmanWindowType;
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
import tektor.minecraft.talldoors.doorworkshop.entity.doorparts.windows.HalfedGlassWindowPartEntity;
import tektor.minecraft.talldoors.doorworkshop.entity.doorparts.windows.HorHalfedGlassWindowPartEntity;
import tektor.minecraft.talldoors.doorworkshop.entity.doorparts.windows.SimpleWindow2PartEntity;
import tektor.minecraft.talldoors.doorworkshop.entity.doorparts.windows.SimpleWindowPartEntity;
import tektor.minecraft.talldoors.doorworkshop.entity.doorparts.windows.watchmen.SmallWatchmanWindowEntity;
import tektor.minecraft.talldoors.doorworkshop.entity.doorparts.windows.watchmen.WatchmanWindowEntity;
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
import tektor.minecraft.talldoors.packet.PacketPipeline;
import tektor.minecraft.talldoors.services.MosaicIconRegistry;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@Mod(modid = "TallDoors", name = "TallDoors", version = "0.4.5")
public class TallDoorsBase {

	// instance
	@Instance("TallDoors")
	public static TallDoorsBase instance;

	// Says where the client and server 'proxy' code is loaded.
	@SidedProxy(clientSide = "tektor.minecraft.talldoors.client.TallDoorsClientProxy", serverSide = "tektor.minecraft.talldoors.TallDoorsCommonProxy")
	public static TallDoorsCommonProxy proxy;

	// Network
	public static final PacketPipeline packetPipeline = new PacketPipeline();

	// CreativeTab
	public static CreativeTabs tabTallDoors = new CreativeTabs("tallDoors") {
		@Override
		@SideOnly(Side.CLIENT)
		public Item getTabIconItem() {
			return TallDoorsBase.modularDoorPlacer;
		}
	};

	//ITEMS
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

	
	//BLOCKS
	public static Block luiviteOre;
	public static Block iconoStone;
	
	public static Block drawbridgeWorkbench;
	public static Block keyRedstoneLock;
	public static Block mosaic;
	public static Block mosaicGlass;

	public static Block doorWorkshop;
	public static Block doorAssembly;

	
	//MISC
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
			Enumeration<?> e = zf.entries();
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
				if (ze.getName().contains("assets/talldoors/textures/doorparts/sides/"))
				{
					String[] a = ze.getName().split(
							"assets/talldoors/textures/doorparts/sides/");
					if (a.length > 1 && a[1].contains(".png")) {
						DoorPartRegistry.texturePaths.put(a[1].split("\\.(?=[^\\.]+$)")[0],"talldoors:textures/doorparts/sides/"+a[1]);
					}
					
				}
				
			}
			zf.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		MosaicIconRegistry.mosaicsIntern = results;

		Configuration config = new Configuration(event.getSuggestedConfigurationFile());
		config.load();
		
		spawnLuivite = config.get(Configuration.CATEGORY_GENERAL, "spawnLuiviteOre", false).getBoolean(false);
		
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
		
		GameRegistry.registerWorldGenerator(new TallDoorshWorldGen(), 0);
		registerDoorParts();
		DoorPartRegistry.initialize();
		proxy.registerRenderers();
		registerTileEntities();

		NetworkRegistry.INSTANCE.registerGuiHandler(this,
				new TallDoorsGuiHandler());

	}

	private void registerIngot() {
		GameRegistry.registerItem(luiviteIngot, "luiviteIngot");

		OreDictionary.registerOre("ingotLuivite", new ItemStack(luiviteIngot,
				1, 0));

	}

	private void registerOre() {
		//Luivite
		GameRegistry.registerBlock(luiviteOre, OreBaseItemBlock.class,
		"luiviteOre");
		OreDictionary.registerOre("oreLuivite", new ItemStack(luiviteOre, 1,
		0));
		FurnaceRecipes.smelting().func_151394_a(new ItemStack(
				TallDoorsBase.luiviteOre,1, 0),
				new ItemStack(TallDoorsBase.luiviteIngot, 1, 0), 0.4F);

	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {


	}

	@EventHandler
	public void initialise(FMLInitializationEvent evt) {
		packetPipeline.initalise();
	}

	@EventHandler
	public void postInitialise(FMLPostInitializationEvent evt) {
		packetPipeline.postInitialise();
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

		luiviteIngot = new IngotBase();
		luiviteOre = new OreBase();
		iconoStone = new StoneBase();

		modularDoorPlacer = new ModularDoorPlacer();

		drawbridgeWorkbench = new DrawbridgeWorkbench();
		keyRedstoneLock = new KeyRedstoneLock();
		mosaic = new MosaicBlock();
		mosaicGlass = new MosaicGlass();

		doorWorkshop = new DoorWorkshop();
		doorAssembly = new ModuleAssembler();
		doorModule = new DoorModule();

	}

	private void registerDoorParts() {
		DoorPartRegistry.registerDoorPart("plain", new PlainDoorPartType());
		DoorPartRegistry.registerDoorPart("horizontal_balk",
				new HorizontalBalkPartType());
		DoorPartRegistry.registerDoorPart("double_horizontal",
				new DoubleHorizontalBalkPartType());
		DoorPartRegistry.registerDoorPart("empty", new NullPartType());
		DoorPartRegistry.registerDoorPart("simple_window", new SimpleWindowPartType());
		DoorPartRegistry.registerDoorPart("glass_window", new GlassWindowPartType());
		DoorPartRegistry.registerDoorPart("glass_window_2", new GlassWindow2PartType());
		DoorPartRegistry.registerDoorPart("simple_window_2", new SimpleWindow2PartType());
		DoorPartRegistry.registerDoorPart("vertical_balk", new VerticalBalkPartType());
		DoorPartRegistry.registerDoorPart("vertical_balk(f_b)", new FBVerticalBalkPartType());
		DoorPartRegistry.registerDoorPart("plus_balk", new PlusBalkPartType());
		DoorPartRegistry.registerDoorPart("double_plus_balk", new DoublePlusBalkPartType());
		
		DoorPartRegistry.registerDoorPart("2x_vertical_front", new DoubleVerticalFrontBalkPartType());
		DoorPartRegistry.registerDoorPart("vert_halfed_glass", new HalfedGlassWindowPartType());
		DoorPartRegistry.registerDoorPart("hor_halfed_glass", new HorHalfedGlassWindowPartType());
		
		DoorPartRegistry.registerDoorPart("watchman_window", new WatchmanWindowType());
		DoorPartRegistry.registerDoorPart("small_watchman_window", new SmallWatchmanWindowType());
	}

	private void registerBlocks() {

		GameRegistry.registerBlock(drawbridgeWorkbench,
				DrawbridgeWorkbenchItemBlock.class, "drawbridgeWorkbench");
		GameRegistry.registerBlock(iconoStone, StoneBaseItemBlock.class, "iconoStone");
		
		GameRegistry.registerBlock(keyRedstoneLock, "keyRedstoneLock");
		GameRegistry.registerBlock(mosaic, "mosaic");
		GameRegistry.registerBlock(mosaicGlass, "mosaicGlass");

		GameRegistry.registerBlock(doorWorkshop, "Door Workshop");
		GameRegistry.registerBlock(doorAssembly, "Door Assembly");
	}

	private void registerItems() {
				
		GameRegistry.registerItem(doorPlacer, "doorplacer");
		GameRegistry.registerItem(connector, "connector");
		GameRegistry.registerItem(drawbridge, "drawbridge");
		GameRegistry.registerItem(destructionHammer, "destructionHammer");
		GameRegistry.registerItem(key, "key");
		GameRegistry.registerItem(mosaicTool, "mosaicTool");
		GameRegistry.registerItem(mosaicTool2, "mosaicTool2");
		GameRegistry.registerItem(keyMakerPlacer, "keyMakerPlacer");
		GameRegistry.registerItem(trapDoor, "trapDoorPlacer");

		GameRegistry.registerItem(modularDoorPlacer, "modularDoorPlacer");
		GameRegistry.registerItem(doorModule, "doorModule");

	}

	private void registerEntities() {
		EntityRegistry.registerModEntity(EntranceDoor1.class, "EntranceDoor1",
				0, TallDoorsBase.instance, 120, 5, true);
		EntityRegistry.registerModEntity(EntranceDoor2.class, "EntranceDoor2",
				1, TallDoorsBase.instance, 120, 5, true);
		EntityRegistry.registerModEntity(EntranceDoor3.class, "EntranceDoor3",
				2, TallDoorsBase.instance, 120, 5, true);
		EntityRegistry.registerModEntity(FenceGate1.class, "FenceGate1", 3,
				TallDoorsBase.instance, 120, 5, true);
		EntityRegistry.registerModEntity(EntityConnector.class,
				"EntityConnector", 4, TallDoorsBase.instance, 120, 5, true);
		
		EntityRegistry.registerModEntity(MetalEntranceDoor1.class,
				"MetalEntranceDoor1", 5, TallDoorsBase.instance, 120, 5, true);
		EntityRegistry.registerModEntity(DarkMetalEntranceDoor1.class,
				"DarkMetalEntranceDoor1", 6, TallDoorsBase.instance, 120, 5,
				true);
		EntityRegistry.registerModEntity(DrawbridgeBase.class,
				"DrawbridgeBase", 7, TallDoorsBase.instance, 120, 5, true);
		EntityRegistry.registerModEntity(DrawbridgeMachine.class,
				"DrawbridgeMachine", 8, TallDoorsBase.instance, 120, 5, true);

		EntityRegistry.registerModEntity(DarkMetalEntranceDoor2.class,
				"DarkMetalEntranceDoor2", 9, TallDoorsBase.instance, 120, 5,
				true);

		EntityRegistry.registerModEntity(DarkMetalEntranceDoor3.class,
				"DarkMetalEntranceDoor3", 10, TallDoorsBase.instance, 120, 5,
				true);
		EntityRegistry.registerModEntity(MetalEntranceDoor2.class,
				"MetalEntranceDoor2", 11, TallDoorsBase.instance, 120, 5, true);
		EntityRegistry.registerModEntity(MetalEntranceDoor3.class,
				"MetalEntranceDoor3", 12, TallDoorsBase.instance, 120, 5, true);
		EntityRegistry.registerModEntity(FakeEntity.class, "FakeEntity", 13,
				TallDoorsBase.instance, 120, 5, true);
		EntityRegistry.registerModEntity(KeyMaker.class, "KeyMaker", 14,
				TallDoorsBase.instance, 120, 5, true);
		EntityRegistry.registerModEntity(TrapDoor.class, "TrapDoor", 15,
				TallDoorsBase.instance, 120, 5, true);
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
				"SimpleWindowPartEntity", 21, TallDoorsBase.instance, 128, 5, true);
		EntityRegistry.registerModEntity(GlassWindowPartEntity.class,
				"GlassWindowPartEntity", 22, TallDoorsBase.instance, 128, 5, true);
		EntityRegistry.registerModEntity(GlassWindow2PartEntity.class,
				"GlassWindow2PartEntity", 23, TallDoorsBase.instance, 128, 5, true);
		EntityRegistry.registerModEntity(SimpleWindow2PartEntity.class,
				"SimpleWindow2PartEntity", 24, TallDoorsBase.instance, 128, 5, true);
		EntityRegistry.registerModEntity(VerticalBalkPartEntity.class,
				"VerticalBalkPartEntity", 25, TallDoorsBase.instance, 128, 5, true);
		EntityRegistry.registerModEntity(FBVerticalBalkPartEntity.class,
				"FBVerticalBalkPartEntity", 26, TallDoorsBase.instance, 128, 5, true);
		EntityRegistry.registerModEntity(PlusBalkPartEntity.class,
				"PlusBalkPartEntity", 27, TallDoorsBase.instance, 128, 5, true);
		EntityRegistry.registerModEntity(DoublePlusBalkPartEntity.class,
				"DoublePlusBalkPartEntity", 28, TallDoorsBase.instance, 128, 5, true);
		EntityRegistry.registerModEntity(DoubleVerticalFrontBalkPartEntity.class,
				"DoubleVerticalFrontBalkPartEntity", 29, TallDoorsBase.instance, 128, 5, true);
		EntityRegistry.registerModEntity(HalfedGlassWindowPartEntity.class,
				"HalfedGlassWindowPartEntity", 30, TallDoorsBase.instance, 128, 5, true);
		EntityRegistry.registerModEntity(HorHalfedGlassWindowPartEntity.class,
				"HorHalfedGlassWindowPartEntity", 31, TallDoorsBase.instance, 128, 5, true);
		EntityRegistry.registerModEntity(WatchmanWindowEntity.class,
				"WatchmanWindowEntity", 32, TallDoorsBase.instance, 128, 5, true);
		EntityRegistry.registerModEntity(SmallWatchmanWindowEntity.class,
				"SmallWatchmanWindowEntity", 33, TallDoorsBase.instance, 128, 5, true);
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

	private void registerRecipes() {
		ItemStack door = new ItemStack(Items.wooden_door, 1, 0);
		new ItemStack(Blocks.trapdoor, 1, 0);
		ItemStack fenceGate = new ItemStack(Blocks.fence_gate, 1);
		ItemStack wood = new ItemStack(Blocks.planks, 1);
		ItemStack wood2 = new ItemStack(Blocks.log, 1);
		ItemStack cobble = new ItemStack(Blocks.cobblestone, 1);
		ItemStack sand = new ItemStack(Blocks.sand, 1);
		ItemStack iron = new ItemStack(Items.iron_ingot, 1);
		ItemStack string = new ItemStack(Items.string, 1);
		ItemStack stick = new ItemStack(Items.stick, 1);
		ItemStack redstone = new ItemStack(Items.redstone, 1);
		ItemStack glass = new ItemStack(Blocks.glass, 1);
		ItemStack glow = new ItemStack(Items.glowstone_dust, 1);
		
		ItemStack luivite = new ItemStack(TallDoorsBase.luiviteIngot,1,0);

		//luivite ingot
		GameRegistry.addRecipe(new ItemStack(TallDoorsBase.luiviteIngot, 4,
				0), new Object[] { "YX", "XZ", 'X', iron, 'Y', redstone, 'Z', glow });
		GameRegistry.addRecipe(new ItemStack(TallDoorsBase.luiviteIngot, 4,
				0), new Object[] { "ZX", "XY", 'X', iron, 'Y', redstone, 'Z', glow });
		
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

		// Door Module Workbench
		GameRegistry.addShapedRecipe(new ItemStack(TallDoorsBase.doorWorkshop,
				1, 0), new Object[] { "ZYZ", "XXX", "XXX", 'X', wood, 'Y',
				cobble, 'Z', iron });

		// Machine Workbench
		GameRegistry.addShapedRecipe(new ItemStack(TallDoorsBase.doorAssembly,
				1, 0), new Object[] { "ZYZ", "ZXZ", "ZXZ", 'X', wood, 'Y',
				cobble, 'Z', iron });

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

}
