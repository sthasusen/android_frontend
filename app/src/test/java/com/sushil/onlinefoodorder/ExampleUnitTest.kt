package com.sushil.onlinefoodorder

import UserRepo
import com.kiran.student.api.ServiceBuilder
import com.sushil.onlinefoodorder.Class.Food
import com.sushil.onlinefoodorder.Class.books
import com.xrest.finalassignment.FoodRepo
import com.xrest.finalassignment.Models.User
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class UnitTest {
    val repo = UserRepo()
    val repos = FoodRepo()



    @Test
    fun logins() = runBlocking {
        var response = repo.Login("sonam","sonam")
        var exp =true
        Assert.assertEquals(exp,response.status)

    }

    @Test
    fun login() = runBlocking {
        var response = repo.Login("123","123")
        var exp =true
        Assert.assertEquals(exp,response.status)

    }
    @Test
    fun register() = runBlocking {
        var response = repo.register(User(fname="Asd",lname = "Asd",username = "lll",password = "lll",address = "jjjj"))
        var exp =false
        Assert.assertEquals(exp,response.status)

    }
    @Test
    fun registers() = runBlocking {
        var response = repo.register(User(fname="Asd",lname = "Asd",username = "lll",password = "lll",address = "jjjj"))
        var exp =true
        Assert.assertEquals(exp,response.status)

    }
    @Test
    fun addP() = runBlocking {
        val repos = FoodRepo()
        var response = repos.insertFood(Food(Name = "Asdasd",Price = "555",Description = "ASdasd",Rating = "5"))
        var exp =true
        Assert.assertEquals(exp,response.success)

    }
    @Test
    fun addfood() = runBlocking {
        val repos = FoodRepo()
        var response = repos.insertFood(Food(Name = "Asdasd",Price = "555",Description = "ASdasd",Rating = "5"))
        var exp =false
        Assert.assertEquals(exp,response.success)

    }
    @Test
    fun book() = runBlocking {

        ServiceBuilder.token="Bearer "+repo.Login("sonam","sonam").token
        var response = repos.addToCart("60796426c883235170921dd8", books(5))
        var exp =true
        Assert.assertEquals(exp,response.success)

    }
    @Test
    fun books() = runBlocking {

        ServiceBuilder.token=repo.Login("123","123").token
        var response = repos.addToCart("60796426c883235170921dd8", books(5))
        var exp =true
        Assert.assertEquals(exp,response.success)

    }
    @Test
    fun delete() = runBlocking {

        ServiceBuilder.token="Bearer "+repo.Login("sonam","sonam").token
        var response = repos.delete("607b0da71bcd50573cbbe882")
        var exp =false
        Assert.assertEquals(exp,response.success)

    }
    @Test
    fun deletes() = runBlocking {

        ServiceBuilder.token=repo.Login("sonam","sonam").token
        var response = repos.delete("607b0da71bcd50573cbbe882")
        var exp =true
        Assert.assertEquals(exp,response.success)

    }


}