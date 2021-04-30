# XDialog
> Android Dialog组件

## how to use

1. app/build.gradle 配置如下：
```
dependencies {
   ...

   implementation 'com.yazao:xdialog:1.0.0'
}
```

2.use

```
private fun createDialog(): XDialog {
    

    //begin 

    return XDialog.Builder(this)
        .setLayoutRes(R.layout.dialog_demo_layout)
        .setCancelable(true)
        .setCanceledOnTouchOutside(true)
        .setGravity(Gravity.BOTTOM)
        .setText(R.id.dialog_content, "tax")
        .setOnClickListener(
            R.id.btn_text,
            OnDialogClickListener { dialog, view ->
                Toast.makeText(
                    this@MainActivity,
                    "click btn",
                    Toast.LENGTH_SHORT
                ).show()

                dialog.hide()
            })
        .show()
    
    //end
}
```

布局文件： R.layout.dialog_demo_layout

```
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="wrap_content">

    <TextView
        android:id="@+id/dialog_content"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:gravity="center"
        android:padding="40dp"
        android:textColor="@android:color/white"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintLeft_toLeftOf="parent"
        app:layout_constraintRight_toRightOf="parent"
        app:layout_constraintTop_toTopOf="parent" />

    <TextView
        android:id="@+id/btn_text"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginTop="30dp"
        android:layout_marginBottom="20dp"
        android:padding="20dp"
        android:text="Sure"
        android:textColor="@android:color/white"
        app:layout_constraintLeft_toLeftOf="parent"
        app:layout_constraintRight_toRightOf="parent"
        app:layout_constraintTop_toBottomOf="@id/dialog_content" />

</androidx.constraintlayout.widget.ConstraintLayout>
```