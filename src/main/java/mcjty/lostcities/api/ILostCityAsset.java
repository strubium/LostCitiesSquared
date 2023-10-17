package mcjty.lostcities.api;

import com.google.gson.JsonObject;

public interface ILostCityAsset {

    // Called after the asset is fetched from the registry
    default void init() {}

    String getName();

    int getZone(); // 1: Downtown 2: Uptown 3: Suburban 4: Rural
    
    void readFromJSon(JsonObject object);
}
