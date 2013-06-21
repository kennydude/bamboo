package me.kennydude.minecraft.bamboo.blocks;

import me.kennydude.minecraft.bamboo.ClientBambooProxy;
import net.minecraft.block.BlockFence;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.world.IBlockAccess;

/**
 * Created by kennydude on 21/06/13.
 */
public class BambooPole extends BlockFence {
    public BambooPole(int par1, String par2Str, Material par3Material) {
        super(par1, par2Str, par3Material);

        // This should be the bounds to a pole
        float f = 0.375F;
        float f1 = 0.625F;
        this.setBlockBounds(f, 0.0F, f, f1, 1.0F, f1);
    }

    public boolean canConnectFenceTo(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
        return false; // We do not connect to anything. Sorry
    }

    @Override
    public void registerIcons(IconRegister iconRegister) {
        this.blockIcon = iconRegister.registerIcon("bamboo:pole");
    }

    /*
    @Override
    public int getRenderType(){
        // We want to be a circle please :)
        return ClientBambooProxy.poleRendererId;
    }
    */
}
