package com.example.flutterproject

import android.accessibilityservice.AccessibilityServiceInfo
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.accessibility.AccessibilityManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.flutterproject.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()

    }

    fun init() {

        binding.apply {
            button1.setOnClickListener {
                val okay = checkAccessibilityPermissions()
                if (okay) {
                    Toast.makeText(this@MainActivity, "성공!", Toast.LENGTH_SHORT).show()

                }
                else {
                    Toast.makeText(this@MainActivity, "실패...", Toast.LENGTH_SHORT).show()
                }
            }
            button2.setOnClickListener {
                setAccessibilityPermissions()
            }
        }

    }

    fun checkAccessibilityPermissions(): Boolean {
        val accessibilityManager =
            getSystemService(Context.ACCESSIBILITY_SERVICE) as AccessibilityManager
        val list =
            accessibilityManager.getEnabledAccessibilityServiceList(AccessibilityServiceInfo.FEEDBACK_GENERIC)
        Log.d("service_test", "size : " + list.size)
        for (i in list.indices) {
            val info = list[i]
            if (info.resolveInfo.serviceInfo.packageName == application.packageName) {
                return true
            }
        }
        return false
    }

    fun setAccessibilityPermissions() {
        val permissionDialog: AlertDialog.Builder = AlertDialog.Builder(this)
        permissionDialog.setTitle("접근성 권한 설정")
        permissionDialog.setMessage("앱을 사용하기 위해 접근성 권한이 필요합니다.")
        permissionDialog.setPositiveButton("허용",
            DialogInterface.OnClickListener { dialog, which ->
                startActivity(Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS))
                return@OnClickListener
            }).create().show()
    }

}