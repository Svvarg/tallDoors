package tektor.minecraft.talldoors.client;

import cpw.mods.fml.client.registry.RenderingRegistry;
import tektor.minecraft.talldoors.renderer.RenderEntranceDoor1;
import tektor.minecraft.talldoors.TallDoorsCommonProxy;
import tektor.minecraft.talldoors.entities.EntranceDoor1;

public class TallDoorsClientProxy extends TallDoorsCommonProxy{

	@Override
	public void registerRenderers() {
		RenderingRegistry.registerEntityRenderingHandler(EntranceDoor1.class, new RenderEntranceDoor1());
		
		
	}
}
