package net.codedstingray.reinvigorated.client;

import net.codedstingray.reinvigorated.client.datagen.*;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class ReinvigoratedDataGenerator implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

        pack.addProvider(ReinvigoratedBlockTagProvider::new);
        pack.addProvider(ReinvigoratedItemTagProvider::new);
        pack.addProvider(ReinvigoratedLootTableProvider::new);
        pack.addProvider(ReinvigoratedModelProvider::new);
        pack.addProvider(ReinvigoratedRecipeProvider::new);
    }
}
