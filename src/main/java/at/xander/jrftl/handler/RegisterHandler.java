package at.xander.jrftl.handler;

import java.util.ArrayList;
import java.util.List;

import at.xander.jrftl.JRFTL;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = JRFTL.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class RegisterHandler {
	public static final RegisterHandler instance = new RegisterHandler();

	@SubscribeEvent
	public void onItemRegistry(Register<Item> event) {
		List<Item> itemsToRegister = new ArrayList<>();
		JRFTL.instance.registerItems(itemsToRegister);
		itemsToRegister.forEach(i -> event.getRegistry().register(i));
	}
}
