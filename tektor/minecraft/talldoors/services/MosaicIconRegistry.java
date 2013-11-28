package tektor.minecraft.talldoors.services;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.util.Icon;

public class MosaicIconRegistry {
	
	public static HashMap icons = new HashMap();
	public static List<String> mosaicsIntern;
	
	public MosaicIconRegistry()
	{
		
	}
	
	public static void register(IconRegister reg)
	{
		
		try {
		    
			for(String fil : mosaicsIntern)
			{
				icons.put(fil.split("\\.(?=[^\\.]+$)")[0], reg.registerIcon("tallDoors:mosaic/"+fil.split("\\.(?=[^\\.]+$)")[0]));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	public static Icon getIcon(String icon) {
		return (Icon) icons.get(icon);
	}
	
	public static String getRandom()
	{	Random r = new Random();
		int i = r.nextInt(icons.size());
		return (String) icons.keySet().toArray()[i];
	}

}
