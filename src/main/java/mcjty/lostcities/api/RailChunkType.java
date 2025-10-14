package mcjty.lostcities.api;

/**
 * The type of a railway at this spot.
 * This enum is used to differentiate between various types of railway chunks in a lost cities world.
 */
public enum RailChunkType {
    /**
     * No rail chunk at this spot.
     */
    NONE(false, false),

    /**
     * A station on the surface.
     */
    STATION_SURFACE(true, true),

    /**
     * A station underground.
     */
    STATION_UNDERGROUND(true, false),

    /**
     * An extension of a station on the surface.
     */
    STATION_EXTENSION_SURFACE(true, true),

    /**
     * An extension of a station underground.
     */
    STATION_EXTENSION_UNDERGROUND(true, false),

    /**
     * A rail chunk going down two levels from the surface.
     */
    GOING_DOWN_TWO_FROM_SURFACE(false, true),

    /**
     * A rail chunk going down one level from the surface.
     */
    GOING_DOWN_ONE_FROM_SURFACE(false, true),

    /**
     * A rail chunk going down further underground.
     */
    GOING_DOWN_FURTHER(false, false),

    /**
     * A horizontal rail chunk.
     */
    HORIZONTAL(false, false),

    /**
     * A rail chunk with three splits.
     */
    THREE_SPLIT(false, false),

    /**
     * A vertical rail chunk.
     */
    VERTICAL(false, false),

    /**
     * A rail chunk with a double bend.
     */
    DOUBLE_BEND(false, false),

    /**
     * The end of rails at this spot.
     */
    RAILS_END_HERE(false, false);

    private final boolean isStation;
    private final boolean isSurface;

    /**
     * Constructor for RailChunkType.
     *
     * @param isStation whether the rail chunk is a station
     * @param isSurface whether the rail chunk is on the surface
     */
    RailChunkType(boolean isStation, boolean isSurface) {
        this.isStation = isStation;
        this.isSurface = isSurface;
    }

    /**
     * Checks if the rail chunk is a station.
     *
     * @return true if the rail chunk is a station, false otherwise
     */
    public boolean isStation() {
        return isStation;
    }

    /**
     * Checks if the rail chunk is on the surface.
     *
     * @return true if the rail chunk is on the surface, false otherwise
     */
    public boolean isSurface() {
        return isSurface;
    }
}
