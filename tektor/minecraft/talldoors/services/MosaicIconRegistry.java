package tektor.minecraft.talldoors.services;

import java.util.HashMap;
import java.util.List;
import java.util.Random;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;

public class MosaicIconRegistry {
	
	public static HashMap<String,IIcon> icons = new HashMap<String,IIcon>();
	public static List<String> mosaicsIntern;
	
	public MosaicIconRegistry()
	{
		
	}
	
	public static void register(IIconRegister reg)
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

	public static IIcon getIcon(String icon) {
		return (IIcon) icons.get(icon);
	}
	
	public static String getRandom()
	{	Random r = new Random();
		int i = r.nextInt(icons.size());
		return (String) icons.keySet().toArray()[i];
	}

}
