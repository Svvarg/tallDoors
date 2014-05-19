package tektor.minecraft.talldoors.doorworkshop.util;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class ModuleTexturePackage implements Serializable{
	
	
	/**
	 * 
	 */
	public static final long serialVersionUID = 123464344512335L;
	public String module;
	public String texture1,texture2;
	public String sideTexture;
	
	private void writeObject(ObjectOutputStream s)
	{
		try {
			s.writeUTF(module);
			s.writeUTF(texture1);
			s.writeUTF(texture2);
			s.writeUTF(sideTexture);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private void readObject(ObjectInputStream s)
	{
		try {
			module = s.readUTF();
			texture1 = s.readUTF();
			texture2 = s.readUTF();
			sideTexture = s.readUTF();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
