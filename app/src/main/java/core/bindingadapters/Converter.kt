package core.bindingadapters

object Converter {
    @JvmStatic
    fun toInt(any: Any): Int {
        return any as Int
    }

    @JvmStatic
    fun toString(any: Any): String {
        return any as String
    }

}