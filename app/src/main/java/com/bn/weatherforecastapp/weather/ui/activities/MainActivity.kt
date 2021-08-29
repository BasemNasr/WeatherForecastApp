package com.bn.weatherforecastapp.weather.ui.activities

import android.app.Activity
import android.app.ProgressDialog
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bn.kotlinproject.WeathesAdapter
import com.bn.weatherforecastapp.R
import com.bn.weatherforecastapp.utils.App
import com.bn.weatherforecastapp.utils.Constants
import com.bn.weatherforecastapp.weather.data.ViewModel.WeatherViewModel
import com.bn.weatherforecastapp.weather.data.entities.WeatherEntity
import com.bn.weatherforecastapp.weather.data.models.City
import com.bn.weatherforecastapp.weather.data.models.WeatherData
import com.bn.weatherforecastapp.weather.events.ShowDialogEvent
import com.bn.weatherforecastapp.weather.events.ShowRetryBtnEvent
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import kotlin.reflect.KClass


class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var toolbar: Toolbar
    private lateinit var toolbarTitle: TextView
    private lateinit var searchIv: ImageView
    private lateinit var searchLayout: RelativeLayout
    private lateinit var noitemsLayout: LinearLayout
    private lateinit var searchET: EditText
    private lateinit var subbmitSearchIV: ImageView
    private lateinit var weatherViewModel: WeatherViewModel

    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var recyclerView: RecyclerView
    private lateinit var weathesAdapter: WeathesAdapter

    var GoneAnimation: Animation? = null
    var VisibleAnimation:Animation? = null

    lateinit var count: LiveData<WeatherEntity>
    lateinit var datas: List<WeatherData>
    lateinit var cityInfo: City
    lateinit  var progressDialog:ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
        weatherViewModel= ViewModelProviders.of(this).get(WeatherViewModel::class.java)


        if (App().getLastCityName().toString().isEmpty()){
            //showing NoData Layout
            noitemsLayout.visibility = View.VISIBLE;
            searchET.setText("");
            searchLayout.visibility = View.VISIBLE;
            toolbarTitle.visibility = View.GONE;
            searchIv.visibility = View.GONE;
            searchLayout.startAnimation(VisibleAnimation);
            toolbarTitle.startAnimation(GoneAnimation);
            searchIv.startAnimation(GoneAnimation);
        }else{
            toolbarTitle.text = App().getLastCityName()
        }

        count= weatherViewModel.getWeatherEnitityByCityName(App().getLastCityName().toString())
        count.observe(this, Observer {
            if (it!=null){
                datas = it.weathers
                cityInfo = it.cityInfo
                if(datas.size>0){
                    noitemsLayout.visibility = View.GONE
                    weathesAdapter = WeathesAdapter(applicationContext,datas,cityInfo)
                    recyclerView.adapter = weathesAdapter
                    weathesAdapter.notifyDataSetChanged()
                }
            }

        })


    }

    fun initViews(){
        toolbar=findViewById(R.id.app_bar)
        toolbarTitle=findViewById(R.id.toolbar_title)
        searchIv=findViewById(R.id.search_iv)
        searchLayout=findViewById(R.id.search_layout)
        noitemsLayout=findViewById(R.id.noitems_layout)
        searchET=findViewById(R.id.SearcheditText)
        subbmitSearchIV=findViewById(R.id.iv_subbmit_search)
        recyclerView =findViewById(R.id.rec_view)

        searchIv.setOnClickListener(this)
        subbmitSearchIV.setOnClickListener(this)

        GoneAnimation = AnimationUtils.loadAnimation(this, R.anim.slide_right);
        VisibleAnimation = AnimationUtils.loadAnimation(this, R.anim.slide_left);

        linearLayoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = linearLayoutManager


        progressDialog = ProgressDialog(this)
        progressDialog.setTitle("connecting")
        progressDialog.setMessage("Fetching data, please wait...")


    }


    override fun onClick(v: View?) {
        if(v==searchIv){
            searchET.setText("");
            searchLayout.visibility = View.VISIBLE;
            toolbarTitle.visibility = View.GONE;
            searchIv.visibility = View.GONE;
            searchLayout.startAnimation(VisibleAnimation);
            toolbarTitle.startAnimation(GoneAnimation);
            searchIv.startAnimation(GoneAnimation);

        }else if (v == subbmitSearchIV){
            var cityName = searchET.text.toString();

            if (cityName.isEmpty()){
                Toast.makeText(this,""+R.string.pleasefillfield,Toast.LENGTH_LONG);
            }else{
                searchLayout.visibility = View.GONE;
                toolbarTitle.visibility = View.VISIBLE;
                searchIv.visibility = View.VISIBLE;

                searchLayout.startAnimation(GoneAnimation);
                toolbarTitle.startAnimation(VisibleAnimation);
                searchIv.startAnimation(VisibleAnimation);

                count = weatherViewModel.getWeatherEnitityByCityName(cityName)
                count.observe(this, Observer {
                    if (it!=null){
                        datas = it.weathers
                        cityInfo = it.cityInfo
                        if(datas.size>0){
                            noitemsLayout.visibility = View.GONE
                            weathesAdapter = WeathesAdapter(applicationContext,datas,cityInfo)
                            recyclerView.adapter = weathesAdapter
                            weathesAdapter.notifyDataSetChanged()
                        }

                    }

                })


            }



        }


    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onErrorGettingNewData(event: ShowRetryBtnEvent?) {
//        btnFinishService.startMorphRevertAnimation()
        if (event!!.type.toString().equals(Constants.ERROR_INTERNET_TYPE)){
            Toast.makeText(this,""+getString(R.string.check_internet),Toast.LENGTH_LONG).show();
        }else if(event!!.type.toString().equals(Constants.NOT_FOUND_CITY_TYPE)){
            Toast.makeText(this,""+getString(R.string.notfoundcity),Toast.LENGTH_LONG).show();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun DialogStatuesComming(event: ShowDialogEvent?) {
//        btnFinishService.startMorphRevertAnimation()
        if (event!!.type.toString().equals(Constants.OPEN_DIALOG_TYPE)){
            progressDialog.show()
        }else if(event!!.type.toString().equals(Constants.DISMISS_DIALOG_TYPE)){
            progressDialog.dismiss()
        }
    }

    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }

    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }


}