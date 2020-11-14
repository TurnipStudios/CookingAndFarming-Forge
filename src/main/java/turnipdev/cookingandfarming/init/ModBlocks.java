package turnipdev.cookingandfarming.init;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import turnipdev.cookingandfarming.CookingAndFarming;
import turnipdev.cookingandfarming.block.PotBlock;

public final class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, CookingAndFarming.MODID);

    // Blocks
    public static final RegistryObject<Block> POT = BLOCKS.register("pot", () -> new PotBlock(Block.Properties.create(Material.IRON)));

    // Block-Items
    public static final RegistryObject<Item> POT_ITEM = ModItems.ITEMS.register("pot", () -> new BlockItem(POT.get(), new Item.Properties().group(ItemGroup.MISC)));
}
