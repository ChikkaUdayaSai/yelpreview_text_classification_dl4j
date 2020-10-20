# yelpreview_text_classification_dl4j
First time to use DL4J to do a simple nlp classification task. 
Couldn't try many times to make a better model without a charming GPU at home.

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
3. The accuracy of the model. (See [Result](https://github.com/jackspiderman/yelpreview_text_classification_dl4j#2-results))
4. The cleanliness and the structure of the code.

## 1.3 Submit Items
1. .jar files of your codes together with trained model. (See on [Baidu Yunpan](https://pan.baidu.com/s/1olbxijXOpF0mEhJ0WmyQZg), password:dl4j )
2. Source code (put it on [GitHub](https://github.com/jackspiderman/yelpreview_text_classification_dl4j))
3. Log files (model training logs file is required. See [train.log](https://github.com/jackspiderman/yelpreview_text_classification_dl4j/blob/main/train.log)).

# 2. Model Performance on Dataset (10% as test set)
## 2.1 Evaluation Metrics
|  Index   | Epoch  | Accuracy  | Precision  | Recall | F1 Score |
|  ----  | ----  | ----  | ----  | ----  | ----  |
| 1  | 1 | 0.6162  | 0.6194 | 0.6162  | 0.6172 |
| 2  | 2 | 0.6238  | 0.6302 | 0.6238  | 0.6258 |
| 3  | 3 |  0.6267 | 0.6326 | 0.6267  | 0.6284 |
| 4  | 4 |  0.6318 | 0.6369 | 0.6318  | 0.6334 |

## 2.2 Confusion Matrix

## 2.2.1 1st epoch
|  0  | 1| 2 | 3  | 4 |  |
|  ----  | ----  | ----  | ----  | ----  | ----  |
| 9213  | 2873  | 374 | 56| 83 | 0 = 0|
| 2414 | 6707 | 3188  | 205 | 85  | 1 = 1|
| 455 | 2212 | 7470  | 2164 | 298 | 2 = 2|
| 160 | 317 | 2701  | 6722 | 2699  | 3 = 3|
| 195 | 132 | 484  | 3084 | 8704  | 4 = 4|

### 2.2.2 2nd epoch
|  0  | 1| 2 | 3  | 4 |  |
|  ----  | ----  | ----  | ----  | ----  | ----  |
| 9287  | 2894  | 310 | 49| 59 | 0 = 0|
| 2329 | 7250 | 2832  | 137 | 51  | 1 = 1|
| 424 | 2506 | 7591  | 1846 | 232 | 2 = 2|
| 154 | 357 | 2933  | 6819 | 2336  | 3 = 3|
| 173 | 161 | 563  | 3354 | 8348  | 4 = 4|

### 2.2.3 3th epoch
 |  0  | 1| 2 | 3  | 4 |  |
|  ----  | ----  | ----  | ----  | ----  | ----  |
| 9460  | 2726  | 324 | 35| 54 | 0 = 0|
| 2481 | 7186 | 2757  | 117 | 58  | 1 = 1|
| 461 | 2471 | 7641  | 1833 | 193 | 2 = 2|
| 145 | 354 | 2929  | 6907 | 2264  | 3 = 3|
| 179 | 160 | 543  | 3430 | 8287  | 4 = 4|

### 2.2.4 4th epoch
 |  0  | 1| 2 | 3  | 4 |  |
|  ----  | ----  | ----  | ----  | ----  | ----  |
| 9505  | 2682  | 313 | 39| 60 | 0 = 0|
| 2448 | 7224 | 2735  | 130 | 62  | 1 = 1|
| 424 | 2441 | 7669  | 1856 | 209 | 2 = 2|
| 128 | 334 | 2822  | 7003 | 2312  | 3 = 3|
| 155 | 148 | 498  | 3400 | 8398  | 4 = 4|
  
  # 3. Usage
  
  ## 3.1 download best model or trained
  
  
  ## 3.2 edit configuration file
  following properities should be set before using.
  dataDir = /the/path/of/yelpreview
  
  
  ## 3.3 Prepare Training Data and Testing Data
```
java -jar PrepareDataset.jar /your/config/path
```

## 3.4 Train
```
java -jar TrainReviews.jar /your/config/path
```


## 3.5 Inference
Download the best [model](https://pan.baidu.com/s/1olbxijXOpF0mEhJ0WmyQZg) (pwd: dl4j)or train another one. 
Then add path of the model to the configuration file. 
```
java -jar TestReviews.jar /your/config/path
```
### Example:
Key in a sentence into the input box, 
click the 'Check' button. Then it shows the category of that sentence.

![UI](imgs/Inference_UI.png?raw=true "Inference UI")

  # 4. References
  [Deeplearning examples](https://github.com/eclipse/deeplearning4j-examples) put on github
  makes this task easier, such as [ImdbReviewClassificationRNN](https://github.com/eclipse/deeplearning4j-examples/blob/master/dl4j-examples/src/main/java/org/deeplearning4j/examples/advanced/modelling/textclassification/pretrainedword2vec/ImdbReviewClassificationRNN.java)
  and [customcorpusword2vec](https://github.com/eclipse/deeplearning4j-examples/blob/master/dl4j-examples/src/main/java/org/deeplearning4j/examples/advanced/modelling/textclassification/customcorpusword2vec/TrainNews.java)


