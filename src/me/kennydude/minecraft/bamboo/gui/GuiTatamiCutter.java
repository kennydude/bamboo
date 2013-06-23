package me.kennydude.minecraft.bamboo.gui;

import me.kennydude.minecraft.bamboo.Constants;
import me.kennydude.minecraft.bamboo.blocks.TatamiMat;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiSmallButton;
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
    public void initGui() {
        int height = 20;
        int width = 40;
        int centerW = this.width / 2 - width/2;
        int centerH = this.height / 2;

        bleft = new GuiButton(0, centerW - 60, centerH, width, height, "N");
        bbottom = new GuiButton(1, centerW, centerH + 60, width, height, "N");
        btop = new GuiButton(2, centerW, centerH - 60, width, height, "N");
        bright = new GuiButton(3, centerW + 60, centerH, width, height, "N");
        bok = new GuiButton(4, centerW, centerH + 80, width, height, "OK");

        buttonList.add(bleft);
        buttonList.add(bright);
        buttonList.add(bbottom);
        buttonList.add(btop);
        buttonList.add(bok);

        // Set values from existing metadata
        int cMeta = world.getBlockMetadata(x,y,z);
        System.out.println("GuiTatamiCutter: Existing Metadata: " + cMeta );
        if(cMeta == TatamiMat.META_LEFT){
            setLeft = true;
        } else if(cMeta == TatamiMat.META_LEFT_TOP){
            setLeft = true;
            setTop = true;
        } else if(cMeta == TatamiMat.META_LEFT_BOTTOM){
            setLeft = true;
            setBottom = true;
        } else if(cMeta == TatamiMat.META_TOP){
            setTop = true;
        } else if(cMeta == TatamiMat.META_BOTTOM){
            setBottom = true;
        } else if(cMeta == TatamiMat.META_RIGHT){
            setRight = true;
        } else if(cMeta == TatamiMat.META_RIGHT_TOP){
            setRight = true;
            setTop = true;
        }  else if(cMeta == TatamiMat.META_RIGHT_BOTTOM){
            setRight = true;
            setBottom = true;
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
                if(setLeft && setTop){
                    newMeta = TatamiMat.META_LEFT_TOP;
                } else if(setLeft && setBottom){
                    newMeta = TatamiMat.META_LEFT_BOTTOM;
                } else if(setLeft){
                    newMeta = TatamiMat.META_LEFT;
                } else if(setRight && setTop){
                    newMeta = TatamiMat.META_RIGHT_TOP;
                } else if(setRight && setBottom){
                    newMeta = TatamiMat.META_RIGHT_BOTTOM;
                } else if(setRight){
                    newMeta = TatamiMat.META_RIGHT;
                } else if(setTop){
                    newMeta = TatamiMat.META_TOP;
                } else if(setBottom){
                    newMeta = TatamiMat.META_BOTTOM;
                }
                System.out.println("GuiTatamiCutter: " + newMeta);
                world.setBlockMetadataWithNotify(x,y,z, newMeta, Constants.SEND_BLOCK_CHANGE );
                world.markBlockForRenderUpdate(x,y,z);

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
