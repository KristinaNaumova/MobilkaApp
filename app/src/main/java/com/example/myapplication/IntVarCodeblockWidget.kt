package com.example.myapplication


import android.content.Context
import android.view.LayoutInflater
import android.view.View

class IntVarCodeblockWidget(context: Context) : BaseWidget() {
    var view: View

    init {
        view = LayoutInflater.from(context).inflate(R.layout.assignment_block, null, false)
        super.onInit(view)
    }
}