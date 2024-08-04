package com.bibek.pokemonapp.utils.network

import kotlinx.coroutines.flow.Flow

suspend inline fun <T> Flow<NetworkResult<T>>.collectResponse(
     crossinline onSuccess: suspend (T?) -> Unit ,
     crossinline onError: suspend (String) -> Unit,
     crossinline onLoading: suspend (Boolean) -> Unit
)  {
        collect{networkResult->
            onLoading(false)
            when(networkResult){
                is NetworkResult.Error -> onError(networkResult.message?:"Unknown Error")
                is NetworkResult.Loading -> onLoading(true)
                is NetworkResult.Success ->  { onSuccess(networkResult.data) }
            }
        }

}