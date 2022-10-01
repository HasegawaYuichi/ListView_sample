package com.example.listview_sample

//import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //viewの取得
        val lv:ListView = findViewById(R.id.lv)
        //ListViewにリストを追加するためのボタン
        val btnAdd:Button = findViewById(R.id.btnAdd)
        //選択モードで選択したリストを削除するためのボタン
        val deletebtn:com.google.android.material.floatingactionbutton.FloatingActionButton = findViewById(R.id.deleteBtn)
        //ListViewのリストを選択モードに移行するためのボタンとして用意
        val btnEdit:Button = findViewById(R.id.btnEdit)

        //アダプターに入れてListViewをセット
        val adapter = ArrayAdapter<String>(
            this,
            android.R.layout.simple_list_item_1,
            arrayListOf()
        )
        lv.adapter = adapter

        //追加ボタンでアラートダイアログを表示する
        btnAdd.setOnClickListener{
            //EditText
            val et = EditText(this)
            AlertDialog.Builder(this)
                .setTitle("項目の追加")
                .setMessage("何をする？")
                .setView(et)
                .setPositiveButton("追加"){ _, _ ->
                    //add()でアダプターに追加する
                    val myTodo = et.text.toString()
                    adapter.add(myTodo)
                }
                .setNegativeButton("取消",null)
                .show()
        }

        //ファイル削除時のボタン押下アクション
        deletebtn.setOnClickListener{
            AlertDialog.Builder(this)
                .setTitle("項目を削除しますか？")
                //Yesを押したらremoveで削除
                .setPositiveButton("Yes",null)
                .setNegativeButton("No",null)
                .show()
        }

        //ListViewをタッチしたらアラートダイアログ
        lv.setOnItemClickListener { _, _, i, _ ->
            AlertDialog.Builder(this)
                .setTitle("項目を削除しますか？")
                //Yesを押したらremoveで削除
                .setPositiveButton("Yes") { _, _ ->
                    adapter.remove(adapter.getItem(i))
                    adapter.notifyDataSetChanged() //画面更新
                }
                .setNegativeButton("No",null)
                .show()
        }
    }
}