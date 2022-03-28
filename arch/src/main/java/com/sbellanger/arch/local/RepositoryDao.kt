package com.sbellanger.arch.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sbellanger.arch.local.model.Repository
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

@Dao
interface RepositoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRepository(repository: Repository): Completable

    @Query("DELETE FROM Repository WHERE id = :id")
    fun removeRepository(id: Int): Completable

    @Query("SELECT * FROM Repository WHERE id = :id")
    fun getRepository(id: Int): Single<Repository>

    @Query("SELECT * FROM Repository")
    fun getRepositories(): Observable<List<Repository>>
}
