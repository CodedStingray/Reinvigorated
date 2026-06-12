package net.codedstingray.reinvigorated.client.datagen;

import net.codedstingray.reinvigorated.world.block.ReinvigoratedBlocks;
import net.codedstingray.reinvigorated.world.item.ReinvigoratedItems;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.model.ModelTemplates;

public class ReinvigoratedModelProvider extends FabricModelProvider {

    public ReinvigoratedModelProvider(FabricPackOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators blockModelGenerators) {
        blockModelGenerators.createTrivialCube(ReinvigoratedBlocks.BURNT_REDSTONE_BLOCK);
    }

    @Override
    public void generateItemModels(ItemModelGenerators itemModelGenerators) {
        itemModelGenerators.generateFlatItem(ReinvigoratedItems.COPPER_ROD, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ReinvigoratedItems.IRON_ROD, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ReinvigoratedItems.GOLD_ROD, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ReinvigoratedItems.REDSTONE_CABLE, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ReinvigoratedItems.TRACK, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ReinvigoratedItems.POWERED_TRACK, ModelTemplates.FLAT_ITEM);
    }
}
