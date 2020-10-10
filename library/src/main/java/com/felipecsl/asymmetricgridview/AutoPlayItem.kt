package com.felipecsl.asymmetricgridview

import android.view.View

interface AutoPlayItem {
    fun setActive()
    fun deactivate()
    val autoplayView: View
}