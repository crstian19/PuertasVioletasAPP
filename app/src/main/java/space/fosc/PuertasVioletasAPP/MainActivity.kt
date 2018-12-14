package space.fosc.PuertasVioletasAPP

import android.Manifest
import android.content.Context
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import android.content.Intent
import android.net.Uri
import android.support.v4.app.ActivityCompat
import java.security.Permission
import com.nabinbhandari.android.permissions.PermissionHandler
import com.nabinbhandari.android.permissions.Permissions
import android.R.id.message
import android.telephony.SmsManager
import space.fosc.test.R


class MainActivity : AppCompatActivity() {


    val tlf : String = "+34 000 000 000"

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }


    lateinit var mContext : Context;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        mContext = this;

        button_arriba.setOnClickListener {
            Permissions.check(this/*context*/, Manifest.permission.CALL_PHONE, null, object : PermissionHandler() {
                override fun onGranted() {
                    mContext.startActivity(Intent(Intent.ACTION_CALL, Uri.parse("tel:" + tlf)))
                }
            })
        }


        button_abajo.setOnClickListener {
            Permissions.check(this/*context*/, Manifest.permission.SEND_SMS, null, object : PermissionHandler() {
                override fun onGranted() {
                    val sm = SmsManager.getDefault()
                    sm.sendTextMessage(tlf, null, "Mensaje", null, null)
                }
            })
        }

    }

    override fun onResume() {
        super.onResume()

    }
}
