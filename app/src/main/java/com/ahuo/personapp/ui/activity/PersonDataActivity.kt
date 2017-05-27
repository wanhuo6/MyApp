package com.ahuo.personapp.ui.activity

import android.os.Bundle
import android.widget.TextView
import com.ahuo.personapp.R
import com.ahuo.personapp.base.BaseActivity

class PersonDataActivity : BaseActivity() {

    private var mTvTitle: TextView?=null


    override fun getLayoutId(): Int {
        return R.layout.activity_person_data
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_person_data)
        initView()
    }

    fun initView(){

        mTvTitle= findViewById(R.id.tvTitle) as TextView?


    }

    override fun initData() {
        super.initData()
        mTvTitle?.text=("你好卡特琳")

    }




}

