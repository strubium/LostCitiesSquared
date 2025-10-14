package mcjty.lostcities.api;

/**
 * The chunk generator for Lost Cities implements this interface. To get access to this you have to
 * call ILostCities.getLostGenerator(dimensionId);
 * Note: the old way (using chunkGenerator instanceof ILostChunkGenerator is *not* recommended anymore
 * as it is not compatible with Sponge!
 */
public interface ILostChunkGenerator {

    /**
     * Get information about a chunk. This is an efficient function as it is cached and
     * only created once every session.
     *
     * @param chunkX The X coordinate of the chunk.
     * @param chunkZ The Z coordinate of the chunk.
     * @return The information about the chunk.
     */
    ILostChunkInfo getChunkInfo(int chunkX, int chunkZ);

    /**
     * Convert a 'level' (like a city level) to a real height. This is basically
     * groundLevel + 6*level. Note that for buildings this will actually point
     * to the height at which the first blocks of a floor are. So actual usable
     * empty space will be at getRealHeight()+1.
     *
     * @param level The level to convert.
     * @return The real height.
     */
    int getRealHeight(int level);

    /**
     * Get the registry of buildings.
     *
     * @return The registry of buildings.
     */
    ILostCityAssetRegistry<ILostCityBuilding> getBuildings();

    /**
     * Get the registry of multi-buildings.
     *
     * @return The registry of multi-buildings.
     */
    ILostCityAssetRegistry<ILostCityMultiBuilding> getMultiBuildings();

    /**
     * Get the registry of city styles.
     *
     * @return The registry of city styles.
     */
    ILostCityAssetRegistry<ILostCityCityStyle> getCityStyles();
}