package tk.kingalteriv.mesystem.utilities;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.bukkit.inventory.ItemStack;

public final class ReflectionUtils {

    private static String version;

    private static Method methodAsCraftMirror;
    private static Method methodAsNMSCopy;
    private static Method methodSave;
    private static Method methodAsString;
    private static Method methodParse;
    private static Method methodFromNBTCompound;

    private static Constructor<?> constructorNBTTagCompound;

    private ReflectionUtils() { }

    public static ItemStack itemStackFromNBT(String nbtString) {
        try {
            Object nbtCompound = methodParse.invoke(null, nbtString);
            Object nmsItemStack = methodFromNBTCompound.invoke(null, nbtCompound);
            return (ItemStack) methodAsCraftMirror.invoke(null, nmsItemStack);
        } catch (ReflectiveOperationException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String itemStackToNBT(ItemStack item) {
        try {
            Object nmsItemStack = methodAsNMSCopy.invoke(null, item);
            methodSave.invoke(nmsItemStack, constructorNBTTagCompound.newInstance());
            return (String) methodAsString.invoke(nmsItemStack);
        } catch (ReflectiveOperationException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void init(String version) {
        ReflectionUtils.version = version;

        Class<?> classNBTBase = getNMSClass("NBTBase");
        Class<?> classNBTTagCompound = getNMSClass("NBTTagCompound");
        Class<?> classMojangsonParser = getNMSClass("MojangsonParser");
        Class<?> classItemStack = getNMSClass("ItemStack");
        Class<?> classCraftItemStack = getCraftBukkitClass("inventory.CraftItemStack");

        methodAsCraftMirror = getMethod(classCraftItemStack, "asCraftMirror", classItemStack);
        methodAsNMSCopy = getMethod(classCraftItemStack, "asNMSCopy", ItemStack.class); // Bukkit's ItemStack
        methodSave = getMethod(classItemStack, "save", classNBTTagCompound);
        methodAsString = getMethod(classNBTBase, "asString");
        methodParse = getMethod(classMojangsonParser, "parse", String.class);
        methodFromNBTCompound = getMethod(classItemStack, "a", classNBTTagCompound); // NOTE: This is obfuscuated and may need updating between versions

        constructorNBTTagCompound = getConstructor(classNBTTagCompound);
    }

    private static Class<?> getCraftBukkitClass(String className) {
        try {
            return Class.forName("org.bukkit.craftbukkit." + version + "." + className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static Class<?> getNMSClass(String className) {
        try {
            return Class.forName("net.minecraft.server." + version + "." + className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static Method getMethod(Class<?> clazz, String methodName, Class<?>... params) {
        try {
            return clazz.getDeclaredMethod(methodName, params);
        } catch (ReflectiveOperationException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static Constructor<?> getConstructor(Class<?> clazz, Class<?>... params) {
        try {
            return clazz.getDeclaredConstructor(params);
        } catch (ReflectiveOperationException e) {
            e.printStackTrace();
            return null;
        }
    }

}
