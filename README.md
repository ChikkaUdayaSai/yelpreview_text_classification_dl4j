# yelpreview_text_classification_dl4j
use dl4j to do a simple nlp classification task.

# 1. Objective
Create an app to predict the customers’ rating score using DL4J

## 1.1 Instructions

1. Download the datasets from [data](https://s3.amazonaws.com/fast-ai-nlp/yelp_review_full_csv.tgz).
2. Perform data preprocessing.
3. Build a model from scratch and train it with DL4J.
4. Evaluate your model and save the best model. Log down the necessary evaluation
metric score.
5. Note: create a log to log down the data preprocessing and model training step.
6. Create a separate file for inference that loads in the saved model and allows user to key
in review and predict the respective rating score.
7. Create separate .jar for model training (which includes preprocessing steps) and
inference

## 1.2 Evaluations

1. The completion of given tasks.
2. Machine learning pipelines.
3. The accuracy of the model.
4. The cleanliness and the structure of the code.

## 1.3 Submit Items
1. .jar files of your codes together with trained model.
2. Source code (put it on GitHub)
3. Log files (model training logs file is required)

# 2. Results
## 2.1 Model Performance on Dataset (10%)
|  Index   | Epoch  | Accuracy  | Precision  | Recall | F1 Score |
|  ----  | ----  | ----  | ----  | ----  | ----  |
| 1  | 1 | 0.6162  | 0.6194 | 0.6162  | 0.6172 |
| 2  | 2 | 0.6238  | 0/6302 | 0.6238  | 0.6258 |
| 3  | 3 |   |  |   |  |
| 4  | 4 |   |  |   |  |

## 2.2 1st epoch
![1st epoch result](imgs/1st epoch.png?raw=true "1st epoch result") 

## 2.3 2nd epoch
![1st epoch result](imgs/2nd epoch.png?raw=true "2nd epoch result")  