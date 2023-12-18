package ravenweave.client.module.modules.puhfy;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.weavemc.loader.api.event.SubscribeEvent;
import ravenweave.client.event.UpdateEvent;
import ravenweave.client.module.Module;
import ravenweave.client.module.modules.client.Targets;
import ravenweave.client.module.setting.impl.SliderSetting;
import ravenweave.client.module.setting.impl.TickSetting;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class Backtrack extends Module {

    private static SliderSetting ticks;
    private static TickSetting render;


    public Backtrack() {
        super("Backtrack", ModuleCategory.puhfy);
        this.registerSetting(ticks = new SliderSetting("ticks", 3, 1, 5, 1));
        this.registerSetting(render = new TickSetting("render", false));
    }


}
