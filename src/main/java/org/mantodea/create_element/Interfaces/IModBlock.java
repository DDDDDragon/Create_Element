package org.mantodea.create_element.Interfaces;

import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import org.mantodea.create_element.ModRegistries;

public interface IModBlock extends IModContent<Block>
{
    default Class<Block> GetTClass()
    {
        return Block.class;
    }

    public static Block GetInstance()
    {
        return ModRegistries.BlockInstances.get(Thread.currentThread().getStackTrace()[1].getClassName());
    }
}
