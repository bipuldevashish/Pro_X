package com.bipuldevashish.pro_x.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.bipuldevashish.pro_x.data.api.ApiService
import com.bipuldevashish.pro_x.data.api.RetrofitBuilder
import com.bipuldevashish.pro_x.data.local.ImageDatabase
import com.bipuldevashish.pro_x.data.paging.PexelPagingSource

class ImageRepository(val db : ImageDatabase) {

            suspend fun getImagesResults(page : Int, per_page : Int) =
                    RetrofitBuilder.api.getImageResults(page, per_page)

            suspend fun getSearchReasults(apiService: ApiService, query : String) =
                    Pager(
                            config = PagingConfig(
                                    pageSize = 20,
                                    maxSize = 100,
                                    enablePlaceholders = false
                            ),
                            pagingSourceFactory = { PexelPagingSource(apiService, query)}
                    ).liveData
}