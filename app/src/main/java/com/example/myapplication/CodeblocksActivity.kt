package com.example.myapplication



///gbhjnkml,

import android.content.ClipDescription
import android.os.Bundle
import android.view.*
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.allViews
import androidx.core.view.get
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView



class CodeblocksActivity : AppCompatActivity() {
    private lateinit var blocksContainer: LinearLayout
    private lateinit var navView: NavigationView
    private lateinit var toggle: ActionBarDrawerToggle
    private lateinit var drawerLayout: DrawerLayout

    private lateinit var cellArray: Array<LinearLayout>

    private val dragListener = View.OnDragListener { view, event ->
        when(event.action) {
            DragEvent.ACTION_DRAG_STARTED -> {
                event.clipDescription.hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)
                true
            }
            DragEvent.ACTION_DRAG_ENTERED -> {
                view.invalidate()
                true
            }
            DragEvent.ACTION_DRAG_LOCATION -> true
            DragEvent.ACTION_DRAG_EXITED -> {
                view.invalidate()
                true
            }
            DragEvent.ACTION_DROP -> {
                val v = event.localState as View

                //println("ACTION_DROP = " + view.id.toString())
                val source = v.parent as ViewGroup

                val destination = view as LinearLayout
                if(isCellFree(destination))
                {
                    source.removeView(v)
                    destination.addView(v)
                    v.visibility = View.VISIBLE
                    true
                }
                else {
                    false
                }
            }
            DragEvent.ACTION_DRAG_ENDED -> {
                view.invalidate()
                true
            }
            else -> false
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_codeblocks)

        blocksContainer = findViewById(R.id.blocksContainer)
        drawerLayout = findViewById(R.id.drawerLayout)


        initializeTable()
        setOnDragListenerToTable()

        toggle = ActionBarDrawerToggle (this,drawerLayout,R.string.open,R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        navView = findViewById(R.id.navView)
        navView.setNavigationItemSelectedListener {
            when(it.itemId)
            {   R.id.nav_home->{ Toast.makeText(applicationContext,"Clicked Home",Toast.LENGTH_SHORT).show();   finish()}
                R.id.nav_intVar-> {
                    addInitCodeblockView()
                    Toast.makeText(applicationContext,"Created Integer variable",Toast.LENGTH_SHORT).show();
                }
                R.id.nav_assignValtoVar->{
                    addIntCodeblockView()
                    Toast.makeText(applicationContext,"Created Assigning value",Toast.LENGTH_SHORT).show();
                }
                R.id.nav_while-> {
                    addWhileCodeblockView()
                    Toast.makeText(applicationContext,"Created While Expression",Toast.LENGTH_SHORT).show();
                }
                R.id.nav_if-> {
                    addIfCodeblockView()
                    Toast.makeText(applicationContext,"Created If",Toast.LENGTH_SHORT).show()
                }
                R.id.nav_print-> {
                    addPrintCodeblockView()
                    Toast.makeText(applicationContext,"Created Output Expression",Toast.LENGTH_SHORT).show();
                }
            }
            true
        }

    }

    private fun initializeTable() {
        val cell1:LinearLayout = findViewById(R.id.cell_1)
        val cell2:LinearLayout = findViewById(R.id.cell_2)
        val cell3:LinearLayout = findViewById(R.id.cell_3)
        val cell4:LinearLayout = findViewById(R.id.cell_4)
        val cell5:LinearLayout = findViewById(R.id.cell_5)
        val cell6:LinearLayout = findViewById(R.id.cell_6)
        val cell7:LinearLayout = findViewById(R.id.cell_7)
        val cell8:LinearLayout = findViewById(R.id.cell_8)
        val cell9:LinearLayout = findViewById(R.id.cell_9)
        val cell10:LinearLayout = findViewById(R.id.cell_10)
        val cell11:LinearLayout = findViewById(R.id.cell_11)
        val cell12:LinearLayout = findViewById(R.id.cell_12)
        cellArray = arrayOf(cell1,cell2,cell3,cell4,cell5,cell6,cell7,cell8, cell9, cell10, cell11, cell12)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu,menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if(toggle.onOptionsItemSelected(item))
            return true

        return super.onOptionsItemSelected(item)
    }

    private fun setOnDragListenerToTable() {
        for (cell in cellArray){
            cell.setOnDragListener(dragListener)
        }
    }

    private fun isCellFree(view: LinearLayout): Boolean {

        if (view.childCount == 0) {
            return true
        }

        return false
    }

    private fun findFreeCellIndex(): Int {
        for ((index, cell) in cellArray.withIndex()) {
            if (cell.childCount == 0) {
                return index
            }
        }
        return -1
    }

    private fun addIntVarCodeblockView() {
        val subView = IntVarCodeblockWidget(this)
        val index = findFreeCellIndex()
        if (index == -1) {
            return
        }
        cellArray[index].addView(subView.view)
    }

    private fun addIfCodeblockView() {
        val subView = IfCodeblockWidget(this)
        val index = findFreeCellIndex()
        if (index == -1) {
            return
        }
        cellArray[index].addView(subView.view)
    }

    private fun addInitCodeblockView() {
        val subView = InitCodeblockWidget(this)
        val index = findFreeCellIndex()
        if (index == -1) {
            return
        }
        cellArray[index].addView(subView.view)
    }

    private fun addWhileCodeblockView() {
        val subView = WhileCodeblockWidget(this)
        val index = findFreeCellIndex()
        if (index == -1) {
            return
        }
        cellArray[index].addView(subView.view)
    }

    private fun addPrintCodeblockView() {
        val subView = PrintCodeblockWidget(this)
        val index = findFreeCellIndex()
        if (index == -1) {
            return
        }
        cellArray[index].addView(subView.view)
    }
    private fun addIntCodeblockView() {
        val subView = IntVarCodeblockWidget(this)
        val index = findFreeCellIndex()
        if (index == -1) {
            return
        }
        cellArray[index].addView(subView.view)
    }
}