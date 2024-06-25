package id.mchtr.disable_optimization.managers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import id.mchtr.disable_optimization.devices.Asus;
import id.mchtr.disable_optimization.devices.DeviceAbstract;
import id.mchtr.disable_optimization.devices.DeviceBase;
import id.mchtr.disable_optimization.devices.HTC;
import id.mchtr.disable_optimization.devices.Huawei;
import id.mchtr.disable_optimization.devices.Letv;
import id.mchtr.disable_optimization.devices.Meizu;
import id.mchtr.disable_optimization.devices.OnePlus;
import id.mchtr.disable_optimization.devices.Oppo;
import id.mchtr.disable_optimization.devices.Samsung;
import id.mchtr.disable_optimization.devices.Vivo;
import id.mchtr.disable_optimization.devices.Xiaomi;
import id.mchtr.disable_optimization.devices.ZTE;

public class DevicesManager {
    private static final List<DeviceAbstract> deviceBaseList = new ArrayList<>(Arrays.asList(
            new Asus(),
            new Huawei(),
            new Letv(),
            new Meizu(),
            new OnePlus(),
            new Oppo(),
            new Vivo(),
            new HTC(),
            new Samsung(),
            new Xiaomi(),
            new ZTE()));

    public static DeviceBase getDevice() {
        List<DeviceBase> currentDeviceBase = new ArrayList<>();
        for (DeviceBase deviceBase : deviceBaseList) {
            if (deviceBase.isThatRom()) {
                currentDeviceBase.add(deviceBase);
            }
        }
        if (currentDeviceBase.size() > 1) {
            StringBuilder logDevices = new StringBuilder();
            for (DeviceBase deviceBase : currentDeviceBase) {
                logDevices.append(deviceBase.getDeviceManufacturer());
            }
        }

        if (!currentDeviceBase.isEmpty()) {
            return currentDeviceBase.get(0);
        } else {
            return null;
        }
    }
}
