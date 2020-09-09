package com.stiehl.testfcm.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.stiehl.testfcm.R
import com.stiehl.testfcm.api.results.UsersResult
import com.stiehl.testfcm.model.User
import com.stiehl.testfcm.util.FirebaseServiceGenerator
import kotlinx.android.synthetic.main.item_user.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserAdapter() :
    RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    private var users = mutableListOf<User>()
    var selectedUsers = mutableListOf<User>()
    private val service = FirebaseServiceGenerator.getService()

    init {
        service.getUsers().enqueue(object : Callback<UsersResult> {
            override fun onFailure(call: Call<UsersResult>, t: Throwable) {}

            override fun onResponse(call: Call<UsersResult>, response: Response<UsersResult>) {
                val result = response.body()!!
                if (result.status == "success") {
                    users = result.data.users.toMutableList()
                    notifyDataSetChanged()
                }
            }
        })
    }

    override fun getItemViewType(position: Int): Int {
        val user = users[position]
        return if (selectedUsers.contains(user)) {
            R.layout.item_user_selected
        } else {
            R.layout.item_user
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(viewType, parent, false)
        )

    override fun getItemCount() = users.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = users[position]
        holder.fillView(user)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun fillView(user: User) {
            itemView.lbUserName.text = user.name
            itemView.setOnClickListener {
                val position = users.indexOf(user)
                if (selectedUsers.contains(user)) {
                    selectedUsers.remove(user)
                    notifyItemChanged(position)
                } else {
                    selectedUsers.add(user)
                    notifyItemChanged(position)
                }
            }
        }
    }
}