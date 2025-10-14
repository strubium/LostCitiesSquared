package mcjty.lostcities.config;

import java.util.HashMap;
import java.util.Map;

/**
 * Enum representing different types of landscapes in the game.
 * This enum is used to define the different types of landscapes that can be generated in the game.
 */
public enum LandscapeType {
    DEFAULT("default"),
    FLOATING("floating"),
    SPACE("space"),
    CAVERN("cavern");

    private final String name;

    /**
     * A map to store the LandscapeType instances by their names.
     * This map is used for efficient retrieval of LandscapeType instances based on their names.
     */
    private static final Map<String, LandscapeType> NAME_TO_TYPE = new HashMap<>();

    static {
        // Populate the NAME_TO_TYPE map with LandscapeType instances
        for (LandscapeType type : LandscapeType.values()) {
            NAME_TO_TYPE.put(type.getName(), type);
        }
    }

    /**
     * Constructor for LandscapeType enum.
     *
     * @param name The name of the landscape type.
     */
    LandscapeType(String name) {
        this.name = name;
    }

    /**
     * Getter for the name of the landscape type.
     *
     * @return The name of the landscape type.
     */
    public String getName() {
        return name;
    }

    /**
     * Retrieves a LandscapeType instance based on its name.
     *
     * @param name The name of the landscape type.
     * @return The LandscapeType instance corresponding to the given name, or null if no such instance exists.
     */
    public static LandscapeType getTypeByName(String name) {
        return NAME_TO_TYPE.get(name);
    }
}
