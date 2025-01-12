package org.mantodea.create_element;

import com.mojang.logging.LogUtils;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegisterEvent;
import org.mantodea.create_element.ModClasses.ModCreativeTab;
import org.slf4j.Logger;
import org.spongepowered.asm.launch.MixinBootstrap;
import org.spongepowered.asm.mixin.Mixins;

@Mod(CreateElement.MODID)
public class CreateElement
{
    public static final String MODID = "create_element";

    public static final Logger LOGGER = LogUtils.getLogger();

    public CreateElement()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModRegistries.LoadClasses();

        ModRegistries.Register(modEventBus);

        ModCreativeTab.Register(modEventBus);

        modEventBus.addListener(this::register);

        modEventBus.addListener(this::commonSetup);

        MinecraftForge.EVENT_BUS.register(this);

        modEventBus.addListener(this::addCreative);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
    }

    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event)
    {
        if(event.getTabKey() == CreativeModeTabs.INGREDIENTS)
        {

        }
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {

    }

    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {

        }
    }

    @SubscribeEvent
    public void register(RegisterEvent event)
    {
        event.register(ForgeRegistries.Keys.ITEMS, ModRegistries::RegisterItems);
        event.register(ForgeRegistries.Keys.BLOCKS, ModRegistries::RegisterBlocks);
        event.register(ForgeRegistries.Keys.BIOMES, ModRegistries::RegisterBiomes);
        event.register(ForgeRegistries.Keys.RECIPE_SERIALIZERS, ModRegistries::RegisterIngredients);
    }
}
