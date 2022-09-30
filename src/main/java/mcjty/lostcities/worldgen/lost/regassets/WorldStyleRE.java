package mcjty.lostcities.worldgen.lost.regassets;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import mcjty.lostcities.worldgen.lost.regassets.data.*;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Optional;

public class WorldStyleRE implements IAsset<WorldStyleRE> {

    public static final Codec<WorldStyleRE> CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                    Codec.STRING.fieldOf("outsidestyle").forGetter(l -> l.outsideStyle),
                    ScatteredSettings.CODEC.optionalFieldOf("scattered").forGetter(l -> Optional.ofNullable(l.scatteredSettings)),
                    PartSelector.CODEC.optionalFieldOf("parts").forGetter(l -> l.partSelector.get()),
                    Codec.list(CityStyleSelector.CODEC).fieldOf("citystyles").forGetter(l -> l.cityStyleSelectors),
                    Codec.list(CityBiomeMultiplier.CODEC).optionalFieldOf("citybiomemultipliers").forGetter(l -> Optional.ofNullable(l.cityBiomeMultipliers)),
                    Codec.list(ScatteredReference.CODEC).optionalFieldOf("scattered").forGetter(l -> Optional.ofNullable(l.scatteredReferences))
            ).apply(instance, WorldStyleRE::new));

    private ResourceLocation name;
    private final String outsideStyle;
    private final ScatteredSettings scatteredSettings;
    @Nonnull private final PartSelector partSelector;
    private final List<CityStyleSelector> cityStyleSelectors;
    private final List<CityBiomeMultiplier> cityBiomeMultipliers;
    private final List<ScatteredReference> scatteredReferences;

    public WorldStyleRE(String outsideStyle, Optional<ScatteredSettings> scatteredSettings,
                        Optional<PartSelector> partSelector,
                        List<CityStyleSelector> values,
                        Optional<List<CityBiomeMultiplier>> cityBiomeMultipliers,
                        Optional<List<ScatteredReference>> scatteredReferences) {
        this.outsideStyle = outsideStyle;
        this.scatteredSettings = scatteredSettings.orElse(null);
        this.partSelector = partSelector.orElse(PartSelector.DEFAULT);
        this.cityStyleSelectors = values;
        this.cityBiomeMultipliers = cityBiomeMultipliers.orElse(null);
        this.scatteredReferences = scatteredReferences.orElse(null);
    }

    public String getOutsideStyle() {
        return outsideStyle;
    }

    @Nonnull
    public PartSelector getPartSelector() {
        return partSelector;
    }

    @Nullable
    public ScatteredSettings getScatteredSettings() {
        return scatteredSettings;
    }

    public List<CityStyleSelector> getCityStyleSelectors() {
        return cityStyleSelectors;
    }

    public List<CityBiomeMultiplier> getCityBiomeMultipliers() {
        return cityBiomeMultipliers;
    }

    public List<ScatteredReference> getScatteredReferences() {
        return scatteredReferences;
    }

    @Override
    public WorldStyleRE setRegistryName(ResourceLocation name) {
        this.name = name;
        return this;
    }

    @Nullable
    public ResourceLocation getRegistryName() {
        return name;
    }
}