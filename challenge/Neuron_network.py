#### Libraries
import random

# Library for Algebra
import numpy

class Neuron_network(object):
	def __init__(self, layers_sizes):

		# self
		# layers_size: Number of neurons per layer, i.e. [2,2,2] 2 neurons for input, 2 for hidden layer, 2 for output
		self.layers_sizes = layers_sizes
		self.number_layers = len(layers_sizes)
		self.biases = [numpy.random.randn(y, 1)for y in layers_sizes[1:]]
		self.weights = [numpy.random.randn(y, x) for x, y in zip(layers_sizes[:-1], layers_sizes[1:])]
	# Activation function's methods
	def sigmoid_function(self, z):
		return 1.0/(1.0 + numpy.exp(-z))
	def sigmoid_prime(self, z):
		return self.sigmoid_function(z) * (1 - self.sigmoid_function(z))
	def cost_derivative(self, output_activations, y):
		# returns the derivative of the cost function Cx

		# self
		# output_activations: output activations vector for the tested sets
		# y:

		return (output_activations - y)
	def calculate_output(self, a):

		# self
		# a: input

		for bias, weight in zip(self.biases, self.weights):
			a = self.sigmoid_function(numpy.dot(weight, a) + bias)
		return a
	def evaluate_outputs(self, test_data):

		# returns the evaluations of the test sets

		# self
		# test_data: Sets of test set for testing the score of our network

		tests_outputs = [(numpy.argmax(self.calculate_output(x)), y) for (x, y) in test_data]
		return sum(int(x == y) for (x, y) in tests_outputs)
	def back_propagation(self, x, y):
		# self
		# x
		# y
		new_biases = [numpy.zeros(bias.shape) for bias in self.biases]
		new_weights = [numpy.zeros(weight.shape) for weight in self.weights]
		# Feeding forward process
		activation = x
		activations = [x]
		z_vectors = []
		for bias, weight in zip(self.biases, self.weights):
			z = numpy.dot(weight, activation) + bias
			z_vectors.append(z)
			activation = self.sigmoid_prime(z)
			activations.append(activation)

		# Backward process

		delta = self.cost_derivative(activations[-1], y) * self.sigmoid_prime(z_vectors[-1])
		new_biases[-1] = delta
		new_weights[-1] = numpy.dot(delta, activations[-2].transpose())

		for reverse_layer in xrange(2, self.number_layers):
			z = z_vectors[-reverse_layer]
			sigmoid_p = self.sigmoid_prime(z)
			delta = numpy.dot(self.weights[1 - reverse_layer].transpose(), delta) * sigmoid_p
			new_biases[-reverse_layer] = delta
			new_weights[-reverse_layer] = numpy.dot(delta, activations[-1 - reverse_layer].transpose())
		return (new_biases, new_weights)
	def update_batch(self, batch, learning_rate):
		# self
		# batch: batch for updating the biases and weights
		# learning_rate
		new_biases = [numpy.zeros(biases.shape) for biases in self.biases]
		new_weights = [numpy.zeros(weights.shape)for weights in self.weights]
		for x, y in batch:
			delta_new_biases, delta_new_weights = self.back_propagation(x, y)
			new_biases = [new_bias + delta_bias for new_bias, delta_bias in zip(new_biases, delta_new_biases)]
			new_weights = [new_weight + delta_weight for new_weight, delta_weight in zip(new_weights, delta_new_weights)]
		self.weights = [weight - (learning_rate / len(batch)) * new_weight for weight, new_weight in zip(self.weights, new_weights)]
		self.biases = [bias - (learning_rate / len(batch)) * new_bias for bias, new_bias in zip(self.biases, new_biases)]
	def slow_gradient_descent(self, training_data, epochs, batch_size, learning_rate, test_data = None):
		# self
		# training_data : Complete set of training data
		# epochs: Maximum number of iterations for not overfitting
		# batch_size: For not iterating over all the data a reduced number of groups is used
		# learning_rate
		# test_data: Complete set of test data for testing the network
		if test_data:
			length_test = len(test_data)
		length = len(training_data)
		for i in xrange(epochs):
			random.shuffle(training_data)
			batches = [training_data[j : j + batch_size] for j in xrange(0, length, batch_size)]
			for batch in batches:
				self.update_batch(batch, learning_rate)
			if test_data:
				print "Iteration {0}: {1} of {2}".format( i, self.evaluate_outputs(test_data), length_test)
			else:
				print "Iteration {0} complete".format(i)
