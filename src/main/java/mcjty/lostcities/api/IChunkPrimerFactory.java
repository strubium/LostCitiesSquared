package mcjty.lostcities.api;

import net.minecraft.world.chunk.ChunkPrimer;

/**
 * This interface should be implemented in your IChunkGenerator implementation to provide
 * a chunk. The provided implementation should only create a basic chunkprimer. No biome decoration
 * or other chunk generation features should be included in this implementation.
 */
public interface IChunkPrimerFactory {

    /**
     * Fills a chunk primer with base data. No biome decoration should be performed in this method.
     *
     * @param chunkX The X coordinate of the chunk to be filled.
     * @param chunkZ The Z coordinate of the chunk to be filled.
     * @param primer The ChunkPrimer object to be filled with base data.
     */
    void fillChunk(int chunkX, int chunkZ, ChunkPrimer primer);

    /**
     * Gets an estimate of the height (0-255) of a coordinate in a chunk.
     *
     * @param chunkX The X coordinate of the chunk.
     * @param chunkZ The Z coordinate of the chunk.
     * @param x The X coordinate within the chunk.
     * @param z The Z coordinate within the chunk.
     * @return An estimate of the height (0-255) of the specified coordinate in the chunk.
     */
    int getHeight(int chunkX, int chunkZ, int x, int z);
}