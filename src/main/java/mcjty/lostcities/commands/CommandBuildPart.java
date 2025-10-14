package mcjty.lostcities.commands;

import mcjty.lostcities.dimensions.world.LostCityChunkGenerator;
import mcjty.lostcities.dimensions.world.WorldTypeTools;
import mcjty.lostcities.dimensions.world.lost.BuildingInfo;
import mcjty.lostcities.dimensions.world.lost.cityassets.AssetRegistries;
import mcjty.lostcities.dimensions.world.lost.cityassets.BuildingPart;
import mcjty.lostcities.dimensions.world.lost.cityassets.CompiledPalette;
import net.minecraft.block.state.IBlockState;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;

import javax.annotation.Nullable;
import java.util.Collections;
import java.util.List;

/**
 * This class represents a command to build a specific part in a lost city.
 * It implements the {@link ICommand} interface from Minecraft.
 */
public class CommandBuildPart implements ICommand {

    /**
     * Returns the name of the command.
     *
     * @return The name of the command.
     */
    @Override
    public String getName() {
        return "lc_buildpart";
    }

    /**
     * Returns the usage of the command.
     *
     * @param sender The sender of the command.
     * @return The usage of the command.
     */
    @Override
    public String getUsage(ICommandSender sender) {
        return getName() + " <partname>";
    }

    /**
     * Returns an empty list of aliases for the command.
     *
     * @return An empty list of aliases.
     */
    @Override
    public List<String> getAliases() {
        return Collections.emptyList();
    }

    /**
     * Executes the command.
     *
     * @param server The Minecraft server.
     * @param sender The sender of the command.
     * @param args The arguments of the command.
     * @throws CommandException If an error occurs during command execution.
     */
    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        try {
            // Get the part name from the command arguments
            String partname = args[0];

            // Get the building part from the asset registry
            BuildingPart part = AssetRegistries.PARTS.get(partname);

            // If the part is not found, send a message to the sender and return
            if (part == null) {
                sender.sendMessage(new TextComponentString("Cannot find part '" + partname + "'!"));
                return;
            }

            // Get the player's position
            EntityPlayer player = (EntityPlayer) sender;
            BlockPos start = player.getPosition();

            // Get the chunk generator for the lost city dimension
            LostCityChunkGenerator provider = WorldTypeTools.getChunkGenerator(sender.getEntityWorld().provider.getDimension());

            // Get the building info for the chunk containing the player's position
            BuildingInfo info = BuildingInfo.getBuildingInfo(start.getX() >> 4, start.getZ() >> 4, provider);

            // Get the compiled palette for the building
            CompiledPalette palette = info.getCompiledPalette();

            // Loop through each slice of the building part
            for (int y = 0 ; y < part.getSliceCount() ; y++) {
                // Loop through each x and z coordinate of the slice
                for (int x = 0; x < part.getXSize(); x++) {
                    for (int z = 0; z < part.getZSize(); z++) {
                        // Calculate the block position
                        BlockPos pos = new BlockPos(info.chunkX*16+x, start.getY()+y, info.chunkZ*16+z);

                        // Get the character representing the block state
                        Character character = part.getC(x, y, z);

                        // Get the block state from the palette
                        IBlockState state = palette.getStraight(character);

                        // If the block state is not null and not a command block, set the block state
                        if (state!= null && state.getBlock()!= Blocks.COMMAND_BLOCK) {
                            try {
                                sender.getEntityWorld().setBlockState(pos, state, 3);
                            } catch (Exception e) {
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            // Print the stack trace if an error occurs
            e.printStackTrace();
        }
    }

    /**
     * Checks if the sender has permission to execute the command.
     *
     * @param server The Minecraft server.
     * @param sender The sender of the command.
     * @return True if the sender has permission, false otherwise.
     */
    @Override
    public boolean checkPermission(MinecraftServer server, ICommandSender sender) {
        return true;
    }

    /**
     * Returns an empty list of tab completions for the command.
     *
     * @param server The Minecraft server.
     * @param sender The sender of the command.
     * @param args The arguments of the command.
     * @param targetPos The target position for the command.
     * @return An empty list of tab completions.
     */
    @Override
    public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, @Nullable BlockPos targetPos) {
        return Collections.emptyList();
    }

    /**
     * Checks if the given index is a username index.
     *
     * @param args The arguments of the command.
     * @param index The index to check.
     * @return False for all indices.
     */
    @Override
    public boolean isUsernameIndex(String[] args, int index) {
        return false;
    }

    /**
     * Compares this command to another command.
     *
     * @param o The other command to compare to.
     * @return The result of the comparison.
     */
    @Override
    public int compareTo(ICommand o) {
        return getName().compareTo(o.getName());
    }

    /**
     * This class represents a slice of a building part.
     */
    public static class Slice {
        // A sequence of strings representing the blocks in the slice
        String sequence[] = new String[256];
    }
}