package com.praktikunandroid7live.ui.user

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.praktikunandroid7live.R
import com.praktikunandroid7live.databinding.ActivityUserBinding
import com.praktikunandroid7live.model.UserData

class UserActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUserBinding
    private val list = ArrayList<UserData>()
    private val viewModel: UserViewModel by lazy {
        ViewModelProvider(this).get(UserViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvUsers.setHasFixedSize(true)

        binding.progressBarUser.visibility = View.VISIBLE

        getListUser()

    }

    private fun getListUser() {
        viewModel.response.observe(this, {
            binding.progressBarUser.visibility = View.INVISIBLE
            list.addAll(it.data)
            binding.rvUsers.layoutManager = LinearLayoutManager(this)
            val listUserAdapter = ListUserAdapter(list)
            binding.rvUsers.adapter = listUserAdapter
        })
    }
}