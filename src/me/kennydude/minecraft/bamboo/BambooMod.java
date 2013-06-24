package me.kennydude.minecraft.bamboo;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import me.kennydude.minecraft.bamboo.blocks.Bamboo;
import me.kennydude.minecraft.bamboo.blocks.BambooPole;
import me.kennydude.minecraft.bamboo.blocks.TatamiMat;
import me.kennydude.minecraft.bamboo.gui.GuiTatamiCutterHandler;
import me.kennydude.minecraft.bamboo.tools.TatamiCutter;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

/**
 * Created by kennydude on 21/06/13.
 */
@Mod(modid="Bamboo", name="Bamboo", version="1.0.0")
@NetworkMod(
        clientSideRequired=true,
        serverSideRequired=true,
        channels={Constants.PACKET_CHANNEL},
        packetHandler=PacketHandler.class
)
public class BambooMod {

    @Instance("Bamboo")
    public static BambooMod instance;

    // Says where the client and server 'proxy' code is loaded.
    @SidedProxy(clientSide="me.kennydude.minecraft.bamboo.ClientBambooProxy", serverSide="me.kennydude.minecraft.bamboo.BambooProxy")
    public static BambooProxy proxy;

    // Blocks
    public static final Block bambooBlock = new Bamboo(601, 0, Material.plants);
    public static final Block bambooPole = new BambooPole(602, "bamboo", Material.plants);
    public static final Block tatamiMat = new TatamiMat(603, Material.plants);

    // Tools
    public static final Item tatamiCutter = new TatamiCutter(605);

    public static CreativeTabs tabBamboo = new CreativeTabs("bamboo");

    @PreInit
    public void preInit(FMLPreInitializationEvent event) {
        // Stub Method
    }

    @Init
    public void load(FMLInitializationEvent event) {
        proxy.registerRenderers();

        // Register blocks
        // 1 : Bamboo itself
        bambooBlock.setCreativeTab( tabBamboo );
        GameRegistry.registerBlock(bambooBlock, "bamboo");
        LanguageRegistry.addName(bambooBlock, "Bamboo");

        // 2 : Bamboo Pole
        bambooPole.setCreativeTab( tabBamboo );
        GameRegistry.registerBlock(bambooPole, "bamboopole");
        LanguageRegistry.addName(bambooPole, "Bamboo Pole");
        GameRegistry.addRecipe(new ItemStack(bambooPole), "   ", " x ", " x ", 'x', bambooBlock);
        Block.setBurnProperties( bambooPole.blockID, 5, 5 );

        // 3: Tatami Mat
        tatamiMat.setCreativeTab( tabBamboo );
        GameRegistry.registerBlock( tatamiMat, "tatamiMat" );
        LanguageRegistry.addName(tatamiMat, "Tatami Mat");
        Block.setBurnProperties( tatamiMat.blockID, 5, 5 );

        // Tools!
        tatamiCutter.setCreativeTab( tabBamboo );
        GameRegistry.registerItem(tatamiCutter, "tatamiCutter");
        LanguageRegistry.addName(tatamiCutter, "Tatami Cutter");

        // Register GUI
        NetworkRegistry.instance().registerGuiHandler(this, new GuiTatamiCutterHandler());
    }

    @PostInit
    public void postInit(FMLPostInitializationEvent event) {
        // Stub Method
    }

}
