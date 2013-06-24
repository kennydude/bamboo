package me.kennydude.minecraft.bamboo.gui;

import me.kennydude.minecraft.bamboo.Constants;
import me.kennydude.minecraft.bamboo.PacketHandler;
import me.kennydude.minecraft.bamboo.blocks.TatamiMat;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiSmallButton;
import net.minecraft.util.MathHelper;
import net.minecraft.util.StringTranslate;
import net.minecraft.world.World;

/**
 * This is the GUI for the Tatami Cutter.
 *
 * It is used to set the data for the mat
 *
 * Created by kennydude on 21/06/13.
 */
public class GuiTatamiCutter extends GuiScreen {

    GuiButton bleft, bright, btop, bbottom, bok;
    public boolean setLeft, setRight, setBottom, setTop;

    int x, y, z;
    World world;

    public GuiTatamiCutter(int x, int y, int z, World world){
        super();
        this.x = x; this.y = y; this.z = z;
        this.world = world;
    }

    @Override
    public boolean doesGuiPauseGame(){
        return false;
    }

    @Override
    public void initGui() {
        int height = 20;
        int width = 40;
        int centerW = this.width / 2 - width/2;
        int centerH = this.height / 2;

        bleft = new GuiButton(0, centerW - 60, centerH, width, height, "N");
        bbottom = new GuiButton(1, centerW, centerH + 60, width, height, "N");
        btop = new GuiButton(2, centerW, centerH - 60, width, height, "N");
        bright = new GuiButton(3, centerW + 60, centerH, width, height, "N");

        bok = new GuiButton(4, centerW, centerH, width, height, "OK");

        buttonList.add(bleft);
        buttonList.add(bright);
        buttonList.add(bbottom);
        buttonList.add(btop);
        buttonList.add(bok);

        // Set values from existing metadata
        int cMeta = this.mc.theWorld.getBlockMetadata(x,y,z);
        System.out.println("GuiTatamiCutter: Existing Metadata: " + cMeta + "@" + x + "," + y + "," + z );

        int direction = MathHelper.floor_double((double) ((this.mc.thePlayer.rotationYaw * 4F) / 360F) + 0.5D) & 3;
        System.out.println("GuiTatamiCutter: Player direction: " + direction);

        boolean setEast = false, setNorth = false, setSouth = false, setWest = false;
        if(cMeta == TatamiMat.META_LEFT){
            setWest = true;
        } else if(cMeta == TatamiMat.META_LEFT_TOP){
            setWest = true;
            setNorth = true;
        } else if(cMeta == TatamiMat.META_LEFT_BOTTOM){
            setWest = true;
            setSouth = true;
        } else if(cMeta == TatamiMat.META_TOP){
            setNorth = true;
        } else if(cMeta == TatamiMat.META_BOTTOM){
            setSouth = true;
        } else if(cMeta == TatamiMat.META_RIGHT){
            setEast = true;
        } else if(cMeta == TatamiMat.META_RIGHT_TOP){
            setEast = true;
            setNorth = true;
        }  else if(cMeta == TatamiMat.META_RIGHT_BOTTOM){
            setEast = true;
            setSouth = true;
        }

        // Now set them to the right place. We do this because
        // otherwise the GUI looks confusing
        if(direction == Constants.DIRECTION_NORTH){
            setTop = setNorth; setRight = setEast;
            setBottom = setSouth; setLeft = setWest;
        } else if(direction == Constants.DIRECTION_EAST){
            setTop = setEast; setRight = setSouth;
            setBottom = setWest; setLeft = setNorth;
        } else if(direction == Constants.DIRECTION_SOUTH){
            setTop = setSouth; setRight = setWest;
            setBottom = setNorth; setLeft = setEast;
        } else if(direction == Constants.DIRECTION_WEST){
            setTop = setWest; setRight = setNorth;
            setBottom = setEast; setLeft = setSouth;
        }

        calc();
    }

    public void calc(){
        btop.displayString = setTop ? "Y" : "N";
        bleft.displayString = setLeft ? "Y" : "N";
        bright.displayString = setRight ? "Y" : "N";
        bbottom.displayString = setBottom ? "Y" : "N";

        // Check for impossible
        if( setTop && setBottom ){
            bok.enabled = false;
        } else if(setLeft && setRight){
            bok.enabled = false;
        } else{
            bok.enabled = true;
        }
    }

    @Override
    protected void actionPerformed(GuiButton guibutton) {
        switch (guibutton.id){
            case 0:
                setLeft = !setLeft;
                break;
            case 1:
                setBottom = !setBottom;
                break;
            case 2:
                setTop = !setTop;
                break;
            case 3:
                setRight = !setRight;
                break;
            case 4:
                // Save metadata
                int newMeta = 0;

                int direction = MathHelper.floor_double((double) ((this.mc.thePlayer.rotationYaw * 4F) / 360F) + 0.5D) & 3;
                System.out.println("GuiTatamiCutter: Player direction E: " + direction);

                boolean setNorth = false, setEast = false, setSouth = false, setWest = false;

                // Now set them to the right place. We do this because
                // otherwise the GUI looks confusing
                if(direction == Constants.DIRECTION_NORTH){
                    setNorth = setTop; setEast = setRight;
                    setSouth = setBottom; setWest = setLeft;
                } else if(direction == Constants.DIRECTION_EAST){
                    setNorth = setLeft; setEast = setTop;
                    setSouth = setRight; setWest = setBottom;
                } else if(direction == Constants.DIRECTION_SOUTH){
                    setNorth = setBottom; setEast = setLeft;
                    setSouth = setTop; setWest = setRight;
                } else if(direction == Constants.DIRECTION_WEST){
                    setNorth = setRight; setEast = setTop;
                    setSouth = setLeft; setWest = setBottom;
                }

                if(setWest && setNorth){
                    newMeta = TatamiMat.META_LEFT_TOP;
                } else if(setWest && setSouth){
                    newMeta = TatamiMat.META_LEFT_BOTTOM;
                } else if(setWest){
                    newMeta = TatamiMat.META_LEFT;
                } else if(setEast && setNorth){
                    newMeta = TatamiMat.META_RIGHT_TOP;
                } else if(setEast && setSouth){
                    newMeta = TatamiMat.META_RIGHT_BOTTOM;
                } else if(setEast){
                    newMeta = TatamiMat.META_RIGHT;
                } else if(setNorth){
                    newMeta = TatamiMat.META_TOP;
                } else if(setSouth){
                    newMeta = TatamiMat.META_BOTTOM;
                }
                System.out.println("GuiTatamiCutter: " + newMeta + "@" + x + "," + y + "," + z );
               // BAD!!!! this.mc.theWorld.setBlockMetadataWithNotify(x,y,z, newMeta, 3 );
                this.mc.thePlayer.sendQueue.addToSendQueue(PacketHandler.getSetMetadataPacket(x,y,z,newMeta) );

                this.mc.displayGuiScreen((GuiScreen)null);
                this.mc.setIngameFocus();

                break;
        }
        calc();
    }

    @Override
    public void drawScreen(int par1, int par2, float par3) {
        this.drawDefaultBackground();

        this.drawCenteredString(this.fontRenderer, "TATAMI", this.width / 2, 20, 16777215);


        super.drawScreen(par1, par2, par3);
    }

}
