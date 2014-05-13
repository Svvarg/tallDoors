package tektor.minecraft.talldoors;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import cpw.mods.fml.common.IWorldGenerator;

public class TallDoorshWorldGen implements IWorldGenerator {

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world,
			IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		if(TallDoorsBase.spawnLuivite)
		{
			for (int k = 0; k < 8; k++) {
				int firstBlockXCoord = chunkX * 16 + random.nextInt(16);
				int firstBlockYCoord = 10 + random.nextInt(64);
				int firstBlockZCoord = chunkZ * 16 + random.nextInt(16);
				(new WorldGenMinable(TallDoorsBase.iconoStone,0,23, Blocks.stone)).generate(
				world, random, firstBlockXCoord, firstBlockYCoord,
				firstBlockZCoord);
				(new WorldGenMinable(TallDoorsBase.luiviteOre,0,6, TallDoorsBase.iconoStone)).generate(
						world, random, firstBlockXCoord, firstBlockYCoord,
						firstBlockZCoord);
				}
		}

	}

}
