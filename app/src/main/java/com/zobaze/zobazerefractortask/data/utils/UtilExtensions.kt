package com.zobaze.zobazerefractortask.data.utils

import android.content.Context
import android.widget.Toast

// to show the short toast
fun Context.showShortToast(message:String){
    Toast.makeText(this,message,Toast.LENGTH_SHORT).show()
}
// to show long toasts
fun Context.showLongToast(message:String){
    Toast.makeText(this,message,Toast.LENGTH_LONG).show()
}