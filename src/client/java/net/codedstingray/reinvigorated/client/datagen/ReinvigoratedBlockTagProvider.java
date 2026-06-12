package net.codedstingray.reinvigorated.client.datagen;

import net.codedstingray.reinvigorated.world.block.ReinvigoratedBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.BlockTags;

import java.util.concurrent.CompletableFuture;

public class ReinvigoratedBlockTagProvider extends FabricTagsProvider.BlockTagsProvider {

    public ReinvigoratedBlockTagProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookupFuture) {
        super(output, registryLookupFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider registries) {
        valueLookupBuilder(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ReinvigoratedBlocks.BURNT_REDSTONE_BLOCK);

        valueLookupBuilder(BlockTags.NEEDS_IRON_TOOL)
                .add(ReinvigoratedBlocks.BURNT_REDSTONE_BLOCK);
    }
}
