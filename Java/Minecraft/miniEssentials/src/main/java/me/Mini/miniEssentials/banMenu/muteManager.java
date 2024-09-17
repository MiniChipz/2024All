package me.Mini.miniEssentials.banMenu;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public class muteManager {
    private final Map<UUID, Long> mutedPlayers = new HashMap<>();

    public void mutePlayer(UUID playerUUID, long muteEndTime) {
        mutedPlayers.put(playerUUID, muteEndTime);
    }

    public void unmutePlayer(UUID playerUUID) {
        mutedPlayers.remove(playerUUID);
    }

    public boolean isPlayerMuted(UUID playerUUID) {
        Long muteEndTime = mutedPlayers.get(playerUUID);
        if (muteEndTime == null) {
            return false;
        }
        if (System.currentTimeMillis() > muteEndTime) {
            mutedPlayers.remove(playerUUID);
            return false;
        }
        return true;
    }

    public Set<UUID> getMutedPlayers() {
        return mutedPlayers.keySet();
    }
}
