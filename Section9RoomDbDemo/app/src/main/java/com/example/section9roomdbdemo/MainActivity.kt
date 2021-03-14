package com.example.section9roomdbdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.example.section9roomdbdemo.databinding.ActivityMainBinding
import com.example.section9roomdbdemo.db.SubscriberDaoRepository
import com.example.section9roomdbdemo.db.SubsriberDatabase

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainActivityViewModel

    private lateinit var adapter: SubscriberRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        val dao = SubsriberDatabase.getInstance(applicationContext).subscriberDao

        val repository = SubscriberDaoRepository(dao)

        val factory = MainViewModelProvider(repository)

        viewModel = ViewModelProvider(this,factory).get(MainActivityViewModel::class.java)

        binding.subViewModel = viewModel

        binding.lifecycleOwner = this

        initRecyclerView()

        setupObserver()

    }

    private fun initRecyclerView(){
        adapter = SubscriberRecyclerViewAdapter {
            Toast.makeText(applicationContext, it.name, Toast.LENGTH_SHORT).show()
            viewModel.subscribeUpdateOrDelete(it)
        }
        binding.recyclerViewSubscriber.adapter = adapter
    }

    private fun setupObserver(){
        viewModel.subscribersList.observe(this, {
            adapter.addAllSub(it)
            Log.i("TAG", "setupObserver: $it")
        })


        viewModel.errorSubscribers.observe(this,{
            Toast.makeText(applicationContext, it.getContentIfNotHandled(), Toast.LENGTH_SHORT).show()
        })
    }

}