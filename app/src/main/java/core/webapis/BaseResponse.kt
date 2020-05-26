package core.webapis

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class BaseResponse<T> where T : Any? {

    @SerializedName("statusCode")
    @Expose
    var statusCode: Int? = 0

    @SerializedName("isSuccess")
    @Expose
    var isSuccess: Boolean = false

    @SerializedName("data")
    @Expose
    var data: T? = null

    @SerializedName("message")
    @Expose
    var message: String? = null

    @SerializedName("exceptionMessage")
    @Expose
    var exceptionMessage: String? = null


    @SerializedName("IsError")
    @Expose
    var isError: Boolean = false
}