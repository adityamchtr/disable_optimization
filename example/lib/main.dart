import 'package:flutter/foundation.dart';
import 'package:flutter/material.dart';

import 'package:disable_optimization/disable_optimization.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatefulWidget {
  const MyApp({super.key});

  @override
  State<MyApp> createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {

  @override
  void initState() {
    super.initState();
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: const Text('Disable Optimizations Plugin example app'),
        ),
        body: Center(
          child: Column(
            children: <Widget>[
              MaterialButton(
                child: const Text("Is Auto Start Enabled"),
                onPressed: () async {
                  var isAutoStartEnabled = await DisableOptimization.isAutoStartAvailable;
                  if (kDebugMode) {
                    print("Auto start is $isAutoStartEnabled");
                  }
                }
              ),
              MaterialButton(
                child: const Text("Is Battery optimization disabled"),
                onPressed: () async {
                  var isBatteryOptimizationDisabled = await DisableOptimization.isBatteryOptimizationDisabled;
                  if (kDebugMode) {
                    print("Battery optimization is $isBatteryOptimizationDisabled");
                  }
                }
              ),
              MaterialButton(
                child: const Text("Enable Auto Start"),
                onPressed: () {
                  DisableOptimization.showEnableAutoStartSettings();
                }
              ),
              MaterialButton(
                child: const Text("Disable Battery Optimizations"),
                onPressed: () {
                  DisableOptimization.showDisableBatteryOptimizationSettings();
                }
              ),
            ],
          ),
        ),
      ),
    );
  }
}
