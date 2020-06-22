package net.teamfruit.crashmod;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.event.FMLServerStoppedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

@Mod(
        modid = CrashMod.MOD_ID,
        name = CrashMod.MOD_NAME,
        version = CrashMod.VERSION,
        acceptableRemoteVersions = "*"
)
public class CrashMod {
    public static final String MOD_ID = "crashmod";
    public static final String MOD_NAME = "CrashMod";
    public static final String VERSION = "1.0-SNAPSHOT";

    public final CrashRunnable.CrashScheduler scheduler = new CrashRunnable.CrashScheduler();

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void tick(TickEvent.ServerTickEvent event) {
        scheduler.tick();
    }

    @Mod.EventHandler
    public void serverStarting(FMLServerStartingEvent event) {
        event.registerServerCommand(new CrashCommand(this));
    }

    @Mod.EventHandler
    public void serverStopped(FMLServerStoppedEvent event) {
        scheduler.reset();
    }
}
