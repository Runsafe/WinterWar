package no.runsafe.winterwar;

import no.runsafe.framework.api.ILocation;
import no.runsafe.framework.api.entity.IEntity;
import no.runsafe.framework.api.player.IPlayer;
import no.runsafe.worldguardbridge.IRegionControl;

import java.util.List;

public class PlayerManager
{
	public PlayerManager(Config config, Scoreboard scoreboard, IRegionControl worldGuard)
	{
		this.config = config;
		this.scoreboard = scoreboard;
		this.worldGuard = worldGuard;
	}

	public boolean isInWinterLocation(IEntity entity)
	{
		ILocation entityLocation = entity.getLocation();
		if (entityLocation == null)
			return false;

		List<String> regions = config.getRegions().get(entityLocation.getWorld().getName());
		if (regions == null || regions.isEmpty())
			return false;

		List<String> insideRegions = worldGuard.getRegionsAtLocation(entityLocation);
		if (insideRegions == null || insideRegions.isEmpty())
			return false;

		for (String winterWarRegionName : regions)
			if (insideRegions.contains(winterWarRegionName))
				return true;

		return false;
	}

	public void registerHit(IPlayer player)
	{
		scoreboard.registerHit(player);
	}

	public void updatePlayerSign(IPlayer player)
	{
		scoreboard.updatePersonalSign(player);
	}

	private final Config config;
	private final Scoreboard scoreboard;
	private final IRegionControl worldGuard;
}
