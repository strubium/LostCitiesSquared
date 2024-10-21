package mcjty.lostcities.config;

import mcjty.lostcities.LostCities;
import mcjty.lostcities.setup.ModSetup;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.FMLLog;
import org.apache.logging.log4j.Level;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * This class is responsible for setting up and managing the configuration files for the Lost Cities mod.
 */
public class ConfigSetup {

    /**
     * The main configuration file for the mod.
     */
    private static Configuration mainConfig;

    /**
     * A map of profile configurations, keyed by their names.
     */
    public static Map<String, Configuration> profileConfigs = new HashMap<>();

    /**
     * Initializes the configuration files.
     */
    public static void init() {
        // Initialize the main configuration file
        mainConfig = new Configuration(new File(ModSetup.modConfigDir.getPath() + File.separator + "lostcities", "general.cfg"));
        Configuration cfg = mainConfig;

        try {
            // Load the configuration files
            cfg.load();

            // Initialize the profile lists
            String[] profileList = LostCityConfiguration.init(cfg);
            initProfiles(profileList, true);
            profileList = LostCityConfiguration.getPrivateProfiles(cfg);
            initProfiles(profileList, false);

            // Fix any deprecated or incorrect configurations
            fixConfigs();
        } catch (Exception e1) {
            // Log any errors that occur during configuration loading
            FMLLog.log(Level.ERROR, e1, "Problem loading config file!");
        } finally {
            // Save any changes made to the configuration files
            if (mainConfig.hasChanged()) {
                mainConfig.save();
            }
            for (Configuration config : profileConfigs.values()) {
                if (config.hasChanged()) {
                    config.save();
                }
            }
        }
    }

    /**
     * Initializes the profile configurations.
     *
     * @param profileList The list of profile names to initialize.
     * @param isPublic    Whether the profiles are public or private.
     */
    private static void initProfiles(String[] profileList, boolean isPublic) {
        for (String name : profileList) {
            // Create a new profile and load its configuration
            LostCityProfile profile = new LostCityProfile(name, LostCityConfiguration.standardProfiles.get(name), isPublic);
            Configuration profileCfg = new Configuration(new File(ModSetup.modConfigDir.getPath() + File.separator + "lostcities", "profile_" + name + ".cfg"));
            profileCfg.load();

            // Initialize the profile with its configuration
            profile.init(profileCfg);

            // Add the profile to the map of profiles
            LostCityConfiguration.profiles.put(name, profile);
            profileConfigs.put(name, profileCfg);
        }
    }

    /**
     * Fixes any deprecated or incorrect configurations.
     */
    private static void fixConfigs() {
        for (Map.Entry<String, LostCityProfile> entry : LostCityConfiguration.profiles.entrySet()) {
            String name = entry.getKey();
            LostCityProfile profile = entry.getValue();

            // Fix deprecated 'outsideGroundLevel' configuration
            if (profile.CITYSPHERE_OUTSIDE_GROUNDLEVEL!= -1) {
                if (!profile.CITYSPHERE_OUTSIDE_PROFILE.isEmpty()) {
                    String otherName = profile.CITYSPHERE_OUTSIDE_PROFILE;
                    LostCityProfile otherProfile = LostCityConfiguration.profiles.get(otherName);

                    if (otherProfile!= null) {
                        // Log a message indicating the migration of the deprecated configuration
                        LostCities.setup.getLogger().info("Migrating deprecated 'outsideGroundLevel' from '" + name + "' to '" + otherName + "'");

                        // Migrate the deprecated configuration to the other profile
                        otherProfile.GROUNDLEVEL = profile.CITYSPHERE_OUTSIDE_GROUNDLEVEL;
                        otherProfile.WATERLEVEL_OFFSET = profile.WATERLEVEL_OFFSET;
                        profileConfigs.get(otherName).getCategory(otherProfile.getCategoryLostcity()).get("groundLevel").set(otherProfile.GROUNDLEVEL);
                        profileConfigs.get(otherName).getCategory(otherProfile.getCategoryLostcity()).get("waterLevelOffset").set(otherProfile.WATERLEVEL_OFFSET);
                    }
                }

                // Reset the deprecated configuration in the current profile
                profile.CITYSPHERE_OUTSIDE_GROUNDLEVEL = -1;
                profile.WATERLEVEL_OFFSET = 8;
                profileConfigs.get(name).getCategory(profile.getCategoryCitySpheres()).get("outsideGroundLevel").set(profile.CITYSPHERE_OUTSIDE_GROUNDLEVEL);
                profileConfigs.get(name).getCategory(profile.getCategoryLostcity()).get("waterLevelOffset").set(profile.WATERLEVEL_OFFSET);
            }
        }
    }

    /**
     * Saves any changes made to the configuration files.
     */
    public static void postInit() {
        if (mainConfig.hasChanged()) {
            mainConfig.save();
        }
        for (Configuration config : profileConfigs.values()) {
            if (config.hasChanged()) {
                config.save();
            }
        }
    }
}
