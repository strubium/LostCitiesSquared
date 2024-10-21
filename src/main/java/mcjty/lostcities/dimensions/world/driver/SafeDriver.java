package mcjty.lostcities.dimensions.world.driver;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.world.chunk.ChunkPrimer;

import java.util.Objects;

public class SafeDriver implements IPrimerDriver {

    private ChunkPrimer primer;
    private int currentX;
    private int currentY;
    private int currentZ;

    @Override
    public void setPrimer(ChunkPrimer primer) {
        this.primer = primer;
    }

    @Override
    public ChunkPrimer getPrimer() {
        return primer;
    }

    @Override
    public IPrimerDriver current(int x, int y, int z) {
        currentX = x;
        currentY = y;
        currentZ = z;
        return this;
    }

    @Override
    public IPrimerDriver current(IIndex index) {
        Index i = (Index) index;
        currentX = i.x;
        currentY = i.y;
        currentZ = i.z;
        return this;
    }

    @Override
    public IIndex getCurrent() {
        return new Index(currentX, currentY, currentZ);
    }

    @Override
    public void incY() {
        currentY++;
    }

    @Override
    public void incY(int amount) {
        currentY += amount;
    }

    @Override
    public void decY() {
        currentY--;
    }

    @Override
    public void incX() {
        currentX++;
    }

    @Override
    public void incZ() {
        currentZ++;
    }

    @Override
    public int getX() {
        return currentX;
    }

    @Override
    public int getY() {
        return currentY;
    }

    @Override
    public int getZ() {
        return currentZ;
    }

    @Override
    public void setBlockRange(int x, int y, int z, int y2, int blockId) { // Changed char to int
        IBlockState state = Block.BLOCK_STATE_IDS.getByValue(blockId); // Use blockId as int
        while (y < y2) {
            primer.setBlockState(x, y, z, state);
            y++;
        }
    }

    @Override
    public void setBlockRangeSafe(int x, int y, int z, int y2, int blockId) { // Changed char to int
        IBlockState state = Block.BLOCK_STATE_IDS.getByValue(blockId); // Use blockId as int
        while (y < y2) {
            primer.setBlockState(x, y, z, state);
            y++;
        }
    }

    @Override
    public IPrimerDriver block(int blockId) { // Changed char to int
        IBlockState state = Block.BLOCK_STATE_IDS.getByValue(blockId); // Use blockId as int
        primer.setBlockState(currentX, currentY, currentZ, state);
        return this;
    }

    @Override
    public IPrimerDriver block(IBlockState state) {
        primer.setBlockState(currentX, currentY, currentZ, state);
        return this;
    }

    @Override
    public IPrimerDriver add(int blockId) { // Changed char to int
        IBlockState state = Block.BLOCK_STATE_IDS.getByValue(blockId); // Use blockId as int
        primer.setBlockState(currentX, currentY++, currentZ, state);
        return this;
    }

    @Override
    public char getBlock() { // Changed char to int
        return (char) Block.BLOCK_STATE_IDS.get(primer.getBlockState(currentX, currentY, currentZ)); // Return as int
    }

    @Override
    public int getBlockDown() { // Changed char to int
        return Block.BLOCK_STATE_IDS.get(primer.getBlockState(currentX, currentY - 1, currentZ)); // Return as int
    }

    @Override
    public int getBlockEast() { // Changed char to int
        return Block.BLOCK_STATE_IDS.get(primer.getBlockState(currentX + 1, currentY, currentZ)); // Return as int
    }

    @Override
    public int getBlockWest() { // Changed char to int
        return Block.BLOCK_STATE_IDS.get(primer.getBlockState(currentX - 1, currentY, currentZ)); // Return as int
    }

    @Override
    public int getBlockSouth() { // Changed char to int
        return Block.BLOCK_STATE_IDS.get(primer.getBlockState(currentX, currentY, currentZ + 1)); // Return as int
    }

    @Override
    public int getBlockNorth() { // Changed char to int
        return Block.BLOCK_STATE_IDS.get(primer.getBlockState(currentX, currentY, currentZ - 1)); // Return as int
    }

    @Override
    public int getBlock(int x, int y, int z) { // Changed char to int
        return Block.BLOCK_STATE_IDS.get(primer.getBlockState(x, y, z)); // Return as int
    }

    @Override
    public IIndex getIndex(int x, int y, int z) {
        return new Index(x, y, z);
    }

    private class Index implements IIndex {
        private final int x;
        private final int y;
        private final int z;

        Index(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Index index = (Index) o;
            return x == index.x &&
                    y == index.y &&
                    z == index.z;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y, z);
        }
    }

    @Override
    public IPrimerDriver copy() {
        SafeDriver driver = new SafeDriver();
        driver.currentX = currentX;
        driver.currentY = currentY;
        driver.currentZ = currentZ;
        driver.primer = primer;
        return driver;
    }
}