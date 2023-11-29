package no.runsafe.winterwar;

import no.runsafe.framework.RunsafeConfigurablePlugin;
import no.runsafe.framework.api.IServer;
import no.runsafe.framework.api.log.IDebug;
import no.runsafe.framework.features.Events;

public class WinterWar extends RunsafeConfigurablePlugin
{
	public static IDebug Debugger = null;
	public static IServer Server;

	@Override
	protected void pluginSetup()
	{
		Debugger = getComponent(IDebug.class);
		Server = getComponent(IServer.class);
		addComponent(Events.class);
		addComponent(Config.class);
		addComponent(Scoreboard.class);
		addComponent(PlayerManager.class);
		addComponent(Watcher.class);
	}
}
