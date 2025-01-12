package org.mantodea.create_element.Simply;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.registries.RegisterEvent;
import org.mantodea.create_element.CreateElement;

public class SimplyRegister<T> {
    public RegisterEvent.RegisterHelper<T> Helper;

    public ResourceLocation Location(String ID)
    {
        return new ResourceLocation(CreateElement.MODID, ID);
    }

    public void Register(RegisterEvent.RegisterHelper<T> helper)
    {
        Helper = helper;
    }
}
