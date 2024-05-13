package mcjty.lostcities.config;

import java.util.HashMap;
import java.util.Map;
/**
 * Enum representing different strategies for selecting biomes in the Lost Cities mod.
 *
 * @author McJty
 */
public enum BiomeSelectionStrategy {
    ORIGINAL("original"),
    RANDOMIZED("randomized"),
    VARIED("varied");

    private final String name;

    /**
     * Map to store the mapping of names to BiomeSelectionStrategy instances.
     */
    private static final Map<String, BiomeSelectionStrategy> NAME_TO_TYPE = new HashMap<>();

    static {
        // Populate the NAME_TO_TYPE map with the enum values.
        for (BiomeSelectionStrategy type : BiomeSelectionStrategy.values()) {
            NAME_TO_TYPE.put(type.getName(), type);
        }
    }

    /**
     * Constructor for BiomeSelectionStrategy.
     *
     * @param name The name of the biome selection strategy.
     */
    BiomeSelectionStrategy(String name) {
        this.name = name;
    }

    /**
     * Get the name of the biome selection strategy.
     *
     * @return The name of the biome selection strategy.
     */
    public String getName() {
        return name;
    }

    /**
     * Get the BiomeSelectionStrategy instance by its name.
     *
     * @param name The name of the biome selection strategy.
     * @return The corresponding BiomeSelectionStrategy instance, or null if not found.
     */
    public static BiomeSelectionStrategy getTypeByName(String name) {
        return NAME_TO_TYPE.get(name);
    }
}
