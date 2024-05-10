package mcjty.lostcities.gui;

import com.google.common.collect.Lists;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

/**
 * A custom GUI label that can display multiple lines of text and can be centered.
 * It also supports background color, border, and text color customization.
 *
 * @author mcjty
 */
@SideOnly(Side.CLIENT)
public class GuiMutableLabel extends Gui {

    protected int width = 200;
    protected int height = 20;
    public int x;
    public int y;
    private final List<String> labels;
    public int id;
    private boolean centered;
    public boolean visible = true;
    private boolean labelBgEnabled;
    private final int textColor;
    private int backColor;
    private int ulColor;
    private int brColor;
    private final FontRenderer fontRenderer;
    private int border;

    /**
     * Constructor for the GuiMutableLabel.
     *
     * @param fontRendererObj the font renderer used to draw the text
     * @param p_i45540_2_      the id of the label
     * @param p_i45540_3_      the x position of the label
     * @param p_i45540_4_      the y position of the label
     * @param p_i45540_5_      the width of the label
     * @param p_i45540_6_      the height of the label
     * @param p_i45540_7_      the color of the text
     */
    public GuiMutableLabel(FontRenderer fontRendererObj, int p_i45540_2_, int p_i45540_3_, int p_i45540_4_, int p_i45540_5_, int p_i45540_6_, int p_i45540_7_) {
        this.fontRenderer = fontRendererObj;
        this.id = p_i45540_2_;
        this.x = p_i45540_3_;
        this.y = p_i45540_4_;
        this.width = p_i45540_5_;
        this.height = p_i45540_6_;
        this.labels = Lists.<String>newArrayList();
        this.centered = false;
        this.labelBgEnabled = false;
        this.textColor = p_i45540_7_;
        this.backColor = -1;
        this.ulColor = -1;
        this.brColor = -1;
        this.border = 0;
    }

    /**
     * Adds a line of text to the label.
     *
     * @param p_175202_1_ the text to add
     */
    public void addLine(String p_175202_1_) {
        this.labels.add(I18n.format(p_175202_1_, new Object[0]));
    }

    /**
     * Clears all lines of text from the label.
     */
    public void clearLines() {
        labels.clear();
    }

    /**
     * Sets the label to be centered.
     *
     * @return the label itself for chaining
     */
    public GuiMutableLabel setCentered() {
        this.centered = true;
        return this;
    }

    /**
     * Draws the label on the screen.
     *
     * @param mc       the Minecraft instance
     * @param mouseX   the x position of the mouse cursor
     * @param mouseY   the y position of the mouse cursor
     */
    public void drawLabel(Minecraft mc, int mouseX, int mouseY) {
        if (this.visible) {
            GlStateManager.enableBlend();
            GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
            this.drawLabelBackground(mc, mouseX, mouseY);
            int i = this.y + this.height / 2 + this.border / 2;
            int j = i - this.labels.size() * 10 / 2;

            for (int k = 0; k < this.labels.size(); ++k) {
                if (this.centered) {
                    this.drawCenteredString(this.fontRenderer, (String) this.labels.get(k), this.x + this.width / 2, j + k * 10, this.textColor);
                } else {
                    this.drawString(this.fontRenderer, (String) this.labels.get(k), this.x, j + k * 10, this.textColor);
                }
            }
        }
    }

    /**
     * Draws the background of the label.
     *
     * @param mcIn       the Minecraft instance
     * @param p_146160_2_ the x position of the mouse cursor
     * @param p_146160_3_ the y position of the mouse cursor
     */
    protected void drawLabelBackground(Minecraft mcIn, int p_146160_2_, int p_146160_3_) {
        if (this.labelBgEnabled) {
            int i = this.width + this.border * 2;
            int j = this.height + this.border * 2;
            int k = this.x - this.border;
            int l = this.y - this.border;
            drawRect(k, l, k + i, l + j, this.backColor);
            this.drawHorizontalLine(k, k + i, l, this.ulColor);
            this.drawHorizontalLine(k, k + i, l + j, this.brColor);
            this.drawVerticalLine(k, l, l + j, this.ulColor);
            this.drawVerticalLine(k + i, l, l + j, this.brColor);
        }
    }
}