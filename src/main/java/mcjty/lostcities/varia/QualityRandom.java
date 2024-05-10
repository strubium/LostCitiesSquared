package mcjty.lostcities.varia;

import java.util.Random;

/**
 * A subclass of java.util.Random that provides a higher quality random number generation.
 * 
 * @author mcjty
 */
public class QualityRandom extends Random {
    private long u;
    private long v = 4101842887655102017L;
    private long w = 1;

    /**
     * Constructs a new QualityRandom object with a seed value derived from the current system time.
     */
    public QualityRandom() {
        this(System.nanoTime());
    }

    /**
     * Constructs a new QualityRandom object with the specified seed value.
     *
     * @param seed the initial seed value
     */
    public QualityRandom(long seed) {
        u = seed ^ v;
        nextLong();
        v = u;
        nextLong();
        w = v;
        nextLong();
    }

    /**
     * Generates and returns the next pseudorandom, uniformly distributed {@code long} value.
     *
     * @return the next pseudorandom, uniformly distributed {@code long} value
     */
    @Override
    public long nextLong() {
        u = u * 2862933555777941757L + 7046029254386353087L;
        v ^= v >>> 17;
        v ^= v << 31;
        v ^= v >>> 8;
        w = 4294957665L * (w & 0xffffffff) + (w >>> 32);
        long x = u ^ (u << 21);
        x ^= x >>> 35;
        x ^= x << 4;
        long ret = (x + v) ^ w;
        return ret;
    }

    /**
     * Generates and returns the next pseudorandom, uniformly distributed {@code int} value
     * from the specified number of bits.
     *
     * @param bits the number of bits to generate
     * @return the next pseudorandom, uniformly distributed {@code int} value
     */
    @Override
    protected int next(int bits) {
        return (int) (nextLong() >>> (64-bits));
    }
}