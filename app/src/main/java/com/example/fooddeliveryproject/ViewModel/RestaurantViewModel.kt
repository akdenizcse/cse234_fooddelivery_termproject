package com.example.fooddeliveryproject.ViewModel

import android.net.Uri
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fooddeliveryproject.Models.Food
import com.example.fooddeliveryproject.Models.OrderedFood
import com.example.fooddeliveryproject.Models.Restaurant
import com.example.fooddeliveryproject.Utils.uploadImage
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class RestaurantViewModel ():ViewModel() {
    private val db=Firebase.firestore
    private val storage=FirebaseStorage.getInstance()
    private val storageReference=storage.reference
    val fauth=FirebaseAuth.getInstance()
    private var uuid :String= fauth.currentUser?.uid ?: ""
    val isLoading= mutableStateOf(false)
    val editedFood: MutableState<Food?> = mutableStateOf(null)

    val _restaurant=MutableLiveData<Restaurant>()
    val restaurant :LiveData<Restaurant> get() = _restaurant

    val _restaurantList=MutableLiveData<ArrayList<Restaurant>>()
    val restaurantList :LiveData<ArrayList<Restaurant>> get() = _restaurantList
    val _restaurantFoodList=MutableLiveData<ArrayList<Food>>()
    val restaurantFoodList :LiveData<ArrayList<Food>> get() = _restaurantFoodList

    val _restaurantOrderList=MutableLiveData<ArrayList<OrderedFood>>()
    val restaurantOrderList :LiveData<ArrayList<OrderedFood>> get() = _restaurantOrderList

    fun getRestaurantInfo(){
        viewModelScope.launch {
            db.collection("Restaurant").document(uuid).get().addOnSuccessListener {
                _restaurant.value=it.toObject(Restaurant::class.java)
            }
        }
    }

    fun addProduct(food: Food,imageUri: Uri,calllback: (Boolean) -> Unit){
     viewModelScope.launch {
         try {
             isLoading.value=true
             delay(1000)
             uploadImage(imageUri,"food",storageReference){
                 if (it=="error"){
                     Log.d("hatamVM","image not upload error")
                     calllback(false)
                 }else{
                     food.imageUrl=it
                     db.collection("Food").document(food.id).set(food)
                         .addOnSuccessListener {
                             Log.d("hatamVM","success")
                             addFoodToRestaurant(food.id){
                                 if (it){
                                     calllback(true)
                                 }else{
                                     calllback(false)
                                 }
                             }
                             isLoading.value=false
                         }.addOnFailureListener(){error->
                             Log.d("hatamVM","false error= "+error.toString())
                             calllback(false)
                             isLoading.value=false
                         }
                 }
             }
         }catch (e:Exception){
             Log.d("hatamAddProduct","error Add poroduct"+e.toString())
         }

     }
    }

    private fun addFoodToRestaurant(foodId: String, callback: (Boolean) -> Unit) {
        try {
            db.collection("Restaurant").document(uuid).get().addOnSuccessListener {
                val foodList = it.toObject(Restaurant::class.java)?.foodList ?: mutableListOf<String>()
                if (!foodList.contains(foodId)) {
                    foodList.add(foodId)
                    db.collection("Restaurant").document(uuid).update("foodList", foodList)
                    callback(true)
                } else {
                    callback(false)
                }
            }
        } catch (e: Exception) {
            Log.e("hatamAddProduct", "Error adding food to restaurant: ${e.message}")
            callback(false)
        }
    }

        fun updateFood(food: Food,calllback: (Boolean) -> Unit) {
        try {
            viewModelScope.launch {
                ///TODO LOOK AT
                db.collection("Food").document(food.id).set(food).addOnSuccessListener {
                    Log.e("hatamAddProduct", "success Add poroduct")
                    calllback(true)
                }.addOnFailureListener() { error ->
                    Log.e("hatamAddProduct", "error Add poroduct" + error.toString())
                    calllback(false)
                }
            }
        } catch (e: Exception) {
            Log.d("hatamAddProduct", "error Add poroduct" + e.toString())

        }
    }

    fun getRestaurantList(){
        try {
            db.collection("Restaurant").get().addOnSuccessListener {
                _restaurantList.value=it.toObjects(Restaurant::class.java) as ArrayList<Restaurant>
            }
        }catch (e:Exception){

        }
    }
    fun getRestaurantFoodList() {
        viewModelScope.launch {
            try {
                db.collection("Restaurant").document(uuid).get().addOnSuccessListener { rest ->
                    val foodIDList = rest?.get("foodList") as? List<String?>
                    if (!foodIDList.isNullOrEmpty()) {
                        Log.d("hatamRVM", "foodIDList size = ${foodIDList.size}")
                        foodIDList.forEach { fd ->
                            fd?.let {
                                db.collection("Food").document(it).get().addOnSuccessListener { document ->
                                    val food = document.toObject(Food::class.java)
                                    food?.let { foodItem ->
                                        val currentList = _restaurantFoodList.value ?: arrayListOf()
                                        if (!currentList.contains(foodItem)){
                                            currentList.add(foodItem)
                                            _restaurantFoodList.value=currentList
                                        }
                                        Log.d("hatamRVM", "food = ${foodItem.name}")
                                    }
                                }
                            }
                        }
                    } else {
                        Log.d("hatamRVM", "foodIDList is null or empty")
                    }
                }
            } catch (e: Exception) {
                Log.e("hatamRVM", "Error fetching restaurant food list", e)
            }
        }
    }
    fun getRestaurantOrderList(){
        try {
            db.collection("Restaurant").document(uuid).get().addOnSuccessListener {
                _restaurantOrderList.value= it.toObject(Restaurant::class.java)?.soldFood
            }.addOnFailureListener{
                Log.e("hatamRVM", "Error fetching restaurant order list", it)
            }
        }catch (e:Exception){

        }
    }

}