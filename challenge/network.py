##Libraries
import random

# Library for Algebra
import numpy

class Neuron_Network(object):

	def __init__(self, layers_sizes):
		self.layers_sizes = layers_sizes
		self.biases = [numpy.random.randn(y, 1) for y in layers_sizes[1:]]
		self.weights = [numpy.random.randn(y, x) for x, y in zip(layers_sizes[:-1], layers_sizes[1:])]

	def backpropagate_error(self, new_biases, new_weights, activations, output, z_vectors):
		# Back propagation
		delta = self.cost_derivative(activations[-1], output) * sigmoid_derivative(z_vectors[-1])
		new_biases[-1] = delta
		new_weights[-1] = numpy.dot(delta, activations[-2].transpose())
		
		for reverse_layer in xrange(2, len(self.layers_sizes)):
			z = z_vectors[-reverse_layer]
			delta = numpy.dot(self.weights[1 - reverse_layer].transpose(), delta) * sigmoid_derivative(z)
			new_biases[-reverse_layer] = delta
			new_weights[-reverse_layer] = numpy.dot(delta, activations[-1 - reverse_layer].transpose())
			
		return (new_biases, new_weights)

	def full_feed_forward(self, inputs):
		# Feed forward but returning activations and a z vector
		activation = inputs
		activations = [inputs]
		z_vectors = []
		for bias, weight in zip(self.biases, self.weights):
			z = numpy.dot(weight, activation) + bias
			z_vectors.append(z)
			activation = sigmoid(z)
			activations.append(activation)
		return (activations, z_vectors)

	def back_propagation(self, inputs, output):
		# Learning process
		new_biases = [numpy.zeros(bias.shape) for bias in self.biases]
		new_weights = [numpy.zeros(weight.shape) for weight in self.weights]
		# Modified Feed Forward Propagation
		activations, z_vectors = self.full_feed_forward(inputs)
		# Backpropagate error
		new_biases, new_weights = self.backpropagate_error(new_biases, new_weights, activations, output, z_vectors)
		return (new_biases, new_weights)

	def update_subset(self, subset, learning_rate):
		# Updates the neuron network by parts, using subsets tinier than the original training data
		new_biases = [numpy.zeros(bias.shape) for bias in self.biases]
		new_weights = [numpy.zeros(weight.shape) for weight in self.weights]
		for inputs, output in subset:
			delta_biases, delta_weights = self.back_propagation(inputs, output)
			tuple_biases = zip(new_biases, delta_biases)
			tuple_weights = zip(new_weights, delta_weights)
			new_biases = [new_bias + delta_bias for new_bias, delta_bias in tuple_biases]
			new_weights = [new_weight + delta_weight for new_weight, delta_weight in tuple_weights]
		scaled_learning_rate = learning_rate / len(subset)
		self.weights = [weight - scaled_learning_rate * new_weight for weight, new_weight in zip(self.weights, new_weights)]
		self.biases = [bias - scaled_learning_rate * new_bias for bias, new_bias in zip(self.biases, new_biases)]

	def Stochastic_Gradient_Descent(self, training_data, epochs, subset_size, learning_rate, test_data = None):
		# Main function
		if test_data: 
			test_length = len(test_data)
		training_length = len(training_data)
		# Iteration's cycles of training
		for cur_iter in xrange(epochs):
			random.shuffle(training_data)
			# Dividing by subsets
			subsets = [training_data[subset_number: subset_number + subset_size] for subset_number in xrange(0, training_length, subset_size)]
			for subset in subsets:
				self.update_subset(subset, learning_rate)
			if test_data:
				print "Iteration {0}: {1} of {2}".format(cur_iter, self.evaluate_accuracy(test_data), test_length)
			else:
				print "Iteration {0} complete".format(cur_iter)

	def feed_forward(self, a):
		
		# Function returning an array of the outputs of each output neuron
		for bias, weight in zip(self.biases, self.weights):
			a = sigmoid(numpy.dot(weight, a) + bias)
		return a

	def evaluate_accuracy(self, test_data):
		# Function for evaluating the accuracy in the test data, it returns the number of correctly predicted numbers
		test_results = [(numpy.argmax(self.feed_forward(x)), y) for (x, y) in test_data]
		return sum(int(x == y) for (x, y) in test_results)

	def cost_derivative(self, output_activations, y):
		# Function returning a vector of the cost derivatives of the activations
		return (output_activations - y)
		
	def predict_digit(self, digit_data):
		return (numpy.argmax(self.feed_forward(digit_data)))

##General Purpose
def sigmoid(z):
	return 1.0 / (1.0 + numpy.exp(-z))

def sigmoid_derivative(z):
	return sigmoid(z)* (1 - sigmoid(z)) # = e^-z / (1+e^-z)^2