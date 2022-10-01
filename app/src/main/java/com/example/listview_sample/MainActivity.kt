package com.example.listview_sample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import com.example.listview_sample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val lv = binding.lv
        val btnAdd = binding.btnAdd
        val deletebtn = binding.deleteBtn

        // データを用意
        val data = mutableListOf(
            "a", "b", "c", "d",
            "e", "f", "g", "h"
        )

        //アダプターに入れてListViewをセット
        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            data
        )
        lv.adapter = adapter

        //追加ボタンでアラートダイアログを表示する
        btnAdd.setOnClickListener{
            //EditText
            val et = EditText(this)
            AlertDialog.Builder(this)
                .setTitle("項目の追加")
                .setView(et)
                .setPositiveButton("追加"){ _, _ ->
                    //add()でアダプターに追加する
                    val adlist = et.text.toString()
                    adapter.add(adlist)
                }
                .setNegativeButton("取消",null)
                .show()
        }

        //ファイル削除時のボタン押下アクション
        deletebtn.setOnClickListener{
            AlertDialog.Builder(this)
                .setTitle("項目を削除しますか？")
                //Yesを押したらremoveで削除、未実装部なのでnull
                .setPositiveButton("Yes",null)
                .setNegativeButton("No",null)
                .show()
        }

        //ListViewをタッチしたらアラートダイアログ表示
        lv.setOnItemClickListener { _, _, i, _ ->
            AlertDialog.Builder(this)
                .setTitle("項目を削除しますか？")
                //Yesを押したらremoveでリストを削除
                .setPositiveButton("Yes") { _, _ ->
                    adapter.remove(data[i])
                    adapter.notifyDataSetChanged() //画面更新
                }
                .setNegativeButton("No",null)
                .show()
        }
    }
}