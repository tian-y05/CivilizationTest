package com.router.civilization.library.view

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.router.civilization.library.R


/**
 * Created by tian
on 2019/8/19.
 */
class SetSuccessDialog(context: Context?, themeResId: Int) : Dialog(context, themeResId) {
    class Builder(private val context: Context) {
        private var title: String? = null
        private var message: String? = null
        private var positiveButtonContent: String? = null
        private var negativeButtonContent: String? = null
        private var positiveButtonListener: DialogInterface.OnClickListener? = null
        private var negativeButtonListener: DialogInterface.OnClickListener? = null
        private var imageid: Int = 0
        private var color: Int = 0
        private var withOffSize: Float = 0.toFloat()
        private var heightOffSize: Float = 0.toFloat()


        fun setTitle(title: String): Builder {
            this.title = title
            return this
        }


        fun setTitle(title: Int): Builder {
            this.title = context.getText(title) as String
            return this
        }

        fun setMessage(message: String): Builder {
            this.message = message
            return this
        }

        fun setMessageColor(color: Int): Builder {
            this.color = color
            return this
        }

        fun setImageHeader(Imageid: Int): Builder {
            this.imageid = Imageid
            return this
        }


        fun setPositiveButton(text: String, listener: DialogInterface.OnClickListener): Builder {
            this.positiveButtonContent = text
            this.positiveButtonListener = listener
            return this
        }

        fun setPositiveButton(textId: Int, listener: DialogInterface.OnClickListener): Builder {
            this.positiveButtonContent = context.getText(textId) as String
            this.positiveButtonListener = listener
            return this
        }

        fun setNegativeButton(text: String, listener: DialogInterface.OnClickListener): Builder {
            this.negativeButtonContent = text
            this.negativeButtonListener = listener
            return this
        }

        fun setNegativeButton(textId: Int, listener: DialogInterface.OnClickListener): Builder {
            this.negativeButtonContent = context.getText(textId) as String
            this.negativeButtonListener = listener
            return this
        }

        fun setWith(v: Float): Builder {
            this.withOffSize = v
            return this
        }

        fun setContentView(v: Float): Builder {
            this.heightOffSize = v
            return this
        }

        fun create(): SetSuccessDialog {
            /**
             * 利用我们刚才自定义的样式初始化Dialog
             */
            val dialog = SetSuccessDialog(context,
                    R.style.CommonDialogStyle)
            /**
             * 下面就初始化Dialog的布局页面
             */
            val inflater = context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val dialogLayoutView = inflater.inflate(R.layout.dialog_success,
                    null)
            dialog.addContentView(dialogLayoutView, ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT))

            if (imageid != 0) {
                (dialogLayoutView.findViewById<View>(R.id.iv_image_header) as ImageView).visibility = View.VISIBLE
                (dialogLayoutView.findViewById<View>(R.id.iv_image_header) as ImageView)
                        .setImageResource(imageid)
            } else {
                (dialogLayoutView.findViewById<View>(R.id.iv_image_header) as ImageView).visibility = View.GONE
            }

            if (!TextUtils.isEmpty(title)) {
                (dialogLayoutView.findViewById<View>(R.id.tv_dialog_title) as TextView).visibility = View.VISIBLE
                (dialogLayoutView.findViewById<View>(R.id.tv_dialog_title) as TextView).text = title
            } else {
                (dialogLayoutView.findViewById<View>(R.id.tv_dialog_title) as TextView).visibility = View.GONE
                // Log.w(context.getClass().toString(), "未设置对话框标题！");
            }

            if (color != 0) {
                val viewById = dialogLayoutView.findViewById<View>(R.id.dialog_content) as TextView
                viewById.setTextColor(color)
            }

            if (!TextUtils.isEmpty(message)) {
                (dialogLayoutView.findViewById<View>(R.id.dialog_content) as TextView).visibility = View.VISIBLE
                (dialogLayoutView.findViewById<View>(R.id.dialog_content) as TextView).text = message
            }  else {
                (dialogLayoutView.findViewById<View>(R.id.dialog_content) as TextView).visibility = View.GONE
            }

            if (!TextUtils.isEmpty(positiveButtonContent)) {
                (dialogLayoutView.findViewById<View>(R.id.tv_dialog_pos) as TextView).visibility = View.VISIBLE
                (dialogLayoutView.findViewById<View>(R.id.tv_dialog_pos) as TextView).text = positiveButtonContent
                if (positiveButtonListener != null) {
                    (dialog.findViewById<View>(R.id.tv_dialog_pos) as TextView)
                            .setOnClickListener { positiveButtonListener!!.onClick(dialog, -1) }

                }
            } else {
                (dialogLayoutView.findViewById<View>(R.id.tv_dialog_pos) as TextView).visibility = View.GONE
            }

            if (!TextUtils.isEmpty(negativeButtonContent)) {
                (dialogLayoutView.findViewById<View>(R.id.tv_dialog_neg) as TextView).visibility = View.VISIBLE
                (dialogLayoutView.findViewById<View>(R.id.tv_dialog_neg) as TextView).text = negativeButtonContent
                if (negativeButtonListener != null) {
                    (dialogLayoutView
                            .findViewById<View>(R.id.tv_dialog_neg) as TextView)
                            .setOnClickListener { negativeButtonListener!!.onClick(dialog, -2) }
                }
            } else {
                (dialogLayoutView.findViewById<View>(R.id.tv_dialog_neg) as TextView).visibility = View.GONE
            }
            /**
             * 将初始化完整的布局添加到dialog中
             */
            dialog.setContentView(dialogLayoutView)
            /**
             * 禁止点击Dialog以外的区域时Dialog消失
             */
            dialog.setCanceledOnTouchOutside(false)


            val window = dialog.window
            val context = this.context as Activity
            val windowManager = context.windowManager

            val defaultDisplay = windowManager.defaultDisplay

            val attributes = window!!.attributes

            if (withOffSize.toDouble() != 0.0) {

                attributes.width = (defaultDisplay.width * withOffSize).toInt()
            } else {
                attributes.width = (defaultDisplay.width * 0.77).toInt()

            }
            if (heightOffSize.toDouble() != 0.0) {

                attributes.height = (defaultDisplay.height * heightOffSize).toInt()
            }
            window.attributes = attributes
            return dialog
        }
    }
}