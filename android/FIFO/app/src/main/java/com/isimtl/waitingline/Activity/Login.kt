package com.isimtl.waitingline.Activity

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import com.isimtl.waitingline.Extensions.MyImageAnalyzer
import com.isimtl.waitingline.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.login.*
import java.security.cert.CertPathValidator

class Login : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)


        homeback.setOnClickListener {
            finish()
        }



    }
}

