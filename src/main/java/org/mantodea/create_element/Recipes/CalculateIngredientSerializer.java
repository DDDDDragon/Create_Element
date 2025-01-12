package org.mantodea.create_element.Recipes;

import com.google.gson.JsonObject;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.common.crafting.IIngredientSerializer;
import org.checkerframework.checker.regex.qual.Regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CalculateIngredientSerializer implements IIngredientSerializer<CalculateIngredient> {

    public static final CalculateIngredientSerializer INSTANCE = new CalculateIngredientSerializer();

    @Override
    public CalculateIngredient parse(FriendlyByteBuf buffer) {
        CalculateIngredient ingredient = new CalculateIngredient();
        String str = buffer.readUtf();

        String valPattern = "\"val\":[ ]*([0-9]+)";
        String comparePattern = "\"compare\":[ ]*([0-9]+)";
        String nbtIDPattern = "\"nbtID\":[ ]*([a-zA-Z]+)";

        String val = Pattern.compile(valPattern).matcher(str).group(1);
        String compare = Pattern.compile(comparePattern).matcher(str).group(1);
        String nbtID = Pattern.compile(nbtIDPattern).matcher(str).group(1);
        ingredient.val = Integer.parseInt(val);
        ingredient.compare = Integer.parseInt(compare);
        ingredient.nbtID = nbtID;
        return ingredient;
    }

    @Override
    public CalculateIngredient parse(JsonObject json) {
        CalculateIngredient ingredient = new CalculateIngredient();
        ingredient.val = json.get("val").getAsInt();
        ingredient.compare = json.get("compare").getAsInt();
        ingredient.nbtID = json.get("nbtID").getAsString();
        return ingredient;
    }

    @Override
    public void write(FriendlyByteBuf buffer, CalculateIngredient ingredient) {

    }
}
