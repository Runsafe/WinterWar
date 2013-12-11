package no.runsafe.winterwar;

import no.runsafe.framework.RunsafePlugin;
import no.runsafe.framework.api.IDebug;

public class Plugin extends RunsafePlugin
{
	public static IDebug Debugger = null;

	@Override
	protected void PluginSetup()
	{
		Debugger = getComponent(IDebug.class);
		//addComponent(SomeComponent.class);
	}
}
