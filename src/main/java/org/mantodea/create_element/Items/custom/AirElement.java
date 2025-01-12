package org.mantodea.create_element.Items.custom;

import net.minecraft.world.item.Item;
import org.mantodea.create_element.Interfaces.IModItem;

public class AirElement extends Item implements IModItem {

    public AirElement() {
        super(new Properties());
    }

    @Override
    public String GetID() {
        return "air_element";
    }
}
