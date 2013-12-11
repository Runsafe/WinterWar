package no.runsafe.winterwar;

import no.runsafe.framework.api.ILocation;
import no.runsafe.framework.api.block.ISign;
import no.runsafe.framework.api.player.IPlayer;
import no.runsafe.framework.minecraft.packets.PacketUpdateSign;

import java.util.HashMap;
import java.util.Map;

public class Scoreboard
{
	public Scoreboard(Config config)
	{
		this.config = config;
	}

	public void registerHit(IPlayer player)
	{
		String playerName = player.getName();
		int currentScore = scores.containsKey(playerName) ? scores.get(playerName) : 0;
		scores.put(playerName, currentScore + 1);

		updateSign();
		updatePersonalSign(player);
	}

	private void updateSign()
	{
		ILocation signLocation = config.getScoreboardLocation();
		if (signLocation == null)
			return;

		ISign sign = signLocation.getBlock().getAsSign();
		if (sign != null)
		{
			Map.Entry<String, Integer> player = getHighestPlayer();
			if (player != null)
				sign.setLines("Today's Highest", "", player.getKey(), "Hits: " + player.getValue());
			else
				sign.setLines("Today's Highest", "", "Nobody :(", "");

			sign.update(true);
		}
	}

	private void updatePersonalSign(IPlayer player)
	{
		ILocation signLocation = config.getPersonalScoreboardLocation();
		if (signLocation == null)
			return;


		new PacketUpdateSign(signLocation, "Your Score", "", "" + getPlayerScore(player), "").send(player);
	}

	private int getPlayerScore(IPlayer player)
	{
		String playerName = player.getName();
		if (scores.containsKey(playerName))
			return scores.get(playerName);

		return 0;
	}

	private Map.Entry<String, Integer> getHighestPlayer()
	{
		Map.Entry<String, Integer> currentPlayer = null;

		for (Map.Entry<String, Integer> node : scores.entrySet())
			if (currentPlayer == null || node.getValue() > currentPlayer.getValue())
				currentPlayer = node;

		return currentPlayer;
	}

	private Config config;
	private HashMap<String, Integer> scores = new HashMap<String, Integer>();
}
