package com.eva.scannerapp.presentation.util.preview

import androidx.paging.PagingData
import com.eva.scannerapp.domain.image.models.ImageBucketModel
import com.eva.scannerapp.domain.image.models.ImageDataModel
import com.eva.scannerapp.domain.ml.models.RecognizedLabel
import kotlinx.coroutines.flow.MutableStateFlow

object PreviewFakes {

	private val FAKE_IMAGE_BUCKET_MODEL =
		ImageBucketModel(bucketId = 1, bucketName = "Preview Bucket")

	val FAKE_IMAGE_DATA_MODEL = ImageDataModel(
		id = 0,
		fileSize = "2kb",
		title = "THIS IS WONDERFUL",
		bucketModel = FAKE_IMAGE_BUCKET_MODEL,
		imageUri = ""
	)

	private val FAKE_RECOGNIZED_LABEL_RESULT_MODEL =
		RecognizedLabel(text = "Something", confidence = .5f)

	val FAKE_RECOGNIZED_LABEL_RESULTS_LIST = List(10) {
		FAKE_RECOGNIZED_LABEL_RESULT_MODEL.copy(confidence = it.toFloat())
	}

	val FAKE_IMAGE_DATA_MODELS = List(10) {
		FAKE_IMAGE_DATA_MODEL.copy(id = it.toLong())
	}

	val FAKE_PAGED_DATA = MutableStateFlow(PagingData.from(FAKE_IMAGE_DATA_MODELS))
}