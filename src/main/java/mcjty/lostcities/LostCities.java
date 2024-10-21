package mcjty.lostcities;

import mcjty.lostcities.api.ILostCities;
import mcjty.lostcities.commands.CommandBuildPart;
import mcjty.lostcities.commands.CommandDebug;
import mcjty.lostcities.commands.CommandExportBuilding;
import mcjty.lostcities.commands.CommandExportPart;
import mcjty.lostcities.dimensions.world.WorldTypeTools;
import mcjty.lostcities.dimensions.world.lost.*;
import mcjty.lostcities.setup.IProxy;
import mcjty.lostcities.setup.ModSetup;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.*;

import java.util.Optional;
import java.util.function.Function;

/**
 * The main class of The Lost Cities Squared
 * This class is responsible for initializing the mod and setting up the proxy
 */
@Mod(modid = LostCities.MODID, name="The Lost Cities Squared",
        dependencies =
                        "after:forge@[" + LostCities.MIN_FORGE11_VER + ",)",
        version = LostCities.VERSION,
        acceptedMinecraftVersions = "[1.12, 1.13)",
        acceptableRemoteVersions = "*")
public class LostCities {
    public static final String MODID = "lostcitiessquared";
    public static final String VERSION = "Dev-5";
    public static final String MIN_FORGE11_VER = "13.19.0.2176";

    /**
     * The proxy instance.
     * This instance is used to separate the client-side and server-side logic.
     */
    @SidedProxy(clientSide="mcjty.lostcities.setup.ClientProxy", serverSide="mcjty.lostcities.setup.ServerProxy")
    public static IProxy proxy;

    /**
     * The mod setup instance.
     * This instance is responsible for setting up the mod's configuration and other initialization tasks.
     */
    public static ModSetup setup = new ModSetup();

    /**
     * The mod instance.
     * This instance is used to access the mod's main class from other parts of the code.
     */
    @Mod.Instance("lostcitiessquared")
    public static LostCities instance;

    /**
     * The implementation of the ILostCities interface.
     * This instance provides the mod's functionality to other mods through inter-mod communication.
     */
    public static LostCitiesImp lostCitiesImp = new LostCitiesImp();

    /**
     * This method is called before the mod is initialized.
     * It is used to set up the mod's configuration and other pre-initialization tasks.
     *
     * @param e The FMLPreInitializationEvent object containing information about the pre-initialization event.
     */
    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent e) {
        setup.preInit(e);
        proxy.preInit(e);
    }

    /**
     * This method is called after the mod is initialized.
     * It is used to perform additional initialization tasks.
     *
     * @param e The FMLInitializationEvent object containing information about the initialization event.
     */
    @Mod.EventHandler
    public void init(FMLInitializationEvent e) {
        setup.init(e);
        proxy.init(e);
    }

    /**
     * This method is called after the mod is fully initialized.
     * It is used to perform post-initialization tasks.
     *
     * @param e The FMLPostInitializationEvent object containing information about the post-initialization event.
     */
    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent e) {
        setup.postInit(e);
        proxy.postInit(e);
    }

    /**
     * This method is called when the server is starting.
     * It is used to register server-side commands.
     *
     * @param event The FMLServerStartingEvent object containing information about the server starting event.
     */
    @Mod.EventHandler
    public void serverLoad(FMLServerStartingEvent event) {
        event.registerServerCommand(new CommandDebug());
        event.registerServerCommand(new CommandExportBuilding());
        event.registerServerCommand(new CommandExportPart());
        event.registerServerCommand(new CommandBuildPart());
        cleanCaches();
    }

    /**
     * This method is called when the server is stopped.
     * It is used to clean up any resources or caches.
     *
     * @param event The FMLServerStoppedEvent object containing information about the server stopped event.
     */
    @Mod.EventHandler
    public void serverStopped(FMLServerStoppedEvent event) {
        cleanCaches();
        WorldTypeTools.cleanChunkGeneratorMap();
    }

    /**
     * This method is used to clean up any caches or resources used by the mod.
     */
    private void cleanCaches() {
        BuildingInfo.cleanCache();
        Highway.cleanCache();
        Railway.cleanCache();
        BiomeInfo.cleanCache();
        City.cleanCache();
        CitySphere.cleanCache();
        WorldTypeTools.cleanCache();
    }

    /**
     * This method is called when inter-mod communication (IMC) messages are received.
     * It is used to handle specific IMC messages related to the mod.
     *
     * @param event The FMLInterModComms.IMCEvent object containing information about the IMC event.
     */
    @Mod.EventHandler
    public void imcCallback(FMLInterModComms.IMCEvent event) {
        for (FMLInterModComms.IMCMessage message : event.getMessages()) {
            if (message.key.equalsIgnoreCase("getLostCities")) {
                Optional<Function<ILostCities, Void>> value = message.getFunctionValue(ILostCities.class, Void.class);
                if (value.isPresent()) {
                    value.get().apply(lostCitiesImp);
                } else {
                    setup.getLogger().warn("Some mod didn't return a valid result with getLostCities!");
                }
            }
        }
    }
}
