package me.kennydude.minecraft.bamboo.blocks;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import me.kennydude.minecraft.bamboo.ClientBambooProxy;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.IBlockAccess;

/**
 * Created by kennydude on 21/06/13.
 */
public class BambooPoleRenderer implements ISimpleBlockRenderingHandler {
    @Override
    public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {

    }

    @Override
    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
        Tessellator var10 = Tessellator.instance;


        return false;
    }

    @Override
    public boolean shouldRender3DInInventory() {
        return true;
    }

    @Override
    public int getRenderId() {
        return ClientBambooProxy.poleRendererId;
    }
}
