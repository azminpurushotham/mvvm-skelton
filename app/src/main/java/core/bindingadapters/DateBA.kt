package core.bindingadapters

import android.text.TextUtils
import android.widget.TextView
import androidx.databinding.BindingAdapter
import java.util.*
import core.utility.DateTimeUtils

object DateBA {
    @JvmStatic
    @BindingAdapter("android:date")
    fun TextView.setDate(date: String?) {
        if (date != null) {
            setText(date)
        }
    }

    @JvmStatic
    @BindingAdapter(value = ["android:start_date", "android:end_date"], requireAll = true)
    fun TextView.setRemainDate(start_date: String?, end_date: String?) {
        if (!TextUtils.isEmpty(start_date) && !TextUtils.isEmpty(end_date)) {
            var temp1 = start_date?.replace("T", " ")
            var temp2 = end_date?.replace("T", " ")
            var start_date = DateTimeUtils.getDateTime(temp1, "MMM dd,yyyy", "yyyy-MM-dd hh:mm:ss")
            var d1: Date = DateTimeUtils.getDate(temp1, "yyyy-MM-dd hh:mm:ss")
            var d2: Date = DateTimeUtils.getDate(temp2, "yyyy-MM-dd hh:mm:ss")
            var duration = DateTimeUtils.getTimeDifferenceRemains(context, d1.time, d2.time)
            var value = ""
            if (!TextUtils.isEmpty(duration)) {
                value = start_date + " | " + duration
            } else {
                value = start_date + " | overdue "
            }
            if (value != null) {
                setText(value)
            }
        }
    }

    @JvmStatic
    @BindingAdapter(
        value = ["android:start_date", "android:end_date", "android:is_meeting_date"],
        requireAll = true
    )
    fun TextView.setMeetingDate(start_date: String?, end_date: String?, is_meeting_date: Boolean?) {
        if (start_date != null && end_date != null && is_meeting_date != null) {
            var temp1 = start_date?.replace("T", " ")
            var temp2 = end_date?.replace("T", " ")
            var start_date = DateTimeUtils.getDateTime(temp1, "MMM dd,yyyy", "yyyy-MM-dd hh:mm:ss")
            var end_date = DateTimeUtils.getDateTime(temp2, "MMM dd,yyyy", "yyyy-MM-dd hh:mm:ss")
            var value = ""
            if (is_meeting_date!!) {
                value = start_date + " | " + end_date
            }
            if (value != null) {
                setText(value)
            }
        }
    }

    @JvmStatic
    @BindingAdapter("android:work_date")
    fun TextView.setWorkReportDate(date: String?) {
        if (date != null) {
            if (date?.contains("T")) {
                var temp = date.replace("T", " ")
                temp = DateTimeUtils.getDateTime(temp, "hh:mm: a", "yyyy-MM-dd hh:mm:ss")
                setText(temp)
            } else {
                setText(date)
            }
        }
    }
}