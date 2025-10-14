package mcjty.lostcities.api;

/**
 * This interface represents a registry for lost city assets.
 * It provides methods to retrieve specific assets by name and to iterate over all assets.
 *
 * @param <T> The type of the lost city asset. It must implement the {@link ILostCityAsset} interface.
 */
public interface ILostCityAssetRegistry<T extends ILostCityAsset> {

    /**
     * Retrieves a lost city asset by its name.
     *
     * @param name The name of the lost city asset.
     * @return The lost city asset with the given name, or {@code null} if no such asset exists.
     */
    T get(String name);

    /**
     * Retrieves an iterable over all lost city assets.
     *
     * @return An iterable over all lost city assets.
     */
    Iterable<T> getIterable();
}
