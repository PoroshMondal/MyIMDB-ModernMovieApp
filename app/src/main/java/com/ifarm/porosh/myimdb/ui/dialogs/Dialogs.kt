package com.ifarm.porosh.myimdb.ui.dialogs

import android.app.Activity
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatDelegate
import com.ifarm.porosh.myimdb.R
import com.ifarm.porosh.myimdb.databinding.LayoutNotifyDialogBinding
import com.techiness.progressdialoglibrary.ProgressDialog

class Dialogs() {

    private lateinit var context: Activity
    constructor (context: Activity) : this() {
        this.context = context
    }

    fun showNotifyDialog(message: String){
        val binding = LayoutNotifyDialogBinding.inflate(context.layoutInflater,null,false)
        val view = binding.root

        val builder = AlertDialog.Builder(context)
        builder.setView(view)
        builder.setCancelable(true)

        val dialog : AlertDialog = builder.create()
        dialog.show()

        dialog.window?.attributes?.windowAnimations = R.style.BubbleDialogAnimation
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        binding.txtMsg.text = message

        binding.btnOk.setOnClickListener {
            dialog.dismiss()
        }
    }

    /*fun progressDialog() : ProgressDialog {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.R)
        {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM) //This is optional. This will enable Android's Autotheming for the entire App
            val progressDialog = ProgressDialog(context,ProgressDialog.THEME_FOLLOW_SYSTEM) // Enables AutoTheming for the ProgressDialog instance.
            return progressDialog
        }
        else //Autotheming not compatible
        {
            val progressDialog = ProgressDialog(context,ProgressDialog.THEME_DARK) // or any other constructors mentioned above
            return progressDialog
        }
    }*/

}