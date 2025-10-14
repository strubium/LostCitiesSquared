package mcjty.lostcities.api;

import net.minecraft.util.math.BlockPos;

/**
 * This interface represents a custom explosion in the Lost Cities mod.
 * It provides methods to retrieve the radius and center of the explosion.
 */
public interface ILostExplosion {

    /**
     * Returns the radius of the explosion.
     *
     * @return the radius of the explosion
     */
    int getRadius();

    /**
     * Returns the center position of the explosion.
     *
     * @return the center position of the explosion
     */
    BlockPos getCenter();
}
