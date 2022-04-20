package com.example.omapp

const val BASE_URL = "https://smarttv.orangetv.orange.es/stv/api/rtv/v1/"
const val MOVIE_COUNT = "5"
//const val GET_MOVIES_PATH = "GetUnifiedList?client=json&statuses=published&definitions=SD;HD;4K&external_category_id=SED_3880&filter_empty_categories=true&count=$MOVIE_COUNT"
const val GET_MOVIES_PATH = "GetUnifiedList?client=json&external_category_id=U7D_14039&statuses=published&definitions=SD;HD&order=asc&order_by=tree&from=0&count=$MOVIE_COUNT"
const val IMAGES_BASE_PATH = "https://smarttv.orangetv.orange.es/stv/api/rtv/v1/images"

// DATABASE
const val DATABASE_VERSION = 1
const val DATABASE_NAME = "interbus_database"

const val EMPTY_STRING = ""