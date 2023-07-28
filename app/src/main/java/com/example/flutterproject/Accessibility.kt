package com.example.flutterproject

import android.accessibilityservice.AccessibilityService
import android.content.Intent
import android.util.Log
import android.view.accessibility.AccessibilityEvent
import android.widget.Toast


class Accessibility : AccessibilityService() {
    override fun onAccessibilityEvent(event: AccessibilityEvent?) {
        val denyApp = false
        if (event != null) {
            if (event.eventType === AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED) {
                if ("com.google.android.youtube".equals(event.getPackageName())) {
                    Toast.makeText(
                        this.applicationContext,
                        (event.getPackageName().toString() + "앱이 거부되었습니다"),
                        Toast.LENGTH_LONG
                    )
                    gotoHome()
                }

                Log.e("testFlutter", "Catch Event Package Name : " + event.getPackageName());
                Log.e("testFlutter", "Catch Event TEXT : " + event.getText());
                Log.e("testFlutter", "Catch Event ContentDescription : " + event.getContentDescription());
                Log.e("testFlutter", "Catch Event getSource : " + event.getSource());
                Log.e("testFlutter", "=========================================================================");
            }
        }
    }

    override fun onInterrupt() {
        TODO("Not yet implemented")
    }

    private fun gotoHome() {
        val intent = Intent()
        intent.action = "android.intent.action.MAIN"
        intent.addCategory("android.intent.category.HOME")
        intent.addFlags(
            Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS
                    or Intent.FLAG_ACTIVITY_FORWARD_RESULT
                    or Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_PREVIOUS_IS_TOP
                    or Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED
        )
        startActivity(intent)
        println("youtube 차단")
    }


}