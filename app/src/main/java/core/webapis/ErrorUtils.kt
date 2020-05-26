package core.webapis
import okhttp3.ResponseBody
import retrofit2.Converter

class ErrorUtils {


    fun parseError(responseErrorBody: ResponseBody?, code: Int): ResourceError {
        var converter: Converter<ResponseBody, ResourceError> =
            ApiService.retrofit!!.responseBodyConverter(
                ResourceError::class.java,
                arrayOf<Annotation>()
            )
        var error = ResourceError()
        try {
            if (responseErrorBody != null) {
                error = converter.convert(responseErrorBody)!!
            } else {
                error.error = Error(code, "Something went wrong")
            }
        } catch (e: Exception) {
            e.printStackTrace()
            error.error = Error(code, "Something went wrong")
        }
        return error
    }

    fun parseTokenError(responseErrorBody: ResponseBody?, code: Int): ResourceError {
        var error = ResourceError()
        error.error = Error(code, "Invalid Token")
        return error
    }
}