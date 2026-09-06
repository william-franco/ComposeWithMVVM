package br.com.williamfranco.composewithmvvm.src.routes

object Routes {
    const val USERS = "users"
    const val USER_DETAIL = "users-detail/{userId}"
    const val SETTINGS = "setting"

    const val ARG_USER_ID = "userId"

    fun userDetail(userId: Int): String = "users-detail/$userId"
}
