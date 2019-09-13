package com.reyesmagos.gl.core

interface Interactor<Response, Params> where Response : Any {

    suspend operator fun invoke(
        params: Params
    ): Response
}
