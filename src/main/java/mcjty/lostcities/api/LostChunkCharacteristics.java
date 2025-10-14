package mcjty.lostcities.api;

/**
 * This class represents the characteristics of a lost city chunk.
 * It contains information about whether the chunk is a city,
 * whether it could contain a building, the section of the building it belongs to,
 * the city level, the city style, the multi-building type, and the building type.
 */
public class LostChunkCharacteristics {

    /**
     * Indicates whether the chunk is a city.
     */
    public boolean isCity;

    /**
     * Indicates whether this chunk could contain a building.
     * True if this chunk could contain a building, false otherwise.
     */
    public boolean couldHaveBuilding;

    /**
     * The section of the building this chunk belongs to.
     * -1 if the chunk is a single building, else it represents the index of the sub-building.
     */
    public int section;

    /**
     * The city level of the chunk.
     * 0 is the lowest city level.
     */
    public int cityLevel;

    /**
     * The city style of the chunk.
     * It represents the style of the city.
     */
    public ILostCityCityStyle cityStyle;

    /**
     * The multi-building type of the chunk.
     * It represents the type of multi-building if the chunk is part of a multi-building.
     */
    public ILostCityMultiBuilding multiBuilding;

    /**
     * The building type of the chunk.
     * It represents the type of building if the chunk is a building.
     */
    public ILostCityBuilding buildingType;
}
