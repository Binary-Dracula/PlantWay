package com.plant.way

import android.view.View
import android.view.ViewGroup
import android.widget.TextView

/**
 * Helper class to show/hide delete confirmation overlay
 * Usage:
 * 
 * val deleteHelper = DeleteConfirmationHelper(rootView)
 * deleteHelper.show(
 *     title = "Delete Task",
 *     message = "Are you sure you want to delete this Pruning task?",
 *     onConfirm = {
 *         // Handle delete action
 *     },
 *     onCancel = {
 *         // Handle cancel action (optional)
 *     }
 * )
 */
class DeleteConfirmationHelper(private val rootView: ViewGroup) {
    
    private var overlayView: View? = null
    
    fun show(
        title: String = "Delete Task",
        message: String,
        onConfirm: () -> Unit,
        onCancel: (() -> Unit)? = null
    ) {
        // Inflate the delete confirmation layout
        val inflater = android.view.LayoutInflater.from(rootView.context)
        overlayView = inflater.inflate(R.layout.layout_delete_confirmation, rootView, false)
        
        // Set title and message
        overlayView?.findViewById<TextView>(R.id.tv_delete_title)?.text = title
//        overlayView?.findViewById<TextView>(R.id.tv_delete_message)?.text = message
        
        // Set up cancel button
        overlayView?.findViewById<View>(R.id.btn_cancel)?.setOnClickListener {
            hide()
            onCancel?.invoke()
        }
        
        // Set up confirm button
        overlayView?.findViewById<View>(R.id.btn_confirm)?.setOnClickListener {
            hide()
            onConfirm()
        }
        
        // Clicking outside dismisses the dialog
        overlayView?.findViewById<View>(R.id.delete_confirmation_overlay)?.setOnClickListener {
            hide()
            onCancel?.invoke()
        }
        
        // Add to root view and show
        rootView.addView(overlayView)
        overlayView?.visibility = View.VISIBLE
    }
    
    fun hide() {
        overlayView?.let {
            it.visibility = View.GONE
            rootView.removeView(it)
        }
        overlayView = null
    }
}
