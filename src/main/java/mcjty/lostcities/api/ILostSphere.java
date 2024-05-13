package mcjty.lostcities.api;

import mcjty.lostcities.varia.ChunkCoord;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;

import javax.annotation.Nullable;
/**
 * This interface represents a Lost Sphere, which is a circular area in the world
 * where the Lost Cities mod generates structures.
 */
public interface ILostSphere {

    /**
     * Get the center chunk of this sphere.
     *
     * @return the center chunk of this sphere
     */
    ChunkCoord getCenter();

    /**
     * Get the center position of this sphere.
     *
     * @return the center position of this sphere
     */
    BlockPos getCenterPos();

    /**
     * The radius of this sphere.
     *
     * @return the radius of this sphere
     */
    float getRadius();

    /**
     * If this biome is tied to a fixed biome.
     *
     * @return the biome tied to this sphere, or null if no biome is tied
     */
    @Nullable
    Biome getBiome();

    /**
     * Return true if this sphere is enabled. Always test for this before using
     * the other methods of this interface.
     *
     * @return true if this sphere is enabled, false otherwise
     */
    boolean isEnabled();
}
