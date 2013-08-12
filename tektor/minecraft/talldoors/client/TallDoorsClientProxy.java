package tektor.minecraft.talldoors.client;

import cpw.mods.fml.client.registry.RenderingRegistry;
import tektor.minecraft.talldoors.renderer.RenderDarkMetalEntranceDoor1;
import tektor.minecraft.talldoors.renderer.RenderEntranceDoor1;
import tektor.minecraft.talldoors.renderer.RenderEntranceDoor2;
import tektor.minecraft.talldoors.renderer.RenderEntranceDoor3;
import tektor.minecraft.talldoors.renderer.RenderFenceGate1;
import tektor.minecraft.talldoors.renderer.RenderMetalEntranceDoor1;
import tektor.minecraft.talldoors.renderer.RenderRopeConnector;
import tektor.minecraft.talldoors.TallDoorsCommonProxy;
import tektor.minecraft.talldoors.entities.DarkMetalEntranceDoor1;
import tektor.minecraft.talldoors.entities.EntityConnector;
import tektor.minecraft.talldoors.entities.EntranceDoor1;
import tektor.minecraft.talldoors.entities.EntranceDoor2;
import tektor.minecraft.talldoors.entities.EntranceDoor3;
import tektor.minecraft.talldoors.entities.FenceGate1;
import tektor.minecraft.talldoors.entities.MetalEntranceDoor1;

public class TallDoorsClientProxy extends TallDoorsCommonProxy{

	@Override
	public void registerRenderers() {
		RenderingRegistry.registerEntityRenderingHandler(EntranceDoor1.class, new RenderEntranceDoor1());
		RenderingRegistry.registerEntityRenderingHandler(EntranceDoor2.class, new RenderEntranceDoor2());
		RenderingRegistry.registerEntityRenderingHandler(EntranceDoor3.class, new RenderEntranceDoor3());
		RenderingRegistry.registerEntityRenderingHandler(FenceGate1.class, new RenderFenceGate1());
		RenderingRegistry.registerEntityRenderingHandler(EntityConnector.class, new RenderRopeConnector());
		RenderingRegistry.registerEntityRenderingHandler(MetalEntranceDoor1.class, new RenderMetalEntranceDoor1());
		RenderingRegistry.registerEntityRenderingHandler(DarkMetalEntranceDoor1.class, new RenderDarkMetalEntranceDoor1());
		
		
	}
}
