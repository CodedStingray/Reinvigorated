package net.codedstingray.reinvigorated.world.block;

import net.codedstingray.reinvigorated.Reinvigorated;
import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;

import java.util.function.Function;

@SuppressWarnings("unused")
public class ReinvigoratedBlocks {

    public static final Block BURNT_REDSTONE_BLOCK = registerBlock(
            "burnt_redstone_block",
            Block::new,
            BlockBehaviour.Properties.of()
                    .sound(SoundType.METAL)
                    .mapColor(MapColor.FIRE)
                    .requiresCorrectToolForDrops()
                    .strength(5.0F, 6.0F),
            true,
            CreativeModeTabs.REDSTONE_BLOCKS
    );

    public static void initialize() {
    }

    private static Block registerBlock(String name, Function<BlockBehaviour.Properties, Block> blockFactory, BlockBehaviour.Properties settings, boolean shouldRegisterItem, ResourceKey<CreativeModeTab> creativeModeTab) {
        // Create a registry key for the block
        ResourceKey<Block> blockKey = keyOfBlock(name);
        // Create the block instance
        Block block = blockFactory.apply(settings.setId(blockKey));

        // Sometimes, you may not want to register an item for the block.
        // Eg: if it's a technical block like `minecraft:moving_piston` or `minecraft:end_gateway`
        if (shouldRegisterItem) {
            // Items need to be registered with a different type of registry key, but the ID
            // can be the same.
            ResourceKey<Item> itemKey = keyOfItem(name);

            BlockItem blockItem = new BlockItem(block, new Item.Properties().setId(itemKey).useBlockDescriptionPrefix());
            Registry.register(BuiltInRegistries.ITEM, itemKey, blockItem);

            // Get the event for modifying entries in the given group.
            // And register an event handler that adds our item to the given group.
            CreativeModeTabEvents.modifyOutputEvent(creativeModeTab)
                    .register((creativeTab) -> creativeTab.accept(block.asItem()));
        }

        return Registry.register(BuiltInRegistries.BLOCK, blockKey, block);
    }

    private static ResourceKey<Block> keyOfBlock(String name) {
        return ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(Reinvigorated.MOD_ID, name));
    }

    private static ResourceKey<Item> keyOfItem(String name) {
        return ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(Reinvigorated.MOD_ID, name));
    }
}
