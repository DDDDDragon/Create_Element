package org.mantodea.create_element.Interfaces;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.registries.RegisterEvent;
import org.mantodea.create_element.CreateElement;

public interface IModContent<T>
{
    String GetID();

    Class<T> GetTClass();

    default void Register(RegisterEvent.RegisterHelper<T> helper)
    {
        if (GetTClass().isAssignableFrom(this.getClass()))
            helper.register(new ResourceLocation(CreateElement.MODID, GetID()), (T)this);
    }
}
