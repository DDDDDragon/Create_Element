package org.mantodea.create_element.Datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;
import org.mantodea.create_element.CreateElement;
import org.mantodea.create_element.ModRegistries;

public class ModItemModelProvider extends ItemModelProvider
{
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, CreateElement.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels()
    {

    }

    private ItemModelBuilder simpleItem(RegistryObject<Item> item)
    {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(CreateElement.MODID, "item/" + item.getId().getPath()));
    }
}
