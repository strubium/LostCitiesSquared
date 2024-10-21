package mcjty.lostcities.setup;

import com.google.common.util.concurrent.ListenableFuture;
import mcjty.lostcities.ClientEventHandlers;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import java.util.concurrent.Callable;

/**
 * This class represents the client-side proxy for the mod. It implements the IProxy interface.
 * It provides methods to interact with the client-side Minecraft environment.
 */
public class ClientProxy implements IProxy {

    /**
     * This method is called during the pre-initialization phase of the mod on the client-side.
     * It is used to perform any necessary setup before the initialization phase.
     *
     * @param e The FMLPreInitializationEvent object containing information about the mod environment.
     */
    @Override
    public void preInit(FMLPreInitializationEvent e) {

    }

    /**
     * This method is called during the initialization phase of the mod on the client-side.
     * It is used to register event handlers and perform any necessary initialization tasks.
     *
     * @param e The FMLInitializationEvent object containing information about the mod environment.
     */
    @Override
    public void init(FMLInitializationEvent e) {
        MinecraftForge.EVENT_BUS.register(new ClientEventHandlers());
    }

    /**
     * This method is called during the post-initialization phase of the mod on the client-side.
     * It is used to perform any necessary cleanup or finalization tasks after the initialization phase.
     *
     * @param e The FMLPostInitializationEvent object containing information about the mod environment.
     */
    @Override
    public void postInit(FMLPostInitializationEvent e) {

    }

    /**
     * This method returns the client-side world object.
     *
     * @return The client-side world object.
     */
    @Override
    public World getClientWorld() {
        return Minecraft.getMinecraft().world;
    }

    /**
     * This method returns the client-side player object.
     *
     * @return The client-side player object.
     */
    @Override
    public EntityPlayer getClientPlayer() {
        return Minecraft.getMinecraft().player;
    }

    /**
     * This method adds a scheduled task to the client-side event loop.
     *
     * @param callableToSchedule The callable object to be executed.
     * @return A ListenableFuture object representing the scheduled task.
     */
    @Override
    public <V> ListenableFuture<V> addScheduledTaskClient(Callable<V> callableToSchedule) {
        return Minecraft.getMinecraft().addScheduledTask(callableToSchedule);
    }

    /**
     * This method adds a scheduled task to the client-side event loop.
     *
     * @param runnableToSchedule The runnable object to be executed.
     * @return A ListenableFuture object representing the scheduled task.
     */
    @Override
    public ListenableFuture<Object> addScheduledTaskClient(Runnable runnableToSchedule) {
        return Minecraft.getMinecraft().addScheduledTask(runnableToSchedule);
    }

}