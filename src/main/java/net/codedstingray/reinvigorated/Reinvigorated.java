package net.codedstingray.reinvigorated;

import net.codedstingray.reinvigorated.world.block.ReinvigoratedBlocks;
import net.codedstingray.reinvigorated.world.item.ReinvigoratedItems;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Main class of the Reinvigorated mod. Initializes relevant mod data.
 */
public class Reinvigorated implements ModInitializer {

	public static final String MOD_ID = "reinvigorated";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("Initializing Items");
		ReinvigoratedItems.initialize();

		LOGGER.info("Initializing Blocks");
		ReinvigoratedBlocks.initialize();
	}
}