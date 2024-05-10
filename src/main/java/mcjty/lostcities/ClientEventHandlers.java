package mcjty.lostcities;

import mcjty.lostcities.config.LostCityProfile;
import mcjty.lostcities.dimensions.world.LostWorldType;
import mcjty.lostcities.dimensions.world.WorldTypeTools;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * This class handles client-side events related to the Lost Cities mod.
 */
public class ClientEventHandlers {

    /**
     * This method is called when the fog color is being calculated for an entity in a Lost Cities world.
     * It sets the fog color to the values specified in the world's profile if they are not negative.
     *
     * @param event The event containing information about the fog color and the entity.
     */
    @SubscribeEvent
    public void onFogEvent(EntityViewRenderEvent.FogColors event) {
        if (WorldTypeTools.isLostCities(event.getEntity().world)) {
            LostCityProfile profile = WorldTypeTools.getProfile(event.getEntity().world);
            if (profile.FOG_RED >= 0) {
                event.setRed(profile.FOG_RED);
            }
            if (profile.FOG_GREEN >= 0) {
                event.setGreen(profile.FOG_GREEN);
            }
            if (profile.FOG_BLUE >= 0) {
                event.setBlue(profile.FOG_BLUE);
            }
        }
    }

    /**
     * This method is called when the fog density is being calculated for an entity in a Lost Cities world.
     * It sets the fog density to the value specified in the world's profile if it is not negative.
     * The event is canceled after setting the density to prevent Minecraft from using its default fog density.
     *
     * @param event The event containing information about the fog density and the entity.
     */
    @SubscribeEvent
    public void onFogDensity(EntityViewRenderEvent.FogDensity event) {
        if (WorldTypeTools.isLostCities(event.getEntity().world)) {
            LostCityProfile profile = WorldTypeTools.getProfile(event.getEntity().world);
            if (profile.FOG_DENSITY >= 0) {
                event.setDensity(profile.FOG_DENSITY);
                event.setCanceled(true);
            }
        }
    }
}
