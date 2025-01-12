package org.mantodea.create_element.ModClasses;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import org.mantodea.create_element.CreateElement;
import org.mantodea.create_element.ModRegistries;

public class ModCreativeTab
{
    public static final DeferredRegister<CreativeModeTab> CreativeTabs =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, CreateElement.MODID);

    public static RegistryObject<CreativeModeTab> ModCreativeTab;

    public static void Register(IEventBus eventBus)
    {
        ModCreativeTab = CreativeTabs.register("element_items",
            () -> CreativeModeTab.builder()
                .title(Component.translatable("creative_tab.element_items_tab"))
                .displayItems((parameters, output) -> {
                    for(Item i : ModRegistries.ItemInstances.values())
                    {
                        output.accept(i);
                    }
                })
                .build()
        );
        CreativeTabs.register(eventBus);
    }
}
