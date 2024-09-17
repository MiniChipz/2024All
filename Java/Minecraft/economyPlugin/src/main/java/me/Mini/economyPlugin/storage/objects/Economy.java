package me.Mini.economyPlugin.storage.objects;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter @Getter
@DatabaseTable(tableName = "playerBalances")
public class Economy {
    @DatabaseField(id = true, columnName = "playerUuid")
    private UUID playerUuid;

    @DatabaseField(columnName = "playerUsernames")
    private String playerUsernames;

    @DatabaseField(columnName = "playerMoney")
    private double playerMoney;

    public Economy() {}

    public Economy(UUID playerUuid, String playerUsernames, double playerMoney) {
        this.playerUuid = playerUuid;
        this.playerUsernames = playerUsernames;
        this.playerMoney = playerMoney;
    }
}
