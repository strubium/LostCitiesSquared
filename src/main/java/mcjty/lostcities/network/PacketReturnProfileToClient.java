package mcjty.lostcities.network;

import io.netty.buffer.ByteBuf;
import mcjty.lostcities.LostCities;
import mcjty.lostcities.dimensions.world.WorldTypeTools;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

/**
 * This class represents a network packet sent from the server to the client.
 * The packet contains the dimension and profile information for a lost city world.
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
     * @param dimension The dimension ID of the lost city world.
     * @param profileName The profile name of the lost city world.
     */
    public PacketReturnProfileToClient(int dimension, String profileName) {
        this.dimension = dimension;
        this.profile = profileName;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        dimension = buf.readInt();
        profile = NetworkTools.readString(buf);
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(dimension);
        NetworkTools.writeString(buf, profile);
    }

    /**
     * This static inner class handles the incoming packet.
     */
    public static class Handler implements IMessageHandler<PacketReturnProfileToClient, IMessage> {

        @Override
        public IMessage onMessage(PacketReturnProfileToClient message, MessageContext ctx) {
            LostCities.proxy.addScheduledTaskClient(() -> handle(message, ctx));
            return null;
        }

        /**
         * This method processes the incoming packet.
         *
         * @param message The received packet.
         * @param ctx The message context.
         */
        private void handle(PacketReturnProfileToClient message, MessageContext ctx) {
            WorldTypeTools.setProfileFromServer(message.dimension, message.profile);
        }
    }
}
