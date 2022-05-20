package com.example.myapplication

import android.view.View
import android.content.ClipData
import android.content.ClipDescription
import android.view.DragEvent


open class BaseWidget() {
    lateinit var dragView: View

    fun onInit(view: View) {
        dragView = view
        dragView.setOnClickListener {
            val clipText = "drag and drop"
            val item = ClipData.Item(clipText)
            val mimeTypes = arrayOf(ClipDescription.MIMETYPE_TEXT_PLAIN)
            val clipData = ClipData(clipText,mimeTypes,item)
            val dragShadowBuilder = View.DragShadowBuilder(it)
            it.startDragAndDrop(clipData,dragShadowBuilder,it,0)
            //it.visibility = View.INVISIBLE
            true
        }
    }
}