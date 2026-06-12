package net.codedstingray.reinvigorated.client.datagen;

import net.codedstingray.reinvigorated.world.block.ReinvigoratedBlocks;
import net.codedstingray.reinvigorated.world.item.ReinvigoratedItems;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.CookingBookCategory;
import net.minecraft.world.level.block.Blocks;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ReinvigoratedRecipeProvider extends FabricRecipeProvider {

    public ReinvigoratedRecipeProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected RecipeProvider createRecipeProvider(HolderLookup.Provider registries, RecipeOutput output) {
        return new RecipeProvider(registries, output) {
            @Override
            public void buildRecipes() {
                oreSmelting(
                        List.of(Blocks.REDSTONE_BLOCK),
                        RecipeCategory.REDSTONE,
                        CookingBookCategory.BLOCKS,
                        ReinvigoratedBlocks.BURNT_REDSTONE_BLOCK,
                        0,
                        200,
                        "burnt_redstone_block"
                );

                shapeless(RecipeCategory.REDSTONE, ReinvigoratedItems.REDSTONE_CABLE, 3)
                        .requires(ReinvigoratedBlocks.BURNT_REDSTONE_BLOCK)
                        .unlockedBy(getHasName(ReinvigoratedBlocks.BURNT_REDSTONE_BLOCK), has(ReinvigoratedBlocks.BURNT_REDSTONE_BLOCK))
                        .group("redstone_cable")
                        .save(output);

                shaped(RecipeCategory.MISC, ReinvigoratedItems.COPPER_ROD, 4)
                        .pattern("#")
                        .pattern("#")
                        .define('#', Items.COPPER_INGOT)
                        .group("metal_rods")
                        .unlockedBy(getHasName(Items.COPPER_INGOT), has(Items.COPPER_INGOT))
                        .save(output);

                shaped(RecipeCategory.MISC, ReinvigoratedItems.IRON_ROD, 4)
                        .pattern("#")
                        .pattern("#")
                        .define('#', Items.IRON_INGOT)
                        .group("metal_rods")
                        .unlockedBy(getHasName(Items.IRON_INGOT), has(Items.IRON_INGOT))
                        .save(output);

                shaped(RecipeCategory.MISC, ReinvigoratedItems.GOLD_ROD, 4)
                        .pattern("#")
                        .pattern("#")
                        .define('#', Items.GOLD_INGOT)
                        .group("metal_rods")
                        .unlockedBy(getHasName(Items.GOLD_INGOT), has(Items.GOLD_INGOT))
                        .save(output);

                shaped(RecipeCategory.MISC, ReinvigoratedItems.TRACK, 3)
                        .pattern("#")
                        .pattern("#")
                        .pattern("#")
                        .define('#', Items.IRON_INGOT)
                        .group("track")
                        .unlockedBy(getHasName(Items.IRON_INGOT), has(Items.IRON_INGOT))
                        .save(output);

                shaped(RecipeCategory.MISC, ReinvigoratedItems.POWERED_TRACK, 3)
                        .pattern("I=#")
                        .pattern("I=#")
                        .pattern("I=#")
                        .define('I', ReinvigoratedItems.REDSTONE_CABLE)
                        .define('=', ReinvigoratedItems.GOLD_ROD)
                        .define('#', ReinvigoratedItems.TRACK)
                        .group("powered_track")
                        .unlockedBy(getHasName(ReinvigoratedItems.REDSTONE_CABLE), has(ReinvigoratedItems.REDSTONE_CABLE))
                        .save(output);

                shaped(RecipeCategory.MISC, Items.RAIL, 32)
                        .pattern("#/#")
                        .pattern("#/#")
                        .pattern("#/#")
                        .define('#', ReinvigoratedItems.TRACK)
                        .define('/', Items.STICK)
                        .group("rail")
                        .unlockedBy(getHasName(ReinvigoratedItems.TRACK), has(ReinvigoratedItems.TRACK))
                        .save(output);

                shaped(RecipeCategory.MISC, Items.POWERED_RAIL, 6)
                        .pattern("#.#")
                        .pattern("#/#")
                        .pattern("#.#")
                        .define('#', ReinvigoratedItems.POWERED_TRACK)
                        .define('/', Items.STICK)
                        .define('.', ReinvigoratedItems.REDSTONE_CABLE)
                        .group("powered_rail")
                        .unlockedBy(getHasName(ReinvigoratedItems.POWERED_TRACK), has(ReinvigoratedItems.POWERED_TRACK))
                        .save(output);

                shaped(RecipeCategory.MISC, Items.ACTIVATOR_RAIL, 6)
                        .pattern("#/#")
                        .pattern("#T#")
                        .pattern("#/#")
                        .define('#', ReinvigoratedItems.TRACK)
                        .define('/', Items.STICK)
                        .define('T', Items.REDSTONE_TORCH)
                        .group("activator_rail")
                        .unlockedBy(getHasName(ReinvigoratedItems.TRACK), has(ReinvigoratedItems.TRACK))
                        .save(output);

                shaped(RecipeCategory.MISC, Items.DETECTOR_RAIL, 6)
                        .pattern("#.#")
                        .pattern("#_#")
                        .pattern("#.#")
                        .define('#', ReinvigoratedItems.TRACK)
                        .define('_', Items.STONE_PRESSURE_PLATE)
                        .define('.', ReinvigoratedItems.REDSTONE_CABLE)
                        .group("detector_rail")
                        .unlockedBy(getHasName(ReinvigoratedItems.TRACK), has(ReinvigoratedItems.TRACK))
                        .save(output);
            }
        };
    }

    @Override
    public String getName() {
        return "ReinvigoratedModRecipeProvider";
    }
}
