package com.example.mountaintravel.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mountaintravel.R
import com.example.mountaintravel.databinding.FragmentHomeBinding
import com.example.mountaintravel.main.DetailMountains
import com.example.mountaintravel.main.Mountain

class HomeFragment : Fragment() {

    private lateinit var rvMountain : RecyclerView
    private val list = ArrayList<Mountain>()
    private var dbinding : FragmentHomeBinding? = null
    private val binding get () = dbinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_home, container, false)

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvMountain = view.findViewById(R.id.rv_popular)
        rvMountain.setHasFixedSize(true)
        list.addAll(listMountain)
        setShowRecycleview()

    }

    private val listMountain: ArrayList<Mountain>
        @SuppressLint("Recycle")
        get(){
            val dataName = resources.getStringArray(R.array.mountain_name)
            val dataImg = resources.obtainTypedArray(R.array.mountain_img)
            val listMount = ArrayList<Mountain>()
            for (i in dataName.indices) {
                val mountain = Mountain(dataName[i], dataImg.getResourceId(i, -1))
                listMount.add(mountain)
            }
            return listMount
        }

    private fun setShowRecycleview (){
        rvMountain.layoutManager = LinearLayoutManager(context,
            LinearLayoutManager.HORIZONTAL, false)

        val listMountainAdapter = MountainAdapter(list)
        rvMountain.adapter = listMountainAdapter

        listMountainAdapter.setOnItemClickCallback(object : MountainAdapter.OnItemClickCallback{
            override fun onItemClicked(data: Mountain) {
                val infoDetail = Intent(requireContext(), DetailMountains::class.java)
                infoDetail.putExtra("DATA", data)
                startActivity(infoDetail)
            }
        })
    }
}