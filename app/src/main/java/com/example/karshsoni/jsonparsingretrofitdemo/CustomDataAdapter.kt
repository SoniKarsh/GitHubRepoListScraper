package com.example.karshsoni.jsonparsingretrofitdemo

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class CustomDataAdapter(var repos: List<GitHubRepo>, var context: Context): RecyclerView.Adapter<ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.custom_list_view, parent, false))
    }

    override fun getItemCount(): Int {
        return repos.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.repoName.text = repos[holder.adapterPosition].name
    }
}
class ViewHolder(view: View): RecyclerView.ViewHolder(view){
    var repoName = view.findViewById<TextView>(R.id.textView)
}