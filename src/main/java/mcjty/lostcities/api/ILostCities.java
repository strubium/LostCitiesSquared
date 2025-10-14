package mcjty.lostcities.api;

import javax.annotation.Nullable;

/**
 * This interface provides the main functionality for the Lost Cities mod.
 * It allows other mods to interact with the city generation and information.
 *
 * To get a reference to an implementation of this interface, you can use the following code:
 * <pre>
 *     FMLInterModComms.sendFunctionMessage("lostcities", "getLostCities", "<whatever>.YourClass$GetLostCities");
 * </pre>
 * Replace "<whatever>.YourClass$GetLostCities" with the fully qualified name of your class that implements this interface.
 */
public interface ILostCities {

    /**
     * If the given dimension is handled by Lost Cities, this method will return the chunk generator.
     * It is strongly recommended to use this method instead of checking for the instanceof ILostChunkGenerator,
     * as the latter technique is not compatible with Sponge.
     *
     * @param dimension The dimension ID for which to retrieve the chunk generator.
     * @return The chunk generator for the given dimension if it is handled by Lost Cities, or null if it is not.
     */
    @Nullable
    ILostChunkGenerator getLostGenerator(int dimension);
}