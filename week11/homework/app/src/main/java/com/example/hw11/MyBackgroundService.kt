package com.example.hw11

import android.app.Service
import android.content.Intent
import android.os.IBinder

class MyBackgroundService : Service() {
    override fun onCreate() {
        super.onCreate()
        // Thực hiện các công việc cài đặt ban đầu ở đây.
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        // Thực hiện các công việc chạy trong dịch vụ nền sau ở đây.
        return START_NOT_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        // Thực hiện các công việc dọn dẹp hoặc giải phóng tài nguyên ở đây.
    }

    override fun onBind(intent: Intent?): IBinder? {
        // Trả về null nếu dịch vụ không cần giao tiếp bằng cách sử dụng IBinder.
        return null
    }
}