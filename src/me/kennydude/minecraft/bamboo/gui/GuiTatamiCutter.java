package me.kennydude.minecraft.bamboo.gui;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiSmallButton;
import net.minecraft.util.StringTranslate;

/**
 * This is the GUI for the Tatami Cutter.
 *
 * It is used to set the data for the mat
 *
 * Created by kennydude on 21/06/13.
 */
public class GuiTatamiCutter extends GuiScreen {

    GuiButton bleft, bright, btop, bbottom;
    public int flag = 0;

    public GuiTatamiCutter(){
        super();

        //StringTranslate stringtranslate = StringTranslate.getInstance();
        //title = stringtranslate.translateKey("tatamicutter");
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

        buttonList.add(bleft);
        buttonList.add(bright);
        buttonList.add(bbottom);
        buttonList.add(btop);
    }

    @Override
    protected void actionPerformed(GuiButton guibutton) {
        switch (guibutton.id){
            case 0:
                bleft.displayString = "Y";
                break;
            case 1:
                bbottom.displayString = "Y";
                break;
            case 2:
                btop.displayString = "Y";
                break;
            case 3:
                bright.displayString = "Y";
                break;
        }
    }

    @Override
    public void drawScreen(int par1, int par2, float par3) {
        this.drawDefaultBackground();

        this.drawCenteredString(this.fontRenderer, "TATAMI", this.width / 2, 20, 16777215);


        super.drawScreen(par1, par2, par3);
    }

}
