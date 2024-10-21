package mcjty.lostcities.setup;

import mcjty.lostcities.ForgeEventHandlers;
import mcjty.lostcities.LostCities;
import mcjty.lostcities.TerrainEventHandlers;
import mcjty.lostcities.config.ConfigSetup;
import mcjty.lostcities.config.LostCityConfiguration;
import mcjty.lostcities.dimensions.ModDimensions;
import mcjty.lostcities.dimensions.world.lost.cityassets.AssetRegistries;
import mcjty.lostcities.network.PacketHandler;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;

import static mcjty.lostcities.config.LostCityConfiguration.MWC_LOOT;

/**
 * This class is responsible for setting up the mod, including registering mod compatibility,
 * initializing loot tables, and loading asset registries.
 */
public class ModSetup {

    /**
     * Flags indicating whether certain mods are loaded.
     */
    public static boolean chisel = false;
    public static boolean biomesoplenty = false;
    public static boolean atg = false;
    public static boolean neid = false;
    public static boolean jeid = false;
    public static boolean reid = false;
    public static boolean mwc = false;
    public static boolean cubicchunks = false;

    private Logger logger;
    public static File modConfigDir;

    /**
     * This method is called during the pre-initialization phase of the mod.
     * It initializes the logger, registers network messages, sets up mod compatibility,
     * and initializes the mod configuration directory and other necessary components.
     *
     * @param e The FMLPreInitializationEvent
     */
    public void preInit(FMLPreInitializationEvent e) {
        logger = e.getModLog();
        PacketHandler.registerMessages("lostcitiessquared");

        setupModCompat();

        modConfigDir = e.getModConfigurationDirectory();
        ConfigSetup.init();
        ModDimensions.init();

        LootTableList.register(new ResourceLocation(LostCities.MODID, "chests/lostcitychest"));
        LootTableList.register(new ResourceLocation(LostCities.MODID, "chests/lostcitychestFood"));
        LootTableList.register(new ResourceLocation(LostCities.MODID, "chests/lostcitychestOffice"));
        LootTableList.register(new ResourceLocation(LostCities.MODID, "chests/raildungeonchest"));

        if(MWC_LOOT = true){
            LootTableList.register(new ResourceLocation(LostCities.MODID, "chests/lostcitychestMWC"));
        }
    }

    /**
     * This method sets up mod compatibility by checking if certain mods are loaded.
     */
    private void setupModCompat() {
        chisel = Loader.isModLoaded("chisel");
        biomesoplenty = Loader.isModLoaded("biomesoplenty") || Loader.isModLoaded("BiomesOPlenty");
        atg = Loader.isModLoaded("atg"); // @todo This does nothing
        neid = Loader.isModLoaded("neid");
        jeid = Loader.isModLoaded("jeid");
        reid = Loader.isModLoaded("reid");
        mwc = Loader.isModLoaded("mwc");
        cubicchunks = Loader.isModLoaded("cubicchunks");
    }

    /**
     * This method returns the logger instance.
     *
     * @return The logger instance
     */
    public Logger getLogger() {
        return logger;
    }

    /**
     * This method is called during the initialization phase of the mod.
     * It registers event handlers for Forge and terrain generation events.
     *
     * @param e The FMLInitializationEvent
     */
    public void init(FMLInitializationEvent e) {
        MinecraftForge.EVENT_BUS.register(new ForgeEventHandlers());
        MinecraftForge.TERRAIN_GEN_BUS.register(new TerrainEventHandlers());
    }

    /**
     * This method is called during the post-initialization phase of the mod.
     * It processes the mod configuration, clears profile configurations,
     * resets asset registries, loads asset registries from files or resources,
     * and logs statistics if debug mode is enabled.
     *
     * @param e The FMLPostInitializationEvent
     */
    public void postInit(FMLPostInitializationEvent e) {
        ConfigSetup.postInit();
        ConfigSetup.profileConfigs.clear();

        AssetRegistries.reset();
        for (String path : LostCityConfiguration.ASSETS) {
            if (path.startsWith("/")) {
                try(InputStream inputstream = LostCities.class.getResourceAsStream(path)) {
                    AssetRegistries.load(inputstream, path);
                } catch (IOException ex) {
                    throw new UncheckedIOException(ex);
                }
            } else if (path.startsWith("$")) {
                File file = new File(modConfigDir.getPath() + File.separator + path.substring(1));
                AssetRegistries.load(file);
            } else {
                throw new RuntimeException("Invalid path for lostcity resource in 'assets' config!");
            }
        }

        if (LostCityConfiguration.DEBUG) {
            logger.info("Asset parts loaded: " + AssetRegistries.PARTS.getCount());
            AssetRegistries.showStatistics();
        }
    }
}