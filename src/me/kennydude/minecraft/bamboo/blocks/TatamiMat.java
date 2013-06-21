package me.kennydude.minecraft.bamboo.blocks;

import me.kennydude.minecraft.bamboo.Constants;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.world.World;

/**
 * Created by kennydude on 21/06/13.
 */
public class TatamiMat extends Block {
    public TatamiMat(int par1, Material par2Material) {
        super(par1, par2Material);
        this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.125F, 1.0F);
    }

    @Override
    public void registerIcons(IconRegister iconRegister) {
        this.blockIcon = iconRegister.registerIcon("bamboo:matA");
    }

    public boolean isOpaqueCube(){
        return false;
    }

    public boolean renderAsNormalBlock(){
        return false;
    }

    /*
    @Override
    public int getRenderType(){
        // We want a rail type as that is the closest thing right now
        return Constants.RENDER_TYPE_RAIL;
    }
    */

    @Override
    public void onBlockAdded(World par1World, int par2, int par3, int par4)
    {
        if(!par1World.isRemote){
            updateMatShape(par1World, par2, par3, par4);
        }
    }

    /**
     * This is the real magic. This updates our texture based on what we want to do
     */
    private void updateMatShape(World par1World, int x, int y, int z) {

    }
}
