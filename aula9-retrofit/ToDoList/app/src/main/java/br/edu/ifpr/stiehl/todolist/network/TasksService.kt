package br.edu.ifpr.stiehl.todolist.network

import br.edu.ifpr.stiehl.todolist.entities.Task
import retrofit2.Call
import retrofit2.http.*

interface TasksService {
    @Headers("Accept: application/json")
    @GET("tasks")
    fun getAll(): Call<List<Task>>

    @Headers("Accept: application/json")
    @POST("tasks")
    @FormUrlEncoded
    fun createTask(@Field("task[title]") title: String,
                   @Field("task[description]") description: String,
                   @Field("task[done]") done: Boolean): Call<Task>

    @Headers("Accept: application/json")
    @PATCH("tasks/{id}")
    @FormUrlEncoded
    fun updateTask(@Path("id") id: Long,
                   @Field("task[title]") title: String,
                   @Field("task[description]") description: String,
                   @Field("task[done]") done: Boolean): Call<Task>

    @Headers("Accept: application/json")
    @DELETE("tasks/{id}")
    fun deleteTask(@Path("id") id: Long): Call<Unit>
}