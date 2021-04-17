package com.madmuv.marvelheroesclone.mapper

import com.madmuv.marvelheroesclone.model.PosterErrorResponse
import com.skydoves.sandwich.ApiErrorModelMapper
import com.skydoves.sandwich.ApiResponse
import com.skydoves.sandwich.message

object ErrorResponseMapper : ApiErrorModelMapper<PosterErrorResponse> {

    override fun map(apiErrorResponse: ApiResponse.Failure.Error<*>): PosterErrorResponse {
        return PosterErrorResponse(apiErrorResponse.statusCode.code, apiErrorResponse.message())
    }
}