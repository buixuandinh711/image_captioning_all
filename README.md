# Image Captioning Application

The application includes 3 components:
- Machine learning models serving service ([`image_captioning_ml`](./image_captioning_ml)): Using NVIDIA Triton to serve model.
- Back-end service ([`image_captioning_be`](./image_captioning_be)): Using Nest.js to decode and forward client requests to serving service.