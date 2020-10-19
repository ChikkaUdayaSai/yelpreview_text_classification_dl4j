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
3. Log files (model training logs file is required).

# 2. Results
## 2.1 Model Performance on Dataset (10% as test set)
|  Index   | Epoch  | Accuracy  | Precision  | Recall | F1 Score |
|  ----  | ----  | ----  | ----  | ----  | ----  |
| 1  | 1 | 0.6162  | 0.6194 | 0.6162  | 0.6172 |
| 2  | 2 | 0.6238  | 0.6302 | 0.6238  | 0.6258 |
| 3  | 3 |   |  |   |  |
| 4  | 4 |   |  |   |  |

## 2.2 1st epoch
|  0  | 1| 2 | 3  | 4 |  |
|  ----  | ----  | ----  | ----  | ----  | ----  |
| 9213  | 2873  | 374 | 56| 83 | 0 = 0|
| 2414 | 6707 | 3188  | 205 | 85  | 1 = 1|
| 455 | 2212 | 7470  | 2164 | 298 | 2 = 2|
| 160 | 317 | 2701  | 6722 | 2699  | 3 = 3|
| 195 | 132 | 484  | 3084 | 8704  | 4 = 4|

## 2.3 2nd epoch
|  0  | 1| 2 | 3  | 4 |  |
|  ----  | ----  | ----  | ----  | ----  | ----  |
| 9287  | 2894  | 310 | 49| 59 | 0 = 0|
| 2329 | 7250 | 2832  | 137 | 51  | 1 = 1|
| 424 | 2506 | 7591  | 1846 | 232 | 2 = 2|
| 154 | 357 | 2933  | 6819 | 2336  | 3 = 3|
| 173 | 161 | 563  | 3354 | 8348  | 4 = 4|

## 2.4 3th epoch
