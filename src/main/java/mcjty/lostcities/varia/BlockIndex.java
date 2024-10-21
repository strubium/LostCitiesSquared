package mcjty.lostcities.varia;

import mcjty.lostcities.dimensions.world.lost.Orientation;

/**
 * An index of a block as used in the ChunkPrimer.
 * This class provides methods to create and manipulate block indexs.
 */
public class BlockIndex {

    private int index;

    /**
     * Constructor for BlockIndex.
     *
     * @param index The index value to initialize the BlockIndex with.
     */
    public BlockIndex(int index) {
        this.index = index;
    }

    /**
     * Constructor for BlockIndex.
     *
     * @param x The x-coordinate of the block.
     * @param y The y-coordinate of the block.
     * @param z The z-coordinate of the block.
     * This constructor calculates the index value based on the provided coordinates.
     */
    public BlockIndex(int x, int y, int z) {
        index = (x << 12) | (z << 8) + y;
    }

    /**
     * Constructor for BlockIndex.
     *
     * @param x The x-coordinate of the block.
     * @param y The y-coordinate of the block.
     * @param z The z-coordinate of the block.
     * @param orientation The orientation of the block.
     * This constructor calculates the index value based on the provided coordinates and orientation.
     * @throws IllegalArgumentException If the provided orientation is not X or Z.
     */
    public BlockIndex(int x, int y, int z, Orientation orientation) {
        switch (orientation) {
            case X:
                index = (x << 12) | (z << 8) + y;
                break;
            case Z:
                index = (z << 12) | (x << 8) + y;
                break;
            default:
                throw new IllegalArgumentException("Orientation must be X or Z");
        }
    }

    /**
     * Increments the y-coordinate of the block index.
     */
    public void incY() {
        index++;
    }

    /**
     * Returns the index value of the block.
     *
     * @return The index value.
     */
    public int getIndex() {
        return index;
    }

    /**
     * Returns the index value of the block based on the provided orientation and amount.
     *
     * @param o The orientation of the block.
     * @param amount The amount to adjust the index.
     * @return The adjusted index value.
     */
    public int getIndex(Orientation o, int amount) {
        return index;
    }
}
