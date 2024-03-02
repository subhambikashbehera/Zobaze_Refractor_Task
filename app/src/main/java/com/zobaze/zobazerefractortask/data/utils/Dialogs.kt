package com.zobaze.zobazerefractortask.data.utils

import android.app.Activity
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Handler
import android.os.Looper
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.zobaze.zobazerefractortask.R

object Dialogs {

    private var dismissHandler: Handler? = null
    private var dialogLoading: AlertDialog? = null

    fun showLoadingDialog(
        state: Boolean,
        context: Activity,
        message: String? = null,
        isCancellable: Boolean? = null,
        time:Long = 8000
    ) {
        try {
            if (!context.isFinishing || !context.isDestroyed) {
                context.runOnUiThread {
                    if (state) {
                        if (dialogLoading != null) {
                            if (dialogLoading?.isShowing == true) {
                                dialogLoading?.dismiss()
                                dismissHandler?.removeCallbacksAndMessages(null)
                            }
                        }
                        val popUp = MaterialAlertDialogBuilder(context, R.style.TransparentDialog)
                        val view =
                            context.layoutInflater.inflate(R.layout.layout_progress_dialog, null)
                        popUp.setView(view)
                        val progressTitle = view.findViewById<TextView>(R.id.progressTitle)
                        progressTitle.text = message

                        // Schedule dismiss after 5 seconds if not dismissed manually
                        dismissHandler?.removeCallbacksAndMessages(null) // Cancel previous callbacks
                        dismissHandler = Handler(Looper.myLooper()!!)
                        dismissHandler?.postDelayed({
                            if (dialogLoading?.isShowing == true) {
                                dialogLoading?.dismiss()
                            }
                        }, time) // 5000 milliseconds = 5 seconds

                        dialogLoading = popUp.create()
                        dialogLoading?.setCancelable(isCancellable?:true)
                        dialogLoading?.setCanceledOnTouchOutside(isCancellable?:true)
                        dialogLoading?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                        dialogLoading?.show()
                    } else {
                        if (dialogLoading != null) {
                            if (dialogLoading?.isShowing == true) {
                                dialogLoading?.dismiss()
                                dismissHandler?.removeCallbacksAndMessages(null)
                            }
                        }

                    }
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

}