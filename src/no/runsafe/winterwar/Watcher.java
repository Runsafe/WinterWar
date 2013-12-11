package no.runsafe.winterwar;

import no.runsafe.framework.api.entity.IEntity;
import no.runsafe.framework.api.event.entity.IEntityDamageByEntityEvent;
import no.runsafe.framework.api.player.IPlayer;
import no.runsafe.framework.minecraft.Buff;
import no.runsafe.framework.minecraft.entity.ProjectileEntity;
import no.runsafe.framework.minecraft.entity.RunsafeProjectile;
import no.runsafe.framework.minecraft.event.entity.RunsafeEntityDamageByEntityEvent;

public class Watcher implements IEntityDamageByEntityEvent
{
	public Watcher(PlayerManager playerManager)
	{
		this.playerManager = playerManager;
	}

	@Override
	public void OnEntityDamageByEntity(RunsafeEntityDamageByEntityEvent event)
	{
		IEntity damagedEntity = event.getEntity();

		// Check the entity hit was a player and they are in the correct world.
		if (damagedEntity instanceof IPlayer && playerManager.isInWinterWorld(damagedEntity))
		{
			// If shooter is not null, it is a player who threw a snowball.
			IPlayer shooter = getShooterPlayer(event.getDamageActor());

			if (shooter != null)
			{
				IPlayer damagedPlayer = (IPlayer) damagedEntity; // The player who was hit.
				damagedPlayer.addBuff(Buff.Utility.Movement.DecreaseSpeed.amplification(2).duration(3)); // Slow player for 3 seconds.
				playerManager.registerHit(shooter); // Register a hit for the shooter.
			}
			else
			{
				// We were not hit by a snowball thrown from a player, cancel the event.
				event.cancel();
			}
		}
	}

	private IPlayer getShooterPlayer(IEntity entity)
	{
		if (entity == null || !isSnowball(entity))
			return null;

		return ((RunsafeProjectile) entity).getShooterPlayer();
	}

	private boolean isSnowball(IEntity entity)
	{
		return entity.getEntityType() == ProjectileEntity.Snowball;
	}

	private PlayerManager playerManager;
}
