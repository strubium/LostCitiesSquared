package mcjty.lostcities.setup;

import com.google.common.util.concurrent.ListenableFuture;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import java.util.concurrent.Callable;

/**
 * This class represents the server-side proxy for the mod.
 * It implements the IProxy interface and provides methods for server-side operations.
 * The methods in this class are called by the mod's main class during the respective FML lifecycle events.
 */
public class ServerProxy implements IProxy {

    /**
     * This method is called during the FMLPreInitializationEvent.
     * It is used for any necessary setup or initialization tasks that should be performed before the game starts.
     *
     * @param e The FMLPreInitializationEvent object containing information about the event.
     */
    @Override
    public void preInit(FMLPreInitializationEvent e) {
    }

    /**
     * This method is called during the FMLInitializationEvent.
     * It is used for any necessary setup or initialization tasks that should be performed after the game has started.
     *
     * @param e The FMLInitializationEvent object containing information about the event.
     */
    @Override
    public void init(FMLInitializationEvent e) {
    }

    /**
     * This method is called during the FMLPostInitializationEvent.
     * It is used for any necessary setup or initialization tasks that should be performed after all other mods have been initialized.
     *
     * @param e The FMLPostInitializationEvent object containing information about the event.
     */
    @Override
    public void postInit(FMLPostInitializationEvent e) {
    }

    /**
     * This method is intended to retrieve the client-side world.
     * However, it throws an IllegalStateException because it should only be called from the client side.
     *
     * @return The client-side world.
     * @throws IllegalStateException If called from the server side.
     */
    @Override
    public World getClientWorld() {
        throw new IllegalStateException("This should only be called from client side");
    }

    /**
     * This method is intended to retrieve the client-side player.
     * However, it throws an IllegalStateException because it should only be called from the client side.
     *
     * @return The client-side player.
     * @throws IllegalStateException If called from the server side.
     */
    @Override
    public EntityPlayer getClientPlayer() {
        throw new IllegalStateException("This should only be called from client side");
    }

    /**
     * This method is intended to schedule a callable task on the client-side.
     * However, it throws an IllegalStateException because it should only be called from the client side.
     *
     * @param callableToSchedule The callable task to schedule.
     * @return A ListenableFuture representing the scheduled task.
     * @throws IllegalStateException If called from the server side.
     */
    @Override
    public <V> ListenableFuture<V> addScheduledTaskClient(Callable<V> callableToSchedule) {
        throw new IllegalStateException("This should only be called from client side");
    }

    /**
     * This method is intended to schedule a runnable task on the client-side.
     * However, it throws an IllegalStateException because it should only be called from the client side.
     *
     * @param runnableToSchedule The runnable task to schedule.
     * @return A ListenableFuture representing the scheduled task.
     * @throws IllegalStateException If called from the server side.
     */
    @Override
    public ListenableFuture<Object> addScheduledTaskClient(Runnable runnableToSchedule) {
        throw new IllegalStateException("This should only be called from client side");
    }

}