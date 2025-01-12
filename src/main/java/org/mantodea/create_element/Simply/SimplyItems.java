package org.mantodea.create_element.Simply;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.food.FoodProperties;
import org.mantodea.create_element.CreateElement;
import org.mantodea.create_element.ModRegistries;
import org.mantodea.create_element.Simply.SimplyRegister;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.RegisterEvent;

public class SimplyItems extends SimplyRegister<Item>  {
    public void SimplyItem(Item.Properties properties, String ID)
    {
        Item i = new Item(properties);
        Helper.register(Location(ID), i);
        ModRegistries.ItemInstances.put(ID, i);
    }

    public void SimplyArmor(ArmorMaterial material, ArmorItem.Type type, Item.Properties properties, String ID)
    {
        Item i = new ArmorItem(material, type, properties);
        Helper.register(Location(ID), i);
        ModRegistries.ItemInstances.put(ID, i);
    }

    public void SimplyFood(FoodProperties properties, String ID)
    {
        Item i = new Item(new Item.Properties().food(properties));
        Helper.register(Location(ID), i);
        ModRegistries.ItemInstances.put(ID, i);
    }

    @Override
    public void Register(RegisterEvent.RegisterHelper<Item> helper)
    {

    }
}
