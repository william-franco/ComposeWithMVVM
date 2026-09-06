package br.com.williamfranco.composewithmvvm.src.features.users.repositories

import br.com.williamfranco.composewithmvvm.src.common.constants.ApiConstant
import br.com.williamfranco.composewithmvvm.src.common.patterns.ResultPattern
import br.com.williamfranco.composewithmvvm.src.common.services.ConnectionService
import br.com.williamfranco.composewithmvvm.src.common.services.HttpService
import br.com.williamfranco.composewithmvvm.src.features.users.exceptions.UserException
import br.com.williamfranco.composewithmvvm.src.features.users.models.UserModel

interface UserRepository {
    suspend fun findAllUsers(): ResultPattern<List<UserModel>, UserException>
}

class UserRepositoryImpl(
    private val connectionService: ConnectionService,
    private val httpService: HttpService,
) : UserRepository {

    override suspend fun findAllUsers(): ResultPattern<List<UserModel>, UserException> {
        return try {
            connectionService.checkConnection()

            if (!connectionService.isConnected) {
                return ResultPattern.Error(UserException("Device not connected."))
            }

            val users: List<UserModel> = httpService.getData(ApiConstant.USERS)
            ResultPattern.Success(users)
        } catch (error: Exception) {
            ResultPattern.Error(UserException("Unexpected error: $error"))
        }
    }
}
