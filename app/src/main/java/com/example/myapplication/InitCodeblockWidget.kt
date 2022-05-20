package com.example.myapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.View

class InitCodeblockWidget(context: Context) : BaseWidget() {
    var view: View

    init {
        view = LayoutInflater.from(context).inflate(R.layout.init_codeblock,null,false)
        super.onInit(view)
    }
}