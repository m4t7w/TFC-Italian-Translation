/*
 * Work under Copyright. Licensed under the EUPL.
 * See the project README.md and LICENSE.txt for more information.
 */

package net.dries007.tfc.world.biome;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;

import net.dries007.tfc.config.TFCConfig;
import net.dries007.tfc.world.noise.INoise2D;
import net.dries007.tfc.world.noise.SimplexNoise2D;
import net.dries007.tfc.world.surfacebuilder.TFCSurfaceBuilders;

public class ShoreBiome extends TFCBiome
{
    public ShoreBiome(BiomeTemperature temperature, BiomeRainfall rainfall)
    {
        super(new Biome.Builder().category(Category.BEACH), temperature, rainfall);

        biomeFeatures.enqueue(() -> {
            TFCDefaultBiomeFeatures.addCarvers(this);

            setSurfaceBuilder(TFCSurfaceBuilders.SHORE.get(), SurfaceBuilder.AIR_CONFIG);
        });
    }

    @Override
    public INoise2D createNoiseLayer(long seed)
    {
        int seaLevel = TFCConfig.COMMON.seaLevel.get();
        return new SimplexNoise2D(seed).octaves(4).spread(0.17f).scaled(seaLevel, seaLevel + 1.8f);
    }

    @Override
    public LargeGroup getLargeGroup()
    {
        return LargeGroup.OCEAN;
    }
}
