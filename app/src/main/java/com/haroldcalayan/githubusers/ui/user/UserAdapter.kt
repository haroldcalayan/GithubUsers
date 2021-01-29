/*
 * Copyright (c) 2021. All rights reserved.
 *
 * Created by Harold Calayan on 1/28/2021
 */

package com.haroldcalayan.githubusers.ui.user

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.haroldcalayan.githubusers.data.model.Note
import com.haroldcalayan.githubusers.data.model.User
import com.haroldcalayan.githubusers.databinding.AdapterUserBinding

class UserAdapter : RecyclerView.Adapter<UserAdapter.UserViewHolder> {

    interface Listener {
        fun onItemClick(user : User)
    }

    private val listener : Listener
    private var users : List<User>
    private var notes : List<Note>

    constructor(listener: Listener, users: List<User>, notes: List<Note>) {
        this.listener = listener
        this.users = users
        this.notes = notes
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = AdapterUserBinding.inflate(inflater)
        return UserViewHolder(binding)
    }

    override fun getItemCount() = users.size

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = users[position]
        val note = notes.find { it.userId == user.id }
        holder.bind(user, note)

        holder.itemView.setOnClickListener {
            listener.onItemClick(user)
        }
    }

    fun updateList(users: List<User>, notes: List<Note>) {
        this.users = users
        this.notes = notes
        notifyDataSetChanged()
    }

    inner class UserViewHolder(private val binding: AdapterUserBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(user: User, note: Note?) {
            binding.user = user
            binding.note = note
            binding.executePendingBindings()
        }
    }
}