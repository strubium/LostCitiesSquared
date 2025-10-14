package mcjty.lostcities.api;
/**
 * This interface represents a style of lost city. It extends the {@link ILostCityAsset} interface.
 * It provides methods to retrieve various properties of the city style.
 */
public interface ILostCityCityStyle extends ILostCityAsset {

    /**
     * Returns the style name of the lost city.
     *
     * @return the style name
     */
    String getStyle();

    /**
     * Returns the chance of an explosion occurring in the lost city.
     *
     * @return the explosion chance (0.0 to 1.0)
     */
    Float getExplosionChance();

    /**
     * Returns the width of the streets in the lost city.
     *
     * @return the street width
     */
    int getStreetWidth();

    /**
     * Returns the minimum number of floors in the lost city.
     *
     * @return the minimum floor count
     */
    Integer getMinFloorCount();

    /**
     * Returns the minimum number of cellars in the lost city.
     *
     * @return the minimum cellar count
     */
    Integer getMinCellarCount();

    /**
     * Returns the maximum number of floors in the lost city.
     *
     * @return the maximum floor count
     */
    Integer getMaxFloorCount();

    /**
     * Returns the maximum number of cellars in the lost city.
     *
     * @return the maximum cellar count
     */
    Integer getMaxCellarCount();

    /**
     * Returns the block used for the main street.
     *
     * @return the street block
     */
    Character getStreetBlock();

    /**
     * Returns the block used for the base of the street.
     *
     * @return the street base block
     */
    Character getStreetBaseBlock();

    /**
     * Returns the block used for the variant of the street.
     *
     * @return the street variant block
     */
    Character getStreetVariantBlock();

    /**
     * Returns the block used for the main rails.
     *
     * @return the rail main block
     */
    Character getRailMainBlock();

    /**
     * Returns the block used for the park elevation.
     *
     * @return the park elevation block
     */
    Character getParkElevationBlock();

    /**
     * Returns the block used for the roof of the corridors.
     *
     * @return the corridor roof block
     */
    Character getCorridorRoofBlock();

    /**
     * Returns the block used for the glass of the corridors.
     *
     * @return the corridor glass block
     */
    Character getCorridorGlassBlock();

    /**
     * Returns the block used for the border of the lost city.
     *
     * @return the border block
     */
    Character getBorderBlock();

    /**
     * Returns the block used for the walls of the lost city.
     *
     * @return the wall block
     */
    Character getWallBlock();
}
