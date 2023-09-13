package com.example.hw5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TabHost

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tabs = findViewById<TabHost>(R.id.tabHost);
        tabs.setup()

        val spec1  = tabs.newTabSpec("tab1");
        spec1.setContent(R.id.tab1);
        spec1.setIndicator("1 - Clock");
        tabs.addTab(spec1);

        val spec2 = tabs.newTabSpec("tab2");
        spec2.setContent(R.id.tab2);
        spec2.setIndicator("2 - Person");
        tabs.addTab(spec2);

        tabs.currentTab = 0;
    }
}