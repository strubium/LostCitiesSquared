package mcjty.lostcities.varia;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Teleporter;
import net.minecraft.world.WorldServer;

import javax.annotation.Nonnull;

/**
 * This class is used to teleport entities to a specific location in a given dimension.
 * 
 * @author mcjty
 */
public class CustomTeleporter extends Teleporter {

    /**
     * Constructor for CustomTeleporter.
     *
     * @param world The WorldServer instance to teleport to.
     * @param x The x-coordinate of the destination.
     * @param y The y-coordinate of the destination.
     * @param z The z-coordinate of the destination.
     */
    public CustomTeleporter(WorldServer world, double x, double y, double z) {
        super(world);
        this.worldServer = world;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    private final WorldServer worldServer;
    private double x, y, z;

    /**
     * {@inheritDoc}
     * This method is overridden to place the entity in the portal at the specified coordinates.
     */
    @Override
    public void placeInPortal(@Nonnull Entity entity, float rotationYaw) {
        this.worldServer.getBlockState(new BlockPos((int) this.x, (int) this.y, (int) this.z));

        entity.setPosition(this.x, this.y, this.z);
        entity.motionX = 0.0f;
        entity.motionY = 0.0f;
        entity.motionZ = 0.0f;
    }

    /**
     * Static method to teleport an entity to a specific dimension using a BlockPos.
     *
     * @param player The entity to teleport.
     * @param dimension The dimension to teleport to.
     * @param pos The BlockPos to teleport to.
     */
    public static void teleportToDimension(EntityPlayer player, int dimension, BlockPos pos){
        teleportToDimension(player, dimension, pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5);
    }

    /**
     * Static method to teleport an entity to a specific dimension using coordinates.
     *
     * @param player The entity to teleport.
     * @param dimension The dimension to teleport to.
     * @param x The x-coordinate to teleport to.
     * @param y The y-coordinate to teleport to.
     * @param z The z-coordinate to teleport to.
     * @throws IllegalArgumentException If the specified dimension does not exist.
     */
    public static void teleportToDimension(EntityPlayer player, int dimension, double x, double y, double z) {
        int oldDimension = player.getEntityWorld().provider.getDimension();
        EntityPlayerMP entityPlayerMP = (EntityPlayerMP) player;
        MinecraftServer server = ((EntityPlayerMP) player).getEntityWorld().getMinecraftServer();
        WorldServer worldServer = server.getWorld(dimension);
        player.addExperienceLevel(0);

        if (worldServer == null || worldServer.getMinecraftServer() == null){ //Dimension doesn't exist
            throw new IllegalArgumentException("Dimension: "+dimension+" doesn't exist!");
        }

        worldServer.getMinecraftServer().getPlayerList().transferPlayerToDimension(entityPlayerMP, dimension, new CustomTeleporter(worldServer, x, y, z));
        player.setPositionAndUpdate(x, y, z);
        if (oldDimension == 1) {
            // For some reason teleporting out of the end does weird things.
            player.setPositionAndUpdate(x, y, z);
            worldServer.spawnEntity(player);
            worldServer.updateEntityWithOptionalForce(player, false);
        }
    }

}