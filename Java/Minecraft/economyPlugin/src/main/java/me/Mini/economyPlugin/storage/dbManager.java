package me.Mini.economyPlugin.storage;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import lombok.Getter;
import me.Mini.economyPlugin.EconomyPlugin;
import me.Mini.economyPlugin.storage.objects.Economy;

import java.io.File;
import java.sql.SQLException;
import java.util.UUID;

public class dbManager {
    @Getter
    private static ConnectionSource connectionSource;
    private static Dao<Economy, UUID> economyDao;

    public dbManager() {
        try {
            File dataFolder = EconomyPlugin.getInstance().getDataFolder();
            if (!dataFolder.exists()) {
                dataFolder.mkdirs();
            }

            String databaseUrl = "jdbc:sqlite:" + dataFolder.getAbsolutePath() + File.separator + "economyPlugin.db";

            connectionSource = new JdbcConnectionSource(databaseUrl);
            setupDatabase();

            EconomyPlugin.getInstance().getLogger().info("Database initialized at " + databaseUrl);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setupDatabase() throws SQLException {
        TableUtils.createTableIfNotExists(connectionSource, Economy.class);

        economyDao = DaoManager.createDao(connectionSource, Economy.class);
    }

    public static double getBalance(UUID uuid) {
        try {
            if (economyDao == null) {
                throw new IllegalStateException("economyDao is null");
            }
            Economy economy = economyDao.queryForId(uuid);
            if (economy != null) {
                return economy.getPlayerMoney();
            } else {
                EconomyPlugin.getInstance().getLogger().info("No economy entry found for UUID: " + uuid);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static void setBalance(UUID uuid, double amount) {
        try {
            if (economyDao == null) {
                throw new IllegalStateException("economyDao is null");
            }
            Economy economy = economyDao.queryForId(uuid);
            if (economy != null) {
                economy.setPlayerMoney(amount);
                economyDao.update(economy);
            } else {
                economy = new Economy(uuid, "Unknown", amount);
                economyDao.create(economy);
            }
            EconomyPlugin.getInstance().getLogger().info("Set balance of UUID: " + uuid + " to " + amount);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void giveBalance(UUID uuid, double amount) {
        try {
            if (economyDao == null) {
                throw new IllegalStateException("economyDao is null");
            }
            Economy economy = economyDao.queryForId(uuid);
            if (economy != null) {
                economy.setPlayerMoney(economy.getPlayerMoney() + amount);
                economyDao.update(economy);
            } else {
                economy = new Economy(uuid, "Unknown", amount);
                economyDao.create(economy);
            }
            EconomyPlugin.getInstance().getLogger().info("Gave " + amount + " to UUID: " + uuid);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void removeBalance(UUID uuid, double amount) {
        try {
            if (economyDao == null) {
                throw new IllegalStateException("economyDao is null");
            }
            Economy economy = economyDao.queryForId(uuid);
            if (economy != null) {
                double newBalance = economy.getPlayerMoney() - amount;
                economy.setPlayerMoney(Math.max(newBalance, 0));
                economyDao.update(economy);
            }
            EconomyPlugin.getInstance().getLogger().info("Removed " + amount + " from UUID: " + uuid);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            if (connectionSource != null) {
                connectionSource.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
