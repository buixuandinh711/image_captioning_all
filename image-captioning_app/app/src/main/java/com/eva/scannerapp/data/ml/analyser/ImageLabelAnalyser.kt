package com.eva.scannerapp.data.ml.analyser

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.ImageFormat
import android.graphics.Rect
import android.graphics.YuvImage
import android.media.Image
import android.util.Base64
import android.util.Log
import com.eva.scannerapp.domain.ml.MLModelAnalyzer
import com.eva.scannerapp.domain.ml.MLResource
import com.eva.scannerapp.domain.ml.models.RecognizedLabel
import com.google.mlkit.common.MlKitException
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.label.ImageLabel
import com.google.mlkit.vision.label.ImageLabeler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import org.json.JSONObject
import java.io.ByteArrayOutputStream
import java.io.IOException
import javax.inject.Inject
import kotlin.coroutines.resume

class ImageLabelAnalyser @Inject constructor(
	private val imageLabeler: ImageLabeler
) : MLModelAnalyzer<RecognizedLabel> {
	override suspend fun analyseImage(image: InputImage): MLResource<RecognizedLabel> {
//		val imageBase64 = convertImageToBase64(image)
//		val payload = mapOf("image" to imageBase64)
//		val response = sendRequest("http://127.0.0.1:3000/infers/captioning", payload)


		return suspendCancellableCoroutine { cont ->
			imageLabeler.process(image).apply {

				addOnCompleteListener {
					addOnSuccessListener { labels ->
						val models = labels.map { label: ImageLabel ->
							RecognizedLabel(text = label.text, confidence = label.confidence)
						}
						if (models.isNotEmpty()) cont.resume(MLResource.Success(data = models))
						else cont.resume(value = MLResource.Empty())
					}
					addOnFailureListener { exp ->
						if (exp is MlKitException) {
							cont.cancel()
							Log.i("ANALYZER_TAG", "ML_KIT_EXCEPTION")
							return@addOnFailureListener
						}
						cont.resume(MLResource.Error(exp))
					}
				}

				addOnCanceledListener(cont::cancel)
			}
		}
	}
	private fun convertImageToBase64(image: InputImage): String {
		// Lấy MediaImage từ InputImage
		val mediaImage = image.byteBuffer ?: throw IllegalArgumentException("InputImage must have a MediaImage source")

		// Chuyển MediaImage thành Bitmap
		throw IOException(image.format.toString())

		// Mã hóa ByteArray thành Base64
		return Base64.encodeToString(null, Base64.DEFAULT)
	}


	private suspend fun sendRequest(url: String, payload: Map<String, Any>): String {
		return withContext(Dispatchers.IO) {
			val client = OkHttpClient()
			val requestBody = RequestBody.create(
				"application/json".toMediaType(),
				JSONObject(payload).toString()
			)
			val request = Request.Builder()
				.url(url)
				.post(requestBody)
				.build()

			client.newCall(request).execute().use { response ->
				if (!response.isSuccessful) throw IOException("Unexpected code $response")
				response.body?.string() ?: throw IOException("Empty response body")
			}
		}
	}
}