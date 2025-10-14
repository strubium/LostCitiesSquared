package mcjty.lostcities.dimensions.world.driver;

import net.minecraft.block.state.IBlockState;
import net.minecraft.world.chunk.ChunkPrimer;

public interface IPrimerDriver {

    void setPrimer(ChunkPrimer primer);

    ChunkPrimer getPrimer();

    IPrimerDriver current(int x, int y, int z);

    IPrimerDriver current(IIndex index);

    /// Return a copy of the current position
    IIndex getCurrent();

    /// Increment the height of the current position
    void incY();
    /// Increment the height of the current position
    void incY(int amount);
    /// Decrement the height of the current position
    void decY();

    void incX();

    void incZ();

    int getX();

    int getY();

    int getZ();

    IIndex getIndex(int x, int y, int z);

    void setBlockRange(int x, int y, int z, int y2, int blockId); // Changed char to int

    void setBlockRangeSafe(int x, int y, int z, int y2, int blockId); // Changed char to int

    /// Set a block at the current position
    IPrimerDriver block(int blockId); // Changed char to int

    /// Set a block at the current position
    IPrimerDriver block(IBlockState state);

    /// Set a block at the current position and increase the height with 1
    IPrimerDriver add(int blockId); // Changed char to int

    char getBlock(); // Changed char to int

    int getBlockDown(); // Changed char to int
    int getBlockEast(); // Changed char to int
    int getBlockWest(); // Changed char to int
    int getBlockSouth(); // Changed char to int
    int getBlockNorth(); // Changed char to int

    int getBlock(int x, int y, int z); // Changed char to int

    IPrimerDriver copy();
}
