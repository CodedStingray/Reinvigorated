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

/**
 * Registers and holds all blocks of the Reinvigorated mod.
 */
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
            CreativeModeTabs.REDSTONE_BLOCKS
    );

    public static void initialize() {
    }

    private static Block registerBlock(String name, Function<BlockBehaviour.Properties, Block> blockFactory,
                                       BlockBehaviour.Properties settings) {
        return registerBlock(name, blockFactory, settings, null);
    }

    private static Block registerBlock(
            String name,
            Function<BlockBehaviour.Properties, Block> blockFactory,
            BlockBehaviour.Properties settings,
            ResourceKey<CreativeModeTab> creativeModeTab) {

        ResourceKey<Block> blockKey = keyOfBlock(name);
        Block block = blockFactory.apply(settings.setId(blockKey));

        if (creativeModeTab != null) {
            ResourceKey<Item> itemKey = keyOfItem(name);
            BlockItem blockItem = new BlockItem(block, new Item.Properties().setId(itemKey).useBlockDescriptionPrefix());

            Registry.register(BuiltInRegistries.ITEM, itemKey, blockItem);

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
