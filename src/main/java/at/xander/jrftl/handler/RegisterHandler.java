package at.xander.jrftl.handler;

import at.xander.jrftl.JRFTL;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = JRFTL.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class RegisterHandler {
	public static final RegisterHandler instance = new RegisterHandler();

	@SubscribeEvent
	public static void onItemRegistry(Register<Item> event) {
		JRFTL.instance.PreparedFlesh = new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC)).setRegistryName("prepared_flesh");
		event.getRegistry().register(JRFTL.instance.PreparedFlesh);
	}

}
