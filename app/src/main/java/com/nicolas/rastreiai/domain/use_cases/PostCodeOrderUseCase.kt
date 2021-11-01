package com.nicolas.rastreiai.domain.use_cases

import com.nicolas.rastreiai.common.Resource
import com.nicolas.rastreiai.domain.model.OrderRequest
import com.nicolas.rastreiai.domain.model.OrderStateUiDomain
import com.nicolas.rastreiai.domain.repository.PostmanRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class PostCodeOrderUseCase @Inject constructor(
    private val repository: PostmanRepository
) {
    operator fun invoke(request: OrderRequest): Flow<Resource<List<OrderStateUiDomain>>> =
        flow {
            try {
                emit(Resource.Loading<List<OrderStateUiDomain>>())
                val orderUiList = repository.getOrderState(request)
                emit(Resource.Success<List<OrderStateUiDomain>>(orderUiList))
            } catch (e: HttpException) {
                emit(
                    Resource.Error<List<OrderStateUiDomain>>(
                        e.localizedMessage ?: "An unexpected error occurred."
                    )
                )
            } catch (e: IOException) {
                emit(
                    Resource.Error<List<OrderStateUiDomain>>(
                        "Couldn't reach server. Check your internet connection."
                    )
                )
            }
        }
}