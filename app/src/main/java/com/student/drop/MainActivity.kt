package com.student.drop

import android.Manifest
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.student.drop.databinding.ActivityMainBinding
import com.tbruyelle.rxpermissions3.RxPermissions
class MainActivity : AppCompatActivity() {
    lateinit var mBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setContentView(mBinding.root)
        mBinding.btn.setOnClickListener {
            RxPermissions(this).request(Manifest.permission.CAMERA)
                .subscribe {

                }
        }
    }
}