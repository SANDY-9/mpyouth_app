package go.kr.mapo.mapoyouth.ui.youth

import androidx.paging.PagingSource
import androidx.paging.PagingState
import go.kr.mapo.mapoyouth.util.STARTING_PAGE_INDEX
import java.lang.Exception

/**
 * @author SANDY
 * @email nnal0256@naver.com
 * @created 2021-09-30
 * @desc
 */
/*
class YothPagingSource() : PagingSource<Int, Youth>() {
    override fun getRefreshKey(state: PagingState<Int, Youth>): Int? =
        state.anchorPosition?.let {
            val anchorPage = state.closestPageToPosition(it)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }

    override suspend fun load(params: LoadParams<Int>): PagingSource.LoadResult<Int, Youth> = run {
        val page = params.key ?: STARTING_PAGE_INDEX
        try {
            LoadResult.Page(
                data = null
                prevKey = if(page == STARTING_PAGE_INDEX) null else page-1,
                nextKey = null
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}

 */