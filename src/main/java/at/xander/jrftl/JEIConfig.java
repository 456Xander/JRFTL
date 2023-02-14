package at.xander.jrftl;

import com.google.common.collect.ImmutableList;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.resources.ResourceLocation;

@JeiPlugin
public class JEIConfig implements IModPlugin {

	@Override
	public void registerRecipes(IRecipeRegistration registration) {
		registration.getIngredientManager().removeIngredientsAtRuntime(VanillaTypes.ITEM_STACK,
				ImmutableList.of(JRFTL.instance.items.PreparedFlesh.get().getDefaultInstance()));
	}

	@Override
	public ResourceLocation getPluginUid() {
		return new ResourceLocation(JRFTL.MODID, "jei_plugin");
	}

}
