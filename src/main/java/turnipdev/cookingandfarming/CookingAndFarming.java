package turnipdev.cookingandfarming;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import turnipdev.cookingandfarming.init.ModBlocks;
import turnipdev.cookingandfarming.init.ModItems;

@Mod("cookingandfarming")
public class CookingAndFarming {
    public static final String MODID = "cookingandfarming";
    public static final Logger LOGGER = LogManager.getLogger(MODID);

    public CookingAndFarming() {
        final ModLoadingContext modLoadingContext = ModLoadingContext.get();
        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModBlocks.BLOCKS.register(modEventBus);
        ModItems.ITEMS.register(modEventBus);
    }
}