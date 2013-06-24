package me.kennydude.minecraft.bamboo.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import me.kennydude.minecraft.bamboo.BambooMod;
import me.kennydude.minecraft.bamboo.Constants;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

/**
 * Created by kennydude on 21/06/13.
 */
public class TatamiMat extends Block {
    @SideOnly(Side.CLIENT)
    Icon[] iconArray;

    public TatamiMat(int par1, Material par2Material) {
        super(par1, par2Material);
        this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.125F, 1.0F);
    }

    public static final int META_NONE = 0;
    public static final int META_TOP = 1;
    public static final int META_BOTTOM = 2;
    public static final int META_LEFT = 3;
    public static final int META_LEFT_TOP = 4;
    public static final int META_LEFT_BOTTOM = 5;
    public static final int META_RIGHT = 6;
    public static final int META_RIGHT_TOP = 7;
    public static final int META_RIGHT_BOTTOM = 8;

    @SideOnly(Side.CLIENT)
    @Override
    public void registerIcons(IconRegister iconRegister) {
        iconArray = new Icon[]{
                iconRegister.registerIcon("bamboo:matA"),
                iconRegister.registerIcon("bamboo:matT"),
                iconRegister.registerIcon("bamboo:matB"),
                iconRegister.registerIcon("bamboo:matL"),
                iconRegister.registerIcon("bamboo:matLT"),
                iconRegister.registerIcon("bamboo:matLB"),
                iconRegister.registerIcon("bamboo:matR"),
                iconRegister.registerIcon("bamboo:matRT"),
                iconRegister.registerIcon("bamboo:matRB")
        };
        this.blockIcon = iconRegister.registerIcon("bamboo:matA");
    }

    public boolean isOpaqueCube() {
        return false;
    }

    public boolean renderAsNormalBlock() {
        return false;
    }

    @Override
    public void onBlockAdded(World world, int x, int y, int z) {
        System.out.println("TatamiMat: onBlockAdded");
        //world.setBlockMetadataWithNotify(x, y, z, 0, Constants.SEND_BLOCK_CHANGE);
    }

    /*
    @Override
    public int getRenderType(){
        // We want a rail type as that is the closest thing right now
        return Constants.RENDER_TYPE_RAIL;
    }
    */

    /*
    @Override
    public void onBlockAdded(World par1World, int par2, int par3, int par4)
    {
        if(!par1World.isRemote){
            updateMatShape(par1World, par2, par3, par4);
        }
    }

    /**
     * This is the real magic. This updates our texture based on what we want to do
     *
    private void updateMatShape(World par1World, int x, int y, int z) {

    }
    */

    /**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
    @SideOnly(Side.CLIENT)
    public Icon getIcon(int side, int metadata) {
        // System.out.println("TatamiMat getIcon: " + metadata);
        return iconArray[metadata];
    }
}
