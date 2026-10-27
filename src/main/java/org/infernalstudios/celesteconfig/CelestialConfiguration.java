package org.infernalstudios.celesteconfig;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.Toml4jConfigSerializer;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.world.InteractionResult;
import org.infernalstudios.celesteconfig.config.CelestialConfigClothConfig;

public class CelestialConfiguration implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        AutoConfig.register(CelestialConfigClothConfig.class, Toml4jConfigSerializer::new);

        AutoConfig.getConfigHolder(CelestialConfigClothConfig.class).registerSaveListener((holder, config) -> {
            CommonClass.init(config.moonWidth, config.moonHeight, config.sunWidth, config.sunHeight);
            return InteractionResult.PASS;
        });

        CelestialConfigClothConfig config = AutoConfig.getConfigHolder(CelestialConfigClothConfig.class).getConfig();
        CommonClass.init(config.moonWidth, config.moonHeight, config.sunWidth, config.sunHeight);
    }
}
