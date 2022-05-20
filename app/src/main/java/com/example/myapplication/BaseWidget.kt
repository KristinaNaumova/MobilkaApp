package com.example.myapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.View

class BaseWidget(context: Context) {
    var view: View = LayoutInflater.from(context).inflate(R.layout.init_codeblock,null,false)
}