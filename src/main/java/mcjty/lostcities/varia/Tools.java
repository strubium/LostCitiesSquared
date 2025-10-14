package mcjty.lostcities.varia;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * A utility class containing various helper methods.
 */
public class Tools {

    /**
     * Converts a string representation of a block state into an IBlockState object.
     *
     * @param s The string representation of the block state. It can be in the format "modid:blockname" or "modid:blockname@meta".
     * @return The corresponding IBlockState object.
     * @throws RuntimeException If the block or meta is not found.
     */
    public static IBlockState stringToState(String s) {
        if (s.contains("@")) {
            String[] split = StringUtils.split(s, '@');
            Block value = ForgeRegistries.BLOCKS.getValue(new ResourceLocation(split[0]));
            if (value == null) {
                throw new RuntimeException("Cannot find block: '" + split[0] + "'!");
            }
            try {
                int meta = Integer.parseInt(split[1]);
                return value.getStateFromMeta(meta);
            } catch (NumberFormatException e) {
                throw new RuntimeException("Bad meta for: '" + s + "'!");
            }
        } else {
            Block value = ForgeRegistries.BLOCKS.getValue(new ResourceLocation(s));
            if (value == null) {
                throw new RuntimeException("Cannot find block: '" + s + "'!");
            }
            return value.getDefaultState();
        }
    }
    
    /**
     * Converts an IBlockState object into a string representation.
     *
     * @param state The IBlockState object to convert.
     * @return The string representation of the block state.
     */
    public static String stateToString(IBlockState state) {
        int meta = state.getBlock().getMetaFromState(state);
        if (meta == 0) {
            return state.getBlock().getRegistryName().toString();
        } else {
            return state.getBlock().getRegistryName().toString() + "@" + meta;
        }
    }
    
    /**
     * Picks a random element from a list based on their weights.
     *
     * @param random The random number generator to use.
     * @param list   The list of elements with their weights. Each element is represented as a Pair<Float, String>.
     * @return The randomly picked element.
     * @throws RuntimeException If the list is empty.
     */
    public static String getRandomFromList(Random random, List<Pair<Float, String>> list) {
        if (list.isEmpty()) {
            return null;
        }
        List<Pair<Float, String>> elements = new ArrayList<>();
        float totalweight = 0;
        for (Pair<Float, String> pair : list) {
            elements.add(pair);
            totalweight += pair.getKey();
        }
        float r = random.nextFloat() * totalweight;
        for (Pair<Float, String> pair : elements) {
            r -= pair.getKey();
            if (r <= 0) {
                return pair.getRight();
            }
        }
        return null;
    }
}
