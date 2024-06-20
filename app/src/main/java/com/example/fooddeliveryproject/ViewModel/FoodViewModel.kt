package com.example.fooddeliveryproject.ViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fooddeliveryproject.Models.Food
import com.example.fooddeliveryproject.Models.Restaurant
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch

class FoodViewModel():ViewModel() {
    private val db= Firebase.firestore

    private val _foodList=MutableLiveData<ArrayList<Food>>()
     val foodList : LiveData<ArrayList<Food>> get() = _foodList

    private val _restaurantFoodList=MutableLiveData<ArrayList<Food>>()
    val restaurantFoodList : LiveData<ArrayList<Food>> get() = _restaurantFoodList
    fun getFoodList(){
        viewModelScope.launch {
            db.collection("Food").get().addOnSuccessListener {
                _foodList.value=it.toObjects(Food::class.java) as ArrayList<Food>
            }.addOnSuccessListener {

            }.addOnFailureListener{

            }
        }
    }


    fun searchFood(query: String) {
        val foodList = mutableListOf<Food>()
        // Search in name
        val nameQuery = db.collection("Food")
            .whereGreaterThanOrEqualTo("name", query)
            .whereLessThanOrEqualTo("name", query + "\uf8ff")

        // Search in description
        val descriptionQuery = db.collection("Food")
            .whereGreaterThanOrEqualTo("description", query)
            .whereLessThanOrEqualTo("description", query + "\uf8ff")

        // Search in category
        val categoryQuery = db.collection("Food")
            .whereGreaterThanOrEqualTo("category", query)
            .whereLessThanOrEqualTo("category", query + "\uf8ff")

        // Execute all queries
        nameQuery.get().addOnSuccessListener { nameDocuments ->
            foodList.addAll(nameDocuments.toObjects(Food::class.java))
            descriptionQuery.get().addOnSuccessListener { descriptionDocuments ->
                foodList.addAll(descriptionDocuments.toObjects(Food::class.java))
                categoryQuery.get().addOnSuccessListener { categoryDocuments ->
                    foodList.addAll(categoryDocuments.toObjects(Food::class.java))

                    // Remove duplicates (if any)
                    val uniqueFoodList = foodList.distinctBy { it.id }
                    _foodList.value = ArrayList(uniqueFoodList)
                }.addOnFailureListener { exception ->
                    Log.w("Firestore", "Error getting category documents: ", exception)
                }
            }.addOnFailureListener { exception ->
                Log.w("Firestore", "Error getting description documents: ", exception)
            }
        }.addOnFailureListener { exception ->
            Log.w("Firestore", "Error getting name documents: ", exception)
        }
    }

    fun getRandomFoodItems(count: Int) {
        val foodCollection = db.collection("Food")
        var isShuffled = false

        foodCollection.get().addOnSuccessListener { result ->
            val foodItems = result.documents.mapNotNull { it.toObject(Food::class.java) }
            if (_foodList!=null){
                if (!isShuffled) {
                    val randomItems = ArrayList(foodItems.take(count))
                    isShuffled = true
                    _foodList.value = randomItems
                }
            }

        }.addOnFailureListener { exception ->
        }
    }

    fun getRestaurantFood(uuid:String="EaGAzHb4mucww7t5WtwLaLRMSRX2") {
        viewModelScope.launch {
            try {
                db.collection("Restaurant").document(uuid).get().addOnSuccessListener { rest ->
                    val foodIDList = rest?.get("foodList") as? List<String?>
                    if (!foodIDList.isNullOrEmpty()) {
                        Log.d("hatmagetRVM2", "XfoodIDList size = ${foodIDList.size}")
                        foodIDList.forEach { fd ->
                            fd?.let {foodId->
                                Log.d("hatamgetRVM2", "XfoodId = ${_foodList.value?.size}")
                                    val fdLis= _restaurantFoodList.value?.filter {
                                        it.id==foodId
                                    }
                                Log.d("hatamgetRVM2", "YXfoodId = ${fdLis?.size}")

                                db.collection("Food").document(foodId).get().addOnSuccessListener { document ->
                                    val food = document.toObject(Food::class.java)
                                    Log.d("hatamgetRVM2", "Xfood = ${food?.name}")
                                    food?.let { foodItem ->
                                        val currentList = _restaurantFoodList.value ?: arrayListOf()
                                        if (!currentList.contains(foodItem)){
                                            currentList.add(foodItem)
                                            _restaurantFoodList.value=currentList
                                        }
                                        Log.d("hatamgetRVM2", "food = ${foodItem.name}")
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
/*
    db.collection("Food").document(restaurantFood).get().addOnSuccessListener {
        val food = it.toObject(Food::class.java)
        _foodList.value?.add(food!!)
        Log.d("hatamgetRe","food = ${food}")
    }

 */

}