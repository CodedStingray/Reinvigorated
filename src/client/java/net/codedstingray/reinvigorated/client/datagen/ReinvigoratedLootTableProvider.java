package net.codedstingray.reinvigorated.client.datagen;

import net.codedstingray.reinvigorated.world.block.ReinvigoratedBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootSubProvider;
import net.minecraft.core.HolderLookup;

import java.util.concurrent.CompletableFuture;

public class ReinvigoratedLootTableProvider extends FabricBlockLootSubProvider {

    public ReinvigoratedLootTableProvider(FabricPackOutput packOutput, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(packOutput, registriesFuture);
    }

    @Override
    public void generate() {
        dropSelf(ReinvigoratedBlocks.BURNT_REDSTONE_BLOCK);
    }
}
