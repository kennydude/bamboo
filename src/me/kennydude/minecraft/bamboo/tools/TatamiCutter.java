package me.kennydude.minecraft.bamboo.tools;

import me.kennydude.minecraft.bamboo.BambooMod;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * Created by kennydude on 21/06/13.
 */
public class TatamiCutter extends Item {
    public TatamiCutter(int par1) {
        super(par1);
    }

    @Override
    public void registerIcons(IconRegister iconRegister) {
        this.itemIcon = iconRegister.registerIcon("bamboo:cutter");
    }

    @Override
    public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer player, World world, int par4, int par5, int par6, int par7, float par8, float par9, float par10) {
        int blockId = world.getBlockId(par4, par5, par6);
        System.out.println("onItemUse by a Tatami Mat Cutter: " + blockId);
        if(blockId == BambooMod.tatamiMat.blockID){
            player.openGui(BambooMod.instance, 0, world, par4, par5, par6);
            return true;
        }
        return false;
    }
}
