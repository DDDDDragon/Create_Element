package org.mantodea.create_element.Interfaces;

import net.minecraft.world.item.Item;
import org.mantodea.create_element.ModRegistries;

public interface IModItem extends IModContent<Item>
{
    default Class<Item> GetTClass()
    {
        return Item.class;
    }

    static Item GetInstance()
    {
        return ModRegistries.ItemInstances.get(Thread.currentThread().getStackTrace()[1].getClassName());
    }
}
