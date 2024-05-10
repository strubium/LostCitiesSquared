package mcjty.lostcities;

import mcjty.lostcities.api.ILostChunkGenerator;
import mcjty.lostcities.api.ILostCities;
import mcjty.lostcities.dimensions.world.WorldTypeTools;

import javax.annotation.Nullable;

/**
 * This class implements the {@link ILostCities} interface, providing the necessary functionality for the Lost Cities mod.
 */
public class LostCitiesImp implements ILostCities {

    /**
     * Retrieves the lost chunk generator for the specified dimension.
     *
     * @param dimension The dimension ID for which to retrieve the lost chunk generator.
     * @return The lost chunk generator for the specified dimension, or {@code null} if no generator is found.
     */
    @Nullable
    @Override
    public ILostChunkGenerator getLostGenerator(int dimension) {
        return WorldTypeTools.getChunkGenerator(dimension);
    }
}
