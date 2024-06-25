package com.example.fooddeliveryproject.ViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fooddeliveryproject.Models.Address
import com.example.fooddeliveryproject.Models.ApiResponse
import com.example.fooddeliveryproject.Models.District
import com.example.fooddeliveryproject.Models.User
import com.example.fooddeliveryproject.Network.RetrofitInstance
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddressPageViewModel(): ViewModel() {
    val _provinceList=MutableLiveData<ApiResponse>()
    private val db= Firebase.firestore

    val provinceList: LiveData<ApiResponse>
        get() = _provinceList

    private val _address=MutableLiveData<Address>(Address("Adres","Lütfen Adres seçin"))
    val address  :LiveData<Address> get() = _address



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

    fun setAddress(address: Address,callback:(Boolean)->Unit){
        viewModelScope.launch {
            try {
                var auth= FirebaseAuth.getInstance()
                var uuid :String?=auth.uid
                if (uuid!=null){
                    db.collection("Users").document(uuid!!).update("userAddress",address).addOnSuccessListener {
                        callback(true)
                    }.addOnFailureListener{
                        callback(false)
                    }
                }else{
                    callback(false)
                }
            }catch (e:Exception){
                Log.d("hatamUVM",e.toString())
            }
        }
    }
    fun getAddress(){
        viewModelScope.launch {
            try {
                var auth= FirebaseAuth.getInstance()
                var uuid :String?=auth.uid
                if (uuid!=null){
                    db.collection("Users").document(uuid!!).get().addOnSuccessListener {
                        val user =it.toObject(User::class.java)
                        if (user!=null){
                            _address.value=user.userAddress
                        }
                    }
                }
            }catch (e:Exception){
                Log.d("hatamUVM",e.toString())
            }
        }
    }
}