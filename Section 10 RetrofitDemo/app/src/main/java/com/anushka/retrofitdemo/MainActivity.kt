package com.anushka.retrofitdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.liveData
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        makeGetRequest()
//
//        makeRequestByQuery()
//
//        makeRequestByPath()

        makePostRequest()
    }

    private fun makeGetRequest() {
        val responseLiveData: LiveData<Response<Album>> = liveData {
            val response = ApiHelper.getAlbumServices().getAlbums()
            emit(response)
        }

        responseLiveData.observe(this, Observer {
            println(it.body())

        })
    }

    private fun makeRequestByQuery() {
        val queryLiveData: LiveData<Response<Album>> = liveData {
            val response = ApiHelper.getAlbumServices().getSortedAlbums(1)
            emit(response)
        }

        queryLiveData.observe(this, Observer {
            val albumList = it.body()?.listIterator()
            if(albumList!= null) {
                while (albumList.hasNext()) {
                    val albumItem = albumList.next()
                    val result = """
                        { 
                            title : ${albumItem.title}
                            id : ${albumItem.id}
                            userId : ${albumItem.userId}
                        }
                    """.trimIndent()
                    text_view_info.append(result)
                }
            }
        })
    }

    private fun makeRequestByPath() {
        val pathLiveData: LiveData<Response<AlbumItem>> = liveData {
            val response = ApiHelper.getAlbumServices().getAlbumById(3)
            emit(response)
        }

        pathLiveData.observe(this, Observer {
            println(it.body())
        })
    }

    private fun makePostRequest(){
        val item = AlbumItem(id = 100,userId = 4,title = "lorem ipsum doleres")
        val postLiveData: LiveData<Response<AlbumItem>> = liveData {
            val response = ApiHelper.getAlbumServices().uploadAlbum(item)
            emit(response)
        }

        postLiveData.observe(this, Observer {
            println(it.body())
        })
    }
}
