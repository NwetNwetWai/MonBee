package com.hana.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.hana.data.database.entity.AddressEntity

@Dao
interface AddressDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAddress(address: AddressEntity): Long

    @Transaction
    @Query("SELECT * FROM addresses WHERE id = :addressId")
    suspend fun getAddressWithGeo(addressId: Int): AddressWithGeo

}
