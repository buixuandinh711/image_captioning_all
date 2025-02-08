## LoRA Fine-Tuning BLIP

### Overview
This repository contains a Jupyter Notebook (`lora-ft-blip.ipynb`) for fine-tuning the **BLIP (Bootstrapped Language-Image Pretraining) model** using **LoRA (Low-Rank Adaptation)**. The goal is to efficiently adapt BLIP for image captioning with fewer trainable parameters.

### Requirements

#### 1. Install Dependencies
Make sure you have the necessary dependencies installed before running the notebook.

```bash
pip install torch torchvision transformers peft datasets accelerate
```

#### 2. GPU Support (Optional but Recommended)
Fine-tuning BLIP requires significant computational resources. Using a CUDA-compatible GPU is highly recommended.

Check GPU availability in Python:
```python
import torch
print(torch.cuda.is_available())
```
If `False`, ensure CUDA is properly installed or switch to a machine with GPU support.

### Usage

1. **Open the Notebook**
   ```bash
   jupyter notebook lora-ft-blip.ipynb
   ```

2. **Prepare Dataset**
   - The notebook expects an image-caption dataset.
   - Modify dataset paths in the notebook as needed.

3. **Run Fine-Tuning**
   - Execute the notebook cells sequentially.
   - LoRA optimizes BLIP efficiently with fewer trainable parameters.

4. **Evaluate the Model**
   - The notebook provides sample inference code.
   - Test the fine-tuned model on new images.

### Notes
- Ensure that the dataset is formatted correctly (e.g., JSON, CSV, or Hugging Face datasets format).
- Adjust hyperparameters (learning rate, batch size) for better performance.
- Fine-tuned models can be saved and loaded for inference.

### References
- [BLIP: Bootstrapped Language-Image Pretraining](https://arxiv.org/abs/2201.12086)
- [LoRA: Low-Rank Adaptation of Large Language Models](https://arxiv.org/abs/2106.09685)
- [Hugging Face Transformers](https://huggingface.co/docs/transformers/index)

