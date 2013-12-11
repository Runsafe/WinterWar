package no.runsafe.winterwar;

import no.runsafe.framework.api.IConfiguration;
import no.runsafe.framework.api.ILocation;
import no.runsafe.framework.api.IWorld;
import no.runsafe.framework.api.event.plugin.IConfigurationChanged;

public class Config implements IConfigurationChanged
{
	@Override
	public void OnConfigurationChanged(IConfiguration config)
	{
		location = config.getConfigValueAsLocation("scoreboard");
		personalLocation = config.getConfigValueAsLocation("personalScoreboard");
	}

	public ILocation getScoreboardLocation()
	{
		return location;
	}

	public ILocation getPersonalScoreboardLocation()
	{
		return personalLocation;
	}

	public IWorld getWorld()
	{
		return location.getWorld();
	}

	private ILocation location;
	private ILocation personalLocation;
}
