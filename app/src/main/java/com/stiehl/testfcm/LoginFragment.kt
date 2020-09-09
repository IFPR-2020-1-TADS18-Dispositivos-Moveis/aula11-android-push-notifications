package com.stiehl.testfcm

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.messaging.FirebaseMessaging
import com.stiehl.testfcm.api.inputs.RegisterInput
import com.stiehl.testfcm.api.results.RegisterResult
import com.stiehl.testfcm.api.results.UsersResult
import com.stiehl.testfcm.util.FirebaseServiceGenerator
import com.stiehl.testfcm.util.LocalPersistence
import kotlinx.android.synthetic.main.fragment_login.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_login, container, false)

        view.btLogin.setOnClickListener {
            configureFirebase()
        }

        return view
    }

    private fun configureFirebase() {
        FirebaseInstanceId.getInstance().instanceId.addOnCompleteListener { task ->
            if (!task.isSuccessful) {
                Toast.makeText(activity, "Firebase error", Toast.LENGTH_SHORT).show()
                Log.w("GET_MESSAGE", "getInstanceId failed", task.exception)
                return@addOnCompleteListener
            }

            val token = task.result?.token
            val name = view?.txtName?.text.toString()

            val service = FirebaseServiceGenerator.getService()
            service.register(RegisterInput(name, token!!))
                .enqueue(object : Callback<RegisterResult> {
                    override fun onFailure(call: Call<RegisterResult>, t: Throwable) {}

                    override fun onResponse(
                        call: Call<RegisterResult>,
                        response: Response<RegisterResult>
                    ) {
                        val result = response.body()!!
                        if (result.status == "success") {
                            LocalPersistence(requireActivity()).userId = result.data.id
                            FirebaseMessaging.getInstance().subscribeToTopic("global_messages")
                                .addOnCompleteListener {
                                    findNavController().navigate(R.id.navigateToMessage)
                                }
                        }
                    }
                })
        }
    }


}