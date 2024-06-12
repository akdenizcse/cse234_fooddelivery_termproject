package com.example.fooddeliveryproject.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fooddeliveryproject.Models.ApiResponse
import com.example.fooddeliveryproject.Models.District
import com.example.fooddeliveryproject.Network.RetrofitInstance
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddressPageViewModel(): ViewModel() {
    val _provinceList=MutableLiveData<ApiResponse>()
    val provinceList: LiveData<ApiResponse>
        get() = _provinceList

    var addressTitle=""
    var addressDesc=""
    fun getProvinceList(){
        viewModelScope.launch {
            RetrofitInstance.api.getPorvinceList().enqueue(object :Callback<ApiResponse>{
                override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                    if (response.isSuccessful){
                        _provinceList.value=response.body()
                    }
                }

                override fun onFailure(call: Call<ApiResponse>, t: Throwable) {

                }

            })
        }

    }
    val _districtList=MutableLiveData<ArrayList<District>>()
    val districList: LiveData<ArrayList<District>>
        get() = _districtList

    fun getDistrictList(prvince:String){
        viewModelScope.launch {
            val data=_provinceList.value?.data?.filter { it.name==prvince }
            if (data!=null && data.isNotEmpty()){
                if (data[0].districts!=null){
                    _districtList.value=data[0].districts
                }
            }
//            _districtList.value=data?.get(0)?.district
        }
    }
}