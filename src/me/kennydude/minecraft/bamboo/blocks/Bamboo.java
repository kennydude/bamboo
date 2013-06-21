package me.kennydude.minecraft.bamboo.blocks;

import cpw.mods.fml.client.registry.RenderingRegistry;
import me.kennydude.minecraft.bamboo.Constants;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.world.World;

import java.util.Random;

/**
 * This is a piece of bamboo
 *
 * Created by kennydude on 21/06/13.
 */
public class Bamboo extends Block {
    public Bamboo(int par1, int texture, Material par2Material) {
        super(par1, par2Material);
        float F = 0.300F;
        setBlockBounds(0.5F - F, 0.0F, 0.5F - F, 0.5F + F, 1.0F, 0.5F + F);

        setTickRandomly(true);
    }

    @Override
    public void registerIcons(IconRegister iconRegister) {
        this.blockIcon = iconRegister.registerIcon("bamboo:bamboo");
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    @Override
    public boolean renderAsNormalBlock() {
        return false;
    }

    @Override
    public int getRenderType() {
        return Constants.RENDER_TYPE_CANE;
    }

    @Override
    public int idDropped(int i, Random random, int r2) {
        // We want to drop ourselves!
        return this.blockID;
    }

    @Override
    public void updateTick(World world, int i, int j, int k, Random random) {
        if (world.isAirBlock(i, j + 1, k)) {
            int b;
            for (b = 1; world.getBlockId(i, j - b, k) == blockID; b++) {}
            if (b < 3) {
                int i1 = world.getBlockMetadata(i, j, k);
                if (i1 == 4 ) { // Higher is that number longer it takes to grow
                    world.setBlock(i, j + 1, k, blockID);
                    world.setBlockMetadataWithNotify(i, j, k, 0, Constants.SEND_BLOCK_CHANGE);
                } else {
                    world.setBlockMetadataWithNotify(i, j, k, i1 + 1, Constants.SEND_BLOCK_CHANGE);
                }
            }
        }
    }
}
