package mcjty.lostcities.setup;

import com.google.common.util.concurrent.ListenableFuture;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import java.util.concurrent.Callable;

/**
 * This interface represents a proxy for the mod.
 * It separates the client-side and server-side logic.
 */
public interface IProxy {

    /**
     * This method is called during the pre-initialization phase.
     * It is used for setting up configurations, registering event handlers, etc.
     *
     * @param e The FMLPreInitializationEvent object containing information about the mod environment.
     */
    void preInit(FMLPreInitializationEvent e);

    /**
     * This method is called during the initialization phase.
     * It is used for registering game objects, recipes, etc.
     *
     * @param e The FMLInitializationEvent object containing information about the mod environment.
     */
    void init(FMLInitializationEvent e);

    /**
     * This method is called during the post-initialization phase.
     * It is used for finalizing setup, such as loading resources, etc.
     *
     * @param e The FMLPostInitializationEvent object containing information about the mod environment.
     */
    void postInit(FMLPostInitializationEvent e);

    /**
     * This method returns the client-side world.
     *
     * @return The client-side world.
     */
    World getClientWorld();

    /**
     * This method returns the client-side player.
     *
     * @return The client-side player.
     */
    EntityPlayer getClientPlayer();

    /**
     * This method adds a scheduled task to the client-side thread.
     *
     * @param callableToSchedule The callable object to be scheduled.
     * @param <V> The type of the result returned by the callable.
     * @return A ListenableFuture representing the scheduled task.
     */
    <V> ListenableFuture<V> addScheduledTaskClient(Callable<V> callableToSchedule);

    /**
     * This method adds a scheduled task to the client-side thread.
     *
     * @param runnableToSchedule The runnable object to be scheduled.
     * @return A ListenableFuture representing the scheduled task.
     */
    ListenableFuture<Object> addScheduledTaskClient(Runnable runnableToSchedule);
}
