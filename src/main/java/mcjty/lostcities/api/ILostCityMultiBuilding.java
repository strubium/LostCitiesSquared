package mcjty.lostcities.api;

/**
 * This interface represents a multi-building asset in the Lost Cities mod.
 * It provides methods to get the name of the building for a given chunk relative to the top-left chunk of the multibuilding,
 * as well as the dimensions of the multi-building in chunks.
 */
public interface ILostCityMultiBuilding extends ILostCityAsset {

    /**
     * Gets the name of the building for the given chunk relative to the top-left chunk of the multibuilding.
     *
     * @param x The x-coordinate of the chunk relative to the top-left chunk.
     * @param z The z-coordinate of the chunk relative to the top-left chunk.
     * @return The name of the building for the given chunk.
     */
    String getBuilding(int x, int z);

    /**
     * Gets the dimension of the multi-building in chunks along the x-axis.
     *
     * @return The dimension of the multi-building in chunks along the x-axis.
     */
    int getDimX();

    /**
     * Gets the dimension of the multi-building in chunks along the z-axis.
     *
     * @return The dimension of the multi-building in chunks along the z-axis.
     */
    int getDimZ();
}
