package me.kennydude.minecraft.bamboo;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;
import cpw.mods.fml.relauncher.Side;
import me.kennydude.minecraft.bamboo.gui.GuiTatamiCutter;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

/**
 * Created by kennydude on 21/06/13.
 */
public class PacketHandler implements IPacketHandler {


    @Override
    public void onPacketData(INetworkManager manager, Packet250CustomPayload packet, Player player) {
        String data = new String(packet.data);

        System.out.println("Packet for us. " + data);

        char command = data.charAt(0);
        if(command == 'm'){ // We need to set a metadata value
            Side side = FMLCommonHandler.instance().getEffectiveSide();
            if(side == Side.SERVER){
                String[] bits = data.substring(1).split(",");
                Integer x = Integer.parseInt(bits[0]),
                        y = Integer.parseInt(bits[1]),
                        z = Integer.parseInt(bits[2]),
                        meta = Integer.parseInt(bits[3]);

                System.out.println("BBT: Server-side metadata @" + x + "," + y + "," + z);

                ((EntityPlayer)player).worldObj.setBlockMetadataWithNotify(
                        x, y, z, meta, 2
                );
            } else{
                System.out.println("BBT: Unknown side for metadata setting");
            }
        }
    }

    public static Packet250CustomPayload getSetMetadataPacket( int x, int y, int z, int metadata ){
        return getPacket( "m" + x + "," + y + "," + z + "," + metadata );
    }

    public static Packet250CustomPayload getPacket(String data){
        byte[] d = data.getBytes();

        Packet250CustomPayload packet = new Packet250CustomPayload();
        packet.channel = Constants.PACKET_CHANNEL;
        packet.data = d;
        packet.length = d.length;

        return packet;
    }
}
