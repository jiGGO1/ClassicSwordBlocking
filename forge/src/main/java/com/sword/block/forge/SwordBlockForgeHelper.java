package com.sword.block.forge;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;

import java.lang.reflect.Method;

import static com.sword.block.forge.SwordBlockForge.LOGGER;

public class SwordBlockForgeHelper {

    public static Object getModEventBus(FMLJavaModLoadingContext context) {
        try {
            var method = getMethod(context.getClass(), "getModEventBus");
            if (method == null) {
                method = getMethod(context.getClass(), "getModBusGroup");
            }
            return method.invoke(context);
        } catch (Exception exception) {
            LOGGER.error(exception.getMessage());
            return null;
        }
    }

    public static void register(DeferredRegister<?> object, Object bus) {
        var clazz = forName("net.minecraftforge.eventbus.api.IEventBus");
        var type = clazz != null ? clazz : forName("net.minecraftforge.eventbus.api.bus.BusGroup");
        var register = getMethod(object.getClass(), "register", type);
        try {
            register.invoke(object, bus);
        } catch (Exception exception) {
            LOGGER.error(exception.getMessage());
        }
    }

    public static void register(Object o) {
        try {
            var clazz = MinecraftForge.class;
            var EVENT_BUS = clazz.getField("EVENT_BUS").get(null);
            var register = getMethod(EVENT_BUS.getClass(), "register", Object.class);
            register.invoke(EVENT_BUS, o);
        } catch (Exception exception) {
            LOGGER.error(exception.getMessage());
        }
    }

    public static Method getMethod(Class<?> clazz, String name, Class<?>... types) {
        try {
            LOGGER.info(String.format("Load %s.%s", clazz.getName(), name));
            return clazz.getMethod(name, types);
        } catch (NoSuchMethodException exception) {
            LOGGER.info(String.format("Not Found %s.%s", clazz.getName(), name));
            return null;
        }
    }

    public static Class<?> forName(String className) {
        try {
            return Class.forName(className);
        } catch (ClassNotFoundException e) {
            LOGGER.info(String.format("Not Found %s", className));
            return null;
        }
    }

}