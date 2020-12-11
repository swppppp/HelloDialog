package com.example.hellodialog

import android.content.Context
import android.content.DialogInterface
import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import android.widget.FrameLayout
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import dev.eastar.ktx.isNotNullOrBlank
import dev.eastar.smart.R

class CustomAlert(context: Context) : AlertDialog(context) {

    val builder = AlertDialog.Builder(context)

    interface OnPositiveClickListener {

    }
    interface OnNegativeClickListener {

    }

    class Builder {
        var context: Context? = null
        var title: String? = null
        var msg: String = ""
        var positiveBtnTxt: String? = ""
        var negativeBtnTxt: String? = ""

        lateinit var contentView: View

        var positiveBtnClickListener: OnPositiveClickListener? = null
        var negativeBtnClickListener: OnNegativeClickListener? = null
        var cancelListener: DialogInterface.OnCancelListener? = null
        var dismissListener: DialogInterface.OnDismissListener? = null

        var cancelable: Boolean = false

        fun Builder(context: Context) { this.context = context }
        fun setTitle(title: Int): Builder? {
            this.title = context!!.getText(title) as String
            return this
        }
        fun setTitle(title: String?): Builder? {
            this.title = title
            return this
        }
        fun setMsg(msg: String): Builder? {
            this.msg = msg
            return this
        }
        fun setMsg(msg: Int): Builder? {
            this.msg = context!!.getText(msg) as String
            return this
        }
        fun setPositiveBtn(positiveBtn: String?, listener: OnPositiveClickListener): Builder {
            positiveBtnTxt = positiveBtn
            positiveBtnClickListener = listener
            return this
        }
        fun setNegativeBtn(negativeBtn: String?, listener: OnNegativeClickListener): Builder {
            negativeBtnTxt = negativeBtn
            negativeBtnClickListener = listener
            return this
        }
        fun setDismissListener(listener: DialogInterface.OnDismissListener): Builder? {
            dismissListener = listener
            return this
        }

        fun create(): CustomAlert {
            val inflater: LayoutInflater = context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

            var dlg: CustomAlert = CustomAlert(context!!)
            dlg.requestWindowFeature(Window.FEATURE_NO_TITLE)

            if (contentView == null) {
                contentView = inflater.inflate(R.layout.custom_dialog, null)

                dlg.addContentView(contentView, ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                ))
                val frTitleArea = contentView.findViewById<FrameLayout>(R.id.fr_title_area)
                val tvTitle = contentView.findViewById<TextView>(R.id.tv_dialog_title)

                if (title.isNotNullOrBlank()) {
                    frTitleArea.visibility = View.VISIBLE
                    if (title == "LOGO") {
                        contentView.findViewById<View>(R.id.ll_default_header).visibility = View.VISIBLE
                        contentView.findViewById<View>(R.id.tv_dialog_title).visibility = View.GONE
                        contentView.findViewById<View>(R.id.xbutton).visibility = View.GONE
                    } else {
                        contentView.findViewById<View>(R.id.ll_default_header).visibility = View.GONE
                        contentView.findViewById<View>(R.id.tv_dialog_title).visibility = View.VISIBLE
                        contentView.findViewById<View>(R.id.xbutton).visibility = View.VISIBLE
                        contentView.findViewById<View>(R.id.xbutton).setOnClickListener {
                            dlg.dismiss()
                        }
                        tvTitle.text = title
                    }
                } else {
                    contentView.findViewById<View>(R.id.ll_default_header).visibility = View.GONE
                    contentView.findViewById<View>(R.id.tv_dialog_title).visibility = View.VISIBLE
                    frTitleArea.visibility = View.GONE
                    contentView.findViewById<View>(R.id.fr_dialog_msg).setBackgroundResource(R.drawable.top_rounding_background)
                }
                val tvMsg = contentView.findViewById<TextView>(R.id.tv_dialog_msg)
                tvMsg.movementMethod = ScrollingMovementMethod()

                if (!(msg == null || "" == msg)) {
                    tvMsg.visibility = View.VISIBLE
                    tvMsg.setText(msg)
                } else {
                    tvMsg.visibility = View.GONE
                }
            }

            val negativeBtn = contentView.findViewById<Button>(R.id.btn_dialog_negative)
            if (negativeBtnTxt.isNotNullOrBlank()) {
                negativeBtn.visibility = View.VISIBLE
                negativeBtn.text = negativeBtnTxt

                if (negativeBtnClickListener == null) negativeBtn.setOnClickListener { dlg.dismiss()}
                else negativeBtn.setOnClickListener { negativeBtnClickListener }
            } else negativeBtn.visibility = View.GONE
            val positiveBtn = contentView.findViewById<Button>(R.id.btn_dialog_positive)
            if (positiveBtnTxt.isNotNullOrBlank()) {
                positiveBtn.visibility = View.VISIBLE
                positiveBtn.text = positiveBtnTxt

                if (positiveBtnClickListener == null) positiveBtn.setOnClickListener { dlg.dismiss()}
                else positiveBtn.setOnClickListener { positiveBtnClickListener }
            } else positiveBtn.visibility = View.GONE

            dlg.setContentView(contentView)
            dlg.setCancelable(cancelable)

            return dlg
        }

    }

    fun alert(msg: String, title: String?, positiveBtn: String?, positiveListener: () -> Unit): AlertDialog =
        builder.create().apply {
            this.
        }
}