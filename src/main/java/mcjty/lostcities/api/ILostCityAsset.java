package mcjty.lostcities.api;

import com.google.gson.JsonObject;

/**
 * This interface represents a lost city asset.
 * It provides methods for initializing the asset, getting its name, and reading its data from a JSON object.
 */
public interface ILostCityAsset {

    /**
     * Called after the asset is fetched from the registry.
     * This method can be overridden to perform any necessary initialization.
     * By default, it does nothing.
     */
    default void init() {}

    /**
     * Returns the name of the lost city asset.
     *
     * @return the name of the lost city asset
     */
    String getName();

    /**
     * Reads the data of the lost city asset from a JSON object.
     *
     * @param object the JSON object containing the asset data
     */
    void readFromJSon(JsonObject object);
}
