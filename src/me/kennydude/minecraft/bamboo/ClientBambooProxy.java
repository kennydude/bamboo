package me.kennydude.minecraft.bamboo;

import cpw.mods.fml.client.registry.RenderingRegistry;
import me.kennydude.minecraft.bamboo.blocks.BambooPoleRenderer;
import net.minecraftforge.client.MinecraftForgeClient;

/**
 * Created by kennydude on 21/06/13.
 */
public class ClientBambooProxy extends BambooProxy {

    public static int poleRendererId;

    @Override
    public void registerRenderers() {
        //MinecraftForgeClient.preloadTexture(ITEMS_PNG);
        //MinecraftForgeClient.preloadTexture(BLOCK_PNG);

        //poleRendererId = RenderingRegistry.getNextAvailableRenderId();
        //RenderingRegistry.registerBlockHandler(new BambooPoleRenderer());
    }

}
