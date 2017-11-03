package at.xander.jrftl;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;
import mezz.jei.api.ingredients.IIngredientBlacklist;
import net.minecraft.item.ItemStack;

@JEIPlugin
public class JEIConfig implements IModPlugin {

	@Override
	public void register(IModRegistry registry) {
		IIngredientBlacklist blacklist = registry.getJeiHelpers().getIngredientBlacklist();
		if (!JRFTL.instance.isHardMode()) {
			blacklist.addIngredientToBlacklist(new ItemStack(JRFTL.instance.PreparedFlesh));
		}
	}

}
