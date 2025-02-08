import triton_python_backend_utils as pb_utils
import numpy as np
import PIL.Image
import logging
import sys
import requests
from transformers import Blip2Processor, Blip2ForConditionalGeneration


logger = logging.getLogger()
logger.setLevel(logging.DEBUG)
h1 = logging.StreamHandler(sys.stdout)
h1.setLevel(logging.DEBUG)
h2 = logging.StreamHandler(sys.stderr)
h2.setLevel(logging.DEBUG)
logger.addHandler(h1)
logger.addHandler(h2)


class TritonPythonModel:
    def initialize(self, args):
        self.processor = Blip2Processor.from_pretrained("Salesforce/blip2-opt-2.7b")
        self.model = Blip2ForConditionalGeneration.from_pretrained("Salesforce/blip2-opt-2.7b", device_map="auto")

    def execute(self, requests):
        responses = []
        for request in requests:
            # Convert Tensor to PIL Image
            image = pb_utils.get_input_tensor_by_name(request, "image")
            logger.debug(f"1 image type {type(image)}")
            image = image.as_numpy() * 255.0
            logger.debug(f"2 image type {type(image)}")
            logger.debug(f"2 image shape {image.shape}")
            image = PIL.Image.fromarray(image.astype(np.uint8))
            logger.debug(f"3 image type {type(image)}")
            logger.debug(f"3 image size {image.size}")
            question = ""
            inputs = self.processor(image, question, return_tensors="pt")
            out = self.model.generate(**inputs)
            output = self.processor.decode(out[0], skip_special_tokens=True)

            logger.debug(f"4 output {output}")
            logger.debug(f"4 output.encode() {output.encode()}")

            # Encode the text to byte tensor to send back
            inference_response = pb_utils.InferenceResponse(
                output_tensors=[
                    pb_utils.Tensor(
                        "generated_caption",
                        np.array([output.encode("UTF-8")], dtype=np.bytes_),
                    )
                ]
            )
            responses.append(inference_response)

        return responses

    def finalize(self, args):
        self.generator = None
