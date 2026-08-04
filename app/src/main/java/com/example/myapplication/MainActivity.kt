package com.example.myapplication

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Matrix
import android.os.Bundle
import android.provider.MediaStore
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.PopupMenu
import androidx.appcompat.app.AppCompatActivity
import androidx.camera.core.CameraSelector
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import kotlin.math.atan2
import kotlin.math.sqrt

class MainActivity : AppCompatActivity() {

    private lateinit var previewView: PreviewView
    private lateinit var overlayImage: ImageView

    private val matrix = Matrix()

    private var mode = 0
    private val NONE = 0
    private val DRAG = 1
    private val ZOOM = 2

    private var startX = 0f
    private var startY = 0f
    private var oldDist = 1f
    private var oldAngle = 0f

    private var isLocked = false
    private var crosshairVisible = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        previewView = findViewById(R.id.previewView)
        overlayImage = findViewById(R.id.overlayImage)

        val loadBtn = findViewById<Button>(R.id.loadBtn)
        val seekBar = findViewById<SeekBar>(R.id.opacitySeek)
        val menuBtn = findViewById<Button>(R.id.menuBtn)

        val crossV = findViewById<View>(R.id.crosshairVertical)
        val crossH = findViewById<View>(R.id.crosshairHorizontal)

        overlayImage.scaleType = ImageView.ScaleType.MATRIX
        overlayImage.imageMatrix = matrix

        // 📸 이미지 선택
        loadBtn.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(intent, 1)
        }

        // 🌫️ 투명도
        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                overlayImage.alpha = progress / 100f
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        // ⚙️ 메뉴 버튼
        menuBtn.setOnClickListener {
            val popup = PopupMenu(this, menuBtn)
            popup.menuInflater.inflate(R.menu.menu_main, popup.menu)

            popup.setOnMenuItemClickListener {
                when (it.itemId) {

                    R.id.lock -> {
                        isLocked = !isLocked
                        true
                    }

                    R.id.crosshair -> {
                        crosshairVisible = !crosshairVisible

                        if (crosshairVisible) {
                            crossV.visibility = View.VISIBLE
                            crossH.visibility = View.VISIBLE
                        } else {
                            crossV.visibility = View.GONE
                            crossH.visibility = View.GONE
                        }
                        true
                    }

                    else -> false
                }
            }

            popup.show()
        }

        // 🤏 터치 (이동 + 확대 + 회전)
        overlayImage.setOnTouchListener { _, event ->

            if (isLocked) return@setOnTouchListener true

            when (event.action and MotionEvent.ACTION_MASK) {

                MotionEvent.ACTION_DOWN -> {
                    startX = event.x
                    startY = event.y
                    mode = DRAG
                }

                MotionEvent.ACTION_POINTER_DOWN -> {
                    oldDist = spacing(event)
                    oldAngle = rotation(event)
                    if (oldDist > 10f) {
                        mode = ZOOM
                    }
                }

                MotionEvent.ACTION_MOVE -> {
                    if (mode == DRAG) {
                        val dx = event.x - startX
                        val dy = event.y - startY
                        matrix.postTranslate(dx, dy)
                        overlayImage.imageMatrix = matrix

                        startX = event.x
                        startY = event.y
                    } else if (mode == ZOOM) {
                        val newDist = spacing(event)
                        if (newDist > 10f) {
                            val scale = newDist / oldDist
                            val newAngle = rotation(event)
                            val rotation = newAngle - oldAngle

                            matrix.postScale(scale, scale, overlayImage.width / 2f, overlayImage.height / 2f)
                            matrix.postRotate(rotation, overlayImage.width / 2f, overlayImage.height / 2f)

                            overlayImage.imageMatrix = matrix

                            oldDist = newDist
                            oldAngle = newAngle
                        }
                    }
                }

                MotionEvent.ACTION_UP,
                MotionEvent.ACTION_POINTER_UP -> {
                    mode = NONE
                }
            }
            true
        }

        // 📷 카메라 권한
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
            != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.CAMERA),
                100
            )
        } else {
            startCamera()
        }
    }

    // 거리 계산 (줌)
    private fun spacing(event: MotionEvent): Float {
        if (event.pointerCount < 2) return 0f
        val x = event.getX(0) - event.getX(1)
        val y = event.getY(0) - event.getY(1)
        return sqrt(x * x + y * y)
    }

    // 각도 계산 (회전)
    private fun rotation(event: MotionEvent): Float {
        val dx = event.getX(0) - event.getX(1)
        val dy = event.getY(0) - event.getY(1)
        return Math.toDegrees(atan2(dy, dx).toDouble()).toFloat()
    }

    // 카메라 실행
    private fun startCamera() {
        val cameraProviderFuture = ProcessCameraProvider.getInstance(this)

        cameraProviderFuture.addListener({
            val cameraProvider = cameraProviderFuture.get()

            val preview = Preview.Builder().build()
            preview.setSurfaceProvider(previewView.surfaceProvider)

            val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

            cameraProvider.unbindAll()
            cameraProvider.bindToLifecycle(this, cameraSelector, preview)

        }, ContextCompat.getMainExecutor(this))
    }

    // 이미지 선택 결과
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 1 && resultCode == RESULT_OK) {

            overlayImage.setImageURI(data?.data)

            overlayImage.post {

                val drawable = overlayImage.drawable ?: return@post

                val viewWidth = overlayImage.width.toFloat()
                val viewHeight = overlayImage.height.toFloat()

                val imgWidth = drawable.intrinsicWidth.toFloat()
                val imgHeight = drawable.intrinsicHeight.toFloat()

                matrix.reset()

                // 👉 화면에 맞게 기본 스케일 계산
                val scale = minOf(viewWidth / imgWidth, viewHeight / imgHeight)

                // 👉 중앙 위치 계산
                val dx = (viewWidth - imgWidth * scale) / 2
                val dy = (viewHeight - imgHeight * scale) / 2

                matrix.postScale(scale, scale)
                matrix.postTranslate(dx, dy)

                overlayImage.imageMatrix = matrix
            }
        }
    }
}