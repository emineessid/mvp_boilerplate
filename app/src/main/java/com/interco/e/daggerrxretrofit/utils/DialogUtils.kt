package com.interco.e.daggerrxretrofit.utils

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.interco.e.daggerrxretrofit.R

object DialogUtils {


    fun showBottomMessage(context: Activity, msg: String, delay: Long, color: Int, action: Runnable?) {
        val container = context.findViewById<ViewGroup>(android.R.id.content)
        val view = LayoutInflater.from(context).inflate(R.layout.layout_bottom_message, container, false)

        val message = view.findViewById<TextView>(R.id.txv_msg)
        message.apply {
            setBackgroundColor(ContextCompat.getColor(context, color))
            text = msg
        }
        container.addView(view)
        view.apply {
            alpha = 0f
            animate().alphaBy(1f).setDuration(500).withEndAction {
                if (view.isAttachedToWindow) {
                    view.animate().alpha(0f).setStartDelay(delay).duration = 500
                }
                action?.run()
            }
        }
    }

    @JvmOverloads
    fun showActionDialog(activity: Activity, message: String, actionButtonText: String, cancelButtonText: String, action: Runnable?, isNegativeAction: Boolean = false) {
        val container = activity.findViewById<ViewGroup>(android.R.id.content)
        val view = LayoutInflater.from(activity).inflate(R.layout.ios_style_dialog, container, false)
        container.addView(view)

        // Consume touch
        view.setOnTouchListener { _, event -> true }

        // Message Text
        val messageTextView = view.findViewById<TextView>(R.id.message_textview)
        messageTextView.text = message

        // Action Button
        val actionButton = view.findViewById<TextView>(R.id.txv_confirm)
        if (!TextUtils.isEmpty(actionButtonText)) {
            actionButton.setTextColor(ContextCompat.getColor(activity, if (isNegativeAction) R.color.error_message_color else R.color.colorPrimaryDark))
            actionButton.visibility = View.VISIBLE
            actionButton.text = actionButtonText
            if (action != null)
                actionButton.setOnClickListener {
                    action.run()
                    container.removeView(view)
                }
        } else {
            actionButton.visibility = View.GONE
        }

        // Cancel Button
        val cancelButton = view.findViewById<TextView>(R.id.txv_annuler)
        if (!TextUtils.isEmpty(cancelButtonText)) {
            cancelButton.visibility = View.VISIBLE
            cancelButton.text = cancelButtonText
            cancelButton.setOnClickListener { v -> container.removeView(view) }
        } else {
            cancelButton.visibility = View.GONE
        }
    }

    fun progressDialog(context: Context): Dialog {
        val dialog = Dialog(context)
        val inflate = LayoutInflater.from(context).inflate(R.layout.progress_dialog, null)
        dialog.setContentView(inflate)
        dialog.setCancelable(false)
        dialog.window!!.setBackgroundDrawable(
                ColorDrawable(Color.TRANSPARENT))
        return dialog
    }


    //

    //
    //    public static void showStatusBarMessage(Activity context, String msg, String errorMessage, boolean isError, boolean isTranslucentStatusBar) {
    //        ViewGroup container = context.findViewById(android.R.id.content);
    //        View customToastroot = LayoutInflater.from(context).inflate(R.layout.layout_status_bar_message, container, false);
    //        customToastroot.setBackgroundColor(ContextCompat.getColor(context, isError ? R.color.error_message_color : R.color.success_message_color));
    //
    //        TextView textMsgError = errorMessage//view.findview...
    //        textMsgError.setText(errorMessage);
    //        customToastroot.setAlpha(0);
    //
    //        container.addView(customToastroot);
    //
    //        if (isTranslucentStatusBar && Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
    //            int statusBarHeight = 55;
    //            customToastroot.getLayoutParams().height += statusBarHeight;
    //            customToastroot.setPadding(0, statusBarHeight, 0, 0);
    //        }
    //
    //        AnimatorSet animatorSet = new AnimatorSet();
    //        animatorSet.addListener(new AnimatorListenerAdapter() {
    //            @Override
    //            public void onAnimationEnd(Animator animation) {
    //                super.onAnimationEnd(animation);
    //                try {
    //                    container.removeView(customToastroot);
    //                } catch (Exception e) {
    //                    Timber.e(e, "showStatusBarMessage - animate");
    //                }
    //            }
    //        });
    //        animatorSet.playSequentially(ObjectAnimator.ofFloat(customToastroot, "alpha", 0f, 1f).setDuration(500),
    //                ObjectAnimator.ofFloat(customToastroot, "alpha", 1f, 1f).setDuration(2000),
    //                ObjectAnimator.ofFloat(customToastroot, "alpha", 1f, 0f).setDuration(500));
    //        animatorSet.start();
    //    }


}
