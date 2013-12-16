package no.runsafe.winterwar;

import no.runsafe.framework.RunsafeConfigurablePlugin;
import no.runsafe.framework.api.log.IDebug;

public class WinterWar extends RunsafeConfigurablePlugin
{
	public static IDebug Debugger = null;

	@Override
	protected void pluginSetup()
	{
		Debugger = getComponent(IDebug.class);
		addComponent(Config.class);
		addComponent(Scoreboard.class);
		addComponent(PlayerManager.class);
		addComponent(Watcher.class);
	}
}
