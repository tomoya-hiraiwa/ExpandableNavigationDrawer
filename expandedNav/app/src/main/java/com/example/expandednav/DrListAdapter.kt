package com.example.expandednav

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import com.example.expandednav.databinding.DrlistItemBinding

class DrListAdapter(private val dataList:List<DrList>):BaseExpandableListAdapter() {
    override fun getGroupCount(): Int {
        return dataList.size
    }

    override fun getChildrenCount(groupPosition: Int): Int {
        return dataList[groupPosition].childName.size
    }

    override fun getGroup(groupPosition: Int): Any {
        return dataList[groupPosition]
    }

    override fun getChild(groupPosition: Int, childPosition: Int): Any {
        return dataList[groupPosition].childName[childPosition]
    }

    override fun getGroupId(groupPosition: Int): Long {
        return groupPosition.toLong()
    }

    override fun getChildId(groupPosition: Int, childPosition: Int): Long {
        return childPosition.toLong()
    }

    override fun hasStableIds(): Boolean {
        return true
    }

    override fun getGroupView(
        groupPosition: Int,
        isExpanded: Boolean,
        convertView: View?,
        parent: ViewGroup?
    ): View {
        val data = dataList[groupPosition].groupName
        val binding = DrlistItemBinding.inflate(LayoutInflater.from(parent?.context),parent,false)
        binding.listName.text = data
        return binding.root
    }

    override fun getChildView(
        groupPosition: Int,
        childPosition: Int,
        isLastChild: Boolean,
        convertView: View?,
        parent: ViewGroup?
    ): View {
        val data = dataList[groupPosition].childName[childPosition]
        val binding = DrlistItemBinding.inflate(LayoutInflater.from(parent?.context),parent,false)
        binding.listName.text = data
        return binding.root
    }

    override fun isChildSelectable(groupPosition: Int, childPosition: Int): Boolean {
        return true
    }
}