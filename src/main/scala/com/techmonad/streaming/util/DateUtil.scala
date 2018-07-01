package com.techmonad.streaming.util

import java.text.SimpleDateFormat
import java.util.Date

import scala.util.control.NonFatal


object DateUtil {

  private val dateFormats: Seq[String] = Seq(
    "MM-dd-yyyy H:mm:ss",
    "MM-dd-yyyy HH:mm:ss",
    "MM-dd-yyyy",
    "MM/dd/yyyy H:mm:ss",
    "MM/dd/yyyy HH:mm:ss",
    "MMM dd, yyyy HH:mm a",
    "MMM dd, yyyy, HH:mm a",
    "MMM-dd-yyyy H:mm:ss",
    "MMM-dd-yyyy HH:mm:ss",
    "MMM-dd-yyyy",
    "MMM/dd/yyyy H:mm:ss",
    "MMM/dd/yyyy HH:mm:ss",
    "MMM/dd/yyyy",
    "MM/dd/yy",
    "dd-MM-yyyy H:mm:ss",
    "dd-MM-yyyy HH:mm:ss",
    "dd-MM-yyyy",
    "dd-MMM-yyyy H:mm:ss",
    "dd-MMM-yyyy HH:mm:ss",
    "dd-MMM-yyyy",
    "dd/MM/yyyy H:mm:ss",
    "dd/MM/yyyy HH:mm:ss",
    "dd/MM/yy",
    "yyyy-MM-dd HH:mm:ss",
    "yyyy-MM-dd'T'HH:mm:ss",
    "yyyy-MM-dd"
  )


  private val esDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ")

  def getESDateFormat(dateString: String): String = {
    def getDate(dateFormats: Seq[String], dateString: String): String =
      try {
        val dateFormat = new SimpleDateFormat(dateFormats.head)
        val date = dateFormat.parse(dateString)
        esDateFormat.format(date)
      } catch {
        case _ if (dateFormats.size > 1) => getDate(dateFormats.tail, dateString)
        case NonFatal(_) => esDateFormat.format(new Date())
      }

    getDate(dateFormats, dateString)
  }

}

