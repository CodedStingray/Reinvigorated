package net.codedstingray.reinvigorated.world.item;

import net.codedstingray.reinvigorated.Reinvigorated;
import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;

import java.util.function.Function;

/**
 * Registers and holds all items of the Reinvigorated mod.
 */
@SuppressWarnings("unused")
public class ReinvigoratedItems {

    public static final Item COPPER_ROD = registerItem("copper_rod", CreativeModeTabs.INGREDIENTS);
    public static final Item IRON_ROD = registerItem("iron_rod", CreativeModeTabs.INGREDIENTS);
    public static final Item GOLD_ROD = registerItem("gold_rod", CreativeModeTabs.INGREDIENTS);

    public static final Item REDSTONE_CABLE = registerItem("redstone_cable", CreativeModeTabs.INGREDIENTS);

    public static final Item TRACK = registerItem("track", CreativeModeTabs.INGREDIENTS);
    public static final Item POWERED_TRACK = registerItem("powered_track", CreativeModeTabs.INGREDIENTS);

    public static void initialize() {
    }

    private static Item registerItem(String name, ResourceKey<CreativeModeTab> creativeModeTab) {
        return registerItem(name, Item::new, new Item.Properties(), creativeModeTab);
    }

    private static <T extends Item> T registerItem(
            String name,
            Function<Item.Properties, T> itemFactory,
            Item.Properties settings,
            ResourceKey<CreativeModeTab> creativeModeTab) {

        ResourceKey<Item> itemKey = ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(Reinvigorated.MOD_ID, name));
        T item = itemFactory.apply(settings.setId(itemKey));

        Registry.register(BuiltInRegistries.ITEM, itemKey, item);

        CreativeModeTabEvents.modifyOutputEvent(creativeModeTab)
                .register((creativeTab) -> creativeTab.accept(item));

        return item;
    }
}
