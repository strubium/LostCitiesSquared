package mcjty.lostcities.gui;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import mcjty.lostcities.config.LostCityConfiguration;
import mcjty.lostcities.config.LostCityProfile;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiCreateWorld;
import net.minecraft.client.gui.GuiLabel;
import net.minecraft.client.gui.GuiScreen;

import java.io.IOException;
import java.util.*;

/**
 * GUI for configuring Lost Cities world generation.
 * Allows the user to select and apply different Lost Cities profiles.
 */
public class GuiLostCityConfiguration extends GuiScreen {

    private static final int BUTTON_WIDTH = 90;
    private static final int BUTTON_HEIGHT = 20;
    private static final int LABEL_WIDTH = 230;
    private static final int LABEL_HEIGHT = 20;
    private static final int BUTTON_Y_SPACING = 22;
    private static final int PROFILES_PER_PAGE = 8;

    private final GuiCreateWorld parent;
    private final Map<Integer, Runnable> actionHandler = new HashMap<>();
    private final Map<Integer, String> profileNames = new HashMap<>();

    private int page = 0;
    private int numPages = 0;
    private GuiMutableLabel pageLabel;

    public GuiLostCityConfiguration(GuiCreateWorld parent) {
        this.parent = parent;
    }

    @Override
    public void initGui() {
        String profileName = getCurrentProfileFromJson();
        page = 0;
        numPages = (int) Math.ceil((double) countPublicProfiles() / PROFILES_PER_PAGE);
        setupGui(profileName);
    }

    private String getCurrentProfileFromJson() {
        String profileName = LostCityConfiguration.DEFAULT_PROFILE;
        if (parent.chunkProviderSettingsJson != null && !parent.chunkProviderSettingsJson.trim().isEmpty()) {
            JsonParser parser = new JsonParser();
            JsonElement parsed = parser.parse(parent.chunkProviderSettingsJson);
            if (parsed.getAsJsonObject().has("profile")) {
                profileName = parsed.getAsJsonObject().get("profile").getAsString();
            }
        }
        return profileName;
    }

    private int countPublicProfiles() {
        return (int) LostCityConfiguration.profiles.values().stream().filter(LostCityProfile::isPublic).count();
    }

    private void setupGui(String currentProfile) {
        actionHandler.clear();
        profileNames.clear();
        buttonList.clear();
        labelList.clear();

        List<String> profileKeys = new ArrayList<>(LostCityConfiguration.profiles.keySet());
        profileKeys.sort(String::compareTo);

        int id = 301;
        int y = 8;
        int displayed = 0;
        int skipped = page * PROFILES_PER_PAGE;

        for (String key : profileKeys) {
            LostCityProfile profile = LostCityConfiguration.profiles.get(key);
            if (!profile.isPublic()) continue;
            if (skipped-- > 0) continue;
            if (displayed >= PROFILES_PER_PAGE) break;

            addProfileButtonAndLabel(id++, key, profile, y, currentProfile);
            y += BUTTON_Y_SPACING;
            displayed++;
        }

        addInfoLabel(id++, 200, "(note, you can create your own profiles and many more",
                "configuration options in 'lostcities.cfg')");

        if (numPages > 1) {
            addPageNavigationButtons(id++, 200, currentProfile);
        }
    }

    private void addProfileButtonAndLabel(int id, String key, LostCityProfile profile, int y, String currentProfile) {
        // Profile button
        GuiButton button = new GuiButton(id, 10, y, BUTTON_WIDTH, BUTTON_HEIGHT, key);
        if (currentProfile.equals(profile.getName())) {
            button.packedFGColour = 0xffffff00; // Highlight selected
        }
        buttonList.add(button);
        actionHandler.put(id, () -> setProfile(profile));
        profileNames.put(id, profile.getName());

        // Profile description label
        GuiLabel label = new GuiLabel(Minecraft.getMinecraft().fontRenderer, id + 1000, 110, y, LABEL_WIDTH, LABEL_HEIGHT, 0xffffffff);
        label.addLine(profile.getDescription());
        labelList.add(label);
    }

    private void addInfoLabel(int id, int y, String line1, String line2) {
        GuiLabel label = new GuiLabel(Minecraft.getMinecraft().fontRenderer, id, 20, y, 340, 20, 0xffffffff);
        label.addLine(line1);
        label.addLine(line2);
        labelList.add(label);
    }

    private void addPageNavigationButtons(int id, int y, String currentProfile) {
        GuiButton prev = new GuiButton(id, 330, y, 20, 19, "<");
        buttonList.add(prev);
        actionHandler.put(id, () -> { page = Math.max(page - 1, 0); setupGui(currentProfile); });

        pageLabel = new GuiMutableLabel(Minecraft.getMinecraft().fontRenderer, id + 1, 360, y, 30, 20, 0xffffffff);
        pageLabel.addLine("" + (page + 1) + "/" + numPages);

        GuiButton next = new GuiButton(id + 2, 390, y, 20, 19, ">");
        buttonList.add(next);
        actionHandler.put(id + 2, () -> { page = Math.min(page + 1, numPages - 1); setupGui(currentProfile); });
    }

    private void setProfile(LostCityProfile profile) {
        parent.chunkProviderSettingsJson = "{ \"profile\": \"" + profile.getName() + "\" }";
        mc.displayGuiScreen(parent);
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        super.actionPerformed(button);
        Runnable action = actionHandler.get(button.id);
        if (action != null) {
            action.run();
        }
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);

        if (numPages > 1) {
            pageLabel.clearLines();
            pageLabel.addLine("" + (page + 1) + "/" + numPages);
            pageLabel.drawLabel(Minecraft.getMinecraft(), mouseX, mouseY);
        }

        for (GuiButton button : buttonList) {
            if (button.isMouseOver()) {
                String name = profileNames.get(button.id);
                if (name != null) {
                    LostCityProfile profile = LostCityConfiguration.profiles.get(name);
                    if (profile != null && profile.getIcon() != null) {
                        drawProfileTooltip(button, profile);
                    }
                }
            }
        }
    }

    private void drawProfileTooltip(GuiButton button, LostCityProfile profile) {
        int bx = button.x + 95;
        int by = button.y + 6;
        drawGradientRect(bx - 5, by - 5, bx + 320, by + 85, 0xffffffff, 0xffffffff);
        mc.getTextureManager().bindTexture(profile.getIcon());
        drawScaledCustomSizeModalRect(bx, by, 0, 0, 128, 128, 80, 80, 128, 128);
        String output = profile.getDescription() + "\n" + profile.getExtraDescription();
        mc.fontRenderer.drawSplitString(output, bx + 90, by, 220, 0xff000000);
    }
}
