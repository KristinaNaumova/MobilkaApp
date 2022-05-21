package com.example.myapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.View

class PrintCodeblockWidget(context: Context) : BaseWidget() {
    var view: View

    init {
        view = LayoutInflater.from(context).inflate(R.layout.print_block, null, false)
        super.onInit(view)
    }
}