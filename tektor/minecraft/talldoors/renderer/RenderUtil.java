package tektor.minecraft.talldoors.renderer;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.Entity;

public class RenderUtil {

	public static void renderOutline(double height, double width,
			double depth, double angle, Entity base)
	{
		Tessellator tess = Tessellator.instance;
		
		tess.startDrawingQuads();
		
		double a, b, e, d;
		a = Math.cos(Math.toRadians(90 - angle)) * height;
		b = Math.sin(Math.toRadians(90 - angle)) * height;
		d = Math.cos(Math.toRadians(angle)) * depth;
		e = Math.sin(Math.toRadians(angle)) * depth;
		
		tess.addVertexWithUV(0, b, 0 - a, depth, 0);
		tess.addVertexWithUV(0, b + e, d - a, depth, width);
		tess.addVertexWithUV(width, b + e, d - a, 0, width);
		tess.addVertexWithUV(width, b, -a, 0, 0);
		// right
		tess.addVertexWithUV(0, 0, 0, 0, 0);
		tess.addVertexWithUV(0, e, d, height, 0);
		tess.addVertexWithUV(0, b + e, d - a, height, depth);
		tess.addVertexWithUV(0, b, -a, 0, depth);
		// bottom
		tess.addVertexWithUV(0, 0, 0, depth, 0);
		tess.addVertexWithUV(width, 0, 0, 0, 0);
		tess.addVertexWithUV(width, e, d, 0, width);
		tess.addVertexWithUV(0, e, d, depth, width);
		// left
		tess.addVertexWithUV(width, 0, 0, 0, 0);
		tess.addVertexWithUV(width, b, -a, 0, height);
		tess.addVertexWithUV(width, b + e, d - a, depth, height);
		tess.addVertexWithUV(width, e, d, depth, 0);
		
		tess.draw();
	}
	public static void renderFrontBack(double height, double width,
			double depth, double angle, Entity base) {
		
		Tessellator tess = Tessellator.instance;
		
		tess.startDrawingQuads();
		
		double a, b, e, d;
		a = Math.cos(Math.toRadians(90 - angle)) * height;
		b = Math.sin(Math.toRadians(90 - angle)) * height;
		d = Math.cos(Math.toRadians(angle)) * depth;
		e = Math.sin(Math.toRadians(angle)) * depth;

		// front
		tess.addVertexWithUV(0, 0, 0, width, 0.0);
		tess.addVertexWithUV(0, b, 0 - a, width, height);
		tess.addVertexWithUV(width, b, 0 - a, 0, height);
		tess.addVertexWithUV(width, 0, 0, 0, 0);
		// back
		tess.addVertexWithUV(0, e, d, width, 0.0);
		tess.addVertexWithUV(width, e, d, 0, 0);
		tess.addVertexWithUV(width, e + b, d - a, 0, height);
		tess.addVertexWithUV(0, e + b, d - a, width, height);
		
		tess.draw();
	}
	
	public static void renderCuboid(Entity base, double width,
			double height, double depth, double angle) {
		
		Tessellator tess = Tessellator.instance;
		
		tess.startDrawingQuads();
		
		double a, b, e, d;
		a = Math.cos(Math.toRadians(90 - angle)) * height;
		b = Math.sin(Math.toRadians(90 - angle)) * height;
		d = Math.cos(Math.toRadians(angle)) * depth;
		e = Math.sin(Math.toRadians(angle)) * depth;

		// front
		tess.addVertexWithUV(0, 0, 0, width, 0.0);
		tess.addVertexWithUV(0, b, 0 - a, width, height);
		tess.addVertexWithUV(width, b, 0 - a, 0, height);
		tess.addVertexWithUV(width, 0, 0, 0, 0);
		// top
		tess.addVertexWithUV(0, b, 0 - a, depth, 0);
		tess.addVertexWithUV(0, b + e, d - a, depth, width);
		tess.addVertexWithUV(width, b + e, d - a, 0, width);
		tess.addVertexWithUV(width, b, -a, 0, 0);
		// right
		tess.addVertexWithUV(0, 0, 0, 0, 0);
		tess.addVertexWithUV(0, e, d, height, 0);
		tess.addVertexWithUV(0, b + e, d - a, height, depth);
		tess.addVertexWithUV(0, b, -a, 0, depth);
		// bottom
		tess.addVertexWithUV(0, 0, 0, depth, 0);
		tess.addVertexWithUV(width, 0, 0, 0, 0);
		tess.addVertexWithUV(width, e, d, 0, width);
		tess.addVertexWithUV(0, e, d, depth, width);
		// left
		tess.addVertexWithUV(width, 0, 0, 0, 0);
		tess.addVertexWithUV(width, b, -a, 0, height);
		tess.addVertexWithUV(width, b + e, d - a, depth, height);
		tess.addVertexWithUV(width, e, d, depth, 0);
		// back
		tess.addVertexWithUV(0, e, d, width, 0.0);
		tess.addVertexWithUV(width, e, d, 0, 0);
		tess.addVertexWithUV(width, e + b, d - a, 0, height);
		tess.addVertexWithUV(0, e + b, d - a, width, height);
		
		tess.draw();
	}

}
