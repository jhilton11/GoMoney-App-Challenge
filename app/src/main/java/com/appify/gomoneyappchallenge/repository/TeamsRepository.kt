package com.appify.gomoneyappchallenge.repository

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.android.volley.DefaultRetryPolicy
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.appify.gomoneyappchallenge.model.Match
import com.appify.gomoneyappchallenge.model.Team
import com.appify.gomoneyappchallenge.utils.Converters
import org.json.JSONException

class TeamsRepository(application: Application) {
    val teamLiveData: MutableLiveData<List<Team>> = MutableLiveData()
    val requestQueue = Volley.newRequestQueue(application)

    fun getTeams(id: String) {
        val url = "https://api.football-data.org/v2/competitions/"+id+"/teams"
        val request = object: StringRequest(Method.GET, url, Response.Listener {
                response-> try {
            Log.d("volley", "Response: "+response)
            val teams = Converters.getTeams(response)
            teamLiveData.postValue(teams)
            Log.d("volley", teams.size.toString()+" items found")
        } catch (e: JSONException) {
            Log.d("volley", e.localizedMessage)
        }
        }, Response.ErrorListener {
                error -> Log.d("volley", "Error: "+error.localizedMessage)
        }) {
            override fun getHeaders(): MutableMap<String, String> {
                val headers = HashMap<String, String>()
                headers["X-Auth-Token"] = "a199ae5a0aa84b3eb426eb02b0d8e71a"
                return headers
            }
        }

        request.setRetryPolicy(
            DefaultRetryPolicy(10000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
            DefaultRetryPolicy.DEFAULT_BACKOFF_MULT)
        )

        requestQueue.add(request)
    }

}