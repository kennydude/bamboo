package me.kennydude.minecraft.bamboo;

import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;
import me.kennydude.minecraft.bamboo.gui.GuiTatamiCutter;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;

/**
 * Created by kennydude on 21/06/13.
 */
public class PacketHandler implements IPacketHandler {
    @Override
    public void onPacketData(INetworkManager manager, Packet250CustomPayload packet, Player player) {
        String data = new String(packet.data);

        if(data.equals("cutter")){

        }
    }
}
