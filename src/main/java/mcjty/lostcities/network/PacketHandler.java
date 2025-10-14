package mcjty.lostcities.network;


import mcjty.lostcities.LostCities;
import mcjty.lostcities.setup.ModSetup;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

/**
 * This class is responsible for handling network packets related to the Lost Cities mod.
 * It uses the SimpleNetworkWrapper provided by Forge to register and handle messages.
 */
public class PacketHandler {
    private static int ID = 12;

    /**
     * The static instance of SimpleNetworkWrapper used for registering and handling messages.
     */
    public static SimpleNetworkWrapper INSTANCE = null;

    /**
     * Constructor for PacketHandler.
     * Currently, it does nothing.
     */
    public PacketHandler() {
    }

    /**
     * Returns the next available ID.
     *
     * @return the next available ID
     */
    public static int nextID() {
        return ID++;
    }

    /**
     * Registers the network messages using the provided channel name.
     *
     * @param channelName the name of the network channel
     */
    public static void registerChannelAndMessages(String channelName) {
        try {
        INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(channelName);
        } catch (Exception e) {
            LostCities.setup.getLogger().error("Error Registering Channel: {}, {}", channelName, e.getMessage());
        }
        registerMessages();
    }

    /**
     * Registers the network messages for both the server and client sides.
     */
    private static void registerMessages() {
        // Server side
        INSTANCE.registerMessage(PacketRequestProfile.Handler.class, PacketRequestProfile.class, nextID(), Side.SERVER);

        // Client side
        INSTANCE.registerMessage(PacketReturnProfileToClient.Handler.class, PacketReturnProfileToClient.class, nextID(), Side.CLIENT);
    }
}
