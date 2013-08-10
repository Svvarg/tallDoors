package tektor.minecraft.talldoors.client;

import cpw.mods.fml.client.registry.RenderingRegistry;
import tektor.minecraft.talldoors.renderer.RenderEntranceDoor1;
import tektor.minecraft.talldoors.renderer.RenderEntranceDoor2;
import tektor.minecraft.talldoors.renderer.RenderEntranceDoor3;
import tektor.minecraft.talldoors.renderer.RenderFenceGate1;
import tektor.minecraft.talldoors.TallDoorsCommonProxy;
import tektor.minecraft.talldoors.entities.EntranceDoor1;
import tektor.minecraft.talldoors.entities.EntranceDoor2;
import tektor.minecraft.talldoors.entities.EntranceDoor3;
import tektor.minecraft.talldoors.entities.FenceGate1;

public class TallDoorsClientProxy extends TallDoorsCommonProxy{

	@Override
	public void registerRenderers() {
		RenderingRegistry.registerEntityRenderingHandler(EntranceDoor1.class, new RenderEntranceDoor1());
		RenderingRegistry.registerEntityRenderingHandler(EntranceDoor2.class, new RenderEntranceDoor2());
		RenderingRegistry.registerEntityRenderingHandler(EntranceDoor3.class, new RenderEntranceDoor3());
		RenderingRegistry.registerEntityRenderingHandler(FenceGate1.class, new RenderFenceGate1());
		
		
	}
}
