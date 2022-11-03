package dev.jackluna.radiantcraft.item;

import dev.jackluna.radiantcraft.RadiantCraft;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tiers;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    // The register for the items added by the mod?
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, RadiantCraft.MOD_ID);

    //Registers the default shardblade as an item, and assigns it to the misc tab for now
    public static final RegistryObject<Item> SHARDBLADE_DEFAULT =
            ITEMS.register("shardblade_default",
                    () -> new SwordItem(Tiers.NETHERITE, 10, 5f,
                            new Item.Properties().tab(CreativeModeTab.TAB_MISC).stacksTo(1)));

    //Adds the item register / block register to the event bus for the mod
    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }

}
