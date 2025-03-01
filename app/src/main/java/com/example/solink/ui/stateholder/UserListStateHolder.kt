package com.example.solink.ui.stateholder

data class UserListItemStateHolder(val name:String, val imageURL:String = "", val onClick:(()->Unit)? = null)
data class UserListStateHolder(val users:List<UserListItemStateHolder>)