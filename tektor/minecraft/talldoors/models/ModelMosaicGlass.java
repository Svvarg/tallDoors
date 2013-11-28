package tektor.minecraft.talldoors.models;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.Tessellator;

@SideOnly(Side.CLIENT)
public class ModelMosaicGlass extends ModelBase{
	
    /** The board on a sign that has the writing on it. */
    public ModelRenderer signBoard = new ModelRenderer(this, 0, 0);


    public ModelMosaicGlass()
    {
        this.signBoard.addBox(0F, 0F, 0F, 16, 16, 16, 0.0F);
    }

    /**
     * Renders the sign model through TileEntitySignRenderer
     */
    public void renderSign()
    {
        this.signBoard.render(0.0625F);
    }

}
