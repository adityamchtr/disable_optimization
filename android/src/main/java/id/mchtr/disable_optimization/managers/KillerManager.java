package id.mchtr.disable_optimization.managers;

import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import id.mchtr.disable_optimization.devices.DeviceBase;
import id.mchtr.disable_optimization.utils.ActionsUtils;

public class KillerManager {

    public enum Actions {
        ACTION_AUTOSTART("ACTION_AUTOSTART"),
        ACTION_POWER_SAVING("ACTION_POWER_SAVING"),
        ACTION_NOTIFICATIONS("ACTION_NOTIFICATIONS");

        private final String mValue;

        Actions(String value) {
            this.mValue = value;
        }

        @NonNull
        public String toString() {
            return this.mValue;
        }
    }

    private static DeviceBase sDevice;

    public static boolean isActionAvailable(Context context, Actions actions) {
        sDevice = DevicesManager.getDevice();
        boolean actionAvailable = false;
        if (sDevice != null) {
            switch (actions) {
                case ACTION_AUTOSTART:
                    actionAvailable = sDevice.isActionAutoStartAvailable(context);
                    break;
                case ACTION_POWER_SAVING:
                    actionAvailable = sDevice.isActionPowerSavingAvailable(context);
                    break;
                case ACTION_NOTIFICATIONS:
                    actionAvailable = sDevice.isActionNotificationAvailable(context);
                    break;
            }
        }
        return actionAvailable;
    }

    /**
     * Return the intent for a specific action
     *
     * @param context the current context
     * @param actions the wanted actions
     * @return the intent
     */
    @Nullable
    private static Intent getIntentFromAction(Context context, Actions actions) {
        sDevice = DevicesManager.getDevice();
        if (sDevice != null) {
            Intent intent = null;
            switch (actions) {
                case ACTION_AUTOSTART:
                    intent = sDevice.getActionAutoStart(context);
                    break;
                case ACTION_POWER_SAVING:
                    intent = sDevice.getActionPowerSaving(context);
                    break;
                case ACTION_NOTIFICATIONS:
                    intent = sDevice.getActionNotification(context);
                    break;
            }
            if (intent != null && ActionsUtils.isIntentAvailable(context, intent)) {
                // Intent found action succeed
                return intent;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    /**
     * Execute the action
     *
     * @param context the current context
     * @param actions the wanted action to execute
     */
    public static void doAction(Context context, Actions actions) {
        // Avoid main app to crash when intent denied by using try catch
        try {
            Intent intent = getIntentFromAction(context, actions);
            if (intent != null && ActionsUtils.isIntentAvailable(context, intent)) {
                context.startActivity(intent); // Intent found action succeed
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void doActionAutoStart(Context context) {
        doAction(context, Actions.ACTION_AUTOSTART);
    }

    public static void doActionNotification(Context context) {
        doAction(context, Actions.ACTION_NOTIFICATIONS);
    }

    public static void doActionPowerSaving(Context context) {
        doAction(context, Actions.ACTION_POWER_SAVING);
    }
}
