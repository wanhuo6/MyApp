package com.ahuo.personapp.ui.activity

import android.app.Activity
import android.content.Intent
import com.ahuo.personapp.R
import com.ahuo.personapp.base.BaseActivity
import kotlinx.android.synthetic.main.activity_person_data.*

class PersonDataActivity : BaseActivity() {

    companion object {
        fun startActivity(activity: Activity) {
            val intent = Intent(activity, PersonDataActivity::class.java)
            activity.startActivity(intent)
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_person_data
    }

    override fun initData() {
        super.initData()
        tvTitle.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

    }




}

