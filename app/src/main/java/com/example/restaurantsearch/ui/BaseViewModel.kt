package com.example.restaurantsearch.ui

import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.restaurantsearch.MyApplication
import com.example.restaurantsearch.db.AppRepository
import com.example.restaurantsearch.models.SearchResult
import com.example.restaurantsearch.models.menu_model.Category
import com.example.restaurantsearch.models.menu_model.Menu
import com.example.restaurantsearch.models.menu_model.MenuItems
import com.example.restaurantsearch.models.restaurant_model.Latlng
import com.example.restaurantsearch.models.restaurant_model.OperatingHours
import com.example.restaurantsearch.models.restaurant_model.Restaurant
import com.example.restaurantsearch.models.restaurant_model.Review
import kotlinx.coroutines.launch
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.util.ArrayList

open class BaseViewModel(
    private val appRepository: AppRepository,
    private val application: MyApplication
) :
    ViewModel() {
    var itemClickedEvent: MutableLiveData<Boolean> = MutableLiveData<Boolean>()
    var restaurantLiveData: MutableLiveData<List<Restaurant>> = MutableLiveData<List<Restaurant>>()
    var searchLiveData: MutableLiveData<List<SearchResult>> = MutableLiveData<List<SearchResult>>()

    fun saveRestaurantsList() {
        val list = loadRestaurantJSONFromAsset()
        viewModelScope.launch {
            appRepository.saveRestaurantsDb(list!!)
            val result = appRepository.getResturants("", false)
            Log.d("ResultSize", "aaa${result.size}")

        }

    }

    fun saveMenuList() {
        val list = loadMenuJSONFromAsset()
        viewModelScope.launch {
            appRepository.saveMenuDb(list!!)
            itemClickedEvent.value = true
        }
    }

    private fun loadRestaurantJSONFromAsset(): ArrayList<Restaurant>? {
        val restaurantList: ArrayList<Restaurant> = ArrayList<Restaurant>()
        val json: String? = try {
            val inputStream = application.assets.open("restaurant_list")
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            String(buffer, charset("UTF-8"))
        } catch (ex: IOException) {
            //            ex.printStackTrace();
            return null
        }
        try {
            val obj = JSONObject(json)
            val restaurantsList = obj.getJSONArray("restaurants")
            for (i in 0 until restaurantsList.length()) {
                val restaurantObj = restaurantsList.getJSONObject(i)
                val id = restaurantObj.getInt("id")
                val restaurantName = restaurantObj.getString("name")
                val cuisineType = restaurantObj.getString("cuisine_type")
                val reviewsList = restaurantObj.getJSONArray("reviews")
                val reviewList: ArrayList<Review> = ArrayList<Review>()

                for (j in 0 until reviewsList.length()) {
                    val reviewObj = reviewsList.getJSONObject(j)
                    val name = reviewObj.getString("name")
                    val date = reviewObj.getString("date")
                    val rating = reviewObj.getDouble("rating")
                    val comments = reviewObj.getString("comments")
                    val review =
                        Review(name = name, date = date, rating = rating, comments = comments)
                    reviewList.add(review)
                }
                val restaurant = Restaurant(
                    address = "",
                    cuisineType = cuisineType,
                    id = id,
                    latlng = Latlng(0.0, 0.0),
                    name = restaurantName,
                    neighborhood = "",
                    operatingHours = OperatingHours("", "", "", "", "", "", ""),
                    photograph = "",
                    reviews = reviewList
                )
                restaurantList.add(restaurant)


            }
        } catch (e: JSONException) {
            //e.printStackTrace();
        }
        return restaurantList
    }

    private fun loadMenuJSONFromAsset(): ArrayList<Menu>? {
        val menuArrayList: ArrayList<Menu> = ArrayList<Menu>()
        val json: String? = try {
            val inputStream = application.assets.open("menu_list")
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            String(buffer, charset("UTF-8"))
        } catch (ex: IOException) {
            //            ex.printStackTrace();
            return null
        }
        try {
            val obj = JSONObject(json)
            val menuList = obj.getJSONArray("menus")
            for (i in 0 until menuList.length()) {
                var restaurantId: Int;
                val categoryList: ArrayList<Category> = ArrayList<Category>()
                val menuObj = menuList.getJSONObject(i)
                restaurantId = menuObj.getInt("restaurantId")

                val categoriesList = menuObj.getJSONArray("categories")

                for (j in 0 until categoriesList.length()) {
                    val categoryObj = categoriesList.getJSONObject(j)
                    val categoryName = categoryObj.getString("name")
                    val categoryId = categoryObj.getString("id")

                    val menuList = categoryObj.getJSONArray("menu-items")
                    val menuItemList: ArrayList<MenuItems> = ArrayList<MenuItems>()


                    for (k in 0 until menuList.length()) {
                        val menuObj = menuList.getJSONObject(k)
                        val menuId = menuObj.getString("id")
                        val menuName = menuObj.getString("name")
                        val price = menuObj.getString("price")
                        val description = menuObj.getString("description")


                        val menuItem =
                            MenuItems(
                                name = menuName,
                                description = description,
                                id = menuId,
                                images = ArrayList<Any>(), price = price

                            )
                        menuItemList.add(menuItem)
                    }
                    categoryList.add(
                        Category(
                            id = categoryId,
                            menuItems = menuItemList,
                            name = categoryName
                        )
                    )

                }

                val menu = Menu(restaurantId = restaurantId, categories = categoryList, menuId = i);
                menuArrayList.add(menu)

            }
        } catch (e: JSONException) {
        }
        return menuArrayList
    }


    fun saveDataInDatabase(s: String) {
        viewModelScope.launch {
            val restaurantList = appRepository.getResturantsBySearch(s)
            val list = ArrayList<SearchResult>()
            for (restaurant in restaurantList) {
                val searchResult = SearchResult(
                    restaurantId = restaurant.id,
                    restaurantName = restaurant.name,
                    menuId = -1,
                    menuName = "",
                    isRestaurant = true
                )
                list.add(searchResult)
            }

            searchLiveData.postValue(list)

        }
    }

    fun saveRestaurantDataInDatabase() {
        viewModelScope.launch {
            val result = appRepository.getResturants("", false)
            restaurantLiveData.postValue(result)
        }
    }

    var menuLiveData: MutableLiveData<List<Menu>> = MutableLiveData<List<Menu>>()


    fun getRestaurantMenu(restaurantId: Int) {
        viewModelScope.launch {
            val result = appRepository.getMenByRestaurantIdu(restaurantId, false)
            menuLiveData.postValue(result)
        }
    }

}

