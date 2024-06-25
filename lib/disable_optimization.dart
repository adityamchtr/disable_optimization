import 'package:flutter/services.dart';

class DisableOptimization {
  static const MethodChannel _channel = MethodChannel("disable_optimization");

  static Future<bool?> get isAutoStartAvailable async {
    return await _channel.invokeMethod("isAutoStartAvailable");
  }

  static Future<bool?> get isBatteryOptimizationAvailable async {
    return await _channel.invokeMethod("isBatteryOptimizationAvailable");
  }

  static Future<bool?> get isBatteryOptimizationDisabled async {
    return await _channel.invokeMethod("isBatteryOptimizationDisabled");
  }

  static Future<bool?> showEnableAutoStartSettings() async {
    return await _channel.invokeMethod('showEnableAutoStart');
  }

  static Future<bool?> showDisableBatteryOptimizationSettings() async {
    return await _channel.invokeMethod('showDisableBatteryOptimization');
  }
}
