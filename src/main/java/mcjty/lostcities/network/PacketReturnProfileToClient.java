package mcjty.lostcities.network;

import io.netty.buffer.ByteBuf;
import mcjty.lostcities.LostCities;
import mcjty.lostcities.dimensions.world.WorldTypeTools;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

/**
 * This class represents a network packet sent from the server to the client in response to a PacketRequestProfile message.
 * It contains the dimension and the name of the Lost Cities profile associated with that dimension.
 */
public class PacketReturnProfileToClient implements IMessage {

    private int dimension;
    private String profile;

    /**
     * Default constructor for PacketReturnProfileToClient.
     */
    public PacketReturnProfileToClient() {
    }

    /**
     * Constructor for PacketReturnProfileToClient.
     *
     * @param dimension The dimension for which the profile is returned.
     * @param profileName The name of the Lost Cities profile associated with the dimension.
     */
    public PacketReturnProfileToClient(int dimension, String profileName) {
        this.dimension = dimension;
        this.profile = profileName;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        dimension = buf.readInt(); // Read the dimension from the byte buffer.
        profile = NetworkTools.readString(buf); // Read the profile name from the byte buffer.
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(dimension); // Write the dimension to the byte buffer.
        NetworkTools.writeString(buf, profile); // Write the profile name to the byte buffer.
    }

    /**
     * This static inner class handles the incoming PacketReturnProfileToClient message.
     */
    public static class Handler implements IMessageHandler<PacketReturnProfileToClient, IMessage> {

        @Override
        public IMessage onMessage(PacketReturnProfileToClient message, MessageContext ctx) {
            // Run the handle method on the client thread.
            LostCities.proxy.addScheduledTaskClient(() -> handle(message, ctx));
            return null;
        }

        /**
         * This method handles the incoming PacketReturnProfileToClient message.
         *
         * @param message The incoming message.
         * @param ctx     The message context.
         */
        private void handle(PacketReturnProfileToClient message, MessageContext ctx) {
            WorldTypeTools.setProfileFromServer(message.dimension, message.profile); // Set the Lost Cities profile for the specified dimension.
        }
    }
}
