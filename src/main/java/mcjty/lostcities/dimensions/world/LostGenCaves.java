package mcjty.lostcities.dimensions.world;

import net.minecraft.world.World;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.MapGenCaves;

/**
 * Customized cave generation class for the Lost Cities mod.
 * Extends the vanilla MapGenCaves class to modify cave generation behavior.
 */
public class LostGenCaves extends MapGenCaves {

    /**
     * The chunk generator associated with this cave generator.
     */
    private final LostCityChunkGenerator provider;

    /**
     * Constructor for the LostGenCaves class.
     *
     * @param provider The chunk generator associated with this cave generator.
     */
    public LostGenCaves(LostCityChunkGenerator provider) {
        this.provider = provider;
    }

    /**
     * Recursively called by generate() to generate caves in a chunk.
     *
     * @param worldIn The world in which the caves are being generated.
     * @param chunkX The X coordinate of the chunk.
     * @param chunkZ The Z coordinate of the chunk.
     * @param originalX The original X coordinate of the chunk.
     * @param originalZ The original Z coordinate of the chunk.
     * @param chunkPrimerIn The chunk primer used to store the generated cave data.
     */
    @Override
    protected void recursiveGenerate(World worldIn, int chunkX, int chunkZ, int originalX, int originalZ, ChunkPrimer chunkPrimerIn) {
        int i = this.rand.nextInt(this.rand.nextInt(this.rand.nextInt(15) + 1) + 1);

        if (this.rand.nextInt(7)!= 0) {
            i = 0;
        }

        for (int j = 0; j < i; ++j) {
            double d0 = (double) (chunkX * 16 + this.rand.nextInt(16));
            double d1 = (double) this.rand.nextInt(this.rand.nextInt(provider.getProfile().MAX_CAVE_HEIGHT - 8) + 8);
            double d2 = (double) (chunkZ * 16 + this.rand.nextInt(16));
            int k = 1;

            if (this.rand.nextInt(4) == 0) {
                this.addRoom(this.rand.nextLong(), originalX, originalZ, chunkPrimerIn, d0, d1, d2);
                k += this.rand.nextInt(4);
            }

            for (int l = 0; l < k; ++l) {
                float f = this.rand.nextFloat() * ((float) Math.PI * 2F);
                float f1 = (this.rand.nextFloat() - 0.5F) * 2.0F / 8.0F;
                float f2 = this.rand.nextFloat() * 2.0F + this.rand.nextFloat();

                if (this.rand.nextInt(10) == 0) {
                    f2 *= this.rand.nextFloat() * this.rand.nextFloat() * 3.0F + 1.0F;
                }

                this.addTunnel(this.rand.nextLong(), originalX, originalZ, chunkPrimerIn, d0, d1, d2, f2, f, f1, 0, 0, 1.0D);
            }
        }
    }
}
}

