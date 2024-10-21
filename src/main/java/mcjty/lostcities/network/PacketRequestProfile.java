package mcjty.lostcities.network;

import io.netty.buffer.ByteBuf;
import mcjty.lostcities.config.LostCityProfile;
import mcjty.lostcities.dimensions.world.WorldTypeTools;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

/**
 * This class represents a network packet sent from the client to the server to request a Lost Cities profile.
 */
public class PacketRequestProfile implements IMessage {

    private int dimension;

    /**
     * Default constructor for PacketRequestProfile.
     */
    public PacketRequestProfile() {
    }

    /**
     * Constructor for PacketRequestProfile.
     *
     * @param dimension The dimension for which the profile is requested.
     */
    public PacketRequestProfile(int dimension) {
        this.dimension = dimension;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        dimension = buf.readInt(); // Read the dimension from the byte buffer.
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(dimension); // Write the dimension to the byte buffer.
    }

    /**
     * This static inner class handles the incoming PacketRequestProfile message.
     */
    public static class Handler implements IMessageHandler<PacketRequestProfile, IMessage> {

        @Override
        public IMessage onMessage(PacketRequestProfile message, MessageContext ctx) {
            // Run the handle method on the world thread.
            FMLCommonHandler.instance().getWorldThread(ctx.netHandler).addScheduledTask(() -> handle(message, ctx));
            return null;
        }

        /**
         * This method handles the incoming PacketRequestProfile message.
         *
         * @param message The incoming message.
         * @param ctx     The message context.
         */
        private void handle(PacketRequestProfile message, MessageContext ctx) {
            EntityPlayerMP player = ctx.getServerHandler().player; // Get the player associated with the message context.
            LostCityProfile profile = WorldTypeTools.getProfile(DimensionManager.getWorld(message.dimension)); // Get the Lost Cities profile for the specified dimension.
            PacketHandler.INSTANCE.sendTo(new PacketReturnProfileToClient(message.dimension, profile.getName()), player); // Send a response packet back to the client.
        }
    }
}
