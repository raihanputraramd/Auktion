package com.example.auktion.data;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.auktion.model.Barang;

@Dao
public interface BarangDAO {

    @Query("SELECT * FROM barang WHERE id_barang = :id LIMIT 1")
    Barang selectBarangDetail(int id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertBarang(Barang barang);

    @Query("SELECT * FROM barang")
    Barang[] selectAllBarang();

    @Query("UPDATE barang SET harga_awal = :hargaAwal WHERE id_barang = :id")
    void updateHarga(String hargaAwal,int id);


    @Query("SELECT * FROM barang ORDER BY id_barang LIMIT 1")
    int checkEmptyBarang();

    @Query("UPDATE barang SET status = :status WHERE id_barang =:id")
    void updateStatus(int status, int id);


}
