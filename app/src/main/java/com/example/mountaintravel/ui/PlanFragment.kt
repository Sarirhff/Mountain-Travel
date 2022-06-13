package com.example.mountaintravel.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mountaintravel.R
import com.example.mountaintravel.databinding.FragmentHomeBinding
import com.example.mountaintravel.databinding.FragmentPlanBinding
import com.example.mountaintravel.main.DetailMountains
import com.example.mountaintravel.main.Mountain

class PlanFragment : Fragment() {

    private lateinit var rvTrip: RecyclerView
    private val list = ArrayList<Mountain>()
    private var dbinding: FragmentPlanBinding? = null
    private val binding get() = dbinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_plan, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvTrip = view.findViewById(R.id.rv_mountain)
        rvTrip.setHasFixedSize(true)
        list.addAll(listTripMountain)
        setShowRecycleview()

    }
    private val listTripMountain: ArrayList<Mountain>
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

    private fun setShowRecycleview() {
        rvTrip.layoutManager = LinearLayoutManager(context,
            LinearLayoutManager.HORIZONTAL, false)

        val listMountainAdapter = MountainAdapter(list)
        rvTrip.adapter = listMountainAdapter

        listMountainAdapter.setOnItemClickCallback(object : MountainAdapter.OnItemClickCallback{
            override fun onItemClicked(data: Mountain) {
                val infoDetail = Intent(requireContext(), DetailMountains::class.java)
                infoDetail.putExtra("DATA", data)
                startActivity(infoDetail)
            }
        })
    }
}
