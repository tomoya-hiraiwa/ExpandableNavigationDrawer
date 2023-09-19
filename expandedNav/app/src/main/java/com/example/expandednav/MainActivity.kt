package com.example.expandednav

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import com.example.expandednav.databinding.ActivityMainBinding
import java.util.IllegalFormatCodePointException

val drList = listOf<DrList>(DrList("Group1", listOf("Fragment1","Fragment2")),
    DrList("Group2", listOf("Fragment3","Fragment4"))
)

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var onOpen = false
    private var lastPosition = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val list = binding.drList
        val adapter = DrListAdapter(drList)
        list.setAdapter(adapter)
        val drl = binding.drl
        binding.main.drBt.setOnClickListener {
            drl.openDrawer(GravityCompat.START)
        }
        list.setOnChildClickListener { parent, v, groupPosition, childPosition, id ->
            val text = adapter.getChild(groupPosition, childPosition).toString()
            when(text){
                "Fragment1" -> changeFragment(Fragment1())
                "Fragment2" -> changeFragment(Fragment2())
                "Fragment3" -> changeFragment(Fragment3())
                "Fragment4" -> changeFragment(Fragment4())
            }
            drl.closeDrawer(GravityCompat.START)
            list.collapseGroup(groupPosition)
            onOpen = false
            true
        }
        list.setOnGroupClickListener { parent, v, groupPosition, id ->
            if (!onOpen){
                list.expandGroup(groupPosition)
                lastPosition = groupPosition
                onOpen = true
            }
            else{
                if (lastPosition != groupPosition){
                    list.expandGroup(groupPosition)
                    list.collapseGroup(lastPosition)
                    lastPosition = groupPosition
                }
                else{
                    list.collapseGroup(lastPosition)
                    onOpen = false
                }
            }
            true
        }

    }

    private fun changeFragment(fm:Fragment){
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerView,fm)
            .commit()
    }
}
data class DrList(val groupName: String,val childName: List<String>)