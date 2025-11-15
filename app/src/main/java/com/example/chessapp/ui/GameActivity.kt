package com.example.chessapp.ui

import android.graphics.Color
import android.os.Bundle
import android.widget.GridLayout
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.chessapp.R
import com.example.chessapp.model.Piece
import com.example.chessapp.network.WebSocketManager
import kotlinx.coroutines.launch

class GameActivity : AppCompatActivity() {

    private lateinit var grid: GridLayout
    private lateinit var btnback : ImageButton
    private lateinit var statusText: TextView
    private val board = Array(8) { arrayOfNulls<Piece>(8) }
    private var selectedCell: String? = null
    private val ws = WebSocketManager()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        grid = findViewById(R.id.chessBoard)
        statusText = findViewById(R.id.statusText)
        btnback = findViewById(R.id.btnBack)

        btnback.setOnClickListener {
            finish()
        }

        initBoard()
        drawBoard()
    }

    // --- создаём начальную позицию ---
    private fun initBoard() {
        val order = arrayOf("rook","knight","bishop","queen","king","bishop","knight","rook")

        for (i in 0..7) {
            board[1][i] = Piece("pawn", "black")
            board[6][i] = Piece("pawn", "white")
            board[0][i] = Piece(order[i], "black")
            board[7][i] = Piece(order[i], "white")
        }
    }

    // --- визуализируем клетки ---
    private fun drawBoard() {
        grid.removeAllViews()
        val size = 8
        grid.rowCount = size
        grid.columnCount = size

        for (row in 0 until size) {
            for (col in 0 until size) {
                val cell = ImageView(this)
                val tag = "${'a' + col}${8 - row}"
                cell.tag = tag
                val color = if ((row + col) % 2 == 0)
                    Color.parseColor("#fbf5de")
                else
                    Color.parseColor("#4b4847")
                cell.setBackgroundColor(color)
                cell.adjustViewBounds = true
                cell.scaleType = ImageView.ScaleType.CENTER_INSIDE

                val piece = board[row][col]
                if (piece != null) {
                    val resId = getPieceImage(piece)
                    if (resId != 0) cell.setImageResource(resId)
                }

                cell.setOnClickListener { onCellClick(tag) }

                val params = GridLayout.LayoutParams().apply {
                    width = 0
                    height = 0
                    columnSpec = GridLayout.spec(col, 1f)
                    rowSpec = GridLayout.spec(row, 1f)
                }
                grid.addView(cell, params)
            }
        }
    }

    // --- клик по клетке ---
    private fun onCellClick(tag: String) {
        if (selectedCell == null) {
            selectedCell = tag
            statusText.text = "Выбрано: $tag"
        } else {
            val from = selectedCell!!
            val to = tag
            selectedCell = null

            makeMove(from, to)
        }
    }

    // --- логика хода ---
    private fun makeMove(from: String, to: String) {
        val fromPos = convert(from)
        val toPos = convert(to)

        val movingPiece = board[fromPos.first][fromPos.second] ?: return
        board[toPos.first][toPos.second] = movingPiece
        board[fromPos.first][fromPos.second] = null

        drawBoard()
        statusText.text = "Ход: $from → $to"

        lifecycleScope.launch {
            ws.sendMove(from, to)
        }
    }

    private fun convert(coord: String): Pair<Int, Int> {
        val col = coord[0] - 'a'
        val row = 8 - coord[1].digitToInt()
        return Pair(row, col)
    }

    private fun getPieceImage(piece: Piece): Int {
        return when ("${piece.type}_${piece.color}") {
            "pawn_white" -> R.drawable.wp
            "rook_white" -> R.drawable.wr
            "knight_white" -> R.drawable.wn
            "bishop_white" -> R.drawable.wb
            "queen_white" -> R.drawable.wq
            "king_white" -> R.drawable.wk

            "pawn_black" -> R.drawable.bp
            "rook_black" -> R.drawable.br
            "knight_black" -> R.drawable.bn
            "bishop_black" -> R.drawable.bb
            "queen_black" -> R.drawable.bq
            "king_black" -> R.drawable.bk
            else -> 0
        }
    }
}
