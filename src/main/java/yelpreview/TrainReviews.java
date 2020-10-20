package yelpreview;

import java.io.File;
import java.io.IOException;
import org.deeplearning4j.models.embeddings.loader.WordVectorSerializer;
import org.deeplearning4j.models.embeddings.wordvectors.WordVectors;
import org.deeplearning4j.nn.conf.GradientNormalization;
import org.deeplearning4j.nn.conf.MultiLayerConfiguration;
import org.deeplearning4j.nn.conf.NeuralNetConfiguration;
import org.deeplearning4j.nn.conf.layers.LSTM;
import org.deeplearning4j.nn.conf.layers.RnnOutputLayer;
import org.deeplearning4j.nn.conf.layers.recurrent.Bidirectional;
import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.deeplearning4j.nn.weights.WeightInit;
import org.deeplearning4j.optimize.api.InvocationType;
import org.deeplearning4j.optimize.listeners.EvaluativeListener;
import org.deeplearning4j.optimize.listeners.ScoreIterationListener;
import org.deeplearning4j.text.tokenization.tokenizer.preprocessor.CommonPreprocessor;
import org.deeplearning4j.text.tokenization.tokenizerfactory.DefaultTokenizerFactory;
import org.deeplearning4j.text.tokenization.tokenizerfactory.TokenizerFactory;
import org.nd4j.linalg.activations.Activation;
import org.nd4j.linalg.learning.config.RmsProp;
import org.nd4j.linalg.lossfunctions.LossFunctions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TrainReviews {
	
	final static Logger logger = LoggerFactory.getLogger(TrainReviews.class);

	public static void main(String[] args) {
		
		logger.info("Training...");
		
		String dataDir = "";
		WordVectors wordVectors;
		String wordVectorsPath = "";
		int batchSize = 32; // Number of examples in each minibatch
		int nEpochs = 4; // Number of epochs (full passes of training data) to train on
		int truncateReviewsToLength = 300; // Truncate reviews with length (# words) greater than this
		String modelDir = "";
		
		try {
			dataDir = Utils.loadConf(args[0],"dataDir");
			wordVectorsPath = Utils.loadConf(args[0],"wordVectorsPath");
			modelDir = Utils.loadConf(args[0],"modelDir");
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		logger.info("Set data path to {}", dataDir);
		logger.info("Set word2vector path to {}", wordVectorsPath);
		logger.info("Set model path to {}", modelDir);

		wordVectors = WordVectorSerializer.loadStaticModel(new File(wordVectorsPath));

		TokenizerFactory tokenizerFactory = new DefaultTokenizerFactory();
		tokenizerFactory.setTokenPreProcessor(new CommonPreprocessor());

		ReviewsIterator iTrain = new ReviewsIterator.Builder().dataDirectory(dataDir).wordVectors(wordVectors)
				.batchSize(batchSize).truncateLength(truncateReviewsToLength).tokenizerFactory(tokenizerFactory)
				.train(true).build();

		ReviewsIterator iTest = new ReviewsIterator.Builder().dataDirectory(dataDir).wordVectors(wordVectors)
				.batchSize(batchSize).tokenizerFactory(tokenizerFactory).truncateLength(truncateReviewsToLength)
				.train(false).build();

		int inputNeurons = wordVectors.getWordVector(wordVectors.vocab().wordAtIndex(0)).length;
		int outputs = iTrain.getLabels().size();

		logger.info("inputNeurons: " + inputNeurons);

		// Set up network configuration
		MultiLayerConfiguration conf = new NeuralNetConfiguration.Builder().updater(new RmsProp(0.0018))
				.l2(1e-5).weightInit(WeightInit.XAVIER)
				.gradientNormalization(GradientNormalization.ClipElementWiseAbsoluteValue)
				.gradientNormalizationThreshold(1.0).list()
				.layer(new Bidirectional(
						new LSTM.Builder().nIn(inputNeurons).nOut(200).activation(Activation.TANH).build()))
				.layer(new RnnOutputLayer.Builder().activation(Activation.SOFTMAX)
						.lossFunction(LossFunctions.LossFunction.MCXENT).nIn(400).nOut(outputs).build())
				.build();

		MultiLayerNetwork net = new MultiLayerNetwork(conf);
		
		net.init();
		
		logger.info(net.summary());
		logger.info("Starting training...");
		
		/**
		 * For the reasons of slow training by my humble GPU and inconvenience of watching the process, 
		 * I don't use the following code to get the best model.
		 
		String tempDir = System.getProperty("java.io.tmpdir");
        String modelDir = FilenameUtils.concat(tempDir, "yelpreview/");
        File dirFile = new File(modelDir); 
        dirFile.mkdir();
		EarlyStoppingModelSaver<MultiLayerNetwork> saver = new LocalFileModelSaver(modelDir);
        @SuppressWarnings({"unchecked", "rawtypes" })
		EarlyStoppingConfiguration<MultiLayerNetwork> esConf = new EarlyStoppingConfiguration.Builder()
                .epochTerminationConditions(new MaxEpochsTerminationCondition(4), new ScoreImprovementEpochTerminationCondition(3)) //Max of 50 epochs
                .evaluateEveryNEpochs(1)
                .iterationTerminationConditions(new MaxTimeIterationTerminationCondition(20, TimeUnit.MINUTES)) //Max of 20 minutes
                .scoreCalculator(new DataSetLossCalculator(iTest, true))     //Calculate test set score
                .modelSaver(saver)
                .build();
        
		EarlyStoppingTrainer trainer = new EarlyStoppingTrainer(esConf,conf,iTrain);

        //Conduct early stopping training:
		EarlyStoppingResult<MultiLayerNetwork> result = trainer.fit();
		logger.info("Termination reason: " + result.getTerminationReason());
		logger.info("Termination details: " + result.getTerminationDetails());
		logger.info("Total epochs: " + result.getTotalEpochs());
		logger.info("Best epoch number: " + result.getBestModelEpoch());
		logger.info("Score at best epoch: " + result.getBestModelScore());
		 */
		
		net.setListeners(new ScoreIterationListener(100), new EvaluativeListener(iTest, 1, InvocationType.EPOCH_END));
		net.fit(iTrain, nEpochs);

		try {
			net.save(new File(modelDir), true);
		} catch (IOException e) {
			e.printStackTrace();
		}

		logger.info("Training is Done");

	}

}
