package org.mantodea.create_element;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.forgespi.language.ModFileScanData;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegisterEvent;
import org.mantodea.create_element.Interfaces.IModBlock;
import org.mantodea.create_element.Interfaces.IModItem;
import org.mantodea.create_element.Recipes.CalculateIngredient;
import org.mantodea.create_element.Recipes.CalculateIngredientSerializer;
import org.mantodea.create_element.Simply.SimplyItems;

import java.lang.reflect.Modifier;
import java.util.*;

public class ModRegistries
{
    public static final DeferredRegister<Item> ItemRegister =
            DeferredRegister.create(ForgeRegistries.ITEMS, CreateElement.MODID);

    public static HashMap<String, Item> ItemInstances = new HashMap<String, Item>();
    public static HashMap<String, Block> BlockInstances = new HashMap<String, Block>();

    public static ArrayList<Class<?>> Items = new ArrayList<Class<?>>();
    public static ArrayList<Class<?>> Blocks = new ArrayList<Class<?>>();

    public static void LoadClasses()
    {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        var classes = ModList.get().getModFileById(CreateElement.MODID)
                .getFile().getScanResult().getClasses();

        var modItem = IModItem.class;
        var modBlock = IModBlock.class;

        for(ModFileScanData.ClassData classData : classes)
        {
            var name = classData.clazz().getClassName();

            try
            {
                var cls = classLoader.loadClass(name);

                if(Modifier.isAbstract(cls.getModifiers()))
                    continue;

                if(modItem.isAssignableFrom(cls) && !modItem.equals(cls)) Items.add(cls);
                if(modBlock.isAssignableFrom(cls) && !modBlock.equals(cls)) Blocks.add(cls);
            }
            catch (Exception e)
            {
                throw new RuntimeException(e);
            }
        }
    }

    public static void RegisterItems(RegisterEvent.RegisterHelper<Item> helper)
    {
        try
        {
            for (Class<?> cls : Items)
            {
                var constructor = cls.getConstructor();

                var instance = (IModItem)constructor.newInstance();

                instance.Register(helper);

                ItemInstances.put(instance.GetID(), (Item)instance);
            }

            SimplyItems items = new SimplyItems();
            items.Register(helper);
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }

    public static void RegisterBlocks(RegisterEvent.RegisterHelper<Block> helper)
    {
        try
        {
            for (Class<?> cls : Blocks)
            {
                var constructor = cls.getConstructor();

                var instance = (IModBlock)constructor.newInstance();

                instance.Register(helper);

                BlockInstances.put(cls.getName(), (Block)instance);
            }
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }

    public static void RegisterBiomes(RegisterEvent.RegisterHelper<Biome> helper)
    {

    }

    public static void RegisterIngredients(RegisterEvent.RegisterHelper<RecipeSerializer<?>> helper)
    {
        CraftingHelper.register(new ResourceLocation(CreateElement.MODID, "calculate"), new CalculateIngredientSerializer());
    }

    public static void Register(IEventBus eventBus)
    {
        ItemRegister.register(eventBus);
    }
}
