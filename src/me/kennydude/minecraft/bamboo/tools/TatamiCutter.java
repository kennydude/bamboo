package me.kennydude.minecraft.bamboo.tools;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
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
    public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer player, World world, int x, int y, int z, int par7, float par8, float par9, float par10) {
        if(!world.isRemote) return true;

        int blockId = world.getBlockId(x, y, z);
        int meta = world.getBlockMetadata(x,y,z);

        if(blockId == BambooMod.tatamiMat.blockID){
            System.out.println("onItemUse by a Tatami Mat Cutter: m"+meta+"@" + x + "," + y + "," + z );
            player.openGui(BambooMod.instance, 0, world, x, y, z);
            return true;
        }
        return false;
    }

}
