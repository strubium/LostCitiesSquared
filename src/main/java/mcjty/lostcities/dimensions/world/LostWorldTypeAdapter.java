package mcjty.lostcities.dimensions.world;

import mcjty.lostcities.api.IChunkPrimerFactory;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraft.world.gen.IChunkGenerator;

/**
 * This class is an adapter for the Lost Cities mod to allow it to work with other world types.
 * It extends the LostWorldType class and overrides its methods to provide a custom chunk generator and biome provider.
 */
public class LostWorldTypeAdapter extends LostWorldType {

    /**
     * Constructor for the LostWorldTypeAdapter class.
     *
     * @param other The name of the other world type to adapt.
     */
    public LostWorldTypeAdapter(String other) {
        super("lc_" + other);
        this.otherWorldtype = other;
    }

    private final String otherWorldtype;
    private BiomeProvider biomeProvider = null;
    private IChunkGenerator otherGenerator = null;
    private IChunkPrimerFactory factory = null;

    /**
     * This method overrides the getChunkGenerator method from the LostWorldType class.
     * It creates a custom chunk generator for the lost cities world based on the other world type.
     *
     * @param world The world object.
     * @param generatorOptions The generator options.
     * @return The custom chunk generator for the lost cities world.
     */
    @Override
    public IChunkGenerator getChunkGenerator(World world, String generatorOptions) {
        if (otherGenerator == null) {
            for (WorldType type : WorldType.WORLD_TYPES) {
                if (otherWorldtype.equals(type.getName())) {
                    WorldType orig = world.getWorldInfo().getTerrainType();
                    world.getWorldInfo().setTerrainType(type);
                    otherGenerator = type.getChunkGenerator(world, generatorOptions);
                    world.getWorldInfo().setTerrainType(orig);
                    if (otherGenerator instanceof IChunkPrimerFactory) {
                        factory = (IChunkPrimerFactory) otherGenerator;
                    }
                    break;
                }
            }
        }
        return new LostCityChunkGenerator(world, factory);
    }

    /**
     * This method overrides the getInternalBiomeProvider method from the LostWorldType class.
     * It creates a custom biome provider for the lost cities world based on the other world type.
     *
     * @param world The world object.
     * @return The custom biome provider for the lost cities world.
     */
    @Override
    protected BiomeProvider getInternalBiomeProvider(World world) {
        if (biomeProvider == null) {
            for (WorldType type : WorldType.WORLD_TYPES) {
                if (otherWorldtype.equals(type.getName())) {
                    WorldType orig = world.getWorldInfo().getTerrainType();
                    world.getWorldInfo().setTerrainType(type);
                    biomeProvider = type.getBiomeProvider(world);
                    world.getWorldInfo().setTerrainType(orig);
                    break;
                }
            }
        }
        return biomeProvider;
    }
}
