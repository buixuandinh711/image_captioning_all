package com.eva.scannerapp.data.ml.feed

import com.eva.scannerapp.domain.ml.MLModelAnalyzer
import com.eva.scannerapp.domain.ml.models.RecognizedLabel
import javax.inject.Inject

class FeedAnalyzers {
	class Labels @Inject constructor(
		labels: MLModelAnalyzer<RecognizedLabel>
	) : CameraFeedImageAnalyzer<RecognizedLabel>(mlAnalyser = labels)

}

