package com.mostafa.domain.usecases

import com.mostafa.domain.repository.INewsRepository
import javax.inject.Inject


class GetNewsUseCase @Inject constructor(private val repository: INewsRepository) {

    suspend operator fun invoke() = repository.getNews()
}