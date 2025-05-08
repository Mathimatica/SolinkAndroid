package com.example.solink.ui.stateholder

data class UserListItemStateHolder(val name:String, val smallURL:String = "", val originalURL:String = "")
data class UserListStateHolder(val users:List<UserListItemStateHolder>)