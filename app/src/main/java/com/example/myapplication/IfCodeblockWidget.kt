package com.example.myapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.View

class IfCodeblockWidget(context: Context) : BaseWidget() {
    var view: View

    init {
        view = LayoutInflater.from(context).inflate(R.layout.if_block,null,false)
        super.onInit(view)
    }
}