package com.example.omapp

const val BASE_URL = "https://smarttv.orangetv.orange.es/stv/api/rtv/v1/"
const val MOVIE_COUNT = "5"
const val GET_MOVIES_PATH = "GetUnifiedList?client=json&statuses=published&definitions=SD;HD;4K&external_category_id=SED_3880&filter_empty_categories=true&count=$MOVIE_COUNT"

// DATABASE
const val DATABASE_VERSION = 1
const val DATABASE_NAME = "interbus_database"
const val MASTER_VERSION = "1.0"