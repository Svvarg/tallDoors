package tektor.minecraft.talldoors;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.Configuration;
import tektor.minecraft.talldoors.entities.FenceGate1;
import tektor.minecraft.talldoors.entities.doors_width2.DarkMetalEntranceDoor1;
import tektor.minecraft.talldoors.entities.doors_width2.EntranceDoor1;
import tektor.minecraft.talldoors.entities.doors_width2.EntranceDoor2;
import tektor.minecraft.talldoors.entities.doors_width2.EntranceDoor3;
import tektor.minecraft.talldoors.entities.doors_width2.MetalEntranceDoor1;
import tektor.minecraft.talldoors.entities.drawbridge.EntityConnector;
import tektor.minecraft.talldoors.items.DoorPlacer;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid = "TallDoors", name = "TallDoors", version = "0.1.5")
@NetworkMod(clientSideRequired = true)
public class TallDoorsBase {

	// instance
	@Instance("TallDoors")
	public static TallDoorsBase instance;

	// Says where the client and server 'proxy' code is loaded.
	@SidedProxy(clientSide = "tektor.minecraft.talldoors.client.TallDoorsClientProxy", serverSide = "tektor.minecraft.talldoors.TallDoorsCommonProxy")
	public static TallDoorsCommonProxy proxy;

	public static int itemID1;
	// public static int itemID2;
	public static Item doorPlacer;

	// public static Item connector;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		Configuration config = new Configuration(
				event.getSuggestedConfigurationFile());
		config.load();
		itemID1 = config.get(Configuration.CATEGORY_ITEM, "itemID1", 7100)
				.getInt();
		// itemID2 = config.get(Configuration.CATEGORY_ITEM, "itemID2", 7101)
		// .getInt();
		config.save();
	}

	@EventHandler
	public void load(FMLInitializationEvent event) {
		initializeIDs();
		registerItems();
		registerEntities();
		registerRecipes();

		proxy.registerRenderers();
	}

	private void registerRecipes() {
		ItemStack door = new ItemStack(Item.doorWood, 1, 0);
		ItemStack fenceGate = new ItemStack(Block.fenceGate, 1);
		ItemStack wood = new ItemStack(Block.planks, 1);
		ItemStack cobble = new ItemStack(Block.cobblestone, 1);
		ItemStack iron = new ItemStack(Item.ingotIron, 1);

		// Dark Metal Right 4 high Door
		GameRegistry.addShapedRecipe(new ItemStack(TallDoorsBase.doorPlacer, 1, 10),
				new Object[] { "XXX", "XXY", "XXX", 'X', cobble, 'Y', door });
		// Dark Metal left 4 high
		GameRegistry.addShapedRecipe(new ItemStack(TallDoorsBase.doorPlacer, 1, 11),
				new Object[] { "XXX", "XXX", "YXX", 'X', cobble, 'Y', door });

		// Metal Right 4 high Door
		GameRegistry.addShapedRecipe(new ItemStack(TallDoorsBase.doorPlacer, 1, 8),
				new Object[] { "XXX", "XXY", "XXX", 'X', iron, 'Y', door });
		// Metal left 4 high
		GameRegistry.addShapedRecipe(new ItemStack(TallDoorsBase.doorPlacer, 1, 9),
				new Object[] { "XXX", "XXX", "YXX", 'X', iron, 'Y', door });

		// Right 4 high Door
		GameRegistry.addShapedRecipe(new ItemStack(TallDoorsBase.doorPlacer, 1, 0),
				new Object[] { "XXX", "XXY", "XXX", 'X', wood, 'Y', door });
		// left 4 high
		GameRegistry.addShapedRecipe(new ItemStack(TallDoorsBase.doorPlacer, 1, 1),
				new Object[] { "XXX", "XXX", "YXX", 'X', wood, 'Y', door });
		// right 5 high
		GameRegistry.addShapedRecipe(new ItemStack(TallDoorsBase.doorPlacer, 1, 2),
				new Object[] { "XXX", "XXY", "XXX", 'X', wood, 'Y',
						new ItemStack(TallDoorsBase.doorPlacer, 1, 0) });
		// left 5 high
		GameRegistry.addShapedRecipe(new ItemStack(TallDoorsBase.doorPlacer, 1, 3),
				new Object[] { "XXX", "YXX", "XXX", 'X', wood, 'Y',
						new ItemStack(TallDoorsBase.doorPlacer, 1, 1) });
		// right 6 high
		GameRegistry.addShapedRecipe(new ItemStack(TallDoorsBase.doorPlacer, 1, 4),
				new Object[] { "XXX", "XXY", "XXX", 'X', wood, 'Y',
						new ItemStack(TallDoorsBase.doorPlacer, 1, 2) });
		// left 6 high
		GameRegistry.addShapedRecipe(new ItemStack(TallDoorsBase.doorPlacer, 1, 5),
				new Object[] { "XXX", "YXX", "XXX", 'X', wood, 'Y',
						new ItemStack(TallDoorsBase.doorPlacer, 1, 3) });
		// right fence gate
		GameRegistry
				.addShapedRecipe(new ItemStack(TallDoorsBase.doorPlacer, 1, 6),
						new Object[] { "XXX", "YXX", "XXX", 'X', wood, 'Y',
								fenceGate });
		// left fence gate
		GameRegistry
				.addShapedRecipe(new ItemStack(TallDoorsBase.doorPlacer, 1, 7),
						new Object[] { "XXX", "XXX", "YXX", 'X', wood, 'Y',
								fenceGate });

	}

	private void initializeIDs() {
		doorPlacer = new DoorPlacer(itemID1);
		// connector = new Connector(itemID2);

	}

	private void registerItems() {
		// TODO Auto-generated method stub

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
				"DarkMetalEntranceDoor1", 6, TallDoorsBase.instance, 120, 5, true);

	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {

	}

}
