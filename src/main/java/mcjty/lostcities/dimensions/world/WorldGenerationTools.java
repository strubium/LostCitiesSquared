package mcjty.lostcities.dimensions.world;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * This class provides various utility methods for world generation in the Lost Cities mod.
 */
public class WorldGenerationTools {

    /**
     * Finds an upside-down empty spot in the world.
     *
     * @param world The world to search in.
     * @param x The x-coordinate of the starting position.
     * @param z The z-coordinate of the starting position.
     * @return The y-coordinate of the found empty spot, or -1 if no suitable spot was found.
     */
    public static int findUpsideDownEmptySpot(World world, int x, int z) {
        for (int y = 90 ; y > 0 ; y--) {
            if (world.isAirBlock(new BlockPos(x, y, z)) && world.isAirBlock(new BlockPos(x, y+1, z)) && world.isAirBlock(new BlockPos(x, y+2, z))
                    && world.isAirBlock(new BlockPos(x, y+3, z)) && world.isAirBlock(new BlockPos(x, y+4, z))) {
                return y;
            }
        }
        return -1;
    }

    /**
     * Finds a suitable empty spot in the world.
     *
     * @param world The world to search in.
     * @param x The x-coordinate of the starting position.
     * @param z The z-coordinate of the starting position.
     * @return The y-coordinate of the found empty spot, or -1 if no suitable spot was found.
     */
    public static int findSuitableEmptySpot(World world, int x, int z) {
        int y = world.getTopSolidOrLiquidBlock(new BlockPos(x, 0, z)).getY();
        if (y == -1) {
            return -1;
        }

        y--;            // y should now be at a solid or liquid block.

        if (y > world.getHeight() - 5) {
            y = world.getHeight() / 2;
        }

        IBlockState state = world.getBlockState(new BlockPos(x, y + 1, z));
        Block block = state.getBlock();
        while (block.getMaterial(state).isLiquid()) {
            y++;
            if (y > world.getHeight()-10) {
                return -1;
            }
            state = world.getBlockState(new BlockPos(x, y + 1, z));
            block = state.getBlock();
        }

        return y;
    }

    /**
     * Checks if a block is solid.
     *
     * @param world The world to check in.
     * @param x The x-coordinate of the block.
     * @param y The y-coordinate of the block.
     * @param z The z-coordinate of the block.
     * @return True if the block is solid, false otherwise.
     */
    public static boolean isSolid(World world, int x, int y, int z) {
        if (world.isAirBlock(new BlockPos(x, y, z))) {
            return false;
        }
        IBlockState state = world.getBlockState(new BlockPos(x, y, z));
        Block block = state.getBlock();
        return block.getMaterial(state).blocksMovement();
    }

    /**
     * Checks if a block is air.
     *
     * @param world The world to check in.
     * @param x The x-coordinate of the block.
     * @param y The y-coordinate of the block.
     * @param z The z-coordinate of the block.
     * @return True if the block is air, false otherwise.
     */
    public static boolean isAir(World world, int x, int y, int z) {
        if (world.isAirBlock(new BlockPos(x, y, z))) {
            return true;
        }
        Block block = world.getBlockState(new BlockPos(x, y, z)).getBlock();
        return block == null;
    }

    /**
     * Starting at the current height, go down and fill all air blocks with stone until a
     * non-air block is encountered.
     *
     * @param world The world to modify.
     * @param x The x-coordinate of the starting position.
     * @param y The y-coordinate of the starting position.
     * @param z The z-coordinate of the starting position.
     */
    public static void fillEmptyWithStone(World world, int x, int y, int z) {
        while (y > 0 &&!isSolid(world, x, y, z)) {
            world.setBlockState(new BlockPos(x, y, z), Blocks.STONE.getDefaultState(), 2);
            y--;
        }
    }
}