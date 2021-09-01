package com.appify.gomoneyappchallenge.repository

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.android.volley.DefaultRetryPolicy
import com.android.volley.Response
import com.android.volley.RetryPolicy
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.appify.gomoneyappchallenge.model.Competition
import com.appify.gomoneyappchallenge.model.Match
import com.appify.gomoneyappchallenge.utils.Converters
import org.json.JSONException

class CompetitionRepository(application: Application) : AndroidViewModel(application) {
    val competitionLiveData: MutableLiveData<List<Competition>> = MutableLiveData()
    val requestQueue = Volley.newRequestQueue(application)

    fun getCompetitions() {
        val url = "https://api.football-data.org/v2/competitions?plan=TIER_ONE"
        val request = object: StringRequest(Method.GET, url, Response.Listener {
                response-> try {
            Log.d("volley", "Response: "+response)
            val competition = Converters.getCompetitions(response)
            competitionLiveData.postValue(competition)
            Log.d("volley", competition.size.toString()+" items found")
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
        request.setRetryPolicy(DefaultRetryPolicy(10000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
            DefaultRetryPolicy.DEFAULT_BACKOFF_MULT))

        requestQueue.add(request)
    }

}