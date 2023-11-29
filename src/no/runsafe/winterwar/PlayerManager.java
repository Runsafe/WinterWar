package no.runsafe.winterwar;

import no.runsafe.framework.api.IWorld;
import no.runsafe.framework.api.entity.IEntity;
import no.runsafe.framework.api.player.IPlayer;

public class PlayerManager
{
	public PlayerManager(Config config, Scoreboard scoreboard)
	{
		this.config = config;
		this.scoreboard = scoreboard;
	}

	public boolean isInWinterWorld(IEntity entity)
	{
		IWorld entityWorld = entity.getWorld();
		return entityWorld != null && entityWorld.isWorld(config.getWorld());
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
}
