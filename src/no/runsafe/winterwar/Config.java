package no.runsafe.winterwar;

import no.runsafe.framework.api.IConfiguration;
import no.runsafe.framework.api.ILocation;
import no.runsafe.framework.api.event.plugin.IConfigurationChanged;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Config implements IConfigurationChanged
{
	@Override
	public void OnConfigurationChanged(IConfiguration config)
	{
		location = config.getConfigValueAsLocation("scoreboard");
		personalLocation = config.getConfigValueAsLocation("personalScoreboard");
		winterWarRegions.putAll(config.getConfigSectionsAsList("winterWarRegions"));
	}

	public ILocation getScoreboardLocation()
	{
		return location;
	}

	public ILocation getPersonalScoreboardLocation()
	{
		return personalLocation;
	}

	public Map<String, List<String>> getRegions()
	{
		return winterWarRegions;
	}

	private ILocation location;
	private ILocation personalLocation;
	private static final Map<String, List<String>> winterWarRegions = new HashMap<>();
}
